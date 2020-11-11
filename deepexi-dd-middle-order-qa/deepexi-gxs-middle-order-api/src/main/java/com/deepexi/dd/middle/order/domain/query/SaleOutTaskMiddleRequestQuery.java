package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
* SaleOutTaskQuery
*
* @author admin
* @date Wed Jun 24 09:42:06 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskMiddleRequestQuery")
public class SaleOutTaskMiddleRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    @ApiModelProperty(value = "")
    private Long id;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "应用ID")
    private Long appId;

    @ApiModelProperty(value = "出库单编号")
    private String code;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "是否红冲。1：是。0：否。")
    private Integer isShowHedge;

    @ApiModelProperty(value = "客户名称")
    private String buyerName ;

    @ApiModelProperty(value = "订单ID集合")
    private List<Long> saleOrderIdList;

    @ApiModelProperty(value = "单据状态")
    private List<Integer> signStatusList;

    @ApiModelProperty(value = "单据开始时间")
    private String createTimeFrom;

    @ApiModelProperty(value = "单据结束时间")
    private String createTimeTo;

    @ApiModelProperty(value = "发货计划ID")
    private Long saleDeliveryPlanId;
    @ApiModelProperty(value = "发货计划编号")
    private String saleDeliveryPlanCode;
    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;
    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;

    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;

    @ApiModelProperty(value = "拆单所属组织")
    private Long ascriptionOrgId;
}

