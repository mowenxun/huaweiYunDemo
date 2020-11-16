package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.OrderDeliveryInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-02 14:42
 */
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface OrderDeliveryInfoClient extends OrderDeliveryInfoApi{
}
