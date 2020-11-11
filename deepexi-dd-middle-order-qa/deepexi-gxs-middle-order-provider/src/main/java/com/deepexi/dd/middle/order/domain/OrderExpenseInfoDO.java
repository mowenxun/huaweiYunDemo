package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * OrderExpenseInfoDO
 *
 * @author admin
 * @date Wed Jun 24 09:42:04 CST 2020
 * @version 1.0
 */
@TableName("order_expense_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderExpenseInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 费用金额
     */
    private BigDecimal amount;

    /**
     * 费用类型
     */
    private Integer policyType;

    /**
     * 费用ID
     */
    private Long policyId;

    /**
     * 费用名称
     */
    private String policyName;

    /**
     * 是否可退(0,不可退,1,可退)
     */
    private Integer canReturn;



}

