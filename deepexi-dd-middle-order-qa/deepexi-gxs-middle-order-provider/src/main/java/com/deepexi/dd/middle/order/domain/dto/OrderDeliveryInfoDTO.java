package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * OrderDeliveryInfoDTO
 *
 * @author admin
 * @date Wed Jul 01 19:40:51 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDeliveryInfoDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
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
     * 销售出库单ID
     */
    private Long saleOutTaskId;

    /**
     * 销售出库单编号
     */
    private String saleOutTaskCode;

    /**
     * 物流公司名称
     */
    private String deliveryName;

    /**
     * 物流编号
     */
    private String deliveryCode;

    /**
     * 物流投递时间
     */
    private Date deliveryTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 提货计划id
     */
    private Long salePickGoodsId;
    /**
     * 提货计划编号
     */
    private String salePickGoodsCode;


}

