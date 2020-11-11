package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 18:50
 */
@TableName("activity_sku_order_quantity")
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivitySkuOrderQuantityDO extends BaseDO implements Serializable {

    private Long id;

    private Long appId;

    private String tenantId;
    /**
     * 合作伙伴id
     */
    private Long partnerId;

    /**
     * 活动id
     */
    private Long activitiesId;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 已下单数量
     */
    private Long quantityOrdered;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 备注
     */
    private String remark;

}
