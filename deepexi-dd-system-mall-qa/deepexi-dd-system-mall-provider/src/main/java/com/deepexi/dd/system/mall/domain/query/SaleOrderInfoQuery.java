package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SaleOrderInfoQuery
 *
 * @author admin
 * @date Tue Jun 30 11:43:59 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderInfoQuery extends AbstractObject implements Serializable {

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
         * 订单编号
         */
        private String code;
        /**
         * 订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消
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

        private String skuName;

        private String skuCode;

    /**
     * 排除的订单类型
     */
    @ApiModelProperty(value = "排除的订单类型")
    private List<Integer> notTickType;

    @ApiModelProperty("产品线")
    private Long productId;

    @ApiModelProperty("年份波段")
    private String year;

    @ApiModelProperty("只显示可提货数量不为零的数据")
    private Boolean availablePickNumNotZero;
}

