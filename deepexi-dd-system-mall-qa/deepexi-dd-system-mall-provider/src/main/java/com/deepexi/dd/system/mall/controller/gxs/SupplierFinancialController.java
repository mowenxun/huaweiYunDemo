package com.deepexi.dd.system.mall.controller.gxs;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.api.gxs.domain.CreatePayOrderItemRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListPayOrderRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListPayOrderResponseDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.PayOrderDetailsResponseDTO;
import com.deepexi.dd.system.mall.service.gxs.SupplierFinancialMallService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 供销社，供应商端 财务管理api
 *
 * @author huanghuai
 * @date 2020/10/14
 */
@Slf4j
@RestController
@RequestMapping("/admin-api/v1/gxs/supplier/financial")
@Api(value = "SupplierFinancialController", tags = "供销社-供应商端-财务管理api")
public class SupplierFinancialController {


    @Autowired
    SupplierFinancialMallService supplierFinancialMallService;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;

    /**
     * 查询应收单 ，发货之后才有应收单， 付了钱才会发货
     */
    @GetMapping("/listPayOrders")
    @ApiOperation(value = "应收单列表展示", notes = "应收单列表展示")
   public Payload<PageBean<ListPayOrderResponseDTO>> listPayOrders(ListPayOrderRequestDTO vo){
        PageBean<ListPayOrderResponseDTO> rs = supplierFinancialMallService.listPayOrders(vo);
        rs.getContent().sort((e1,e2)->e2.getCreatedTime().compareTo(e1.getCreatedTime()));
        return new Payload<>(rs);
    }

    /**
     * 供应商财务收款<br>
     * 1.创建收款单
     * 2.修改应收单的已收和待收金额
     */
    @PostMapping("/supplierCollection")
    @ApiOperation(value = "供应商财务收款", notes = "供应商财务收款")
    public Payload<Boolean> supplierCollection(@RequestBody @Valid CreatePayOrderItemRequestDTO vo) {
        return new Payload<>(supplierFinancialMallService.supplierCollection(vo));
    }


    /**
     * 查询应收单详情. <br>
     * 1.查应收单 pay_order
     * 2.查应收单item pay_order_item
     */
    @GetMapping("/payOrdersDetails/{id}")
    @ApiOperation(value = "查询应收单详情", notes = "查询应收单详情")
    public Payload<PayOrderDetailsResponseDTO> payOrdersDetails(@ApiParam(value = "应收单的主键id", required = true) @PathVariable("id") Long id) {
        return new Payload<>(supplierFinancialMallService.payOrdersDetails(id));
    }




}
