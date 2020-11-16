package com.deepexi.dd.domain.transaction.domain.dto.shoppingcart;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 购物车商品
 *
 * @author yangwu
 * @date 2020/7/6
 */
@Data
public class ShoppingCartCommodityDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 9189807520052691934L;

    @ApiModelProperty(value = "商品sku id", required = true)
    @NotNull(message = "商品sku id不能为空.")
    private Long skuId;

    @ApiModelProperty(value = "店铺id", required = true)
    @NotNull(message = "店铺id不能为空.")
    private Long shopId;

    @ApiModelProperty(value = "数量，删除操作时为空", required = true)
    @NotNull(message = "商品数量不能为空.")
    private Integer num;

    @ApiModelProperty(value = "所属组织", required = true)
    @NotNull(message = "所属组织id不能为空.")
    private Long affiliatedOrganizationId;

    @ApiModelProperty("操作时间")
    private Long operationTime;

    @ApiModelProperty("订单类型")
    private Integer orderTypeId;

    @ApiModelProperty(value = "活动id")
    private Long activitiesId;

    @ApiModelProperty(value = "直供id")
    private Long directId;

    @ApiModelProperty(value = "一级组织id")
    private Long firstOrgId;

    private List<String> arrays1;

    private List<String> arrays2;

    private List<String> arrays3;

    private String args1;

    private String args2;

    private String args3;

    private String args4;

    private String args5;

    private String args6;

    private String args7;

    private String args8;

    private String args9;

    private String args10;
}
