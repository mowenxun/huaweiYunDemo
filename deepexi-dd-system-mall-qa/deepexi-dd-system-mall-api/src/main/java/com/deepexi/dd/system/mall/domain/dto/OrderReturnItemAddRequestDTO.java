package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-08 19:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "退货明细添加对象")
public class OrderReturnItemAddRequestDTO extends AbstractObject implements Serializable {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long skuId;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    /**
     * 退货数量
     */
    @ApiModelProperty(value = "退货数量")
    @Min(value = 0,message = "数量必须大于0")
    private Long returnQuantity;

}