package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName : SaleOrderAmountStatuEditDTO
 * @Description : 父订单金额状态变更对象
 * @Author : yuanzaishun
 * @Date: 2020-08-21 11:43
 */
@Data
@ApiModel("父订单金额状态变更对象")
public class SaleOrderAmountStatuEditDTO extends TenantDTO {
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
    /**
     * 支付类型
     */
    private Integer paymentType;
    /**
     * 子订单信息
     */
    List<SaleOrderInfoAmountEditDTO> subList;
}
