package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.dd.system.mall.domain.query.shoppingcart.ShoppingCartCommodityRequestQuery;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车查询返回结果
 * @author yangwu
 * @date 2020/7/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "购物车skuId查询")
public class ListCommodityShoppingCartRequestQuery extends AbstractObject implements Serializable {


    /**
     * query
     */
    @ApiModelProperty("查询参数")
    private List<ShoppingCartCommodityRequestQuery> requestQueryList;

}
