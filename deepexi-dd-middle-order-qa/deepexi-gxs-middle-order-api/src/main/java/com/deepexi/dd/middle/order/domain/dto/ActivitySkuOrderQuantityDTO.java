package com.deepexi.dd.middle.order.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 19:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "ActivitySkuOrderQuantityDTO")
public class ActivitySkuOrderQuantityDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "appId")
    private Long appId;

    @ApiModelProperty(value = "tenantId")
    private String tenantId;

    /**
     * 合作伙伴id
     */
    @ApiModelProperty(value = "合作伙伴id")
    private Long partnerId;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Long activitiesId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long skuId;

    /**
     * 已下单数量
     */
    @ApiModelProperty(value = "已下单数量")
    private Long quantityOrdered;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    /**
     * 订单编码
     */
    @ApiModelProperty(value = "订单编码")
    private String orderCode;
}
