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
 * @author yp
 * @version 1.0
 * @date 2020-08-28 20:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单关联支付记录订单号更新")
public class SaleOrderInfoPayOrderCodeEditDTO extends TenantDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单id", required = true)
    @Size(min = 1, message = "订单ID错误")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "支付流水关联订单号")
    private String payOrderCode;

}
