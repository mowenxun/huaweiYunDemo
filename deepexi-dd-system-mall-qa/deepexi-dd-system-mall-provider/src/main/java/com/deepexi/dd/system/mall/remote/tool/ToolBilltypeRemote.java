package com.deepexi.dd.system.mall.remote.tool;

import com.deepexi.dd.middle.tool.api.ToolBilltypeApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yy
 * @version 1.0
 * @date 2020-09-01 23:33
 */
@FeignClient(value = "${deepexi.dd.domain.tool.name}")
public interface ToolBilltypeRemote extends ToolBilltypeApi {
}
