package com.deepexi.dd.domain.transaction.domain.dto.finance;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FinancePaymentRecordsDTO
 *
 * @author admin
 * @version 1.0
 * @date Mon Jul 20 10:41:15 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "FinancePaymentRecordsRequestDTO")
public class FinancePaymentRecordsRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    private String tenantId;
    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private Long appId;
    /**
     * 收款单id
     */
    @ApiModelProperty(value = "收款单id")
    private Long collectionId;
    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号")
    private String code;
    /**
     * 来源订单id
     */
    @ApiModelProperty(value = "来源订单id")
    private Long orderId;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderCode;
    /**
     * 支付类型：0-在线支付、1-线下支付
     */
    @ApiModelProperty(value = "支付类型：0-在线支付、1-线下支付、2信用支付")
    private Integer payType;
    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Date receiptsTime;
    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private BigDecimal amount;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer version;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;
    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private Boolean deleted;
    /**
     * 状态：0-待确认、1-已确认、2-已作废
     */
    @ApiModelProperty(value = "状态：0-待确认、1-已确认、2-已作废")
    private Integer status;
    /**
     * 还款状态：0-无、1-待还款、2-待确认、3-已还款、4-已退款
     */
    @ApiModelProperty(value = "还款状态")
    private Integer repaymentStatus;
    /**
     * 数据隔离id
     */
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;
    /**
     * 收款账户
     */
    @ApiModelProperty(value = "收款账户")
    private String proceedsAccount;
    /**
     * 支付账户
     */
    @ApiModelProperty(value = "支付账户")
    private String paymentAccount;
    /**
     * 支付凭证
     */
    @ApiModelProperty(value = "支付凭证")
    private String paymentVoucher;
    /**
     * 收款银行账号
     */
    @ApiModelProperty(value = "收款银行账号")
    private String proceedsBankAccount;
    /**
     * 支付银行账号
     */
    @ApiModelProperty(value = "支付银行账号")
    private String paymentBankAccount;
    /**
     * 所属组织ID
     */
    @ApiModelProperty(value = "所属组织ID")
    private Long orgId;
    /**
     * 业务伙伴ID
     */
    @ApiModelProperty(value = "业务伙伴ID")
    private Long partnerId;
    /**
     * 支付单据类型：0-网批订单、1-提货计划、2-信用还款
     */
    @ApiModelProperty(value = "支付单据类型：0-网批订单、1-提货计划、2-信用还款、3-退款订单")
    private Integer orderType;
    /**
     * 支付渠道：1-微信、2-支付宝、3-云闪付
     */
    @ApiModelProperty(value = "支付渠道：1-微信、2-支付宝、3-云闪付")
    private Integer payChannel;
}

