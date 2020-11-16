package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.ActivitySkuOrderQuantityApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 19:57
 */
@FeignClient(name = "${deepexi.dd.middle.order.name}")
public interface ActivitySkuOrderQuantityClient extends ActivitySkuOrderQuantityApi {

}