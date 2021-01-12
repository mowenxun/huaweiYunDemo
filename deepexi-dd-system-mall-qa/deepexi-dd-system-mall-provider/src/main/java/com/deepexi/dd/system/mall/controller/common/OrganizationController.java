package com.deepexi.dd.system.mall.controller.common;

import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.system.mall.domain.query.common.OrganizationRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.tool.ToolSkuAuthorizeOrgResponseVO;
import com.deepexi.dd.system.mall.service.common.OranizationService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName OranizationController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-09-10
 * @Version 1.0
 **/
@Api(tags = "组织管理")
@RestController
@RequestMapping("/admin-api/v1/common/organization")
public class OrganizationController {

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private OranizationService oranizationService;

    @GetMapping("/getCurrentOrganization")
    @ApiOperation("获取当前用户的组织")
    public Payload<GroupResultVO> getCurrentOrganization(@Valid OrganizationRequestQuery requestQuery) throws Exception {
        return new Payload<>(appRuntimeEnv.getUserOrganization());
    }

    @GetMapping("/getTopOrganization")
    @ApiOperation("获取顶级组织")
    public Payload<GroupResultVO> getTopOrganization(@Valid OrganizationRequestQuery requestQuery) throws Exception {
        return new Payload<>(appRuntimeEnv.getTopOrganization());
    }

    @ApiOperation(value = "通过sku查询上游组织信息[一级组织跟顶级组织]", nickname = "findUpstreamSuppliersBySku")
    @PostMapping("/findUpstreamSuppliersBySku")
    public Payload<List<ToolSkuAuthorizeOrgResponseVO>> findUpstreamSuppliersBySku(@RequestBody OrganizationRequestQuery requestQuery) throws Exception {
        List<ToolSkuAuthorizeOrgResponseVO> result = oranizationService.findUpstreamSuppliersBySku(requestQuery);
        return new Payload<>(result);
    }
}
