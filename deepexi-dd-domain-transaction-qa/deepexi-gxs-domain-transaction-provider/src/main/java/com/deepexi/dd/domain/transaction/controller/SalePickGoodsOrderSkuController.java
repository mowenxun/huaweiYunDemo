package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderSkuRespDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleDeliveryPlanInfoRequest;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsOrderSkuService;
import com.deepexi.dd.domain.transaction.service.impl.SalePickGoodsOrderSkuExportService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-15 14:14
 */
@RestController
@Api(tags = "提货sku信息管理API【运营中心】")
@RequestMapping("admin-api/v1/domain/transaction/salepickgoodsorderskus")
public class SalePickGoodsOrderSkuController {
    @Autowired
    private SalePickGoodsOrderSkuService salePickGoodsOrderSkuService;
    @Autowired
    private SalePickGoodsOrderSkuExportService salePickGoodsOrderSkuExportService;
    @Autowired
    private HttpServletResponse response;

    @ApiOperation("查寻待编排计划列表【待发货】")
    @GetMapping("/getPageList")
    public Payload<PageBean<SalePickGoodsOrderSkuRespDTO>> getSaleDeliveryPlanInfoDetails(SalePickGoodsOrderSkuQuery query) throws Exception {
        return new Payload<>(salePickGoodsOrderSkuService.getPageList(query));
    }

    @ApiOperation(value = "计划编排【导出待编排计划列表】", nickname = "export")
    @GetMapping("/exportSalePickGoodsOrderSku")
    public void exportSaleOrder(SalePickGoodsOrderSkuQuery query) throws Exception {
        salePickGoodsOrderSkuExportService.export(response, query);
    }
}