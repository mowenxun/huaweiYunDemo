package com.deepexi.dd.domain.transaction.mq.producter;

import com.deepexi.util.IdGenerator;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName : MqMessageProducter
 * @Description : MQ消息通知
 * @Author : yuanzaishun
 * @Date: 2020-09-11 15:49
 */
@Component
public class MqMessageProducter {
    Logger logger= LoggerFactory.getLogger(MqMessageProducter.class);
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     *
     * @param topic
     * @param tag
     * @param message
     */
    public void sendMsg(String topic,String tag,String message){
        String destination = String.format("%s:%s", topic,tag);
        rocketMQTemplate.syncSend(destination,
                MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.TRANSACTION_ID, IdGenerator.getUuId()).build());
        logger.info("发送MQ通知订单!,tag:{},msg:{}",tag, message);
    }
}
