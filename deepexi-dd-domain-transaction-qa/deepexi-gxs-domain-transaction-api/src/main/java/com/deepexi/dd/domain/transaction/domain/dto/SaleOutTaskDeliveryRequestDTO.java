package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by liaop on 2020/7/7.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "发货接口参数")
public class SaleOutTaskDeliveryRequestDTO {

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "出库单编号")
    private String code;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;

    @ApiModelProperty(value = "出库单类型,1原单,2蓝单,3红单")
    private Integer taskType;

    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;

    @ApiModelProperty(value = "红冲的原订单标识")
    private Long hedgeOrder;

    @ApiModelProperty(value = "发货物流ID")
    private Long orderDeliveryId;

    @ApiModelProperty(value = "收货地址")
    private String deliveryAddress;

    @ApiModelProperty(value = "联系人")
    private String deliveryConsignee;

    @ApiModelProperty(value = "联系电话")
    private String deliveryMobilePhone;
}
