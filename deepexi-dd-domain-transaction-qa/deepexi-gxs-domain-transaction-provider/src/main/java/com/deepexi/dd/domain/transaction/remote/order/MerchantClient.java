package com.deepexi.dd.domain.transaction.remote.order;

import api.MerchantApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${deepexi.dd.domain.customer.name}")
public interface MerchantClient extends MerchantApi {
}
