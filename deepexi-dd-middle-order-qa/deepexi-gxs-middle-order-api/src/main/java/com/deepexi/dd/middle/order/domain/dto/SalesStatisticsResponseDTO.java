package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalesStatisticsResponseDTO")
public class SalesStatisticsResponseDTO extends AbstractObject implements Serializable {
    @ApiModelProperty(value="id")
    private Long id;

    @ApiModelProperty(value="订单状态",dataType = "Integer")
    private Integer status;

    /**
     * 应收金额
     */
    @ApiModelProperty(value="应收金额")
    private BigDecimal accrueAmount;

    /**
     * 已支付金额
     */
    @ApiModelProperty(value="已支付金额")
    private BigDecimal payAmount;
}
