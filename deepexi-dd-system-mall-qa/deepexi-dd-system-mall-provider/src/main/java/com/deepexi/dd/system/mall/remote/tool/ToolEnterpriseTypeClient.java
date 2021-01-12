package com.deepexi.dd.system.mall.remote.tool;

import com.deepexi.dd.domain.tool.api.ToolEnterpriseTypeApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${deepexi.dd.domain.tool.name}")
public interface ToolEnterpriseTypeClient extends ToolEnterpriseTypeApi {
}
