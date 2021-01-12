package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeResponseDTO;
import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeTabResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolBilltypeRequestQuery;
import com.deepexi.dd.system.mall.service.app.ToolBilltypeService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 * @date 2020-09-01 23:36
 */
@Api(value = "单据类型", tags = "单据类型")
@RequestMapping("/admin-api/v1/domain/toolBilltype")
@RestController
@Slf4j
public class ToolBilltypeController {

    @Autowired
    private ToolBilltypeService toolBilltypeService;

    @ApiOperation("获取单据类型列表")
    @GetMapping("/list")
    public Payload<List<ToolBilltypeResponseDTO>> listToolBilltype(ToolBilltypeRequestQuery query) throws Exception {
        return new Payload<>(toolBilltypeService.listToolBilltype(query));
    }

    @ApiOperation("单据类型id获取页签列表")
    @GetMapping("/getTabList/{id}")
    public Payload<List<ToolBilltypeTabResponseDTO>> getTabList(@PathVariable Long id) throws Exception{
        return new Payload<>(toolBilltypeService.getTabList(id));
    }

}
