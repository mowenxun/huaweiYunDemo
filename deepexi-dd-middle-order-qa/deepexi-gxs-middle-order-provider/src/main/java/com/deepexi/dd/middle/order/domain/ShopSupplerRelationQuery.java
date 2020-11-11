package com.deepexi.dd.middle.order.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ShopSupplerRelationQuery
 *
 * @author admin
 * @version 1.0
 * @date Tue Oct 13 15:15:24 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShopSupplerRelationQuery extends AbstractObject implements Serializable {

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
     * 店铺订单id
     */
    private Long shopOrderId;
    /**
     * 店铺订单单号
     */
    private String shopOrderCode;
    /**
     * 已分发订单id
     */
    private Long supplerOrderId;
    @ApiModelProperty(value = "已分发订单ids")
    private List<Long> supplerOrderIds;
    /**
     * 已分发订单订单号
     */
    private String supplerOrderCode;

    /**
     * 店铺订单id
     */
    @ApiModelProperty(value = "店铺订单id集合")
    private List<Long> shopOrderIds;
}

