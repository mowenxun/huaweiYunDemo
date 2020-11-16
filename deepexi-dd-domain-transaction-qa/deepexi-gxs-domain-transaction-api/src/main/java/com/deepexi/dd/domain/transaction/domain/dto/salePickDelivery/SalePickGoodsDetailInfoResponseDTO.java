package com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SalePickGoodsDetailInfoResponseDTO
 * @Description 提货单发货详情里的订单列表
 * @Author SongTao
 * @Date 2020-08-21
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsDetailInfoResponseDTO")
public class SalePickGoodsDetailInfoResponseDTO extends AbstractObject implements Serializable {

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "发货仓库Id")
    private Long deliveryWareHouseId;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "是云仓，发货时不可编辑数量")
    private Boolean editable;

    @ApiModelProperty(value = "单据类型(1:普通销售单,0:直供订单,2:非标准订单;3:订货计划单)")
    private String orderType;

    @ApiModelProperty(value = "商品明细列表")
    private List<SalePickGoodsItemInfoResponseDTO> salePickGoodsItemInfoList;
}
