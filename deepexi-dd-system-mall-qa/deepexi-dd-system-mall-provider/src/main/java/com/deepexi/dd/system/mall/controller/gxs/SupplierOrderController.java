package com.deepexi.dd.system.mall.controller.gxs;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.dd.system.mall.service.impl.gxs.ExportSupplierOrdersService;
import com.deepexi.dd.system.mall.service.gxs.SupplierOrderMallService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 供销社，供应商端 订单controller
 *
 * @author huanghuai
 * @date 2020/10/13
 */
@Slf4j
@RestController
@RequestMapping("/admin-api/v1/gxs/supplier/order")
@Api(value = "SupplierOrderController", tags = "供销社-供应商端-订单管理")
public class SupplierOrderController {

    @Autowired
    SupplierOrderMallService supplierOrderMallService;
    @Autowired
    ExportSupplierOrdersService exportSupplierOrdersService;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;
    @Value("${plat.customer.name:威宁县供销社}")
    String customerName;

    /**
     * 供应商订单列表展示,查询
     */
    @GetMapping("/listSupplierOrders")
    @ApiOperation(value = "供应商订单列表查询展示", notes = "供应商订单列表查询展示")
    public Payload<PageBean<ListSupplierOrderResponseDTO>> listSupplierOrders(ListSupplierOrderRequestDTO vo) {
        PageBean<ListSupplierOrderResponseDTO> rs = supplierOrderMallService.listSupplierOrdersPage(vo);
        rs.getContent().sort((e1,e2)->e2.getCreatedTime().compareTo(e1.getCreatedTime()));
        rs.getContent().forEach(e->e.setCustomerName(customerName));
        return new Payload<>(rs);
    }

    /**
     * 供应商订单列表导出
     */
    @GetMapping("/exportSupplierOrders")
    @ApiOperation(value = "供应商订单列表导出", notes = "供应商订单列表导出")
    public void exportSupplierOrders(HttpServletResponse response, ListSupplierOrderRequestDTO vo) throws Exception {
        exportSupplierOrdersService.export(response, vo);
    }

    /**
     * 供应商接单, 将订单状态由 ORDER_TO_BE_RECEIVED -->> TO_BE_DELIVERED
     */
    @GetMapping("/receiveOrder/{id}")
    @ApiOperation(value = "供应商接单", notes = "供应商接单")
    public Payload<Boolean> receiveOrder(@PathVariable("id") Long id) {
        return new Payload<>(supplierOrderMallService.receiveOrder(id));
    }

    /**
     * 供应商拒单, 将订单状态改为 REJECTED
     */
    @GetMapping("/rejectOrder/{id}")
    @ApiOperation(value = "供应商拒单", notes = "供应商拒单")
    public Payload<Boolean> rejectOrder(@PathVariable("id") Long id,
                                        @ApiParam(value = "拒单原因", required = true) @RequestParam("reason") String reason) {
        return new Payload<>(supplierOrderMallService.rejectOrder(id, reason));
    }

    /**
     * 查看供应商订单详情
     */
    @GetMapping("/orderDetails/{id}")
    @ApiOperation(value = "通过订单id查看供应商订单详情", notes = "通过订单id查看供应商订单详情")
    public Payload<SupplierOrderDetailResponseDTO> orderDetailsById(@PathVariable("id") Long id) {
        SupplierOrderDetailResponseDTO rs = supplierOrderMallService.orderDetailsById(id);
        if (rs != null) {
            rs.setCustomerName(customerName);
        }
        return new Payload<>(rs);
    }
    /**
     * 查看供应商订单详情
     */
    @GetMapping("/orderDetailsByCode/{code}")
    @ApiOperation(value = "通过订单code查看供应商订单详情", notes = "通过订单code查看供应商订单详情")
    public Payload<SupplierOrderDetailResponseDTO> orderDetailsByCode(@PathVariable("code") String code) {
        SupplierOrderDetailResponseDTO rs = supplierOrderMallService.orderDetailsByCode(code);
        if (rs != null) {
            rs.setCustomerName(customerName);
        }
        return new Payload<>(rs);
    }


    /**
     * 订单发货
     */
    @PostMapping("/deliverGoods")
    @ApiOperation(value = "订单发货", notes = "订单发货")
    public Payload<Boolean> deliverGoods(@RequestBody @Valid SupplierDeliverGoodsRequestDTO vo) {
        return new Payload<>(supplierOrderMallService.deliverGoods(vo));
    }


    /**
     * 查看付款凭证
     */
    @GetMapping("/queryPayVoucher/{id}")
    @ApiOperation(value = "查看付款凭证", notes = "查看付款凭证")
    public Payload<QueryPayVoucherResponseDTO> queryPayVoucher(@PathVariable("id") Long id) {
        return new Payload<>(supplierOrderMallService.queryPayVoucher(id));
    }
}
