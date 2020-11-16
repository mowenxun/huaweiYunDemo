package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.OrderStatusOperationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-13 19:37
 */
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface OrderStatusOperationClient extends OrderStatusOperationApi{
}
