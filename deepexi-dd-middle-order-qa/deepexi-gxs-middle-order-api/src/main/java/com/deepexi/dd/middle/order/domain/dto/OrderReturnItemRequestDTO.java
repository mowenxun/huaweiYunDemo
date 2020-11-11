package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* OrderReturnItemDTO
*
* @author admin
* @date Wed Jun 24 09:42:06 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderReturnItemRequestDTO")
public class OrderReturnItemRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 计价单位
    */
    @ApiModelProperty(value = "计价单位")
    private Long unitId;
    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID")
    private Long skuId;
    /**
    * 商品名称
    */
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    /**
    * 商品编号
    */
    @ApiModelProperty(value = "商品编号")
    private String skuCode;
    /**
    * 商品规格
    */
    @ApiModelProperty(value = "商品规格")
    private String skuFormat;
    /**
    * 商品数量
    */
    @ApiModelProperty(value = "商品数量")
    private Long skuQuantity;

    /**
    * 销售订单明细ID
    */
    @ApiModelProperty(value = "销售订单明细ID")
    private Long saleOrderItem;
    /**
    * 单价
    */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    /**
    * 税率
    */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;
    /**
    * 金额小计（含税）
    */
    @ApiModelProperty(value = "金额小计（含税）")
    private BigDecimal totalAmount;
    /**
    * 优惠后金额
    */
    @ApiModelProperty(value = "优惠后金额")
    private BigDecimal accrueAmount;
    /**
    * 优惠分摊金额
    */
    @ApiModelProperty(value = "优惠分摊金额")
    private BigDecimal discountAmount;
    /**
    * 成本价（单价)
    */
    @ApiModelProperty(value = "成本价（单价)")
    private BigDecimal costPrice;
    /**
    * 价格政策标识
    */
    @ApiModelProperty(value = "价格政策标识")
    private Long pricePolicyId;
    /**
    * 退货数量
    */
    @ApiModelProperty(value = "退货数量")
    private Long returnQuantity;

}

