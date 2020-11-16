package com.deepexi.dd.domain.transaction.domain.dto.saleOutTask;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* SaleOutTaskDTO
*
* @author admin
* @date Wed Jun 24 09:42:06 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskRequestDTO")
public class SaleOutTaskRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
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
    private Date updatedTime;
    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
    * 订单确认状态 0:已确认,1已作废
    */
    @ApiModelProperty(value = "订单确认状态 0:已确认,1已作废")
    private Integer status;
    /**
    * 单据日期
    */
    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;
    /**
    * 计划出库总数量
    */
    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;
    /**
    * 出库单类型,1原单,2蓝单,3红单
    */
    @ApiModelProperty(value = "出库单类型,1原单,2蓝单,3红单")
    private Integer taskType;
    /**
    * 红冲的原订单标识
    */
    @ApiModelProperty(value = "红冲的原订单标识")
    private Long hedgeOrder;
    /**
     * 发货物流ID
     */
    @ApiModelProperty(value = "发货物流ID")
    private Long orderDeliveryId;


}

