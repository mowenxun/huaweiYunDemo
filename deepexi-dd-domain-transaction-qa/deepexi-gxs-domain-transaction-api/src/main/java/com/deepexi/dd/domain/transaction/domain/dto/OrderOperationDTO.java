package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by liaop on 2020/7/7.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "操作按钮")
public class OrderOperationDTO {

    @ApiModelProperty(value = "按钮名")
    private String name;

    @ApiModelProperty(value = "接口url")
    private String api;

    @ApiModelProperty(value = "中文名")
    private String chineseName;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "启用状态")
    private Boolean enable;
}
