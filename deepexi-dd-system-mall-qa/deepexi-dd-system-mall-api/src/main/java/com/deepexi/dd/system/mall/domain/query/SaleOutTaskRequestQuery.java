package com.deepexi.dd.system.mall.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
* SaleOutTaskQuery
*
* @author admin
* @date Tue Jun 23 19:44:58 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskRequestQuery")
public class SaleOutTaskRequestQuery extends BasePage implements Serializable {

private static final long serialVersionUID = 1L;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
     * 出库单编号
     */
    @ApiModelProperty(value = "出库单编号")
    private String code;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String buyerName;
    /**
     * 客户编号
     */
    @ApiModelProperty(value = "客户编号")
    private String buyerNo;
    /**
    * 是否显示红单
    */
    @ApiModelProperty(value = "是否显示红单")
    private Integer isShowHedge;

    @ApiModelProperty(value = "发货计划ID")
    private Long saleDeliveryPlanId;
    @ApiModelProperty(value = "发货计划编号")
    private String saleDeliveryPlanCode;
    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;
    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;
}

