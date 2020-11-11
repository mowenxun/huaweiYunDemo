package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * OrderPromotionInfoDO
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@TableName("order_promotion_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderPromotionInfoDO extends BaseDO implements Serializable {

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
     * 促销类型
     */
    private Integer promotionType;

    /**
     * 促销ID
     */
    private Long promotionId;

    /**
     * 促销名称
     */
    private String promotionName;

    /**
     * 促销描述
     */
    private String promotionSpec;

    /**
     * 促销规则
     */
    private String promotionRule;

    /**
     * 促销开始时间
     */
    private Date startTime;

    /**
     * 促销结束时间
     */
    private Date endTime;

    /**
     * sku商品ID集合
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

