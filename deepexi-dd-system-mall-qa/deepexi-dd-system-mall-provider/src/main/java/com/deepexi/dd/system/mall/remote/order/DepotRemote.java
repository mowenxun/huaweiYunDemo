package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.DepotApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${eureka.client.transaction.name}")
public interface DepotRemote extends DepotApi {
}
