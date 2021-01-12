package com.deepexi.dd.system.mall.domain.query.common;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName OrganizationRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-09-10
 * @Version 1.0
 **/
@Data
@ApiModel
public class OrganizationRequestQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = -6462940663350166742L;

    @ApiModelProperty(value = "租户id", required = true)
    @NotNull(message = "租户id不能为空.")
    private String tenantId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "上游顶级组织id")
    private Long orgId;

    @ApiModelProperty(value = "sku列表不能为空")
    private List<Long> skuIds;
}
