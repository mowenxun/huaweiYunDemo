package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.domain.tool.api.ToolCommoditytypeAuthorizeApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "deepexi.dd.domain.tool")
public interface ToolCommoditytypeAuthorizeClient extends ToolCommoditytypeAuthorizeApi
{
}
