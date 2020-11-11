package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * SalePickReceiveOrderYunLogDO
 *
 * @author admin
 * @version 1.0
 * @date Wed Sep 23 13:47:55 CST 2020
 */
@TableName("sale_pick_receive_order_yun_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickReceiveOrderYunLogDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 接收的消息体
     */
    private String receiveBody;

    /**
     * 提货单号
     */
    private String pickOrderCode;

    /**
     * 0000-成功
     */
    private String resultCode;

    /**
     * 操作类型  1 云仓取消订单 2 云仓发货
     */
    private Integer operationType;

}

