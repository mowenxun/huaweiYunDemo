package com.deepexi.dd.system.mall.remote.customer;

import api.MerchantDocumentApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName MerchantDocumentClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface MerchantDocumentClient extends MerchantDocumentApi {
}
