package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.ShoppingCartApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yangwu
 * @date 2020/7/9
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface ShoppingCartRemote extends ShoppingCartApi {

}
