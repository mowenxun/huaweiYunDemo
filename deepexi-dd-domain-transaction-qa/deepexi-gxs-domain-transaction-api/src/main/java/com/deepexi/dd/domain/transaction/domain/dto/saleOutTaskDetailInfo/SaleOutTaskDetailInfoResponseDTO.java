package com.deepexi.dd.domain.transaction.domain.dto.saleOutTaskDetailInfo;

import com.deepexi.dd.domain.transaction.domain.responseDto.AbstractTenantResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleOutTaskDetailInfoMiddleResponseDTO
 * @Description 出库单关联的商品信息
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskDetailInfoResponseDTO")
public class SaleOutTaskDetailInfoResponseDTO extends AbstractTenantResponseDTO implements Serializable {
    private static final long serialVersionUID = 7280697060134631458L;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "销售出库单ID")
    private Long saleOutTaskId;

    @ApiModelProperty(value = "商品明细ID")
    private Long saleOrderItemId;

    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;
}
