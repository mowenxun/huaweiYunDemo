package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* OrderExpenseInfoDTO
*
* @author admin
* @date Wed Jun 24 09:42:04 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderExpenseInfoRequestDTO")
public class OrderExpenseInfoRequestDTO extends AbstractTenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
    * 费用金额
    */
    @ApiModelProperty(value = "费用金额")
    private BigDecimal amount;
    /**
    * 费用类型
    */
    @ApiModelProperty(value = "费用类型")
    private Integer policyType;
    /**
    * 费用ID
    */
    @ApiModelProperty(value = "费用ID")
    private Long policyId;
    /**
    * 费用名称
    */
    @ApiModelProperty(value = "费用名称")
    private String policyName;
    /**
    * 是否可退(0,不可退,1,可退)
    */
    @ApiModelProperty(value = "是否可退(0,不可退,1,可退)")
    private Integer canReturn;

}

