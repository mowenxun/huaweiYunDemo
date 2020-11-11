package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleOutTaskDetailInfoMiddleResponseDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskDetailInfoMiddleResponseDTO")
public class SaleOutTaskDetailInfoMiddleResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 7280697060134631458L;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "销售出库单ID")
    private Long saleOutTaskId;

    @ApiModelProperty(value = "商品明细ID")
    private Long saleOrderItemId;

    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;

    @ApiModelProperty(value = "提货单ID")
    private Long salePickGoodsId;

    /**
     * 申请数量
     */
    @ApiModelProperty(value = "商品本次申请提货数量")
    private Long skuPickQuantity;
}
