package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* SaleDeliveryPlanItemQuery
*
* @author admin
* @date Thu Aug 13 16:42:15 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryPlanItemRequestQuery")
public class SaleDeliveryPlanItemRequestQuery extends AbstractObject implements Serializable {

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
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
    /**
    * 发货计划ID
    */
    @ApiModelProperty(value = "发货计划ID")
    private Long saleDeliveryPlanId;
    /**
    * 发货计划编号
    */
    @ApiModelProperty(value = "发货计划编号")
    private String saleDeliveryPlanCode;
    /**
    * 提货订单id
    */
    @ApiModelProperty(value = "提货订单id")
    private Long pickGoodsInfoId;
    /**
    * 提货单编号
    */
    @ApiModelProperty(value = "提货单编号")
    private Long pickGoodsOrderId;
    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
    * 客户编号
    */
    @ApiModelProperty(value = "客户编号")
    private String customerCode;
    /**
    * 地址
    */
    @ApiModelProperty(value = "地址")
    private String address;
    /**
    * 价格
    */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    /**
    * 主图地址
    */
    @ApiModelProperty(value = "主图地址")
    private String majorPicture;
    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID")
    private Long skuId;
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
    private BigDecimal skuPrice;
    /**
    * 计划数量
    */
    @ApiModelProperty(value = "计划数量")
    private Long planQuantity;
    /**
    * 特价价格
    */
    @ApiModelProperty(value = "特价价格")
    private BigDecimal specialPrice;
    /**
    * 特价价格编号
    */
    @ApiModelProperty(value = "特价价格编号")
    private String specialPriceCode;
    /**
    * 仓库编号
    */
    @ApiModelProperty(value = "仓库编号")
    private Long warehouseCode;
    /**
    * 部门
    */
    @ApiModelProperty(value = "部门")
    private Long department;
    /**
    * ZT/WQ
    */
    @ApiModelProperty(value = "ZT/WQ")
    private Long ztWq;

    @ApiModelProperty(value = "商品名称")
    private String skuName;
}

