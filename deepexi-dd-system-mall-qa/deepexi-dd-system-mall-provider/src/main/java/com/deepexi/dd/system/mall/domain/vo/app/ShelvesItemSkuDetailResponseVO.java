package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.dd.system.mall.domain.TenantDTO;
import com.deepexi.sdk.commodity.model.PageShelvesItemSkuItemItemTagResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
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
 * @date 2020-07-13 11:39
 */
@Data
@ApiModel(description = "ShelvesItemSkuDetailResponseVO")
public class ShelvesItemSkuDetailResponseVO extends AbstractObject implements Serializable {

    private String code;

    private String msg;

    /**
     * 店铺ID
     */
    @ApiModelProperty("店铺ID")
    private Long shopId;

    /**
     * 供应商名称
     */
    @ApiModelProperty("供应商名称")
    private String supplierName;

    /**
     * 供应商ID
     */
    @ApiModelProperty("供应商ID")
    private Long supplierId;

    /**
     * 供应商CODE
     */
    @ApiModelProperty("供应商ID")
    private String supplierCode;

    /**
     * item聚合
     */
    @ApiModelProperty("item聚合")
    private GetShelvesItemSkuItemWholeResponseDTO itemWhole;

    /**
     * sku数组
     */
    @ApiModelProperty("sku数组")
    private List<GetShelvesItemSkuGroupSkuResponseDTO> skuList;

    @ApiModelProperty(value = "活动id")
    private Long activitiesId;

    @ApiModelProperty(value = "活动名称")
    private String activitiesName;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class GetShelvesItemSkuItemWholeResponseDTO extends TenantDTO {

        /**
         * item详情数组
         **/
        @ApiModelProperty("item详情数组")
        private List<GetShelvesItemSkuItemWholeItemDetailResponseDTO> itemDetailList;

        /**
         * item属性值数组
         **/
        @ApiModelProperty("item属性值数组")
        private List<GetShelvesItemSkuItemWholeItemPropertyResponseDTO> itemPropertyList;

        @ApiModelProperty("商品标签列表")
        private List<PageShelvesItemSkuItemItemTagResponseDTO> itemTagList;

        /**
         * 主图
         **/
        @ApiModelProperty("主图")
        private String majorPicture;

        /**
         * item名称
         **/
        @ApiModelProperty("item名称")
        private String name;

        /**
         * 商品副名称
         */
        @ApiModelProperty("商品副名称")
        private String subName;

        /**
         * 是否开启多规格
         */
        @ApiModelProperty("是否开启多规格")
        private Integer multiSpec;

        /**
         * 备注
         */
        @ApiModelProperty("备注")
        private String remark;

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class GetShelvesItemSkuItemWholeItemDetailResponseDTO extends TenantDTO {

            /**
             * spu图文详情
             **/
            @ApiModelProperty("spu图文详情")
            private String detailContent;

            /**
             * 视频
             **/
            @ApiModelProperty("视频")
            private String video;

            /**
             * 图片
             **/
            @ApiModelProperty("图片")
            private String picture;

            /**
             * 终端类型: 0图片，1是视频，3是富文本
             **/
            @ApiModelProperty("终端类型: 0图片，1是视频，3是富文本")
            private Integer terminalType;
        }

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class GetShelvesItemSkuItemWholeItemPropertyResponseDTO extends TenantDTO {

            /**
             * 属性值数组
             *
             * @return itemPropertyValue
             **/
            @ApiModelProperty("属性值数组")
            private List<GetShelvesItemSkuItemWholeItemPropertyValueResponseDTO> itemPropertyValueList;

            /**
             * item ID
             *
             * @return itemId
             **/
            @ApiModelProperty("item ID")
            private Long itemId;

            /**
             * 属性名称
             *
             * @return propertyName
             **/
            @ApiModelProperty("属性名称")
            private String propertyName;

            /**
             * 属性类型，1：关键属性，2：销售属性，3：非关键属性
             *
             * @return propertyType
             **/
            @ApiModelProperty("属性类型")
            private String propertyType;

            /**
             * 关联属性ID
             *
             * @return propertyId
             **/
            @ApiModelProperty("关联属性ID")
            private Long propertyId;

            @Data
            @EqualsAndHashCode(callSuper = true)
            @ApiModel
            public static class GetShelvesItemSkuItemWholeItemPropertyValueResponseDTO extends TenantDTO {

                /**
                 * item ID
                 **/
                @ApiModelProperty("item ID")
                private Long itemId;

                /**
                 * 关联属性值ID
                 **/
                @ApiModelProperty("关联属性值ID")
                private Long propertyValueId;

                /**
                 * 属性值
                 **/
                @ApiModelProperty("属性值")
                private String propertyValue;

                /**
                 * item属性ID
                 **/
                @ApiModelProperty("item属性ID")
                private Long itemPropertyId;
            }
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class GetShelvesItemSkuGroupSkuResponseDTO extends TenantDTO {

        /**
         * sku ID
         *
         * @return skuId
         **/
        @ApiModelProperty("sku ID")
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

        /**
         * itemID
         **/
        @ApiModelProperty("itemID")
        private Long itemId;

        /**
         * 直供-价格
         */
        @ApiModelProperty(value = "直供-价格")
        private BigDecimal directSupplyPrice;

        @ApiModelProperty(value = "直供id")
        private Long directId;

        @ApiModelProperty(value = "活动id")
        private Long activitiesId;

        /**
         * 销售总库存数量
         */
        @ApiModelProperty(value = "销售总库存数量")
        private Integer salableStockQty;

        /**
         * 主图
         **/
        @ApiModelProperty(value = "主图")
        private String majorPicture;

        /**
         * 批发价-价格
         */
        @ApiModelProperty(value = "批发价-价格")
        private BigDecimal tradePrice;

        /**
         * 建议零售价
         */
        @ApiModelProperty(value = "建议零售价")
        private BigDecimal price;

        /**
         * 成本价
         */
        @ApiModelProperty(value = "成本价")
        private BigDecimal purchasePrice = null;

        /**
         * 单位名称
         */
        @ApiModelProperty("单位名称")
        private String unitName;

        /**
         * 数据隔离id
         */
        @ApiModelProperty("数据隔离id")
        private String isolationId;

        /**
         * 商品数量
         */
        @ApiModelProperty("商品数量")
        private Integer count = 0;

        @ApiModelProperty(value = "是否限购")
        private Boolean isLimited;

        @ApiModelProperty("限购数量")
        private Integer limitNum;

        /**
         * sku属性数组
         **/
        @ApiModelProperty("sku属性数组")
        private List<SkuPropertyValueResDTO> skuPropertyValueList;

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class SkuPropertyValueResDTO extends TenantDTO {

            /**
             * 属性名
             *
             * @return propertyName
             **/
            @ApiModelProperty("属性名")
            private String propertyName;

            /**
             * 属性值ID
             *
             * @return propertyValueId
             **/
            @ApiModelProperty("属性值ID")
            private Long propertyValueId;

            /**
             * 属性值
             *
             * @return propertyValue
             **/
            @ApiModelProperty("属性值")
            private String propertyValue;

            /**
             * 属性ID
             *
             * @return propertyId
             **/
            @ApiModelProperty("属性ID")
            private Long propertyId;

            /**
             * sku ID
             *
             * @return skuId
             **/
            @ApiModelProperty("sku ID")
            private Long skuId;
        }

    }
}
