package com.deepexi.dd.system.mall.remote.commodity;

import com.deepexi.dd.domain.tool.api.ToolCommoditytypeAuthorizeApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.tool.name}")
public interface ToolCommoditytypeAuthorizeApiRemote extends ToolCommoditytypeAuthorizeApi {
}
