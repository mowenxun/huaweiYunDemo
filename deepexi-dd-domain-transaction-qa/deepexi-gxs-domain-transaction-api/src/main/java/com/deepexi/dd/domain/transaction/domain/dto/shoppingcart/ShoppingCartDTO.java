package com.deepexi.dd.domain.transaction.domain.dto.shoppingcart;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车
 *
 * @author yangwu
 * @date 2020/7/6
 */
@Data
@ApiModel(description = "购物车")
public class ShoppingCartDTO extends TenantDTO implements Serializable {

    private static final long serialVersionUID = -1410604969445461093L;

    @ApiModelProperty("类型")
    private Integer orderTypeId;

    @ApiModelProperty("商品信息列表")
    private List<ShoppingCartCommodityDTO> shoppingCartCommodityDTOList;
}
