package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* 销售订单审核参数对象
*
* @author admin
* @date Tue Jun 23 19:44:57 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单金额更新")
public class SaleOrderInfoAmountEditDTO extends TenantDTO implements Serializable {

private static final long serialVersionUID = 1L;
    /**
    * 订单Id
    */
    @ApiModelProperty(value = "订单id",required = true)
    @Size(min = 1,message = "订单ID错误")
    private Long id;
    @NotEmpty(message = "付款状态为空")
    @ApiModelProperty(value = "付款状态",dataType = "Integer",required = true)
    private Integer status;
    @NotNull
    @ApiModelProperty(value = "付款金额,用于追加的金额，如果是撤销则为负数")
    private BigDecimal amount;

    private Integer version;
    @ApiModelProperty("支付类型")
    private Integer paymentType;
}

