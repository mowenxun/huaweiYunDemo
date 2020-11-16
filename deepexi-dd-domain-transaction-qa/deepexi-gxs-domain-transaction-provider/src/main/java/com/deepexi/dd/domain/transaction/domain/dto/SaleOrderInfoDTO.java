package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class SaleOrderInfoDTO extends AbstractTenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

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
    @NotEmpty(message = "客户ID为空")
    private Long buyerId;
    /**
    * 下单类型:(agent:代客下单,mall:商城下单)
    */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:商城下单),当前默认填agent",required = true)
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
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)",required = true)
    @NotEmpty(message = "单据类型为空")
    private Integer ticketType;
    /**
    * 送货方式(logistics:物流配送)
    */
    @ApiModelProperty(value = "送货方式(logistics:物流配送)",required = true)
    @NotEmpty(message = "送货方式为空")
    private String shippingType="logistics";

    /**
    * 发货仓库
    */
    @ApiModelProperty(value = "发货仓库",required = true)
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
    * 其他费用合计
    */
    @ApiModelProperty(value = "其他费用合计")
    private BigDecimal totalExpense;

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
     * 出库状态 0:未出库,1已出库
     */
    @ApiModelProperty(value = "出库状态 0:未出库,1已出库  ")
    private Integer shipmentStatus;

    /**
     * 结算状态 0:未结算,1:已结算,2:部分结算
     */
    @ApiModelProperty(value = "结算状态 0:未结算,1:已结算,2:部分结算")
    private Integer settlementStatus;

    /**
     * 商品总数
     */
    private Long quantity;

    /**
     * 订单状态:从工具域获取
     */
    @ApiModelProperty(value="订单状态:从工具域获取",dataType = "Integer")
    private Integer status;
    @ApiModelProperty("订单编号")
    private String code;

    @ApiModelProperty(value = "支付类型:1线下支付,2信用支付",required = true)
    private Integer paymentType;

     @ApiModelProperty(value = "要求送达日期")
     private Date arriveDate;

    @ApiModelProperty(value = "经手人名称")
    private String handlerName;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "订单送货地址添加对象")
    private OrderConsigneeInfoDTO orderConsigneeInfo;
    @ApiModelProperty(value = "订单自提地址添加对象",required = true)
    private OrderSelfRaisingInfoAddDTO orderSelfRaisingInfoAddInfo;
    @ApiModelProperty(value = "优惠券列表")
    private List<OrderCouponInfoDTO> orderCouponInfoList;
    @ApiModelProperty(value = "促销活动列表")
    private List<OrderPromotionInfoDTO> orderPromotionInfoList;
    @ApiModelProperty(value = "商品明细列表")
    @Size(min = 1,message = "未选择商品信息")
    private List<SaleOrderItemDTO> items;
    @ApiModelProperty(value = "开票信息")
    private OrderInvoiceInfoDTO orderInvoiceInfo;
    @ApiModelProperty(value = "订单费用信息列表")
    private List<OrderExpenseInfoDTO> orderExpenseInfoList;
    @ApiModelProperty(value = "订单物流信息对象")
    private OrderDeliveryInfoDTO orderDeliveryInfo;
    @ApiModelProperty(value = "操作类型:1提交,0保存草稿",required = true)
    private Integer type;

    /**
     * 隔离标识
     */
    private String isolationId;
    /**
     * 备注
     */
    private String remark;

    private Long partnerId;

    private String partnerName;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 客户名称
     */
    private String customerName;

    private Long customerType;

    /**
     * 拆单所属组织
     */
    private Long ascriptionOrgId;

    @ApiModelProperty(value = "父订单ID")
    private Long parentSaleOrderId;

    @ApiModelProperty(value = "父订单编号")
    private String parentSaleOrderCode;
    @ApiModelProperty(value = "产品线id")
    private Long productId;

    @ApiModelProperty(value = "计划月份")
    private String planMonth;

    @ApiModelProperty(value = "产品线名称")
    private String productName;

    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "0=活动订单；1=网批直供单；3=其他类型")
    private Integer orderType;

    /**
     * 配送类型 0=送货 1=自提
     */
    @ApiModelProperty(value = "配送类型 0=送货 1=自提")
    private Integer deliveryType;

    /**
     * 支付流水关联订单号
     */
    @ApiModelProperty(value = "支付流水关联订单号")
    private String payOrderCode;
    @ApiModelProperty(value = "子订单ID")
    private List<Long> subOrderId;
    @ApiModelProperty(value = "子订单编号以|分隔")
    private String subOrderCode;

}
