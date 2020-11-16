package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.order.api.SalePickGoodsConsigneeApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SalePickGoodsConsigneeClient
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-24
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface SalePickGoodsConsigneeClient extends SalePickGoodsConsigneeApi {
}
