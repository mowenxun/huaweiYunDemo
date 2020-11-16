package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.SaleOrderPayNotifyApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${deepexi.dd.middle.order.name}")
public interface SaleOrderPayNotifyClient extends SaleOrderPayNotifyApi {
}
