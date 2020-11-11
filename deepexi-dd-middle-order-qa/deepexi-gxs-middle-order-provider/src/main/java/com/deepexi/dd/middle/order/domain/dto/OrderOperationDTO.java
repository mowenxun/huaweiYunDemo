package com.deepexi.dd.middle.order.domain.dto;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * OrderOperationDTO
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderOperationDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;

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
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;


}

