package com.deepexi.dd.domain.transaction.domain.dto.finance;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-19 12:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "支付订单接口参数【提货计划】")
public class SalePickOrderPayRequestDTO extends TenantDTO {
    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单id", required = true)
    @Min(value = 1, message = "订单ID错误")
    private Long id;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型:0-网批订单、1-提货计划、2-信用还款")
    private Integer orderType;
}
