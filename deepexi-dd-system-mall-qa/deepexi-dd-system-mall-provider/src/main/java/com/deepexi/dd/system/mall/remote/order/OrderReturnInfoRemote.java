package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.OrderReturnInfoApi;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-14 17:49
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface OrderReturnInfoRemote extends OrderReturnInfoApi{
}
