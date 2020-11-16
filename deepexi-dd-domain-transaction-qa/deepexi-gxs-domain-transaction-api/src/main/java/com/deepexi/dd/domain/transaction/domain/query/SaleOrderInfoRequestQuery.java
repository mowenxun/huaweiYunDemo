package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.List;

/**
 * SaleOrderInfoQuery
 *
 * @author admin
 * @version 1.0
 * @date Tue Jun 23 19:44:57 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单查询参数对象")
public class SaleOrderInfoRequestQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String code;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态:从业务链路获取", dataType = "Integer")
    private Integer status;
    /**
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID")
    private Long sellerId;
    /**
     * 单据来源:(agent:代客下单,mall:商城下单)
     */
    @ApiModelProperty(value = "单据来源:(agent:代客下单,mall:H5商城下单)")
    private String buyerType;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String buyerName;

    /**
     * 客户Id
     */
    @ApiModelProperty(value = "客户Id")
    private Long buyerId;
    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "订单类型,0直供订单,1标准订单，2：非标准订单，3：订货计划订单")
    private Integer ticketType;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "支付状态 0:未付款,1:已付款,2:部分付款")
    private Integer paymentStatus;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String skuName;

    /**
     * 商品code
     */
    @ApiModelProperty(value = "商品code")
    private String skuCode;


    /**
     * 排除的订单类型
     */
    @ApiModelProperty(value = "排除的订单类型")
    private List<Integer> notTickType;

    /**
     * 排除的订单类型(目前该字段用于Controller在get方式下传递notTickType数组)
     */
    @ApiModelProperty(value = "排除的订单类型")
    private String notTickTypeStr;

    @ApiModelProperty("产品线")
    private Long productId;


    @ApiModelProperty(value = "订单金额 开始")
    private BigDecimal pricePre;

    @ApiModelProperty(value = "订单金额 结束")
    private BigDecimal priceEnd;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "收货人电话")
    private String mobile;

    @ApiModelProperty(value = "收货地址")
    private String addressArea;

    @ApiModelProperty(value = "下单日期开始")
    private String createTimeFrom;

    @ApiModelProperty(value = "下单日期结束")
    private String createTimeTo;

    @ApiModelProperty(value = "要求到货日期开始")
    private String arriveDateFrom;

    @ApiModelProperty(value = "要求到货日期结束")
    private String arriveDateTo;

    @ApiModelProperty(value = "签收日期开始")
    private String signTimeFrom;

    @ApiModelProperty(value = "签收日期结束")
    private String signTimeTo;

    @ApiModelProperty(value = "单据类型多选")
    private List<Integer> ticketTypeList;

    @ApiModelProperty(value = "订单状态:从业务链路获取")
    private List<Integer> statusList;

    @ApiModelProperty(value = "支付状态 0:未付款,1:已付款,2:部分付款")
    private List<Integer> paymentStatusList;

    @ApiModelProperty(value = "单据来源:(agent:代客下单,mall:H5商城下单)")
    private List<String> buyerTypeList;

    @ApiModelProperty(value = "年份波段")
    private String year;

    @ApiModelProperty("拆单所属一级组织")
    private Long ascriptionOrgId;

    @ApiModelProperty("只显示可提货数量不为零的数据")
    private Boolean availablePickNumNotZero;

    @ApiModelProperty("提货申请时，若产品线选择其他，则进行特殊查询")
    private Boolean productLineIsOther;

    @ApiModelProperty(value = "订单编号模糊查询")
    private String likeCode;

    @ApiModelProperty(value = "客户Id")
    private Long customerId;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "计划开始月份")
    private String planMonthFrom;

    @ApiModelProperty(value = "计划结束月份")
    private String planMonthTo;

    @ApiModelProperty(value = "商品名称或商品规格模糊搜索")
    private String likeSkuNameOrSkuFormat;

//    /**
//    * 送货方式(logistics:物流配送)
//    */
//    @ApiModelProperty(value = "送货方式(logistics:物流配送)")
//    private String shippingType;
//    /**
//    * 发货仓库
//    */
//    @ApiModelProperty(value = "发货仓库")
//    private Long fromStorehouse;

    @ApiModelProperty(value = "制单人名称")
    private String handlerName;


}

