package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * SaleOrderItemDO
 *
 * @author admin
 * @date Tue Jun 23 19:44:58 CST 2020
 * @version 1.0
 */
@TableName("sale_order_item")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderItemDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 明细编号
     */
    private String code;

    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

    /**
     * 计价单位
     */
    private Long unitId;

    /**
     * 商品ID
     */
    private Long skuId;
    /**
     * 店铺ID
     */
    private Long shopId;

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
     * 金额小计（含税）
     */
    private BigDecimal totalAmount;

    /**
     * 优惠后金额
     */
    private BigDecimal accrueAmount;

    /**
     * 优惠分摊金额
     */
    private BigDecimal discountAmount;

    /**
     * 成本价（单价)
     */
    private BigDecimal costPrice;

    /**
     * 价格政策标识
     */
    private Long pricePolicyId;

    /**
     * 商品出库总数量
     */
    private Long skuTotalQuantity;
    /**
     * 主图片地址
     */
    private String majorPicture;
    /**
     * 单位中文名称
     */
    private String unitName;

    /**
     * 商品签收数量
     */
    private Long signQuantity;

    /**
     * 可提货数量
     */
    private Long availablePickNum;

    /**
     * 活动Id
     */
    private Long activitiesId;

    /**
     * 直供ID
     */
    private Long directId;

    /**
     * 行号
     */
    private String rowCode;
}

