package com.deepexi.dd.system.mall.remote.finance;

import com.deepexi.dd.domain.finance.api.FinanceAmountOpenApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/29 17:11
 */
@FeignClient(value = "${deepexi.dd.domain.finance.name}")
public interface FinanceAmountRemote extends FinanceAmountOpenApi {
}
