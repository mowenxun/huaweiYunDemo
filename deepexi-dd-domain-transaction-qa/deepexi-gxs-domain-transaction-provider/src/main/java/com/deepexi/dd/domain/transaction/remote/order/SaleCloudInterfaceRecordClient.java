package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.SaleCloudInterfaceRecordApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface SaleCloudInterfaceRecordClient extends SaleCloudInterfaceRecordApi {
}
