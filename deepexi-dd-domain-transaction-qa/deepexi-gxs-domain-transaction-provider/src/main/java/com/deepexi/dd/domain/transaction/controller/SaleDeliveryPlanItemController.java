package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.transaction.domain.dto.SaleDeliveryPlanInfoDetail;
import com.deepexi.dd.domain.transaction.domain.query.SaleDeliveryPlanInfoRequest;
import com.deepexi.dd.domain.transaction.service.SaleDeliveryPlanInfoService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-13 20:19
 */
@RestController
@Api(tags = "发货计划明细管理API【运营中心】")
@RequestMapping("admin-api/v1/domain/transaction/saledeliveryplanitems")
public class SaleDeliveryPlanItemController {
    @Autowired
    private SaleDeliveryPlanInfoService saleDeliveryPlanInfoService;

    @ApiOperation("发货计划记录【查看详情】")
    @GetMapping("/getDetail")
    public Payload<PageBean<SaleDeliveryPlanInfoDetail>> getSaleDeliveryPlanInfoDetails(SaleDeliveryPlanInfoRequest query) throws Exception {
        return new Payload<>(saleDeliveryPlanInfoService.getSaleDeliveryPlanInfoDetails(query));
    }
}
