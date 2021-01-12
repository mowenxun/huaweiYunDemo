package com.deepexi.dd.system.mall.domain.vo.commodity;

import com.deepexi.commodity.dto.es.EsGoodInformationGroupSkuDetailApiResponseVO;
import com.deepexi.commodity.dto.es.TenantApiVO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author mumu
 * @version 1.0
 * @date 2020/7/7 14:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class EsGoodInformationApiSysResponseVO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 4778331539673578246L;
    /**
     * item聚合
     */
    @ApiModelProperty("item聚合")
    private EsGoodInformationItemWholeResponseVO itemWhole;

    /**
     * sku聚合数组
     */
    @ApiModelProperty("sku聚合数组")
    private List<EsGoodInformationGroupSkuResponseVO> skuList;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class EsGoodInformationItemWholeResponseVO extends TenantApiVO implements Serializable {

        /**
         * item id
         */
        @ApiModelProperty("item id")
        private Long id;
        /**
         * spuID
         */
        @ApiModelProperty("spuID")
        private Long spuId;
        /**
         * 类目ID
         */
        @ApiModelProperty("类目ID")
        private Long categoryId;
        /**
         * 品牌ID
         */
        @ApiModelProperty("品牌ID")
        private Long brandId;

        /**
         * 品牌名称
         */
        @ApiModelProperty("品牌名称")
        private String chineseName;

        /**
         * 类目名称
         */
        @ApiModelProperty("类目名称")
        private String categoryName;
        /**
         * 店铺ID
         */
        @ApiModelProperty("店铺ID")
        private Long shopId;
        /**
         * item编号
         */
        @ApiModelProperty("item编号")
        private String code;
        /**
         * item名称
         */
        @ApiModelProperty("item名称")
        private String name;
        /**
         * 商品副名称
         */
        @ApiModelProperty("商品副名称")
        private String subName;
        /**
         * 主图
         */
        @ApiModelProperty("主图")
        private String majorPicture;
        /**
         * 状态 {@link com.deepexi.commodity.enums.ItemStatusEnum}
         */
        @ApiModelProperty("状态")
        private Integer status;

        @ApiModelProperty("true：可以编辑；false:不可编辑")
        private Boolean isUpdate;

        /**
         * 商品类型
         */
        @ApiModelProperty("商品类型")
        private Integer type;
        /**
         * 来源
         */
        @ApiModelProperty("来源")
        private Integer source;

        /**
         * 创建人
         */
        @ApiModelProperty("创建人")
        private Long createdBy;

        /**
         * 是否开启多规格
         */
        @ApiModelProperty("是否开启多规格")
        private Integer multiSpec;

        /**
         * 商品条形码
         */
        @ApiModelProperty("商品条形码")
        private String barCode;

        /**
         * 上架状态
         */
        @ApiModelProperty("上架状态")
        private Integer saleStatus;

        /**
         * 类目树
         */
        @ApiModelProperty("类目树")
        private EsGoodInformationCategoryTreeResponseVO categoryTree;

        /**
         * item详情数组
         */
        @ApiModelProperty("item详情数组")
        private List<EsGoodInformationItemDetailResponseVO> itemDetailList;

        /**
         * item属性值数组
         */
        @ApiModelProperty("item属性值数组")
        private List<EsGoodInformationItemPropertyResponseVO> itemPropertyList;

        /**
         * 商品财务信息列表
         */
        @ApiModelProperty("商品财务信息列表")
        private List<EsGoodInformationItemFinanceResponseVO> itemFinanceList;

        /**
         * 商品库存信息
         */
        @ApiModelProperty("商品库存信息列表")
        private List<EsGoodInformationItemStockConfiguredResponseVO> itemStockConfiguredList;

        /**
         * 商品标签信息
         */
        @ApiModelProperty("商品标签信息")
        private List<EsGoodInformationItemTagResponseVO> itemTagList;

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class EsGoodInformationCategoryTreeResponseVO extends TenantApiVO implements Serializable {

            /**
             * 类目主键
             */
            @ApiModelProperty("类目主键")
            private Long id;

            /**
             * 类目名称
             */
            @ApiModelProperty("类目名称")
            private String name;

            /**
             * 叶子节点
             */
            @ApiModelProperty("叶子节点")
            private Set<EsGoodInformationCategoryTreeResponseVO> children;
        }

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class EsGoodInformationItemDetailResponseVO extends TenantApiVO implements Serializable {
            /**
             * 详情id
             */
            @ApiModelProperty("详情id")
            private Long id;
            /**
             * item ID
             */
            @ApiModelProperty("item ID")
            private Long itemId;
            /**
             * 图片
             */
            @ApiModelProperty("图片")
            private String picture;
            /**
             * 视频
             */
            @ApiModelProperty("视频")
            private String video;
            /**
             * spu图文详情
             */
            @ApiModelProperty("spu图文详情")
            private String detailContent;
            /**
             * 备注
             */
            @ApiModelProperty("备注")
            private String remark;
            /**
             * 终端类型
             */
            @ApiModelProperty("终端类型")
            private Integer terminalType;
        }

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class EsGoodInformationItemPropertyResponseVO extends TenantApiVO implements Serializable {
            /**
             * item属性id
             */
            @ApiModelProperty("item属性id")
            private Long id;
            /**
             * item ID
             */
            @ApiModelProperty("item ID")
            private Long itemId;
            /**
             * 关联属性分组ID
             */
            @ApiModelProperty("关联属性分组ID")
            private Long propertyGroupId;
            /**
             * 关联属性ID
             */
            @ApiModelProperty("关联属性ID")
            private Long propertyId;
            /**
             * 属性组名称
             */
            @ApiModelProperty("属性组名称")
            private String propertyGroupName;
            /**
             * 属性code
             */
            @ApiModelProperty("属性code")
            private String propertyCode;
            /**
             * 属性名称
             */
            @ApiModelProperty("属性名称")
            private String propertyName;
            /**
             * 属性类型，1：关键属性，2：销售属性，3：非关键属性
             */
            @ApiModelProperty("属性类型")
            private String propertyType;
            /**
             * 属性排序
             */
            @ApiModelProperty("属性排序")
            private Integer sort;

            /**
             * 属性值数组
             */
            @ApiModelProperty("属性值数组")
            private List<EsGoodInformationItemPropertyValueResponseVO> itemPropertyValueList;

            @Data
            @EqualsAndHashCode(callSuper = true)
            @ApiModel
            public static class EsGoodInformationItemPropertyValueResponseVO extends TenantApiVO implements Serializable {
                /**
                 * item属性值id
                 */
                @ApiModelProperty("item属性值id")
                private Long id;
                /**
                 * itemID
                 */
                @ApiModelProperty("itemID")
                private Long itemId;
                /**
                 * item属性ID
                 */
                @ApiModelProperty("item属性ID")
                private Long itemPropertyId;
                /**
                 * 关联属性值ID
                 */
                @ApiModelProperty("关联属性值ID")
                private Long propertyValueId;
                /**
                 * 属性值
                 */
                @ApiModelProperty("属性值")
                private String propertyValue;
                /**
                 * 属性排序
                 */
                @ApiModelProperty("属性排序")
                private Integer sort;
            }
        }

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class EsGoodInformationItemFinanceResponseVO extends TenantApiVO implements Serializable {
            /**
             * item财务信息id
             */
            @ApiModelProperty("item财务信息id")
            private Long id;
            /**
             * itemId
             */
            @ApiModelProperty("itemId")
            private Long itemId;
            /**
             * 税号
             */
            @ApiModelProperty("税号")
            private String taxNumber;
            /**
             * 税率
             */
            @ApiModelProperty("税率")
            private Double taxPercent;

        }

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class EsGoodInformationItemStockConfiguredResponseVO extends TenantApiVO implements Serializable {
            /**
             * item库存信息id
             */
            @ApiModelProperty("item库存信息id")
            private Long id;
            /**
             * itemId
             */
            @ApiModelProperty(value = "itemId")
            private Long itemId;
            /**
             * 供应商ID
             */
            @ApiModelProperty(value = "供应商ID")
            private Long supplierId;
            /**
             * 保质期天数
             */
            @ApiModelProperty(value = "保质期天数")
            private Long validityDays;
            /**
             * 预警天数
             */
            @ApiModelProperty(value = "预警天数")
            private Long warningDays;
            /**
             * 是否开启批次号
             */
            @ApiModelProperty(value = "是否开启批次号")
            private Boolean isBatchNumber;
            /**
             * 是否开启序列号
             */
            @ApiModelProperty(value = "是否开启序列号")
            private Boolean isSerialNumber;

        }

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class EsGoodInformationItemTagResponseVO extends TenantApiVO implements Serializable {
            /**
             * item和标签关联表id
             */
            @ApiModelProperty("item和标签关联表id")
            private Long id;
            /**
             * itemId
             */
            @ApiModelProperty("itemId")
            private Long itemId;
            /**
             * 标签id
             */
            @ApiModelProperty("标签id")
            private Long tagId;
            /**
             * 标签信息
             */
            @ApiModelProperty("标签信息")
            private EsGoodInformationTagResponseVO tag;

            @Data
            @EqualsAndHashCode(callSuper = true)
            @ApiModel
            public static class EsGoodInformationTagResponseVO extends TenantApiVO implements Serializable {
                /**
                 * 标签id
                 */
                @ApiModelProperty("标签id")
                private Long id;
                /**
                 * 标签编号
                 */
                @ApiModelProperty("标签编号")
                private String code;
                /**
                 * 标签名称
                 */
                @ApiModelProperty("标签名称")
                private String name;
                /**
                 * 标签定义或描述
                 */
                @ApiModelProperty("标签定义或描述")
                private String description;
                /**
                 * 是否启用,1启用,0不启用
                 */
                @ApiModelProperty("是否启用,1启用,0不启用")
                private Integer status;
                /**
                 * 标签组id
                 */
                @ApiModelProperty("标签组id")
                private Long tagGroupId;
                /**
                 * 标签类型 1：销售标签
                 */
                @ApiModelProperty("标签类型 1：销售标签")
                private Integer type;
            }
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ApiModel
    public static class EsGoodInformationGroupSkuResponseVO extends TenantApiVO implements Serializable {
        /**
         * sku id
         */
        @ApiModelProperty("sku id")
        private Long id;
        /**
         * sku类型:(1:普通商品sku,2:组合商品sku)
         */
        @ApiModelProperty("sku类型:(1:普通商品sku,2:组合商品sku)")
        private Integer type;
        /**
         * 单位ID
         */
        @ApiModelProperty("单位ID")
        private Long unitId;
        /**
         * price
         */
        @ApiModelProperty("price")
        private BigDecimal price;

        /**
         * 进货价-价格
         */
        @ApiModelProperty("进货价-价格")
        private BigDecimal purchasePrice;

        /**
         * 寄售价-价格
         */
        @ApiModelProperty("寄售价-价格")
        private BigDecimal consignSalesPrice;
        /**
         * itemID
         */
        @ApiModelProperty("itemID")
        private Long itemId;
        /**
         * 关联specID
         */
        @ApiModelProperty("关联specID")
        private Long cspuId;
        /**
         * 主图
         */
        @ApiModelProperty("主图")
        private String majorPicture;
        /**
         * sku编号
         */
        @ApiModelProperty("sku编号")
        private String code;
        /**
         * 单位名称
         */
        @ApiModelProperty("单位名称")
        private String unitName;
        /**
         * 单位值
         */
        @ApiModelProperty("单位值")
        private String unitValue;
        /**
         * sku属性数组
         */
        @ApiModelProperty("sku属性数组")
        private List<EsGoodInformationGroupSkuPropertyValueResponseVO> skuPropertyValueList;
        /**
         * 组合商品关联sku信息
         */
        @ApiModelProperty("组合商品关联sku信息")
        private List<EsGoodInformationGroupSkuDetailApiResponseVO> groupSkuDetail;

        @ApiModelProperty("可销售库存")
        private Integer salableStockQty;

        /**
         * 销售积分
         */
        @ApiModelProperty("销售积分")
        private Integer salesPoints;

        /**
         * sku名称
         */
        @ApiModelProperty("sku名称")
        private String skuName;

        /**
         * 商品条形码
         */
        @ApiModelProperty("商品条形码")
        private String barCode;

        @Data
        @EqualsAndHashCode(callSuper = true)
        @ApiModel
        public static class EsGoodInformationGroupSkuPropertyValueResponseVO extends TenantApiVO implements Serializable {
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
             * 属性ID
             */
            @ApiModelProperty("属性ID")
            private Long propertyId;
            /**
             * 属性值ID
             */
            @ApiModelProperty("属性值ID")
            private Long propertyValueId;
            /**
             * 属性名
             */
            @ApiModelProperty("属性名")
            private String propertyName;
            /**
             * 属性编码
             */
            @ApiModelProperty("属性编码")
            private String propertyCode;
            /**
             * 属性值
             */
            @ApiModelProperty("属性值")
            private String propertyValue;
        }

    }
}