package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SalePickDeliveryLogisticsInfoMiddleRequestDTO
 * @Description 提货计划发货的物流信息
 * @Author SongTao
 * @Date 2020-08-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickDeliveryLogisticsInfoMiddleRequestDTO")
public class SalePickDeliveryLogisticsInfoMiddleRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -1025712678202260459L;

    @ApiModelProperty(value = "物流公司名称")
    private String deliveryName;

    @ApiModelProperty(value = "物流编号")
    private String deliveryCode;

    @ApiModelProperty(value = "车牌号码")
    private String licensePlate;

    @ApiModelProperty(value = "司机名称")
    private String driver;

    @ApiModelProperty(value = "司机电话")
    private String driverMobile;

    @ApiModelProperty(value = "身份证号码（司机）")
    private String idCardNum;
}
