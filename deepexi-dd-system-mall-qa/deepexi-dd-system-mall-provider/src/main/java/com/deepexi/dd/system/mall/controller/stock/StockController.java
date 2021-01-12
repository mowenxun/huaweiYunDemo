package com.deepexi.dd.system.mall.controller.stock;

import com.deepexi.dd.domain.transaction.domain.dto.StockResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.StockFindAllPostRequestQuery;
import com.deepexi.dd.system.mall.domain.query.stock.StockFindAllPostAdminQuery;
import com.deepexi.dd.system.mall.service.stock.StockService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-24 11:45
 */
@RestController
@Api(tags = "查库存接口")
@RequestMapping("/admin-api/v1/domain/transaction/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/realTimeStock")
    @ApiOperation("获取实时库存")
    public Payload<List<StockResponseDTO>> getRealTimeStock(StockFindAllPostAdminQuery query) throws Exception {
        return new Payload<>(stockService.getRealTimeStock(query.clone(StockFindAllPostRequestQuery.class)));
    }
}
