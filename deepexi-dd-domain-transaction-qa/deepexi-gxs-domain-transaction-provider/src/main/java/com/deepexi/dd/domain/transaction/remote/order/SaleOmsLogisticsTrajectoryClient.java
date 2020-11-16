package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.SaleOmsLogisticsTrajectoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SaleOmsLogisticsTrajectoryClient
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface SaleOmsLogisticsTrajectoryClient extends SaleOmsLogisticsTrajectoryApi {
}
