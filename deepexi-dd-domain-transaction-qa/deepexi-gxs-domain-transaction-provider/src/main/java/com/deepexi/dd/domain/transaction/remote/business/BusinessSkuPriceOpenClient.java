package com.deepexi.dd.domain.transaction.remote.business;

import com.deepexi.dd.domain.business.api.BusinessSkuPriceOpenApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "deepexi.dd.domain.business")
public interface BusinessSkuPriceOpenClient extends BusinessSkuPriceOpenApi {
}
