package com.deepexi.dd.domain.transaction.domain.dto.saleOutTask;

import com.deepexi.dd.domain.transaction.domain.dto.OrderActionResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.AbstractTenantResponseDTO;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SaleOutTaskInfoResponseVO
 * @Description 销售出库单列表
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskInfoResponseDTO")
public class SaleOutTaskInfoResponseDTO extends AbstractTenantResponseDTO implements Serializable {
    private static final long serialVersionUID = 8723632532522833995L;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
     * 出库单编号
     */
    @ApiModelProperty(value = "出库单编号")
    private String code;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
     * 订单确认状态 0:已确认,1已作废
     */
    @ApiModelProperty(value = "订单确认状态 0:已确认,1已作废")
    private Integer status;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;
    /**
     * 计划出库总数量
     */
    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;
    /**
     * 出库单类型,1原单,2蓝单,3红单
     */
    @ApiModelProperty(value = "出库单类型,1原单,2蓝单,3红单")
    private Integer taskType;
    /**
     * 红冲的原订单标识
     */
    @ApiModelProperty(value = "红冲的原订单标识")
    private Long hedgeOrder;

    //补充字段 SongTao 2020/07/06
    @ApiModelProperty(value = "单据类型:out 销售出库单")
    private String type;

    @ApiModelProperty(value = "客户名称")
    private String buyerName ;

    @ApiModelProperty(value = "部门名称")
    private String department;

    @ApiModelProperty(value = "经手人名称")
    private String handlerName;

    @ApiModelProperty(value = "制单时间")
    private Date saleOrderDate;
    //发货信息
    @ApiModelProperty(value = "物流公司")
    private  String deliveryName;

    @ApiModelProperty(value = "物流单号")
    private String deliveryCode;

    @ApiModelProperty(value = "物流备注")
    private String deliveryRemark;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    //TODO 结算及开票以下字段需要调用财务域接口获取
    @ApiModelProperty(value = "结算状态 0:未结算,1:已结算,2:部分结算")
    private Integer settlementStatus;

    @ApiModelProperty(value = "已结算金额")
    private BigDecimal amountSettled;

    @ApiModelProperty(value = "未结算金额")
    private BigDecimal unAmountSettled;

    @ApiModelProperty(value = "开票状态 0:未开票,1:已开票,2:部分开票")
    private String billingStatus;

    @ApiModelProperty(value = "已开票金额")
    private BigDecimal invoicedAmount;

    @ApiModelProperty(value = "未开票金额")
    private BigDecimal unvoicedAmount;

    @ApiModelProperty(value="发货仓库")
    private Long fromStorehouse;

    @ApiModelProperty(value = "订单备注")
    private String saleOrderRemark;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "总商品金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "促销优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "应收金额")
    private BigDecimal accrueAmount;

    @ApiModelProperty(value = "其他费用合计")
    private BigDecimal totalExpense;

    //也是制单人
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "收货地址")
    private String shippingAddress;

    /** 未找到对应字段
     分销员 待定
     */
    @ApiModelProperty(value = "发货仓库ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long deliveryWareHouseId;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提)")
    private Integer deliveryType;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "签收状态(17:待收货；19:已签收)")
    private Integer signStatus;

    @ApiModelProperty(value = "商品出库总数量")
    private Long deliveryQuantity;

    @ApiModelProperty(value = "签收人")
    private String signBy;

    @ApiModelProperty(value = "签收时间")
    private Date signTime;

    @ApiModelProperty(value = "发货计划ID")
    private Long saleDeliveryPlanId;
    @ApiModelProperty(value = "发货计划编号")
    private String saleDeliveryPlanCode;
    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;
    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;

    @ApiModelProperty(value = "操作按钮")
    private List<OrderActionResponseDTO> actions;
}