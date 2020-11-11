package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;



/**
 * OrderRefundReasonDO
 *
 * @author admin
 * @date Wed Aug 19 16:00:27 CST 2020
 * @version 1.0
 */
@TableName("order_refund_reason")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderRefundReasonDO extends BaseDO implements Serializable {

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
     * 数据隔离id
     */
    private String isolationId;

    /**
     * 退款单号
     */
    private String refundCode;

    /**
     * 申请金额
     */
    private BigDecimal applyAmount;

    /**
     * 单据类型：0网批订单，1提货计划
     */
    private Integer orderType;

    /**
     * 单据id
     */
    private Long orderId;

    /**
     * 单据编号
     */
    private String orderCode;

    /**
     * 退款原因(字典配置)
     */
    private Long refundReason;

    /**
     * 申请日期
     */
    private Date applyDate;

    /**
     * 退款时间
     */
    private Date refundDate;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 支付流水号
     */
    private String payCode;

    /**
     * 退款方式：0余额，1信用账户
     */
    private Integer refundType;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 退款状态：0待确认
     */
    private Integer refundStatus;

    /**
     * 已支付金额
     */
    private BigDecimal payAmount;

    /**
     * 凭证
     */
    private String voucher;

    /**
     * 凭证名称
     */
    private String voucherName;

    /**
     * 单据日期
     */
    private Date ticketDate;

    /**
     * 审核原因
     */
    private String auditReason;

    /**
     * 一级组织id
     */
    private Long orgId;

    /**
     * 支付类型
     */
    private Integer paymentType;

    /**
     * 买家id
     */
    private Long buyerId;

    /**
     * 伙伴id
     */
    private Long partnerId;
}

