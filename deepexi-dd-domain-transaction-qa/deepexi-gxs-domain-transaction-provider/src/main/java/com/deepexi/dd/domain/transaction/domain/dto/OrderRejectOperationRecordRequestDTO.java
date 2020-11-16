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
@ApiModel(description = "OrderRejectOperationRecordRequestDTO")
public class OrderRejectOperationRecordRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
}