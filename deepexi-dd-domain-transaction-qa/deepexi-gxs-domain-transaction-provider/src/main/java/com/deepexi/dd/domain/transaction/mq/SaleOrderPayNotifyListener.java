package com.deepexi.dd.domain.transaction.mq;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderPayNotifyDto;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderPayNotifyClient;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderPayNotifySearchQuery;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName : OrderSureMessageListener
 * @Description : 订单确认MQ监听
 * @Author : yuanzaishun
 * @Date: 2020-07-22 20:13
 */
//@Component
@RocketMQMessageListener(topic = "${finance.payment.record.topic}", consumerGroup = "${rocketmq.consumer.group.record}",accessKey = "${rocketmq.producer.access.key}",secretKey = "${rocketmq.producer.secret.key}",consumeMode = ConsumeMode.CONCURRENTLY)
public class SaleOrderPayNotifyListener implements RocketMQListener<SaleOrderPayNotifyDto> {
    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    private static Integer SURE=1;
    private static Integer CANCEL=0;
    private static final String SUCCESS="0";
    private static final Logger LOGGER = LoggerFactory.getLogger(SaleOrderPayNotifyListener.class);
    SaleOrderPayNotifyClient saleOrderPayNotifyClient;
    @Override
    public void onMessage(SaleOrderPayNotifyDto message) {
        LOGGER.info("收到交易确认消息 received:{}", JsonUtil.bean2JsonString(message));
        List<SaleOrderPayNotifyResponseDTO> records=getSaleOrderPayNotify(message);
        if(records!=null && records.size()>0) {
            LOGGER.info("交易记录已经存在:{}",message.getPayNo());

        }else {
            //如果确认
            if (SURE.equals(message.getType())) {
                try {
                    saleOrderInfoService.payOrderNextStep(message);
                } catch (Exception e) {
                    LOGGER.error("扭转链路异常:{}", JsonUtil.bean2JsonString(message));
                    LOGGER.error("扭转链路异常:{}", e);
                    throw new ApplicationException("扭转链路异常:", e);
                }
            } else {
                try {
                    saleOrderInfoService.cancelOrderPayAmount(message);
                }catch(Exception e){
                    LOGGER.error("撤销金额失败:{}", JsonUtil.bean2JsonString(message));
                    LOGGER.error("撤销金额失败:{}", e);
                    throw new ApplicationException("撤销金额失败");
                }
            }
        }
        //保存消息记录
        saveSaleOrderPayNotify(message);

    }

    private void saveSaleOrderPayNotify(SaleOrderPayNotifyDto payNotifyDto){
        SaleOrderPayNotifyRequestDTO requestDTO=payNotifyDto.clone(SaleOrderPayNotifyRequestDTO.class);
        saleOrderPayNotifyClient.insert(requestDTO);
    }

    private List<SaleOrderPayNotifyResponseDTO> getSaleOrderPayNotify(SaleOrderPayNotifyDto payNotifyDto)  {
        SaleOrderPayNotifySearchQuery query=new SaleOrderPayNotifySearchQuery();
        query.setOrderId(payNotifyDto.getOrderId());
        query.setPayNo(payNotifyDto.getPayNo());
        try {
            Payload<List<SaleOrderPayNotifyResponseDTO>> payload = saleOrderPayNotifyClient.querySaleOrderNotify(query);
            if (SUCCESS.equals(payload.getCode())) {
                return GeneralConvertUtils.convert2List(payload.getPayload(), SaleOrderPayNotifyResponseDTO.class);
            }
        }catch(Exception e){
            LOGGER.error("查询支付记录异常:{}",JsonUtil.bean2JsonString(payNotifyDto));
            LOGGER.error("查询支付记录异常:",e);
        }
        return null;
    }
}
