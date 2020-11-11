package com.deepexi.dd.middle.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * @author
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAspectJAutoProxy(exposeProxy = true)
public class StartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class, args);
    }

}
