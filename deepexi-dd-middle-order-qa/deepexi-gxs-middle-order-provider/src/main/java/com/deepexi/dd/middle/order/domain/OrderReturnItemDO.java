package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * OrderReturnItemDO
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@TableName("order_return_item")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderReturnItemDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 退单ID
     */
    private Long orderReturnId;

    /**
     * 退单编号
     */
    private String orderReturnCode;

    /**
     * 计价单位
     */
    private Long unitId;

    /**
     * 商品ID
     */
    private Long skuId;

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
     * 销售订单ID
     */
    private Long saleOrderId;

    /**
     * 销售订单编号
     */
    private String saleOrderCode;

    /**
     * 销售订单明细ID
     */
    private Long saleOrderItem;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 税率
     */
    private BigDecimal taxRate;

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
     * 退货数量
     */
    private Long returnQuantity;



}

