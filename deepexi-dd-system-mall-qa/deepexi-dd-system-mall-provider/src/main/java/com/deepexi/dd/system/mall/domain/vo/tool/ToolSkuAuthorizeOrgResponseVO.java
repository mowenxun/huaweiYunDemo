package com.deepexi.dd.system.mall.domain.vo.tool;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ToolSkuAuthorizeOrgResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-09-17
 * @Version 1.0
 **/
@Data
@ApiModel
public class ToolSkuAuthorizeOrgResponseVO extends AbstractObject implements Serializable {

    @ApiModelProperty(value = "skuId")
    private Long skuId;

    @ApiModelProperty(value = "顶级组织id")
    private Long orgId;

    @ApiModelProperty(value = "顶级组织名称")
    private String orgName;

    @ApiModelProperty(value = "一级组织id")
    private Long firstOrgId;

    @ApiModelProperty(value = "一级组织名称")
    private String firstOrgName;
}
