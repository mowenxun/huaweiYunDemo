package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.middle.tool.domain.dto.ToolCategoryMainResponseDTO;
import com.deepexi.dd.middle.tool.domain.dto.ToolCategoryResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolCategoryRequestQuery;
import com.deepexi.dd.middle.tool.domain.query.ToolCategoryTreeQuery;
import com.deepexi.dd.system.mall.service.app.ToolCategoryService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-09-10 13:42
 */
@Api("产品线")
@RequestMapping("/admin-api/v1/domain/toolCategory")
@RestController
@Slf4j
public class ToolCategoryController {

    @Autowired
    private ToolCategoryService toolCategoryService;

    @GetMapping("/topList")
    public Payload<List<ToolCategoryMainResponseDTO>> listToolCategory(ToolCategoryRequestQuery query) throws Exception{
        return toolCategoryService.listToolCategory(query);
    }
}
