package com.deepexi.dd.domain.transaction.domain.dto.add;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退货明细添加对象
 *
 * @author chenqili
 * @date 2020/7/7
 * @version 1.0
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
     * 销售订单明细ID
     */
//    @ApiModelProperty(value = "销售订单明细ID")
//    private Long saleOrderItem;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    /**
     * 金额小计（含税）
     */
//    @ApiModelProperty(value = "金额小计（含税）")
//    private BigDecimal totalAmount;
    /**
     * 退货数量
     */
    @ApiModelProperty(value = "退货数量")
    @Min(value = 0,message = "数量必须大于0")
    private Long returnQuantity;

}

