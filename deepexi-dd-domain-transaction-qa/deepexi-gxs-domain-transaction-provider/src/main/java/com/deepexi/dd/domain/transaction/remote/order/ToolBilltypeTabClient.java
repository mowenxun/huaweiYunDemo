package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.middle.tool.api.ToolBilltypeTabApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 10:38
 */
@FeignClient(name = "deepexi.dd.domain.tool")
public interface ToolBilltypeTabClient extends ToolBilltypeTabApi {
}
