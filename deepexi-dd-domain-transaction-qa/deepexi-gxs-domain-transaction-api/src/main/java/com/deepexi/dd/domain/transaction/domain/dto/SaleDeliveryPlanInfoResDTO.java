package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* SaleDeliveryPlanInfoDTO
*
* @author admin
* @date Thu Aug 13 15:26:43 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryPlanInfoRespDTO")
public class SaleDeliveryPlanInfoResDTO extends AbstractObject implements Serializable {

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
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
    /**
    * 发货计划编号
    */
    @ApiModelProperty(value = "发货计划编号")
    private String code;
    /**
    * 发货计划状态1成功,0失败
    */
    @ApiModelProperty(value = "发货计划状态1成功,0失败")
    private Integer status;
    /**
    * 执行结果描述
    */
    @ApiModelProperty(value = "执行结果描述")
    private String reason;
    /**
    * 编排主表ID
    */
    @ApiModelProperty(value = "编排主表ID")
    private Long saleDeliveryPlanMaid;
    /**
    * 编排主表编号
    */
    @ApiModelProperty(value = "编排主表编号")
    private String saleDeliveryPlanCode;

}

