package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-09-07 14:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "StockFindAllPostQuery")
public class StockFindAllPostQuery {

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    private Long appId;
    /**
     * skuID
     */
    @ApiModelProperty(value = "skuID")
    private Long skuId;

    /**
     * 根据客户授权过滤显示仓库
     * 目前提货申请页面选择仓库时用到这个参数
     */
    @ApiModelProperty(value = "pickGoodStoreHouseReq")
    PickGoodStoreHouseReq pickGoodStoreHouseReq;

    @ApiModelProperty("skuCode")
    private String skuCode;
}
