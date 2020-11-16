package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.SalePickGoodsOrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-15 14:20
 */
@FeignClient(value = "${deepexi.dd.middle.order.name}")
//@FeignClient(value = "${deepexi.dd.middle.order.name}",url = "http://localhost:8501")
public interface SalePickGoodsOrderClient extends SalePickGoodsOrderApi {
}
