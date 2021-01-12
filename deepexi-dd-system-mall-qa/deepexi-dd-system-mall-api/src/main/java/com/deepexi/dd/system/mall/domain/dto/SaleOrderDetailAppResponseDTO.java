package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-16 10:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderDetailAppResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID",dataType = "Long")
    private Long id;

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
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消",dataType = "Integer")
    private Integer status;


    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期",dataType = "Date")
    private Date ticketDate;

    /**
     * 单据类型(1:普通销售单,0:直供订单)
     */
    @ApiModelProperty(value = "单据类型(1:普通销售单,0:直供订单)",dataType = "Integer")
    private Integer ticketType;

    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商",dataType = "String")
    private String sellerName ;

    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商ID",dataType = "Long")
    private Long sellerId ;

    /**
     * 客户
     */
    @ApiModelProperty(value = "客户",dataType = "String")
    private String buyerName ;


    /**
     * 总商品金额
     */
    @ApiModelProperty(value = "总商品金额",dataType = "BigDecimal")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "商品种类")
    private Integer commodityType;

    @ApiModelProperty(value = "商品数量")
    private Long commodityNum;


    /**
     * 要求到达日期
     */
    @ApiModelProperty(value = "要求到达日期",dataType = "Date")
    private Date arriveDate;

    /**
     * 已支付金额
     */
    @ApiModelProperty(value="已支付金额",dataType = "BigDecimal")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "订单送货地址对象")
    private OrderConsigneeInfoResponseDTO orderConsigneeInfo;
    @ApiModelProperty(value = "优惠券列表")
    private List<OrderCouponInfoResponseDTO> orderCouponList;
    @ApiModelProperty(value = "促销活动列表")
    private List<OrderPromotionInfoResponseDTO> orderPromotionList;
    @ApiModelProperty(value = "商品明细列表")
    private List<SaleOrderItemAppResponseDTO> items;
    @ApiModelProperty(value = "开票信息")
    private OrderInvoiceInfoResponseDTO orderInvoiceInfo;
    @ApiModelProperty(value = "订单费用信息列表")
    private List<OrderExpenseInfoResponseDTO> orderExpenseInfo;
    @ApiModelProperty(value = "出库单")
    private List<SaleOutTaskInfoResponseDTO> saleOutTaskList;
    @ApiModelProperty(value = "物流信息")
    private List<OrderDeliveryInfoResponseDTO> orderDeliveryInfoList;

    @ApiModelProperty(value = "产品线id")
    private Long productId;

    @ApiModelProperty(value = "计划月份")
    private String planMonth;

    @ApiModelProperty(value = "产品线名称")
    private String productName;


    @ApiModelProperty(value = "合同ID")
    private Integer contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "项目ID")
    private Integer projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "自提地址")
    private OrderDeliverySelfRaisingInfoResponseDTO orderDeliverySelfRaisingInfoResponseDTO;

    /**
     * 送货方式
     */
    @ApiModelProperty(value = "送货方式 0=送货 1=自提")
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

    @ApiModelProperty(value = "取消信息")
    private List<OrderOperationRecordResponseDTO> orderOperationRecordResponseDTOs;
}
