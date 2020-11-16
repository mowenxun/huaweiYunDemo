package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderOperationsRequestDTO")
public class OrderOperationsRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("应用ID")
    private Long appId;
    @ApiModelProperty("版本号")
    private Integer version;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("是否删除,0否,1是")
    private Boolean deleted;
    @ApiModelProperty("创建时间")
    private Date createdTime;
    @ApiModelProperty("更新时间")
    private Date updatedTime;
    @ApiModelProperty("订单ID")
    private Long orderId;
    @ApiModelProperty("订单编码")
    private String orderCode;
    @ApiModelProperty("操作类型")
    private Integer operationType;
    @ApiModelProperty("创建人")
    private String createdBy;
    @ApiModelProperty("动作")
    private String operation;
    private Integer page = -1;
    private Integer size = 10;
}
