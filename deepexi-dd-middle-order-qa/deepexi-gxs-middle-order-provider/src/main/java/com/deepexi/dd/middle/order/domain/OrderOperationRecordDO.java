package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * OrderOperationRecordDO
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
@TableName("order_operation_record")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderOperationRecordDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 动作
     */
    private String operation;

    /**
     * 操作类型
     */
    private Integer operationType;

    /**
     * 操作人名字
     */
    private String createdName;

    /**
     * 操作Code
     */
    private String actionCode;
    /**
     * 取消原因
     */
    private String radioName;
}

