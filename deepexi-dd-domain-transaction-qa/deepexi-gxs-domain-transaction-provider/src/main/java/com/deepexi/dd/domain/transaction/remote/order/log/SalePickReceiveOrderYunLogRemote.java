package com.deepexi.dd.domain.transaction.remote.order.log;

import com.deepexi.dd.middle.order.api.SalePickReceiveOrderYunLogApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-09-23 14:21
 */
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface SalePickReceiveOrderYunLogRemote extends SalePickReceiveOrderYunLogApi {
}
