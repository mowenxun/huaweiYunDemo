package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.domain.tool.api.ToolAuthorizeApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "deepexi.dd.domain.tool")
public interface ToolAuthorizeClient extends ToolAuthorizeApi {
}
