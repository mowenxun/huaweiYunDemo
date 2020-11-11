package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SalePickGoodsOrderSkuDTO
 *
 * @author admin
 * @date Thu Aug 13 07:37:26 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickGoodsOrderSkuDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 0：未逻辑删除状态，1：删除状态
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 提货订单id
     */
    private Long pickGoodsInfoId;

    /**
     * 
     */
    private Long pickGoodsOrderId;

    /**
     * 主图地址
     */
    private String majorPicture;

    /**
     * sale_order_item表的id字段
     */
    private Long saleOrderItemId;

    /**
     * 商品ID
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品编号
     */
    private String skuCode;

    /**
     * 商品规格
     */
    private String skuFormat;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 采购数量
     */
    private Long purchaseQuantity;

    /**
     * 待发货数量
     */
    private Long waitSendNum;

    /**
     * 提货数量
     */
    private Long pickNum;

    /**
     * 本次修改提货订单占用的可提货数量值（对表sale_order_item的available_pick_num字段改变的数值）
     */
    private Long lockNum;

    /**
     * 计价单位
     */
    private Long unitId;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 金额小计
     */
    private BigDecimal skuItemSubtotal;

    /**
     * 是否加急  YES加急   NO不加急
     */
    private String ifEager;

    /**
     * 仓库 新加字段 SongTao 2020/08/13 用于审批通过流程
     */
    private String warehouse;

    /**
     * 仓库出库数量
     */
    @ApiModelProperty(value = "仓库出库数量")
    private Long deliveryQuantity;

    /**
     *提货计划单编号
     */
    @ApiModelProperty(value = "提货计划单编号")
    private String pickGoodsCode;

    /**
     *要求到货时间
     */
    @ApiModelProperty(value = "要求到货时间")
    private String requiredTime;

    @ApiModelProperty(value = "行号")
    private String rowCode;
}

