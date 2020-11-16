package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderActionResponseDTO implements Serializable {
    @ApiModelProperty("操作按钮编码")
    private String actionCode;
    @ApiModelProperty("操作按钮名称")
    private String actionName;

}
