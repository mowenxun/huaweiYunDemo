package com.deepexi.dd.domain.transaction.domain.query;

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
* @date Tue Jun 23 19:44:58 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskRequestQuery")
public class SaleOutTaskRequestQuery extends BasePage implements Serializable {

private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "出库单编号")
    private String code;

    @ApiModelProperty(value = "客户名称")
    private String buyerName;

    @ApiModelProperty(value = "是否显示红单 1：是。0：否。")
    private Integer isShowHedge;

    @ApiModelProperty(value = "单据状态")
    private List<Integer> signStatusList;

    //2020/08/01 SongTao 新加字段 供app调用使用
    @ApiModelProperty(value = "订单ID集合")
    private List<Long> saleOrderIdList;

    @ApiModelProperty(value = "发货计划ID")
    private Long saleDeliveryPlanId;
    @ApiModelProperty(value = "发货计划编号")
    private String saleDeliveryPlanCode;
    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;
    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;

}

