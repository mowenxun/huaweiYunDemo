package com.deepexi.dd.system.mall.domain.dto.add;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

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
public class SaleOrderItemAddRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID",required = true)
    @NotNull(message = "商品ID为空")
    @Range(min = 1,message = "商品ID错误")
    private Long skuId;




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
    private BigDecimal taxRate;
    /**
    * 单价
    */
    @ApiModelProperty(value = "单价",required = true)
    private BigDecimal price;

    /**
    * 价格政策标识
    */
    @ApiModelProperty(value = "价格政策标识,如果是手动输入价格，则为空")
    private Long pricePolicyId;

    @ApiModelProperty(value = "店铺ID,必否则，不能获取商品详情,库存",required = true)
    @NotNull(message = "店铺ID为空")
    @Range(min = 1,message = "店铺ID错误")
    private Long shopId;

    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID")
    @NotNull(message = "商品所属组织为空")
    private Long orgId;

    //活动Id
    @ApiModelProperty(value = "活动Id")
    private Long activitiesId;

    //直供单Id
    @ApiModelProperty(value = "直供单Id")
    private Long directId;
}

