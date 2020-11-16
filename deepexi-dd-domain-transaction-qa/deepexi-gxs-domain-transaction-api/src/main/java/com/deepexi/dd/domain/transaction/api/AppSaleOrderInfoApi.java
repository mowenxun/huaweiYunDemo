package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoReceiptRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: mumu
 * @Description: app 销售单
 * @Datetime: 2020/9/1   16:02
 * @version: v1.0
 */
@Api(value = "app销售订单模块rpc接口")
@RequestMapping("/open-api/v1/domain/app/saleOrderInfo")
public interface AppSaleOrderInfoApi {

    /**
     * 分页查询销售订单表列表(sellerId是当前用户id)
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查询销售订单表列表")
    Payload<PageBean<SaleOrderBtnResponseDTO>> listSaleOrderInfosPage(@RequestBody @Valid SaleOrderInfoRequestQuery query) throws Exception;


    /**
     * 关闭订单
     *
     * @param saleOrderCloseRequestDTO
     * @return
     */
    @ApiOperation("关闭订单")
    @PutMapping("/close")
    Payload<Boolean> closeSaleOrder(@RequestBody @Valid SaleOrderCloseRequestDTO saleOrderCloseRequestDTO);

    /**
     * 取消订单
     *
     * @param saleOrderCancelRequestDTO
     * @return
     */
    @ApiOperation("取消")
    @PutMapping("/cancel")
    Payload<Boolean> cancelSaleOrder(@RequestBody @Valid SaleOrderCancelRequestDTO saleOrderCancelRequestDTO);

    /**
     * 接单
     *
     * @param saleOrderReceivingRequestDTO
     * @return
     * @throws Exception
     */
    @ApiOperation("接单")
    @PostMapping("/orderReceiving")
    Payload<Boolean> orderReceiving(@RequestBody @Valid SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception;

    /**
     * 发货
     *
     * @param saleOrderInfoDeliverGoodsRequestDTO
     * @return
     * @throws Exception
     */
    @ApiOperation("发货")
    @PostMapping("/deliveryGoodsOrder")
    Payload<Boolean> deliveryGoodsOrder(@RequestBody @Valid SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception;


    /**
     * 按单收款分页查询销售订单表列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("按单收款分页查询销售订单表列表")
    @GetMapping("/receiptPage")
    Payload<PageBean<SaleOrderInfoReceiptResponseDTO>> listSaleOrderInfosPageForReceipt(@Valid SaleOrderInfoReceiptRequestQuery query) throws Exception;


    /**
     * 根据ID查询销售订单表
     *
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询销售订单表")
    @GetMapping("/{id}")
    Payload<SaleOrderInfoResponseDTO> selectById(@PathVariable("id") Long id);

    @GetMapping("/getOrderOperationRecord")
    @ApiOperation("查询操作记录")
    Payload<PageBean<OrderOperationRecordResponseDTO>> getOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO);
}
