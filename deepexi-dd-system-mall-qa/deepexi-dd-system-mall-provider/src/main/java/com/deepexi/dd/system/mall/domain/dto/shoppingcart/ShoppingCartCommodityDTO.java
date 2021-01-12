package com.deepexi.dd.system.mall.domain.dto.shoppingcart;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 购物车商品
 *
 * @author yangwu
 * @date 2020/7/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "购物车商品")
public class ShoppingCartCommodityDTO extends AbstractObject implements Serializable {
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
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer num;

}
