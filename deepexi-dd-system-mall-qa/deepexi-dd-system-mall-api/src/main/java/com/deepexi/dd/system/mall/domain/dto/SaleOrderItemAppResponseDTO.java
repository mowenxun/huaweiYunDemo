package com.deepexi.dd.system.mall.domain.dto;

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
public class SaleOrderItemAppResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;



    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;


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
     * 计价单位名
     */
    @ApiModelProperty(value = "计价单位名")
    private String unitName;

    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID")
    private Long skuId;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
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
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String majorPicture;

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    private String promotionName;

    @ApiModelProperty(value = "商品出库总数量")
    private Long skuTotalQuantity;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty("供应商顶层组织")
    private Long orgId;

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
     * 商品剩余可提货数量
     */
    @ApiModelProperty(value = "商品剩余可申请提货数量")
    private Long availablePickNum;


    @ApiModelProperty(value = "库存数量")
    private Integer stockNum;
}

