package com.deepexi.dd.domain.transaction.domain.vo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalesStatisticsResponseVO")
public class SalesStatisticsResponseVO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 销售笔数
     */
    @ApiModelProperty(value = "销售笔数")
    private Long salesCount;

    /**
     * 销售收入
     */
    @ApiModelProperty(value = "销售收入")
    private BigDecimal salesAmount;

    /**
     * 收款金额
     */
    @ApiModelProperty(value = "收款金额")
    private BigDecimal payAmount;
}
