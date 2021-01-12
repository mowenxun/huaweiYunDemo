package com.deepexi.dd.system.mall.controller;

import com.deepexi.dd.system.mall.domain.dto.LogisticsInfoUpdateRequest;
import com.deepexi.dd.system.mall.domain.dto.OrderItemUpdateRequest;
import com.deepexi.dd.system.mall.service.SaleOrderItemLogisticsService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @ClassName SaleOrderItemLogisticsController
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@RestController
@Api(tags = "oms对接接口")
@RequestMapping("/admin-api/v1/domain/transaction/saleOrderItemLogistics")
public class SaleOrderItemLogisticsController {

    @Autowired
    private SaleOrderItemLogisticsService saleOrderItemLogisticsService;

    @ApiOperation("OMS同步发货状态、物流基础信息至B端")
    @PostMapping("/orderItemUpdate")
    public Payload<Boolean> orderItemUpdate(@RequestBody @Valid OrderItemUpdateRequest request) throws Exception {
        Optional.ofNullable(request).orElseThrow( () -> new ApplicationException("参数为空"));
        return new Payload<>(saleOrderItemLogisticsService.orderItemUpdate(request));
    }

    @ApiOperation("OMS同步物流轨迹至B端")
    @PostMapping("/logisticsInfoUpdate")
    public Payload<Boolean> logisticsInfoUpdate(@RequestBody @Valid LogisticsInfoUpdateRequest request) throws Exception {
        Optional.ofNullable(request).orElseThrow( () -> new ApplicationException("参数为空"));
        return new Payload<>(saleOrderItemLogisticsService.logisticsInfoUpdate(request));
    }
}
