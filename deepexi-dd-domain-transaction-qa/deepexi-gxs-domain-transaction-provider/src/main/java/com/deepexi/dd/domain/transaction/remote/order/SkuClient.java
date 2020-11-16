package com.deepexi.dd.domain.transaction.remote.order;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${deepexi.dd.domain.common.name}")
public interface SkuClient {
}
