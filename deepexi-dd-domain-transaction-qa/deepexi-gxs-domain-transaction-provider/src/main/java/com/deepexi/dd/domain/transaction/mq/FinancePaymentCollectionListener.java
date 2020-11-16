package com.deepexi.dd.domain.transaction.mq;

import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderCollectionNotifyDto;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.JsonUtil;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName : FinancePaymentCollectionListener
 * @Description : 收款单发起监听器
 * @Author : yuanzaishun
 * @Date: 2020-07-23 20:39
 */
//@Component
@RocketMQMessageListener(topic = "${finance.payment.collection.topic}", consumerGroup = "${rocketmq.consumer.group.collection}",accessKey = "${rocketmq.producer.access.key}",secretKey = "${rocketmq.producer.secret.key}",consumeMode = ConsumeMode.CONCURRENTLY)
public class FinancePaymentCollectionListener implements RocketMQListener<SaleOrderCollectionNotifyDto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FinancePaymentCollectionListener.class);
    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    @Override
    public void onMessage(SaleOrderCollectionNotifyDto message) {
        LOGGER.info("收到按单收款消息received:{}", JsonUtil.bean2JsonString(message));
        saleOrderInfoService.saleOrdercollection(message);
    }
}
