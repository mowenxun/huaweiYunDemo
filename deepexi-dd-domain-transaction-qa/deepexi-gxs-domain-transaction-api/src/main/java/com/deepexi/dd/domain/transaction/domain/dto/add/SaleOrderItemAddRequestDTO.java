package com.deepexi.dd.domain.transaction.domain.dto.add;

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
public class SaleOrderItemAddRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID",required = true)
    @NotNull(message = "商品ID为空")
    @Range(min = 1,message = "商品ID错误")
    private Long skuId;

    @ApiModelProperty(value = "店铺ID,必否则，不能获取商品详情,库存",required = true)
    @NotNull(message = "店铺ID为空")
    @Range(min = 1,message = "店铺ID错误")
    private Long shopId;


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
    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID",required = true)
    @NotNull(message = "商品所属组织为空")
    private Long orgId;

    //2020/07/15 SongTao 添加商品关联的本次出库数量字段
    @ApiModelProperty(value = "商品本次出库数量")
    @NotNull(message = "商品本次出库数量为空")
    private Long skuShipmentQuantity;

    //活动Id
    @ApiModelProperty(value = "活动Id")
    private Long activitiesId;

    //直供单Id
    @ApiModelProperty(value = "直供单Id")
    private Long directId;
}

