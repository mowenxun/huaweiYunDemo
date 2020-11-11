package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SalePickDeliveryInfoMiddleRequestDTO
 * @Description 提货计划的发货信息
 * @Author SongTao
 * @Date 2020-08-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickDeliveryInfoMiddleRequestDTO")
public class SalePickDeliveryInfoMiddleRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1568639437381986182L;

    @ApiModelProperty(value = "提货单ID")
    private Long pickGoodsId;

    @ApiModelProperty(value = "提货编号")
    private String pickGoodsCode;

    @ApiModelProperty(value = "发货方式(1:自提；2:小库；3:外仓；4:工地)")
    private Integer deliveryType;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "发货物流信息")
    private SalePickDeliveryLogisticsInfoMiddleRequestDTO salePickDeliveryLogisticsInfo;

    @ApiModelProperty(value = "收货地址信息")
    private OrderDeliveryConsigneeInfoMiddleRequestDTO orderDeliveryConsigneeInfo;

    @ApiModelProperty(value = "发货订单信息")
    private List<SalePickDeliveryOrderInfoMiddleRequestDTO> salePickDeliveryOrderInfoList;

    @ApiModelProperty(value = "操作人")
    private String createdBy;

    //2020/08/30 用于出库单拿最顶级组织 SongTao
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;

    @ApiModelProperty(value = "拆单所属组织")
    private Long ascriptionOrgId;
}
