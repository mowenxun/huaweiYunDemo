package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* 销售订单明细查询参数对象
*
* @author admin
* @date Tue Jun 23 19:44:57 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单明细查询参数对象")
public class SaleOrderInfoDetailRequestQuery extends TenantDTO implements Serializable {

private static final long serialVersionUID = 1L;
    /**
    * 订单Id
    */
    @ApiModelProperty(value = "订单ID")
    @NotNull(message = "订单ID为空")
    private Long id;

}

