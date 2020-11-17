package com.deepexi.dd.domain.transaction.config;

import com.deepexi.xxl.service.executor.JobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
//@Configuration
public class XxlJobExecutorConfiguration {
    @Value("${deepexi.job.admin.addresses}")
    private String adminAddresses;
    @Value("${deepexi.job.executor.appname}")
    private String appName;
    @Value("${deepexi.job.executor.ip}")
    private String ip;
    @Value("${deepexi.job.executor.port}")
    private int port;
    @Value("${deepexi.job.accessToken}")
    private String accessToken;
    @Value("${deepexi.job.executor.logpath}")
    private String logPath;
    @Value("${deepexi.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public JobExecutor jobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        JobExecutor jobExecutor = new JobExecutor();
        jobExecutor.setAdminAddresses(adminAddresses);
        jobExecutor.setAppName(appName);
        jobExecutor.setIp(ip);
        jobExecutor.setPort(port);
        jobExecutor.setAccessToken(accessToken);
        jobExecutor.setLogPath(logPath);
        jobExecutor.setLogRetentionDays(logRetentionDays);
        return jobExecutor;
    }
}