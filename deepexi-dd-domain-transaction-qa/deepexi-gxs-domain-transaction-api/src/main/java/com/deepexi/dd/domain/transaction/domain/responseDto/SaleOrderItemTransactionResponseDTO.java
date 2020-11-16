package com.deepexi.dd.domain.transaction.domain.responseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by liaop on 2020/7/6.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderItemTransactionResponseDTO extends AbstractTenantResponseDTO implements Serializable {

    /**
     * 明细编号
     */
    @ApiModelProperty(value = "明细编号")
    private String code;
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
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
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
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

    @ApiModelProperty(value = "促销活动")
    private String promotionName;

    @ApiModelProperty(value="主图地址")
    private String majorPicture;
}
