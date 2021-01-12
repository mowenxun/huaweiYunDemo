package com.deepexi.dd.system.mall.remote.tool;

import com.deepexi.dd.domain.tool.api.ToolCategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-09-10 13:40
 */
@FeignClient(value = "${deepexi.dd.domain.tool.name}")
public interface ToolCategoryRemote extends ToolCategoryApi {
}
