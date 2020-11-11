package com.deepexi.dd.middle.order.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SaleOmsLogisticsTrajectoryDTO
 *
 * @author admin
 * @date Tue Aug 25 16:23:34 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOmsLogisticsTrajectoryDTO extends AbstractObject implements Serializable {

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
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 第三方子单号
     */
    private String subExternalOrderCode;

    /**
     * 销售订单id
     */
    private Long saleOrderId;

    /**
     * 商品ID
     */
    private Long skuItemId;

    /**
     * 是否增量 0:增量推送;1:全量推送
     */
    private Boolean whetherIncrement;

    /**
     * 物流时间
     */
    private Date time;

    /**
     * 物流轨迹描述
     */
    private String message;


}

