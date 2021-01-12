package com.deepexi.dd.system.mall.remote.finance;

import com.deepexi.dd.domain.finance.api.FinanceAccountApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author easy
 * @date 2020/8/28 16:25
 **/
@FeignClient(value = "${deepexi.dd.domain.finance.name}")
public interface FinanceAccountClient extends FinanceAccountApi {
}
