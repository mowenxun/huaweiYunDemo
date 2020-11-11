package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName SalePickDeliveryItemInfoMiddleRequestDTO
 * @Description 提货计划发货中的商品明细
 * @Author SongTao
 * @Date 2020-08-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickDeliveryItemInfoMiddleRequestDTO")
public class SalePickDeliveryItemInfoMiddleRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -1135836645667608273L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "商品ID")
    @NotNull(message = "商品ID为空")
    private Long skuId;

    @ApiModelProperty(value = "商品提货申请数量")
    private Long skuPickQuantity;

    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;
}
