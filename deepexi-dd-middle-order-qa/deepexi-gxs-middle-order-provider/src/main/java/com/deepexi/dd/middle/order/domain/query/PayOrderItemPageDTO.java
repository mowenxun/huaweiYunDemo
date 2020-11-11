package com.deepexi.dd.middle.order.domain.query;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghuai
 * @date 2020-10-14 06:53
 */
@TableName("pay_order_item")
@Data
@EqualsAndHashCode(callSuper = false)
public class PayOrderItemPageDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 8463537374592047826L;
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    private Long appId;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer version;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    /**
     * 结算单位
     */
    @ApiModelProperty(value = "结算单位")
    private String settleUnit;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
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
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String payWay;

    /**
     * 收款银行名称
     */
    @ApiModelProperty(value = "收款银行名称")
    private String bankName;

    /**
     * 支行名称
     */
    @ApiModelProperty(value = "支行名称")
    private String branchName;

    /**
     * 收款账户名称
     */
    @ApiModelProperty(value = "收款账户名称")
    private String accountName;

    /**
     * 供应商收款账号
     */
    @ApiModelProperty(value = "供应商收款账号")
    private String accountNo;

    /**
     * 应支付总金额
     */
    @ApiModelProperty(value = "应支付总金额")
    private BigDecimal totalAmount;

    /**
     * 付款银行
     */
    @ApiModelProperty(value = "付款银行")
    private String payBank;

    /**
     * 付款银行账号
     */
    @ApiModelProperty(value = "付款银行账号")
    private String payAccount;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    /**
     * 本次支付金额
     */
    @ApiModelProperty(value = "本次支付金额")
    private BigDecimal payMoney;

    /**
     * 支付凭证，多张图片用;隔开
     */
    @ApiModelProperty(value = "支付凭证，多张图片用;隔开")
    private String payVoucherPicture;
}