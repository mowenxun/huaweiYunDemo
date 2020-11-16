package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.SaleDeliveryGoodsPlanRulesApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-17 18:05
 */
@FeignClient(value = "${deepexi.dd.middle.order.name}")
//@FeignClient(value = "${deepexi.dd.middle.order.name}",url = "http://localhost:8501")
public interface SaleDeliveryGoodsPlanRulesClient extends SaleDeliveryGoodsPlanRulesApi {
}
