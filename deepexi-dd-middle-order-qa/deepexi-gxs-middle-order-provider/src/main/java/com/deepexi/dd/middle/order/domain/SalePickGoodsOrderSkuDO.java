package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * SalePickGoodsOrderSkuDO
 *
 * @author admin
 * @version 1.0
 * @date Thu Aug 13 07:37:26 CST 2020
 */
@TableName("sale_pick_goods_order_sku")
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickGoodsOrderSkuDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 提货订单id
     */
    private Long pickGoodsInfoId;

    /**
     * 表sale_pick_goods_order的id
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
     * 仓库
     */
    private String warehouse;
    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 仓库出库数量
     */
    private Long deliveryQuantity;

    /**
     * 是否加急  YES加急   NO不加急
     */
    private String ifEager;

    //行号
    private String rowCode;

}

