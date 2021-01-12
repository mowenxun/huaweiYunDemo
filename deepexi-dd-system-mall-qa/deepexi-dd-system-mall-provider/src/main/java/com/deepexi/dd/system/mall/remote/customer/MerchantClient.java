package com.deepexi.dd.system.mall.remote.customer;

import api.MerchantApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface MerchantClient extends MerchantApi {
}
