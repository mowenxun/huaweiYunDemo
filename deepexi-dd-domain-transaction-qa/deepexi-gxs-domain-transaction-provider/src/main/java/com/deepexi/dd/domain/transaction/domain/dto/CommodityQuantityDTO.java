package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;

/**
 * @ClassName : CommodityQuanlityDTO
 * @Description : 商品数量DTO
 * @Author : yuanzaishun
 * @Date: 2020-07-13 14:18
 */
@Data
public class CommodityQuantityDTO extends AbstractObject {
    /**
     * SKUID
     */
    private Long skuId;

    /**
     * 数量
     */
    private Integer skuQty;
}
