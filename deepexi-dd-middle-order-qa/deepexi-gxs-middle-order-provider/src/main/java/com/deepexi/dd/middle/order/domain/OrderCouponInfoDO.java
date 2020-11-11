package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * OrderCouponInfoDO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@TableName("order_coupon_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderCouponInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 优惠券编码
     */
    private String couponCode;

    /**
     * 优惠券类型
     */
    private Integer couponType;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 优惠券数量
     */
    private Integer couponQuntity;

    /**
     * 面值金额
     */
    private BigDecimal faceValue;

    /**
     * 优惠规则
     */
    private String couponRule;

    /**
     * 优惠券过期时间
     */
    private Date expiredTime;

    /**
     * sku商品ids
     */
    private String skuIds;

    /**
     * sku名称、规格
     */
    private String skuNames;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

}

