package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Created by liaop on 2020/7/7.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "接单接口参数")
public class SaleOrderAcceptRequestDTO {
    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单id,数组",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;
    @ApiModelProperty(value = "备注",required = false)
    private String remark;
    @ApiModelProperty("数据版本")
    private Integer version;
}
