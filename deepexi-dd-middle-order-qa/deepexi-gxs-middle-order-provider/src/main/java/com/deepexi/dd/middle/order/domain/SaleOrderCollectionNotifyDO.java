package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;



/**
 * SaleOrderCollectionNotifyDO
 *
 * @author admin
 * @date Fri Jul 24 14:50:09 CST 2020
 * @version 1.0
 */
@TableName("sale_order_collection_notify")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderCollectionNotifyDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 金额
     */
    private BigDecimal amount;



}

