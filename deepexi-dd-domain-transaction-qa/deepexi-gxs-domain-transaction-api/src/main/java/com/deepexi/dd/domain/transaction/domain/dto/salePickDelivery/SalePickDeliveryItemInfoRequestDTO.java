package com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName SalePickDeliveryItemInfoRequestDTO
 * @Description 提货计划发货中的商品明细
 * @Author SongTao
 * @Date 2020-08-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderItemDeliverGoodsRequestDTO")
public class SalePickDeliveryItemInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 7347785255260864759L;

    @ApiModelProperty(value = "id",required = true)
    @NotNull(message = "id为空")
    private Long id;

    @ApiModelProperty(value = "商品ID",required = true)
    @NotNull(message = "商品ID为空")
    @Range(min = 1,message = "商品ID错误")
    private Long skuId;

    @ApiModelProperty(value = "商品提货申请数量",required = true)
    @NotNull(message = "商品提货申请数量为空")
    @Range(min = 1,message = "商品提货申请数量错误")
    private Long skuPickQuantity;

    @ApiModelProperty(value = "商品本次出库数量",required = true)
    @NotNull(message = "商品本次出库数量为空")
    private Long skuShipmentQuantity;


    @ApiModelProperty("订单编号")
    private String saleOrderCode;

}
