package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.dd.middle.order.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 19:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "ActivitySkuOrderQuantityQuery")
public class ActivitySkuOrderQuantityQuery {

    @ApiModelProperty(value = "id")
    private Long id;

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
    private List<Long> skuIds;



    /**
     * 订单编码
     */
    @ApiModelProperty(value = "订单编码")
    private String orderCode;
}
