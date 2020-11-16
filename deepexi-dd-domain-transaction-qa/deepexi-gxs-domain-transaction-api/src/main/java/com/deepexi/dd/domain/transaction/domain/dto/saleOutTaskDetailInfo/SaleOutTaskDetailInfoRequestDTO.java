package com.deepexi.dd.domain.transaction.domain.dto.saleOutTaskDetailInfo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleOutTaskDetailInfoRequestDTO
 * @Description 出库单关联的商品信息
 * @Author SongTao
 * @Date 2020-07-09
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskDetailInfoRequestDTO")
public class SaleOutTaskDetailInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1156464562842177522L;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "商品明细ID")
    private Long saleOrderItemId;

    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;
}
