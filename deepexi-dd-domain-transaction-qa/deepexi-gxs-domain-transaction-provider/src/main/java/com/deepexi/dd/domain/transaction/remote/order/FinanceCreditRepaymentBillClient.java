package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.finance.api.FinanceCreditRepaymentBillApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.middle.finance.name}")
public interface FinanceCreditRepaymentBillClient extends FinanceCreditRepaymentBillApi {
}
