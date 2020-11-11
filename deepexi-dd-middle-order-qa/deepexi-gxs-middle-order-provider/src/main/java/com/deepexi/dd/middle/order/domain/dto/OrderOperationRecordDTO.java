package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * OrderOperationRecordDTO
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderOperationRecordDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除,0否,1是
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 操作类型
     */
    private Integer operationType;
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 动作
     */
    private String operation;

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

