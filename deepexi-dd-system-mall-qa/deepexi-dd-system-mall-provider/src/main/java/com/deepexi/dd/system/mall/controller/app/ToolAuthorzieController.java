package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.domain.tool.domain.query.ToolUserRequestQuery;
import com.deepexi.dd.middle.tool.domain.dto.ToolAuthorizeCommodityResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolAuthorizeCommodityInfoQuery;
import com.deepexi.dd.system.mall.service.app.ToolAuthorizeService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: zhaochongsen
 * @Datetime: 2020/9/4   17:08
 * @version: v1.0
 */
@RequestMapping("/admin-api/v1/app/toolAuthorzie")
@RestController
@Api(value = "状态管理", tags = "状态管理")
public class ToolAuthorzieController {

    @Autowired
    private ToolAuthorizeService toolAuthorizeService;

    @ApiOperation("返回列表")
    @GetMapping("/listAuthorizeCommodityInfo")
    public Payload<List<ToolAuthorizeCommodityResponseDTO>> listAuthorizeCommodityInfo(ToolAuthorizeCommodityInfoQuery query) throws Exception {
        return new Payload<>(toolAuthorizeService.listAuthorizeCommodityInfo(query));
    }

    @ApiOperation("根据用户id获取是否授权价格")
    @GetMapping("/getUserPriceAuthorize")
    public Payload<Boolean> getUserPriceAuthorize(@Valid ToolUserRequestQuery query) throws Exception {
        return new Payload<>(toolAuthorizeService.getUserPriceAuthorize(query));
    }

}
