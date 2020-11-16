package com.deepexi.dd.domain.transaction.config;

import com.deepexi.sdk.commodity.api.*;
import com.deepexi.sdk.core.ApiClient;
import com.deepexi.sdk.storage.api.StockChangeSdkApi;
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
@Configuration
@ConditionalOnProperty(prefix = "deepexi.sdk.shopping", value = "enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "deepexi.sdk.shopping")
public class CommodityClientConfig {

    private String basePath;
    private String appKey;
    private String appSecret;

    @Bean(name = "shopping")
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
    StockChangeSdkApi stockChangeSdkApi(){return new StockChangeSdkApi(apiClient());}

    @Bean
    SaleStockOpenApi getSaleStockOpenApi(){
        return new SaleStockOpenApi(apiClient());
    }

    @Bean
    ActivityStockOpenApi activityStockOpenApi(){
        return new ActivityStockOpenApi(apiClient());
    }


    @Bean
    SkuOpenApi getSkuOpenApi(){
        return new SkuOpenApi(apiClient());
    }

    @Bean
    EsCommodityApi getEsCommodityApi(){
        return new EsCommodityApi(apiClient());
    }

    @Bean
    BrandApi getBrandApi(){
        return new BrandApi(apiClient());
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