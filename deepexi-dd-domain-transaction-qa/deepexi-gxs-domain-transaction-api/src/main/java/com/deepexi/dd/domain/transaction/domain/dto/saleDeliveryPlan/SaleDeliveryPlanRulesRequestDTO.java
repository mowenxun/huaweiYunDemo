package com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan;

import com.deepexi.dd.domain.transaction.domain.AbstractTenantDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleDeliveryPlanRulesRequestDTO
 * @Description 发货计划编排规则
 * @Author SongTao
 * @Date 2020-08-12
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryPlanRulesResponseDTO")
public class SaleDeliveryPlanRulesRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -1397628087528512653L;

    @ApiModelProperty(value = "租户id")
    private String tenantId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "适用提货方式(0:全部;1:外仓;2:小库;3:工地)",required = true)
    private String deliveryWay;

    @ApiModelProperty(value = "规则名称",required = true)
    private String ruleName;

    @ApiModelProperty(value = "规则",required = true)
    private String rule;

    @ApiModelProperty(value = "参数值")
    private Long value;

    @ApiModelProperty(value = "是否启用(0:启用;1:停用)")
    private Long isEnabled;
}
