package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderSkuRespDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsOrderSkuQuery;
import com.deepexi.util.pageHelper.PageBean;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-15 14:26
 */
public interface SalePickGoodsOrderSkuService {
    /**
     * 待排发货计划列表 只查询待发货状态数据
     * @param query
     * @return
     */
    PageBean<SalePickGoodsOrderSkuRespDTO> getPageList(SalePickGoodsOrderSkuQuery query);
}
