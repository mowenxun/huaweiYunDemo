package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * OrderReturnInfoDO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@TableName("order_return_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderReturnInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 退单编号
     */
    private String code;

    /**
     * 退单状态
     */
    private Integer status;

    /**
     * 卖家ID
     */
    private Long sellerId;

    /**
     * 卖家名称
     */
    private String sellerName;

    /**
     * 客户ID
     */
    private Long buyerId;

    /**
     * 下单类型:(agent:代客下单,mall:商城下单)
     */
    private String buyerType;

    /**
     * 客户名称
     */
    private String buyerName;

    /**
     * 单据类型(ordinary:普通销售单)
     */
    private Integer ticketType;

    /**
     * 送货方式(logistics:物流配送)
     */
    private String shippingType;

    /**
     * 入库仓库
     */
    private Long toStorehouse;

    /**
     * 经手人
     */
    private Long handler;

    /**
     * 单据日期
     */
    private Date ticketDate;

    /**
     * 收货日期
     */
    private Date expectDate;

    /**
     * 退货方式。0：按单退货。1：按商品退货。
     */
    private Integer returnMode;

    /**
     * 原销售订单ID
     */
    private Long saleOrderId;

    /**
     * 原订单编号
     */
    private String saleOrderCode;

    /**
     * 退还金额
     */
    private BigDecimal canReturnAmount;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;



}

