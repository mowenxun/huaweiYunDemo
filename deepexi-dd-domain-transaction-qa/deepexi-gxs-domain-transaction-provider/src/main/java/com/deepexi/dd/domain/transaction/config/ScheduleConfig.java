package com.deepexi.dd.domain.transaction.config;

import com.deepexi.sdk.core.ApiClient;
import com.deepexi.sdk.schedule.api.ScheduleDomainSdkApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @ClassName ScheduleConfig
 * @Description 调度域
 * @Author SongTao
 * @Date 2020-07-16
 * @Version 1.0
 **/
@Configuration
@ConditionalOnProperty(prefix = "deepexi.sdk.supply", value = "enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "deepexi.sdk.supply")
public class ScheduleConfig {
    private String basePath;
    private String appKey;
    private String appSecret;

    @Bean(name = "schedule")
    @Scope("prototype")
    ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        apiClient.setAppKey(appKey);
        apiClient.setAppSecret(appSecret);
        return apiClient;
    }

    @Bean
    ScheduleDomainSdkApi scheduleDomainSdkApi(){
        return new ScheduleDomainSdkApi(apiClient());
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
