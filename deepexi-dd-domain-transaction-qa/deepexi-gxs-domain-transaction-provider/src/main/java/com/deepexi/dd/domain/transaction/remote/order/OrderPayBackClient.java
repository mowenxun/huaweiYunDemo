package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.OrderPayBackApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface OrderPayBackClient extends OrderPayBackApi {
}
