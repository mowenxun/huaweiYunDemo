package com.deepexi.dd.system.mall.domain.dto.shoppingcart;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 购物车
 *
 * @author yangwu
 * @date 2020/7/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "购物车")
public class ShoppingCartRequestDTO extends AbstractObject implements Serializable {

    @ApiModelProperty(value = "订单类型=0:直供单,1:标准订单,2:非标准订单", required = true)
    @NotNull(message = "订单类型不能为空.")
    private Integer orderTypeId;

    @ApiModelProperty("应用id")
    private Long appId;

    @ApiModelProperty("商品信息列表")
    @Valid
    @NotNull(message = "商品列表不能为空.")
    private List<ShoppingCartCommodityRequestDTO> shoppingCartCommodityDTOList;
}
