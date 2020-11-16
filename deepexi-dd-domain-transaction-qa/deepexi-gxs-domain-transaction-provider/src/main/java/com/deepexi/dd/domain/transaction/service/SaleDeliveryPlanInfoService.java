package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.SaleDeliveryPlanInfoDetail;
import com.deepexi.dd.domain.transaction.domain.query.SaleDeliveryPlanInfoRequest;
import com.deepexi.util.pageHelper.PageBean;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-13 21:19
 */
public interface SaleDeliveryPlanInfoService {
    /**
     * 发货计划明细
     * @param query
     * @return
     */
    PageBean<SaleDeliveryPlanInfoDetail> getSaleDeliveryPlanInfoDetails(SaleDeliveryPlanInfoRequest query);
}
