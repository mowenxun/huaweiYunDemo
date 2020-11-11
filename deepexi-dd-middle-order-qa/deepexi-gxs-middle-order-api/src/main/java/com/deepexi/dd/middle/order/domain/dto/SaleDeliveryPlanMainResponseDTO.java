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
* SaleDeliveryPlanMainDTO
*
* @author admin
* @date Thu Aug 13 15:26:43 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryPlanMainResponseDTO")
public class SaleDeliveryPlanMainResponseDTO extends AbstractObject implements Serializable {

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
    * 编排编号
    */
    @ApiModelProperty(value = "编排编号")
    private String code;
    /**
    * 编排数量
    */
    @ApiModelProperty(value = "编排数量")
    private Long quantity;

}

