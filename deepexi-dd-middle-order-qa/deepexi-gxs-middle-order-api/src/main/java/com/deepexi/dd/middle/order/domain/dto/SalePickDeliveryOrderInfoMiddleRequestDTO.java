package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SalePickDeliveryOrderInfoMiddleRequestDTO
 * @Description 提货计划发货里的订单信息
 * @Author SongTao
 * @Date 2020-08-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickDeliveryOrderInfoMiddleRequestDTO")
public class SalePickDeliveryOrderInfoMiddleRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -9192023157226879983L;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "出库单编号")
    private String saleOutTaskCode;

    @ApiModelProperty(value = "商品明细列表")
    private List<SalePickDeliveryItemInfoMiddleRequestDTO> salePickDeliveryItemInfoList;
}
