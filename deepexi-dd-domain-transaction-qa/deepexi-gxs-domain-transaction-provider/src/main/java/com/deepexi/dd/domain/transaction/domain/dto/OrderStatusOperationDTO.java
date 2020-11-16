package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-13 18:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单操作对象")
public class OrderStatusOperationDTO extends AbstractTenantDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Long id;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer statusIdentity;
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
