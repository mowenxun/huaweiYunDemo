package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SaleOrderInfoDeliverGoodsInfoRequestDTO
 * @Description 订单发货DTO
 * @Author SongTao
 * @Date 2020-08-06
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderInfoDeliverGoodsInfoRequestDTO")
public class SaleOrderInfoDeliverGoodsInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -2175135293622667556L;

    @ApiModelProperty(value = "订单ID",required = true)
    @NotNull(message = "订单ID为空")
    private Long saleOrderId;

    @ApiModelProperty(value = "订单编码",required = true)
    @NotEmpty(message = "订单编码为空")
    private String saleOrderCode;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提)",required = true)
    @NotNull(message = "发货方式为空")
    private Integer deliveryType;

    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;

    @ApiModelProperty(value = "发货仓库ID")
    private Long deliveryWareHouseId;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;
    @ApiModelProperty(value = "发货物流信息")
    private OrderDeliveryInfoRequestDTO orderDeliveryInfo;

    @ApiModelProperty(value = "订单送货地址信息")
    private OrderDeliveryConsigneeRequestDTO orderDeliveryConsigneeInfo;

    @ApiModelProperty(value = "商品明细列表")
    @NotNull(message = "商品明细不能为空")
    @Size(min = 1,message = "未选择商品信息")
    private List<SaleOrderItemDeliverGoodsInfoRequestDTO> items;

}
