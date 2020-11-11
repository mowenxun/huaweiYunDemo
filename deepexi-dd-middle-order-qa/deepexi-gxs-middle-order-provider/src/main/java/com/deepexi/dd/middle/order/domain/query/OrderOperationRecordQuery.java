package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * OrderOperationRecordQuery
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderOperationRecordQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 页数
     */
    private Integer size = 10;

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
     * 删除标记
     */
    private Integer isDeleted;
    /**
     * 销售订单编号
     */
    private String  saleOrderCode;

    /**
     * 操作Code
     */
    private String actionCode;
    /**
     * 取消原因
     */
    private String radioName;
}

