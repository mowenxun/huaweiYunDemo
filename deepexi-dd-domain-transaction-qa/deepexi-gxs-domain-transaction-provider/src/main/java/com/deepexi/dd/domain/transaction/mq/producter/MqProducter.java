package com.deepexi.dd.domain.transaction.mq.producter;

import com.deepexi.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : MqProducter
 * @Description : 消息生产者
 * @Author : yuanzaishun
 * @Date: 2020-08-20 17:11
 */
@Slf4j
//@RestController
//@RequestMapping("/test")
@Service
public class MqProducter {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private static final String TOPOIC = "DEEPEXI_DD";

    // @PostMapping("/sendMsg")
    public void confirmOrCancel(@RequestBody String msg) {
        String destination = String.format("%s:%s", TOPOIC, "TRANSACTION-ORDER-CONFIRMORCANCEL");
        rocketMQTemplate.syncSend(destination,
                MessageBuilder.withPayload(msg).setHeader(RocketMQHeaders.TRANSACTION_ID, IdGenerator.getUuId()).build());
        log.info("确认收款，发n送MQ通知订单!,tag:{},msg:{}", "TRANSACTION-ORDER-CONFIRMORCANCEL", msg);
    }
}
