package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = false)
public class ListSupplierOrderRequestDTO extends PageDTO {


    private static final long serialVersionUID = 8637690224194187918L;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号:模糊查询")
    private String orderCodeLike;
    // @ApiModelProperty(value = "订单编号:精确查询")
    // private String orderCode;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态:1待接单,2待发货,3已发货,4已签收,5已拒单,6已撤销 ", example = "2")
    private String status;
    /**
     * 下单日期开始
     */
    @ApiModelProperty(value = "下单日期开始", example = "2020-09-10 12:00:01")
    private String createdTimeFrom;
    /**
     * 下单日期结束
     */
    @ApiModelProperty(value = "下单日期结束", example = "2020-10-10 12:00:01")
    private String createdTimeTo;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "支付状态 1待付款,2部分付款,3已付清", example = "1")
    private Integer paymentStatus;


}