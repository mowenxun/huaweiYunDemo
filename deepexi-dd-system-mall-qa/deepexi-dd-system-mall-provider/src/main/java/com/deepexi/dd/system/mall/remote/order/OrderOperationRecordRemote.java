package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.OrderOperationRecordApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-09-09 14:36
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface OrderOperationRecordRemote extends OrderOperationRecordApi {
}
