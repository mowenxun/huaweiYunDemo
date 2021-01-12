package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author zhouquan
 * @version 1.0
 * @date 2020-07-30 11:56
 */
@Data
public class SaleOrderReceivingAppRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;
    @ApiModelProperty(value = "备注",required = false)
    private String remark;
}
