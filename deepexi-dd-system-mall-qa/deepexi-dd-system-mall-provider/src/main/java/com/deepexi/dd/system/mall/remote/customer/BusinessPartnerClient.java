package com.deepexi.dd.system.mall.remote.customer;

import api.BusinessPartnerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName BusinessPartnerClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-14
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface BusinessPartnerClient extends BusinessPartnerApi {
}
