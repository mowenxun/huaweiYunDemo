package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* SaleOmsLogisticsTrajectoryDTO
*
* @author admin
* @date Tue Aug 25 16:23:34 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOmsLogisticsTrajectoryResponseDTO")
public class SaleOmsLogisticsTrajectoryResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

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
    * 0：未逻辑删除状态。1:删除
    */
    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
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
    * 销售订单id
    */
    @ApiModelProperty(value = "销售订单id")
    private Long saleOrderId;
    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID")
    private Long skuItemId;
    /**
    * 是否增量 0:增量推送;1:全量推送
    */
    @ApiModelProperty(value = "是否增量 true:增量推送;false:全量推送")
    private Boolean whetherIncrement;
    /**
    * 物流时间
    */
    @ApiModelProperty(value = "物流时间")
    private Date time;
    /**
    * 物流轨迹描述
    */
    @ApiModelProperty(value = "物流轨迹描述")
    private String message;

}

