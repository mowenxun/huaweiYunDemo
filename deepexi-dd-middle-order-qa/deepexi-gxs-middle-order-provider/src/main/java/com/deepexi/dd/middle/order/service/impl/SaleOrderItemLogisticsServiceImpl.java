package com.deepexi.dd.middle.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.*;
import com.deepexi.dd.middle.order.domain.*;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemLogisticsQuery;
import com.deepexi.dd.middle.order.service.SaleOrderInfoService;
import com.deepexi.dd.middle.order.service.SaleOrderItemLogisticsService;
import com.deepexi.dd.middle.order.service.SaleOutTaskService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.dd.middle.order.util.Utils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * SaleOrderItemLogisticsServiceImpl
 *
 * @author admin
 * @date Sat Aug 22 16:34:04 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleOrderItemLogisticsServiceImpl implements SaleOrderItemLogisticsService {

    @Autowired
    private SaleOrderItemLogisticsDAO saleOrderItemLogisticsDao;

    @Autowired
    private SaleOrderItemDAO saleOrderItemDAO;

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Autowired
    private SaleOutTaskDetailInfoDAO saleOutTaskDetailInfoDAO;

    @Autowired
    private OrderConsigneeInfoDAO orderConsigneeInfoDAO;

    @Autowired
    private SaleOrderInfoDAO saleOrderInfoDAO;

    @Autowired
    private OrderDeliveryInfoDAO orderDeliveryInfoDAO;

    @Autowired
    private SaleOutTaskService saleOutTaskService;

    @Override
    public List<SaleOrderItemLogisticsDTO> listSaleOrderItemLogisticss(SaleOrderItemLogisticsQuery query) {
        return ObjectCloneUtils.convertList(saleOrderItemLogisticsDao.listSaleOrderItemLogisticss(query), SaleOrderItemLogisticsDTO.class);
    }


    @Override
    public PageBean<SaleOrderItemLogisticsDTO> listSaleOrderItemLogisticssPage(SaleOrderItemLogisticsQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleOrderItemLogisticsDao.listSaleOrderItemLogisticss(query)), SaleOrderItemLogisticsDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除OMS物流信息,传入ID为空");
            return false;
        }
        return saleOrderItemLogisticsDao.deleteByIdIn(id) == id.size();
    }
    /**
     * @Description:  新增OMS物流信息.
     * @Param: [record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/23
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Long insert(SaleOrderItemLogisticsDTO record) throws Exception{
        log.info("oms物流信息:{}",JSON.toJSONString(record));
        if (Objects.isNull(record)) {
            throw new ApplicationException("新增OMS物流信息,传入参数为空");
        }
        SaleOrderItemLogisticsDO saleOrderItemLogisticsDo = record.clone(SaleOrderItemLogisticsDO.class);
        saleOrderItemLogisticsDo.setCreatedBy("OMS");
        DomainUtils.initDomainCreatedInfo(saleOrderItemLogisticsDo);
        if (saleOrderItemLogisticsDao.insert(saleOrderItemLogisticsDo) == 0) {
            throw new ApplicationException("新增物流信息异常");
        }
        //生成出库单
        return generateSaleOutTaskInfo(record);
    }
    /**
     * @Description:  生成出库单.
     * @Param: [dto]
     * @return: Long
     * @Author: SongTao
     * @Date: 2020/8/23
     */
    private Long generateSaleOutTaskInfo(SaleOrderItemLogisticsDTO dto) throws Exception{
        Optional.ofNullable(dto).orElseThrow(() -> new ApplicationException("OMS同步发货信息参数为空."));
        log.info("同步发货信息:{}", JSON.toJSONString(dto));
        //查询订单sku信息
        QueryWrapper<SaleOrderItemDO> query = new QueryWrapper<>();
        query.lambda().eq(SaleOrderItemDO::getRowCode, dto.getSubExternalOrderCode()).eq(BaseDO::getDeleted , 0);
        List<SaleOrderItemDO> orderItemDOList = saleOrderItemDAO.list(query);
        List<SaleOrderItemMiddleResponseDTO> saleOrderItemMiddleResponseDTOList = ObjectCloneUtils.convertList(orderItemDOList, SaleOrderItemMiddleResponseDTO.class);
        if (Utils.isEmpty(saleOrderItemMiddleResponseDTOList)){
            throw new ApplicationException("第三方子单号错误");
        }
        SaleOrderItemMiddleResponseDTO saleOrderItemMiddleResponseDTO = Utils.selectOne(saleOrderItemMiddleResponseDTOList);
        //拿到订单行的订单信息
        Boolean flag = false;
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = saleOrderInfoService.selectById(saleOrderItemMiddleResponseDTO.getSaleOrderId());;
        //查物流单号是否存在,不存在则生成出库单/存在则要判断出库单对应的订单和订单行的订单是否一致,一致则商品挂载于该出库单下,不一致则重新根据订单号的订单生成出库单
        //1.物流单号不存在 则生成出库单 2.物流单号存在,对应的出库单依附于的订单和订单行一致 3.物流单号存在,对应的出库单依附的订单和订单行的订单不一致
        QueryWrapper<OrderDeliveryInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderDeliveryInfoDO::getDeliveryCode,dto.getTrackingNo()).eq(BaseDO::getDeleted,0);
        List<OrderDeliveryInfoDO> deliveryInfoDOList = orderDeliveryInfoDAO.list(queryWrapper); //物流信息
        if (Utils.isEmpty(deliveryInfoDOList)){
            flag = true;
        }else if (Utils.isNotEmpty(deliveryInfoDOList) ){
            OrderDeliveryInfoDO orderDeliveryInfoDO = Utils.selectOne(deliveryInfoDOList);
            OrderDeliveryInfoResponseDTO orderDeliveryInfoResponseDTO = orderDeliveryInfoDO.clone(OrderDeliveryInfoResponseDTO.class);
            //查出库单信息
            SaleOutTaskMiddleResponseDTO saleOutTaskMiddleResponseDTO = saleOutTaskService.selectById(orderDeliveryInfoResponseDTO.getSaleOutTaskId());
            if (ObjectUtils.equals(saleOutTaskMiddleResponseDTO.getSaleOrderId(),saleOrderInfoResponseDTO.getId())){
                flag = false;
            }else {
                saleOrderInfoResponseDTO = saleOrderInfoService.selectById(saleOutTaskMiddleResponseDTO.getSaleOrderId());
                flag = true;
            }
        }
        //如果订单未生成出库单 则生成出库单
        if (flag){
            SaleOrderInfoDeliverGoodsMiddleRequestDTO middleRequestDTO = new SaleOrderInfoDeliverGoodsMiddleRequestDTO();
            middleRequestDTO.setId(saleOrderInfoResponseDTO.getId());//订单ID
            middleRequestDTO.setSaleOrderId(saleOrderInfoResponseDTO.getId());//订单ID
            middleRequestDTO.setSaleOrderCode(saleOrderInfoResponseDTO.getCode());//订单编码
            middleRequestDTO.setSaleOutTaskCode(dto.getSaleOutTaskCode());//出库单
            middleRequestDTO.setDeliveryType(0);//写死 物流
            middleRequestDTO.setDeliveryTime(new Date());//发货时间
            middleRequestDTO.setTicketDate(new Date());//单据日期
            middleRequestDTO.setTenantId(saleOrderInfoResponseDTO.getTenantId());
            middleRequestDTO.setAppId(saleOrderInfoResponseDTO.getAppId());
            middleRequestDTO.setCreatedBy("OMS");
            middleRequestDTO.setUpdateBy("OMS");
            //查订单关联的默认收货地址
            QueryWrapper<OrderConsigneeInfoDO> doQueryWrapper = new QueryWrapper<>();
            doQueryWrapper.lambda().eq(OrderConsigneeInfoDO::getSaleOrderId, saleOrderInfoResponseDTO.getId()).eq(BaseDO::getDeleted , 0);
            middleRequestDTO.setOrderDeliveryConsigneeInfo(Utils.selectOne(orderConsigneeInfoDAO.list(doQueryWrapper)).clone(OrderDeliveryConsigneeInfoMiddleRequestDTO.class));
            //商品信息
            List<SaleOrderItemMiddleRequestDTO> items = Lists.newArrayList();
            SaleOrderItemMiddleRequestDTO saleOrderItemMiddleRequestDTO = saleOrderItemMiddleResponseDTO.clone(SaleOrderItemMiddleRequestDTO.class);
            //默认全部发完
            saleOrderItemMiddleRequestDTO.setSkuTotalQuantity(saleOrderItemMiddleResponseDTO.getSkuQuantity());//默认全部发完
            Long skuShipmentQuantity = Optional.ofNullable(saleOrderItemMiddleRequestDTO.getSkuShipmentQuantity()).orElse(0L);
            Long unSkuShipmentQuantity = saleOrderItemMiddleRequestDTO.getSkuTotalQuantity() - skuShipmentQuantity;
            saleOrderItemMiddleRequestDTO.setSkuShipmentQuantity(unSkuShipmentQuantity);//本次出库数量
            items.add(saleOrderItemMiddleRequestDTO);
            middleRequestDTO.setItems(items);
            //物流信息
            OrderDeliveryInfoRequestDTO orderDeliveryInfo = Optional.ofNullable(middleRequestDTO.getOrderDeliveryInfo()).orElse(new OrderDeliveryInfoRequestDTO());
            orderDeliveryInfo.setDeliveryName(dto.getScac());//物流公司
            orderDeliveryInfo.setDeliveryCode(dto.getTrackingNo());//物流编号
            middleRequestDTO.setOrderDeliveryInfo(orderDeliveryInfo);
            middleRequestDTO.setIsolationId(saleOrderInfoResponseDTO.getSellerId()+"");//数据隔离ID
            middleRequestDTO.setAscriptionOrgId(saleOrderInfoResponseDTO.getAscriptionOrgId());//一级组织
            //订单发货
            saleOrderInfoService.deliveryGoodsOrder(middleRequestDTO);
        }else {//如果生成过了 则商品挂载于该出库单下
            //修改sku的发货数量
            SaleOrderItemMiddleRequestDTO saleOrderItemMiddleRequestDTO = new SaleOrderItemMiddleRequestDTO();
            saleOrderItemMiddleRequestDTO.setId(saleOrderItemMiddleResponseDTO.getId());
            saleOrderItemMiddleRequestDTO.setSkuTotalQuantity(saleOrderItemMiddleResponseDTO.getSkuQuantity());//默认发货数就是商品总数
            SaleOrderItemDO saleOrderItemDO = saleOrderItemMiddleRequestDTO.clone(SaleOrderItemDO.class);
            saleOrderItemDAO.updateById(saleOrderItemDO);
            //修改订单的发货数量
            SaleOrderInfoRequestDTO saleOrderInfoRequestDTO = new SaleOrderInfoRequestDTO();
            saleOrderInfoRequestDTO.setId(saleOrderItemMiddleResponseDTO.getSaleOrderId());
            //订单总发货数量加上发货的商品
            Long totalDeliveryQuantity = saleOrderInfoResponseDTO.getTotalQuantity() + saleOrderItemMiddleRequestDTO.getSkuTotalQuantity();
            saleOrderInfoRequestDTO.setTotalQuantity(totalDeliveryQuantity);
            SaleOrderInfoDO saleOrderInfoDO = saleOrderInfoRequestDTO.clone(SaleOrderInfoDO.class);
            saleOrderInfoDAO.updateById(saleOrderInfoDO);
            //出库单挂载一条新商品
            QueryWrapper<SaleOutTaskDetailInfoDO> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(SaleOutTaskDetailInfoDO::getSaleOrderId,saleOrderItemMiddleResponseDTO.getSaleOrderId()).eq(BaseDO::getDeleted,0);
            List<SaleOutTaskDetailInfoDO> list = saleOutTaskDetailInfoDAO.list(wrapper);
            //拿到该订单下的随意一条出库单
            SaleOutTaskDetailInfoDO saleOutTaskDetailInfoDO = Utils.selectOne(list);
            SaleOutTaskDetailInfoRequestDTO saleOutTaskDetailInfoRequestDTO = saleOutTaskDetailInfoDO.clone(SaleOutTaskDetailInfoRequestDTO.class);
            saleOutTaskDetailInfoRequestDTO.setSaleOrderItemId(saleOrderItemMiddleResponseDTO.getId());
            saleOutTaskDetailInfoRequestDTO.setSkuShipmentQuantity(saleOrderItemMiddleResponseDTO.getSkuQuantity());//默认发货数就是商品总数
            SaleOutTaskDetailInfoDO taskDetailInfoDO = saleOutTaskDetailInfoRequestDTO.clone(SaleOutTaskDetailInfoDO.class);
            saleOutTaskDetailInfoDAO.save(taskDetailInfoDO);
        }
        return saleOrderInfoResponseDTO.getId();
    }
    @Override
    public SaleOrderItemLogisticsDTO selectById(Long id) {
        if (id == null) {
            log.error("查询OMS物流信息,传入ID为空");
            return null;
        }
        SaleOrderItemLogisticsDO saleOrderItemLogisticsDo = saleOrderItemLogisticsDao.selectById(id);
        if (saleOrderItemLogisticsDo == null) {
            return null;
        }
        return saleOrderItemLogisticsDo.clone(SaleOrderItemLogisticsDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleOrderItemLogisticsDTO record) {
        if (id == null) {
            log.error("修改OMS物流信息,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleOrderItemLogisticsDO saleOrderItemLogisticsDo = record.clone(SaleOrderItemLogisticsDO.class);
        DomainUtils.initUpdateTimeInfo(saleOrderItemLogisticsDo);
        return saleOrderItemLogisticsDao.updateById(saleOrderItemLogisticsDo) > 0;
    }

}