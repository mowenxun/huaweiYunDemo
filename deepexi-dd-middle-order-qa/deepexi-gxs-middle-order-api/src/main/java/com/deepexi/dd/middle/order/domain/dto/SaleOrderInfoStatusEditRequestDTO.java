package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
* 销售订单审核参数对象
*
* @author admin
* @date Tue Jun 23 19:44:57 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单审核参数对象")
public class SaleOrderInfoStatusEditRequestDTO extends TenantDTO implements Serializable {

private static final long serialVersionUID = 1L;
    /**
    * 订单Id
    */
    @ApiModelProperty(value = "订单id,数组",required = true)
    @Size(min = 1,message = "订单ID错误")
    private Long id;
    @NotEmpty(message = "订单状态为空")
    @ApiModelProperty(value = "订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消",dataType = "String",required = true)
    private Integer status;
}

