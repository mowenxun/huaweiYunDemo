package com.deepexi.dd.domain.transaction.domain.vo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalesTodoResponseVO")
public class SalesTodoResponseVO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 待接单网批订单
     */
    @ApiModelProperty(value = "待接单网批订单")
    private Long s4DirectSupply;

    /**
     * 待发货网批订单
     */
    @ApiModelProperty(value = "待发货网批订单")
    private Long s67DirectSupply;

    /**
     * 待签收网批订单
     */
    @ApiModelProperty(value = "待签收网批订单")
    private Long s8DirectSupply;

    /**
     * 待付款网批订单
     */
    @ApiModelProperty(value = "待付款网批订单")
    private Long s26DirectSupply;

    /**
     * 待审核订货计划
     */
    @ApiModelProperty(value = "待审核订货计划")
    private Long s20OrderPlan;

    /**
     * 待确认提货计划
     */
    @ApiModelProperty(value = "待确认提货计划")
    private Long s24PickPlan;

}