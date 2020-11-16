package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.DepotResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.StockDetailDTO;
import com.deepexi.dd.domain.transaction.domain.query.StockDetailQuery;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName : DepotController
 * @Description : 仓库接口
 * @Author : yuanzaishun
 * @Date: 2020-07-22 17:30
 */
@RestController
@Api(tags = "仓库接口")
@RequestMapping("/admin-api/v1/domain/transaction/depot")
public class DepotController {
    @Autowired
    SaleOrderInfoService saleOrderInfoService;

    @GetMapping("/getDepotList/{appId}")
    @ApiOperation("查询仓库列表")
    public Payload<List<DepotResponseDTO>> getAllDepotList(@PathVariable("appId") Long appId,
                                                           @RequestParam(name = "isolationId", required = false) String isolationId) {
        List<DepotResponseDTO> list = saleOrderInfoService.getAllDepotList(appId, isolationId);
        return new Payload<>(list);
    }

    @GetMapping("/getStockDetailPage")
    @ApiOperation("查询出入库明细分页")
    public Payload<PageBean<StockDetailDTO>> getStockDetailPage(@Valid StockDetailQuery query) throws Exception {
        return new Payload<>(GeneralConvertUtils.convert2PageBean(saleOrderInfoService.getStockDetailPage(query),StockDetailDTO.class));
    }


}
