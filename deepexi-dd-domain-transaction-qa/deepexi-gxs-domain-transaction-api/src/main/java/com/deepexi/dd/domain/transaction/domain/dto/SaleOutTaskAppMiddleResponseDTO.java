package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleOutTaskAppMiddleResponseDTO
 * @Description 出库单列表供app使用
 * @Author SongTao
 * @Date 2020-08-10
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskAppMiddleResponseDTO")
public class SaleOutTaskAppMiddleResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 7231526347081161967L;

    @ApiModelProperty(value = "订单ID")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String code;

    @ApiModelProperty(value = "签收状态(17:待收货；19:已签收)")
    private Integer signStatus;
}
