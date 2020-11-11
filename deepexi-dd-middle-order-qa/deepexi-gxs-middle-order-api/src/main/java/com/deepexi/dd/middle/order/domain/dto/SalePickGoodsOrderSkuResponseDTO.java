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
 * SalePickGoodsOrderSkuDTO
 *
 * @author admin
 * @version 1.0
 * @date Thu Aug 13 07:37:26 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsOrderSkuResponseDTO")
public class SalePickGoodsOrderSkuResponseDTO extends AbstractObject implements Serializable {

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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 0：未逻辑删除状态，1：删除状态
     */
    @ApiModelProperty(value = "0：未逻辑删除状态，1：删除状态")
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

    @ApiModelProperty(value = "仓库")
    private String warehouse;

    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    @ApiModelProperty(value = "仓库出库数量")
    private Long deliveryQuantity;

    @ApiModelProperty(value = "提货计划单编号")
    private String pickGoodsCode;

    @ApiModelProperty(value = "要求到货时间")
    private String requiredTime;

    @ApiModelProperty(value = "行号")
    private String rowCode;

    @ApiModelProperty(value = "剩余的可申请提货数量,目前该字段仅用于详情接口返回")
    private Long availablePickNum;



}

