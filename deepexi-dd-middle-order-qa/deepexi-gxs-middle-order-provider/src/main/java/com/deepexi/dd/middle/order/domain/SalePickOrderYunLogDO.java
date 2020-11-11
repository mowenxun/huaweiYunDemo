package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * SalePickOrderYunLogDO
 *
 * @author admin
 * @date Thu Aug 27 21:37:43 CST 2020
 * @version 1.0
 */
@TableName("sale_pick_order_yun_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickOrderYunLogDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 发送的消息体
     */
    private String sendBody;

    /**
     * 提货单号
     */
    private String pickOrderCode;

    /**
     * 0000-成功
     */
    private String resultCode;



}

