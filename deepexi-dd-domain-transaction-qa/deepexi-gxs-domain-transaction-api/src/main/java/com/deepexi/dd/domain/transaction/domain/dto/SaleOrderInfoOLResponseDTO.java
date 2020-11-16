package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情返回对象(在线订购)
 *
 * @author yuanzaishun
 * @date Wed Jun 24 11:00:03 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderInfoOLResponseDTO  implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID",dataType = "Long")
    private Long id;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号",dataType = "Integer")
    private Integer version;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",dataType = "String")
    private String remark;

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
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value="订单类型,0直供订单,1标准订单，2：非标准订单，3：订货计划订单")
    private Integer ticketType;


    /**
     * 单据日期
     */
    @ApiModelProperty(value="单据日期",dataType = "Date")
    private Date ticketDate;

    /**
     * 要求到达日期
     */
    @ApiModelProperty(value="要求到达日期",dataType = "Date")
    private Date arriveDate;

    /**
     * 商品总数
     */
    @ApiModelProperty(value="商品总数",dataType = "Long")
    private Long quantity;

    /**
     * 总商品金额
     */
    @ApiModelProperty(value="总商品金额")
    private BigDecimal totalAmount;


    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value="支付状态 0:未付款,1:已付款,2:部分付款",dataType = "Integer")
    private Integer paymentStatus;

    /**
     * 支付类型:1线下支付,2在线支付,3信用支付,4余额支付
     */
    @ApiModelProperty(value="支付类型:1线下支付,2在线支付,3信用支付,4余额支付",dataType = "Integer")
    private Integer paymentType;

    /**
     * 卖家id
     */
    @ApiModelProperty(value="卖家id")
    private Long sellerId;

    /**
     * 拆单所属组织
     */
    private Long ascriptionOrgId;

    /**
     * 是否有退款
     */
    private Boolean hasRefund;


    @ApiModelProperty(value = "商品明细列表")
    private List<SaleOrderItemResponseDTO> items;
    @ApiModelProperty(value = "出库单数量")
    private Integer saleOutTaskAmount;

    @ApiModelProperty(value = "操作按钮")
    private List<OrderActionResponseDTO> actions;

    @ApiModelProperty(value = "出库单列表")
    private List<SaleOutTaskAppMiddleResponseDTO> saleOutTaskList;

    @ApiModelProperty(value = "产品线id")
    private Long productId;

    @ApiModelProperty(value = "计划月份")
    private String planMonth;

    @ApiModelProperty(value = "产品线名称")
    private String productName;

    @ApiModelProperty(value = "子订单ID")
    private List<Long> subOrderId;

    @ApiModelProperty(value = "支付流水code")
    private String payOrderCode;

    @ApiModelProperty(value = "子订单编号以|分隔")
    private String subOrderCode;

    /**
     * 可退金额
     */
    @ApiModelProperty(value="可退金额",dataType = "BigDecimal")
    private BigDecimal refundableAmount;

    /**
     * 供货商名称
     */
    @ApiModelProperty(value="供货商名称",dataType = "String")
    private String sellerName;
}

