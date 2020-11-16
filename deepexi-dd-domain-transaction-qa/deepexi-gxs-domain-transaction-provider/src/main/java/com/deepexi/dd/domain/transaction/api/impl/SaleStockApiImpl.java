package com.deepexi.dd.domain.transaction.api.impl;


import com.deepexi.dd.domain.transaction.api.SaleStockApi;
import com.deepexi.dd.domain.transaction.domain.dto.StockResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.StockFindAllPostQuery;
import com.deepexi.dd.domain.transaction.domain.query.StockFindAllPostRequestQuery;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleStockApiImpl implements SaleStockApi {

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Override
    public Payload<List<StockResponseDTO>> getRealTimeStock(@RequestBody StockFindAllPostRequestQuery query) throws Exception {
        return new Payload<>(saleOrderInfoService.getRealTimeStock(query.clone(StockFindAllPostQuery.class)));
    }
}
