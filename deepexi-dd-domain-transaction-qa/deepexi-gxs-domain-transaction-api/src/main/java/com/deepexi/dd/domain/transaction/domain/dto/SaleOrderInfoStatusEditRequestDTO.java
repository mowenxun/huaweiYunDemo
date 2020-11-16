package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

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
public class SaleOrderInfoStatusEditRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;
    /**
    * 订单Id
    */
    @ApiModelProperty(value = "订单id,数组",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;
//    @ApiModelProperty(value = "订单编码",required = true)
//    @NotEmpty
//    private String saleOrderCode;
    @NotEmpty(message = "订单状态为空")
    @ApiModelProperty(value = "订单状态",dataType = "Integer",required = true)
    private Integer status;
}

