package com.deepexi.dd.system.mall.config;

import com.deepexi.clientiam.api.UserControllerApi;
import com.deepexi.sdk.commodity.api.*;
import com.deepexi.sdk.core.ApiClient;
import com.deepexi.sdk.storage.api.DepotSdkApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ConditionalOnProperty(prefix = "deepexi.sdk", value = "enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "deepexi.sdk")
public class SdkConfig {
    private String basePath;
    private String appKey;
    private String appSecret;
    private String commodityPath;

    @Bean
    @Scope("prototype")
    ApiClient commodityApiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(commodityPath);
        apiClient.setAppKey(appKey);
        apiClient.setAppSecret(appSecret);
        return apiClient;
    }

    @Bean
    BrandApi brandApi() {
        return new BrandApi(commodityApiClient());
    }

    @Bean
    CategoryFrontOpenApi getFrontCategoryTree() {
        ApiClient commodityApiClient = new ApiClient();
        commodityApiClient.setBasePath(commodityPath);
        commodityApiClient.setAppKey(appKey);
        commodityApiClient.setAppSecret(appSecret);
        return new CategoryFrontOpenApi(commodityApiClient);
    }

    @Bean
    ShelvesItemSkuOpenApi getShelvesItemSkuDetail() {
        ApiClient commodityApiClient = new ApiClient();
        commodityApiClient.setBasePath(commodityPath);
        commodityApiClient.setAppKey(appKey);
        commodityApiClient.setAppSecret(appSecret);
        return new ShelvesItemSkuOpenApi(commodityApiClient);
    }

    @Bean
    ActivityStockOpenApi getActivityStockOpenApi() {
        ApiClient commodityApiClient = new ApiClient();
        commodityApiClient.setBasePath(commodityPath);
        commodityApiClient.setAppKey(appKey);
        commodityApiClient.setAppSecret(appSecret);
        return new ActivityStockOpenApi(commodityApiClient);
    }

    @Bean
    SkuOpenApi getSkuOpenApi() {
        ApiClient commodityApiClient = new ApiClient();
        commodityApiClient.setBasePath(commodityPath);
        commodityApiClient.setAppKey(appKey);
        commodityApiClient.setAppSecret(appSecret);
        return new SkuOpenApi(commodityApiClient);
    }

    @Bean
    SaleStockOpenApi saleStockOpenApi() {
        ApiClient commodityApiClient = new ApiClient();
        commodityApiClient.setBasePath(commodityPath);
        commodityApiClient.setAppKey(appKey);
        commodityApiClient.setAppSecret(appSecret);
        return new SaleStockOpenApi(commodityApiClient);
    }

    @Bean
    CategoryBackOpenApi categoryBackOpenApi() {
        ApiClient categoryBackApiClient = new ApiClient();
        categoryBackApiClient.setBasePath(commodityPath);
        categoryBackApiClient.setAppKey(appKey);
        categoryBackApiClient.setAppSecret(appSecret);
        return new CategoryBackOpenApi(categoryBackApiClient);
    }

    @Bean
    DepotSdkApi getDepotSdkApi() {
        ApiClient depotSdkApiClient = new ApiClient();
        depotSdkApiClient.setBasePath(commodityPath);
        depotSdkApiClient.setAppKey(appKey);
        depotSdkApiClient.setAppSecret(appSecret);
        return new DepotSdkApi(depotSdkApiClient);
    }

    @Bean
    UserControllerApi userControllerApi() {
        return new UserControllerApi(commodityApiClient());
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

    public String getCommodityPath() {
        return commodityPath;
    }

    public void setCommodityPath(String commodityPath) {
        this.commodityPath = commodityPath;
    }
}
