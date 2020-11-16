package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.SaleOrderInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-01 10:57
 */
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface SaleOrderInfoClient extends SaleOrderInfoApi{
}
