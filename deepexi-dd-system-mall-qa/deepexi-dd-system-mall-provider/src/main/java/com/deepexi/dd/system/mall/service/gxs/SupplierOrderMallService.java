package com.deepexi.dd.system.mall.service.gxs;

import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 供销社，供应商端 订单controller
 *
 * @author huanghuai
 * @date 2020/10/13
 */
public interface SupplierOrderMallService {


    /**
     * 供应商订单列表展示,查询
     */
    @GetMapping("/listSupplierOrders")
    @ApiOperation(value = "供应商订单列表查询展示", notes = "供应商订单列表查询展示")
    PageBean<ListSupplierOrderResponseDTO> listSupplierOrdersPage(ListSupplierOrderRequestDTO vo);

    /**
     * 供应商接单, 将订单状态由 ORDER_TO_BE_RECEIVED -->> TO_BE_DELIVERED
     */
    @GetMapping("/receiveOrder/{id}")
    @ApiOperation(value = "供应商接单", notes = "供应商接单")
    Boolean receiveOrder(@PathVariable("id") Long id);

    /**
     * 供应商拒单, 将订单状态改为 REJECTED
     */
    @GetMapping("/rejectOrder/{id}")
    @ApiOperation(value = "供应商拒单", notes = "供应商拒单")
    Boolean rejectOrder(@PathVariable("id") Long id,
                                 @ApiParam(value = "拒单原因", required = true) @RequestParam("reason") String reason);

    /**
     * 查看供应商订单详情
     */
    @GetMapping("/orderDetails/{id}")
    @ApiOperation(value = "通过订单id查看供应商订单详情", notes = "通过订单id查看供应商订单详情")
    SupplierOrderDetailResponseDTO orderDetailsById(@PathVariable("id") Long id);


    /**
     * 订单发货
     */
    @PostMapping("/deliverGoods")
    @ApiOperation(value = "订单发货", notes = "订单发货")
    Boolean deliverGoods(@RequestBody @Valid SupplierDeliverGoodsRequestDTO vo);

    /**
     * 查看付款凭证
     */
    @GetMapping("/queryPayVoucher/{id}")
    @ApiOperation(value = "查看付款凭证", notes = "查看付款凭证")
    QueryPayVoucherResponseDTO queryPayVoucher(
            @ApiParam(name = "id", value = "供应商订单主键id") @PathVariable("id") Long id);

    SupplierOrderDetailResponseDTO orderDetailsByCode(String code);

}
