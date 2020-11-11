package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * SendGoodsInfoDO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@TableName("pay_voucher")
@Data
@EqualsAndHashCode(callSuper = false)
public class PayVoucherDO extends BaseDO implements Serializable {

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
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 供应商收款银行名称
     */
    private String bankName;

    /**
     * 支行名称
     */
    private String branchName;

    /**
     * 供应商收款账户名称
     */
    private String accountName;

    /**
     * 供应商收款账号
     */
    private String accountNo;

    /**
     * 应支付总金额
     */
    private BigDecimal totalAmount;

    /**
     * 支付银行
     */
    private String payBank;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 本次支付金额
     */
    private BigDecimal payMoney;

    /**
     * 支付凭证，多张图片用;隔开
     */
    private String payVoucherPicture;




}

