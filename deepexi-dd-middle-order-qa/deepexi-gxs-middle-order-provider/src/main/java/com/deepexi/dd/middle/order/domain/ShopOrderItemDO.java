package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;



/**
 * ShopOrderItemDO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@TableName("shop_order_item")
@Data
@EqualsAndHashCode(callSuper = false)
public class ShopOrderItemDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 订单ID
     */
    private Long shopOrderId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 单位id
     */
    private Long unitId;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 商品ID
     */
    private Long skuId;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺编码
     */
    private String shopCode;

    /**
     * 供货商ID
     */
    private Long sellerId;

    /**
     * 供货商名称
     */
    private String sellerName;

    /**
     * 供货商编码
     */
    private String sellerCode;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品编号
     */
    private String skuCode;

    /**
     * 商品规格
     */
    private String skuFormat;

    /**
     * 商品数量
     */
    private Long skuQuantity;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额小计
     */
    private BigDecimal totalAmount;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 主图地址
     */
    private String majorPicture;

    /**
     * 签收数量
     */
    private Long signQuantity;

    /**
     * 建议零售价
     */
    private BigDecimal proposePrice;

    /**
     * 县级门店批发价
     */
    private BigDecimal countyPrice;

    /**
     * 镇级门店批发价
     */
    private BigDecimal townPrice;

    /**
     * 村级门店批发价
     */
    private BigDecimal villagePrice;

    /**
     * 团购级门店批发价
     */
    private BigDecimal groupPurchasePrice;

    /**
     * 集采价格
     */
    private BigDecimal collectPurchasePrice;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 品牌名
     */
    private String brandName;



}

