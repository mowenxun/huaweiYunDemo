package com.deepexi.dd.middle.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.*;
import com.deepexi.dd.middle.order.domain.*;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.mapper.SaleOrderItemMapper;
import com.deepexi.dd.middle.order.service.*;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.dd.middle.order.util.Utils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * SalePickGoodsInfoServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 12 19:18:27 CST 2020
 */
@Service
@Slf4j
public class SalePickGoodsInfoServiceImpl implements SalePickGoodsInfoService {

    @Autowired
    private SalePickGoodsInfoDAO salePickGoodsInfoDao;

    @Autowired
    private SalePickGoodsOrderSkuDAO salePickGoodsOrderSkuDAO;

    @Autowired
    private SaleOrderItemDAO saleOrderItemDAO;

    @Autowired
    private SaleOrderItemService saleOrderItemService;

    @Autowired
    private SaleOrderInfoDAO saleOrderInfoDAO;

    @Autowired
    private SaleOutTaskDAO saleOutTaskDAO;

    @Autowired
    private SaleOutTaskDetailInfoDAO saleOutTaskDetailInfoDAO;

    @Autowired
    private OrderDeliveryConsigneeInfoDAO orderDeliveryConsigneeInfoDAO;

    @Autowired
    private OrderDeliveryInfoDAO orderDeliveryInfoDAO;

    @Autowired
    private SalePickGoodsOrderService salePickGoodsOrderService;

    @Autowired
    private SalePickGoodsOrderSkuService salePickGoodsOrderSkuService;

    @Autowired
    private SalePickGoodsOrderDAO salePickGoodsOrderDAO;

    @Autowired
    private SalePickGoodsConsigneeService salePickGoodsConsigneeService;

    @Autowired
    private SaleOrderItemMapper saleOrderItemMapper;

    @Override
    public List<SalePickGoodsInfoDTO> listSalePickGoodsInfos(SalePickGoodsInfoQuery query) {
        return ObjectCloneUtils.convertList(salePickGoodsInfoDao.listSalePickGoodsInfos(query), SalePickGoodsInfoDTO.class);
    }


    @Override
    public PageBean<SalePickGoodsInfoDTO> listSalePickGoodsInfosPage(SalePickGoodsInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        PageBean<SalePickGoodsInfoDO> salePickGoodsInfoDOPageBean = new PageBean<>(salePickGoodsInfoDao.listSalePickGoodsInfos(query));
        return ObjectCloneUtils.convertPageBean(salePickGoodsInfoDOPageBean, SalePickGoodsInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除提货单据表,传入ID为空");
            return false;
        }
        return salePickGoodsInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SalePickGoodsInfoDTO insert(SalePickGoodsInfoDTO record) {
        if (record == null) {
            log.error("新增提货单据表,传入参数为空");
            return null;
        }
        SalePickGoodsInfoDO salePickGoodsInfoDo = record.clone(SalePickGoodsInfoDO.class);
        DomainUtils.initDomainCreatedInfo(salePickGoodsInfoDo);
        if (salePickGoodsInfoDao.insert(salePickGoodsInfoDo) > 0) {
            record.setId(salePickGoodsInfoDo.getId());
            return record;
        }
        log.error("新增提货单据表失败");
        return null;
    }

    @Override
    public SalePickGoodsInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询提货单据表,传入ID为空");
            return null;
        }
        SalePickGoodsInfoDO salePickGoodsInfoDo = salePickGoodsInfoDao.selectById(id);
        if (salePickGoodsInfoDo == null) {
            return null;
        }
        //封装地址
        SalePickGoodsConsigneeQuery salePickGoodsConsigneeQuery=new SalePickGoodsConsigneeQuery();
        salePickGoodsConsigneeQuery.setPickGoodsInfoId(id);
        List<SalePickGoodsConsigneeDTO> salePickGoodsConsigneeDTOList=
                salePickGoodsConsigneeService.listSalePickGoodsConsignees(salePickGoodsConsigneeQuery);
        SalePickGoodsInfoDTO salePickGoodsInfoDTO=salePickGoodsInfoDo.clone(SalePickGoodsInfoDTO.class);
        salePickGoodsInfoDTO.setSalePickGoodsConsigneeDTOS(salePickGoodsConsigneeDTOList);
        return salePickGoodsInfoDTO;
    }

    @Override
    public SalePickGoodsInfoResponseDTO getSalePickGoodsInfoByCode(String code) {
        QueryWrapper<SalePickGoodsInfoDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SalePickGoodsInfoDO::getPickGoodsCode,code).eq(SalePickGoodsInfoDO::getDeleted,0);
        SalePickGoodsInfoDO infoDO= salePickGoodsInfoDao.getOne(queryWrapper);
        if(infoDO!=null){
          return  infoDO.clone(SalePickGoodsInfoResponseDTO.class);
        }
        return null;
    }

    @Override
    public Boolean updateById(Long id, SalePickGoodsInfoDTO record) {
        if (id == null) {
            log.error("修改提货单据表,传入ID为空");
            return false;
        }
        record.setId(id);
        SalePickGoodsInfoDO salePickGoodsInfoDo = record.clone(SalePickGoodsInfoDO.class);
        DomainUtils.initUpdateTimeInfo(salePickGoodsInfoDo);
        return salePickGoodsInfoDao.updateById(salePickGoodsInfoDo);
    }

    @Override
    public Boolean updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO) {
        if (salePickGoodsInfoDao.updateOrderStatus(saleOrderInfoStatusEditRequestDTO) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateOrderAmount(SalePickGoodsInfoAmountEditDTO salePickGoodsInfoAmountEditDTO) {
        return salePickGoodsInfoDao.updateOrderAmount(salePickGoodsInfoAmountEditDTO) > 0;
    }
    @Override
    public PageBean<SalePickGoodsOrderSkuResponseDTO> searchPickGoodsList(Long saleOrderId, Integer page, Integer size) {
        PageHelper.startPage(null == page ? 1 : page, null == size ? 10 : size);
        List<SalePickGoodsOrderSkuResponseDTO> salePickGoodsOrderSkuDTO = salePickGoodsInfoDao.searchPickGoodsList(saleOrderId);
        return new PageBean<>(salePickGoodsOrderSkuDTO);
    }

    /**
     * 用于新建提货单或更新提货单
     * @param dateDTO
     * @return
     */
    @Override
    @Transactional
    public SalePickGoodsInfoResponseDTO saveOrEdit(SalePickGoodsInfoTransferDTO dateDTO) {
        log.info("====================进入了'用于新建提货单或更新提货单'方法中=========================");
        //变量定义
        Long pickGoodsInfoId = null;//提货单的id
        SalePickGoodsInfoDTO salePickGoodsInfoDTO = null;//提货单对象
        String type = dateDTO.getType();//需要进行的操作：保存或更新
        SalePickGoodsInfoRequestDTO record = dateDTO.getRecordObj();
        if(type.equals("update")){
             //更新旧的提货订单计划
            SalePickGoodsInfoResponseDTO oldPickInfo = dateDTO.getOldPickInfo();
            pickGoodsInfoId = oldPickInfo.getId();
            salePickGoodsInfoDTO = new SalePickGoodsInfoDTO();
            BeanUtils.copyProperties(record,salePickGoodsInfoDTO);
            this.updateById(pickGoodsInfoId,salePickGoodsInfoDTO);
            //删除旧提货订单的订单
            QueryWrapper<SalePickGoodsOrderDO> wrapper = new QueryWrapper<>();
            wrapper.eq("pick_goods_info_id",pickGoodsInfoId);
            salePickGoodsOrderService.deleteByWrapper(wrapper);
             //删除旧提货订单的sku
            QueryWrapper<SalePickGoodsOrderDO> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("pick_goods_info_id",pickGoodsInfoId);
            salePickGoodsOrderSkuService.deleteByWrapper(wrapper2);
            //去除旧提货订单的sku对销售订单sku可提货数量的影响(如果该单据原来是已驳回或交易取消的状态，则代表已经去除了影响，无需再次去除)
            Integer status = oldPickInfo.getStatus();
            Integer twelve = 12;
            Integer five = 5;
            if(!twelve.equals(status) && !five.equals(status) ){
                List<SalePickGoodsOrderSkuResponseDTO> oldSkus = dateDTO.getOldSkus();
                List<SalePickGoodsOrderSkuDTO> oldSkus2 = ObjectCloneUtils.convertList(oldSkus, SalePickGoodsOrderSkuDTO.class);
                oldSkus2.stream().forEach(sku -> sku.setLockNum(sku.getPickNum()));
                List<SaleOrderItemMiddleResponseDTO> oldSaleSkus = dateDTO.getOldSaleSkus();
                List<SaleOrderItemDTO> oldSaleSkus2 = ObjectCloneUtils.convertList(oldSaleSkus, SaleOrderItemDTO.class);
                updateAvailablePickNum(oldSaleSkus2,oldSkus2);
            }
            //修改该提货订单关联的收货地址信息
            SalePickGoodsConsigneeDTO consigneeDTO = new SalePickGoodsConsigneeDTO();
            BeanUtils.copyProperties(record,consigneeDTO);
            consigneeDTO.setId(null);
            consigneeDTO.setRemark(null);
            consigneeDTO.setPickGoodsInfoId(record.getId());
            QueryWrapper<SalePickGoodsConsigneeDO> wrapper3 = new QueryWrapper<>();
            wrapper3.eq("pick_goods_info_id",pickGoodsInfoId);
            salePickGoodsConsigneeService.updateByWrapper(consigneeDTO,wrapper3);

        }
        if(type.equals("save")){
            //保存提货单据表
            salePickGoodsInfoDTO = new SalePickGoodsInfoDTO();
            BeanUtils.copyProperties(record,salePickGoodsInfoDTO);
            this.insert(salePickGoodsInfoDTO);
            pickGoodsInfoId = salePickGoodsInfoDTO.getId();
            //保存提货订单关联的收货地址信息
            SalePickGoodsConsigneeDTO consigneeDTO = new SalePickGoodsConsigneeDTO();
            BeanUtils.copyProperties(record,consigneeDTO);
            consigneeDTO.setId(null);
            consigneeDTO.setRemark(null);
            consigneeDTO.setPickGoodsInfoCode(record.getPickGoodsCode());
            consigneeDTO.setPickGoodsInfoId(pickGoodsInfoId);
            salePickGoodsConsigneeService.insert(consigneeDTO);
        }
        //保存订单明细
        List<SalePickGoodsOrderDTO> orderlist = new ArrayList<>();
        for(SalePickGoodsOrderRequestDTO item : record.getItems()){
            SalePickGoodsOrderDTO dto = item.clone(SalePickGoodsOrderDTO.class);
            dto.setPickGoodsInfoId(pickGoodsInfoId);
            orderlist.add(dto);
        }
        List<SalePickGoodsOrderDO> orderlist2 = salePickGoodsOrderService.insertBatch(orderlist);
        Map<String, List<SalePickGoodsOrderDO>> orderlistMap = orderlist2.stream().collect(Collectors.groupingBy(SalePickGoodsOrderDO::getSaleOrderCode));
        //保存sku明细
        ArrayList<SalePickGoodsOrderSkuDTO> skulist = new ArrayList<>();
        for(SalePickGoodsOrderRequestDTO orderItem :record.getItems()){
            Long pickGoodsOrderId = orderlistMap.get(orderItem.getSaleOrderCode()).get(0).getId();
            List<SalePickGoodsOrderSkuRequestDTO> skuDtos = orderItem.getItems();
            for(SalePickGoodsOrderSkuRequestDTO item : skuDtos){
                SalePickGoodsOrderSkuDTO dto = item.clone(SalePickGoodsOrderSkuDTO.class);
                dto.setPickGoodsInfoId(pickGoodsInfoId);
                dto.setPickGoodsOrderId(pickGoodsOrderId);
                skulist.add(dto);
            }
        }
        salePickGoodsOrderSkuService.insertBatch(skulist);
        //根据新的提货单商品明细，更新商品可提数量
        List<Long> saleItemIds = skulist.stream().map(sku -> sku.getSaleOrderItemId()).collect(Collectors.toList());
        SaleOrderItemQuery query2 = new SaleOrderItemQuery();
        query2.setIdList(saleItemIds);
        List<SaleOrderItemDTO> orderItems = saleOrderItemService.listSaleOrderItems(query2);
        List<SalePickGoodsOrderSkuDTO> cloneSkus = skulist.stream().map(sku -> {
            SalePickGoodsOrderSkuDTO cloneSku = new SalePickGoodsOrderSkuDTO();
            cloneSku.setId(sku.getId());
            cloneSku.setSaleOrderItemId(sku.getSaleOrderItemId());
            cloneSku.setLockNum(-sku.getPickNum());
            return cloneSku;
        }).collect(Collectors.toList());
        updateAvailablePickNum(orderItems,cloneSkus);

        //封装返回
        SalePickGoodsInfoResponseDTO dto = salePickGoodsInfoDTO.clone(SalePickGoodsInfoResponseDTO.class);
        return dto;
    }


    /**
     * 用于取消提货订单
     * @param dateDTO
     * @return
     */
    @Override
    @Transactional
    public SalePickGoodsInfoResponseDTO doCancelPickGoods(SalePickGoodsInfoTransferDTO dateDTO) {
        //1.获取该提货单所关联的所有sku明细
        SalePickGoodsInfoRequestDTO req = dateDTO.getRecordObj();
        String pickGoodsCode = req.getPickGoodsCode();
        SalePickGoodsInfoResponseDTO oldPickInfo = getSalePickGoodsInfoByCode(pickGoodsCode);
        Long pickInfoId = oldPickInfo.getId();
        SalePickGoodsOrderSkuQuery skuQuery = new SalePickGoodsOrderSkuQuery();
        skuQuery.setPickGoodsInfoId(pickInfoId);
        List<SalePickGoodsOrderSkuDTO> skus = salePickGoodsOrderSkuService.listSalePickGoodsOrderSkus(skuQuery);
        //2.获取提货sku所关联的所有销售订单的sku
        if(skus == null || skus.size() == 0){
            log.error("无法获得提货订单的sku.提货单编号："+pickGoodsCode);
            return null;
        }
        List<Long> saleOrderItemIds = skus.stream().map(sku -> sku.getSaleOrderItemId()).collect(Collectors.toList());
        List<SaleOrderItemDTO> orderItems = null;
        if(saleOrderItemIds != null && saleOrderItemIds.size() != 0){
            SaleOrderItemQuery query2 = new SaleOrderItemQuery();
            query2.setIdList(saleOrderItemIds);
            orderItems = saleOrderItemService.listSaleOrderItems(query2);
        }
        //3.取消提货订单需要释放数量（待付款状态和待确认状态下需要释放数量，已驳回不需要释放   释放数量 = 提货数量）
        Integer oldStatus = oldPickInfo.getStatus();
        String closeType = req.getCloseType();
        Integer five = 5;
        if("cancel".equals(closeType) && !five.equals(oldStatus)){
            for (SalePickGoodsOrderSkuDTO sku : skus) {
                sku.setLockNum(sku.getPickNum());
            }
            updateAvailablePickNum(orderItems,skus);
        }
        //4.关闭提货订单需要释放数量  （释放数量 = 该仓库下的申请提货数量 - 已发货数量 = 未发货数量）
        if("close".equals(closeType)){
            //4.1获取每个提货商品详情的需要释放的数量
            for (SalePickGoodsOrderSkuDTO sku : skus) {
                Long waitSendNum = sku.getWaitSendNum() == null ? 0L : sku.getWaitSendNum();//待发货数量
                sku.setLockNum(waitSendNum);
            }
            //4.2进行释放操作
            updateAvailablePickNum(orderItems,skus);
        }
        //5.修改提货订单状态为交易取消或关闭，并保存原因和备注
        SalePickGoodsInfoDO pickInfoDO = new SalePickGoodsInfoDO();
        BeanUtils.copyProperties(req,pickInfoDO);
        pickInfoDO.setPickGoodsCode(null);
        pickInfoDO.setId(pickInfoId);
        salePickGoodsInfoDao.updateById(pickInfoDO);
        return pickInfoDO.clone(SalePickGoodsInfoResponseDTO.class);
    }






    /**
     * 增加销售订单sku的剩余可申请提货数量
     *
     * @param orderItems 提货订单sku关联的所有销售订单sku的数据
     * @param skus 提货订单sku
     */
   /* private void updateAvailablePickNum(List<SaleOrderItemDTO> orderItems, List<SalePickGoodsOrderSkuDTO> skus) {
        ArrayList<SaleOrderItemDTO> updateItems = new ArrayList<>();
        if (orderItems != null && orderItems.size() != 0 && skus != null) {
            Map<Long, List<SaleOrderItemDTO>> orderItemsMap = orderItems.stream().collect(Collectors.groupingBy(item -> item.getId()));;
            for (SalePickGoodsOrderSkuDTO sku : skus) {
                List<SaleOrderItemDTO> matchItems = orderItemsMap.get(sku.getSaleOrderItemId());
                if(matchItems == null || matchItems.get(0) == null){
                    log.error("存在一个提货sku无法找到对应销售sku的情况sku.getSaleOrderItemId():"+sku.getSaleOrderItemId());
                    continue;
                }
                if(matchItems.size() > 1){
                    log.error("存在一个提货sku对应多个销售sku的情况，sku.getSaleOrderItemId():"+sku.getSaleOrderItemId());
                }
                SaleOrderItemDTO matchItem = matchItems.get(0);
                SaleOrderItemDTO updateItem = new SaleOrderItemDTO();
                updateItem.setId(matchItem.getId());
                updateItem.setAvailablePickNum(matchItem.getAvailablePickNum() + sku.getLockNum());
                updateItems.add(updateItem);
            }
        }
        if(updateItems.size() != 0){
            saleOrderItemService.updateBatchById(updateItems);
        }
    }*/





    /**
     * 增加销售订单sku的剩余可申请提货数量
     *
     * @param orderItems 提货订单sku关联的所有销售订单sku的数据  （目前该字段已没什么用）
     * @param skus 提货订单sku
     **/
    @Transactional
    public void updateAvailablePickNum(List<SaleOrderItemDTO> orderItems, List<SalePickGoodsOrderSkuDTO> skus) {
        if (orderItems != null && orderItems.size() != 0 && skus != null) {
            Map<Long, List<SaleOrderItemDTO>> orderItemsMap = orderItems.stream().collect(Collectors.groupingBy(item -> item.getId()));
            for (SalePickGoodsOrderSkuDTO sku : skus) {
                //检查是否存在异常情况
                List<SaleOrderItemDTO> matchItems = orderItemsMap.get(sku.getSaleOrderItemId());
                if(matchItems == null || matchItems.get(0) == null){
                    log.error("存在一个提货sku无法找到对应销售sku的情况sku.getSaleOrderItemId():"+sku.getSaleOrderItemId());
                    continue;
                }
                if(matchItems.size() > 1){
                    log.error("存在一个提货sku对应多个销售sku的情况，sku.getSaleOrderItemId():"+sku.getSaleOrderItemId());
                }
                //更新可申请的提货数量，同时对数据行加写锁，其他事务若要同时对数据进行修改将会被阻塞
                saleOrderItemMapper.updateAvailablePickNum(sku);
            }
            //检查是否可提货数量不足，不足则回滚事务（以下读取的数据一定是最新的数据，因为刚被更新过，RR可以读到自己的数据。并且从上面的修改到下面的读取，由于存在写锁，所以无法有其他线程对相同数据进行修改操作）
            SaleOrderItemMiddleRequestQuery query = new SaleOrderItemMiddleRequestQuery();
            List<Long> saleItemIds = orderItems.stream().map(item -> item.getId()).collect(Collectors.toList());
            query.setIdList(saleItemIds);
            List<SaleOrderItemDO> saleItemDOS = saleOrderItemDAO.listSaleOrderItems(query);
            for(SaleOrderItemDO saleItem : saleItemDOS){
                if(saleItem.getAvailablePickNum() < 0){
                    throw new ApplicationException("可申请的提货数量不足，请检查明细，商品编号："+saleItem.getSkuCode());
                }
            }
        }
    }



    /**
     * @Description:  审批通过.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    @Override
    @Transactional
    public Boolean examinePass(SalePickGoodsInfoRequestDTO dto) throws Exception {
        log.info("审核通过 参数：{}", JSON.toJSONString(dto));
        if (Objects.isNull(dto)){
            return Boolean.FALSE;
        }
        SalePickGoodsInfoDTO salePickGoodsInfoDTO = selectById(dto.getId());//提货单信息
        List<SalePickGoodsOrderSkuRequestDTO> skuList = dto.getItems().stream().map(SalePickGoodsOrderRequestDTO::getItems).flatMap(Collection::stream).collect(Collectors.toList());//sku集合
        Map<Boolean, List<SalePickGoodsOrderSkuRequestDTO>> map = skuList.stream().collect(Collectors.groupingBy(e -> Objects.isNull(e.getId())));
        //新增不同仓库的商品
        List<SalePickGoodsOrderSkuRequestDTO> saveList = map.get(Boolean.TRUE);
        if (Utils.isNotEmpty(saveList)){
            List<Long> skuIdList = saveList.stream().map(SalePickGoodsOrderSkuRequestDTO::getSkuId).distinct().collect(Collectors.toList());
            QueryWrapper<SalePickGoodsOrderSkuDO> queryWrapper = new QueryWrapper();
            queryWrapper.lambda().eq(SalePickGoodsOrderSkuDO::getPickGoodsInfoId, salePickGoodsInfoDTO.getId());
            queryWrapper.lambda().in(SalePickGoodsOrderSkuDO::getSkuId,skuIdList);
            List<SalePickGoodsOrderSkuDO> salePickGoodsOrderSkuDOS = salePickGoodsOrderSkuDAO.list(queryWrapper);//相同的skuId 不同的仓库sku数据
            List<SalePickGoodsOrderSkuResponseDTO> list = ObjectCloneUtils.convertList(salePickGoodsOrderSkuDOS, SalePickGoodsOrderSkuResponseDTO.class);
            Map<Long, SalePickGoodsOrderSkuResponseDTO> skuMap = list.stream().collect(Collectors.toMap(SalePickGoodsOrderSkuResponseDTO::getSkuId, Function.identity(),(t1,t2) -> t2));
            List<SalePickGoodsOrderSkuResponseDTO> salePickGoodsOrderSkuResponseDTOList = saveList.stream().map(item -> {
                SalePickGoodsOrderSkuResponseDTO salePickGoodsOrderSkuResponseDTO = skuMap.get(item.getSkuId()).clone(SalePickGoodsOrderSkuResponseDTO.class);
                salePickGoodsOrderSkuResponseDTO.setRowCode(null);//行号会重复 则设置为null 数据库有触发器 为null则自生成
                salePickGoodsOrderSkuResponseDTO.setWarehouse(item.getWarehouse());//仓库
                salePickGoodsOrderSkuResponseDTO.setIfEager(item.getIfEager());//是否加急
                salePickGoodsOrderSkuResponseDTO.setDeliveryQuantity(item.getDeliveryQuantity());//仓库出库数量
                salePickGoodsOrderSkuResponseDTO.setWaitSendNum(item.getDeliveryQuantity());//待发货数量 = 仓库出库数量
                salePickGoodsOrderSkuResponseDTO.setWarehouseId(item.getWarehouseId());//仓库ID
                return salePickGoodsOrderSkuResponseDTO;
            }).collect(Collectors.toList());
            List<SalePickGoodsOrderSkuDO> saveDOList = ObjectCloneUtils.convertList(salePickGoodsOrderSkuResponseDTOList, SalePickGoodsOrderSkuDO.class);
            if (Utils.isEmpty(saveDOList)){
                throw new ApplicationException("审核异常!");
            }
            log.info("审核通过 新增商品入参：{}",JSON.toJSONString(saveDOList));
            salePickGoodsOrderSkuDAO.saveBatch(saveDOList);
        }
        //修改商品
        List<SalePickGoodsOrderSkuRequestDTO> updateList = map.get(Boolean.FALSE);
        if (Utils.isNotEmpty(updateList)){
            updateList.forEach(f->{f.setWaitSendNum(f.getDeliveryQuantity());});//待发货数量 = 仓库出库数量
            List<SalePickGoodsOrderSkuDO> updateDOList = ObjectCloneUtils.convertList(updateList, SalePickGoodsOrderSkuDO.class);
            if (Utils.isEmpty(updateList)) {
                throw new ApplicationException("审核异常.");
            }
            log.info("审核通过 修改商品入参：{}",JSON.toJSONString(updateList));
            salePickGoodsOrderSkuDAO.updateBatchById(updateDOList);
        }
        return Boolean.TRUE;
    }

    /**
     * @param dto
     * @Description: 审批驳回.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/30
     */
    @Override
    @Transactional
    public Boolean examineReject(SalePickGoodsInfoRequestDTO dto) throws Exception {
        //先查到对应的提货单的商品信息
        Optional.ofNullable(dto).orElseThrow( () -> new ApplicationException("审批驳回参数为空."));
        log.info("审批驳回的入参:{}",JSON.toJSONString(dto));
        QueryWrapper<SalePickGoodsOrderSkuDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SalePickGoodsOrderSkuDO::getPickGoodsInfoId, dto.getId()).eq(BaseDO::getDeleted,0);
        List<SalePickGoodsOrderSkuDO> list = salePickGoodsOrderSkuDAO.list(queryWrapper);
        if (Utils.isEmpty(list)){
            throw new ApplicationException("审批驳回,传入的提货单/订单参数错误");
        }
        log.info("sku信息:{}",JSON.toJSONString(list));
        //sku 对应的提货数量
        Map<Long, Long> skuMap = list.stream().collect(Collectors.toMap(SalePickGoodsOrderSkuDO::getSaleOrderItemId, SalePickGoodsOrderSkuDO::getPickNum , (t1 , t2) -> t2));
        List<Long> skuItemList = list.stream().map(SalePickGoodsOrderSkuDO::getSaleOrderItemId).collect(Collectors.toList());
        QueryWrapper<SaleOrderItemDO> wrapper = new QueryWrapper();
        wrapper.lambda().in(SaleOrderItemDO::getId,skuItemList).eq(BaseDO::getDeleted,0);
        List<SaleOrderItemDO> saleOrderItemDOList = saleOrderItemDAO.list(wrapper);
        List<SaleOrderItemMiddleResponseDTO> saleOrderItemMiddleResponseDTOList = ObjectCloneUtils.convertList(saleOrderItemDOList, SaleOrderItemMiddleResponseDTO.class);
        List<SaleOrderItemDO> saleOrderItemList = saleOrderItemMiddleResponseDTOList.stream().map(map -> {
            //商品本次提货数量
            Long shipPickQuantity = skuMap.get(map.getId());
            //商品剩余的可提货数量
            Long availablePickNum = map.getAvailablePickNum();
            if (Objects.isNull(availablePickNum)) {
                availablePickNum = 0L;
            }
            //驳回要把本次提货数量给加回去
            availablePickNum += shipPickQuantity;
            SaleOrderItemDO saleOrderItemDO = new SaleOrderItemDO();
            saleOrderItemDO.setId(map.getId());
            saleOrderItemDO.setUpdatedTime(new Date());
            if (availablePickNum <= map.getSkuQuantity()) {
                //更新商品的可提货数量
                saleOrderItemDO.setAvailablePickNum(availablePickNum);
            } else {
                throw new ApplicationException("商品库存不足.");
            }
            return saleOrderItemDO;
        }).collect(Collectors.toList());
        //更改SKU的提货数量
        if (Utils.isNotEmpty(saleOrderItemList)){
            log.info("审批驳回,修改后的商品信息:{}",JSON.toJSONString(saleOrderItemDOList));
            return saleOrderItemDAO.updateBatchById(saleOrderItemList);
        }
        return Boolean.FALSE;
    }

    /**
     * @param dto
     * @Description: 提货计划的发货.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/15
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean pickPlanDeliveryGoods(SalePickDeliveryInfoMiddleRequestDTO dto) throws Exception {
        log.info("提货计划发货的入参：{} ", JSON.toJSONString(dto));
        if (Objects.isNull(dto)){
            throw new ApplicationException("发货失败,提货信息为空");
        }
        SalePickGoodsInfoDTO pickGoodsInfoDTO = selectById(dto.getPickGoodsId());
        if (Objects.isNull(pickGoodsInfoDTO)){
            throw new ApplicationException("提货编号错误");
        }
        //拿到提货单信息
        SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = pickGoodsInfoDTO.clone(SalePickGoodsInfoResponseDTO.class);
        //提货单商品信息
        List<SalePickDeliveryItemInfoMiddleRequestDTO> pickDeliveryItemList = dto.getSalePickDeliveryOrderInfoList().stream().
                map(SalePickDeliveryOrderInfoMiddleRequestDTO::getSalePickDeliveryItemInfoList).flatMap(Collection::stream).
                filter(f -> Objects.nonNull(f.getSkuShipmentQuantity())).collect(Collectors.toList());//商品信息
        //更新提货单的发货数
        updatePickGoodsInfo(salePickGoodsInfoResponseDTO,pickDeliveryItemList);
        //更新提货单的sku商品待发货数量
        updatePickGoodsSkuInfo(pickDeliveryItemList);
        //更新订单的发货数
        updateSaleOrderInfo(pickDeliveryItemList);
        //更新订单关联的sku库存信息
        updateItemDeliveryInfo(pickDeliveryItemList);
        //生成出库单
        Map<Long, String> saleOutTaskMap = generateSaleOutTaskInfo(dto, salePickGoodsInfoResponseDTO);
        //生成物流信息
        generateDeliveryInfo(dto,salePickGoodsInfoResponseDTO,saleOutTaskMap);
        //生成收货地址信息
        generateDeliveryConsigneeInfo(dto,salePickGoodsInfoResponseDTO,saleOutTaskMap);
        return Boolean.TRUE;
    }
    /**
     * @Description:  更新订单的发货数量.
     * @Param: [dto]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    private void updateSaleOrderInfo(List<SalePickDeliveryItemInfoMiddleRequestDTO> list){
        List<String> saleOrderCodeList = list.stream().
                map(SalePickDeliveryItemInfoMiddleRequestDTO::getSaleOrderCode).distinct().collect(Collectors.toList());
        //订单 对应总的发货数
        Map<String, Long> skuMap = list.stream().collect(Collectors.groupingBy(SalePickDeliveryItemInfoMiddleRequestDTO::getSaleOrderCode,
                Collectors.summingLong(SalePickDeliveryItemInfoMiddleRequestDTO::getSkuShipmentQuantity)));
        //拿到订单信息
        QueryWrapper<SaleOrderInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SaleOrderInfoDO::getCode,saleOrderCodeList).eq(BaseDO::getDeleted,0);
        List<SaleOrderInfoDO> saleOrderInfoDOList = saleOrderInfoDAO.list(queryWrapper);
        List<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOList = ObjectCloneUtils.convertList(saleOrderInfoDOList, SaleOrderInfoResponseDTO.class);
        List<SaleOrderInfoRequestDTO> saleOrderInfoList = saleOrderInfoResponseDTOList.stream().map(map -> {
            Long totalDeliveryQuantity = skuMap.get(map.getCode());//订单本次总发货数量
            SaleOrderInfoRequestDTO saleOrderInfoRequestDTO = new SaleOrderInfoRequestDTO();
            Long totalQuantity = map.getTotalQuantity();//商品总发货数
            if (Objects.isNull(totalQuantity)) {
                totalQuantity = 0L;
            }
            saleOrderInfoRequestDTO.setId(map.getId());
            saleOrderInfoRequestDTO.setUpdatedTime(new Date());
            totalQuantity += totalDeliveryQuantity;//总发货数量叠加
            if (totalQuantity <= map.getQuantity()) {
                saleOrderInfoRequestDTO.setTotalQuantity(totalQuantity);//更新库存
            } else {
                throw new ApplicationException("商品库存不足.");
            }
            saleOrderInfoRequestDTO.setShipmentStatus(totalQuantity == map.getQuantity() ? 1 : 2);//总发货数 等于 商品总数 则该订单已全部发货
            return saleOrderInfoRequestDTO;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        List<SaleOrderInfoDO> orderInfoDOList = ObjectCloneUtils.convertList(saleOrderInfoList, SaleOrderInfoDO.class);
        log.info("修改后订单的信息:{}",JSON.toJSONString(orderInfoDOList));
        if (Utils.isEmpty(orderInfoDOList)){
            throw new ApplicationException("修改订单发货数失败,订单信息为空.");
        }
        saleOrderInfoDAO.updateBatchById(orderInfoDOList);
    }
    /**
     * @Description:  更新提货单的发货数.
     * @Param: [dto, list]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/17
     */
    private void updatePickGoodsInfo(SalePickGoodsInfoResponseDTO dto,List<SalePickDeliveryItemInfoMiddleRequestDTO> list) throws Exception{
        log.info("修改提货单的提货信息:{},商品信息:{}", JSON.toJSONString(dto),JSON.toJSONString(list));
        Long totalDeliveryQuantity = list.stream().mapToLong(SalePickDeliveryItemInfoMiddleRequestDTO::getSkuShipmentQuantity).sum();//本次提货单的总发货数
        SalePickGoodsInfoDTO salePickGoodsInfoDTO = new SalePickGoodsInfoDTO();
        salePickGoodsInfoDTO.setUpdatedTime(new Date());
        Long skuTotalDeliveryQuantity = dto.getTotalDeliveryQuantity();//提货单的总发货数
        if(Objects.isNull(skuTotalDeliveryQuantity)){
            skuTotalDeliveryQuantity = 0L;
        }
        skuTotalDeliveryQuantity += totalDeliveryQuantity;
        if (skuTotalDeliveryQuantity <= dto.getTotalGoodsNumber()){
            salePickGoodsInfoDTO.setTotalDeliveryQuantity(skuTotalDeliveryQuantity);//更新库存
        }else {
            throw new ApplicationException("提货单商品总库存不足.");
        }
        log.info("修改后提货单的发货信息:{}",JSON.toJSONString(salePickGoodsInfoDTO));
        if (Objects.isNull(salePickGoodsInfoDTO)){
            throw new ApplicationException("修改提货单发货数失败,提货单信息为空.");
        }
        updateById(dto.getId(),salePickGoodsInfoDTO);
    }
    /**
     * @Description:  更新提货单的sku库存信息.
     * @Param: [list]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/15
     */
    private void updatePickGoodsSkuInfo(List<SalePickDeliveryItemInfoMiddleRequestDTO> list) throws Exception{
        //每个商品id+仓库 对应的发货数量 确保数据唯一
        Map<String,Long> skuMap = list.stream().collect(Collectors.toMap(map->map.getId()+"-"+map.getDeliveryWareHouseName(), SalePickDeliveryItemInfoMiddleRequestDTO::getSkuShipmentQuantity));
        log.info("提货单sku itemId对应的本次发货数 ：{}",JSON.toJSONString(skuMap));
        List<Long> pickSkuIdList = list.stream().map(SalePickDeliveryItemInfoMiddleRequestDTO::getId).collect(Collectors.toList());//提货单id集合
        QueryWrapper<SalePickGoodsOrderSkuDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(BaseDO::getId,pickSkuIdList).eq(BaseDO::getDeleted,0);
        List<SalePickGoodsOrderSkuDO> salePickGoodsOrderSkuDOList = salePickGoodsOrderSkuDAO.list(queryWrapper);
        List<SalePickGoodsOrderSkuResponseDTO> salePickGoodsOrderSkuResponseDTOList = ObjectCloneUtils.convertList(salePickGoodsOrderSkuDOList, SalePickGoodsOrderSkuResponseDTO.class);
        salePickGoodsOrderSkuResponseDTOList.forEach (item->{
            Long skuShipmentQuantity = skuMap.get(item.getId()+"-"+item.getWarehouse());//商品本次发货数量
            if (skuShipmentQuantity >0){
                item.setUpdatedTime(new Date());
                Long waitSendNum = item.getWaitSendNum();//商品待发货数量
                waitSendNum -= skuShipmentQuantity;
                if (waitSendNum >= 0 && waitSendNum <= item.getDeliveryQuantity() ){
                    item.setWaitSendNum(waitSendNum);//更新商品待发货数量
                }else {
                    throw new ApplicationException("商品待发货数量不能小于零.");
                }
            }
        });
        List<SalePickGoodsOrderSkuDO> skuDOList = ObjectCloneUtils.convertList(salePickGoodsOrderSkuResponseDTOList, SalePickGoodsOrderSkuDO.class);
        log.info("修改后提货单的sku信息：{}",JSON.toJSONString(skuDOList));
        if (Utils.isEmpty(skuDOList)){
            throw new ApplicationException("更新提货单sku库存信息失败,sku信息为空.");
        }
        salePickGoodsOrderSkuDAO.updateBatchById(skuDOList);
    }
    /**
     * @Description:  更新订单关联的sku库存信息.
     * @Param: [dto]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/15
     */
    private void updateItemDeliveryInfo(List<SalePickDeliveryItemInfoMiddleRequestDTO> skuList) throws Exception{
        //处理商品的总出库数量
        Map<String, Long> skuMap = skuList.stream().collect(Collectors.groupingBy(g->g.getSkuId() + "-" + g.getSaleOrderCode(),
                Collectors.summingLong(SalePickDeliveryItemInfoMiddleRequestDTO::getSkuShipmentQuantity)));//skuId 对应每次的发货数
        log.info("商品sku 对应的本次发货数 ：{}",JSON.toJSONString(skuMap));
        //处理sku的库存 得用订单号+skuId 来确保数据的唯一
        List<Long> skuIdList = skuList.stream().map(SalePickDeliveryItemInfoMiddleRequestDTO::getSkuId).distinct().collect(Collectors.toList());
        List<String> saleOrderCodeList = skuList.stream().map(SalePickDeliveryItemInfoMiddleRequestDTO::getSaleOrderCode).distinct().collect(Collectors.toList());
        QueryWrapper<SaleOrderItemDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(SaleOrderItemDO::getSkuId,skuIdList).in(SaleOrderItemDO::getSaleOrderCode,saleOrderCodeList).eq(SaleOrderItemDO::getDeleted,0);
        List<SaleOrderItemDO> saleOrderItemDOList = saleOrderItemDAO.list(queryWrapper);
        List<SaleOrderItemMiddleResponseDTO> itemList = ObjectCloneUtils.convertList(saleOrderItemDOList, SaleOrderItemMiddleResponseDTO.class);
        log.info("商品信息: {}", JSON.toJSONString(itemList));
        //更新item表商品库存
        List<SaleOrderItemMiddleRequestDTO> itemResponseDTO = itemList.stream().map(sku -> {
            Long skuShipmentQuantity = skuMap.get(sku.getSkuId() + "-" + sku.getSaleOrderCode());//商品本次发货数量
            if (skuShipmentQuantity!=null && skuShipmentQuantity > 0) {
                SaleOrderItemMiddleRequestDTO requestDTO = new SaleOrderItemMiddleRequestDTO();
                requestDTO.setId(sku.getId());
                requestDTO.setUpdatedTime(new Date());
                Long skuTotalDeliveryQuantity = sku.getSkuTotalQuantity();//商品总发货数
                if (Objects.isNull(skuTotalDeliveryQuantity)) {
                    skuTotalDeliveryQuantity = 0L;
                }
                skuTotalDeliveryQuantity += skuShipmentQuantity;
                if (skuTotalDeliveryQuantity <= sku.getSkuQuantity()) {
                    requestDTO.setSkuTotalQuantity(skuTotalDeliveryQuantity);//更新库存
                } else {
                    throw new ApplicationException("商品库存不足.");
                }
                return requestDTO;
            }
            return new SaleOrderItemMiddleRequestDTO();
        }).filter(f -> Objects.nonNull(f.getId())).collect(Collectors.toList());
        List<SaleOrderItemDO> itemDOList = ObjectCloneUtils.convertList(itemResponseDTO, SaleOrderItemDO.class);
        log.info("修改后item的sku信息：{}",JSON.toJSONString(itemDOList));
        if (Utils.isEmpty(itemDOList)){
            throw new ApplicationException("更新sku库存信息失败,sku信息为空");
        }
        saleOrderItemDAO.updateBatchById(itemDOList);//更新库存
    }
    /**
     * @Description: 生成物流信息 .
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/17
     */
    private void generateDeliveryInfo(SalePickDeliveryInfoMiddleRequestDTO requestDTO,SalePickGoodsInfoResponseDTO dto,Map<Long,String> map) throws Exception {
        List<OrderDeliveryInfoDO> list = Lists.newArrayList();
        map.forEach((k,v) ->{
            OrderDeliveryInfoDO orderDeliveryInfo = requestDTO.getSalePickDeliveryLogisticsInfo().clone(OrderDeliveryInfoDO.class);//复制原有属性
            orderDeliveryInfo.setSaleOutTaskId(k);//出库单ID
            orderDeliveryInfo.setSaleOutTaskCode(v);//出库单编号
            orderDeliveryInfo.setDeliveryTime(requestDTO.getDeliveryTime());
            orderDeliveryInfo.setSalePickGoodsId(dto.getId());//提货单ID
            orderDeliveryInfo.setSalePickGoodsCode(dto.getPickGoodsCode());//提货单编号
            orderDeliveryInfo.setTenantId(dto.getTenantId());//企业ID
            orderDeliveryInfo.setAppId(dto.getAppId());//appId
            orderDeliveryInfo.setVersion(dto.getVersion());//版本号
            orderDeliveryInfo.setCreatedBy(requestDTO.getCreatedBy());//创建人
            orderDeliveryInfo.setUpdatedBy(requestDTO.getCreatedBy());//修改人
            DomainUtils.initDomainCreatedInfo(orderDeliveryInfo);
            list.add(orderDeliveryInfo);
        });
        orderDeliveryInfoDAO.saveBatch(list);
    }

    /**
     * @Description: 保存出库单发货信息.
     * @Param: [dto, id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/17
     */
    private void generateDeliveryConsigneeInfo(SalePickDeliveryInfoMiddleRequestDTO requestDTO,SalePickGoodsInfoResponseDTO dto,Map<Long,String> map) throws Exception {
        List<OrderDeliveryConsigneeInfoDO> list = Lists.newArrayList();
        map.forEach((k,v) ->{
            OrderDeliveryConsigneeInfoDO orderDeliveryConsigneeInfoDO = requestDTO.getOrderDeliveryConsigneeInfo().clone(OrderDeliveryConsigneeInfoDO.class);//复制原有属性
            orderDeliveryConsigneeInfoDO.setSaleOutTaskId(k);//出库单ID
            orderDeliveryConsigneeInfoDO.setSaleOutTaskCode(v);//出库单编号
            orderDeliveryConsigneeInfoDO.setSalePickGoodsId(dto.getId());//提货单id
            orderDeliveryConsigneeInfoDO.setSalePickGoodsCode(dto.getPickGoodsCode());//提货单编号
            orderDeliveryConsigneeInfoDO.setTenantId(dto.getTenantId());//企业ID
            orderDeliveryConsigneeInfoDO.setAppId(dto.getAppId());//appId
            orderDeliveryConsigneeInfoDO.setVersion(dto.getVersion());//版本号
            orderDeliveryConsigneeInfoDO.setCreatedBy(requestDTO.getCreatedBy());//创建人
            orderDeliveryConsigneeInfoDO.setUpdatedBy(requestDTO.getCreatedBy());//修改人
            DomainUtils.initDomainCreatedInfo(orderDeliveryConsigneeInfoDO);
            list.add(orderDeliveryConsigneeInfoDO);
        });
        orderDeliveryConsigneeInfoDAO.saveBatch(list);

    }
    /**
     * @Description:  生成出库单.
     * @Param: [dto]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/15
     */
    private Map<Long,String> generateSaleOutTaskInfo(SalePickDeliveryInfoMiddleRequestDTO dto,SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO) throws Exception {
        log.info("生成出库单发货信息：{},提货单信息：{}",JSON.toJSONString(dto),JSON.toJSONString(salePickGoodsInfoResponseDTO));
        List<SalePickDeliveryOrderInfoMiddleRequestDTO> salePickDeliveryOrderInfoList = dto.getSalePickDeliveryOrderInfoList();
        List<String> saleOrderCodeList = salePickDeliveryOrderInfoList.stream().map(SalePickDeliveryOrderInfoMiddleRequestDTO::getSaleOrderCode).distinct().collect(Collectors.toList());
        //获取订单信息
        QueryWrapper<SaleOrderInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(SaleOrderInfoDO::getCode,saleOrderCodeList).eq(SaleOrderInfoDO::getDeleted,0);
        List<SaleOrderInfoDO> saleOrderInfoDOList = saleOrderInfoDAO.list(queryWrapper);
        List<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOList = ObjectCloneUtils.convertList(saleOrderInfoDOList, SaleOrderInfoResponseDTO.class);
        Map<String, Long> saleOrderInfoMap = saleOrderInfoResponseDTOList.stream().collect(Collectors.toMap(SaleOrderInfoResponseDTO::getCode, SaleOrderInfoResponseDTO::getId, (t1, t2) -> t1));
        //生成出库单
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskMiddleResponseDTOList = salePickDeliveryOrderInfoList.stream().map(deliveryOrderInfo -> {
            SaleOutTaskMiddleResponseDTO saleOutTaskMiddleResponseDTO = salePickGoodsInfoResponseDTO.clone(SaleOutTaskMiddleResponseDTO.class);
            Long deliveryQuantity = deliveryOrderInfo.getSalePickDeliveryItemInfoList().stream().mapToLong(SalePickDeliveryItemInfoMiddleRequestDTO::getSkuShipmentQuantity).sum();
            Long skuPickQuantity = deliveryOrderInfo.getSalePickDeliveryItemInfoList().stream().mapToLong(SalePickDeliveryItemInfoMiddleRequestDTO::getSkuPickQuantity).sum();
            saleOutTaskMiddleResponseDTO.setCode(deliveryOrderInfo.getSaleOutTaskCode());//出库单编号
            saleOutTaskMiddleResponseDTO.setTaskType(1);
            saleOutTaskMiddleResponseDTO.setDeliveryQuantity(deliveryQuantity);//商品出库总数量
            saleOutTaskMiddleResponseDTO.setSkuQuantity(skuPickQuantity);//出库单层面 申请数量=计划出库总数量
            saleOutTaskMiddleResponseDTO.setSkuApplyQuantity(skuPickQuantity);
            saleOutTaskMiddleResponseDTO.setSignStatus(17);//先写死 出库默认待收货
            saleOutTaskMiddleResponseDTO.setTicketDate(new Date());//单据日期 默认是创建的日期
            saleOutTaskMiddleResponseDTO.setType("销售出库单");//单据类型写死
            saleOutTaskMiddleResponseDTO.setSalePickGoodsId(salePickGoodsInfoResponseDTO.getId());//提货单id
            saleOutTaskMiddleResponseDTO.setSalePickGoodsCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());//提货单编号
            saleOutTaskMiddleResponseDTO.setDeliveryTime(dto.getDeliveryTime());//发货时间
            saleOutTaskMiddleResponseDTO.setDeliveryType(dto.getDeliveryType());//发货方式
            saleOutTaskMiddleResponseDTO.setDeliveryWareHouseName(deliveryOrderInfo.getDeliveryWareHouseName());//发货仓库
            saleOutTaskMiddleResponseDTO.setSaleOrderId(saleOrderInfoMap.get(deliveryOrderInfo.getSaleOrderCode()));//订单id
            saleOutTaskMiddleResponseDTO.setSaleOrderCode(deliveryOrderInfo.getSaleOrderCode());//订单编号
            saleOutTaskMiddleResponseDTO.setDeleted(false);//是否删除
            saleOutTaskMiddleResponseDTO.setVersion(1);//版本号
            saleOutTaskMiddleResponseDTO.setCreatedBy(dto.getCreatedBy());//创建人
            saleOutTaskMiddleResponseDTO.setCreatedTime(new Date());//创建时间
            saleOutTaskMiddleResponseDTO.setUpdatedTime(new Date());//更新时间
            saleOutTaskMiddleResponseDTO.setStatus(0);
            saleOutTaskMiddleResponseDTO.setIsolationId(dto.getIsolationId());//数据隔离ID
            saleOutTaskMiddleResponseDTO.setAscriptionOrgId(dto.getAscriptionOrgId());//拆单所属组织
            return saleOutTaskMiddleResponseDTO;
        }).collect(Collectors.toList());
        List<SaleOutTaskDO> saleOutTaskDOList = ObjectCloneUtils.convertList(saleOutTaskMiddleResponseDTOList, SaleOutTaskDO.class);
        log.info("生成的出库单信息：{}",JSON.toJSONString(saleOutTaskDOList));
        if (!saleOutTaskDAO.saveBatch(saleOutTaskDOList)) {//生成出库单
            throw new ApplicationException("生成出库单异常,请检查参数");
        }
        //获取订单商品信息
        List<Long> skuIdList = dto.getSalePickDeliveryOrderInfoList().stream().map(SalePickDeliveryOrderInfoMiddleRequestDTO::getSalePickDeliveryItemInfoList).
                flatMap(Collection::stream).map(SalePickDeliveryItemInfoMiddleRequestDTO::getSkuId).distinct().collect(Collectors.toList());
        List<String> orderCodeList = dto.getSalePickDeliveryOrderInfoList().stream().map(SalePickDeliveryOrderInfoMiddleRequestDTO::getSalePickDeliveryItemInfoList).
                flatMap(Collection::stream).map(SalePickDeliveryItemInfoMiddleRequestDTO::getSaleOrderCode).distinct().collect(Collectors.toList());
        QueryWrapper<SaleOrderItemDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SaleOrderItemDO::getSkuId,skuIdList).in(SaleOrderItemDO::getSaleOrderCode,orderCodeList).eq(BaseDO::getDeleted,0);
        Map<String, Long> itemSkuMap = saleOrderItemDAO.list(wrapper).stream().collect(Collectors.toMap(item -> item.getSkuId() + "-" + item.getSaleOrderCode(), BaseDO::getId, (t1, t2) -> t1));
        //生成出库单关联的商品明细
        List<SaleOutTaskMiddleResponseDTO> outTaskList = ObjectCloneUtils.convertList(saleOutTaskDOList, SaleOutTaskMiddleResponseDTO.class);
        //用来拿出库单信息
        Map<String, SaleOutTaskMiddleResponseDTO> outMap = outTaskList.stream().
                collect(Collectors.toMap(g -> g.getSaleOrderCode() + "-" + g.getDeliveryWareHouseName(), Function.identity(), (t1, t2) -> t2));
        //生成明细
        List<SaleOutTaskDetailInfoRequestDTO> saleOutTaskDetailDtoList = dto.getSalePickDeliveryOrderInfoList().stream().
                map(SalePickDeliveryOrderInfoMiddleRequestDTO::getSalePickDeliveryItemInfoList).flatMap(Collection::stream).map(deliveryItem -> {
            SaleOutTaskDetailInfoRequestDTO requestDTO = new SaleOutTaskDetailInfoRequestDTO();
            SaleOutTaskMiddleResponseDTO saleOutTaskMiddleResponseDTO = outMap.get(deliveryItem.getSaleOrderCode()+"-"+deliveryItem.getDeliveryWareHouseName());
            requestDTO.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());//tenantId
            requestDTO.setAppId(saleOutTaskMiddleResponseDTO.getAppId());//appId
            requestDTO.setSaleOutTaskId(saleOutTaskMiddleResponseDTO.getId());//根据标识拿到出库单id
            requestDTO.setSaleOrderId(saleOutTaskMiddleResponseDTO.getSaleOrderId());//订单ID
            requestDTO.setSalePickGoodsId(salePickGoodsInfoResponseDTO.getId());//提货单号
            requestDTO.setSalePickGoodsCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());//提货单号
            requestDTO.setSaleOrderItemId(itemSkuMap.get(deliveryItem.getSkuId()+"-"+deliveryItem.getSaleOrderCode()));//商品明细ID
            requestDTO.setSalePickGoodsId(salePickGoodsInfoResponseDTO.getId());//提货单ID
            requestDTO.setSalePickGoodsCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());//提货单编号
            requestDTO.setSkuPickQuantity(deliveryItem.getSkuPickQuantity());//本次提货数量
            requestDTO.setSkuShipmentQuantity(deliveryItem.getSkuShipmentQuantity());//本次出库数量
            return requestDTO;
        }).collect(Collectors.toList());
        List<SaleOutTaskDetailInfoDO> saleOutTaskDetailInfoDOList = ObjectCloneUtils.convertList(saleOutTaskDetailDtoList, SaleOutTaskDetailInfoDO.class);
        log.info("生成的出库单关联的商品信息：{}",JSON.toJSONString(saleOutTaskDetailInfoDOList));
        if (!saleOutTaskDetailInfoDAO.saveBatch(saleOutTaskDetailInfoDOList)){
            throw new ApplicationException("生成出库单商品异常,请检查参数");
        }
        //记录出库单id 对应 编号 物流信息和收货地址要用
        Map<Long, String> saleOutTaskMap = outTaskList.stream().collect(Collectors.toMap(SaleOutTaskMiddleResponseDTO::getId, SaleOutTaskMiddleResponseDTO::getCode,(t1 , t2) -> t1));
        return saleOutTaskMap;
    }
    /**
     * @param dto
     * @Description: 提货单的出库单签收.
     * @Param: [saleOutTaskMiddleRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    @Override
    @Transactional
    public Boolean sign(SaleOutTaskMiddleRequestDTO dto) throws Exception {
        log.info("提货单的出库单签收参数:{}", JSON.toJSONString(dto));
        if (Objects.isNull(dto)) {
            throw new ApplicationException("提货单签收失败,传入参数为空");
        }
        if (Utils.isEmpty(dto.getIdList())) {
            throw new ApplicationException("提货单签收失败,传入id集合为空");
        }
        //根据出库单ID拿到对应出库单信息
        QueryWrapper<SaleOutTaskDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(SaleOutTaskDO::getId, dto.getIdList()).eq(SaleOutTaskDO::getDeleted, 0);
        List<SaleOutTaskDO> saleOrderItemDOList = saleOutTaskDAO.list(queryWrapper);
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskList = ObjectCloneUtils.convertList(saleOrderItemDOList, SaleOutTaskMiddleResponseDTO.class);
        if (!ObjectUtils.equals(dto.getIdList().size(),saleOutTaskList.size())) {//传入ID异常
            throw new ApplicationException("出库单签收,输入ID有误.");
        }
        //提货单数量
        Long salePickGoodsId = saleOutTaskList.stream().map(SaleOutTaskMiddleResponseDTO::getSalePickGoodsId).distinct().findFirst().get();
        Long count = saleOutTaskList.stream().map(SaleOutTaskMiddleResponseDTO::getSalePickGoodsId).distinct().count();
        //被签收的出库单id
        List<Long> signIdList = saleOutTaskList.stream().filter(f -> ObjectUtils.equals(f.getSignStatus(), 19)).
                map(SaleOutTaskMiddleResponseDTO::getId).collect(Collectors.toList());
        if (count != 1) {//签收 不管单个还是多个出库单 对应的提货单一定是一个
            throw new ApplicationException("出库单签收,输入ID只能对应一个提货单");
        }
        if (Utils.isNotEmpty(signIdList)) {
            throw new ApplicationException("该出库单ID" + signIdList + "已被签收.无法重复签收");
        }
        synchronized (salePickGoodsId.toString().intern()) {//字符串锁，相同提货单的修改操作排队执行
            //更改提货单的签收数量
            updatePickGoodsSignQuantity(saleOutTaskList);
            //更改订单的签收数量
            updateSaleOrderSignQuantity(dto.getIdList());
            //更改出库单关联商品的签收数量
            updateSaleOrderItemSignQuantity(dto.getIdList());
            //更改出库单信息
            updateSaleOutTaskInfo(saleOutTaskList,dto.getCreatedBy());
        }
        return Boolean.TRUE;
    }
    /**
     * @Description:  签收时修改订单的签收数量.
     * @Param: [idList]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    private void updateSaleOrderSignQuantity(List<Long> idList) throws Exception {
        //根据批量的出库单ID拿到批量的商品关联的出库信息
        QueryWrapper<SaleOutTaskDetailInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SaleOutTaskDetailInfoDO::getSaleOutTaskId, idList).eq(SaleOutTaskDetailInfoDO::getDeleted, 0);
        List<SaleOutTaskDetailInfoDO> saleOutTaskDetailInfoDOList = saleOutTaskDetailInfoDAO.list(queryWrapper);
        List<SaleOutTaskDetailInfoMiddleResponseDTO> list = ObjectCloneUtils.convertList(saleOutTaskDetailInfoDOList, SaleOutTaskDetailInfoMiddleResponseDTO.class);
        //订单ID
        List<Long> saleOrderIdList = list.stream().map(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderId).distinct().collect(Collectors.toList());
        //每笔订单对应的总发货数
        Map<Long, Long> saleOrderDeliveryMap = list.stream().collect(Collectors.groupingBy(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderId,
                Collectors.summingLong(SaleOutTaskDetailInfoMiddleResponseDTO::getSkuShipmentQuantity)));
        //拿到订单信息
        QueryWrapper<SaleOrderInfoDO> doQueryWrapper = new QueryWrapper<>();
        doQueryWrapper.lambda().in(SaleOrderInfoDO::getId, saleOrderIdList).eq(BaseDO::getDeleted, 0);
        List<SaleOrderInfoDO> saleOrderInfoDOList = saleOrderInfoDAO.list(doQueryWrapper);
        List<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOList = ObjectCloneUtils.convertList(saleOrderInfoDOList, SaleOrderInfoResponseDTO.class);
        log.info("签收时更改当前出库单关联的订单签收数量--订单数据: {}", JSON.toJSONString(saleOrderInfoResponseDTOList));
        //更改订单签收数量
        List<SaleOrderInfoRequestDTO> saleOrderItemList = saleOrderInfoResponseDTOList.stream().map(pick -> {
            Long skuShipmentQuantity = saleOrderDeliveryMap.get(pick.getId());//订单本次签收数量
            Long saleOrderSignQuantity = pick.getTotalSignQuantity();//订单总签收数量
            if (Objects.isNull(saleOrderSignQuantity)) {
                saleOrderSignQuantity = 0L;
            }
            saleOrderSignQuantity += skuShipmentQuantity;
            SaleOrderInfoRequestDTO requestDTO = new SaleOrderInfoRequestDTO();
            requestDTO.setId(pick.getId());//订单id
            requestDTO.setTotalSignQuantity(saleOrderSignQuantity);//订单的签收数量
            requestDTO.setUpdatedTime(new Date());//修改时间
            return requestDTO;
        }).collect(Collectors.toList());
        List<SaleOrderInfoDO> orderInfoDOList = ObjectCloneUtils.convertList(saleOrderItemList, SaleOrderInfoDO.class);
        log.info("修改后的订单信息:{}",JSON.toJSONString(orderInfoDOList));
        if (Utils.isEmpty(orderInfoDOList)){
            throw new ApplicationException("签收时提货单信息为空.");
        }
        saleOrderInfoDAO.updateBatchById(orderInfoDOList);
    }
    /**
     * @Description: 签收时更改当前提货单的签收数量.
     * @Param: [list]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    private void updatePickGoodsSignQuantity(List<SaleOutTaskMiddleResponseDTO> list) throws Exception {
        log.info("更改提货单信息入参:{}",JSON.toJSONString(list));
        if (Utils.isEmpty(list)) {
            throw new ApplicationException("出库单签收,关联的提货单数据为空");
        }
        Long salePickGoodsId = Utils.selectOne(list.stream().map(SaleOutTaskMiddleResponseDTO::getSalePickGoodsId).distinct().collect(Collectors.toList()));//提货单ID
        Long signTotalQuantity = list.stream().mapToLong(SaleOutTaskMiddleResponseDTO::getDeliveryQuantity).sum();//指定出库单的总发货量 也就是提货单签收的总数量
        SalePickGoodsInfoDTO pickGoodsInfoDTO = selectById(salePickGoodsId);//拿到对应的提货单信息
        SalePickGoodsInfoRequestDTO salePickGoodsInfoRequestDTO = pickGoodsInfoDTO.clone(SalePickGoodsInfoRequestDTO.class);
        Long totalSignQuantity = Objects.isNull(salePickGoodsInfoRequestDTO.getTotalSignQuantity()) ? 0L : salePickGoodsInfoRequestDTO.getTotalSignQuantity();;//商品总签收数
        Long quantity = salePickGoodsInfoRequestDTO.getTotalGoodsNumber();//总数量
        log.info("签收时更改当前提货单的签收数量--商品总签收数: {}--商品总数量: {}", totalSignQuantity,quantity);
        totalSignQuantity += signTotalQuantity;
        if (totalSignQuantity <= quantity) {
            salePickGoodsInfoRequestDTO.setTotalSignQuantity(totalSignQuantity);//更新签收数
        } else {
            throw new ApplicationException("商品库存不足.");
        }
        SalePickGoodsInfoDO salePickGoodsInfoDO = salePickGoodsInfoRequestDTO.clone(SalePickGoodsInfoDO.class);
        DomainUtils.initUpdateTimeInfo(salePickGoodsInfoDO);
        log.info("修改后的提货信息:{}",JSON.toJSONString(salePickGoodsInfoDO));
        if (Objects.isNull(salePickGoodsInfoDO)){
            throw new ApplicationException("签收时提货信息为空.");
        }
        salePickGoodsInfoDao.updateById(salePickGoodsInfoDO);
    }
    /**
     * @Description: 签收时更改当前出库单关联的商品签收数量.
     * @Param: [idList]
     * @return: java.util.Map<java.lang.Long,java.lang.Long>
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    private void updateSaleOrderItemSignQuantity(List<Long> idList) throws Exception {
        //根据批量的出库单ID拿到批量的商品关联的出库信息
        QueryWrapper<SaleOutTaskDetailInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SaleOutTaskDetailInfoDO::getSaleOutTaskId, idList).eq(SaleOutTaskDetailInfoDO::getDeleted, 0);
        List<SaleOutTaskDetailInfoDO> saleOutTaskDetailInfoDOList = saleOutTaskDetailInfoDAO.list(queryWrapper);
        List<SaleOutTaskDetailInfoMiddleResponseDTO> list = ObjectCloneUtils.convertList(saleOutTaskDetailInfoDOList, SaleOutTaskDetailInfoMiddleResponseDTO.class);
        //商品id集合
        List<Long> itemIdList = list.stream().map(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderItemId).distinct().collect(Collectors.toList());
        //每笔商品对应的发货数
        final Map<Long, Long> itemMap = list.stream().collect(Collectors.toMap(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderItemId,
                SaleOutTaskDetailInfoMiddleResponseDTO::getSkuShipmentQuantity,(t1 , t2) -> t1 + t2));
        //拿到商品信息
        QueryWrapper<SaleOrderItemDO> query = new QueryWrapper<>();
        query.lambda().in(SaleOrderItemDO::getId, itemIdList).eq(SaleOrderItemDO::getDeleted, 0);
        List<SaleOrderItemDO> saleOrderItemDOList = saleOrderItemDAO.list(query);
        List<SaleOrderItemMiddleResponseDTO> saleOrderItemMiddleResponseDTOList = ObjectCloneUtils.convertList(saleOrderItemDOList, SaleOrderItemMiddleResponseDTO.class);
        log.info("签收时更改当前出库单关联的商品签收数量--商品数据: {}", JSON.toJSONString(saleOrderItemMiddleResponseDTOList));
        //更改签收数量
        List<SaleOrderItemMiddleRequestDTO> saleOrderItemList = saleOrderItemMiddleResponseDTOList.stream().map(m -> {
            Long skuShipmentQuantity = itemMap.get(m.getId());//商品本次签收数量
            Long itemSignQuantity = m.getSignQuantity();//商品总签收数量
            if (Objects.isNull(itemSignQuantity)) {
                itemSignQuantity = 0L;
            }
            itemSignQuantity += skuShipmentQuantity;
            SaleOrderItemMiddleRequestDTO dto = new SaleOrderItemMiddleRequestDTO();
            dto.setId(m.getId());//商品明细id
            dto.setSignQuantity(itemSignQuantity);//商品的签收数量
            dto.setUpdatedTime(new Date());//修改时间
            return dto;
        }).collect(Collectors.toList());
        List<SaleOrderItemDO> itemDOList = ObjectCloneUtils.convertList(saleOrderItemList, SaleOrderItemDO.class);
        log.info("修改后的商品信息:{}",JSON.toJSONString(itemDOList));
        if (Utils.isEmpty(itemDOList)){
            throw new ApplicationException("签收时商品信息为空.");
        }
        saleOrderItemDAO.updateBatchById(itemDOList);
    }
    /**
     * @Description:  修改出库单签收信息.
     * @Param: [list, operator]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    private void updateSaleOutTaskInfo(List<SaleOutTaskMiddleResponseDTO> list,String operator){
        log.info("出库单签收信息:{},操作人:{}",JSON.toJSONString(list),operator);
        Date now = new Date();
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskList = list.stream().map(map -> {//更改出库单状态
            SaleOutTaskMiddleResponseDTO dto = new SaleOutTaskMiddleResponseDTO();
            dto.setId(map.getId());
            dto.setSignStatus(19);//已签收
            dto.setUpdatedTime(now);
            dto.setSignBy(operator);//签收人
            dto.setSignTime(now);
            return dto;
        }).collect(Collectors.toList());
        List<SaleOutTaskDO> saleOutTaskDOList = ObjectCloneUtils.convertList(saleOutTaskList, SaleOutTaskDO.class);
        log.info("修改后的出库单信息:{}",JSON.toJSONString(saleOutTaskDOList));
        if (Utils.isEmpty(saleOutTaskDOList)){
            throw new ApplicationException("出库单信息为空.");
        }
        saleOutTaskDAO.updateBatchById(saleOutTaskDOList);
    }
    /**
     * @param id
     * @Description: 根据提货单ID查询商品sku列表.
     * @Param: [id]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SalePickGoodsDetailInfoMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/21
     */
    @Override
    public List<SalePickGoodsDetailInfoMiddleResponseDTO> searchSkuDetailList(Long id) throws Exception {
        List<SalePickGoodsDetailInfoMiddleResponseDTO> salePickGoodsDetailInfoMiddleResponseDTOList = Lists.newArrayList();
        //拿到提货单下的sku信息
        QueryWrapper<SalePickGoodsOrderSkuDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SalePickGoodsOrderSkuDO::getPickGoodsInfoId, id).eq(SalePickGoodsOrderSkuDO::getDeleted, 0);
        List<SalePickGoodsOrderSkuDO> salePickGoodsOrderSkuDOList = salePickGoodsOrderSkuDAO.list(queryWrapper);
        List<SalePickGoodsOrderSkuResponseDTO> list = ObjectCloneUtils.convertList(salePickGoodsOrderSkuDOList, SalePickGoodsOrderSkuResponseDTO.class);
        List<Long> pickGoodsOrderId = list.stream().map(SalePickGoodsOrderSkuResponseDTO::getPickGoodsOrderId).collect(Collectors.toList());//提货单集合
        //拿到提货单下的订单信息
        QueryWrapper<SalePickGoodsOrderDO> query = new QueryWrapper<>();
        query.lambda().in(SalePickGoodsOrderDO::getId, pickGoodsOrderId).eq(SalePickGoodsOrderDO::getDeleted, 0);
        List<SalePickGoodsOrderDO> salePickGoodsOrderDOList = salePickGoodsOrderDAO.list(query);
        List<SalePickGoodsOrderResponseDTO> salePickGoodsOrderResponseDTOList = ObjectCloneUtils.convertList(salePickGoodsOrderDOList, SalePickGoodsOrderResponseDTO.class);
        //生成sku列表
        Map<Long, SalePickGoodsOrderResponseDTO> pickOrderMap = salePickGoodsOrderResponseDTOList.stream().collect(Collectors.toMap(SalePickGoodsOrderResponseDTO::getId, v->v, (t1, t2) -> t2));
        Map<String, List<SalePickGoodsOrderSkuResponseDTO>> skuMap = list.stream().collect(Collectors.groupingBy(item -> item.getPickGoodsOrderId() + "-" + item.getWarehouse()));
        skuMap.forEach((k,v)->{
            SalePickGoodsDetailInfoMiddleResponseDTO responseDTO = new SalePickGoodsDetailInfoMiddleResponseDTO();
            String[] split = k.split("-");
            if (pickOrderMap.containsKey(Long.valueOf(split[0]))) {
                SalePickGoodsOrderResponseDTO salePickGoodsOrderResponseDTO = pickOrderMap.get(Long.valueOf(split[0]));
                responseDTO.setSaleOrderCode(salePickGoodsOrderResponseDTO.getSaleOrderCode());//订单
                responseDTO.setOrderType(salePickGoodsOrderResponseDTO.getOrderType());
            }
            responseDTO.setDeliveryWareHouseName(split[1]);//仓库名称
            responseDTO.setDeliveryWareHouseId(v.get(0).getWarehouseId());//仓库id
            responseDTO.setSalePickGoodsItemInfoList(ObjectCloneUtils.convertList(v,SalePickGoodsItemInfoMiddleResponseDTO.class));
            salePickGoodsDetailInfoMiddleResponseDTOList.add(responseDTO);
        });
        return salePickGoodsDetailInfoMiddleResponseDTOList;
    }

    @Override
    public SalePickGoodsInfoResponseDTO selectByPickCode(SalePickGoodsInfoRequestQuery query) {
        return salePickGoodsInfoDao.selectByPickCode(query);
    }
}