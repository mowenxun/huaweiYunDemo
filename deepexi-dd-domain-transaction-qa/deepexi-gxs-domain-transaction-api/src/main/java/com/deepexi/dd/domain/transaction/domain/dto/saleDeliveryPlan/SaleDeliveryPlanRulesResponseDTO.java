package com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SaleDeliveryPlanRulesResponseDTO
 * @Description 发货计划编排规则
 * @Author SongTao
 * @Date 2020-08-12
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryPlanRulesResponseDTO")
public class SaleDeliveryPlanRulesResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -8512381701574589601L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    @ApiModelProperty(value = "适用提货方式(0:全部;1:外仓;2:小库;3:工地)")
    private String deliveryWay;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "规则")
    private String rule;

    @ApiModelProperty(value = "参数值")
    private Long value;

    @ApiModelProperty(value = "是否启用(0:启用;1:停用)")
    private Long isEnabled;
}
