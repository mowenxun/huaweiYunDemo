package com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* 商品明细添加对象
*
* @author yuanzaishun
* @date Tue Jun 23 19:44:58 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "商品明细添加对象")
public class SaleOrderItemRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID",required = true)
    @NotNull(message = "商品ID为空")
    @Range(min = 1,message = "商品ID错误")
    private Long skuId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称",required = true)
    @NotEmpty(message = "商品名称为空")
    private String skuName;
    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号")
    private String skuCode;
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格",required = true)
    @NotEmpty(message = "商品名称为空")
    private String skuFormat;

    /**
    * 商品数量
    */
    @ApiModelProperty(value = "商品数量",required = true)
    @NotNull(message = "商品数量为空")
    @Range(min = 1,message = "商品数量错误")
    private Long skuQuantity;
    /**
    * 税率
    */
    @ApiModelProperty(value = "税率",required = true)
    @NotNull(message = "税率为空")
    @Range(min = 0,message = "税率错误")
    private BigDecimal taxRate;
    /**
    * 单价
    */
    @ApiModelProperty(value = "单价",required = true)
    @NotNull(message = "单价为空")
    @Range(min = 0,message = "单价错误")
    private BigDecimal price;

    /**
    * 价格政策标识
    */
    @ApiModelProperty(value = "价格政策标识,如果是手动输入价格，则为空")
    private Long pricePolicyId;

    //2020/07/15 SongTao 用来记录商品本次出库数量
    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;

}

