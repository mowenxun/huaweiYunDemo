package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *根据Id和Code查找销售订单或提货计划订单的dto
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "根据Id和Code查找销售订单或提货计划订单")
public class SaleOrPinkOrderRequestDTO extends AbstractObject implements Serializable {

    @ApiModelProperty(value = "销售订单的id或销售订单的父订单Id或提货订单Id")
    private Long id;

    @ApiModelProperty(value = "销售订单的编号或销售订单的父订单编号或提货订单编号")
    private String code;




}
