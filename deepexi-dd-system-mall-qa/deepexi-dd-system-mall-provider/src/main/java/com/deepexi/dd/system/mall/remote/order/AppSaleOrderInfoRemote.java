package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.AppSaleOrderInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: mumu
 * @Datetime: 2020/9/1   19:57
 * @version: v1.0
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface AppSaleOrderInfoRemote extends AppSaleOrderInfoApi {
}
