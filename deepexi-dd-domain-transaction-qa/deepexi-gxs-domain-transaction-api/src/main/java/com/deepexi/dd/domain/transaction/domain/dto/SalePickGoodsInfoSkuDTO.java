package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsInfoSkuDTO")
public class SalePickGoodsInfoSkuDTO {
    List<SalePickGoodsOrderSkuRequestDTO> skus;

}
