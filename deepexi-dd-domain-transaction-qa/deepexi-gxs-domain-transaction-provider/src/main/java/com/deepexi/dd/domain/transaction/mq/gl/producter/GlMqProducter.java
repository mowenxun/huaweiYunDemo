package com.deepexi.dd.domain.transaction.mq.gl.producter;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/26/19:30
 * @Description:
 */

import com.deepexi.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * 格力生产者
 *
 * @ClassName GlMqProducter
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/26
 * @Version V1.0
 **/
@Slf4j
@Service
public class GlMqProducter {

    @Value("${gl.namesrvAddr}")
    String namesrvAddr;
    @Value("${gl.producerGroup}")
    String producerGroup;

    public void sendMqMsg(String TOPOIC, String msg) throws Exception {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setInstanceName(UUID.randomUUID().toString());
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        defaultMQProducer.start();
        Message message = new Message(TOPOIC, msg.getBytes("UTF-8"));
        SendResult sendResult = defaultMQProducer.send(message);
        log.info("发送消息到格力mq!topic={};msg={}", TOPOIC, msg);
        log.info("sendResult={}", sendResult);
    }
}
