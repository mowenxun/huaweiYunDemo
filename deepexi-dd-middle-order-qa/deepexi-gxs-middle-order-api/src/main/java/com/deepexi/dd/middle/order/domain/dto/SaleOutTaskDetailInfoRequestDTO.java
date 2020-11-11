package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by liaop on 2020/7/9.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskDetailInfoRequestDTO")
public class SaleOutTaskDetailInfoRequestDTO  extends AbstractTenantDTO implements Serializable {
    private static final long serialVersionUID = -3539082499433013126L;
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
     * 销售出库单ID
     */
    @ApiModelProperty(value = "销售出库单ID")
    private Long saleOutTaskId;
    /**
     * 商品明细ID
     */
    @ApiModelProperty(value = "商品明细ID")
    private Long saleOrderItemId;

    /**
     * 商品本次出库数量
     */
    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;

    //2020/08/17 SongTao 新加字段用于提货计划的发货
    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;

    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;

    @ApiModelProperty(value = "商品本次提货数量")
    private Long skuPickQuantity;
}
