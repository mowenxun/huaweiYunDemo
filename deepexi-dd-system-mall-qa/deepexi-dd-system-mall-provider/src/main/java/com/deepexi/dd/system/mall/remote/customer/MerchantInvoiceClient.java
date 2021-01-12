package com.deepexi.dd.system.mall.remote.customer;

import api.MerchantInvoiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName MerchantInvoiceClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface MerchantInvoiceClient extends MerchantInvoiceApi {
}
