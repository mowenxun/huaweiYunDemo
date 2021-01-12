package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.SalePickGoodsInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wujinhua
 * @version 1.0
 * @date 2020-07-14 17:43
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface SalePickGoodsInfoRemote extends SalePickGoodsInfoApi {
}
