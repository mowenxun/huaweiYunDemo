package com.deepexi.dd.middle.order.domain.query;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;

import java.util.Date;

/**
 * SaleDeliveryPlanMainQuery
 *
 * @author admin
 * @version 1.0
 * @date Thu Aug 13 15:26:43 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryPlanMainQuery extends AbstractObject implements Serializable {

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
     * APP标识
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
     * 创建人
     */
    private String createdBy;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 编排编号
     */
    private String code;
    /**
     * 编排数量
     */
    private Long quantity;
}

