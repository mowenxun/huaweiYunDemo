package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleOrderAscertainInfoResponseDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-27
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderAscertainRequestDTO")
public class SaleOrderAscertainInfoResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 2975771108148267427L;

    @ApiModelProperty(value = "单据类型(ordinary:普通销售单,direct:直供订单)")
    private Integer ticketType;

    @ApiModelProperty(value = "组织名称")
    private String orgName;
}
