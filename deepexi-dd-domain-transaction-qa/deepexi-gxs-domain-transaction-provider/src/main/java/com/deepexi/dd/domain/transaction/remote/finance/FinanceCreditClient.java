package com.deepexi.dd.domain.transaction.remote.finance;

import com.deepexi.dd.middle.finance.api.FinanceCreditApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-20 11:25
 */
@FeignClient(value = "${deepexi.dd.middle.finance.name}")
public interface FinanceCreditClient extends FinanceCreditApi {
}
