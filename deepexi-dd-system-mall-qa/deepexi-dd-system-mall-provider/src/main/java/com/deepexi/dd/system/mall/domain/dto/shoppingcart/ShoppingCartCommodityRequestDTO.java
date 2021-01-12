package com.deepexi.dd.system.mall.domain.dto.shoppingcart;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName ShoppingCartCommodityRequestDTO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class ShoppingCartCommodityRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = -3662714704891372984L;

    @ApiModelProperty(value = "商品sku id", required = true)
    @NotNull(message = "商品sku id不能为空.")
    private Long skuId;

    @ApiModelProperty(value = "店铺id", required = true)
    @NotNull(message = "店铺id不能为空.")
    private Long shopId;

    @ApiModelProperty(value = "数量，删除操作时为空", required = true)
    @NotNull(message = "商品数量不能为空.")
    @Min(1)
    private Integer num;

    @ApiModelProperty(value = "所属组织", required = true)
    @NotNull(message = "所属组织id不能为空.")
    private Long affiliatedOrganizationId;

    @ApiModelProperty(value = "活动id")
    private Long activitiesId;

    @ApiModelProperty(value = "直供id")
    private Long directId;
}
