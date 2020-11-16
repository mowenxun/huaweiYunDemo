package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * SaleDeliveryPlanInfoDTO
 *
 * @author admin
 * @version 1.0
 * @date Thu Aug 13 15:26:43 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryPlanInfoDetail")
public class SaleDeliveryPlanInfoDetail extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发货计划记录
     */
    @ApiModelProperty(value = "发货计划记录")
    private SaleDeliveryPlanInfoResDTO saleDeliveryPlanInfoResponseDTO;

    /**
     * 发货计划明细
     */
    @ApiModelProperty(value = "发货计划明细")
    private List<SaleDeliveryPlanItemRespDTO> saleDeliveryPlanItemRespDTOS;
}

