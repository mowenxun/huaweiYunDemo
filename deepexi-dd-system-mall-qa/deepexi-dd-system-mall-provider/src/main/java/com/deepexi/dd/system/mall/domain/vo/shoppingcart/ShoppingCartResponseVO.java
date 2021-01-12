package com.deepexi.dd.system.mall.domain.vo.shoppingcart;

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
@ApiModel(description = "购物车查询返回结果")
public class ShoppingCartResponseVO extends AbstractObject implements Serializable {
    /**
     * 所属组织
     */
    @ApiModelProperty("所属组织")
    private Long affiliatedOrganization;
    /**
     * 所属组织名称
     */
    @ApiModelProperty("所属组织名称")
    private String affiliatedOrganizationName;
    /**
     * 订单类型
     */
    @ApiModelProperty("订单类型")
    private Integer orderTypeId;
    /**
     * 订单类型名称
     */
    @ApiModelProperty("订单类型名称")
    private String orderTypeName;
    /**
     * 商品信息列表
     */
    @ApiModelProperty("商品信息列表")
    private List<ShoppingCartCommodityResponseVO> shoppingCartCommodityResponseDTOList;

}
