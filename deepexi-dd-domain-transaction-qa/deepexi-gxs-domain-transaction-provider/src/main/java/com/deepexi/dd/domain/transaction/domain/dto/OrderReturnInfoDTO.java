package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.AbstractTenantDTO;
import com.deepexi.dd.domain.transaction.domain.dto.add.OrderReturnItemAddRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售订单添加和编辑对象
 *
 * @author admin
 * @date Wed Jun 24 11:00:03 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "退货订单添加和编辑对象")
public class OrderReturnInfoDTO extends AbstractTenantDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
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
     * 卖家ID
     */
    @ApiModelProperty(value = "卖家ID")
    private Long sellerId;
    /**
     * 卖家名称
     */
    @ApiModelProperty(value = "卖家名称")
    private String sellerName;
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
     * 送货方式(logistics:物流配送)
     */
    @ApiModelProperty(value = "送货方式(logistics:物流配送)")
    private String shippingType;
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
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    /**
     * 退货商品总数量
     */
    @ApiModelProperty(value = "退货商品总数量")
    private Long returnQuantity;

    /**
     * 退货明细
     */
    @ApiModelProperty(value = "退货明细")
    private List<OrderReturnItemRequestDTO> items;
}
