package com.deepexi.dd.middle.order.domain.query;

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
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
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
     * 创建时间开始
     */

    private Date createdTime;

    private String createTimeFrom;

    /**
     * 创建时间结束
     */

    private String createTimeTo;
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

    private Integer notStatus;
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
     * 商品总数
     */
    private Long quantity;
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

    /**
     * 隔离标识
     */
    private String isolationId;
    /**
     * 经手人名称
     */
    private String handlerName;
    /**
     * 部门名称
     */
    private String department;
    /**
     * 已支付金额
     */
    private BigDecimal payAmount;
    /**
     * 总发货数量
     */
    private Long totalQuantity;

    /**
     * 总签收数量
     */
    private Long totalSignQuantity;

    /**
     * 总提货数量
     */
    private Long pickQuantity;

    /**
     * 审核状态（13未审核 14已审核）
     */
    private Integer verifyStatus;

    /**
     * 接单状态（4未接单 16已接单）
     */
    private Integer acceptStatus;

    private Long partnerId;

    private String partnerName;

    /**
     * 订单ids
     */
    private List<Long> ids;

    /**
     * 过滤状态列表
     */
    private List<Integer> exceptStatusList;

    /**
     * 付款状态
     */
    private List<Integer> paymentStatusList;
    /**
     * 排除的订单类型
     */
    private List<Integer> notTickType;

    /**
     * 拆单所属组织
     */
    @ApiModelProperty("拆单所属一级组织")
    private Long ascriptionOrgId;


    /**
     * 订单金额 开始
     */
    private BigDecimal pricePre;

    /**
     * 订单金额 结束
     */
    private BigDecimal priceEnd;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收货人电话
     */
    private String mobile;

    /**
     * 收货地址
     */
    private String addressArea;

    /**
     * 要求到货日期开始
     */
    private String arriveDateFrom;

    /**
     * 要求到货日期结束
     */
    private String arriveDateTo;

    /**
     * 签收日期开始
     */
    private String signTimeFrom;

    /**
     * 签收日期结束
     */
    private String signTimeTo;

    /**
     * 单据类型多选
     */
    private List<Integer> ticketTypeList;

    /**
     * 订单状态:从业务链路获取
     */
    private List<Integer> statusList;

    /**
     * 单据来源:(agent:代客下单,mall:H5商城下单)
     */
    private List<String> buyerTypeList;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 区级编码
     */
    private String areaCode;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份波段
     */
    private String monthBand;
    /**
     * 年份波段
     */
    private String yearBand;

    /**
     * 产品线ID
     */
    private Long productId;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 父订单ID
     */
    private Long parentSaleOrderId;

    /**
     * 父订单编号
     */
    private String parentSaleOrderCode;

    /**
     * 只显示可提货数量不为零的数据
     */
    private Boolean availablePickNumNotZero;

    /**
     * 合同ID
     */
    private Long contractId;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 提货申请时，若产品线选择其他，则进行特殊查询
     */
    private Boolean productLineIsOther;

    /**
     * 订单编号模糊查询
     */
    private String likeCode;

    /**
     * 计划开始月份
     */
    private String planMonthFrom;

    /**
     * 计划结束月份
     */
    private String planMonthTo;

    /**
     * 商品名称或商品规格模糊搜索
     */
    private String likeSkuNameOrSkuFormat;

}

