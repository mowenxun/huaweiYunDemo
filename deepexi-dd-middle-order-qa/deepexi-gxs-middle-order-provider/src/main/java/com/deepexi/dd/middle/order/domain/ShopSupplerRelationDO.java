package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * ShopSupplerRelationDO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@TableName("shop_suppler_relation")
@Data
@EqualsAndHashCode(callSuper = false)
public class ShopSupplerRelationDO extends BaseDO implements Serializable {

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
     * 店铺订单id
     */
    private Long shopOrderId;

    /**
     * 店铺订单单号
     */
    private String shopOrderCode;

    /**
     * 已分发订单id
     */
    private Long supplerOrderId;

    /**
     * 已分发订单订单号
     */
    private String supplerOrderCode;



}

