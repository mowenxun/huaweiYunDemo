package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghuai
 * @date 2020-10-14 06:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class ListPayOrderResponseDTO extends AppIdDTO {
    private static final long serialVersionUID = 7888192850738771336L;

    @ApiModelProperty(value = "订单ID")
    private Long id;
    @ApiModelProperty(value = "应收单编号")
    private String orderCode;
    @ApiModelProperty(value = "来源单号 ")
    private String sourceOrderCode;
    @ApiModelProperty(value = "来源单号Id ")
    private Long sourceOrderId;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalAmount;
    @ApiModelProperty(value = "已收金额")
    private BigDecimal paidMoney;
    @ApiModelProperty(value = "未收金额")
    private BigDecimal unpayMoney;
    @ApiModelProperty(value = "单据日期")
    private Date createdTime;
    @ApiModelProperty(value = "最后修改日期")
    private Date updatedTime;

    // 创建收款单使用
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
     * 收款状态
     */
    @ApiModelProperty(value = "收款状态", notes = "10待收款,11部分收款,12已收讫")
    private Integer collectionStatus;


}