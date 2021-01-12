package com.deepexi.dd.system.mall.service.stock.impl;

import com.deepexi.dd.domain.transaction.domain.dto.StockResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.StockFindAllPostRequestQuery;
import com.deepexi.dd.system.mall.remote.stock.SaleStockClient;
import com.deepexi.dd.system.mall.service.stock.StockService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private SaleStockClient saleStockClient;

    @Override
    public List<StockResponseDTO> getRealTimeStock(StockFindAllPostRequestQuery query) throws Exception {
        Payload<List<StockResponseDTO>> payload = saleStockClient.getRealTimeStock(query);
        return GeneralConvertUtils.convert2List(payload.getPayload(),StockResponseDTO.class);
    }
}
