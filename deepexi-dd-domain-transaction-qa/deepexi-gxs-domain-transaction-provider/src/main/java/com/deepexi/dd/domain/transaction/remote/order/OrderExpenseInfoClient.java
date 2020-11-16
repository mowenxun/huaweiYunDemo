package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.OrderExpenseInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-02 14:43
 */
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface OrderExpenseInfoClient extends OrderExpenseInfoApi{
}
