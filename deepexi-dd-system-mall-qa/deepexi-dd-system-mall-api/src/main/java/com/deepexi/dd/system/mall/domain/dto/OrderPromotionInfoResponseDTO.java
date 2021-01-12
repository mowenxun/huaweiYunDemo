package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 订单活动信息
*
* @author admin
* @date Wed Jun 24 09:42:06 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单活动信息")
public class OrderPromotionInfoResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 0：未逻辑删除状态。1:删除
    */
    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;

    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
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
    @ApiModelProperty(value = "适用商品ID集合,逗号分隔")
    private String skuIds;
    /**
    * sku名称、规格
    */
    @ApiModelProperty(value = "适用商品名称,逗号分隔")
    private String skuNames;
    /**
    * 优惠金额
    */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;

}

