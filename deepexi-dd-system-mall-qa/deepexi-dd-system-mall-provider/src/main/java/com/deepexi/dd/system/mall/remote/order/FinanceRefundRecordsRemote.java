package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.finance.api.FinanceRefundRecordsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Title: FinanceRefundRecordsRemote
 * @Description: TODO
 * @Author: hezhijian
 * @Datetime: 2020/9/17 17:24
 * @version: v1.0
 */
@FeignClient(value = "${deepexi.dd.domain.finance.name}")
public interface FinanceRefundRecordsRemote extends FinanceRefundRecordsApi {
}
