package com.deepexi.dd.domain.transaction.remote.order;


import api.CustomerPickUpWarehouseApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface CustomerPickUpWarehouseClient extends CustomerPickUpWarehouseApi {

}
