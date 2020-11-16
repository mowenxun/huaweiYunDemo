package com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName SaleOrderItemDeliverGoodsRequestDTO
 * @Description 发货商品明细对象
 * @Author SongTao
 * @Date 2020-07-17
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderItemDeliverGoodsRequestDTO")
public class SaleOrderItemDeliverGoodsRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 6587141162422241410L;

    @ApiModelProperty(value = "id",required = true)
    @NotNull(message = "id为空")
    private Long id;

    @ApiModelProperty(value = "商品ID",required = true)
    @NotNull(message = "商品ID为空")
    @Range(min = 1,message = "商品ID错误")
    private Long skuId;

    @ApiModelProperty(value = "商品名称",required = true)
    @NotEmpty(message = "商品名称为空")
    private String skuName;

    @ApiModelProperty(value = "店铺ID必填,否则不能获取商品详情/库存",required = true)
    @NotNull(message = "店铺ID为空")
    @Range(min = 1,message = "店铺ID错误")
    private Long shopId;

    @ApiModelProperty(value = "商品总数量",required = true)
    @NotNull(message = "商品总数量为空")
    @Range(min = 1,message = "商品数量错误")
    private Long skuQuantity;

    @ApiModelProperty(value = "单价",required = true)
    @NotNull(message = "单价为空")
    @Range(min = 0,message = "单价错误")
    private BigDecimal price;

    @ApiModelProperty(value = "商品本次出库数量",required = true)
    @NotNull(message = "商品本次出库数量为空")
    private Long skuShipmentQuantity;

}
