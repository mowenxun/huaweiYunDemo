package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.gxs.SupplierFinancialDomainApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@FeignClient("${eureka.client.transaction.name:deepexi.dd.domain.transaction}")
public interface SupplierFinancialRemoteService extends SupplierFinancialDomainApi {

}
