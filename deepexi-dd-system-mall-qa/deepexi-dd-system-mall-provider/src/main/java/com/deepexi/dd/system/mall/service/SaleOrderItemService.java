package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.transaction.domain.query.SaleOrderItemRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.SaleOrderItemInfoResponseDTO;
import com.deepexi.util.pageHelper.PageBean;

/**
 * @ClassName SaleOrderItemService
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
public interface SaleOrderItemService {

    /**
     * @Description:   查询分页订单明细信息..
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.system.mall.domain.dto.SaleOrderItemInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    PageBean<SaleOrderItemInfoResponseDTO> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) throws Exception;
}
