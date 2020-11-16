package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* SaleOrderItemDTO
*
* @author admin
* @date Tue Jun 23 19:44:58 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderItemRequestDTO")
public class SaleOrderItemDTO extends AbstractTenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

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
     * 品牌编码
     */
    @ApiModelProperty(value = "品牌编码")
    private String brandCode;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    /**
     * 品牌名
     */
    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "店铺ID,必否则，不能获取商品详情,库存",required = true)
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
    * 价格政策标识
    */
    @ApiModelProperty(value = "价格政策标识")
    private Long pricePolicyId;
    @ApiModelProperty(value = "单位名称")
    private String unitName;
    /**
     * 主图片地址
     */
    @ApiModelProperty(value = "主图片地址")
    private String majorPicture;

    /**
     * 库存,不是数据库字段
     */
    private Long stock;

    @ApiModelProperty(value = "商品id")
    private Long itemId;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "商品副标题")
    private String subName;

    @ApiModelProperty(value = "商品状态")
    private Integer status;

    @ApiModelProperty(value = "价格")
    private BigDecimal purchasePrice;

    @ApiModelProperty(value = "属性规格")
    private String propertyValue;

    @ApiModelProperty(value = "直供id")
    private Long directId;

    @ApiModelProperty(value = "活动id")
    private Long activitiesId;

    @ApiModelProperty(value = "是否限购")
    private Boolean isLimited;

    @ApiModelProperty("限购数量")
    private Integer limitNum;

    public String getShopIdAndSkuId(){
        return shopId+"@_@"+skuId;
    }
}

