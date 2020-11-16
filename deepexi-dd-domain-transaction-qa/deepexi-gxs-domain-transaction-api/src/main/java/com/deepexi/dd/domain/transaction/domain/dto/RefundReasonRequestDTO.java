package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * OrderRefundReasonDTO
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderRefundReasonRequestDTO")
public class RefundReasonRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * ids
     */
    @ApiModelProperty(value = "ids")
    private List<Long> ids;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    /**
     * 租户
     */
    @ApiModelProperty(value = "租户")
    private String tenantId;
    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private Long appId;
    /**
     * 版本号，乐观锁
     */
    @ApiModelProperty(value = "版本号，乐观锁")
    private Integer version;
    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    private Boolean deleted;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间")
    private Date updatedTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
    /**
     * 数据隔离id
     */
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 退款单号
     */
    @ApiModelProperty(value = "退款单号")
    private String refundCode;
    /**
     * 申请金额
     */
    @ApiModelProperty(value = "申请金额")
    private BigDecimal applyAmount;
    /**
     * 单据类型：0网批订单，1提货计划
     */
    @ApiModelProperty(value = "单据类型：0网批订单，1提货计划")
    private Integer orderType;
    /**
     * 单据编号
     */
    @ApiModelProperty(value = "单据编号")
    private String orderCode;
    /**
     * 退款原因(字典配置)
     */
    @ApiModelProperty(value = "退款原因(字典配置)")
    private Long refundReason;
    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    private Date applyDate;
    /**
     * 退款时间
     */
    @ApiModelProperty(value = "退款时间")
    private Date refundDate;
    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;
    /**
     * 支付流水号
     */
    @ApiModelProperty(value = "支付流水号")
    private String payCode;
    /**
     * 退款方式：0余额，1信用账户
     */
    @ApiModelProperty(value = "退款方式：0余额，1信用账户")
    private Integer refundType;
    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    private Long customerId;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 退款状态：0待确认
     */
    @ApiModelProperty(value = "退款状态：0待确认")
    private Integer refundStatus;
    /**
     * 已支付金额
     */
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal payAmount;
    /**
     * 凭证
     */
    @ApiModelProperty(value = "凭证")
    private String voucher;
    /**
     * 凭证名称
     */
    @ApiModelProperty(value = "凭证名称")
    private String voucherName;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;

    /**
     * 审核原因
     */
    @ApiModelProperty(value = "审核原因")
    private String auditReason;

    /**
     * 一级组织id
     */
    @ApiModelProperty(value = "一级组织id")
    private Long orgId;

}

