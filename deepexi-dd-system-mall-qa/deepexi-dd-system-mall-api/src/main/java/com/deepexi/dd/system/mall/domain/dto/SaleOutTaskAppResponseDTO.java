package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleOutTaskAppResponseDTO
 * @Description 出库单列表供app使用
 * @Author SongTao
 * @Date 2020-08-10
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskAppResponseDTO")
public class SaleOutTaskAppResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 981280156088545282L;

    @ApiModelProperty(value = "订单ID")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String code;
}
