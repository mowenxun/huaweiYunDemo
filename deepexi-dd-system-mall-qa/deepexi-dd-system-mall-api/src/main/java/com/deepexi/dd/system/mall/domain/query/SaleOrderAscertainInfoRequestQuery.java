package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName SaleOrderAscertainInfoRequestQuery
 * @Description 确认订单的订单信息
 * @Author SongTao
 * @Date 2020-07-27
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderAscertainInfoRequestQuery")
public class SaleOrderAscertainInfoRequestQuery extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -3067838004311171159L;

    @ApiModelProperty(value = "单据类型(0:直供订单,1:普通销售单)",required = true)
    @NotNull(message = "单据类型为空")
    private Integer ticketType;

    @ApiModelProperty(value = "组织id(单据类型为非直供必填)")
    private Long orgId;
}
