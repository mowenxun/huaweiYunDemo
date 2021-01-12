package com.deepexi.dd.system.mall.remote.customer;

import api.CustomerPickUpWarehouseApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface CustomerPickUpWarehouseClient extends CustomerPickUpWarehouseApi {
}
