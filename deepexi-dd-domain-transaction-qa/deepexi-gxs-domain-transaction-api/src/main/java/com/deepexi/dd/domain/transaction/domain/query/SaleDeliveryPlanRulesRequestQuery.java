package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SaleDeliveryPlanRulesRequestQuery
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-12
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryPlanRulesRequestQuery")
public class SaleDeliveryPlanRulesRequestQuery extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -8484294270977371031L;

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
