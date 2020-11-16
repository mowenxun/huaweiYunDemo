package com.deepexi.dd.domain.transaction.api.gxs.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = false)
public class ListSupplierOrderResponseDTO extends AbstractObject implements Serializable {


    private static final long serialVersionUID = 8637690224194187918L;

    @ApiModelProperty(value = "订单ID")
    private Long id;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 订单金额，元
     */
    @ApiModelProperty(value = "订单金额，元")
    private BigDecimal totalAmount;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Long quantity;
    /**
     * 订单日期（就是创建时间）
     */
    @ApiModelProperty(value = "订单日期：使用创建时间")
    private Date createdTime;
    /**
     * 来自门店订单
     */
    @ApiModelProperty(value = "来自门店订单")
    private String shopOrderCodes;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态:1待接单,2待发货,3已发货,4已签收,5已拒单,6已撤销 ",example = "2")
    private String status;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "支付状态 1待付款,2部分付款,3已付清",example = "1")
    private Integer paymentStatus;

    // 以下字段不展示,用户发货操作回传
    @ApiModelProperty(value = "收货供销社编码:发货操作回传")
    private String receiveDistributionCode;
    @ApiModelProperty(value = "收货供销社id:发货操作回传")
    private Long receiveDistributionId;
    @ApiModelProperty(value = "收货供销社名称:发货操作回传")
    private String receiveDistributionName;


    /**
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID")
    private Long sellerId;
    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    private String sellerName;
    /**
     * 供货商编码
     */
    @ApiModelProperty(value = "供货商编码")
    private String sellerCode;
}