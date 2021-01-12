package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-24 10:00
 */
public class SaleOrderAcceptAppRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;
    @ApiModelProperty(value = "备注",required = false)
    private String remark;
}
