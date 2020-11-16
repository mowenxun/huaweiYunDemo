package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.dto.add.SaleOrderItemAddRequestDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售订单添加和编辑对象
 *
 * @author admin
 * @date Wed Jun 24 11:00:03 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单添加和编辑对象")
public class SaleOrderInfoRequestDTO extends AbstractObject implements Serializable {

    @ApiModelProperty(value = "租户id")
    private String tenantId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "Id,如果是为创建直接提交则为空,如果是已经保存为草稿提交则不为空")
    private Long id;

    /**
     * 下单类型:(agent:代客下单,mall:商城下单)
     */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:商城下单),当前默认填agent",required = true)
    @NotEmpty(message = "下单类型为空")
    private String buyerType;

    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单,direct:直供订单)",required = true)
    @NotNull(message = "单据类型为空")
    private Integer ticketType;
    /**
     * 送货方式(logistics:物流配送)
     */
    @ApiModelProperty(value = "送货方式(logistics:物流配送)")
    private String shippingType;
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
     * 其他费用合计
     */
    @ApiModelProperty(value = "其他费用合计")
    private BigDecimal totalExpense;


    @ApiModelProperty(value = "支付类型:1线下支付,2在线支付,3信用支付,4余额支付",required = true)
    private Integer paymentType;

    @ApiModelProperty(value = "要求送达日期")
    private Date arriveDate;

    @ApiModelProperty(value = "操作类型:1提交,0保存草稿",required = true)
    @NotNull(message = "提交类型错误")
    @Range(min = 0,max = 1,message = "提交类型错误")
    private Integer type;

    @ApiModelProperty(value = "订单送货地址添加对象")
    private OrderConsigneeInfoAddRequestDTO orderConsigneeInfo;

    @ApiModelProperty(value = "订单自提地址添加对象",required = true)
    private OrderSelfRaisingInfoAddRequestDTO orderSelfRaisingInfoAddInfo;

    @ApiModelProperty(value = "优惠券列表")
    private List<Long> orderCouponIds;

    @ApiModelProperty(value = "促销活动列表")
    private List<Long> orderPromotionIds;

    @ApiModelProperty(value = "商品明细列表")
    @Size(min = 1,message = "未选择商品信息")
    private List<SaleOrderItemAddRequestDTO> items;

    @ApiModelProperty(value = "开票信息")
    private OrderInvoiceInfoRequestDTO orderInvoiceInfo;

    @ApiModelProperty(value = "订单费用信息列表")
    private List<OrderExpenseInfoRequestDTO> orderExpenseInfo;
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "产品线id")
    private Long productId;

    @ApiModelProperty(value = "计划月份")
    private String planMonth;

    @ApiModelProperty(value = "产品线名称")
    private String productName;

    /**
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id")
    private Long sellerId;
    /**
     * 供应商名称
     */
    @ApiModelProperty(value = "供应商名称")
    private String sellerName;

    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

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
}
