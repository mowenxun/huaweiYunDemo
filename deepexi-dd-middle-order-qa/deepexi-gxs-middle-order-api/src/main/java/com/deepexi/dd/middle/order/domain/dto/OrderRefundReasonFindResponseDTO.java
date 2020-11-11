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
@ApiModel(description = "OrderRefundReasonFindResponseDTO")
public class OrderRefundReasonFindResponseDTO extends AbstractObject implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    /**
     * 订单编码
     */
    @ApiModelProperty(value = "订单编码")
    private String orderCode;

    /**
     * 退款状态
     */
    @ApiModelProperty(value="退款状态")
    private Integer refundStatus;

    /**
     * 退款金额
     */
    @ApiModelProperty(value="退款金额")
    private BigDecimal refundAmount;

}
