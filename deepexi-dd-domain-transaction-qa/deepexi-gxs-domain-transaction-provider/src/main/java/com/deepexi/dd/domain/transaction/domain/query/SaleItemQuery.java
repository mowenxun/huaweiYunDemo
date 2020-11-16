package com.deepexi.dd.domain.transaction.domain.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName : SaleItemQuery
 * @Description : 商品查询参数对象
 * @Author : yuanzaishun
 * @Date: 2020-08-27 11:56
 */
@Data
public class SaleItemQuery {
    /**
     * 活动ID
     */
    Long activitiesId;
    /**
     * 店铺ID
     */
    Long shopId;
    /**
     * sku集合
     */
    List<Long> skuList;
    /**
     * 租户ID
     */
    String tenantId;
    /**
     * appId
     */
    Long appId;
    /**
     * 单据类型
     */
    Integer ticketType;

    /**
     * 业务伙伴ID
     */
    private Long parterId;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 供应商顶级组织ID
     */
    private Long orgId;
    @ApiModelProperty("企业性质id")
    private Long natureCompanyId;
}
