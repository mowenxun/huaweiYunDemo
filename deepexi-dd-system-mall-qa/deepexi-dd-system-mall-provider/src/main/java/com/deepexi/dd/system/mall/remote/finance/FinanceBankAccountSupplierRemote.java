package com.deepexi.dd.system.mall.remote.finance;

import com.deepexi.dd.domain.finance.api.FinanceBankAccountSupplierOpenApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.finance.name}")
public interface FinanceBankAccountSupplierRemote extends FinanceBankAccountSupplierOpenApi {

}
