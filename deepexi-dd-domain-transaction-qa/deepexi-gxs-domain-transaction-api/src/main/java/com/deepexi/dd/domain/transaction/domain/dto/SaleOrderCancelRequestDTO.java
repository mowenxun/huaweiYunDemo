package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
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
@ApiModel(description = "取消接口参数")
public class SaleOrderCancelRequestDTO extends TenantDTO {
    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单id",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;

    @ApiModelProperty(value = "actionCode",required = true)
    @NotEmpty(message = "actionCode不能为空")
    private String actionCode;

    @ApiModelProperty(value = "备注",required = false)
    private String remark;


    @ApiModelProperty(value = "取消原因",required = true)
    private String radioName;

}
