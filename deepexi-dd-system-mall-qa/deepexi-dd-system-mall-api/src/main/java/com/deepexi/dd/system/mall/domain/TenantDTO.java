package com.deepexi.dd.system.mall.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhoujiawen
 * @date 2020-04-23 15:20
 */
@Data
public class TenantDTO extends AbstractObject {

    @ApiModelProperty(value = "租户id",required = true)
    @NotNull(message = "租户id不能为空")
    private String tenantId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

}
