package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by chenqili on 2020/7/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "支付订单接口参数")
public class SaleOrderPayRequestDTO extends TenantDTO {
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
    @Range(max = 2,min = -1,message = "订单类型错误")
    @NotNull(message = "订单类型错误")
    private Integer orderType;

    /**
     * 订单编码
     */
    @ApiModelProperty(value = "关联订单编码")
    private String orderCode;

}
