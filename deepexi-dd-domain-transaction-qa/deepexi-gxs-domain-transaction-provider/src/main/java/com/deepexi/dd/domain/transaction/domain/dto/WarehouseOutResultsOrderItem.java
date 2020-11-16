package com.deepexi.dd.domain.transaction.domain.dto;

import lombok.Data;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-25 11:16
 * 云仓确认出库回传 订单明细
 */
@Data
public class WarehouseOutResultsOrderItem {

    /**
     * 一体化平台订单行ID
     */
    private String sourceOrderLineId;

    /**
     * 云仓销售单行号
     */
    private String OrderLineId;

    /**
     * 套机物料代码，最大长度16位
     */
    private String setCode;

    /**
     * 单机物料代码
     */
    private String itemCode;

    /**
     * 本次出库数量
     */
    private Long deliveredQuantity;

    /**
     * 申请数量
     */
    private Long orderQuantity;
}
