package com.deepexi.dd.system.mall.remote.stock;

import com.deepexi.dd.domain.transaction.api.SaleStockApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${eureka.client.transaction.name}")
public interface SaleStockClient extends SaleStockApi {
}
