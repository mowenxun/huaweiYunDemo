package com.deepexi.dd.domain.transaction.mq.yuncang;

import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.domain.dto.CanalOrderListenerDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsPlanInfoRequestDTO;
import com.deepexi.dd.domain.transaction.remote.order.log.SalePickReceiveOrderYunLogRemote;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsInfoService;
import com.deepexi.dd.middle.order.domain.dto.SalePickReceiveOrderYunLogRequestDTO;
import com.deepexi.util.JsonUtil;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-25 10:57
 * 取消订单结果监听
 */
@Service
public class CanalOrderListener implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(CanalOrderListener.class);

    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private SalePickReceiveOrderYunLogRemote salePickReceiveOrderYunLogRemote;

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
            CanalOrderListenerDTO canalOrderListenerDTO = JsonUtil.json2Bean(body, CanalOrderListenerDTO.class);
            //判断是否取消成功
            if (canalOrderListenerDTO.getDealType() == 2) {
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

                SalePickGoodsPlanInfoRequestDTO salePickGoodsPlanInfoRequestDTO = new SalePickGoodsPlanInfoRequestDTO();
                salePickGoodsPlanInfoRequestDTO.setCloseType("close");
                salePickGoodsPlanInfoRequestDTO.setAppId(appRuntimeEnv.getAppId());
                salePickGoodsPlanInfoRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
                salePickGoodsPlanInfoRequestDTO.setPickGoodsCode(canalOrderListenerDTO.getSourceOrderNo());
                salePickGoodsPlanInfoRequestDTO.setCancelCause(canalOrderListenerDTO.getMessage());

                SalePickReceiveOrderYunLogRequestDTO record =  new SalePickReceiveOrderYunLogRequestDTO();
                record.setAppId(appRuntimeEnv.getAppId());
                record.setCreatedBy(appRuntimeEnv.getUsername());
                record.setTenantId(appRuntimeEnv.getTenantId());
                record.setCreatedTime(new Date());
                record.setOperationType(1);
                record.setPickOrderCode(canalOrderListenerDTO.getSourceOrderNo());
                record.setReceiveBody(JsonUtil.bean2JsonString(canalOrderListenerDTO));
                try {
                    record.setResultCode("取消订单成功");
                    record.setRemark(canalOrderListenerDTO.getMessage());
                    Boolean payload = salePickGoodsInfoService.cancelPickGoods(salePickGoodsPlanInfoRequestDTO).getPayload();
                    if(!payload){
                        logger.info("取消订单失败");
                        record.setResultCode("取消订单失败");
                    }
                    //记录云仓回传
                    salePickReceiveOrderYunLogRemote.insert(record);
                } catch (Exception e) {
                    logger.error("errorMessage:{}",e.getMessage());
                    record.setResultCode(e.getMessage());
                    salePickReceiveOrderYunLogRemote.insert(record);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
