package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.OrderOperationRecordApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName OrderOperationRecordClient
 * @Description 操作记录
 * @Author SongTao
 * @Date 2020-07-29
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface OrderOperationRecordClient extends OrderOperationRecordApi {
}
