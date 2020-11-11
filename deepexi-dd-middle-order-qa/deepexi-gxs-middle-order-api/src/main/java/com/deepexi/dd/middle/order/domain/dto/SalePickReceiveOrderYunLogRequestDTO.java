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
* SalePickReceiveOrderYunLogDTO
*
* @author admin
* @date Wed Sep 23 13:47:55 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickReceiveOrderYunLogRequestDTO")
public class SalePickReceiveOrderYunLogRequestDTO extends AbstractObject implements Serializable {

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
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private String updatedTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 接收的消息体
    */
    @ApiModelProperty(value = "接收的消息体")
    private String receiveBody;
    /**
    * 提货单号
    */
    @ApiModelProperty(value = "提货单号")
    private String pickOrderCode;
    /**
    * 0000-成功
    */
    @ApiModelProperty(value = "0000-成功")
    private String resultCode;

    /**
     * 操作类型  1 云仓取消订单 2 云仓发货
     */
    @ApiModelProperty(value = "操作类型")
    private Integer operationType;
}

