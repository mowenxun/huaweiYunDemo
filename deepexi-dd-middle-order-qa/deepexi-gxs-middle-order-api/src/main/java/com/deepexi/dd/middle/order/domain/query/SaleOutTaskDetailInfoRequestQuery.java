package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* SaleOutTaskDetailInfoQuery
*
* @author admin
* @date Thu Sep 17 17:01:09 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskDetailInfoRequestQuery")
public class SaleOutTaskDetailInfoRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

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
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
    * 提货单ID
    */
    @ApiModelProperty(value = "提货单ID")
    private Long salePickGoodsId;
    /**
    * 提货单编号
    */
    @ApiModelProperty(value = "提货单编号")
    private String salePickGoodsCode;
    /**
    * 发货计划ID
    */
    @ApiModelProperty(value = "发货计划ID")
    private Long saleDeliveryPlanId;
    /**
    * 发货计划编号
    */
    @ApiModelProperty(value = "发货计划编号")
    private String saleDeliveryPlanCode;
    /**
    * 销售出库单ID
    */
    @ApiModelProperty(value = "销售出库单ID")
    private Long saleOutTaskId;
    /**
    * 商品明细ID
    */
    @ApiModelProperty(value = "商品明细ID")
    private Long saleOrderItemId;
    /**
    * 商品本次出库数量
    */
    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;
    /**
    * 商品本次申请数量
    */
    @ApiModelProperty(value = "商品本次申请数量")
    private Long skuPickQuantity;
}

