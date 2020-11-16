package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.domain.tool.api.ToolLinkApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by liaop on 2020/7/14.
 */
@FeignClient(value = "deepexi.dd.domain.tool")
public interface ToolLinkClient extends ToolLinkApi{
}
