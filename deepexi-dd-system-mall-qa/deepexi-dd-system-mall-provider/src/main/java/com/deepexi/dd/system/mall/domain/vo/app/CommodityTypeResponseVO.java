package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.dd.system.mall.domain.TenantDTO;
import com.deepexi.sdk.commodity.model.PageShelvesItemSkuItemItemTagResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-08 18:58
 */
@Data
@ApiModel(description = "CommodityTypeResponseVO")
public class CommodityTypeResponseVO extends AbstractObject implements Serializable {

    private String code;

    private String msg;

    /**
     * 店铺ID
     */
    @ApiModelProperty("店铺ID")
    private Long shopId;

    /**
     * item聚合
     */
    @ApiModelProperty("item聚合")
    private PageShelvesItemSkuItemWholeResponseDTO itemWhole;

    /**
     * sku数组
     */
    @ApiModelProperty("sku数组")
    private List<PageShelvesItemSkuGroupSkuResponseDTO> skuList;

    @ApiModelProperty(value = "活动id")
    private Long activitiesId;

    @ApiModelProperty(value = "活动名称")
    private String activitiesName;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class PageShelvesItemSkuItemWholeResponseDTO extends TenantDTO {

        /**
         * item id
         */
        @ApiModelProperty("item id")
        private Long id;

        /**
         * item名称
         */
        @ApiModelProperty("item名称")
        private String name;

        /**
         * 商品编码
         */
        @ApiModelProperty("商品编码")
        private String code;

        /**
         * 商品副名称
         */
        @ApiModelProperty("商品副名称")
        private String subName;

        /**
         * 商品副名称
         */
        @ApiModelProperty("商品副名称")
        private String remark;

        /**
         * 主图
         */
        @ApiModelProperty("主图")
        private String majorPicture;

        /**
         * 状态
         */
        @ApiModelProperty("状态")
        private Integer status;


        /**
         * 是否开启多规格
         */
        @ApiModelProperty("是否开启多规格")
        private Integer multiSpec;

        @ApiModelProperty("商品详情列表")
        private List<ItemWholeDetailResponseDTO> itemDetailList;

        @ApiModelProperty("商品属性列表")
        private List<ItemWholeItemPropertyResponseDTO> itemPropertyList;

        @ApiModelProperty("商品标签列表")
        private List<PageShelvesItemSkuItemItemTagResponseDTO> itemTagList;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class ItemWholeItemPropertyResponseDTO extends TenantDTO {

        @ApiModelProperty("itemId")
        private Long itemId;

        @ApiModelProperty("itemPropertyValueList")
        private List<ItemWholeItemPropertyValueResponseDTO> itemPropertyValueList;

        @ApiModelProperty("propertyCode")
        private String propertyCode;

        @ApiModelProperty("propertyGroupId")
        private Long propertyGroupId;

        @ApiModelProperty("propertyGroupName")
        private String propertyGroupName;

        @ApiModelProperty("propertyId")
        private Long propertyId;

        @ApiModelProperty("propertyName")
        private String propertyName;

        @ApiModelProperty("propertyType")
        private String propertyType;

        @ApiModelProperty("sort")
        private Integer sort;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class ItemWholeItemPropertyValueResponseDTO extends TenantDTO {

        @ApiModelProperty("itemId")
        private Long itemId;

        @ApiModelProperty("itemPropertyId")
        private Long itemPropertyId;

        @ApiModelProperty("propertyValue")
        private String propertyValue;

        @ApiModelProperty("propertyValueId")
        private Long propertyValueId;

        @ApiModelProperty("sort")
        private Integer sort;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class ItemWholeDetailResponseDTO extends TenantDTO {

        @ApiModelProperty("detailContent")
        private String detailContent;

        @ApiModelProperty("itemId")
        private Long itemId;

        @ApiModelProperty("picture")
        private String picture;

        @ApiModelProperty("terminalType")
        private Integer terminalType;

        @ApiModelProperty("video")
        private String video;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class PageShelvesItemSkuGroupSkuResponseDTO extends TenantDTO {

        /**
         * sku id
         */
        @ApiModelProperty("sku id")
        private Long id;

        /**
         * sku code
         */
        @ApiModelProperty("code")
        private String code = null;
        @ApiModelProperty("name")
        private String name = null;
        @ApiModelProperty("skuName")
        private String skuName = null;

        @ApiModelProperty(value = "直供id")
        private Long directId;

        /**
         * 直供上架状态（0未上架 1已上架 2已下架）
         */
        @ApiModelProperty(value = "直供上架状态（0未上架 1已上架 2已下架）")
        private Integer onStatus;

        /**
         * 直供-价格
         */
        @ApiModelProperty(value = "直供-价格")
        private BigDecimal directSupplyPrice;

        /**
         * 批发价-价格
         */
        @ApiModelProperty(value = "批发价-价格")
        private BigDecimal tradePrice;

        /**
         * 建议零售价
         */
        @ApiModelProperty(value = "建议零售价")
        private BigDecimal price = null;

        /**
         * 成本价
         */
        @ApiModelProperty(value = "成本价")
        private BigDecimal purchasePrice = null;

        /**
         * 数据隔离id
         */
        @ApiModelProperty("数据隔离id")
        private String isolationId;

        /**
         * 销售总库存数量
         */
        @ApiModelProperty(value = "销售总库存数量")
        private Integer salableStockQty;

        /**
         * 单位名称
         */
        @ApiModelProperty("单位名称")
        private String unitName;

        /**
         * 商品数量
         */
        @ApiModelProperty("商品数量")
        private Integer count = 0;

        /**
         * 商品图片
         */
        @ApiModelProperty("商品图片")
        private String majorPicture;

        @ApiModelProperty(value = "是否限购")
        private Boolean isLimited;

        @ApiModelProperty("限购数量")
        private Integer limitNum;

        @ApiModelProperty("活动id")
        private Long activitiesId;

        /**
         * sku属性数组
         */
        @ApiModelProperty("sku属性数组")
        private List<PageShelvesItemSkuGroupSkuPropertyValueResponseDTO> skuPropertyValueList;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class PageShelvesItemSkuGroupSkuPropertyValueResponseDTO extends TenantDTO {

        /**
         * sku 属性值 id
         */
        @ApiModelProperty("sku 属性值 id")
        private Long id;
        /**
         * sku ID
         */
        @ApiModelProperty("sku ID")
        private Long skuId;
        /**
         * 属性名
         */
        @ApiModelProperty("属性名")
        private String propertyName;
        /**
         * 属性值
         */
        @ApiModelProperty("属性值")
        private String propertyValue;

        /**
         * 属性ID
         *
         * @return propertyId
         **/
        @ApiModelProperty("属性ID")
        private Long propertyId;
    }

}
