package com.deepexi.dd.system.mall.domain.query.app;

import com.deepexi.dd.system.mall.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-09 9:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommodityCoreQuery extends BaseQuery implements Serializable {

    /**
     * 页码,传-1代表不分页查询
     */
    @NotNull(message = "页码不能为空")
    @ApiModelProperty(value = "页码", required = true)
    private Integer page;

    /**
     * 每页数量
     */
    @NotNull(message = "每页数量不能为空")
    @ApiModelProperty(value = "每页数量", required = true)
    private Integer size;

    /**
     * 前台类目id
     */
    @ApiModelProperty("前台类目ID")
    private Long frontCategoryId;

    /**
     * 名称模糊(商品名称和副标题)
     */
    @ApiModelProperty("名称模糊(商品名称和副标题)")
    private String name;

    /**
     * item名称模糊
     */
    @ApiModelProperty("item名称模糊")
    private String itemNameFuzzy;

    /**
     * item副标题
     */
    @ApiModelProperty("item副标题")
    private String itemSubName;

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
     * 店铺ID：1直供，2非直供
     */
    @ApiModelProperty("店铺ID：1直供，2非直供")
    private Long shopId;

    @ApiModelProperty("店铺列表")
    private List<Long> shopIds;

    /**
     * 直供上架状态（0未上架 1已上架 2已下架）
     */
    @NotNull(message = "直供上架状态 不能为空")
    @ApiModelProperty(value = "直供上架状态（0未上架 1已上架 2已下架）")
    private Integer onStatus;

    /**
     * skuIdList
     */
    @ApiModelProperty(value = "skuId")
    private List<Long> skuIdList;

    @ApiModelProperty(value = "sku属性条件")
    private List<CommoditySkuPropertyQuery> propertyList;

    @ApiModelProperty(value = "授权的sku信息")
    private List<AuthorizeSkuInfo> authorizeSkuList;
}