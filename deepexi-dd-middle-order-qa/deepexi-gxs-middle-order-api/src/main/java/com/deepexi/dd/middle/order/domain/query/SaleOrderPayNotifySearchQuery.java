package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName : SaleOrderPayNotifyQuery
 * @Description : 支付通知查询
 * @Author : yuanzaishun
 * @Date: 2020-07-23 19:00
 */
@Data
public class SaleOrderPayNotifySearchQuery extends AbstractObject {
    @ApiModelProperty("支付单号")
    private String payNo;
    @ApiModelProperty("订单ID")
    private Long orderId;
}
