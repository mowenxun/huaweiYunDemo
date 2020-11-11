package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* OrderStatusOperationDTO
*
* @author admin
* @date Mon Jul 13 19:13:18 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderStatusOperationRequestDTO")
public class OrderStatusOperationRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Integer id;
    /**
    * 
    */
    @ApiModelProperty(value = "")
    private String statusIdentity;
    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long statusId;
    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long operationId;
    /**
    * 
    */
    @ApiModelProperty(value = "")
    private String operationName;
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
    /**
     * 入口类型
     */
    @ApiModelProperty(value = "入口类型")
    private String portal;

}

