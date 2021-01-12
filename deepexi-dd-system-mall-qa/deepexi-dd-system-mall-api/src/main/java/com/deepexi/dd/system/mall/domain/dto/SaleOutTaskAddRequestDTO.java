package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.dd.system.mall.domain.TenantDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售出库单添加对象
 *
 * @author chenqili
 * @version 1.0
 * @date 2020-06-30 10:28
 */
public class SaleOutTaskAddRequestDTO extends TenantDTO implements Serializable {
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
     * 出库单编号
     */
    @ApiModelProperty(value = "出库单编号")
    private String code;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;
    /**
     * 计划出库总数量
     */
    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;

    /**
     * 发货物流ID
     */
    @ApiModelProperty(value = "发货物流ID")
    private Long orderDeliveryId;

}
