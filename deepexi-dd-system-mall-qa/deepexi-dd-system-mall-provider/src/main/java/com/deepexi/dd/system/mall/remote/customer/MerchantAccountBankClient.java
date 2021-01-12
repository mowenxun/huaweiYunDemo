package com.deepexi.dd.system.mall.remote.customer;

import api.MerchantAccountBankApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName MerchantAccountBankClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-16
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface MerchantAccountBankClient extends MerchantAccountBankApi {
}
