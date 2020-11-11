package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
* SalePickGoodsOrderSkuQuery
*
* @author admin
* @date Thu Aug 13 07:37:26 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsOrderSkuRequestQuery")
public class SalePickGoodsOrderSkuRequestQuery extends AbstractObject implements Serializable {

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
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
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
    * 提货订单id
    */
    @ApiModelProperty(value = "提货订单id")
    private Long pickGoodsInfoId;
    /**
     * 提货订单id列表 目前用于根据多个提货找出sku
     */
    @ApiModelProperty(value = "提货订单id列表")
    private List<Long> pickGoodsInfoIdList;
    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long pickGoodsOrderId;
    /**
    * 主图地址
    */
    @ApiModelProperty(value = "主图地址")
    private String majorPicture;
    /**
     * sale_order_item表的id字段
     */
    @ApiModelProperty(value = "sale_order_item表的id字段")
    private Long saleOrderItemId;
    /**
     * sale_order_item表的id字段列表
     */
    @ApiModelProperty(value = "sale_order_item表的id字段列表")
    private List<Long> saleOrderItemIdList;
    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID")
    private Long skuId;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    /**
    * 商品编号
    */
    @ApiModelProperty(value = "商品编号")
    private String skuCode;
    /**
    * 商品规格
    */
    @ApiModelProperty(value = "商品规格")
    private String skuFormat;
    /**
    * 单价
    */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    /**
    * 采购数量
    */
    @ApiModelProperty(value = "采购数量")
    private Long purchaseQuantity;
    /**
    * 待发货数量
    */
    @ApiModelProperty(value = "待发货数量")
    private Long waitSendNum;
    /**
    * 提货数量
    */
    @ApiModelProperty(value = "提货数量")
    private Long pickNum;
    /**
     * 本次占用的可提货数量值（对表sale_order_item的available_pick_num字段改变的数值）
     */
    @ApiModelProperty(value = "本次占用的可提货数量值（对表sale_order_item的available_pick_num字段改变的数值）")
    private Long lockNum;

    /**
    * 计价单位
    */
    @ApiModelProperty(value = "计价单位")
    private Long unitId;
    /**
    * 单位名称
    */
    @ApiModelProperty(value = "单位名称")
    private String unitName;
    /**
    * 金额小计
    */
    @ApiModelProperty(value = "金额小计")
    private BigDecimal skuItemSubtotal;
    /**
    * 是否加急  YES加急   NO不加急
    */
    @ApiModelProperty(value = "是否加急  YES加急   NO不加急")
    private String ifEager;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;

    /**
     * 提货单编号
     */
    @ApiModelProperty(value = "提货单编号")
    private String pickGoodsCode;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String contactWayZt;

    /**
     * 自提PICK_MYSELF，外部仓库OUTER_WAREHOUSE，工地BUILD_PLACE，小库SMALL_WAREHOUSE
     */
    @ApiModelProperty(value = "提货方式:自提PICK_MYSELF，外部仓库OUTER_WAREHOUSE，工地BUILD_PLACE，小库SMALL_WAREHOUSE")
    private String pickGoodsWayZt;

    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String consignee;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String  receiveAddress;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "提货计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdStartTime;

    @ApiModelProperty(value = "提货计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String requiredTime;

    @ApiModelProperty(value = "提货商品创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String  startTime;

    @ApiModelProperty(value = "提货商品结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String  endTime;

    @ApiModelProperty(value = "所属一级组织ID")
    private Long ascriptionOrgId;

    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    @ApiModelProperty(value = "订单状态 (待付款:26、待确认:24、已驳回:5、待发货:6、 部分发货:7、交易完成:10、交易关闭:11、交易取消:12)")
    private Integer  status;
}

