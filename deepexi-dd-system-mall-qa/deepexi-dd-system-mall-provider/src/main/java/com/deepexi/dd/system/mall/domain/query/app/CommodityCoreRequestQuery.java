package com.deepexi.dd.system.mall.domain.query.app;

import com.deepexi.dd.system.mall.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 商品详情请求参数
 *
 * @version 1.0
 * @date 2020-07-013 11:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommodityCoreRequestQuery extends BaseQuery implements Serializable {

    /**
     * itemId
     */
    @NotNull(message = "itemId 不能为空")
    @ApiModelProperty("itemId")
    private Long itemId;

    /**
     * 商铺ID
     */
    @ApiModelProperty("商铺ID")
    @NotNull(message = "商铺ID 不能为空")
    private Long shopId;

    /**
     * 直供上架状态（0未上架 1已上架 2已下架）
     */
    @ApiModelProperty(value = "直供上架状态（0未上架 1已上架 2已下架）")
    private Integer onStatus;

    /**
     * 过滤授权sku
     */
    @ApiModelProperty(value = "过滤授权")
    private List<Long> skuIdList;
}
