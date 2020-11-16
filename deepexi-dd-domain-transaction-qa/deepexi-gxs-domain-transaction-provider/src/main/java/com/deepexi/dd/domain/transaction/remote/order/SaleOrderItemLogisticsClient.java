package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.SaleOrderItemLogisticsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SaleOrderItemLogisticsClient
 * @Description oms物流信息
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface SaleOrderItemLogisticsClient extends SaleOrderItemLogisticsApi {
}
