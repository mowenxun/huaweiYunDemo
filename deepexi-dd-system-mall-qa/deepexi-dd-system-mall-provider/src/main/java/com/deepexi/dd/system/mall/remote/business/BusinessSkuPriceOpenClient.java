package com.deepexi.dd.system.mall.remote.business;

import com.deepexi.dd.domain.business.api.BusinessSkuPriceOpenApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.business.name}")
public interface BusinessSkuPriceOpenClient extends BusinessSkuPriceOpenApi {
}
