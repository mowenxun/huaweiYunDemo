package com.deepexi.dd.domain.transaction.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "StockDetailQuery")
public class StockDetailQuery extends BasePage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "单据编号")
    private String extendNo;

    @ApiModelProperty(value = "仓库id")
    private Long depotId;

    @ApiModelProperty(value = "单据类型")
    private Integer stockType;
}
