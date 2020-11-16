package com.deepexi.dd.domain.transaction.domain.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-25 15:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseQuery {

    private Integer pageSize;

    private Integer PageNumber;

    /**
     * 物料代码
     */
    private String itemCode;

    /**
     * 客户化物料代码
     */
    private String customItemCode;

    /**
     * 条码头
     */
    private String barcodeHeader;

    /**
     * 客户物料名称
     */
    private String customItemName;

    /**
     * 物料名称
     */
    private String itemName;

    /**
     * 仓库代码
     */
    private String wareCode;

    /**
     * 仓库名称
     */
    private String wareName;
    /**
     * 发货数量
     */
    private Integer shipmentQuantity;

}
