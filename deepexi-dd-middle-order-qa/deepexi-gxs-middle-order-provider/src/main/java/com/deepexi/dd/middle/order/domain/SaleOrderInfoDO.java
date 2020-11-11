package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * SaleOrderInfoDO
 *
 * @author admin
 * @date Tue Jun 30 11:43:59 CST 2020
 * @version 1.0
 */
@TableName("sale_order_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String code;

    /**
     * 订单状态:从工具域获取
     */
    private Integer status;

    /**
     * 供货商ID
     */
    private Long sellerId;

    /**
     * 供货商名称
     */
    private String sellerName;

    /**
     * 客户ID
     */
    private Long buyerId;

    /**
     * 下单类型:(agent:代客下单,mall:h5商城下单)
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
     * 出库状态 0:未出库,1已出库,2部分出库
     */
    private Integer shipmentStatus;

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
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 支付类型:1线下支付,2信用支付
     */
    private Integer paymentType;

    /**
     * 结算状态 0:未结算,1:已结算,2:部分结算
     */
    private Integer settlementStatus;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    private Integer paymentStatus;

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
    private Long totalQuantity;

    /**
     * 总签收数量
     */
    private Long totalSignQuantity;

    /**
     * 总提货数量
     */
    private Long pickQuantity;

    /**
     * 审核状态（13未审核 14已审核）
     */
    private Integer verifyStatus;

    /**
     * 接单状态（4未接单 16已接单）
     */
    private Integer acceptStatus;

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
    /**
     * 拆单所属组织
     */
    private Long ascriptionOrgId;
    /**
     * 父订单ID
     */
    @ApiModelProperty(value = "父订单ID")
    private Long parentSaleOrderId;

    /**
     * 父订单编号"
     */
    private String parentSaleOrderCode;
    //产品线id
    private Long productId;

    //计划月份
    private String planMonth;

    //产品线名称
    private String productName;

    /**
     * 合同ID
     */
    private Long contractId;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     *项目名称
     */
    private String projectName;

    /**
     * 0=活动订单；1=网批直供单；3=其他类型
     */
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

    /**
     * 备注
     */
    private String remark;
}

