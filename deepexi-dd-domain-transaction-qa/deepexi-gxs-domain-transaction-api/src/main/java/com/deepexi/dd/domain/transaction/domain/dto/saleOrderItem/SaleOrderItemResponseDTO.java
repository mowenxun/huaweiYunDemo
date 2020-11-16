package com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* 商品明细信息
*
* @author admin
* @date Tue Jun 23 19:44:58 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "商品明细信息")
public class SaleOrderItemResponseDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "组织id")
    private Long orgId;

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
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

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

    @ApiModelProperty(value = "商品出库总数量")
    private Long skuTotalQuantity;

    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;

    /**
     * 申请数量
     */
    @ApiModelProperty(value = "商品本次申请提货数量")
    private Long skuPickQuantity;

    @ApiModelProperty(value="主图地址")
    private String majorPicture;

    @ApiModelProperty(value="签收数量")
    private Long signQuantity;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 剩余的可申请提货数量
     */
    @ApiModelProperty(value = "剩余的可申请提货数量")
    private Long availablePickNum;

    /**
     * 活动Id
     */
    @ApiModelProperty(value ="活动Id" )
    private Long activitiesId;

    /**
     * 直供ID
     */
    @ApiModelProperty(value = "直供ID")
    private Long directId;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private String rowCode;

    @ApiModelProperty(value = "库存数量")
    private Integer stockNum;
}

