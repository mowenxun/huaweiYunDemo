package com.deepexi.dd.domain.transaction.domain.dto.finance;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * FinanceCreditPaymentDTO
 *
 * @author admin
 * @date Thu Aug 20 19:07:23 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "FinanceCreditPaymentDTO")
public class FinanceCreditPaymentRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 信用id
     */
    @ApiModelProperty(value = "信用id")
    private Long creditId;
    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号")
    private String code;
    /**
     * 伙伴id
     */
    @ApiModelProperty(value = "伙伴id")
    private Long customerId;
    /**
     * 伙伴名称
     */
    @ApiModelProperty(value = "伙伴名称")
    private String customerName;
    /**
     * 操作类型:1-新增；2-额度变更；3-订货；4-收款返还
     */
    @ApiModelProperty(value = "操作类型:1-新增；2-额度变更；3-订货；4-收款返还")
    private Integer type;
    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private BigDecimal happenLimit;
    /**
     * 可用额度
     */
    @ApiModelProperty(value = "可用额度")
    private BigDecimal creditLimit;
    /**
     * 关联订单id
     */
    @ApiModelProperty(value = "关联订单id")
    private Long orderId;
    /**
     * 关联订单编码
     */
    @ApiModelProperty(value = "关联订单编码")
    private String orderCode;
    /**
     * 关联订单类型0-网批订单、1-提货计划、2-信用还款
     */
    @ApiModelProperty(value = "关联订单类型:0-网批订单、1-提货计划、2-信用还款")
    private Integer orderType;
    /**
     * 操作人id
     */
    @ApiModelProperty(value = "操作人id")
    private Long userId;
    /**
     * 操作人名称
     */
    @ApiModelProperty(value = "操作人名称")
    private String userName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 数据隔离
     */
    @ApiModelProperty(value = "数据隔离")
    private String isolationId;
    /**
     *  组织ID
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;
}
