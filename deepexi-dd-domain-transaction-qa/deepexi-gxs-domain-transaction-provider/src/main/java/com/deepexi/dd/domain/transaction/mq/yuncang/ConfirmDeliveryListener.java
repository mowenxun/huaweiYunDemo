package com.deepexi.dd.domain.transaction.mq.yuncang;

import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.orderDeliveryConsigneeInfo.OrderDeliveryConsigneeInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.*;
import com.deepexi.dd.domain.transaction.remote.order.*;
import com.deepexi.dd.domain.transaction.remote.order.log.SalePickReceiveOrderYunLogRemote;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsInfoService;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsConsigneeRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.extension.ApplicationException;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-25 11:06
 * 云仓确认出库后将出库结果回传给一体化系统
 */
@Service
public class ConfirmDeliveryListener implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmDeliveryListener.class);
    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;
    @Autowired
    private SalePickGoodsConsigneeClient salePickGoodsConsigneeClient;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;
    @Autowired
    private SalePickReceiveOrderYunLogRemote salePickReceiveOrderYunLogRemote;
    @Autowired
    private SalePickGoodsOrderSkuClient salePickGoodsOrderSkuClient;
    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    @Autowired
    private SalePickGoodsOrderClient salePickGoodsOrderClient;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(list)) {
            logger.info("MQ接收消息为空，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        logger.info("MQ接收到的消息为：" + messageExt.toString());
        String body = null;
        try {
            String topic = messageExt.getTopic();
            String tags = messageExt.getTags();
            body = new String(messageExt.getBody(), "utf-8");
            logger.info("MQ消息topic={}, tags={}, 消息内容={}", topic,tags,body);
        } catch (Exception e) {
            logger.error("获取MQ消息内容异常{}",e);
        }
        // TODO 处理业务逻辑
        if(null != body){
            //json转换
            WarehouseOutResultsDTO warehouseOutResultsDTO = JsonUtil.json2Bean(body, WarehouseOutResultsDTO.class);
            //获取提货单Id
            SalePickGoodsInfoRequestQuery query = new SalePickGoodsInfoRequestQuery();
            query.setPickGoodsCode(warehouseOutResultsDTO.getSourceOrderNo());
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = null;
            try {
                salePickGoodsInfoResponseDTO = salePickGoodsInfoService.selectByPickCode(query);
                logger.info("根据提货单编号获取提货信息:{}", salePickGoodsInfoResponseDTO);
            } catch (Exception e) {
                logger.error("获取提货信息失败:{}",e.getMessage());
            }

            SalePickDeliveryInfoRequestDTO salePickDeliveryInfoRequestDTO = new SalePickDeliveryInfoRequestDTO();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                salePickDeliveryInfoRequestDTO.setDeliveryTime(null == warehouseOutResultsDTO.getDeliveryDate() ? null : simpleDateFormat.parse(warehouseOutResultsDTO.getDeliveryDate()));
            } catch (ParseException e) {
               logger.error("发货时间转换异常",e.getMessage());
            }
            salePickDeliveryInfoRequestDTO.setPickGoodsCode(warehouseOutResultsDTO.getSourceOrderNo());
            salePickDeliveryInfoRequestDTO.setDeliveryType(3);
            if (null != salePickGoodsInfoResponseDTO) {
                salePickDeliveryInfoRequestDTO.setPickGoodsId(salePickGoodsInfoResponseDTO.getId());
            } else {
                throw new ApplicationException("提货单信息不存在");
            }

            //设置提货单地址信息
            SalePickGoodsConsigneeRequestQuery salePickGoodsConsigneeRequestQuery = new SalePickGoodsConsigneeRequestQuery();
            salePickGoodsConsigneeRequestQuery.setPickGoodsInfoCode(warehouseOutResultsDTO.getSourceOrderNo());
            SalePickGoodsConsigneeResponseDTO salePickGoodsConsigneeResponseDTO = null;
            try {
                salePickGoodsConsigneeResponseDTO = salePickGoodsConsigneeClient.selectByPickCode(salePickGoodsConsigneeRequestQuery);
                logger.info("根据提货单编码获取提单地址:{}", salePickGoodsConsigneeResponseDTO);
            } catch (Exception e) {
                logger.error("获取提货单地址失败");
            }
            if (null != salePickGoodsConsigneeResponseDTO) {
                salePickDeliveryInfoRequestDTO.setOrderDeliveryConsigneeInfo(GeneralConvertUtils.conv(salePickGoodsConsigneeResponseDTO, OrderDeliveryConsigneeInfoRequestDTO.class));
            } else {
                throw new ApplicationException("没找到提货地址信息");
            }

            //设置订单明细
            List<WarehouseOutResultsOrderItem> orderItems = warehouseOutResultsDTO.getOrderItems();
            List<SalePickDeliveryItemInfoRequestDTO> salePickDeliveryItemInfoList = new ArrayList<>();
            //根据云仓回传的行号查出对应的sku信息
            orderItems.forEach(item -> {
                //根据行号查sku信息
                SalePickGoodsOrderSkuResponseDTO salePickGoodsOrderSkuResponseDTO = null;
                try {
                    salePickGoodsOrderSkuResponseDTO = salePickGoodsOrderSkuClient.selectByRowCode(item.getSourceOrderLineId());
                    logger.info("根据sku行号查询sku信息当前行号:{},response:{}", item.getSourceOrderLineId(), salePickGoodsOrderSkuResponseDTO);
                } catch (Exception e) {
                    logger.error("获取sku行号失败当前行号:{}", item.getSourceOrderLineId());
                }
                if (null == salePickGoodsOrderSkuResponseDTO) {
                    throw new ApplicationException("当前行号" + item.getSourceOrderLineId() + "的sku信息不存在");
                }
                SalePickDeliveryItemInfoRequestDTO salePickDeliveryItemInfoRequestDTO = new SalePickDeliveryItemInfoRequestDTO();
                salePickDeliveryItemInfoRequestDTO.setSkuId(salePickGoodsOrderSkuResponseDTO.getSkuId());
                //本次出库数量
                salePickDeliveryItemInfoRequestDTO.setSkuShipmentQuantity(item.getDeliveredQuantity());
                //本次申请数量
                salePickDeliveryItemInfoRequestDTO.setSkuPickQuantity(item.getOrderQuantity());
                salePickDeliveryItemInfoRequestDTO.setId(salePickGoodsOrderSkuResponseDTO.getId());
                //查询当前sku对应的saleOrderCode
                SalePickGoodsOrderResponseDTO salePickGoodsOrderResponseDTO = null;
                try {
                    salePickGoodsOrderResponseDTO = salePickGoodsOrderClient.selectById(salePickGoodsOrderSkuResponseDTO.getPickGoodsOrderId());
                    logger.info("获取sku对应的订单Code:{}", salePickGoodsOrderResponseDTO);
                } catch (Exception e) {
                    logger.error("获取sku对应的订单Code失败:{}",e.getMessage());
                }
                if (null != salePickGoodsOrderResponseDTO) {
                    salePickDeliveryItemInfoRequestDTO.setSaleOrderCode(salePickGoodsOrderResponseDTO.getSaleOrderCode());
                } else {
                    throw new ApplicationException("sku对应的SaleOrderCode不存在");
                }
                salePickDeliveryItemInfoList.add(salePickDeliveryItemInfoRequestDTO);
            });
            List<SalePickDeliveryOrderInfoRequestDTO> salePickDeliveryOrderInfoRequestDTO = new ArrayList<>();
            //设置订单信息
            Map<String, List<SalePickDeliveryItemInfoRequestDTO>> map = salePickDeliveryItemInfoList.stream().collect(Collectors.groupingBy(SalePickDeliveryItemInfoRequestDTO::getSaleOrderCode));
            List<String> num = salePickDeliveryItemInfoList.stream().map(SalePickDeliveryItemInfoRequestDTO::getSaleOrderCode).distinct().collect(Collectors.toList());
            for (int i = 0; i < num.size(); i++) {
                SalePickDeliveryOrderInfoRequestDTO spo = new SalePickDeliveryOrderInfoRequestDTO();
                spo.setSaleOrderCode(num.get(i));
                spo.setSalePickDeliveryItemInfoList(map.get(num.get(i)));
                salePickDeliveryOrderInfoRequestDTO.add(spo);
            }
            //根据仓库编码查仓库名称
            List<DepotResponseDTO> allDepotList = null;
            try {
                allDepotList = saleOrderInfoService.getAllDepotList(appRuntimeEnv.getAppId(), null);
                logger.info("获取仓库列表");
            } catch (Exception e) {
                logger.error("获取仓库列表失败:{}",e.getMessage());
            }
            if (null != allDepotList) {
                allDepotList.forEach(depot -> {
                    if (depot.getDepotNo().equals(warehouseOutResultsDTO.getSourceWarehouseCode())) {
                        salePickDeliveryOrderInfoRequestDTO.forEach(sale -> sale.setDeliveryWareHouseName(depot.getName()));
                    }
                });
            } else {
                logger.error("获取仓库名称失败");
            }
            for (SalePickDeliveryOrderInfoRequestDTO pickDeliveryOrderInfoRequestDTO : salePickDeliveryOrderInfoRequestDTO) {
                if(null == pickDeliveryOrderInfoRequestDTO.getDeliveryWareHouseName()){
                    logger.error("查询"+pickDeliveryOrderInfoRequestDTO.getSaleOrderCode()+"发货仓库名称失败");
                }
            }
            salePickDeliveryInfoRequestDTO.setSalePickDeliveryOrderInfoList(salePickDeliveryOrderInfoRequestDTO);
            //设置物流信息
            SalePickDeliveryLogisticsInfoRequestDTO salePickDeliveryLogisticsInfo = new SalePickDeliveryLogisticsInfoRequestDTO();
            salePickDeliveryLogisticsInfo.setLicensePlate(warehouseOutResultsDTO.getLicenseNumber());
            salePickDeliveryInfoRequestDTO.setSalePickDeliveryLogisticsInfo(salePickDeliveryLogisticsInfo);
            GroupResultVO groupResultVO = new GroupResultVO();
            groupResultVO.setId(312L);
            groupResultVO.setName("格力董明珠店");
            groupResultVO.setCode("gree-deparent");
            groupResultVO.setExtend1(1);
            groupResultVO.setParentId(311L);
            appRuntimeEnv.setTopOrganization(groupResultVO);
            appRuntimeEnv.setUserOrganization(groupResultVO);
            appRuntimeEnv.setUsername("gree");
            appRuntimeEnv.setTenantId("gree");
            appRuntimeEnv.setAppId(623L);

            SalePickReceiveOrderYunLogRequestDTO record =  new SalePickReceiveOrderYunLogRequestDTO();
            record.setAppId(appRuntimeEnv.getAppId());
            record.setCreatedBy(appRuntimeEnv.getUsername());
            record.setTenantId(appRuntimeEnv.getTenantId());
            record.setCreatedTime(new Date());
            record.setOperationType(2);
            record.setPickOrderCode(warehouseOutResultsDTO.getSourceOrderNo());
            record.setReceiveBody(JsonUtil.bean2JsonString(warehouseOutResultsDTO));
            record.setRemark("wms发货成功");
            //发货
            try {
                record.setResultCode("发货成功");
                Boolean aBoolean = salePickGoodsInfoService.pickPlanDeliveryGoods(salePickDeliveryInfoRequestDTO);
                if(!aBoolean){
                    record.setResultCode("发货失败");
                    logger.error("发货失败");
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                //记录云仓发货
                salePickReceiveOrderYunLogRemote.insert(record);
            } catch (Exception e) {
                logger.error("发货失败", e);
                record.setResultCode(e.getMessage());
                //记录云仓发货
                salePickReceiveOrderYunLogRemote.insert(record);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
