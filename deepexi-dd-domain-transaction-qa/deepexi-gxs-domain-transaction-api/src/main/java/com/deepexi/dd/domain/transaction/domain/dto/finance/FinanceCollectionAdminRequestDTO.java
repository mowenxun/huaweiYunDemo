package com.deepexi.dd.domain.transaction.domain.dto.finance;

import com.deepexi.util.pojo.AbstractObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * FinanceCollectionDTO
 *
 * @author admin
 * @version 1.0
 * @date Fri Jul 03 19:06:06 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "FinanceCollectionAdminRequestDTO")
public class FinanceCollectionAdminRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否生成支付流水
     */
    @ApiModelProperty(value = "是否生成支付流水:0-是；1-否")
    private Integer isPaymentRecords;
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
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @NotNull(message = "客户id不能为空")
    private Long customerId;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 经手人id
     */
    @ApiModelProperty(value = "经手人id")
    private Long handlersId;
    /**
     * 经手人名称
     */
    @ApiModelProperty(value = "经手人名称")
    private String handlersName;
    /**
     * 收款账户id
     */
    @ApiModelProperty(value = "收款账户id")
    private Long accountId;
    /**
     * 收款账户名称
     */
    @ApiModelProperty(value = "收款账户名称")
    private String accountName;
    /**
     * 收款银行账号
     */
    @ApiModelProperty(value = "收款银行账号")
    private String accountNumber;
    /**
     * 付款账户名称
     */
    @ApiModelProperty(value = "付款账户名称")
    private String paymentAccountName;
    /**
     * 付款银行账号
     */
    @ApiModelProperty(value = "付款银行账号")
    private String paymentAccountNumber;
    /**
     * 收款编号
     */
    @ApiModelProperty(value = "收款编号")
    private String code;
    /**
     * 单据类型：0收款单
     */
    @ApiModelProperty(value = "单据类型：0收款单")
    private Integer type;
    /**
     * 收款方式：1-线下支付、2-线上、3-余额支付
     */
    @ApiModelProperty(value = "收款方式：1-线下支付、2-线上、3-余额支付")
    @NotNull(message = "收款方式不能为空")
    private Integer way;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    @NotNull(message = "单据日期不能为空")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiptsTime;
    /**
     * 收款金额
     */
    @ApiModelProperty(value = "收款金额")
    private BigDecimal amount;
    /**
     * 收款凭证url
     */
    @ApiModelProperty(value = "收款凭证url")
    private String credentialsUrl;
    /**
     * 结算单位id
     */
    @ApiModelProperty(value = "结算单位id")
    private Long settlementId;
    /**
     * 结算单位名称
     */
    @ApiModelProperty(value = "结算单位名称")
    private String settlementName;
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

    private Integer status;
    /**
     * 数据隔离id
     */
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;
    /**
     * 订单数据
     */
    @ApiModelProperty(value = "订单数据")
    private List<FinanceCollectionOrderAdminRequesDTO> datas;
}