package com.deepexi.dd.system.mall.controller.app.order;

import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoReceiptRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.AppSaleOrderInfoReceiptResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.AppSaleOrderResponseDTO;
import com.deepexi.dd.system.mall.service.app.AppSaleOrderService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: mumu
 * @Description: app销售订单接口
 * @Datetime: 2020/9/1   15:58
 * @version: v1.0
 */
@RestController
@Api(tags = "app销售订单接口")
@RequestMapping("/admin-api/v1/app/transaction/saleOrderInfo")
public class AppSaleOrderController {

    @Autowired
    private AppSaleOrderService appSaleOrderService;


    /**
     * 分页查询销售订单表列表(sellerId是当前用户id)
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查询销售订单表列表")
    Payload<PageBean<AppSaleOrderResponseDTO>> listSaleOrderInfosPage(@RequestBody @Valid SaleOrderInfoRequestQuery query) throws Exception {
        return new Payload<>(appSaleOrderService.listSaleOrderInfosPage(query));
    }


    /**
     * 关闭订单
     *
     * @param saleOrderCloseRequestDTO
     * @return
     */
    @ApiOperation("关闭订单")
    @PutMapping("/close")
    Payload<Boolean> closeSaleOrder(@RequestBody @Valid SaleOrderCloseRequestDTO saleOrderCloseRequestDTO) throws Exception {
        return new Payload<>(appSaleOrderService.closeSaleOrder(saleOrderCloseRequestDTO));
    }

    /**
     * 取消订单
     *
     * @param saleOrderCancelRequestDTO
     * @return
     */
    @ApiOperation("取消")
    @PutMapping("/cancel")
    Payload<Boolean> cancelSaleOrder(@RequestBody @Valid SaleOrderCancelRequestDTO saleOrderCancelRequestDTO) throws Exception {
        return new Payload<>(appSaleOrderService.cancelSaleOrder(saleOrderCancelRequestDTO));
    }

    /**
     * 接单
     *
     * @param saleOrderReceivingRequestDTO
     * @return
     * @throws Exception
     */
    @ApiOperation("接单")
    @PostMapping("/orderReceiving")
    Payload<Boolean> orderReceiving(@RequestBody @Valid SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception {
        return new Payload<>(appSaleOrderService.orderReceiving(saleOrderReceivingRequestDTO));
    }

    /**
     * 发货
     *
     * @param saleOrderInfoDeliverGoodsRequestDTO
     * @return
     * @throws Exception
     */
    @ApiOperation("发货")
    @PostMapping("/deliveryGoodsOrder")
    Payload<Boolean> deliveryGoodsOrder(@RequestBody @Valid SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception {
        return new Payload<>(appSaleOrderService.deliveryGoodsOrder(saleOrderInfoDeliverGoodsRequestDTO));
    }


    @ApiOperation("按单收款分页查询销售订单表列表")
    @GetMapping("/receiptPage")
    public Payload<PageBean<AppSaleOrderInfoReceiptResponseDTO>> listSaleOrderInfosPageForReceipt(@Valid SaleOrderInfoReceiptRequestQuery query) throws Exception {
        return new Payload<>(appSaleOrderService.listSaleOrderInfosPageForReceipt(query));
    }

    /**
     * 根据ID查询销售订单表
     *
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询销售订单表")
    @GetMapping("/{id}")
    Payload<SaleOrderInfoResponseDTO> selectById(@PathVariable("id") Long id) throws Exception {
        return new Payload<>(appSaleOrderService.selectById(id));
    }

    /**
     * 查询操作记录
     *
     * @param orderOperationRecordRequestDTO
     * @return
     * @throws Exception
     */
    @GetMapping("/getOrderOperationRecord")
    @ApiOperation("查询操作记录")
    Payload<PageBean<OrderOperationRecordResponseDTO>> getOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) throws Exception {
        return new Payload<>(appSaleOrderService.getOrderOperationRecord(orderOperationRecordRequestDTO));
    }


}
