package com.deepexi.dd.domain.transaction.config;

import com.deepexi.dd.domain.transaction.mq.yuncang.ConfirmDeliveryListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

//@Configuration
public class GLDeliveredReceiveMqInit {
    //消费者的组名
    @Value("${gl_delivered_order_consumer}")
    private String consumerGroup;

    //NameServer 地址
    @Value("${gl.namesrvAddr}")
    private String namesrvAddr;

    @Value("${wms_sso_delivered_to_gsale}")
    private String topic;

    private static final Logger logger = LoggerFactory.getLogger(GLDeliveredReceiveMqInit.class);

    @Autowired
    private ConfirmDeliveryListener confirmDeliveryListener;

    /**
     * mq 消费者配置
     *
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQPushConsumer deliveredConsumer() throws MQClientException {
        logger.info("GLDeliveredReceiveConsumer 正在创建---------------------------------------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setInstanceName(UUID.randomUUID().toString());
        consumer.setConsumerGroup(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        // 设置监听
        consumer.registerMessageListener(confirmDeliveryListener);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        try {
            // 设置该消费者订阅的主题和tag，如果订阅该主题下的所有tag，则使用*,
            consumer.subscribe(topic, "*");
            consumer.start();
            logger.info("consumer 创建成功 groupName={}, topics={}, namesrvAddr={}", consumerGroup, topic, namesrvAddr);
        } catch (MQClientException e) {
            logger.error("consumer 创建失败!",e);
        }
        return consumer;
    }

}