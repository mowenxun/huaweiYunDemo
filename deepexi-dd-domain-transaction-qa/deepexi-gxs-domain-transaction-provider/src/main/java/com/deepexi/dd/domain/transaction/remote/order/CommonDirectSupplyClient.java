package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.domain.common.api.CommonDirectSupplyApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${deepexi.dd.domain.common.name}")
public interface CommonDirectSupplyClient extends CommonDirectSupplyApi {
}
