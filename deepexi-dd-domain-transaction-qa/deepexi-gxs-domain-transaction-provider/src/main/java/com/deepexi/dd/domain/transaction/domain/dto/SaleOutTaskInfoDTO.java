package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SaleOutTaskInfoDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-03
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售出库单")
public class SaleOutTaskInfoDTO extends AbstractDTO implements Serializable{

    private static final long serialVersionUID = 6206302371017863469L;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "应用ID")
    private Long appId;

    @ApiModelProperty(value = "删除状态（0:未删除/1:删除）")
    private Boolean deleted;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "订单确认状态 0:已确认,1已作废")
    private Integer status;

    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;

    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;

    @ApiModelProperty(value = "出库单类型,1原单,2蓝单,3红单")
    private Integer taskType;

    @ApiModelProperty(value = "红冲的原订单标识")
    private Long hedgeOrder;

    @ApiModelProperty(value = "发货物流ID")
    private Long orderDeliveryId;
}
