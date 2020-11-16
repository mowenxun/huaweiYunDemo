package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import lombok.Data;

import java.util.List;

/**
 * @ClassName : CommodityStockOperationDTO
 * @Description : 商品库存操作DTO
 * @Author : yuanzaishun
 * @Date: 2020-07-13 14:16
 */
@Data
public class CommodityStockOperationDTO extends TenantDTO {
    /**
     * 需要操作的商品信息
     */
    private List<CommodityQuantityDTO> skuQty;
    /**
     * 渠道ID
     */
    private Long channel;
    /**
     * 单据ID
     */
    private Long extendId;
    /**
     * 单据编号
     */
    private String extendNo;
    /**
     * 单据类型
     */
    private Integer extendType;
    /**
     * 店铺ID
     */
    private Long shopId;
    /**
     * 活动ID
     */
    private Long activityId;

}
