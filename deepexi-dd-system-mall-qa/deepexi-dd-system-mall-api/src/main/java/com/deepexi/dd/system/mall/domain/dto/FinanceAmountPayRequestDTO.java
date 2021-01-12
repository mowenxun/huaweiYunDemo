package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName : FinanceAmountPayRequestDTO
 * @Description : 余额支付
 * @Author : yuanzaishun
 * @Date: 2020-08-25 10:25
 */
@Data
@ApiModel("余额支付")
public class FinanceAmountPayRequestDTO extends AbstractObject {
    @ApiModelProperty("账户ID")
    private Long amountId;
    @ApiModelProperty(value = "订单编号",required = true)
    private String orderCode;
    @ApiModelProperty("订单ID")
    private Long orderId;
    @ApiModelProperty(value = "支付金额",required =true)
    private BigDecimal happenAmount;
    @ApiModelProperty(value = "单据类型:0网批,1提货计划,2还款",required =true)
    private Integer orderType;
}
