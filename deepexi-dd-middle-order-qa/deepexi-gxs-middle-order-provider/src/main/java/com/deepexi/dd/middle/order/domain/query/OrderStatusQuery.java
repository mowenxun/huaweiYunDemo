package com.deepexi.dd.middle.order.domain.query;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;
import java.util.Date;

/**
 * OrderStatusQuery
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderStatusQuery extends AbstractObject implements Serializable {

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
         * 
         */
        private Integer id;
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
         * 租户ID
         */
        private String tenantId;
        /**
         * 应用ID
         */
        private Long appId;
        /**
         * 创建时间
         */
        private Date createdTime;
        /**
         * 更新时间
         */
        private Date updatedTime;
        /**
         * 创建人
         */
        private String createdBy;
        /**
         * 更新人
         */
        private String updatedBy;
}

