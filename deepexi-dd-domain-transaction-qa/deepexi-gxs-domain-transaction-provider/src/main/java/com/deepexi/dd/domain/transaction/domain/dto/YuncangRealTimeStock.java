package com.deepexi.dd.domain.transaction.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-25 15:53
 * 云仓实时库存
 */
@Data
public class YuncangRealTimeStock {

    /**
     * 物料名称
     */
    private String itemName;

    /**
     * 客户物料名称
     */
    private String customItemName;

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
     * 仓库名称
     */
    private String wareName;

    /**
     * 良品库存
     */
    private Integer actualQuantity;

    /**
     * 在途库存
     */
    private Integer transferQuantity;

    /**
     * 已分配库存（冻结库存）
     */
    private Integer assignedQuantity;

    /**
     * 次品库存
     */
    private Integer inferiorQuantity;

    /**
     * 寄存在途库存
     */
    private Integer depositoryTransferQuantity;

    /**
     * 寄存冻结库存
     */
    private Integer depositoryAssignedQuantity;

    /**
     * 可下达库存
     */
    private Integer enableOrderQuantity;

    /**
     * 品类
     */
    private String categoryName;

    /**
     * 最后一次更新时间
     */
    private Date modifyDate;

    /**
     * 实际数量
     */
    private Integer quantity;

    /**
     * 库位编码
     */
    private String storageLocationCode;

    /**
     * 库位ID
     */
    private String storageLocationId;

    /**
     * 库位名称
     */
    private String storageLocationName;

    /**
     * 可用数量
     */
    private Integer validQuantity;

}
