package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-10-21 11:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "ShopOrderItemDetailsResponseDTO")
public class ShopOrderItemDetailsResponseDTO {

    @ApiModelProperty(value = "关联的门店订单")
    private List<String> shopOrderCodes;

    @ApiModelProperty(value = "门店收货地址")
    private List<OrderConsigneeAddressResponseDTO> orderConsigneeAddressResponseDTOS;

    @ApiModelProperty(value = "明细")
    private List<ShopOrderItemResponseDTO> shopOrderItemResponseDTOS;
}
