package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* SaleOrderItemQuery
*
* @author admin
* @date Tue Jun 23 19:44:58 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderItemRequestQuery")
public class SaleOrderItemRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "APP标识")
    private Long appId;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    //补充字段 SongTao  2020/07/10
    @ApiModelProperty(value = "出库单ID")
    private Long saleOutTaskId;
}

