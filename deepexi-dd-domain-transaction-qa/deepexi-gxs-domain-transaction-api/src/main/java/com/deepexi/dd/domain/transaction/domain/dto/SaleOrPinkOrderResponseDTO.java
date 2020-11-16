package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.SaleOrPinkOrderItemResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrPinkOrderResponseDTO")
public class SaleOrPinkOrderResponseDTO extends AbstractObject implements Serializable {

    List<SaleOrPinkOrderItemResponseDTO> saleOrPinkList;

}
