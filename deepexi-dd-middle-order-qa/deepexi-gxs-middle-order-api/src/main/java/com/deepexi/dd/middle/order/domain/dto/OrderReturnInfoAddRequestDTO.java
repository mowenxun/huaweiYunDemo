package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import com.deepexi.util.pojo.AbstractObject;
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
 * OrderReturnInfoDTO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderReturnInfoResponseDTO")
public class OrderReturnInfoAddRequestDTO extends AbstractTenantDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 退单编号
     */
    @ApiModelProperty(value = "退单编号",required = true)
    @NotEmpty(message = "退单编号为空")
    private String code;
    /**
     * 退单状态
     */
    @ApiModelProperty(value = "退单状态",required = true)
    @NotEmpty(message = "退单状态为空")
    private Integer status;
    /**
     * 卖家ID
     */
    @ApiModelProperty(value = "卖家ID",required = true)
    @NotEmpty(message = "卖家ID为空")
    private Long sellerId;
    /**
     * 卖家名称
     */
    @ApiModelProperty(value = "卖家名称",required = true)
    @NotEmpty(message = "卖家名称为空")
    private String sellerName;
    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID",required = true)
    @NotEmpty(message = "客户ID为空")
    private Long buyerId;
    /**
     * 下单类型:(agent:代客下单,mall:商城下单)
     */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:商城下单)",required = true)
    @NotEmpty(message = "下单类型为空")
    private String buyerType;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称",required = true)
    @NotEmpty(message = "客户名称为空")
    private String buyerName;
    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)",required = true)
    @NotEmpty(message = "单据类型为空")
    private Integer ticketType;
    /**
     * 送货方式(logistics:物流配送)
     */
    @ApiModelProperty(value = "送货方式(logistics:物流配送)",required = true)
    @NotEmpty(message = "送货方式为空")
    private String shippingType;
    /**
     * 入库仓库
     */
    @ApiModelProperty(value = "入库仓库",required = true)
    @NotEmpty(message = "入库仓库为空")
    private Long toStorehouse;
    /**
     * 经手人
     */
    @ApiModelProperty(value = "经手人")
    private Long handler;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期",required = true)
    @NotEmpty(message = "单据日期为空")
    private Date ticketDate;
    /**
     * 收货日期
     */
    @ApiModelProperty(value = "收货日期")
    private Date expectDate;
    /**
     * 退货方式。0：按单退货。1：按商品退货。
     */
    @ApiModelProperty(value = "退货方式。0：按单退货。1：按商品退货。",required = true)
    @NotEmpty(message = "退货方式为空")
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

    @ApiModelProperty(value = "商品明细列表")
    @Size(min = 1,message = "未选择商品信息")
    private List<OrderReturnItemRequestDTO> items;

}

