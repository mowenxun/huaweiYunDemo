package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;



/**
 * SupplerOrderDO
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@TableName("suppler_order")
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplerOrderDO extends BaseDO implements Serializable {

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
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 收货供销社编码
     */
    private String receiveDistributionCode;

    /**
     * 收货供销社id
     */
    private Long receiveDistributionId;

    /**
     * 收货供销社名称
     */
    private String receiveDistributionName;

    /**
     * 收货地址
     */
    private String receiveAddress;

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
     * 商品总数
     */
    private Long quantity;

    /**
     * 总商品金额
     */
    private BigDecimal totalAmount;

    /**
     * 支付类型:1线下支付,2在线支付,3信用支付,4余额支付
     */
    private Integer paymentType;

    /**
     * 支付状态
     */
    private Integer paymentStatus;

    /**
     * 要货日期
     */
    private Date arriveDate;

    /**
     * 已支付金额
     */
    private BigDecimal payAmount;

    /**
     * 支付流水关联订单号 支付后才会生成
     */
    private String payOrderCode;



}

