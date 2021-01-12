package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.middle.tool.domain.dto.ToolCategoryMainResponseDTO;
import com.deepexi.dd.middle.tool.domain.dto.ToolCategoryResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolCategoryRequestQuery;
import com.deepexi.dd.middle.tool.domain.query.ToolCategoryTreeQuery;
import com.deepexi.util.config.Payload;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-09-10 13:45
 */
public interface ToolCategoryService {

    /**
     * 获取产品线
     * @param query
     * @return
     * @throws Exception
     */
    Payload<List<ToolCategoryMainResponseDTO>> listToolCategory(ToolCategoryRequestQuery query) throws Exception;
}
