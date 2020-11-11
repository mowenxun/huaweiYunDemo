package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SaleOrderInfoDeliverGoodsMiddleRequestDTO
 * @Description 订单发货中心层对象
 * @Author SongTao
 * @Date 2020-07-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderInfSaleOrderInfoDeliverGoodsMiddleRequestDTOoRequestDTO")
public class SaleOrderInfoDeliverGoodsMiddleRequestDTO extends AbstractTenantDTO implements Serializable {
    private static final long serialVersionUID = -5372577362811144439L;

    @ApiModelProperty(value = "订单ID")
    @NotNull(message = "订单ID为空")
    private Long saleOrderId;

    @ApiModelProperty(value = "订单编码")
    @NotEmpty(message = "订单编码为空")
    private String saleOrderCode;

    @ApiModelProperty(value = "出库单编号")
    @NotEmpty(message = "出库单编号为空")
    private String saleOutTaskCode;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提)")
    @NotNull(message = "发货方式为空")
    private Integer deliveryType;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;

    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;

    @ApiModelProperty(value = "发货仓库ID")
    @NotNull(message = "发货仓库ID为空")
    private Long deliveryWareHouseId;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "发货物流信息")
    private OrderDeliveryInfoRequestDTO orderDeliveryInfo;

    @ApiModelProperty(value = "出库单送货地址信息")
    private OrderDeliveryConsigneeInfoMiddleRequestDTO orderDeliveryConsigneeInfo;

    @ApiModelProperty(value = "商品明细列表")
    @NotNull(message = "商品明细不能为空")
    @Size(min = 1,message = "未选择商品信息")
    private List<SaleOrderItemMiddleRequestDTO> items;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    //2020/08/30 用于出库单拿最顶级组织 SongTao
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;

    @ApiModelProperty(value = "拆单所属组织")
    private Long ascriptionOrgId;

}
