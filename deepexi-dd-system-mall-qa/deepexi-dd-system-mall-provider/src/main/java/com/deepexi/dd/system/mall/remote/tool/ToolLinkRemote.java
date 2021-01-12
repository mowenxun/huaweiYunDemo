package com.deepexi.dd.system.mall.remote.tool;

import com.deepexi.dd.domain.tool.api.ToolLinkApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yy
 * @version 1.0
 * @date 2020-08-29 17:42
 */
@FeignClient(value = "${deepexi.dd.domain.tool.name}")
public interface ToolLinkRemote extends ToolLinkApi {
}
