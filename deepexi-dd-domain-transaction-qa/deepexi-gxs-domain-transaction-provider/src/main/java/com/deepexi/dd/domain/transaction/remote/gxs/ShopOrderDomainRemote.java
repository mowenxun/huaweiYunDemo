package com.deepexi.dd.domain.transaction.remote.gxs;

import com.deepexi.dd.middle.order.api.ShopOrderApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${deepexi.dd.middle.order.name}")
public interface ShopOrderDomainRemote extends ShopOrderApi {
}
