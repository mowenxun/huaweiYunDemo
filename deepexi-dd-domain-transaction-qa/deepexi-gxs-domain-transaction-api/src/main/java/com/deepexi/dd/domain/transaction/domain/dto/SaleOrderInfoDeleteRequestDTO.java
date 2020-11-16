package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
* 销售订单删除参数对象
*
* @author admin
* @date Tue Jun 23 19:44:57 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订单删除参数对象")
public class SaleOrderInfoDeleteRequestDTO extends TenantDTO implements Serializable {

private static final long serialVersionUID = 1L;
    /**
    * 订单Id
    */
    @ApiModelProperty(value = "订单id,数组")
    @Size(min = 1,message = "订单ID错误")
    private List<Long> ids;

}

