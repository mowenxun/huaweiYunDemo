package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单信息，在线订购
 *
 * @author admin
 * @date Tue Jun 23 19:44:57 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单信息，在线订购")
public class SaleOrderInfoOLResponseDTO extends AbstractObject implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID",dataType = "Long")
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
    @ApiModelProperty(value = "订单备注",dataType = "String")
    private String remark;

    /**
     * 0：未逻辑删除状态。1:删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",dataType = "Date")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",dataType = "Date")
    private Date updatedTime;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号",dataType = "String")
    private String code;

    /**
     * 订单状态:从工具域获取
     */
    @ApiModelProperty(value="订单状态:从工具域获取",dataType = "Integer")
    private Integer status;

    /**
     * 供货商ID
     */
    @ApiModelProperty(value="供货商ID",dataType = "Long")
    private Long sellerId;

    /**
     * 供货商名称
     */
    @ApiModelProperty(value="供货商名称",dataType = "String")
    private String sellerName;

    /**
     * 客户ID
     */
    @ApiModelProperty(value="客户ID",dataType = "Long")
    private Long buyerId;

    /**
     * 下单类型:(agent:代客下单,mall:h5商城下单)
     */
    @ApiModelProperty(value="下单类型:(agent:代客下单,mall:h5商城下单)",dataType = "String")
    private String buyerType;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称",dataType = "String")
    private String buyerName;

    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value="订单类型,0直供订单,1标准订单，2：非标准订单，3：订货计划订单")
    private Integer ticketType;

    /**
     * 送货方式(logistics:物流配送)
     */
    @ApiModelProperty(value="送货方式(logistics:物流配送)",dataType = "String")
    private String shippingType;

    /**
     * 出库状态 0:未出库,1已出库
     */
    @ApiModelProperty(value="出库状态 0:未出库,1已出库",dataType = "Integer")
    private Integer shipmentStatus;

    /**
     * 发货仓库
     */
    @ApiModelProperty(value="发货仓库",dataType = "Long")
    private Long fromStorehouse;

    /**
     * 经手人
     */
    @ApiModelProperty(value="经手人ID",dataType = "Long")
    private Long handler;

    /**
     * 经手人名称
     */
    @ApiModelProperty(value="经手人名称",dataType = "String")
    private String handlerName;

    /**
     * 单据日期
     */
    @ApiModelProperty(value="单据日期",dataType = "Date")
    private Date ticketDate;

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
     * 促销优惠金额
     */
    @ApiModelProperty(value="促销优惠金额")
    private BigDecimal discountAmount;

    /**
     * 应收金额
     */
    @ApiModelProperty(value="应收金额")
    private BigDecimal accrueAmount;

    /**
     * 费用小计
     */
    @ApiModelProperty(value="费用小计")
    private BigDecimal totalExpense;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人",dataType = "String")
    private String createdBy;

    /**
     * 更新人
     */
    @ApiModelProperty(value="更新人",dataType = "String")
    private String updatedBy;

    /**
     * 支付类型:1线下支付,2信用支付
     */
    @ApiModelProperty(value="支付类型:1线下支付,2信用支付",dataType = "Integer")
    private Integer paymentType;

    /**
     * 结算状态 0:未结算,1:已结算,2:部分结算
     */
    @ApiModelProperty(value="结算状态 0:未结算,1:已结算,2:部分结算",dataType = "Integer")
    private Integer settlementStatus;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value="支付状态 0:未付款,1:已付款,2:部分付款",dataType = "Integer")
    private Integer paymentStatus;

    /**
     * 要求送达日期
     */
    @ApiModelProperty(value="要求送达日期",dataType = "Date")
    private Date arriveDate;
    /**
     * 部门名称
     */
    @ApiModelProperty(value="部门名称",dataType = "String")
    private String department;
    /**
     * 已支付金额
     */
    @ApiModelProperty(value="已支付金额",dataType = "BigDecimal")
    private BigDecimal payAmount;
    /**
     * 总发货数量
     */
    @ApiModelProperty(value = "总发货数量")
    private Long totalQuantity;

    /**
     * 总签收数量
     */
    @ApiModelProperty(value = "总签收数量")
    private Long totalSignQuantity;

    /**
     * 总提货数量
     */
    @ApiModelProperty(value = "总提货数量")
    private Long pickQuantity;

    /**
     * 审核状态（13未审核 14已审核）
     */
    @ApiModelProperty(value = "审核状态（13未审核 14已审核）")
    private Integer verifyStatus;

    /**
     * 接单状态（4未接单 16已接单）
     */
    @ApiModelProperty(value = "接单状态（4未接单 16已接单）")
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

    @ApiModelProperty(value = "拆单所属组织")
    private Long ascriptionOrgId;

    @ApiModelProperty(value = "商品明细列表")
    private List<SaleOrderItemMiddleResponseDTO> items;

    @ApiModelProperty(value = "出库单")
    private List<SaleOutTaskMiddleResponseDTO> saleOutTaskList;

    //产品线id
    private Long productId;

    //计划月份
    private String planMonth;

    //产品线名称
    private String productName;

    @ApiModelProperty(value = "支付流水code")
    private String payOrderCode;


}

