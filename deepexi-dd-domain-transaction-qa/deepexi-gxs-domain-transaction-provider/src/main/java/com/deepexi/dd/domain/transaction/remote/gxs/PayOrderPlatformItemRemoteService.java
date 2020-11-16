package com.deepexi.dd.domain.transaction.remote.gxs;

import com.deepexi.dd.middle.order.api.PayOrderPlatformItemApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@FeignClient("${deepexi.dd.middle.order.name}")
public interface PayOrderPlatformItemRemoteService extends PayOrderPlatformItemApi {

}
