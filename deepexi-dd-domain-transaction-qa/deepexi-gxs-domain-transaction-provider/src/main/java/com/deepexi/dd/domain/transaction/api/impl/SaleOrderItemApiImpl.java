package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.transaction.api.SaleOrderItemApi;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderItemRequestQuery;
import com.deepexi.dd.domain.transaction.service.SaleOrderItemService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SaleOrderItemApiImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
@RestController
public class SaleOrderItemApiImpl implements SaleOrderItemApi {

    @Autowired
    SaleOrderItemService saleOrderItemService;
    /**
     * @param query
     * @Description: 查询分页订单明细信息..
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    @Override
    public PageBean<SaleOrderItemResponseDTO> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) throws Exception {
        return saleOrderItemService.listSaleOrderItemsPage(query);
    }
}
