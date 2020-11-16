package com.deepexi.dd.domain.transaction.config;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/26/19:23
 * @Description:
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * @ClassName GlMqConfig
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/26
 * @Version V1.0
 **/
//@Configuration
public class GlMqConfig {

    /*@Bean(name = "glDefaultMQProducer")
    @ConditionalOnClass(DefaultMQProducer.class)
    @Order(1)
    public DefaultMQProducer glmqProducer()throws Exception {
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("XIAOMOGROUP");
        defaultMQProducer.setInstanceName(UUID.randomUUID().toString());
        defaultMQProducer.setNamesrvAddr("10.52.48.27:9876");
        defaultMQProducer.start();
        return defaultMQProducer;
    }*/

}




