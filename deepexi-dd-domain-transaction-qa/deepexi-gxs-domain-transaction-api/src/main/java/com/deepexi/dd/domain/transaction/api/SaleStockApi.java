package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.StockResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.StockFindAllPostRequestQuery;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(tags = "查库存接口")
@RequestMapping("/open-api/v1/domain/transaction/stock")
public interface SaleStockApi {

    @PostMapping("/getRealTimeStock")
    @ApiOperation("获取实时库存")
    Payload<List<StockResponseDTO>> getRealTimeStock(@RequestBody StockFindAllPostRequestQuery query) throws Exception;
}
