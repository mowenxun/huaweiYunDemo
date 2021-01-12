package com.deepexi.dd.system.mall.remote;

import  com.deepexi.dd.domain.transaction.api.FinanceCreditApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-21 21:21
 */
@FeignClient(value = "${eureka.client.transaction.name}")
//@FeignClient(value = "${eureka.client.transaction.name}",url = "http://localhost:8502")
public interface FinanceCreditRemote extends FinanceCreditApi {
}
