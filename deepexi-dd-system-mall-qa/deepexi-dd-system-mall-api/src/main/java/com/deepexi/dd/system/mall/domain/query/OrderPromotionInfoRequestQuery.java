package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* OrderPromotionInfoQuery
*
* @author admin
* @date Tue Jun 23 19:44:58 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderPromotionInfoRequestQuery")
public class OrderPromotionInfoRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 页码
    */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
    * 页数
    */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * 应用ID
    */
    @ApiModelProperty(value = "应用ID")
    private Long appId;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Date createdTime;
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
    @ApiModelProperty(value = "sku商品ID集合")
    private String skuIds;
    /**
    * sku名称、规格
    */
    @ApiModelProperty(value = "sku名称、规格")
    private String skuNames;
}

