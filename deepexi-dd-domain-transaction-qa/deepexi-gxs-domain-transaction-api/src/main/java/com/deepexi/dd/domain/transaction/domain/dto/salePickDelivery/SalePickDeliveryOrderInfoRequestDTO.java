package com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery;

import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemDeliverGoodsRequestDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SalePickDeliveryOrderInfoRequestDTO
 * @Description 提货计划发货里的订单信息
 * @Author SongTao
 * @Date 2020-08-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickDeliveryOrderInfoRequestDTO")
public class SalePickDeliveryOrderInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 630163222076836530L;

    @ApiModelProperty(value = "订单编号",required = true)
    @NotEmpty(message = "订单编号为空")
    private String saleOrderCode;

    @ApiModelProperty(value = "发货仓库名称")
    @NotEmpty(message = "发货仓库名称为空")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "商品明细列表")
    @NotNull(message = "商品明细不能为空")
    @Size(min = 1,message = "未选择商品信息")
    private List<SalePickDeliveryItemInfoRequestDTO> salePickDeliveryItemInfoList;

}
