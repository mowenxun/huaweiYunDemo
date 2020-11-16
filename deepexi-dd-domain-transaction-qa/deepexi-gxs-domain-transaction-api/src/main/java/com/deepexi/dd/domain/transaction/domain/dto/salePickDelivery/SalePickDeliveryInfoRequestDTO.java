package com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery;

import com.deepexi.dd.domain.transaction.domain.dto.orderDeliveryConsigneeInfo.OrderDeliveryConsigneeInfoRequestDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.Api;
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
 * @ClassName SalePickDeliveryInfoRequestDTO
 * @Description 提货计划的发货信息
 * @Author SongTao
 * @Date 2020-08-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickDeliveryInfoRequestDTO")
public class SalePickDeliveryInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 648724246922605032L;

    @ApiModelProperty(value = "提货单ID",required = true)
    @NotNull(message = "提货单ID为空")
    private Long pickGoodsId;

    @ApiModelProperty(value = "提货编号",required = true)
    @NotEmpty(message = "提货编号为空")
    private String pickGoodsCode;

    @ApiModelProperty(value = "发货方式(1:自提；2:小库；3:外仓；4:工地)",required = true)
    @NotNull(message = "发货方式为空")
    private Integer deliveryType;

    @ApiModelProperty(value = "发货时间",required = true)
    @NotNull(message = "发货时间为空")
    private Date deliveryTime;

    @ApiModelProperty(value = "发货物流信息")
    private SalePickDeliveryLogisticsInfoRequestDTO salePickDeliveryLogisticsInfo;

    @ApiModelProperty(value = "收货地址信息")
    private OrderDeliveryConsigneeInfoRequestDTO orderDeliveryConsigneeInfo;

    @ApiModelProperty(value = "发货订单信息")
    @NotNull(message = "发货订单信息不能为空")
    @Size(min = 1,message = "未选择订单信息")
    private List<SalePickDeliveryOrderInfoRequestDTO> salePickDeliveryOrderInfoList;


}
