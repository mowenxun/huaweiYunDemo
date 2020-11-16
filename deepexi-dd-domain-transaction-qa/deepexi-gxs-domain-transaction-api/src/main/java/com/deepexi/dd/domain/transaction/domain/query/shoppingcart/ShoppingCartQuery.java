package com.deepexi.dd.domain.transaction.domain.query.shoppingcart;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车查询参数
 * @author yangwu
 * @date 2020/7/6
 */
@Data
@ApiModel(description = "购物车查询参数")
public class ShoppingCartQuery  extends TenantDTO implements Serializable {

    private static final long serialVersionUID = 6221255094126573982L;

    /**
     * 订单类型
     */
    @ApiModelProperty("订单类型")
    private Integer orderTypeId;

    /**
     * 所属组织
     */
    @ApiModelProperty(value = "所属组织")
    private Long affiliatedOrganizationId;

    /**
     * 商品信息列表
     */
    @ApiModelProperty("商品信息列表")
    private List<ShoppingCartCommodityQuery> shoppingList;
}
