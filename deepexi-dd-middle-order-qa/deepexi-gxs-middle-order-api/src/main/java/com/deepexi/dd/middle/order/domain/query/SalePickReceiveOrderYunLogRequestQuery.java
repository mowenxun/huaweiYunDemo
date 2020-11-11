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
* SalePickReceiveOrderYunLogQuery
*
* @author admin
* @date Wed Sep 23 13:47:55 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickReceiveOrderYunLogRequestQuery")
public class SalePickReceiveOrderYunLogRequestQuery extends AbstractObject implements Serializable {

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

