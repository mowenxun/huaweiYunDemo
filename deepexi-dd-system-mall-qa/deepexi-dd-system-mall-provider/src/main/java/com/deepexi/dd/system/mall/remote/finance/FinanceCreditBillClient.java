package com.deepexi.dd.system.mall.remote.finance;

import com.deepexi.dd.domain.finance.api.FinanceCreditBillApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author easy
 * @date 2020/9/10 10:09
 **/
@FeignClient(value = "${deepexi.dd.domain.finance.name}")
public interface FinanceCreditBillClient extends FinanceCreditBillApi {
}
