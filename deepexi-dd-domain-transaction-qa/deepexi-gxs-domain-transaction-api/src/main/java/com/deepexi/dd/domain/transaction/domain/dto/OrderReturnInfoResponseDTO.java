package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* OrderReturnInfoDTO
*
* @author admin
* @date Wed Jun 24 09:42:05 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderReturnInfoResponseDTO")
public class OrderReturnInfoResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号",dataType = "Integer")
    private Integer version;

    /**
    * 退单编号
    */
    @ApiModelProperty(value = "退单编号")
    private String code;
    /**
    * 退单状态
    */
    @ApiModelProperty(value = "退单状态")
    private Integer status;
    /**
    * 客户ID
    */
    @ApiModelProperty(value = "客户ID")
    private Long buyerId;
    /**
    * 下单类型:(agent:代客下单,mall:商城下单)
    */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:商城下单)")
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
    * 入库仓库
    */
    @ApiModelProperty(value = "入库仓库")
    private Long toStorehouse;
    /**
    * 单据日期
    */
    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;
    /**
    * 退货方式。0：按单退货。1：按商品退货。
    */
    @ApiModelProperty(value = "退货方式。0：按单退货。1：按商品退货。")
    private Integer returnMode;
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
     * 退货明细列表
     */
    @ApiModelProperty(value = "退货明细列表")
    private List<OrderReturnItemResponseDTO> items;

}

