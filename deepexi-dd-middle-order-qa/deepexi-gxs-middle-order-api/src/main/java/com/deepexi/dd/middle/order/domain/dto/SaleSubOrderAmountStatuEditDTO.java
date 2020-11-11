package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName : SaleSubOrderAmountStatuEditDTO
 * @Description : 子订单金额和状态变更
 * @Author : yuanzaishun
 * @Date: 2020-08-21 11:45
 */
@Data
@ApiModel("子订单金额和状态变更")
public class SaleSubOrderAmountStatuEditDTO extends TenantDTO {
    @ApiModelProperty(value = "父单ID")
    private Long id;
    //父单主状态
    private Integer status;
    //父单支付状态
    private Integer paymenetStatus;
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
}
