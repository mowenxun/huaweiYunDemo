package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;



/**
 * SaleOrderPayNotifyDO
 *
 * @author admin
 * @date Thu Jul 23 18:17:38 CST 2020
 * @version 1.0
 */
@TableName("sale_order_pay_notify")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderPayNotifyDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 确认类型:1确认,0取消
     */
    private Integer type;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 金额
     */
    private BigDecimal amount;


    /**
     * 订单类型1网批订单，2提货订单
     */
    @ApiModelProperty(value ="订单类型1网批订单，2提货订单" )
    private Integer orderType;

    /**
     * 订单号
     */
    @ApiModelProperty(value ="订单号" )
    private String orderCode;

}

