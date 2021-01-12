package com.deepexi.dd.system.mall.domain.query.orderOnline;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName OnlineActivitiesGoodRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-20
 * @Version 1.0
 **/
@Data
@ApiModel
public class OnlineActivitiesGoodRequestQuery extends BaseRequestDTO {

    @ApiModelProperty(value = "活动id", required = true)
    @NotNull(message = "活动id不能为空.")
    private Long activitiesId;

    @ApiModelProperty(value = "sku的ids")
    private List<Long> skuIds;

    @ApiModelProperty("itemId")
    private Long itemId;

    @ApiModelProperty("商铺ID")
    private Long shopId;

    @ApiModelProperty(value = "直供上架状态（0未上架 1已上架 2已下架）")
    private Integer onStatus;
}
