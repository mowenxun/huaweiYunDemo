package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;



/**
 * OrderRefundSkuDO
 *
 * @author admin
 * @date Wed Aug 19 16:31:13 CST 2020
 * @version 1.0
 */
@TableName("order_refund_sku")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderRefundSkuDO extends BaseDO implements Serializable {

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
     * 数据隔离id
     */
    private String isolationId;

    /**
     * 主图地址
     */
    private String majorPicture;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品规格
     */
    private String skuFormat;

    /**
     * 商品编号
     */
    private String skuCode;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private Long skuQuantity;

    /**
     * 商品出库总数量
     */
    private Long skuTotalQuantity;

    /**
     * 退款单id
     */
    private Long refundOrderId;



}

