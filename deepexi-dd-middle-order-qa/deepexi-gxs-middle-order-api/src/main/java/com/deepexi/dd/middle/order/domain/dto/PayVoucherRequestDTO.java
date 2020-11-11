package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghuai
 * @version 1.0
 * @date 2020-10-13 19:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel()
public class PayVoucherRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 供应商收款银行名称
     */
    @ApiModelProperty(value = "供应商收款银行名称")
    private String bankName;

    /**
     * 支行名称
     */
    @ApiModelProperty(value = "支行名称")
    private String branchName;

    /**
     * 供应商收款账户名称
     */
    @ApiModelProperty(value = "供应商收款账户名称")
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
     * 支付银行
     */
    @ApiModelProperty(value = "支付银行")
    private String payBank;

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
