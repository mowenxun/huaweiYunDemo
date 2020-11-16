package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsOrderGroupResponesDTO")
public class SalePickGoodsOrderGroupResponesDTO extends AbstractObject implements Serializable {
    @ApiModelProperty(value = "提货计划单编号")
    private String pickGoodsCode;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "提货计划单列表")
    private List<SalePickGoodsOrderSkuResponseDTO> items;
}
