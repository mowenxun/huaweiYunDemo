package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * OrderOperationDO
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@TableName("order_operation")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderOperationDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;



}

