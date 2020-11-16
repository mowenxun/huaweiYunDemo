package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.OrderRefundReasonApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${deepexi.dd.middle.order.name}")
public interface OrderRefundReasonClient extends OrderRefundReasonApi {
}
