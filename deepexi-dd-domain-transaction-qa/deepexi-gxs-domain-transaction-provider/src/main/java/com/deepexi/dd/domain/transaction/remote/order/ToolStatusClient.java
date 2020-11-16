package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.domain.tool.api.ToolStatusApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "deepexi.dd.domain.tool")
public interface ToolStatusClient extends ToolStatusApi {
}
