package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SaleOutTaskDetailInfoMiddleRequestDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskDetailInfoMiddleRequestDTO")
public class SaleOutTaskDetailInfoMiddleRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 4648254715408574293L;

    @ApiModelProperty(value = "")
    private Long id;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "应用ID")
    private Long appId;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "出库单编号")
    private String saleOutTaskCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "销售出库单ID")
    private Long saleOutTaskId;

    @ApiModelProperty(value = "商品明细ID")
    private Long saleOrderItemId;

    @ApiModelProperty(value = "商品本次出库数量")
    private Long skuShipmentQuantity;
    @ApiModelProperty(value = "商品申请提货数量")
    private Long skuPickQuantity;
}
