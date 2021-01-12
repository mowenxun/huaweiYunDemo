package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author JeremyLian
 * @date 2020/10/24 22:07
 */
public class PayOrderItemResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 8463537374592047826L;
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("APP标识")
    private Long appId;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建人")
    private String createdBy;
    @ApiModelProperty("创建时间")
    private Date createdTime;
    @ApiModelProperty("更新时间")
    private Date updatedTime;
    @ApiModelProperty("修改人")
    private String updatedBy;
    @ApiModelProperty("订单ID")
    private Long orderId;
    @ApiModelProperty("订单编号")
    private String orderCode;
    @ApiModelProperty("结算单位")
    private String settleUnit;
    @ApiModelProperty("单据日期")
    private Date orderTime;
    @ApiModelProperty("供货商ID")
    private Long sellerId;
    @ApiModelProperty("供货商名称")
    private String sellerName;
    @ApiModelProperty("供货商编码")
    private String sellerCode;
    @ApiModelProperty("支付方式")
    private String payWay;
    @ApiModelProperty("收款银行名称")
    private String bankName;
    @ApiModelProperty("支行名称")
    private String branchName;
    @ApiModelProperty("收款账户名称")
    private String accountName;
    @ApiModelProperty("供应商收款账号")
    private String accountNo;
    @ApiModelProperty("应支付总金额")
    private BigDecimal totalAmount;
    @ApiModelProperty("付款银行")
    private String payBank;
    @ApiModelProperty("付款银行账号")
    private String payAccount;
    @ApiModelProperty("支付时间")
    private Date payTime;
    @ApiModelProperty("本次支付金额")
    private BigDecimal payMoney;
    @ApiModelProperty("支付凭证，多张图片用;隔开")
    private String payVoucherPicture;
}
