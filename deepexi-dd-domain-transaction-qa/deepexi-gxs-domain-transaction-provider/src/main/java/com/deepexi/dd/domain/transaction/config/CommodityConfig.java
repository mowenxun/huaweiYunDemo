package com.deepexi.dd.domain.transaction.config;

import com.deepexi.sdk.commodity.api.*;
import com.deepexi.sdk.core.ApiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 商品域api
 *
 * @author yangwu
 * @date 2020/7/8
 */
//@Configuration
//@ConditionalOnProperty(prefix = "deepexi.sdk", value = "enabled" , havingValue = "true", matchIfMissing = false)
//@ConfigurationProperties(prefix = "deepexi.sdk")

public class CommodityConfig {

    private String basePath;
    private String appKey;
    private String appSecret;

    @Bean
    @Scope("prototype")
    ApiClient apiClient(){
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        apiClient.setAppKey(appKey);
        apiClient.setAppSecret(appSecret);
        return apiClient;
    }

    @Bean
    ShelvesItemSkuOpenApi shelvesItemSkuOpenApi(){
        return new ShelvesItemSkuOpenApi(apiClient());
    }
    @Bean
    ShopItemOpenApi shopItemOpenApi(){
        return new ShopItemOpenApi(apiClient());
    }

    @Bean
    SaleStockOpenApi getSaleStockOpenApi(){
        return new SaleStockOpenApi(apiClient());
    }

    @Bean
    SkuOpenApi getSkuOpenApi(){
        return new SkuOpenApi(apiClient());
    }

    @Bean
    EsCommodityApi getEsCommodityApi(){
        return new EsCommodityApi(apiClient());
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