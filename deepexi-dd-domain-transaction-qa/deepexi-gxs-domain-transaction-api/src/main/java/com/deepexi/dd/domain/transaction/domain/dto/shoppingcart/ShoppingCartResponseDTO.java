package com.deepexi.dd.domain.transaction.domain.dto.shoppingcart;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 购物车查询返回结果
 *
 * @author yangwu
 * @date 2020/7/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "购物车查询返回结果")
public class ShoppingCartResponseDTO extends AbstractObject {

    @ApiModelProperty("所属组织")
    private Long affiliatedOrganization;

    @ApiModelProperty("所属组织名称")
    private String affiliatedOrganizationName;

    @ApiModelProperty("一级组织id")
    private Long firstOrgId;

    @ApiModelProperty("类型")
    private Integer orderTypeId;

    @ApiModelProperty("商品信息列表")
    private List<ShoppingCartCommodityResponseDTO> shoppingCartCommodityResponseDTOList;

}
