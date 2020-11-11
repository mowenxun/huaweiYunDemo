package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* OrderStatusQuery
*
* @author admin
* @date Mon Jul 13 19:13:18 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderStatusRequestQuery")
public class OrderStatusRequestQuery extends AbstractObject implements Serializable {

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
    private Integer id;
    /**
    * 订单类型
    */
    @ApiModelProperty(value = "订单类型")
    private String orderType;
    /**
    * 标识
    */
    @ApiModelProperty(value = "标识")
    private String identity;
    /**
    * 状态名
    */
    @ApiModelProperty(value = "状态名")
    private String statusName;
    /**
    * 启用
    */
    @ApiModelProperty(value = "启用")
    private String enable;
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
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 更新人
    */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
}

