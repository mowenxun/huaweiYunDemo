package com.deepexi.dd.system.mall.remote.commodity;

import com.deepexi.dd.domain.common.api.CommonDirectSupplyApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.common.name}")
public interface CommonDirectSupplyRemote extends CommonDirectSupplyApi {

}
