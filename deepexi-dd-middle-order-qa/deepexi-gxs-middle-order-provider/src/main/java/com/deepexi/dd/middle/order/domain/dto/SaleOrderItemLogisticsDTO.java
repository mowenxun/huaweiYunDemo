package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SaleOrderItemLogisticsDTO
 *
 * @author admin
 * @date Sat Aug 22 16:34:04 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderItemLogisticsDTO extends AbstractObject implements Serializable {

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
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 第三方子单号
     */
    private String subExternalOrderCode;

    /**
     * 状态码
     */
    private String orderItemState;

    /**
     * 物流名称
     */
    private String scac;

    /**
     * 物流单号
     */
    private String trackingNo;

    private String createdBy;
    /**
     * 出库单
     */
    private String saleOutTaskCode;
    /**
     * 数据隔离ID
     */
    private String isolationId;


}

