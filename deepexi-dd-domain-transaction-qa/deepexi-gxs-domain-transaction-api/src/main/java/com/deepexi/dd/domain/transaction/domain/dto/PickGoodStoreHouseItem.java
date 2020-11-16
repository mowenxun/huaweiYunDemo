package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PickGoodStoreHouseItem")
public class PickGoodStoreHouseItem {
    /**
     * 仓库id
     */
    @ApiModelProperty(value = "仓库id")
    private String storeHouseId;

    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称")
    private String storeHouseName;

    /**
     * 仓库地址
     */
    @ApiModelProperty(value = "仓库地址")
    private String warehouseAddress;
    /**
     * 仓库联系人
     */
    @ApiModelProperty(value = "仓库联系人")
    private String warehouseLinkman;
    /**
     * 仓库联系方式
     */
    @ApiModelProperty(value = "仓库联系方式")
    private String warehouseLinkway;
}
