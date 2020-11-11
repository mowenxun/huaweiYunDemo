package com.deepexi.dd.middle.order.domain.query.operation.center;

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
 * 运营中心-提货单查询类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsInfoRequestQuery")
public class SalePickGoodsInfoOperationRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 页码
    */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
    * 页数
    */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

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
    * 提货单编号
    */
    @ApiModelProperty(value = "提货单编号")
    private String pickGoodsCode;
    /**
    * 订单状态
    */
    @ApiModelProperty(value = "订单状态")
    private Integer status;
    /**
    * 提货单要求发货时间
    */
    @ApiModelProperty(value = "提货单要求发货时间")
    private Date requiredTime;
    /**
    * 自提PICK_MYSELF，外部仓库OUTER_WAREHOUSE，工地BUILD_PLACE，小库SMALL_WAREHOUSE
    */
    @ApiModelProperty(value = "自提PICK_MYSELF，外部仓库OUTER_WAREHOUSE，工地BUILD_PLACE，小库SMALL_WAREHOUSE")
    private String pickGoodsWayZt;
    /**
    * 收货人(自提、工地、小库)
    */
    @ApiModelProperty(value = "收货人(自提、工地、小库)")
    private String receiveGoodsMan;
    /**
    * 车牌号(自提)
    */
    @ApiModelProperty(value = "车牌号(自提)")
    private String carNumberZt;
    /**
    * 联系方式(自提、工地、小库)
    */
    @ApiModelProperty(value = "联系方式(自提、工地、小库)")
    private String contactWay;
    /**
    * 提货时间(自提)
    */
    @ApiModelProperty(value = "提货时间(自提)")
    private Date pickGoodsTimeZt;
    /**
    * 收货仓库ID(外仓)
    */
    @ApiModelProperty(value = "收货仓库ID(外仓)")
    private Long receiveHouseIdWc;
    /**
    * 收货仓库名称(外仓)
    */
    @ApiModelProperty(value = "收货仓库名称(外仓)")
    private String receiveHouseNameWc;
    /**
    * 工程名称(工地)
    */
    @ApiModelProperty(value = "工程名称(工地)")
    private String projectNameGd;
    /**
    * 身份证号(自提、工地、小库)
    */
    @ApiModelProperty(value = "身份证号(自提、工地、小库)")
    private String idCardNumber;
    /**
    * 收货地址(工地、小库)
    */
    @ApiModelProperty(value = "收货地址(工地、小库)")
    private String receiveAddress;
    /**
    * 提货单商品总数
    */
    @ApiModelProperty(value = "提货单商品总数")
    private Long totalGoodsNumber;
    /**
    * 提货单商品总发货数
    */
    @ApiModelProperty(value = "提货单商品总发货数")
    private Long totalDeliveryQuantity;
    /**
    * 商品总价
    */
    @ApiModelProperty(value = "商品总价")
    private BigDecimal totalGoodsMoney;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 付款状态
    */
    @ApiModelProperty(value = "付款状态")
    private Integer paymentStatus;
    /**
    * 付款金额
    */
    @ApiModelProperty(value = "付款金额")
    private BigDecimal payAmount;
    /**
    * 隔离标识
    */
    @ApiModelProperty(value = "隔离标识")
    private String isolationId;
    /**
    * 产品线ID
    */
    @ApiModelProperty(value = "产品线ID")
    private Long productId;
    /**
    * 产品线名称
    */
    @ApiModelProperty(value = "产品线名称")
    private String productName;
    /**
    * 所属一级组织ID
    */
    @ApiModelProperty(value = "所属一级组织ID")
    private Long ascriptionOrgId;
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
    * 卖家ID，顶层组织ID
    */
    @ApiModelProperty(value = "卖家ID，顶层组织ID")
    private Long sellerId;
    /**
    * 卖家名称，顶级组织名称
    */
    @ApiModelProperty(value = "卖家名称，顶级组织名称")
    private String sellerName;
    /**
    * 买家ID，顶层组织
    */
    @ApiModelProperty(value = "买家ID，顶层组织")
    private Long buyerId;
    /**
    * 买家名称，顶级组织名称
    */
    @ApiModelProperty(value = "买家名称，顶级组织名称")
    private String buyerName;
    /**
     * 经手人
     */
    @ApiModelProperty(value = "经手人")
    private Long handler;
    /**
     * 经手人名称
     */
    @ApiModelProperty(value = "经手人名称")
    private String handlerName;
    /**
     * 过滤状态列表，不存在的状态，排除在外
     */
    @ApiModelProperty(value = "过滤状态列表")
    private List<Integer> exceptStatusList;


    @ApiModelProperty(value = "单据日期（创建时间）开始时间")
    private String createTimeFrom;

    @ApiModelProperty(value = "单据日期（创建时间）结束时间")
    private String createTimeTo;

    @ApiModelProperty(value = "要求送达时间（开始）")
    private String requiredTimeFrom;

    @ApiModelProperty(value = "要求送达时间（结束）")
    private String requiredTimeTo;
}

