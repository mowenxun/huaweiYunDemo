package com.deepexi.dd.domain.transaction.remote.gxs;

import api.CompanyInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description TODO
 * @Author hujinhua
 * @Date 2020/10/14 10:27
 * 
 */
@FeignClient(value = "${deepexi.dd.domain.customer.name}")
public interface CompanyInfoDomainClient extends CompanyInfoApi {
}
