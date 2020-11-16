package com.deepexi.dd.domain.transaction.config;

import com.deepexi.sdk.core.ApiClient;
import com.deepexi.sdk.storage.api.DepotSdkApi;
import com.deepexi.sdk.storage.api.StockSdkApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @ClassName : DepotCOnfig
 * @Description : 仓储域配置
 * @Author : yuanzaishun
 * @Date: 2020-07-22 16:50
 */
@Configuration
@ConditionalOnProperty(prefix = "deepexi.sdk.supply", value = "enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "deepexi.sdk.supply")
public class DepotConfig {
    private String basePath;
    private String appKey;
    private String appSecret;

    @Bean("depot")
    @Scope("prototype")
    ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        apiClient.setAppKey(appKey);
        apiClient.setAppSecret(appSecret);
        return apiClient;
    }
    @Bean
    public DepotSdkApi getDepotSdkApi(){
        return new DepotSdkApi(apiClient());
    }

    @Bean
    public StockSdkApi getStockSdkApi(){return new StockSdkApi(apiClient());}

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
