package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.SaleOrderItemApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SaleOrderItemRemote
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
@FeignClient(value = "${eureka.client.transaction.name}")
public interface SaleOrderItemRemote extends SaleOrderItemApi {
}
