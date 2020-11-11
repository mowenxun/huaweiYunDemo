package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* SaleOrderItemLogisticsQuery
*
* @author admin
* @date Sat Aug 22 16:34:04 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderItemLogisticsRequestQuery")
public class SaleOrderItemLogisticsRequestQuery extends AbstractObject implements Serializable {

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
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * APP标识
    */
    @ApiModelProperty(value = "APP标识")
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
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 第三方子单号
    */
    @ApiModelProperty(value = "第三方子单号")
    private String subExternalOrderCode;
    /**
    * 状态码
    */
    @ApiModelProperty(value = "状态码")
    private String orderItemState;
    /**
    * 物流名称
    */
    @ApiModelProperty(value = "物流名称")
    private String scac;
    /**
    * 物流单号
    */
    @ApiModelProperty(value = "物流单号")
    private String trackingNo;
}

