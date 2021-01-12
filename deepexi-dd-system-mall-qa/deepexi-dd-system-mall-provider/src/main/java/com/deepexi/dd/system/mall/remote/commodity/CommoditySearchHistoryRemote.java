package com.deepexi.dd.system.mall.remote.commodity;

import com.deepexi.dd.domain.business.api.CommoditySearchHistoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.business.name}")
public interface CommoditySearchHistoryRemote extends CommoditySearchHistoryApi {

}
