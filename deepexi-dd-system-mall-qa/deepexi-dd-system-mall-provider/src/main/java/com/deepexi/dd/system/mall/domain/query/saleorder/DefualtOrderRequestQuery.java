package com.deepexi.dd.system.mall.domain.query.saleorder;

import com.deepexi.dd.system.mall.domain.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName DefualtOrderRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-05
 * @Version 1.0
 **/
@Data
@ApiModel
public class DefualtOrderRequestQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -1420490681701020521L;

    @ApiModelProperty(value = "单据类型(0:直供订单,1:普通销售单)", required = true)
    @NotNull(message = "单据类型为空")
    private Integer ticketType;

    @ApiModelProperty(value = "组织id(单据类型为非直供必填)")
    private Long orgId;
}
