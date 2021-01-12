package com.deepexi.dd.system.mall.domain.dto.shoppingcart;

import com.deepexi.dd.system.mall.domain.vo.shoppingcart.ShoppingCartCommodityResponseVO;
import com.deepexi.util.pojo.AbstractObject;
import domain.dto.CompanyInfoResponseApiDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 按供应商分组的购物车查询返回商品信息
 *
 * @author yangwu
 * @date 2020/7/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "按供应商分组的购物车查询返回商品信息")
public class ShoppingCartCommodityGroupBySupplierResponseDTO extends AbstractObject {
    /**
     * 供应商信息
     */
    @ApiModelProperty("供应商信息")
    private CompanyInfoResponseApiDTO supplier;

    @ApiModelProperty("购物车查询返回商品信息")
    private List<ShoppingCartCommodityResponseVO> shoppingCartCommodityList;
}
