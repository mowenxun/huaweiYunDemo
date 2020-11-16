package com.deepexi.dd.domain.transaction.remote.gxs;

import com.deepexi.dd.middle.order.api.PayOrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@FeignClient("${deepexi.dd.middle.order.name}")
public interface PayOrderRemoteService extends PayOrderApi {

}
