package com.deepexi.dd.system.mall.service.stock;

import com.deepexi.dd.domain.transaction.domain.dto.StockResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.StockFindAllPostRequestQuery;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface StockService {

    /***
     * 查询实时库存列表
     * @return
     */
    List<StockResponseDTO> getRealTimeStock(StockFindAllPostRequestQuery query) throws Exception;
}
