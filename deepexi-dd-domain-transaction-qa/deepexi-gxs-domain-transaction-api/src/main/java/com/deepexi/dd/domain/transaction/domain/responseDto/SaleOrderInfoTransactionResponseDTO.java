package com.deepexi.dd.domain.transaction.domain.responseDto;


import com.deepexi.dd.domain.transaction.domain.dto.OrderActionResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderOperationDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liaop on 2020/7/4.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderInfoTransactionResponseDTO extends AbstractTenantResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID",dataType = "Long")
    private Long id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号",dataType = "String")
    private String code;

    /**
     * 订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消
     */
    @ApiModelProperty(value = "订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消",dataType = "String")
    private Integer status;

    /**
     * 供货商ID
     */
    @ApiModelProperty(value="供货商ID",dataType = "Long")
    private Long sellerId;

    /**
     * 供货商名称
     */
    @ApiModelProperty(value="供货商名称",dataType = "String")
    private String sellerName;

    /**
     * 客户ID
     */
    @ApiModelProperty(value="客户ID",dataType = "Long")
    private Long buyerId;

    /**
     * 下单类型:(agent:代客下单,mall:h5商城下单)
     */
    @ApiModelProperty(value="下单类型:(agent:代客下单,mall:h5商城下单)",dataType = "String")
    private String buyerType;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称",dataType = "String")
    private String buyerName;

    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value="单据类型(ordinary:普通销售单)",dataType = "String")
    private Integer ticketType;

    /**
     * 送货方式(logistics:物流配送)
     */
    @ApiModelProperty(value="送货方式(logistics:物流配送)",dataType = "String")
    private String shippingType;

    /**
     * 出库状态 0:未出库,1已出库
     */
    @ApiModelProperty(value="出库状态 0:未出库,1已出库",dataType = "Integer")
    private Integer shipmentStatus;

    /**
     * 发货仓库
     */
    @ApiModelProperty(value="发货仓库",dataType = "Long")
    private Long fromStorehouse;

    /**
     * 经手人
     */
    @ApiModelProperty(value="经手人ID",dataType = "Long")
    private Long handler;

    /**
     * 经手人名称
     */
    @ApiModelProperty(value="经手人名称",dataType = "String")
    private String handlerName;

    /**
     * 单据日期
     */
    @ApiModelProperty(value="单据日期",dataType = "Date")
    private Date ticketDate;

    /**
     * 销售总数量
     */
    @ApiModelProperty(value = "销售总数量",dataType = "Long")
    private Long quantity;

    /**
     * 总商品金额
     */
    @ApiModelProperty(value="总商品金额")
    private BigDecimal totalAmount;

    /**
     * 促销优惠金额
     */
    @ApiModelProperty(value="促销优惠金额")
    private BigDecimal discountAmount;

    /**
     * 应收金额
     */
    @ApiModelProperty(value="应收金额")
    private BigDecimal accrueAmount;

    /**
     * 费用小计
     */
    @ApiModelProperty(value="费用小计")
    private BigDecimal totalExpense;

    /**
     * 支付类型:1线下支付,2信用支付
     */
    @ApiModelProperty(value="支付类型:1线下支付,2信用支付",dataType = "Integer")
    private Integer paymentType;

    /**
     * 结算状态 0:未结算,1:已结算,2:部分结算
     */
    @ApiModelProperty(value="结算状态 0:未结算,1:已结算,2:部分结算",dataType = "Integer")
    private Integer settlementStatus;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value="支付状态 0:未付款,1:已付款,2:部分付款",dataType = "Integer")
    private Integer paymentStatus;

    /**
     * 要求送达日期
     */
    @ApiModelProperty(value="要求送达日期",dataType = "Date")
    private Date arriveDate;
    /**
     * 部门名称
     */
    @ApiModelProperty(value="部门名称",dataType = "String")
    private String department;

    @ApiModelProperty(value = "订单送货地址对象")
    private OrderConsigneeInfoTransactionResponseDTO orderConsigneeInfo;
    @ApiModelProperty(value = "优惠券列表")
    private List<OrderCouponInfoTransactionResponseDTO> orderCouponList;
    @ApiModelProperty(value = "促销活动列表")
    private List<OrderPromotionInfoTransactionResponseDTO> orderPromotionList;
    @ApiModelProperty(value = "商品明细列表")
    private List<SaleOrderItemTransactionResponseDTO> items;
    @ApiModelProperty(value = "开票信息")
    private OrderInvoiceInfoTransactionResponseDTO orderInvoiceInfo;
    @ApiModelProperty(value = "订单费用信息列表")
    private List<OrderExpenseInfoTransactionResponseDTO> orderExpenseInfo;

    @ApiModelProperty(value = "操作按钮")
    private List<OrderActionResponseDTO> actions;

    /**
     * 已支付金额
     */
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal payAmount;
}
