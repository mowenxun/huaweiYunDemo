package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.finance.api.FinanceBankAccountApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName FinanceBankAccountInfoClient
 * @Description 客户域银行账户表接口
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
@FeignClient(name = "${deepexi.dd.middle.finance.name}")
public interface FinanceBankAccountInfoClient extends FinanceBankAccountApi {
}
