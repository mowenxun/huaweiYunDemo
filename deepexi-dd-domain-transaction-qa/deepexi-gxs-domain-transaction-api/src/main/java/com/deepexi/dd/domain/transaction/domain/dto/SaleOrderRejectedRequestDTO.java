package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Created by zhouquan on 2020/7/30.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "接单接口参数")
public class SaleOrderRejectedRequestDTO extends TenantDTO {
    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单id",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;

    @ApiModelProperty(value = "actionCode")
    private String actionCode;

    @ApiModelProperty(value = "备注",required = false)
    private String remark;

}
