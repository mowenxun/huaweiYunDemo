package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-10-19 16:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "DistributionCheckResponseDTO")
public class DistributionCheckResponseDTO {

    @ApiModelProperty(value = "所需配货订单号")
    private List<String> supplierOrderCodes;

    @ApiModelProperty(value = "收货供销社 ")
    private String receiveDistributionName;

    @ApiModelProperty(value = "店铺订单")
    private List<ShopOrderResponseDTO> shopOrderResponseDTOS;
}
