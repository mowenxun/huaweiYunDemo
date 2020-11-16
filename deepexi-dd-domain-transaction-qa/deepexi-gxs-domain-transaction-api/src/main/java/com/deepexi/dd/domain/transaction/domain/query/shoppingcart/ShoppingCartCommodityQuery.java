package com.deepexi.dd.domain.transaction.domain.query.shoppingcart;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ShoppingCartCommodityQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-29
 * @Version 1.0
 **/
@Data
public class ShoppingCartCommodityQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 2583866221581886409L;

    /**
     * 商品sku id
     */
    @ApiModelProperty(value = "商品sku id")
    private Long skuId;
    /**
     * 商品shopId
     */
    @ApiModelProperty(value = "店铺id")
    private Long shopId;
    /**
     * 数量，删除操作时为空
     */
    @ApiModelProperty(value = "数量，删除操作时为空")
    private Integer num;
}
