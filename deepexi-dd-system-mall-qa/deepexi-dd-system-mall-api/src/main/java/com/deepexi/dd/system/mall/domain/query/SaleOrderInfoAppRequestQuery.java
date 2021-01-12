package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* SaleOrderInfoQuery
*
* @author admin
* @date Tue Jun 23 19:44:57 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单查询参数对象")
public class SaleOrderInfoAppRequestQuery extends BasePage implements Serializable {

private static final long serialVersionUID = 1L;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String code;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态:从业务链路获取订单状态")
    private Integer status;


    /**
     * 单据来源:(agent:代客下单,mall:商城下单)
     */
    @ApiModelProperty(value = "单据来源:(agent:代客下单,mall:H5商城下单)")
    private String buyerType;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String buyerName;
    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)")
    private Integer ticketType;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "支付状态 0:未付款,1:已付款,2:部分付款")
    private Integer paymentStatus;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String skuName;

    /**
     * 商品code
     */
    @ApiModelProperty(value = "商品code")
    private String skuCode;

//    /**
//    * 客户名称
//    */
//    @ApiModelProperty(value = "客户名称")
//    private String buyerName;
//    /**
//    * 单据类型(ordinary:普通销售单)
//    */
//    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)")
//    private Integer ticketType;
//    /**
//    * 送货方式(logistics:物流配送)
//    */
//    @ApiModelProperty(value = "送货方式(logistics:物流配送)")
//    private String shippingType;
//    /**
//    * 发货仓库
//    */
//    @ApiModelProperty(value = "发货仓库")
//    private Long fromStorehouse;


}

