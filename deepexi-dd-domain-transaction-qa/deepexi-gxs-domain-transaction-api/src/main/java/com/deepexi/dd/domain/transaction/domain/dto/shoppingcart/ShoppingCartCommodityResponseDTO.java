package com.deepexi.dd.domain.transaction.domain.dto.shoppingcart;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 购物车查询返回商品信息
 *
 * @author yangwu
 * @date 2020/7/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "购物车查询返回商品信息")
public class ShoppingCartCommodityResponseDTO extends AbstractObject {
    /**
     * 商品sku id
     */
    @ApiModelProperty("商品sku id")
    private Long skuId;
    /**
     * 商品shopId
     */
    @ApiModelProperty("商品shopId")
    private Long shopId;
    /**
     * skuCode
     */
    @ApiModelProperty("skuCode")
    private String skuCode;
    /**
     * sku名称
     */
    @ApiModelProperty("sku名称")
    private String skuName;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer num;
    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private BigDecimal price;
    /**
     * 单位
     */
    @ApiModelProperty("单位")
    private String unitName;
    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String commodityName;

    @ApiModelProperty("商品副标题")
    private String subName;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long commodityId;
    /**
     * 商品状态 0.待上架 1.已上架 2.下架
     */
    @ApiModelProperty("商品状态 0.待上架 1.已上架 2.下架 3.已售罄")
    private Integer status;
    /**
     * 是否启用，true=启用，false=禁用
     */
    @ApiModelProperty("是否启用，true=启用，false=禁用")
    private Boolean isEnable;
    /**
     * 库存数量
     */
    @ApiModelProperty("库存数量")
    private String stockNum;

    @ApiModelProperty("图片URL")
    private String pictureUrl;

    @ApiModelProperty(value = "属性规格")
    private String propertyValue;

    @ApiModelProperty(value = "组织id")
    private Long orgId;

    @ApiModelProperty(value = "活动id")
    private Long activitiesId;

    @ApiModelProperty(value = "直供id")
    private Long directId;

    @ApiModelProperty(value = "是否限购")
    private Boolean isLimited;

    @ApiModelProperty("限购数量")
    private Integer limitNum;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    /**
     * 品牌名
     */
    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    /**
     * 品牌编码
     */
    @ApiModelProperty(value = "品牌编码")
    private String brandCode;
}
