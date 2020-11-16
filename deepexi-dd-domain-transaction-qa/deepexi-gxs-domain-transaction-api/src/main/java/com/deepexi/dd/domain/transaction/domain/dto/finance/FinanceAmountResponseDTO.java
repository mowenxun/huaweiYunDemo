package com.deepexi.dd.domain.transaction.domain.dto.finance;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * FinanceAmountDTO
 *
 * @author admin
 * @date Thu Aug 13 09:54:40 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "FinanceAmountResponseDTO")
public class FinanceAmountResponseDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 信用额度
     */
    @ApiModelProperty(value = "信用额度")
    private BigDecimal creditLimit;
    /**
     * 已用额度
     */
    @ApiModelProperty(value = "已用信用额度")
    private BigDecimal creditUsed;

    /**
     * 信用账户id
     */
    @ApiModelProperty(value = "信用账户id")
    private Long creditId;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
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
     * 业务伙伴id
     */
    @ApiModelProperty(value = "业务伙伴id")
    private Long relationId;
    /**
     * 业务伙伴名称
     */
    @ApiModelProperty(value = "业务伙伴名称")
    private String relationName;
    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private BigDecimal amount;
    /**
     * 已用金额
     */
    @ApiModelProperty(value = "已用金额")
    private BigDecimal amountUsed;
    /**
     * 可用金额
     */
    @ApiModelProperty(value = "可用金额")
    private BigDecimal canAmount;

    private String remark;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
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
     * 数据隔离
     */
    @ApiModelProperty(value = "数据隔离")
    private String isolationId;

    /**
     * 组织id.
     */
    private Long orgId;

}

