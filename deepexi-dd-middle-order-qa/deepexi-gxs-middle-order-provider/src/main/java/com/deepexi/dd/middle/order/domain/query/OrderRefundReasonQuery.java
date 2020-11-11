package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * OrderRefundReasonQuery
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderRefundReasonQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 页数
     */
    private Integer size = 10;

    /**
     * 主键
     */
    private Long id;
    /**
     * 租户
     */
    private String tenantId;
    /**
     * 应用id
     */
    private Long appId;
    /**
     * 版本号，乐观锁
     */
    private Integer version;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后更新时间
     */
    private Date updatedTime;
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
     * 备注
     */
    private String remark;
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
     * 申请日期从
     */
    private String applyDateFrom;

    /**
     * 申请日期到
     */
    private String applyDateTo;

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
     * 客户idList
     */
    private List<Long> customerIdList;
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

    private Long buyerId;

    private Long partnerId;
}

