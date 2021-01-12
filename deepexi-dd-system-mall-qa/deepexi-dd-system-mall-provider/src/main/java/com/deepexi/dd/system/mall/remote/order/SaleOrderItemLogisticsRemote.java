package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.SaleOrderItemLogisticsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SaleOrderItemLogisticsRemote
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@FeignClient(value = "${eureka.client.transaction.name}")
public interface SaleOrderItemLogisticsRemote extends SaleOrderItemLogisticsApi {
}
