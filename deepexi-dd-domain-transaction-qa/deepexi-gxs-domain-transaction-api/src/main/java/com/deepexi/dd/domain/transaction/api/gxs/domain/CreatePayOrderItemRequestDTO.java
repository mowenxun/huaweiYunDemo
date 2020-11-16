package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghuai
 * @date 2020-10-14 06:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class CreatePayOrderItemRequestDTO extends AppIdDTO {
    private static final long serialVersionUID = 8463537374592047826L;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID：来源单据id")
    @NotNull(message = "来源单据id不能为空")
    private Long orderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号：来源单据")
    @NotBlank(message = "来源单据Code不能为空")
    private String orderCode;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期",notes = "来源单据日期")
    @NotNull(message = "来源单据日期不能为空")
    private Date orderTime;

    /**
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID")
    private Long sellerId;

    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    private String sellerName;

    /**
     * 供货商编码
     */
    @ApiModelProperty(value = "供货商编码")
    private String sellerCode;

    /**
     * 收款银行名称
     */
    @ApiModelProperty(value = "收款银行名称")
    @NotBlank(message = "收款银行名称不能为空")
    private String bankName;

    /**
     * 供应商收款账号
     */
    @ApiModelProperty(value = "供应商收款账号")
    @NotBlank(message = "收款银行账号不能为空")
    private String accountNo;

    /**
     * 应支付总金额
     */
    @ApiModelProperty(value = "应支付总金额：取本单金额")
    @NotNull(message = "本单金额不能为空")
    private BigDecimal totalAmount;

    /**
     * 付款银行
     */
    @ApiModelProperty(value = "付款银行")
    @NotBlank(message = "付款银行不能为空")
    private String payBank;

    /**
     * 付款银行账号
     */
    @ApiModelProperty(value = "付款银行账号")
    @NotBlank(message = "付款银行账号不能为空")
    private String payAccount;

    /**
     * 本次支付金额==本次收款
     */
    @ApiModelProperty(value = "本次收款")
    @NotNull(message = "本次收款不能为空")
    private BigDecimal payMoney;

    /**
     * 支付凭证，多张图片用;隔开
     */
    @ApiModelProperty(value = "支付凭证，多张图片用;隔开")
    @NotBlank(message = "收款凭证不能为空")
    private String payVoucherPicture;
}