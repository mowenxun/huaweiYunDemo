package com.deepexi.dd.domain.transaction.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-25 11:09
 * 出库结果
 */
@Data
public class WarehouseOutResultsDTO {


    /**
     * 一体化平台单号
     */
    private String sourceOrderNo;

    /**
     * 单据业务类型，1-销售单;2-调拨单
     */
    private Integer businessType;

    /**
     * ERP单号
     */
    private String erpOrderNo;

    /**
     * 出库仓库代码
     */
    private String sourceWarehouseCode;

    /**
     * 入库仓库代码
     */
    private String targetWarehouseCode;

    /**
     * 出库时间
     */
    private String deliveryDate;

    /**
     * 云仓销售单号
     */
    private String OrderNo;

    /**
     * 小黑卡设备ID
     */
    private String littleBlackCard;

    /**
     * 车牌号
     */
    private String licenseNumber;

    /**
     * 订单明细
     */
    private List<WarehouseOutResultsOrderItem> orderItems;
}
