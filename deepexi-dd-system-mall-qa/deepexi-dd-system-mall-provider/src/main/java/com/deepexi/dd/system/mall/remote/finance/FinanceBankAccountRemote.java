package com.deepexi.dd.system.mall.remote.finance;

import com.deepexi.dd.domain.finance.api.FinanceBankAccountOpenApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.finance.name}")
public interface FinanceBankAccountRemote extends FinanceBankAccountOpenApi {

}
