package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.dd.system.mall.domain.TenantDTO;
import com.deepexi.dd.system.mall.domain.dto.add.SaleOrderItemAddRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.order.OrderSelfRaisingInfoAddRequestDTO;
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
public class SaleOrderInfoAppAddRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 下单类型:(agent:代客下单,mall:商城下单)
     */
    @ApiModelProperty(value = "下单类型:(ValetOrder:代客下单,OnlineOrder:商城下单),当前默认填OnlineOrder",required = true)
    @NotEmpty(message = "下单类型为空")
    private String buyerType;

    /**
     * 订单类型,0直供订单,1标准订单
     */
    @ApiModelProperty(value = "订单类型,0直供订单,1标准订单，2：非标准订单，3：订货计划订单",required = true)
    @NotNull(message = "订单类型为空")
    private Integer ticketType;
    /**
     * 送货方式(logistics:物流配送)
     */
    @ApiModelProperty(value = "送货方式(logistics:物流配送),默认填logistics",required = true)
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



    @ApiModelProperty(value = "支付类型:1线下支付,2在线支付,3信用支付,4余额支付")
    private Integer paymentType;

    @ApiModelProperty(value = "要求送达日期",required = true)
    private Date arriveDate;
    @ApiModelProperty(value = "操作类型:1提交,0保存草稿",required = true)
    @NotNull(message = "提交类型错误")
    @Range(min = 0,max = 1,message = "提交类型错误")
    private Integer type;

    @ApiModelProperty(value = "订单送货地址添加对象",required = true)
    private OrderConsigneeInfoAddRequestDTO orderConsigneeInfo;
    @ApiModelProperty(value = "订单自提地址添加对象",required = true)
    private OrderSelfRaisingInfoAddRequestDTO orderSelfRaisingInfoAddInfo;
    @ApiModelProperty(value = "优惠券列表,如果为空，则提交空数组",required = true)
    private List<Long> orderCouponIds;
    @ApiModelProperty(value = "促销活动列表,如果为空，则提交空数组",required = true)
    private List<Long> orderPromotionIds;
    @ApiModelProperty(value = "商品明细列表,必填",required = true)
    @Size(min = 1,message = "未选择商品信息")
    private List<SaleOrderItemAddRequestDTO> items;
    @ApiModelProperty(value = "开票信息",required = true)
    private OrderInvoiceInfoRequestDTO orderInvoiceInfo;
    @ApiModelProperty(value = "订单费用信息列表,如果为空，填空数组",required = true)
    private List<OrderExpenseInfoRequestDTO> orderExpenseInfo;
    @ApiModelProperty(value = "备注",required = false)
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
     * 送货方式
     */
    @ApiModelProperty(value = "配送方式 0=送货 1=自提")
    private Integer deliveryType;

    /**
     * 支付流水关联订单号
     */
    @ApiModelProperty(value = "支付流水关联订单号")
    private String payOrderCode;

}
