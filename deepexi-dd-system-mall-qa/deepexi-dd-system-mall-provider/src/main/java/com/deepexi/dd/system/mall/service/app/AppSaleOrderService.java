package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoReceiptRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.AppSaleOrderInfoReceiptResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.AppSaleOrderResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

/**
 * @Author: mumu
 * @Datetime: 2020/9/1   16:00
 * @version: v1.0
 */
public interface AppSaleOrderService {

    /**
     * 分页查询销售订单表列表(sellerId是当前用户id)
     *
     * @return
     */
    PageBean<AppSaleOrderResponseDTO> listSaleOrderInfosPage(SaleOrderInfoRequestQuery query) throws Exception;


    /**
     * 关闭订单
     *
     * @param saleOrderCloseRequestDTO
     * @return
     */
    Boolean closeSaleOrder(SaleOrderCloseRequestDTO saleOrderCloseRequestDTO) throws Exception;

    /**
     * 取消订单
     *
     * @param saleOrderCancelRequestDTO
     * @return
     */
    Boolean cancelSaleOrder(SaleOrderCancelRequestDTO saleOrderCancelRequestDTO) throws Exception;

    /**
     * 接单
     *
     * @param saleOrderReceivingRequestDTO
     * @return
     * @throws Exception
     */
    Boolean orderReceiving(SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception;

    /**
     * 发货
     *
     * @param saleOrderInfoDeliverGoodsRequestDTO
     * @return
     * @throws Exception
     */
    Boolean deliveryGoodsOrder(SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception;


    /**
     * 按单收款分页查询销售订单表列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<AppSaleOrderInfoReceiptResponseDTO> listSaleOrderInfosPageForReceipt(SaleOrderInfoReceiptRequestQuery query) throws Exception;


    /**
     * 根据ID查询销售订单表
     *
     * @param id
     * @return
     */
    SaleOrderInfoResponseDTO selectById(Long id) throws Exception;

    /**
     * 查询操作记录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    PageBean<OrderOperationRecordResponseDTO> getOrderOperationRecord(OrderOperationRecordRequestDTO dto) throws Exception;
}
