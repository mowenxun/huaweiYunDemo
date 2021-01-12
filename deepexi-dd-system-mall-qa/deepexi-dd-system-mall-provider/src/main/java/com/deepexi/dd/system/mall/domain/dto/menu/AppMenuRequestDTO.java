package com.deepexi.dd.system.mall.domain.dto.menu;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zhaochongsen
 * @version 1.0
 * @date 2020-09-11 15:33
 */
@Data
@ApiModel
public class AppMenuRequestDTO extends AbstractObject {

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    private String tenantId;

    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id",required = true)
    @NotNull(message = "app不能为空")
    private Long appId;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String appCode;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",required = true)
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

}
