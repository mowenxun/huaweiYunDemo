package com.deepexi.dd.middle.order.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Description;

import java.util.HashMap;
import java.util.List;

@Description("该dto用于传输方法和方法之间的数据")
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsInfoTransferDTO")
public class SalePickGoodsInfoTransferDTO {

    //数据库中的更新前的提货单信息
    private SalePickGoodsInfoResponseDTO oldPickInfo;

    //数据库中的更新前的提货单sku信息
    private List<SalePickGoodsOrderSkuResponseDTO> oldSkus;

    //数据库中的更新前的提货sku对应的销售sku的信息
    private List<SaleOrderItemMiddleResponseDTO> oldSaleSkus;

    //按销售订单id整理的销售订单map（目前主要用于校验和更新数据前获取数据）
    HashMap<String, SaleOrderResponseDTO> saleOrderMap;

    //保存或更新对象
    SalePickGoodsInfoRequestDTO recordObj;

    //一级组织id
    Long ascriptionOrgId;

    Long buyerId;

    String buyerName;

    Long customerId;

    String customerName;

    Long sellerId;

    String sellerName;
    /**
     * 保存save、更新update、close:关闭、cancel:取消
     */
    String type;


}
