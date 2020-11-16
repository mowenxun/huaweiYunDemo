package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderItemRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

/**
 * @ClassName SaleOrderItemService
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-10
 * @Version 1.0
 **/
public interface SaleOrderItemService {

    /**
     * @Description:  查询分页订单明细信息.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.domain.transaction.domain.dto.SaleOrderItem.SaleOrderItemResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/10
     */
    PageBean<SaleOrderItemResponseDTO> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) throws Exception;

}
