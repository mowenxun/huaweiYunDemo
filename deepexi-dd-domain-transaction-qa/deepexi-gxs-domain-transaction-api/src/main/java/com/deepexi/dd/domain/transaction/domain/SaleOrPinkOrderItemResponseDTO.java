package com.deepexi.dd.domain.transaction.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrPinkOrderItemResponseDTO")
public class SaleOrPinkOrderItemResponseDTO extends AbstractObject implements Serializable {

    @ApiModelProperty(value = "订单ID")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String code;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal money;
}
