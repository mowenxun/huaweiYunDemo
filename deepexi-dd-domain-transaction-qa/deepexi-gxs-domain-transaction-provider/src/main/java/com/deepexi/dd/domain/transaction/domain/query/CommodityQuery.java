package com.deepexi.dd.domain.transaction.domain.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : CommodityQuery
 * @Description : 商品查询参数
 * @Author : yuanzaishun
 * @Date: 2020-07-09 19:09
 */
@Data
public class CommodityQuery {
    /**
     * 店铺ID
     */
    private Long shopId;
    /**
     * SKUID
     */
    private List<Long> skuIds;
    /**
     * 应用ID
     */
    private Long appId;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 订单类型 直接去订单类型，转成数字
     */
    private Integer ticketType;
    /**
     * 活动id.
     */
    private Long activitiesId;
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

    @ApiModelProperty("客户信息")
    private Map<String, List<Long>> merchantMap;

    @ApiModelProperty("客户信息-购物车")
    private Map<String, List<String>> customerMap;

    @ApiModelProperty("客户信息-购物车-所有客户")
    private Map<Long, Long> allCustomerMap;

    @ApiModelProperty("门店信息")
    private Map<Long, String> storeMap;

    @ApiModelProperty("1表示品牌商,0不是")
    private Integer companyType;
    /**
     * 是否为购物车1,0不是
     */
    private Integer type;
}
