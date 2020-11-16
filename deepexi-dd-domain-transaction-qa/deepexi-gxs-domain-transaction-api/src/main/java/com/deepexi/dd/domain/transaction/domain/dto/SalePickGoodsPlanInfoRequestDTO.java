package com.deepexi.dd.domain.transaction.domain.dto;

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
* SalePickGoodsPlanInfoRequestDTO
*
* @author admin
* @date Wed Aug 12 19:18:27 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsPlanInfoRequestDTO")
public class SalePickGoodsPlanInfoRequestDTO extends AbstractObject implements Serializable {

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
     * 订单状态   (待付款:26、待确认:24、已驳回:5、待发货:6、 部分发货:7、交易完成:10、交易关闭:11、交易取消:12)
     */
    @ApiModelProperty(value = "订单状态   (待付款:26、待确认:24、已驳回:5、待发货:6、 部分发货:7、交易完成:10、交易关闭:11、交易取消:12)")
    private Integer status;
    /**
     * 提货时间(自提方式)、 要求送到日期(外仓、工地、小库)
     */
    @ApiModelProperty(value = "提货时间(自提方式)、 要求送到日期(外仓、工地、小库)")
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
     * 自提仓库名称
     */
    @ApiModelProperty(value = "自提仓库名称")
    private String selfPickHouseName;
    /**
     * 仓库地址
     */
    @ApiModelProperty(value = "仓库地址")
    private String warehouseAddress;
    /**
     * 仓库联系人
     */
    @ApiModelProperty(value = "仓库联系人")
    private String warehouseLinkman;
    /**
     * 仓库联系方式
     */
    @ApiModelProperty(value = "仓库联系方式")
    private String warehouseLinkway;
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
     * 商品总签收数量
     */
    @ApiModelProperty(value = "商品总签收数量")
    private Long totalSignQuantity;
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
     * 提货单所关联的订单项目
     */
    @ApiModelProperty(value = "提货单所关联的订单项目")
    private List<SalePickGoodsOrderRequestDTO> items;

    /**
     * 付款状态(待付款:26、部分付款：27、已收讫：28)
     */
    @ApiModelProperty(value = "付款状态(待付款:26、部分付款：27、已收讫：28)")
    private Integer paymentStatus;
    /**
    * 付款金额
    */
    @ApiModelProperty(value = "付款金额")
    private BigDecimal payAmount;
    /**
     * 支付类型:1线下支付,2在线支付,3信用支付,4余额支付
     */
    @ApiModelProperty(value = "支付类型:1线下支付,2在线支付,3信用支付,4余额支付")
    private Integer paymentType;
    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;
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
     * 卖家ID，顶层组织ID（供应商id）
     */
    @ApiModelProperty(value = "卖家ID，顶层组织ID（供应商id）")
    private Long sellerId;
    /**
     * 卖家名称，顶级组织名称(供应商名称)
     */
    @ApiModelProperty(value = "卖家名称，顶级组织名称(供应商名称)")
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
     * 取消提货原因
     */
    @ApiModelProperty(value = "取消/关闭提货原因")
    private String cancelCause;
    /**
     * 取消提货备注
     */
    @ApiModelProperty(value = "取消/关闭提货备注")
    private String cancelRemarks;
    /**
     * 取消类型
     */
    @ApiModelProperty(value = "取消类型  close:关闭  cancel:取消")
    private String closeType;
    /**
     * 前端传入的组织id
     */
    @ApiModelProperty(value = "前端传入的组织id")
    private Long orgId;
    /**
     * 省份编号
     */
    @ApiModelProperty(value = "省份编号")
    private String provinceCode;

    /**
     * 省份名称
     */
    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    /**
     * 城市编号
     */
    @ApiModelProperty(value = "城市编号")
    private String cityCode;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;

    /**
     * 区域编号
     */
    @ApiModelProperty(value = "区域编号")
    private String areaCode;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**
     * 街道编号
     */
    @ApiModelProperty(value = "街道编号")
    private String streetCode;

    /**
     * 街道名称
     */
    @ApiModelProperty(value = "街道名称")
    private String streetName;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;



    /**
     * 当前操作人是否是买家
     */
    @ApiModelProperty(value = "当前操作人是否是买家")
    private Boolean operaterIsBuyer;




}

