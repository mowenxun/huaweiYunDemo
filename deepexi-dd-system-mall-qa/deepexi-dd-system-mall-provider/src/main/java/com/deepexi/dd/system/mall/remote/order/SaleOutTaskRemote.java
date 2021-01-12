package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.SaleOutTaskApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SaleOutTaskRemote
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-18
 * @Version 1.0
 **/
@FeignClient(value = "${eureka.client.transaction.name}")
public interface SaleOutTaskRemote extends SaleOutTaskApi {
}
