package com.deepexi.dd.system.mall.domain.vo.saleorder;

import com.deepexi.sdk.commodity.model.EsGoodOnlineGroupSkuPropertyValueResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "商品列表dto")
public class ShopPlanItemDTO extends AbstractObject implements Serializable {
    //商品列表的主键id
    @ApiModelProperty(value = "商品列表的主键id")
    private Long goodId;

    //商品编码对应中台 itemWhole下的code
    @ApiModelProperty(value = "商品编码")
    private String itemCode;
    //商品名称
    @ApiModelProperty(value = "商品名称")
    private String name;
    //sku编码
    @ApiModelProperty(value = "sku编码")
    private String code;

    //skuId
    @ApiModelProperty(value = "skuId")
    private Long skuId;
    //itemID
    @ApiModelProperty(value = "itemId")
    private Long itemId;

    //商品属性
    @ApiModelProperty(value = "商品属性")
    private List<EsGoodOnlineGroupSkuPropertyValueResponseDTO> skuPropertyValueList;
    //商品上架状态
    @ApiModelProperty(value = "商品上架状态")
    private Integer status;
    //锁定库存数量
    @ApiModelProperty(value = "锁定库存数量")
    private Integer lockStockQty;
    //销售总库存数量
    @ApiModelProperty(value = "销售总库存数量")
    private Integer salableStockQty;
    //已销售库存数量
    @ApiModelProperty(value = "已销售库存数量")
    private Integer saledStockQty;

    @ApiModelProperty(value = "库存数量")
    private Integer stockQty;
    /**
     * 渠道|1:在线订购|2:线下订购|
     */
    @ApiModelProperty(value = "渠道|1:在线订购|2:线下订购|")
    private Long channelType;

    /**
     * 门店|1:直供门店|2:非直供门店|默认2
     */
    @ApiModelProperty(value = "门店|1:直供门店|2:非直供门店|")
    private Long shopId;

    @ApiModelProperty(value = "授权价格")
    private BigDecimal authorizedPrice;

    @ApiModelProperty("单位id")
    private Long unitId;

    @SerializedName("单位名称")
    private String unitName;

    @ApiModelProperty(value = "主图")
    private String majorPicture;

    public String getShopIdAndSkuId() {
        return shopId + "_" + skuId;
    }
}
