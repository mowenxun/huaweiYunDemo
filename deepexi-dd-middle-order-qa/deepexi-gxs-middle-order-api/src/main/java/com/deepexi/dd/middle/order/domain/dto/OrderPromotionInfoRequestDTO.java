package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 促销活动
*
* @author admin
* @date Wed Jun 24 09:42:06 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "促销活动")
public class OrderPromotionInfoRequestDTO extends AbstractTenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
    * 促销类型
    */
    @ApiModelProperty(value = "促销类型")
    private Integer promotionType;
    /**
    * 促销ID
    */
    @ApiModelProperty(value = "促销ID")
    private Long promotionId;
    /**
    * 促销名称
    */
    @ApiModelProperty(value = "促销名称")
    private String promotionName;
    /**
    * 促销描述
    */
    @ApiModelProperty(value = "促销描述")
    private String promotionSpec;
    /**
    * 促销规则
    */
    @ApiModelProperty(value = "促销规则")
    private String promotionRule;
    /**
    * 促销开始时间
    */
    @ApiModelProperty(value = "促销开始时间")
    private Date startTime;
    /**
    * 促销结束时间
    */
    @ApiModelProperty(value = "促销结束时间")
    private Date endTime;
    /**
    * sku商品ID集合
    */
    @ApiModelProperty(value = "sku商品ID集合")
    private String skuIds;
    /**
    * sku名称、规格
    */
    @ApiModelProperty(value = "sku名称、规格")
    private String skuNames;
    /**
    * 优惠金额
    */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;

}

