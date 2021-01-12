package com.deepexi.dd.system.mall.remote.iam;

import com.deepexi.clientiam.api.*;
import com.deepexi.sdk.core.ApiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ConditionalOnProperty(prefix = "deepexi.iam.sdk", value = "enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "deepexi.iam.sdk")
public class IAMClient {

    private String basePath;
    private String appKey;
    private String appSecret;

    @Bean
    @Scope("prototype")
    ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        apiClient.setAppKey(appKey);
        apiClient.setAppSecret(appSecret);
        return apiClient;
    }

    @Bean
    UserControllerApi userControllerApi(){
        return new UserControllerApi(apiClient());
    }

    @Bean
    ApplicationControllerApi applicationControllerApi(){
        return new ApplicationControllerApi(apiClient());
    }

    @Bean
    MenuGroupControllerApi menuGroupControllerApi(){
        return new MenuGroupControllerApi(apiClient());
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
