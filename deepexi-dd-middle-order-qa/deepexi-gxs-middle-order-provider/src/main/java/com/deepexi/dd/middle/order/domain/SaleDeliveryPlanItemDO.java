package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;



/**
 * SaleDeliveryPlanItemDO
 *
 * @author admin
 * @date Thu Aug 13 16:42:15 CST 2020
 * @version 1.0
 */
@TableName("sale_delivery_plan_item")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryPlanItemDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 发货计划ID
     */
    private Long saleDeliveryPlanId;

    /**
     * 发货计划编号
     */
    private String saleDeliveryPlanCode;

    /**
     * 提货订单id
     */
    private Long pickGoodsInfoId;

    /**
     * 提货单编号
     */
    private Long pickGoodsOrderId;

    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

    /**
     * 客户编号
     */
    private String customerCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 主图地址
     */
    private String majorPicture;

    /**
     * 商品ID
     */
    private Long skuId;

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
    private BigDecimal skuPrice;

    /**
     * 计划数量
     */
    private Long planQuantity;

    /**
     * 特价价格
     */
    private BigDecimal specialPrice;

    /**
     * 特价价格编号
     */
    private String specialPriceCode;

    /**
     * 仓库编号
     */
    private Long warehouseCode;

    /**
     * 部门
     */
    private Long department;

    /**
     * ZT/WQ
     */
    private Long ztWq;

    /**
     * skuName
     */
    private String skuName;


}

