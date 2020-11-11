package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* SaleOutTaskDTO
*
* @author admin
* @date Wed Jun 24 09:42:06 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskMiddleRequestDTO")
public class SaleOutTaskMiddleRequestDTO extends AbstractTenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "出库单编号")
    private String code;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;

    @ApiModelProperty(value = "出库单类型,1原单,2蓝单,3红单")
    private Integer taskType;

    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;

    @ApiModelProperty(value = "红冲的原订单标识")
    private Long hedgeOrder;

    @ApiModelProperty(value = "订单确认状态 0:已确认,1已作废")
    private Integer status;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "发货物流信息")
    private OrderDeliveryInfoRequestDTO orderDeliveryInfo;

    @ApiModelProperty(value = "商品明细")
    private List<SaleOutTaskDetailInfoMiddleRequestDTO> saleOrderDetailInfo;

    @ApiModelProperty(value = "订单送货地址信息")
    private OrderDeliveryConsigneeInfoMiddleRequestDTO orderDeliveryConsigneeInfo;

    //2020/07/09 新加字段 SongTao
    @ApiModelProperty(value = "发货仓库ID")
    private Long deliveryWareHouseId;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提)")
    private Integer deliveryType;

    @ApiModelProperty(value = "签收状态(17:待收货；19:已签收)")
    private Integer signStatus;

    @ApiModelProperty(value = "商品出库总数量")
    private Long deliveryQuantity;

    //2020/07/13 SongTao 用来接收签收的请求参
    @ApiModelProperty(value = "id集合")
    private List<Long> idList;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;
    @ApiModelProperty(value = "发货计划ID")
    private Long saleDeliveryPlanId;
    @ApiModelProperty(value = "发货计划编号")
    private String saleDeliveryPlanCode;
    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;
    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;

    @ApiModelProperty(value = "数据隔离ID")
    private String isolationId;

    @ApiModelProperty(value = "拆单所属组织")
    private Long ascriptionOrgId;
}

