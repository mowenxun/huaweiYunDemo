package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* SaleOrderSplitRecordDTO
*
* @author admin
* @date Wed Aug 12 20:24:31 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderSplitRecordResponseDTO")
public class SaleOrderSplitRecordResponseDTO extends AbstractObject implements Serializable {

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
    * 拆单以后的订单编号，以|分隔
    */
    @ApiModelProperty(value = "拆单以后的订单编号，以|分隔")
    private String code;
    /**
    * 订单状态，从工具域获取
    */
    @ApiModelProperty(value = "订单状态，从工具域获取")
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
    * 订单来源:(agent:代客下单,mall:h5商城下单)
    */
    @ApiModelProperty(value = "订单来源:(agent:代客下单,mall:h5商城下单)")
    private String buyerType;
    /**
    * 客户名称
    */
    @ApiModelProperty(value = "客户名称")
    private String buyerName;
    /**
    * 单据类型(1:普通销售单,0:直供订单,2:非标准订单;3:订货计划单)
    */
    @ApiModelProperty(value = "单据类型(1:普通销售单,0:直供订单,2:非标准订单;3:订货计划单)")
    private Integer ticketType;
    /**
    * 送货方式(logistics:物流配送,custom:自提)
    */
    @ApiModelProperty(value = "送货方式(logistics:物流配送,custom:自提)")
    private String shippingType;
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
    * 商品总数
    */
    @ApiModelProperty(value = "商品总数")
    private Long quantity;
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
    * 支付类型:1线下支付,2在线支付,3信用支付,4余额支付
    */
    @ApiModelProperty(value = "支付类型:1线下支付,2在线支付,3信用支付,4余额支付")
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
    * 伙伴id
    */
    @ApiModelProperty(value = "伙伴id")
    private Long partnerId;
    /**
    * 伙伴名称
    */
    @ApiModelProperty(value = "伙伴名称")
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
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

}

