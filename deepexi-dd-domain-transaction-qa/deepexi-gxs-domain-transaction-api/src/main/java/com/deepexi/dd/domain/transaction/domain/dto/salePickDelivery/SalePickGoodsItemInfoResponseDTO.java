package com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SalePickGoodsItemInfoResponseDTO
 * @Description 提货单发货详情里的商品列表
 * @Author SongTao
 * @Date 2020-08-21
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsItemInfoResponseDTO")
public class SalePickGoodsItemInfoResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 450901278666280087L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value="主图地址")
    private String majorPicture;

    @ApiModelProperty(value = "商品ID")
    private Long skuId;

    @ApiModelProperty(value = "商品编号")
    private String skuCode;

    @ApiModelProperty(value = "商品名称")
    private String skuName;

    @ApiModelProperty(value = "商品规格")
    private String skuFormat;

    @ApiModelProperty("单位")
    private String unitName;

    @ApiModelProperty(value = "商品本次申请提货数量")
    private Long pickNum;

    @ApiModelProperty(value = "待出库数量")
    private Long waitSendNum;

    @ApiModelProperty("仓库出库数量")
    private Long deliveryQuantity;

    @ApiModelProperty("已出库数量")
    private Long deliveryQuantityed;
}
