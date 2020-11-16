package com.deepexi.dd.domain.transaction.domain.responseDto.gxs;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ShopOrderItemDTO
 *
 * @author admin
 * @version 1.0
 * @date Tue Oct 13 15:15:24 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PlatformDistbutionItemAddDTO")
public class PlatformDistbutionItemAddDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id")
    private Long id;


    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    private Long appId;

    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
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
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID")
    private Long sellerId;
    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    private String sellerName;
    /**
     * 供货商编码
     */
    @ApiModelProperty(value = "供货商编码")
    private String sellerCode;
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
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    /**
     * 金额小计
     */
    @ApiModelProperty(value = "金额小计")
    private BigDecimal totalAmount;
    /**
     * 成本价
     */
    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;
    /**
     * 主图地址
     */
    @ApiModelProperty(value = "主图地址")
    private String majorPicture;
    /**
     * 建议零售价
     */
    @ApiModelProperty(value = "建议零售价")
    private BigDecimal proposePrice;
    /**
     * 县级门店批发价
     */
    @ApiModelProperty(value = "县级门店批发价")
    private BigDecimal countyPrice;
    /**
     * 镇级门店批发价
     */
    @ApiModelProperty(value = "镇级门店批发价")
    private BigDecimal townPrice;
    /**
     * 村级门店批发价
     */
    @ApiModelProperty(value = "村级门店批发价")
    private BigDecimal villagePrice;
    /**
     * 团购级门店批发价
     */
    @ApiModelProperty(value = "团购级门店批发价")
    private BigDecimal groupPurchasePrice;
    /**
     * 集采价格
     */
    @ApiModelProperty(value = "集采价格")
    private BigDecimal collectPurchasePrice;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    /**
     * 品牌编码
     */
    @ApiModelProperty(value = "品牌编码")
    private String brandCode;

    /**
     * 品牌名
     */
    @ApiModelProperty(value = "品牌名")
    private String brandName;

}

