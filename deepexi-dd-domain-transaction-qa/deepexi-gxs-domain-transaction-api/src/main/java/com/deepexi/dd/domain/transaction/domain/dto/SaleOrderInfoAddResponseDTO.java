package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemRequestDTO;
import com.deepexi.util.pojo.AbstractObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
* SaleOrderInfoDTO
*
* @author admin
* @date Wed Jun 24 11:00:03 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderInfoRequestDTO")
public class SaleOrderInfoAddResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
    * 卖家ID
    */
    @ApiModelProperty(value = "卖家ID,后期预留暂时可不填")
    private Long sellerId;
    /**
    * 卖家名称
    */
    @ApiModelProperty(value = "卖家名称,后期预留暂时可不填")
    private String sellerName;
    /**
    * 客户ID
    */
    @ApiModelProperty(value = "客户ID",required = true)
    @NotEmpty(message = "客户ID为不能空")
    private Long buyerId;

    @ApiModelProperty("订单编号")
    private String code;

    /**
    * 下单类型:(agent:代客下单,mall:商城下单)
    */
    @ApiModelProperty(value = "下单类型:(ValetOrder:代客下单,OnlineOrder:商城下单),当前默认填OnlineOrder",required = true)
    @NotEmpty(message = "下单类型为空")
    private String buyerType;
    /**
    * 客户名称
    */
    @ApiModelProperty(value = "客户名称")
    private String buyerName;
    /**
    * 单据类型(ordinary:普通销售单)
    */
    @ApiModelProperty(value = "单据类型0直供订单,1标准订单",required = true)
    @NotEmpty(message = "单据类型为空")
    private Integer ticketType;
    /**
    * 送货方式(logistics:物流配送)
    */
    @ApiModelProperty(value = "送货方式(logistics:物流配送)",required = true)
    @NotEmpty(message = "送货方式为空")
    private String shippingType;

    /**
    * 发货仓库
    */
    @ApiModelProperty(value = "发货仓库",required = true)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long fromStorehouse;
    /**
    * 经手人
    */
    @ApiModelProperty(value = "经手人")
    private Long handler;
    /**
    * 单据日期
    */
    @ApiModelProperty(value = "单据日期",required = true)
    private Date ticketDate;
    /**
    * 总商品金额
    */
    @ApiModelProperty(value = "总商品金额")
    private BigDecimal totalAmount;
    /**
    * 促销优惠金额
    */
    @ApiModelProperty(value = "促销优惠金额")
    private BigDecimal discountAmount;
    /**
    * 应收金额
    */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal accrueAmount;
    /**
    * 费用小计
    */
    @ApiModelProperty(value = "费用小计")
    private BigDecimal totalExpense;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "支付类型:1线下支付,2信用支付",required = true)
    private Integer paymentType;

    @ApiModelProperty(value = "订单送货地址添加对象")
    private OrderConsigneeInfoAddRequestDTO orderConsigneeInfoAddDtO;

    @ApiModelProperty(value = "优惠券ID列表")
    private List<Long> orderCouponIds;

    @ApiModelProperty(value = "促销活动ID列表")
    private List<Long> orderPromotionIds;

    @ApiModelProperty(value = "商品明细列表")
    @Size(min = 1,message = "未选择商品信息")
    private List<SaleOrderItemRequestDTO> items;

    @ApiModelProperty(value = "开票信息")
    private OrderInvoiceInfoRequestDTO orderInvoiceInfoAddRequestDTO;

    @ApiModelProperty(value = "订单费用信息列表")
    private List<OrderExpenseInfoRequestDTO>OrderExpenseInfoRequestDTO;

    @ApiModelProperty(value = "产品线id")
    private Long productId;

    @ApiModelProperty(value = "计划月份")
    private String planMonth;

    @ApiModelProperty(value = "产品线名称")
    private String productName;
    @ApiModelProperty(value = "订单状态")
    private Integer status;
    @ApiModelProperty(value = "子订单ID")
    private List<Long> subOrderId;
    @ApiModelProperty(value = "子订单编号以|分隔")
    private String subOrderCode;
}

