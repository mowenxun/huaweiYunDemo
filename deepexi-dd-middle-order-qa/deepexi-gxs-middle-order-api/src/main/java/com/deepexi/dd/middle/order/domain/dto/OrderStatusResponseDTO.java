package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* OrderStatusDTO
*
* @author admin
* @date Mon Jul 13 19:13:18 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderStatusResponseDTO")
public class OrderStatusResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

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
    * 是否删除,0否,1是
    */
    @ApiModelProperty(value = "是否删除,0否,1是")
    private Boolean deleted;
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

