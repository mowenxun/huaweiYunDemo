package com.deepexi.dd.system.mall.remote.customer;

import api.MerchantAddressApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName MerchantAddressClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface MerchantAddressClient extends MerchantAddressApi {
}
