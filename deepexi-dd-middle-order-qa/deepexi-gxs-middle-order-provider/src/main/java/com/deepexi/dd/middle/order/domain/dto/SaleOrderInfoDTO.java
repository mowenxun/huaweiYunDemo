package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SaleOrderInfoDTO
 *
 * @author admin
 * @date Tue Jun 30 11:43:59 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderInfoDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * APP标识
     */
    private Long appId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0：未逻辑删除状态。1:删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

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
     * 出库状态 0:未出库,1已出库
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
    private Long totalQuantity ;

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
     * 项目ID
     */
    private Long projectId;

    /**
     * 配送类型 0=送货 1=自提
     */
    private Integer deliveryType;

    private List<SaleOrderItemMiddleResponseDTO> items;

}

