package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-08-06 17:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "关闭接口参数")
public class SaleOrderCloseAppRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;
    @ApiModelProperty(value = "actionCode",required = true)
    private String actionCode;
    @ApiModelProperty(value = "备注",required = false)
    private String remark;
}
