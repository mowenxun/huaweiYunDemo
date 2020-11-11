package com.deepexi.dd.middle.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.*;
import com.deepexi.dd.middle.order.domain.*;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskMiddleRequestQuery;
import com.deepexi.dd.middle.order.service.*;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.dd.middle.order.util.Utils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * SaleOutTaskServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Jun 24 09:42:06 CST 2020
 */
@Service
@Slf4j
public class SaleOutTaskServiceImpl implements SaleOutTaskService {

    @Autowired
    private SaleOutTaskDAO saleOutTaskDao;

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Autowired
    private OrderDeliveryInfoService orderDeliveryInfoService;

    @Autowired
    private SaleOutTaskDetailInfoService saleOutTaskDetailInfoService;

    @Autowired
    private SaleOrderItemService saleOrderItemService;

    @Autowired
    private SaleOrderInfoDAO saleOrderInfoDAO;

    @Autowired
    private OrderDeliveryConsigneeInfoDAO orderDeliveryConsigneeInfoDAO;

    @Autowired
    private OrderDeliveryInfoDAO orderDeliveryInfoDAO;

    @Autowired
    private SaleOutTaskDetailInfoDAO saleOutTaskDetailInfoDAO;

    @Autowired
    private SaleOrderItemDAO saleOrderItemDAO;

    @Autowired
    private OrderDeliverySelfRaisingInfoService orderDeliverySelfRaisingInfoService;


    /**
     * @Description: 查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    public List<SaleOutTaskMiddleResponseDTO> listSaleOutTasks(SaleOutTaskMiddleRequestQuery query) {
        List<SaleOutTaskDO> saleOutTaskDOList = saleOutTaskDao.listSaleOutTasks(query);
        List<SaleOutTaskMiddleResponseDTO> list = ObjectCloneUtils.convertList(saleOutTaskDOList, SaleOutTaskMiddleResponseDTO.class);
        return list;
    }

    @Override
    public List<SaleOutTaskMiddleResponseDTO> listSaleOutTasksAggregation(SaleOutTaskMiddleRequestQuery query) throws Exception {
        List<SaleOutTaskMiddleResponseDTO> resultList = this.listSaleOutTasks(query);
        if (CollectionUtil.isNotEmpty(resultList)) {
            List<Long> idList = resultList.stream().map(SaleOutTaskMiddleResponseDTO::getId).collect(Collectors.toList());//出库单ID集合
            List<OrderDeliveryInfoResponseDTO> OrderDeliveryInfoList = orderDeliveryInfoService.searchOrderDeliveryInfoById(idList);//拿到物流信息
            Map<Long, OrderDeliveryInfoResponseDTO> orderDeliveryInfoMap = OrderDeliveryInfoList.stream().
                    collect(Collectors.toMap(OrderDeliveryInfoResponseDTO::getSaleOutTaskId, Function.identity()));//物流信息
            resultList.stream().forEach(dto -> {
                //物流信息补充
                OrderDeliveryInfoResponseDTO orderDeliveryInfoDTO = orderDeliveryInfoMap.get(dto.getId());
                if (Objects.nonNull(orderDeliveryInfoDTO)) {
                    dto.setDeliveryName(orderDeliveryInfoDTO.getDeliveryName());//物流公司名称
                    dto.setDeliveryCode(orderDeliveryInfoDTO.getDeliveryCode());//物流单号
                    dto.setDeliveryRemark(orderDeliveryInfoDTO.getRemark());//物流备注
                    dto.setDeliveryTime(orderDeliveryInfoDTO.getDeliveryTime());//物流发货时间
                    dto.setDriver(orderDeliveryInfoDTO.getDriver());//司机
                    dto.setDriverMobile(orderDeliveryInfoDTO.getDriverMobile());//司机电话
                    dto.setLicensePlate(orderDeliveryInfoDTO.getLicensePlate());//车牌号码
                }
            });
            return supplySaleOutTaskInfo(resultList);
        }
        return resultList;
    }

    /**
     * @Description: 新增销售出库单.
     * @Param: [saleOutTaskMiddleRequestDTO]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public SaleOutTaskMiddleResponseDTO insert(SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception {
        log.info("出库单传入参数: {}", JSON.toJSONString(saleOutTaskMiddleRequestDTO));
        if (Objects.isNull(saleOutTaskMiddleRequestDTO)) {
            throw new ApplicationException("新增销售出库单（出库任务单）,传入参数为空");
        }
        List<SaleOutTaskDetailInfoMiddleRequestDTO> saleOrderDetailInfoList = saleOutTaskMiddleRequestDTO.getSaleOrderDetailInfo();//商品信息
        if (Utils.isEmpty(saleOrderDetailInfoList)) {//发货新增出库单一定有商品 没商品就是有问题
            throw new ApplicationException("找不到相关商品信息");
        }
        //两种场景 新增则需要计算商品出库总数量=每件商品的出库总数量之和 红冲时则不需要处理
        if (Objects.isNull(saleOutTaskMiddleRequestDTO.getDeliveryQuantity())) {
            saleOutTaskMiddleRequestDTO.setDeliveryQuantity(saleOrderDetailInfoList.stream().mapToLong(SaleOutTaskDetailInfoMiddleRequestDTO::getSkuShipmentQuantity).filter(Objects::nonNull).sum());//汇总
        }
        saleOutTaskMiddleRequestDTO.setSignStatus(17);//先写死 出库默认待收货
        saleOutTaskMiddleRequestDTO.setTicketDate(new Date());//单据日期 默认是创建的日期
        SaleOutTaskDO saleOutTaskDo = saleOutTaskMiddleRequestDTO.clone(SaleOutTaskDO.class);
        saleOutTaskDo.setType("销售出库单");//单据类型写死
        DomainUtils.initDomainCreatedInfo(saleOutTaskDo);//转换基本属性
        log.info("出库单信息: {}", JSON.toJSONString(saleOutTaskDo));
        if (saleOutTaskDao.insert(saleOutTaskDo) > 0) {
            createDeliveryInfo(saleOutTaskMiddleRequestDTO, saleOutTaskDo.getId());//保存出库单物流信息
            createDeliveryConsigneeInfo(saleOutTaskMiddleRequestDTO, saleOutTaskDo.getId());//保存出库单发货信息
            List<SaleOutTaskDetailInfoMiddleRequestDTO> detailInfoList = saleOrderDetailInfoList.stream().map(m -> {
                SaleOutTaskDetailInfoMiddleRequestDTO dto = new SaleOutTaskDetailInfoMiddleRequestDTO();
                dto = saleOutTaskMiddleRequestDTO.clone(SaleOutTaskDetailInfoMiddleRequestDTO.class);//复制原有属性
                dto.setCreatedTime(saleOutTaskDo.getCreatedTime());//创建时间
                dto.setUpdatedTime(saleOutTaskDo.getUpdatedTime());//更新时间
                dto.setSaleOutTaskId(saleOutTaskDo.getId());//出库单ID
                dto.setSaleOrderItemId(m.getSaleOrderItemId());//商品明细ID
                dto.setSkuShipmentQuantity(m.getSkuShipmentQuantity());//本次发货数量
                return dto;
            }).collect(Collectors.toList());
            if (saleOutTaskDetailInfoService.insert(detailInfoList)) {//新增商品信息
                return saleOutTaskMiddleRequestDTO.clone(SaleOutTaskMiddleResponseDTO.class, CloneDirection.OPPOSITE);
            }
        }
        log.error("新增销售出库单（出库任务单）失败");
        return null;
    }

    /**
     * @Description: 保存出库单物流信息.
     * @Param: [dto, id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    private Boolean createDeliveryInfo(SaleOutTaskMiddleRequestDTO dto, Long id) {
        OrderDeliveryInfoDO orderDeliveryInfo = dto.getOrderDeliveryInfo().clone(OrderDeliveryInfoDO.class);//复制原有属性
        orderDeliveryInfo.setTenantId(dto.getTenantId());//企业ID
        orderDeliveryInfo.setAppId(dto.getAppId());//appId
        orderDeliveryInfo.setVersion(dto.getVersion());//版本号
        orderDeliveryInfo.setSaleOutTaskId(id);//出库单ID
        orderDeliveryInfo.setSaleOutTaskCode(dto.getCode());//出库单编号
        orderDeliveryInfo.setCreatedBy(dto.getCreatedBy());//创建人
        orderDeliveryInfo.setUpdatedBy(dto.getUpdateBy());//修改人
        DomainUtils.initDomainCreatedInfo(orderDeliveryInfo);
        return orderDeliveryInfoDAO.save(orderDeliveryInfo);
    }

    /**
     * @Description: 保存出库单发货信息.
     * @Param: [dto, id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    private Boolean createDeliveryConsigneeInfo(SaleOutTaskMiddleRequestDTO dto, Long id) {
        OrderDeliveryConsigneeInfoDO orderDeliveryConsigneeInfoDO = dto.getOrderDeliveryConsigneeInfo().clone(OrderDeliveryConsigneeInfoDO.class);//复制原有属性
        orderDeliveryConsigneeInfoDO.setTenantId(dto.getTenantId());//企业ID
        orderDeliveryConsigneeInfoDO.setAppId(dto.getAppId());//appId
        orderDeliveryConsigneeInfoDO.setVersion(dto.getVersion());//版本号
        orderDeliveryConsigneeInfoDO.setSaleOutTaskId(id);//出库单ID
        orderDeliveryConsigneeInfoDO.setSaleOutTaskCode(dto.getCode());//出库单编号
        orderDeliveryConsigneeInfoDO.setCreatedBy(dto.getCreatedBy());//创建人
        orderDeliveryConsigneeInfoDO.setUpdatedBy(dto.getUpdateBy());//修改人
        DomainUtils.initDomainCreatedInfo(orderDeliveryConsigneeInfoDO);
        return orderDeliveryConsigneeInfoDAO.save(orderDeliveryConsigneeInfoDO);
    }

    /**
     * @Description: 分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    public PageBean<SaleOutTaskMiddleResponseDTO> listSaleOutTasksPage(SaleOutTaskMiddleRequestQuery query) throws Exception {
        if (Objects.isNull(query)) {
            throw new ApplicationException("查询销售出库单,传入参数为空");
        }
        if (Utils.isNotEmpty(query.getBuyerName())) {//如果客户名称不为空，则处理
            List<Long> saleOrderIdList = saleOrderInfoService.getSaleOrderIdListByBuyerName(query.getBuyerName());//根据客户拿到所有订单ID
            if (Utils.isEmpty(saleOrderIdList)) {
                return new PageBean<>(Lists.newArrayList());
            }
            query.setSaleOrderIdList(saleOrderIdList);
        }
        PageHelper.startPage(query.getPage(),query.getSize(),"ticket_date desc");
        List<SaleOutTaskDO> saleOutTaskDOList = saleOutTaskDao.listSaleOutTasks(query);//先拿到出库单信息
        List<SaleOutTaskMiddleResponseDTO> list = ObjectCloneUtils.convertList(saleOutTaskDOList, SaleOutTaskMiddleResponseDTO.class);
        if (Utils.isEmpty(list)) {
            return new PageBean<>(Lists.newArrayList());
        }
        return new PageBean<>(supplySaleOutTaskInfo(list));
    }

    /**
     * @Description: 出库单列表查询补充物流信息及订单信息.
     * @Param: [list]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    private List<SaleOutTaskMiddleResponseDTO> supplySaleOutTaskInfo(List<SaleOutTaskMiddleResponseDTO> list) throws Exception {
        List<Long> saleOrderIdList = list.stream().map(SaleOutTaskMiddleResponseDTO::getSaleOrderId).collect(Collectors.toList());//订单ID集合
        List<Long> idList = list.stream().map(SaleOutTaskMiddleResponseDTO::getId).collect(Collectors.toList());//出库单ID集合
        List<SaleOrderInfoResponseDTO> saleOrderInfoList = saleOrderInfoService.searchSaleOrderInfoById(saleOrderIdList);//拿到销售订单信息
        List<OrderDeliveryInfoResponseDTO> OrderDeliveryInfoList = orderDeliveryInfoService.searchOrderDeliveryInfoById(idList);//拿到物流信息
        List<OrderDeliveryConsigneeInfoMiddleResponseDTO> orderDeliveryConsigneeInfoList = searchDeliveryConsigneeInfo(idList);//拿到收货信息
        //转成Map 用于获取数据
        Map<Long, SaleOrderInfoResponseDTO> saleOrderInfoMap = saleOrderInfoList.stream().
                collect(Collectors.toMap(SaleOrderInfoResponseDTO::getId, Function.identity()));//订单信息
        Map<Long, OrderDeliveryInfoResponseDTO> orderDeliveryInfoMap = OrderDeliveryInfoList.stream().
                collect(Collectors.toMap(OrderDeliveryInfoResponseDTO::getSaleOutTaskId, Function.identity()));//物流信息
        Map<Long, OrderDeliveryConsigneeInfoMiddleResponseDTO> orderConsigneeInfoMap = orderDeliveryConsigneeInfoList.stream().
                collect(Collectors.toMap(OrderDeliveryConsigneeInfoMiddleResponseDTO::getSaleOutTaskId, Function.identity()));//收货信息
        list.stream().forEach(dto -> {
            //订单信息补充
            SaleOrderInfoResponseDTO saleOrderInfoDTO = saleOrderInfoMap.get(dto.getSaleOrderId());
            if (Objects.nonNull(saleOrderInfoDTO)) {
                dto.setBuyerName(saleOrderInfoDTO.getBuyerName());//客户名称
                dto.setHandlerName(saleOrderInfoDTO.getHandlerName());//经手人
                dto.setDepartment(saleOrderInfoDTO.getDepartment());//部门名称
                dto.setShippingType(saleOrderInfoDTO.getShippingType());//送货方式
                dto.setSaleOrderRemark(saleOrderInfoDTO.getRemark());//物流备注
                dto.setTotalAmount(saleOrderInfoDTO.getTotalAmount());//总商品金额
                dto.setDiscountAmount(saleOrderInfoDTO.getDiscountAmount());//促销优惠金额
                dto.setAccrueAmount(saleOrderInfoDTO.getAccrueAmount());//应收金额
                dto.setTotalExpense(saleOrderInfoDTO.getTotalExpense());//其他费用合计
            }
            //物流信息补充
            OrderDeliveryInfoResponseDTO orderDeliveryInfoDTO = orderDeliveryInfoMap.get(dto.getId());
            if (Objects.nonNull(orderDeliveryInfoDTO)) {
                dto.setDeliveryName(orderDeliveryInfoDTO.getDeliveryName());//物流公司名称
                dto.setDeliveryCode(orderDeliveryInfoDTO.getDeliveryCode());//物流单号
                dto.setDeliveryRemark(orderDeliveryInfoDTO.getRemark());//物流备注
                dto.setDeliveryTime(orderDeliveryInfoDTO.getDeliveryTime());//物流发货时间
            }
            //收货信息补充
            OrderDeliveryConsigneeInfoMiddleResponseDTO orderConsigneeInfoDTO = orderConsigneeInfoMap.get(dto.getId());
            if (Objects.nonNull(orderConsigneeInfoDTO)) {
                dto.setConsignee(orderConsigneeInfoDTO.getConsignee());//收货人
                dto.setMobile(orderConsigneeInfoDTO.getMobile());//手机号码
                dto.setShippingAddress(splicingShippingAddressForSaleOutTask(orderConsigneeInfoDTO));//收货地址
            }
        });
        return list;
    }

    /**
     * @Description: 拼接收货地址.
     * @Param: [dto]
     * @return: java.lang.String
     * @Author: SongTao
     * @Date: 2020/7/22
     */
    private String splicingShippingAddressForSaleOutTask(OrderDeliveryConsigneeInfoMiddleResponseDTO dto) {
        StringBuilder builder = new StringBuilder();
        builder.append(dto.getProvinceName());//省份名称
        builder.append(dto.getCityName());//城市名称
        builder.append(dto.getAreaName());//区域名称
        builder.append(dto.getStreetName());//街道名称
        builder.append(dto.getDetailedAddress());//详细地址
        return builder.toString();
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售出库单（出库任务单）,传入ID为空");
            return false;
        }
        return saleOutTaskDao.deleteByIdIn(id) == id.size();
    }

    /**
     * @Description: 根据id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    @Override
    public SaleOutTaskMiddleResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw new ApplicationException("查询销售出库单（出库任务单）,传入ID为空");
        }
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskMiddleRequestDTOList = searchSaleOutTaskInfo("id", id);
        if (Utils.isEmpty(saleOutTaskMiddleRequestDTOList)) {//如果为空则证明id错误
            throw new ApplicationException("找不到相关出库单信息");
        }
        return supplySaleOutTaskDetailInfo(Utils.selectOne(saleOutTaskMiddleRequestDTOList));
    }

    /**
     * @param id
     * @Description: 通过订单ID查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @Override
    public List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoBySaleOrderId(Long id, Integer signStatus) throws Exception {
        List<SaleOutTaskMiddleResponseDTO> saleList = Lists.newArrayList();
        if (Objects.isNull(id)) {
            throw new ApplicationException("查询销售出库单（出库任务单）,传入订单ID为空");
        }
        SaleOutTaskMiddleRequestQuery query = new SaleOutTaskMiddleRequestQuery();
        query.setSaleOrderIdList(Arrays.asList(id));
        if (!ObjectUtils.equals(signStatus, -1)) {
            query.setSignStatusList(Arrays.asList(signStatus));
        }
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskMiddleRequestDTOList = listSaleOutTasks(query);
        return saleOutTaskMiddleRequestDTOList.stream().map(map -> {
            SaleOutTaskMiddleResponseDTO dto = new SaleOutTaskMiddleResponseDTO();
            try {
                dto = searchSaleOutTaskInfoBySaleOutTaskId(map.getId());
            } catch (Exception e) {
                return dto;
            }
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * @param id
     * @param signStatus
     * @Description: 根据提货单号查销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @Override
    public List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoByPickGoodsId(Long id, Integer signStatus) throws Exception {
        List<SaleOutTaskMiddleResponseDTO> saleList = Lists.newArrayList();
        if (Objects.isNull(id)) {
            throw new ApplicationException("查询销售出库单,传入订单ID为空");
        }
        SaleOutTaskMiddleRequestQuery query = new SaleOutTaskMiddleRequestQuery();
        query.setSalePickGoodsId(id);
        if (!ObjectUtils.equals(signStatus, -1)) {
            query.setSignStatusList(Arrays.asList(signStatus));
        }
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskMiddleRequestDTOList = listSaleOutTasks(query);
        return saleOutTaskMiddleRequestDTOList.stream().map(map -> {
            SaleOutTaskMiddleResponseDTO dto = new SaleOutTaskMiddleResponseDTO();
            try {
                dto = searchSaleOutTaskInfoBySaleOutTaskId(map.getId());
            } catch (Exception e) {
                return dto;
            }
            log.info("dto:{}",JSON.toJSONString(dto));
            return dto;
        }).collect(Collectors.toList());
    }
    /**
     * @param id
     * @Description: 根据主键id查询出库单.
     * @Param: [id]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/20
     */
    @Override
    public SaleOutTaskMiddleResponseDTO selectById(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw new ApplicationException("查询出库单,传入id为空");
        }
        List<SaleOutTaskMiddleResponseDTO> list = searchSaleOutTaskInfo("id", id);
        if (Utils.isEmpty(list)) {
            throw new ApplicationException("查询出库单,找不到相关参数");
        }
        return Utils.selectOne(list);
    }

    /**
     * @Description: 补充销售出库单详情数据（物流信息/商品信息）.
     * @Param: [dto]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    private SaleOutTaskMiddleResponseDTO supplySaleOutTaskDetailInfo(SaleOutTaskMiddleResponseDTO dto) throws Exception {
        List<SaleOutTaskDetailInfoMiddleResponseDTO> responseDTOList = saleOutTaskDetailInfoService.searchSaleOutTaskDetailInfo("sale_out_task_id", dto.getId());//根据出库单拿到对应的多个商品信息
        List<Long> saleOrderItemIdList = responseDTOList.stream().map(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderItemId).collect(Collectors.toList());//商品明细ID
        SaleOrderItemMiddleRequestQuery saleOrderItemQuery = new SaleOrderItemMiddleRequestQuery();
        saleOrderItemQuery.setIdList(saleOrderItemIdList);
        PageBean<SaleOrderItemMiddleResponseDTO> saleOrderItemDTOPageBean = saleOrderItemService.listSaleOrderItemsPage(saleOrderItemQuery);//拿到分页后的商品信息
        dto.setSaleOrderItemList(saleOrderItemDTOPageBean.getContent());//商品列表
        List<OrderDeliveryInfoResponseDTO> orderDeliveryInfoList = orderDeliveryInfoService.searchOrderDeliveryInfoById(Arrays.asList(dto.getId()));//拿到物流信息
        List<Long> idList = Arrays.asList(dto.getId());
        List<OrderDeliveryConsigneeInfoMiddleResponseDTO> orderDeliveryConsigneeInfoList = searchDeliveryConsigneeInfo(idList);//拿到收货信息
        if (Utils.isNotEmpty(orderDeliveryInfoList)) {//一一对应 如果list存在数据 那么一定是一比
            OrderDeliveryInfoResponseDTO orderDeliveryInfoDTO = Utils.selectOne(orderDeliveryInfoList);
            dto.setDeliveryName(orderDeliveryInfoDTO.getDeliveryName());//物流公司名称
            dto.setDeliveryCode(orderDeliveryInfoDTO.getDeliveryCode());//物流单号
            dto.setDeliveryRemark(orderDeliveryInfoDTO.getRemark());//物流备注
            //dto.setDeliveryTime(orderDeliveryInfoDTO.getDeliveryTime());//物流发货时间
            dto.setDriver(orderDeliveryInfoDTO.getDriver());//司机
            dto.setDriverMobile(orderDeliveryInfoDTO.getDriverMobile());//司机电话
            dto.setIdCardNum(orderDeliveryInfoDTO.getIdCardNum());//身份证
            dto.setLicensePlate(orderDeliveryInfoDTO.getLicensePlate());//车牌号码
        }
        if (Utils.isNotEmpty(orderDeliveryConsigneeInfoList)) {//一一对应 如果list存在数据 那么一定是一比
            OrderDeliveryConsigneeInfoMiddleResponseDTO middleResponseDTO = Utils.selectOne(orderDeliveryConsigneeInfoList);
            dto.setConsignee(middleResponseDTO.getConsignee());//收货人
            dto.setMobile(middleResponseDTO.getMobile());//手机号码
            dto.setLicensePlate(Objects.isNull(dto.getLicensePlate())?middleResponseDTO.getLicensePlate():dto.getLicensePlate());//车牌号
            dto.setSaleRaisingName(middleResponseDTO.getSaleRaisingName());//提货人
            dto.setTelephone(middleResponseDTO.getTelephone());//联系电话
            dto.setIdCardNumber(middleResponseDTO.getIdCardNumber());//电话号码
            dto.setShippingAddress(splicingShippingAddressForSaleOutTask(middleResponseDTO));//收货地址
            //获得出库单自提地址信息
            OrderDeliverySelfRaisingInfoRequestDTO orderDeliverySelfRaisingInfoDTO=
                    middleResponseDTO.clone(OrderDeliverySelfRaisingInfoRequestDTO.class);
                   // new OrderDeliverySelfRaisingInfoRequestDTO();
            orderDeliverySelfRaisingInfoDTO.setSaleRaisingName(middleResponseDTO.getConsignee());//收货人
            orderDeliverySelfRaisingInfoDTO.setMobile(middleResponseDTO.getMobile());//手机号码
            orderDeliverySelfRaisingInfoDTO.setCarNumber(Objects.isNull(dto.getLicensePlate())?
                    middleResponseDTO.getLicensePlate():dto.getLicensePlate());//车牌号
            orderDeliverySelfRaisingInfoDTO.setSaleRaisingName(middleResponseDTO.getSaleRaisingName());//提货人
            orderDeliverySelfRaisingInfoDTO.setWarehouseMobile(middleResponseDTO.getTelephone());//联系电话
            orderDeliverySelfRaisingInfoDTO.setIdentityCard(middleResponseDTO.getIdCardNumber());//电话号码
            orderDeliverySelfRaisingInfoDTO.setDetailedAddress(splicingShippingAddressForSaleOutTask(middleResponseDTO));//收货地址
            dto.setOrderDeliverySelfRaisingInfoDTO(orderDeliverySelfRaisingInfoDTO);
        }
        return dto;
    }

    /**
     * @Description: 根据ID修改销售出库单（出库任务单）.
     * @Param: [record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/9
     */
    @Override
    public Boolean updateById(SaleOutTaskMiddleRequestDTO record) throws Exception {
        if (Objects.isNull(record.getId())) {
            throw new ApplicationException("修改销售出库单（出库任务单）,传入ID为空");
        }
        SaleOutTaskDO saleOutTaskDo = record.clone(SaleOutTaskDO.class);
        DomainUtils.initUpdateTimeInfo(saleOutTaskDo);
        return saleOutTaskDao.updateById(saleOutTaskDo);
    }

    /**
     * @param id
     * @Description: 根据出库单主键ID红冲.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/8
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean hedgeOrder(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw new ApplicationException("id为空");
        }
        List<SaleOutTaskMiddleResponseDTO> responseDTOList = searchSaleOutTaskInfo("id", id);
        if (Utils.isEmpty(responseDTOList)) {//如果为空则证明id错误
            throw new ApplicationException("找不到相关出库单信息");
        }
        SaleOutTaskMiddleResponseDTO outTaskMiddleResponseDTO = Utils.selectOne(responseDTOList);
        if (ObjectUtils.equals(outTaskMiddleResponseDTO.getTaskType(), 2)) {
            throw new ApplicationException("该出库单ID：" + id + "已被红冲.");
        }
        SaleOutTaskDO saleOutTaskDO = outTaskMiddleResponseDTO.clone(SaleOutTaskDO.class);
        saleOutTaskDO.setTaskType(2);//当前单生成蓝单
        DomainUtils.initUpdateTimeInfo(saleOutTaskDO);
        saleOutTaskDao.updateById(saleOutTaskDO);//修改当前单
        List<SaleOutTaskDetailInfoMiddleResponseDTO> saleOutTaskDetailInfoList = saleOutTaskDetailInfoService.searchSaleOutTaskDetailInfo("sale_out_task_id", id);//根据出库单拿到对应的多个商品信息
        SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO = outTaskMiddleResponseDTO.clone(SaleOutTaskMiddleRequestDTO.class);
        saleOutTaskMiddleRequestDTO.setId(null);
        saleOutTaskMiddleRequestDTO.setTaskType(3);
        saleOutTaskMiddleRequestDTO.setDeliveryQuantity(0 - saleOutTaskMiddleRequestDTO.getDeliveryQuantity().longValue());
        saleOutTaskMiddleRequestDTO.setSaleOrderDetailInfo(ObjectCloneUtils.convertList(saleOutTaskDetailInfoList, SaleOutTaskDetailInfoMiddleRequestDTO.class));
        insert(saleOutTaskMiddleRequestDTO);//再重新创建一笔红单出库单
        return updateSaleOrderShipmentStatus(outTaskMiddleResponseDTO.getSaleOrderId(), outTaskMiddleResponseDTO.getCode());
    }

    /**
     * @Description: 红冲时修改销售订单的状态及发货状态.
     * @Param: [saleOrderId, code]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/9
     */
    private Boolean updateSaleOrderShipmentStatus(Long saleOrderId, String code) throws Exception {
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskInfoList = searchSaleOutTaskInfo("sale_order_id", saleOrderId);//根据订单拿到多个出库单
        //不等于本身出库单并且该订单下还有其他未红冲的出库单的数量
        long count = saleOutTaskInfoList.stream().filter(f -> (!ObjectUtils.equals(f.getCode(), code)) && ObjectUtils.equals(f.getTaskType(), 1)).count();
        SaleOrderInfoDO saleOrderInfoDO = new SaleOrderInfoDO();
        saleOrderInfoDO.setId(saleOrderId);
        if (count == 0) {
            saleOrderInfoDO.setStatus(0);//订单状态 待发货
            saleOrderInfoDO.setShipmentStatus(0);//未发货
        } else {
            saleOrderInfoDO.setShipmentStatus(2);//部分发货
        }
        DomainUtils.initUpdateTimeInfo(saleOrderInfoDO);
        return saleOrderInfoDAO.updateById(saleOrderInfoDO);
    }

    /**
     * @param saleOutTaskMiddleRequestDTO
     * @Description: 出库单签收.
     * @Param: [saleOutTaskMiddleRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean sign(SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception {
        log.info("签收Middle层--入参: {}", JSON.toJSONString(saleOutTaskMiddleRequestDTO));
        if (Objects.isNull(saleOutTaskMiddleRequestDTO)) {
            throw new ApplicationException("出库单签收失败,传入参数为空");
        }
        if (Utils.isEmpty(saleOutTaskMiddleRequestDTO.getIdList())) {
            throw new ApplicationException("出库单签收失败,传入id集合为空");
        }
        List<Long> idList = saleOutTaskMiddleRequestDTO.getIdList();
        List<SaleOutTaskMiddleResponseDTO> responseDTOList = searchSaleOutTaskInfoById(idList);
        if (idList.size() != responseDTOList.size()) {//传入ID异常
            throw new ApplicationException("出库单签收,输入ID有误.");
        }
        Long count = responseDTOList.stream().map(SaleOutTaskMiddleResponseDTO::getSaleOrderId).distinct().count();//订单数量
        List<Long> signList = responseDTOList.stream().filter(f -> ObjectUtils.equals(f.getSignStatus(), 19)).
                map(SaleOutTaskMiddleResponseDTO::getSaleOrderId).collect(Collectors.toList());//被签收的出库单id
        if (count != 1) {//签收 不管单个还是多个出库单 对应的订单一定是一个
            throw new ApplicationException("出库单签收,输入ID只能对应一个订单");
        }
        if (Utils.isNotEmpty(signList)) {
            throw new ApplicationException("该出库单ID" + signList + "已被签收.无法重复签收");
        }
        updateSaleOrderSignQuantity(responseDTOList);//更新订单签收数量
        updateSaleOrderItemQuantity(idList);//更改商品签收数量
        Date now = new Date();
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskList = responseDTOList.stream().map(map -> {//更改出库单状态
            SaleOutTaskMiddleResponseDTO dto = new SaleOutTaskMiddleResponseDTO();
            dto.setId(map.getId());
            dto.setSignStatus(19);//已签收
            dto.setUpdatedTime(now);
            dto.setSignBy(saleOutTaskMiddleRequestDTO.getUpdateBy());//签收人
            dto.setSignTime(now);
            return dto;
        }).collect(Collectors.toList());
        List<SaleOutTaskDO> list = ObjectCloneUtils.convertList(saleOutTaskList, SaleOutTaskDO.class);
        return saleOutTaskDao.updateBatchById(list);
    }

    /**
     * @param day
     * @Description: 自动签收.
     * @Param: [day]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @Override
    public Boolean autoSign(Integer day) throws Exception {
        List<Long> idList = saleOutTaskDao.selectIdListForAutoSign(day);//先找到最近天数符合条件的数据
        if (Utils.isNotEmpty(idList)) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.in("id",idList);
            queryWrapper.eq("sign_status",17);
            queryWrapper.eq("is_deleted",0);
            List<SaleOutTaskDO> saleOutTaskDOList = saleOutTaskDao.list(queryWrapper);
            List<SaleOutTaskMiddleResponseDTO> saleOutTaskList = ObjectCloneUtils.convertList(saleOutTaskDOList, SaleOutTaskMiddleResponseDTO.class);
            List<SaleOutTaskDO> list = saleOutTaskList.stream().map(map -> {
                SaleOutTaskDO saleOutTaskDO = new SaleOutTaskDO();
                saleOutTaskDO.setId(map.getId());
                saleOutTaskDO.setSignStatus(19);
                saleOutTaskDO.setSignBy("admin");
                saleOutTaskDO.setSignTime(new Date());
                return saleOutTaskDO;
            }).collect(Collectors.toList());
            log.info("自动签收处理的出库单id集合：" + idList.toString());
            //TODO 出库得记录操作人及时间
            return saleOutTaskDao.updateBatchById(list);
        } else {
            log.info("未找到这" + day + "天内的出库单信息：");
            return Boolean.TRUE;
        }
    }

    @Override
    public List<SaleOutTaskMiddleResponseDTO> findSaleOutTaskByIds(SaleOutTaskMiddleRequestQuery query) {
        List<SaleOutTaskDO> saleOutList = saleOutTaskDao.findSaleOutTaskByIds(query.getSaleOrderIdList());
        List<SaleOutTaskMiddleResponseDTO> list = ObjectCloneUtils.convertList(saleOutList, SaleOutTaskMiddleResponseDTO.class);
        return list;
    }

    /**
     * @Description: 签收时更改当前出库单关联的商品签收数量.
     * @Param: [idList]
     * @return: java.util.Map<java.lang.Long,java.lang.Long>
     * @Author: SongTao
     * @Date: 2020/7/17
     */
    private void updateSaleOrderItemQuantity(List<Long> idList) throws Exception {
        QueryWrapper<SaleOutTaskDetailInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(SaleOutTaskDetailInfoDO::getSaleOutTaskId, idList).eq(SaleOutTaskDetailInfoDO::getDeleted, 0);
        List<SaleOutTaskDetailInfoDO> saleOutTaskDetailInfoDOList = saleOutTaskDetailInfoDAO.list(queryWrapper);//根据批量的出库单号拿到批量的商品关联的出库信息
        List<SaleOutTaskDetailInfoMiddleResponseDTO> list = ObjectCloneUtils.convertList(saleOutTaskDetailInfoDOList, SaleOutTaskDetailInfoMiddleResponseDTO.class);
        final Map<Long, Long> itemMap = list.stream().collect(Collectors.toMap(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderItemId,
                SaleOutTaskDetailInfoMiddleResponseDTO::getSkuShipmentQuantity,
                (skuShipmentQuantity, oldSkuShipmentQuantity) -> {
                    oldSkuShipmentQuantity = skuShipmentQuantity + oldSkuShipmentQuantity;
                    return oldSkuShipmentQuantity;
                }));//每比商品对应的发货数
        //商品id集合
        List<Long> itemIdList = list.stream().map(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderItemId).distinct().collect(Collectors.toList());
        QueryWrapper<SaleOrderItemDO> doQueryWrapper = new QueryWrapper();
        doQueryWrapper.lambda().in(SaleOrderItemDO::getId, itemIdList).eq(SaleOrderItemDO::getDeleted, 0);
        List<SaleOrderItemDO> saleOrderItemDOList = saleOrderItemDAO.list(doQueryWrapper);//拿到商品信息
        List<SaleOrderItemMiddleResponseDTO> saleOrderItemMiddleResponseDTOList = ObjectCloneUtils.convertList(saleOrderItemDOList, SaleOrderItemMiddleResponseDTO.class);
        Date now = new Date();
        log.info("签收时更改当前出库单关联的商品签收数量--商品数据: {}", JSON.toJSONString(saleOrderItemMiddleResponseDTOList));
        List<SaleOrderItemMiddleResponseDTO> saleOrderItemList = saleOrderItemMiddleResponseDTOList.stream().map(m -> {
            SaleOrderItemMiddleResponseDTO dto = new SaleOrderItemMiddleResponseDTO();
            Long itemSignQuantity = m.getSignQuantity();//商品总签收数量
            Long skuShipmentQuantity = itemMap.get(m.getId());//商品本次签收数量
            if (Objects.isNull(itemSignQuantity)) {
                itemSignQuantity = 0L;
            }
            itemSignQuantity += skuShipmentQuantity;
            dto.setId(m.getId());//商品明细id
            dto.setSignQuantity(itemSignQuantity);//商品的签收数量
            dto.setUpdatedTime(now);//修改时间
            return dto;
        }).collect(Collectors.toList());
        List<SaleOrderItemDO> itemDOList = ObjectCloneUtils.convertList(saleOrderItemList, SaleOrderItemDO.class);
        saleOrderItemDAO.updateBatchById(itemDOList);
    }

    /**
     * @Description: 签收时更改当前订单的签收数量.
     * @Param: [list]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/7/17
     */
    private void updateSaleOrderSignQuantity(List<SaleOutTaskMiddleResponseDTO> list) throws Exception {
        if (Utils.isEmpty(list)) {
            throw new ApplicationException("出库单签收,关联的订单数据为空");
        }
        List<Long> saleOrderIdList = list.stream().map(SaleOutTaskMiddleResponseDTO::getSaleOrderId).distinct().collect(Collectors.toList());
        Long saleOrderId = Utils.selectOne(saleOrderIdList);//订单
        Long signQuantity = list.stream().mapToLong(SaleOutTaskMiddleResponseDTO::getDeliveryQuantity).sum();//指定出库单的总出库量 也就是签收的数量
        QueryWrapper<SaleOrderInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SaleOrderInfoDO::getId, saleOrderId).eq(SaleOrderInfoDO::getDeleted, 0);
        List<SaleOrderInfoDO> saleOrderInfoDOList = saleOrderInfoDAO.list(queryWrapper);//拿到对应的订单信息
        SaleOrderInfoDO saleOrderInfoDO = Utils.selectOne(saleOrderInfoDOList);
        SaleOrderInfoResponseDTO orderInfoResponseDTO = saleOrderInfoDO.clone(SaleOrderInfoResponseDTO.class);
        Long totalSignQuantity = orderInfoResponseDTO.getTotalSignQuantity();//商品总签收数
        Long quantity = orderInfoResponseDTO.getQuantity();//总数量
        log.info("签收时更改当前订单的签收数量--商品总签收数: {}--商品总数量: {}", totalSignQuantity.toString(),quantity.toString());
        if (Objects.isNull(totalSignQuantity)) {
            totalSignQuantity = 0L;
        }
        totalSignQuantity += signQuantity;
        if (totalSignQuantity <= quantity) {
            saleOrderInfoDO.setTotalSignQuantity(totalSignQuantity);//更新签收数
        } else {
            throw new ApplicationException("商品库存不足.");
        }
        DomainUtils.initUpdateTimeInfo(saleOrderInfoDO);
        saleOrderInfoDAO.updateById(saleOrderInfoDO);
    }

    /**
     * @Description: 根据主键ID集合拿到对应的出库单信息.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/16
     */
    private List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoById(List<Long> idList) throws Exception {
        QueryWrapper<SaleOutTaskDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(SaleOutTaskDO::getId, idList).eq(SaleOutTaskDO::getDeleted, 0);
        List<SaleOutTaskDO> saleOrderItemDOList = saleOutTaskDao.list(queryWrapper);
        List<SaleOutTaskMiddleResponseDTO> responseDTOList = ObjectCloneUtils.convertList(saleOrderItemDOList, SaleOutTaskMiddleResponseDTO.class);
        return responseDTOList;
    }

    /**
     * @Description: 根据动态字段查出库单信息.
     * @Param: [field, value]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/9
     */
    private List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfo(Object field, Object value) throws Exception {
        if (Objects.isNull(field) || Objects.isNull(value)) {
            throw new ApplicationException("查询出库单信息,传入参数为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(field, value);
        queryWrapper.eq("is_deleted", 0);
        List<SaleOutTaskDO> list = saleOutTaskDao.list(queryWrapper);
        return ObjectCloneUtils.convertList(list, SaleOutTaskMiddleResponseDTO.class);
    }

    /**
     * @Description: 通过出库单ID查发货信息.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.OrderDeliveryConsigneeInfoMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/22
     */
    private List<OrderDeliveryConsigneeInfoMiddleResponseDTO> searchDeliveryConsigneeInfo(List<Long> idList) throws Exception {
        if (Utils.isEmpty(idList)) {
            throw new ApplicationException("查询发货信息,出库单id集合为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("sale_out_task_id", idList);
        queryWrapper.eq("is_deleted", 0);
        List<OrderConsigneeInfoDO> list = orderDeliveryConsigneeInfoDAO.list(queryWrapper);
        return ObjectCloneUtils.convertList(list, OrderDeliveryConsigneeInfoMiddleResponseDTO.class);
    }
}