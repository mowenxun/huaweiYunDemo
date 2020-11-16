package com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleOrderAscertainResponseDTO
 * @Description 确认订单的订单信息
 * @Author SongTao
 * @Date 2020-07-27
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderAscertainRequestDTO")
public class SaleOrderAscertainResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -6128585874082154919L;

    @ApiModelProperty(value = "单据类型(ordinary:普通销售单,direct:直供订单)")
    private Integer ticketType;

    @ApiModelProperty(value = "组织名称")
    private String orgName;
}
