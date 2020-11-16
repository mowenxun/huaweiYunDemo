package com.deepexi.dd.domain.transaction.remote.order;

import api.StoreApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName StoreClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-27
 * @Version 1.0
 **/
@FeignClient(name = "${deepexi.dd.domain.customer.name}")
public interface StoreClient extends StoreApi {
}
