package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;



/**
 * SalePickGoodsOrderDO
 *
 * @author admin
 * @date Wed Aug 12 22:17:35 CST 2020
 * @version 1.0
 */
@TableName("sale_pick_goods_order")
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickGoodsOrderDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 销售订单id
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

    /**
     * 提货单信息id
     */
    private Long pickGoodsInfoId;

    /**
     * 单据类型(1:普通销售单,0:直供订单,2:非标准订单;3:订货计划单)
     */
    private String orderType;

    /**
     * 商品数量
     */
    private Long goodsNumber;

    /**
     * 该订单sku金额
     */
    private BigDecimal goodsMoney;



}

