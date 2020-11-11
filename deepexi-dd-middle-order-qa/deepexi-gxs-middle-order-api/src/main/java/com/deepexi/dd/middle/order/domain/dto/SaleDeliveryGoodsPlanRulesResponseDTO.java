package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* SaleDeliveryGoodsPlanRulesDTO
*
* @author admin
* @date Wed Aug 12 14:26:43 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryGoodsPlanRulesResponseDTO")
public class SaleDeliveryGoodsPlanRulesResponseDTO extends AbstractObject implements Serializable {

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
    * 适用提货方式(0:全部;1:外仓;2:小库;3:工地)
    */
    @ApiModelProperty(value = "适用提货方式(0:全部;1:外仓;2:小库;3:工地)")
    private String deliveryWay;
    /**
    * 规则名称
    */
    @ApiModelProperty(value = "规则名称")
    private String ruleName;
    /**
    * 规则
    */
    @ApiModelProperty(value = "规则")
    private String rule;
    /**
    * 参数值
    */
    @ApiModelProperty(value = "参数值")
    private Long value;
    /**
    * 是否启用(0:启用;1:停用)
    */
    @ApiModelProperty(value = "是否启用(0:启用;1:停用)")
    private Long isEnabled;

}

