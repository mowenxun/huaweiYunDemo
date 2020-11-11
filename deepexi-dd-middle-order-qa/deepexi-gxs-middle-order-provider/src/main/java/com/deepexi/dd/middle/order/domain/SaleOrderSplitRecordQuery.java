package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SaleOrderSplitRecordQuery
 *
 * @author admin
 * @date Wed Aug 12 20:24:31 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderSplitRecordQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 页数
     */
    private Integer size = 10;

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
         * 创建时间
         */
        private Date createdTime;
        /**
         * 更新时间
         */
        private Date updatedTime;
        /**
         * 拆单以后的订单编号，以|分隔
         */
        private String code;
        /**
         * 订单状态，从工具域获取
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
         * 订单来源:(agent:代客下单,mall:h5商城下单)
         */
        private String buyerType;
        /**
         * 客户名称
         */
        private String buyerName;
        /**
         * 单据类型(1:普通销售单,0:直供订单,2:非标准订单;3:订货计划单)
         */
        private Integer ticketType;
        /**
         * 送货方式(logistics:物流配送,custom:自提)
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
         * 创建人
         */
        private String createdBy;
        /**
         * 更新人
         */
        private String updatedBy;
        /**
         * 支付类型:1线下支付,2在线支付,3信用支付,4余额支付
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
         * 伙伴id
         */
        private Long partnerId;
        /**
         * 伙伴名称
         */
        private String partnerName;
        /**
         * 客户ID
         */
        private Long customerId;
        /**
         * 客户名称
         */
        private String customerName;
}

