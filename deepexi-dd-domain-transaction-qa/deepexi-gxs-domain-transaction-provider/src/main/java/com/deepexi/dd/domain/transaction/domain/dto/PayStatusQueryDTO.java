package com.deepexi.dd.domain.transaction.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName : PayStatusQueryDTO
 * @Description : 付款状态查询DTO
 * @Author : yuanzaishun
 * @Date: 2020-07-23 16:02
 */
@Data
public class PayStatusQueryDTO {
    private String tenantId;
    private Long appId;
    private Long id;
    /**
     * 已付款金额
     */
    private BigDecimal collectionAmount;
    /**
     * 应收金额
     */
    private BigDecimal totalCollectionAmount;
}
