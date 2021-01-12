package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.SaleOrderInfoApi;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-14 17:43
 */
@FeignClient(value = "${eureka.client.transaction.name}")
//@FeignClient(value = "${eureka.client.transaction.name}",url = "http://localhost:8502")
public interface SaleOrderInfoRemote extends SaleOrderInfoApi{
}
