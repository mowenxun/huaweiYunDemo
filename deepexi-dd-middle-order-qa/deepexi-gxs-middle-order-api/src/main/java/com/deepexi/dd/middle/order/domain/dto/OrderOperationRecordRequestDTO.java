package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* OrderOperationRecordDTO
*
* @author admin
* @date Wed Jul 29 15:12:50 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderOperationRecordRequestDTO")
public class OrderOperationRecordRequestDTO extends AbstractObject implements Serializable {

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
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单编码
     */
    @ApiModelProperty(value = "订单编码")
    private String orderCode;

    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型")
    private Integer operationType;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 动作
    */
    @ApiModelProperty(value = "动作")
    private String operation;

    /**
     * 分页参数
     */
    private Integer page = -1;
    private Integer size = 10;

    @ApiModelProperty(value = "操作Code")
    private String actionCode;
    @ApiModelProperty(value = "取消原因")
    private String radioName;
}

