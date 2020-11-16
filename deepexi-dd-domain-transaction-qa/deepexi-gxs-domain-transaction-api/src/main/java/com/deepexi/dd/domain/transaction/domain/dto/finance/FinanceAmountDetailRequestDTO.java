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
 * FinanceAmountDetailDTO
 *
 * @author admin
 * @date Thu Aug 13 09:54:40 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "FinanceAmountDetailRequestDTO")
public class FinanceAmountDetailRequestDTO extends AbstractObject implements Serializable {

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
     * 余额id
     */
    @ApiModelProperty(value = "余额id")
    private Long amountId;
    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号")
    private String code;
    /**
     * 操作类型：1-余额充值，2-订单扣款，3-订单退款，4-取消返还
     */
    @ApiModelProperty(value = "操作类型：1-余额充值，2-订单扣款，3-订单退款，4-取消返还")
    private Integer type;
    /**
     * 发生金额
     */
    @ApiModelProperty(value = "发生金额")
    private BigDecimal happenAmount;
    /**
     * 账户余额
     */
    @ApiModelProperty(value = "账户余额")
    private BigDecimal amountLimit;
    /**
     * 关联单据id
     */
    @ApiModelProperty(value = "关联单据id")
    private Long orderId;
    /**
     * 关联单据编码
     */
    @ApiModelProperty(value = "关联单据编码")
    private String orderCode;
    /**
     * 关联单据类型
     */
    @ApiModelProperty(value = "关联单据类型")
    private Integer orderType;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
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
     * 数据隔离
     */
    @ApiModelProperty(value = "数据隔离")
    private String isolationId;

}

