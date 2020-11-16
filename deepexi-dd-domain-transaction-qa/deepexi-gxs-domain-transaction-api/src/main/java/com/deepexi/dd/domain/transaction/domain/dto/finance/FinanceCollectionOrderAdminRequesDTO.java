package com.deepexi.dd.domain.transaction.domain.dto.finance;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "FinanceCollectionOrderAdminRequestQuery")
public class FinanceCollectionOrderAdminRequesDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "销售订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "单据类型：0-销售订单、1-销售退货单")
    private Integer type;

    @ApiModelProperty(value = "本次结算金额")
    private BigDecimal settlementAmount;

}
