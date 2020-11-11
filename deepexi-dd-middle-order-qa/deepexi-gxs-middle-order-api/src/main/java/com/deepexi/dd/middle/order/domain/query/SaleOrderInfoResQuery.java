package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * SaleOrderInfoResQuery
 *
 * @author hezhijian
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderInfoResQuery")
public class SaleOrderInfoResQuery extends AbstractObject implements Serializable {

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
     * 订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消
     */
    @ApiModelProperty(value = "订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消")
    private Integer status;

    /**
     * 不等于该状态
     */
    @ApiModelProperty(value = "不等于该状态")
    private Integer notStatus;

    /**
     * 隔离标识
     */
    @ApiModelProperty(value = "隔离标识")
    private String isolationId;

    /**
     * 合同ID
     */
    @ApiModelProperty("合同ID")
    private Long contractId;

    /**
     * 项目ID
     */
    @ApiModelProperty("项目ID")
    private Long projectId;
}

