package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
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
 * @version 1.0
 * @date Wed Jun 24 11:00:03 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单添加和编辑对象")
public class SaleOrderInfoAddRequestDTO extends AbstractTenantDTO implements Serializable {

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
    @ApiModelProperty(value = "客户ID", required = true)
    @NotEmpty(message = "客户ID为空")
    private Long buyerId;
    /**
     * 下单类型:(agent:代客下单,mall:商城下单)
     */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:商城下单),当前默认填agent", required = true)
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
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)", required = true)
    @NotEmpty(message = "单据类型为空")
    private Integer ticketType;
    /**
     * 送货方式(logistics:物流配送)
     */
    @ApiModelProperty(value = "送货方式(logistics:物流配送)", required = true)
    @NotEmpty(message = "送货方式为空")
    private String shippingType;

    /**
     * 发货仓库
     */
    @ApiModelProperty(value = "发货仓库", required = true)
    @NotEmpty(message = "发货仓库为空")
    private Long fromStorehouse;
    /**
     * 经手人
     */
    @ApiModelProperty(value = "经手人")
    private Long handler;

    @ApiModelProperty(value = "经手人名称")
    private String handlerName;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期", required = true)
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
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "支付状态 0:未付款,1:已付款,2:部分付款")
    private Integer paymentStatus;

    /**
     * 订单状态:从工具域获取
     */
    @ApiModelProperty(value = "订单状态:从工具域获取", dataType = "Integer")
    private Integer status;
    @ApiModelProperty("订单编号")
    private String code;

    /**
     * 商品总数
     */
    @ApiModelProperty(value = "商品数量")
    private Long quantity;

    @ApiModelProperty(value = "支付类型:1线下支付,2信用支付", required = true)
    private Integer paymentType;

    @ApiModelProperty(value = "要求送达日期")
    private Date arriveDate;

    @ApiModelProperty(value = "隔离标识")
    private String isolationId;

 /*   @ApiModelProperty(value = "经手人名称")
    private String handlerName;*/

    @ApiModelProperty(value = "部门名称")
    private String department;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "总发货数量")
    private Long totalQuantity;

    @ApiModelProperty(value = "总签收数量")
    private Long totalSignQuantity;

    /**
     * 总提货数量
     */
    @ApiModelProperty(value = "总提货数量")
    private Long pickQuantity;

    /**
     * 审核状态（0未审核 1已审核）
     */
    @ApiModelProperty(value = "审核状态（0未审核 1已审核）")
    private Integer verifyStatus;

    /**
     * 接单状态（0未接单 1已接单）
     */
    @ApiModelProperty(value = "接单状态（0未接单 1已接单）")
    private Integer acceptStatus;

    /**
     * 伙伴id
     */
    @ApiModelProperty(value = "伙伴id")
    private Long partnerId;

    /**
     * 伙伴名字
     */
    @ApiModelProperty(value = "伙伴名字")
    private String partnerName;

    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    private Long customerId;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 拆单所属组织
     */
    @ApiModelProperty(value = "拆单所属组织")
    private Long ascriptionOrgId;
    @ApiModelProperty(value = "父订单ID")
    private Long parentSaleOrderId;

    @ApiModelProperty(value = "父订单编号")
    private String parentSaleOrderCode;
    @ApiModelProperty(value = "订单送货地址添加对象")
    private OrderConsigneeInfoAddRequestDTO orderConsigneeInfo;
    private @ApiModelProperty(value = "订单自提地址添加对象", required = true)
    OrderDeliverySelfRaisingInfoRequestDTO orderSelfRaisingInfo;
    @ApiModelProperty(value = "优惠券列表")
    private List<OrderCouponInfoRequestDTO> orderCouponList;
    @ApiModelProperty(value = "促销活动列表")
    private List<OrderPromotionInfoRequestDTO> orderPromotionList;
    @ApiModelProperty(value = "商品明细列表")
    @Size(min = 1, message = "未选择商品信息")
    private List<SaleOrderItemMiddleRequestDTO> items;
    @ApiModelProperty(value = "开票信息")
    private OrderInvoiceInfoRequestDTO orderInvoiceInfo;
    @ApiModelProperty(value = "订单费用信息列表")
    private List<OrderExpenseInfoRequestDTO> orderExpenseInfo;

    //产品线id
    private Long productId;

    //计划月份
    private String planMonth;

    //产品线名称
    private String productName;

    /**
     * 合同ID
     */
    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    private String contractName;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    /**
     * 项目名称
     */
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
    @ApiModelProperty("备注")
    private String remark;
}
