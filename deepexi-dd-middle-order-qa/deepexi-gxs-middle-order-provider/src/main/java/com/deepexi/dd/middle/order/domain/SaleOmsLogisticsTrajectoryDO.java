package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;



/**
 * SaleOmsLogisticsTrajectoryDO
 *
 * @author admin
 * @date Tue Aug 25 16:23:34 CST 2020
 * @version 1.0
 */
@TableName("sale_oms_logistics_trajectory")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOmsLogisticsTrajectoryDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 第三方子单号
     */
    private String subExternalOrderCode;

    /**
     * 销售订单id
     */
    private Long saleOrderId;

    /**
     * 商品ID
     */
    private Long skuItemId;

    /**
     * 是否增量 0:增量推送;1:全量推送
     */
    private Boolean whetherIncrement;

    /**
     * 物流时间
     */
    private Date time;

    /**
     * 物流轨迹描述
     */
    private String message;



}

