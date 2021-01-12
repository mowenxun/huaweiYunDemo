package com.deepexi.dd.system.mall.service.app.impl;

import com.deepexi.dd.middle.tool.domain.dto.ToolCategoryMainResponseDTO;
import com.deepexi.dd.middle.tool.domain.dto.ToolCategoryResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolCategoryRequestQuery;
import com.deepexi.dd.middle.tool.domain.query.ToolCategoryTreeQuery;
import com.deepexi.dd.system.mall.remote.tool.ToolCategoryRemote;
import com.deepexi.dd.system.mall.service.app.ToolCategoryService;
import com.deepexi.util.config.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-09-10 13:45
 */
@Service
@Slf4j
public class ToolCategoryServiceImpl implements ToolCategoryService {

    @Autowired
    private ToolCategoryRemote toolCategoryRemote;

    /**
     * 获取产品线
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public Payload<List<ToolCategoryMainResponseDTO>> listToolCategory(ToolCategoryRequestQuery query) throws Exception {
        return toolCategoryRemote.listTopToolCategory(query);
    }
}
