package com.deepexi.dd.middle.order.domain.dto;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * SaleDeliveryPlanMainDTO
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryPlanMainDTO extends AbstractObject implements Serializable {

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
     * 0：未逻辑删除状态。1:删除
     */
    private Boolean deleted;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

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

