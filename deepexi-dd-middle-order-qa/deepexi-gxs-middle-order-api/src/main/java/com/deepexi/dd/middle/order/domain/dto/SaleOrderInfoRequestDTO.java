package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* SaleOrderInfoDTO
*
* @author admin
* @date Tue Jun 30 11:43:59 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderInfoRequestDTO")
public class SaleOrderInfoRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * APP标识
    */
    @ApiModelProperty(value = "APP标识")
    private Long appId;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 0：未逻辑删除状态。1:删除
    */
    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String code;
    /**
    * 订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消
    */
    @ApiModelProperty(value = "订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消")
    private Integer status;
    /**
    * 供货商ID
    */
    @ApiModelProperty(value = "供货商ID")
    private Long sellerId;
    /**
    * 供货商名称
    */
    @ApiModelProperty(value = "供货商名称")
    private String sellerName;
    /**
    * 客户ID
    */
    @ApiModelProperty(value = "客户ID")
    private Long buyerId;
    /**
    * 下单类型:(agent:代客下单,mall:h5商城下单)
    */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:h5商城下单)")
    private String buyerType;
    /**
    * 客户名称
    */
    @ApiModelProperty(value = "客户名称")
    private String buyerName;
    /**
    * 单据类型(ordinary:普通销售单)
    */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)")
    private Integer ticketType;
    /**
    * 送货方式(logistics:物流配送)
    */
    @ApiModelProperty(value = "送货方式(logistics:物流配送)")
    private String shippingType;
    /**
    * 出库状态 0:未出库,1已出库
    */
    @ApiModelProperty(value = "出库状态 0:未出库,1已出库")
    private Integer shipmentStatus;
    /**
    * 发货仓库
    */
    @ApiModelProperty(value = "发货仓库")
    private Long fromStorehouse;
    /**
    * 经手人
    */
    @ApiModelProperty(value = "经手人")
    private Long handler;
    /**
    * 单据日期
    */
    @ApiModelProperty(value = "单据日期")
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
    * 支付类型:1线下支付,2信用支付
    */
    @ApiModelProperty(value = "支付类型:1线下支付,2信用支付")
    private Integer paymentType;
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
    * 要求送达日期
    */
    @ApiModelProperty(value = "要求送达日期")
    private Date arriveDate;
    /**
     * 隔离标识
     */
    @ApiModelProperty(value = "隔离标识")
    private String isolationId;
    /**
     * 经手人名称
     */
    @ApiModelProperty(value = "经手人名称")
    private String handlerName;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String department;

    /**
     * 已支付金额
     */
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal payAmount;

    /**
     * 总发货数量
     */
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


    @ApiModelProperty(value = "产品线id")
    private Long productId;

    @ApiModelProperty(value = "计划月份")
    private String planMonth;

    @ApiModelProperty(value = "产品线名称")
    private String productName;

    /**
     * 0=活动订单；1=网批直供单；3=其他类型
     */
    @ApiModelProperty(value = "0=活动订单；1=网批直供单；3=其他类型")
    private Integer orderType;
}

