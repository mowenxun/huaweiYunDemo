package com.deepexi.dd.system.mall.remote.tool;

import com.deepexi.dd.domain.tool.api.ToolAuthorizeApi;
import com.deepexi.dd.domain.tool.api.ToolStatusApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: zhaochongsen
 * @Datetime: 2020/8/29   17:05
 * @version: v1.0
 */
@FeignClient(value = "${deepexi.dd.domain.tool.name}")
public interface ToolAuthorizeClient extends ToolAuthorizeApi {
}
