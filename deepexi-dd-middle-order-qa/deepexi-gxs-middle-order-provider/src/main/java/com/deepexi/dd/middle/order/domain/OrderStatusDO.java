package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * OrderStatusDO
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@TableName("order_status")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderStatusDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 标识
     */
    private String identity;

    /**
     * 状态名
     */
    private String statusName;

    /**
     * 启用
     */
    private String enable;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;



}

