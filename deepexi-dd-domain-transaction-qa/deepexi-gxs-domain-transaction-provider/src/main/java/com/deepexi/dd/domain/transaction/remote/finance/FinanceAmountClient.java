package com.deepexi.dd.domain.transaction.remote.finance;

import com.deepexi.dd.middle.finance.api.FinanceAmountApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-20 15:28
 */
@FeignClient(value = "${deepexi.dd.middle.finance.name}")
//@FeignClient(value = "${deepexi.dd.middle.finance.name}",url = "http://localhost:8401")
public interface FinanceAmountClient extends FinanceAmountApi {
}
