package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.domain.tool.domain.dto.ToolStatusAllResponseDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusAllRequestQuery;
import com.deepexi.dd.middle.tool.domain.dto.ToolStatusResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolStatusRequestQuery;
import com.deepexi.dd.system.mall.service.app.ToolStatusService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: mumu
 * @Datetime: 2020/8/29   17:08
 * @version: v1.0
 */
@RequestMapping("/admin-api/v1/app/toolStatus")
@RestController
@Api(value = "状态管理", tags = "状态管理")
public class ToolStatusController {

    @Autowired
    private ToolStatusService toolStatusService;

    @ApiOperation("查询状态列表")
    @GetMapping("/list")
    public Payload<List<ToolStatusAllResponseDTO>> listToolStatus(ToolStatusAllRequestQuery query) throws Exception {
        return new Payload<>(toolStatusService.listAll(query));
    }

}
