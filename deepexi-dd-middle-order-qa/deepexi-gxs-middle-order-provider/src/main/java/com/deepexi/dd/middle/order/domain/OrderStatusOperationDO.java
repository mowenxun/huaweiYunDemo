package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * OrderStatusOperationDO
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@TableName("order_status_operation")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderStatusOperationDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String statusIdentity;

    /**
     * 
     */
    private Long statusId;

    /**
     * 
     */
    private Long operationId;

    /**
     * 
     */
    private String operationName;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;

    private String portal;



}

