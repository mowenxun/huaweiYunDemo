package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.PayOrderPlatformApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author JeremyLian
 * @date 2020/10/24 22:23
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface PayOrderPlatformRemote extends PayOrderPlatformApi {
}
