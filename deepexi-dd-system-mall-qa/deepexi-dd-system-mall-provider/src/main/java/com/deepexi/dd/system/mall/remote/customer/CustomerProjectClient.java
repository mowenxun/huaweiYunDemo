package com.deepexi.dd.system.mall.remote.customer;

import api.CustomerProjectApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/29 20:06
 */
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface CustomerProjectClient extends CustomerProjectApi {
}
