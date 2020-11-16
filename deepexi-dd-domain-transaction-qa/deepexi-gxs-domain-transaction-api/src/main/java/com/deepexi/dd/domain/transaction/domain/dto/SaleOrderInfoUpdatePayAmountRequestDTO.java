package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-16 14:29
 */
@Data
public class SaleOrderInfoUpdatePayAmountRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单id,数组",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;
    //    @ApiModelProperty(value = "订单编码",required = true)
//    @NotEmpty
//    private String saleOrderCode;

    @ApiModelProperty(value = "支付金额",dataType = "BigDecimal",required = true)
    private BigDecimal payAmount;

}
