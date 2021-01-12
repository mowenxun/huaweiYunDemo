package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-11 10:08
 */

@Data
public class OrderReturnInfoAppResponseDTO extends AbstractObject implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 退单编号
     */
    @ApiModelProperty(value = "退单编号")
    private String code;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long buyerId;
    /**
     * 下单类型:(agent:代客下单,mall:商城下单)
     */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:商城下单)")
    private String buyerType;
    /**
     * 买家名称
     */
    @ApiModelProperty(value = "买家名称")
    private String buyerName;
    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)")
    private Integer ticketType;
    /**
     * 入库仓库
     */
    @ApiModelProperty(value = "入库仓库")
    private Long toStorehouse;
    /**
     * 经手人
     */
    @ApiModelProperty(value = "经手人")
    private Long handler;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;
    /**
     * 收货日期
     */
    @ApiModelProperty(value = "收货日期")
    private Date expectDate;
    /**
     * 退货方式。0：按单退货。1：按商品退货。
     */
    @ApiModelProperty(value = "退货方式。0：按单退货。1：按商品退货。")
    private Integer returnMode;
    /**
     * 原销售订单ID
     */
    @ApiModelProperty(value = "原销售订单ID")
    private Long saleOrderId;
    /**
     * 原订单编号
     */
    @ApiModelProperty(value = "原订单编号")
    private String saleOrderCode;
    /**
     * 退还金额
     */
    @ApiModelProperty(value = "退还金额")
    private BigDecimal canReturnAmount;
    /**
     * 退货商品总数量
     */
    @ApiModelProperty(value = "退货商品总数量")
    private Long returnQuantity;

    /**
     * 退单状态
     */
    @ApiModelProperty(value = "退单状态")
    private String status;

    @ApiModelProperty(value = "退货明细列表")
    private List<OrderReturnItemResponseDTO> items;
}
