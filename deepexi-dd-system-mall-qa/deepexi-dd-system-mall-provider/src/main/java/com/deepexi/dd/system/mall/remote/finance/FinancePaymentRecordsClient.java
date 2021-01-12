package com.deepexi.dd.system.mall.remote.finance;

import com.deepexi.dd.domain.finance.api.FinancePaymentRecordsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName FinancePaymentRecordsClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-08
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.finance.name}")
public interface FinancePaymentRecordsClient extends FinancePaymentRecordsApi {
}
