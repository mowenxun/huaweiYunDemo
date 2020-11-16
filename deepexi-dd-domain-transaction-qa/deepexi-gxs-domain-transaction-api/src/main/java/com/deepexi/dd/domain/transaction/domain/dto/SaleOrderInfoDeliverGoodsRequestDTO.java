package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.AbstractTenantDTO;
import com.deepexi.dd.domain.transaction.domain.dto.orderDeliveryConsigneeInfo.OrderDeliveryConsigneeInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemDeliverGoodsRequestDTO;
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
 * @ClassName SaleOrderInfoDeliverGoodsRequestDTO
 * @Description 订单发货DTO
 * @Author SongTao
 * @Date 2020-07-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单发货对象")
public class SaleOrderInfoDeliverGoodsRequestDTO extends AbstractTenantDTO implements Serializable{
    private static final long serialVersionUID = 3979412527870329609L;

    @ApiModelProperty(value = "订单ID",required = true)
    @NotNull(message = "订单ID为空")
    private Long saleOrderId;

    @ApiModelProperty(value = "订单编码",required = true)
    @NotEmpty(message = "订单编码为空")
    private String saleOrderCode;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提)",required = true)
    @NotNull(message = "发货方式为空")
    private Integer deliveryType;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;

    @ApiModelProperty(value = "发货仓库ID")
    private Long deliveryWareHouseId;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "发货物流信息")
    private OrderDeliveryInfoAddRequestDTO orderDeliveryInfo;

    @ApiModelProperty(value = "订单送货地址信息")
    private OrderDeliveryConsigneeInfoRequestDTO orderDeliveryConsigneeInfo;

    @ApiModelProperty(value = "商品明细列表")
    @NotNull(message = "商品明细不能为空")
    @Size(min = 1,message = "未选择商品信息")
    private List<SaleOrderItemDeliverGoodsRequestDTO> items;
}
