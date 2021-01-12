package com.deepexi.dd.system.mall.remote.customer;

import api.StoreApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName StoreClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-24
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface StoreClient extends StoreApi {
}
