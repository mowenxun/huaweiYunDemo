package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(description = "销售订单添加和编辑对象")
public class SaleOrderInfoAddDTO extends TenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 卖家ID
    */
    private Long sellerId;
    /**
    * 卖家名称
    */
    private String sellerName;
    /**
    * 客户ID
    */
    private Long buyerId;
    /**
    * 下单类型:(agent:代客下单,mall:商城下单)
    */
    private String buyerType;
    /**
    * 客户名称
    */
    private String buyerName;
    /**
    * 单据类型(ordinary:普通销售单)
    */
    private Integer ticketType;
    /**
    * 送货方式(logistics:物流配送)
    */
    private String shippingType;

    /**
    * 发货仓库
    */
    private Long fromStorehouse;
    /**
    * 经手人
    */
    private Long handler;
    /**
    * 单据日期
    */
    private Date ticketDate;
    /**
     * 商品总数
     */
    private Long quantity;
    /**
    * 总商品金额
    */
    private BigDecimal totalAmount;
    /**
    * 促销优惠金额
    */
    private BigDecimal discountAmount;
    /**
    * 应收金额
    */
    private BigDecimal accrueAmount;
    /**
    * 费用小计
    */
    private BigDecimal totalExpense;

    /**
     * 备注
     */
    private String remark;
    /**
     * 支付类型
     */
    private Integer paymentType;
    /**
     * 要求送达日期
     */
    private Date arriveDate;

    /**
     * 隔离标识
     */
    private String isolationId;
    /**
     * 经手人名称
     */
    private String handlerName;
    /**
     * 部门名称
     */
    private String department;
    /**
     * 已支付金额
     */
    private BigDecimal payAmount;
    /**
     * 总发货数量
     */
    private Long totalQuantity ;


    @ApiModelProperty(value = "订单送货地址添加对象")
    private OrderConsigneeInfoAddRequestDTO orderConsigneeInfoAdd;
    @ApiModelProperty(value = "优惠券ID列表")
    private List<Long> orderCouponIds;
    @ApiModelProperty(value = "促销活动ID列表")
    private List<Long> orderPromotionIds;
    @ApiModelProperty(value = "商品明细列表")
    @Size(min = 1,message = "未选择商品信息")
    private List<SaleOrderItemDTO> items;
    @ApiModelProperty(value = "开票信息")
    private OrderInvoiceInfoAddRequestDTO orderInvoiceInfoAdd;
    @ApiModelProperty(value = "订单费用信息列表")
    private List<OrderExpenseInfoAddRequestDTO> orderExpenseInfoAdd;

    //产品线id
    private Long productId;

    //计划月份
    private String planMonth;

    //产品线名称
    private String productName;
}

