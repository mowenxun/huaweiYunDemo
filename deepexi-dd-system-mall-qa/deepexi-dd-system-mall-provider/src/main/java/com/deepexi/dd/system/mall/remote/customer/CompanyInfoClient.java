package com.deepexi.dd.system.mall.remote.customer;

import api.CompanyInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description TODO
 * @Author hujinhua
 * @Date 2020/10/14 10:27
 * 
 */
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface CompanyInfoClient extends CompanyInfoApi {
}
