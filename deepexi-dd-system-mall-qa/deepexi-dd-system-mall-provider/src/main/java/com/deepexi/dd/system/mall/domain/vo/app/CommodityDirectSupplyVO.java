package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-08 18:58
 */
@Data
@ApiModel(description = "CommodityDirectSupplyVO")
public class CommodityDirectSupplyVO extends AbstractObject implements Serializable {

    private String code;

    private String msg;

    /**
     * 店铺ID
     */
    @ApiModelProperty("店铺ID")
    private Long shopId;

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
     * 商品副名称
     */
    @ApiModelProperty("商品副名称")
    private String subName;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    private String commodityCode;

    /**
     * 主图
     */
    @ApiModelProperty("主图")
    private String majorPicture;

    /**
     * sku id
     */
    @ApiModelProperty("sku id")
    private Long skuId;

    /**
     * 直供-价格
     */
    @ApiModelProperty(value = "直供-价格")
    private BigDecimal directSupplyPrice;

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
     * 属性值
     */
    @ApiModelProperty("属性值")
    private String propertyValue;

}
