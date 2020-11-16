package com.deepexi.dd.domain.transaction.domain.dto.saleOutTask;

import com.deepexi.dd.domain.transaction.domain.AbstractTenantDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTaskDetailInfo.SaleOutTaskDetailInfoRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description:  销售出货单新增DTO对象.
 * @Param:
 * @return:
 * @Author: SongTao
 * @Date: 2020/7/3
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskAddRequestDTO")
public class SaleOutTaskAddRequestDTO extends AbstractTenantDTO implements Serializable {

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "出库单编号")
    private String code;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;

    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;

    @ApiModelProperty(value = "发货物流ID")
    private Long orderDeliveryId;

    @ApiModelProperty(value = "订单确认状态 0:已确认,1已作废")
    private Integer status;

    @ApiModelProperty(value = "商品明细")
    private List<SaleOutTaskDetailInfoRequestDTO> saleOrderDetailInfo;

    //2020/07/09 新加字段 SongTao
    @ApiModelProperty(value = "发货仓库ID")
    private Long deliveryWareHouseId;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提)")
    private Integer deliveryType;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "签收状态(17:待收货；19:已签收)")
    private Integer signStatus;

}
