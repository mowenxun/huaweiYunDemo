package com.deepexi.dd.system.mall.domain.query.shoppingcart;

import com.deepexi.dd.system.mall.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车查询参数
 * @author yangwu
 * @date 2020/7/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class ShoppingCartQuery extends TenantDTO implements Serializable {

    private static final long serialVersionUID = 395040255662478130L;
    /**
     * 订单类型 0:直供单,1:标准订单,2:非标准订单
     */
    @ApiModelProperty("订单类型 0:直供单,1:标准订单,2:非标准订单")
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
    private List<ShoppingCartCommodityRequestQuery> shoppingCartCommodityDTOList;
}
