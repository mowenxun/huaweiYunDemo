package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflineCollectionResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.SalePickOrderPayRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo.SaleOrderAscertainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.*;
import com.deepexi.dd.domain.transaction.domain.responseDto.SaleOrderInfoTransactionOLResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.SaleOrderInfoTransactionResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * SaleOrderInfoApi
 *
 * @author admin
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@Api(value = "销售订单模块rpc接口")
@RequestMapping("/open-api/v1/domain/saleOrderInfo")
public interface SaleOrderInfoApi {

    /**
     * 查询销售订单列表
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping("/page")
    Payload<PageBean<SaleOrderInfoTransactionResponseDTO>> listSaleOrderInfosPage(@Valid SaleOrderInfoRequestQuery query) throws Exception;

    /**
     * 查询销售订单列表
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping("/receiptPage")
    Payload<PageBean<SaleOrderInfoReceiptResponseDTO>> listSaleOrderInfosPageForReceipt(@Valid SaleOrderInfoReceiptRequestQuery query) throws Exception;


    /**
     * 查询销售订单列表(在线订购)
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping("/oLpage")
    Payload<PageBean<SaleOrderInfoTransactionOLResponseDTO>> listSaleOrderPage(@Valid SaleOrderInfoRequestQuery query) throws Exception;


    /**
     * 创建订单
     * @param record
     * @return
     */
    @PostMapping("")
    Payload<SaleOrderInfoAddResponseDTO> createOrder(@RequestBody @Valid SaleOrderInfoRequestDTO record) throws Exception;


    /**
     * 根据ID查询销售订单表
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询销售订单表")
    @GetMapping("/{id}")
    Payload<SaleOrderInfoResponseDTO> selectById(@PathVariable("id") Long id) throws Exception;



    @ApiOperation("查询分页订单明细信息")
    @GetMapping("/itemPage")
    Payload<PageBean<SaleOrderItemResponseDTO>> listSaleOrderItemsPage(SaleOrderItemRequestQuery query);

    /**
     * 取消订单
     * @param saleOrderCancelRequestDTO
     * @return
     */
    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    Payload<Boolean> cancelSaleOrder(@RequestBody @Valid SaleOrderCancelRequestDTO saleOrderCancelRequestDTO);

    /**
     * 关闭订单
     * @param saleOrderCloseRequestDTO
     * @return
     */
    @ApiOperation("关闭订单")
    @PostMapping("/close")
    Payload<Boolean> closeSaleOrder(@RequestBody @Valid SaleOrderCloseRequestDTO saleOrderCloseRequestDTO);

    /**
     * 接单
     * @param saleOrderReceivingRequestDTO
     * @return
     */
    @ApiOperation("接单")
    @PostMapping("/orderReceiving")
    Payload<Boolean>  orderReceiving(@RequestBody @Valid SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO);

    @ApiOperation("支付订单 （预下单）")
    @PostMapping("/pay")
    Payload<SaleOrderPayResponseDTO> paySaleOrder(@RequestBody @Valid SaleOrderPayRequestDTO saleOrderPayRequestDTO) ;

    /**
     * 线上支付-还款单
     * @param saleOrderPayRequestDTO
     * @return
     * @throws Exception
     */
    @ApiOperation("线上支付-还款单")
    @PostMapping("/financeCreditPaySaleOrder")
    Payload<SaleOrderPayResponseDTO> financeCreditPaySaleOrder(SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;

    @ApiOperation("接单")
    @PostMapping("/payCallBack")
    Payload<Boolean> payCallBack(@RequestBody @Valid PayCallbackRequestDTO payCallbackRequestDTO);

    @ApiOperation("订单驳回")
    @PostMapping("/orderRejected")
    Payload<Boolean> orderRejected(@RequestBody @Valid SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO)throws Exception;

    @ApiOperation("查询线下付款的付款信息")
    @GetMapping("/offlinePayInfo")
    Payload<OfflinePayInfoResponseDTO> offlinePayInfo(@Valid OfflinePayInfoRequestQuery query) throws Exception;

    @ApiOperation("查询线下付款的收款信息")
    @GetMapping("/offlineCollectionInfo")
    Payload<OfflineCollectionResponseDTO> offlineCollectionInfo(@Valid OfflinePayInfoRequestQuery query) throws Exception;

    @ApiOperation("线下支付")
    @PostMapping("/saveOfflinePayInfo")
    Payload<Boolean> saveOfflinePayInfo(@RequestBody @Valid OfflinePayInfoRequestDTO requestDTO) throws Exception;

    /**
     * 还款保存线下付款的收款信息
     *
     * @param requestDTO
     * @return
     */
    @ApiOperation("线下支付-还款单")
    @PostMapping("/saveRepaymentOfflinePayInfo")
    Payload<Boolean> saveRepaymentOfflinePayInfo(@Valid @RequestBody OfflinePayInfoRequestDTO requestDTO) throws Exception;


    @ApiOperation("获取确认订单的订单信息")
    @GetMapping("ascertainOrderInfo")
    Payload<SaleOrderAscertainResponseDTO> ascertainOrderInfo(@Valid SaleOrderAscertainRequestQuery query) throws Exception;

    @ApiOperation("根据ID更新销售订单表")
    @PutMapping("/{id}")
    Payload<SaleOrderInfoAddResponseDTO> updateById(@PathVariable("id") Long id, @RequestBody @Valid SaleOrderInfoRequestDTO record) throws Exception;


    @ApiOperation("根据ID更新销售订单表")
    @PutMapping("/plan/{id}")
    Payload<SaleOrderInfoResponseDTO> updatePlanById(@PathVariable("id") Long id, @RequestBody @Valid SaleOrderInfoRequestDTO record) throws Exception;

    @ApiOperation("支付完成后的支付信息")
    @GetMapping("/paymentCompletedInfo/{id}/{code}")
    Payload<PaymentCompletedResponseDTO> paymentCompletedInfo(@PathVariable("id") Long saleOrderId, @PathVariable("code") String code) throws Exception;

    @ApiOperation("发货")
    @PostMapping("/deliveryGoodsOrder")
    Payload<Boolean> deliveryGoodsOrder(@RequestBody @Valid SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception;

    @ApiOperation("删除订购计划单")
    @GetMapping("/plan/{id}")
    Payload<Boolean> deletePlanById(@PathVariable("id") Long id) throws Exception;

    @ApiOperation("根据Id和Code查找销售订单或提货计划订单")
    @PostMapping("/findOrderByIdAndCode")
    Payload<SaleOrPinkOrderResponseDTO> findOrderByIdAndCode(@RequestBody SaleOrPinkOrderRequestDTO req) throws Exception;

    @ApiOperation("获取订单操作记录")
    @GetMapping("/getOrderOperationRecord")
    Payload<PageBean<OrderOperationRecordResponseDTO>> getOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) throws Exception;
}
