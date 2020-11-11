package com.deepexi.dd.middle.order.domain.query;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;
import java.util.Date;

/**
 * OrderOperationQuery
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderOperationQuery extends AbstractObject implements Serializable {

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
         * 名称
         */
        private String name;
        /**
         * url
         */
        private String api;
        /**
         * 类型
         */
        private String type;
        /**
         * 启用
         */
        private Boolean enable;
        /**
         * 中文名
         */
        private String chineseName;
        /**
         * 请求方式
         */
        private String method;
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

    /**
     * 操作人名字
     */
    private String createdName;
}

