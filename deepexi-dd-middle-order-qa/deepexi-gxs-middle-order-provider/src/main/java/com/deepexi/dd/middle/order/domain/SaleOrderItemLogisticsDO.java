package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;



/**
 * SaleOrderItemLogisticsDO
 *
 * @author admin
 * @date Sat Aug 22 16:34:04 CST 2020
 * @version 1.0
 */
@TableName("sale_order_item_logistics")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderItemLogisticsDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方子单号
     */
    private String subExternalOrderCode;

    /**
     * 状态码
     */
    private String orderItemState;

    /**
     * 物流名称
     */
    private String scac;

    /**
     * 物流单号
     */
    private String trackingNo;

    /**
     * 创建人
     */
    private String createdBy;


}

