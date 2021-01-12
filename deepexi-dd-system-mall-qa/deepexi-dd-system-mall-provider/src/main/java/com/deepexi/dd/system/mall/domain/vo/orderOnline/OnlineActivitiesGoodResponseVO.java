package com.deepexi.dd.system.mall.domain.vo.orderOnline;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName OnlineActivitiesGoodResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-19
 * @Version 1.0
 **/
@Data
@ApiModel
public class OnlineActivitiesGoodResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = 1939615527459708876L;

    @ApiModelProperty("活动商品id")
    private Long id;

    @ApiModelProperty(value = "商品skuId")
    private Long skuId;

    @ApiModelProperty(value = "活动价格")
    private BigDecimal activitiesPrice;

    @ApiModelProperty(value = "活动数量")
    private Integer availableQuantity;

    @ApiModelProperty(value = "是否限购")
    private Boolean isLimited;

    @ApiModelProperty("活动id")
    private Long onlineActivitiesId;

    @ApiModelProperty(value = "商品itemId")
    private Long itemId;

    @ApiModelProperty("限购数量")
    private Integer limitNum;

    @ApiModelProperty("商品编码")
    private String spuCode;

    @ApiModelProperty("商品名称")
    private String spuName;

    @ApiModelProperty("商品规格")
    private String skuQuantity;

    @ApiModelProperty("商品sku编码")
    private String skuCode;

    @ApiModelProperty("活动上架状态（0未上架  1已上架 2已下架）")
    private Integer onStatus;
}
