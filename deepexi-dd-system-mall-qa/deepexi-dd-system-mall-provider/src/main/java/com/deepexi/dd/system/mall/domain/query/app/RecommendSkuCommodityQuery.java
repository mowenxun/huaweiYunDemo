package com.deepexi.dd.system.mall.domain.query.app;

import com.deepexi.dd.system.mall.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-09 9:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RecommendSkuCommodityQuery extends BaseQuery implements Serializable {

    /**
     * 店铺ID
     */
    @NotNull(message = "店铺ID不能为空")
    @ApiModelProperty("店铺ID")
    private Long shopId;

    /**
     * 直供上架状态（0未上架 1已上架 2已下架）
     */
    @NotNull(message = "直供上架状态 不能为空")
    @ApiModelProperty(value = "直供上架状态（0未上架 1已上架 2已下架）")
    private Integer onStatus;

    /**
     * 是否首页推荐 1:推荐, 0:不推荐
     */
    @ApiModelProperty("是否首页推荐 1:推荐, 0:不推荐")
    private Integer isRecommend;

}