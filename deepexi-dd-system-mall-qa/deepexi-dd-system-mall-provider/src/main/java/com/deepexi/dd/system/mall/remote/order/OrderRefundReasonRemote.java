package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.OrderRefundReasonApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hezhijian
 * @version 1.0
 * @date 2020-08-20 11:28
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface OrderRefundReasonRemote extends OrderRefundReasonApi {
}
