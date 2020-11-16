package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflineCollectionResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo.SaleOrderAscertainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.*;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.sdk.storage.model.DepotFindPagePostResponseDTODepot;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * SaleOrderInfoService
 *
 * @author admin
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
 */
public interface SaleOrderInfoService {


    /**
     * 创建销售订单
     *
     * @param saleOrder
     * @return
     */
    SaleOrderInfoDTO createOrder(SaleOrderInfoDTO saleOrder) throws Exception;


    /**
     * 创建销售订单
     *
     * @param saleOrder
     * @return
     */
    SaleOrderInfoDTO createPlanOrder(SaleOrderInfoDTO saleOrder) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, IOException, Exception;

    /**
     * 查询销售订单表列表
     *
     * @return
     */
    List<SaleOrderInfoResponseDTO> listSaleOrderInfos(SaleOrderInfoRequestQuery query) throws Exception;

    /**
     * 分页查询销售订单表列表(sellerId是当前用户id)
     *
     * @return
     */
    PageBean<SaleOrderBtnResponseDTO> listSaleOrderInfosPage(SaleOrderInfoQuery query) throws Exception;

    /**
     * 分页查询销售订单表列表(buyerId是当前用户id)
     *
     * @return
     */
    PageBean<SaleOrderBtnResponseDTO> listSaleOrderInfosPageForApi(SaleOrderInfoQuery query) throws Exception;


    /**
     * 根据ID删除销售订单表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 查询销售订单表详情
     *
     * @param id
     * @return
     */
    SaleOrderInfoResponseDTO selectById(Long id);

    /**
     * 查询销售订单表详情(只返回订单表信息)
     *
     * @param id
     * @return
     */
    SaleOrderResponseDTO selectSaleOrder(Long id);


    /**
     * 根据ID修改销售订单表
     *
     * @param id
     * @param saleOrder
     * @return
     */
    SaleOrderInfoDTO updateById(Long id, SaleOrderInfoDTO saleOrder) throws Exception;

    com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoResponseDTO updatePlanById(Long id,
                                                                                         SaleOrderInfoRequestDTO saleOrderInfoRequestDTO) throws Exception;

    Boolean updatePayAmount(SaleOrderInfoUpdatePayAmountRequestDTO record);

    Boolean updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequest);

    /**
     * @param saleOrderAccept
     * @return
     */
    Boolean acceptSaleOrder(SaleOrderAcceptRequestDTO saleOrderAccept);

    Boolean cancelSaleOrder(SaleOrderCancelRequestDTO saleOrderCancelRequestDTO);

    Boolean closeSaleOrder(SaleOrderCloseRequestDTO saleOrderCloseRequestDTO);

    SaleOrderPayResponseDTO paySaleOrder(SaleOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;


    /**
     * @Description: 订单发货.
     * @Param: [saleOrderInfoDeliverGoodsRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    Boolean deliveryGoodsOrder(SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception;

    /**
     * 接单
     *
     * @param saleOrderReceivingRequestDTO
     * @return
     * @throws Exception
     */
    Boolean orderReceiving(SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception;

    /**
     * 订单驳回
     *
     * @param saleOrderRejectedRequestDTO
     * @return
     * @throws Exception
     */
    Boolean orderRejected(SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception;

    /**
     * 在线支付回调
     *
     * @param payCallbackRequestDTO
     * @return
     */
    Boolean onlinePayCallBack(PayCallbackRequestDTO payCallbackRequestDTO) throws Exception;

    /**
     * 获取仓库列表
     *
     * @param appId
     * @return
     */
    List<DepotResponseDTO> getAllDepotList(Long appId, String isolationId);

    /**
     * 订单支付后扭转
     *
     * @param saleOrderPayNotifyDto
     */
    void payOrderNextStep(SaleOrderPayNotifyDto saleOrderPayNotifyDto) throws Exception;

    /**
     * 取消已支付的金额和状态
     *
     * @param payNotifyDto
     */
    void cancelOrderPayAmount(SaleOrderPayNotifyDto payNotifyDto) throws Exception;

    /**
     * 按单收款更新金额和状态
     *
     * @param collectionNotifyDto
     */
    void saleOrdercollection(SaleOrderCollectionNotifyDto collectionNotifyDto);

    /**
     * @Description: 查询线下付款的收款/付款信息.
     * @Param: [query]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    OfflinePayInfoResponseDTO offlinePayInfo(OfflinePayInfoRequestQuery query) throws Exception;

    /**
     * @Description: 查询线下付款的收款信息.
     * @Param: [query]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflineCollectionResponseDTO
     * @Author: SongTao
     * @Date: 2020/8/5
     */
    OfflineCollectionResponseDTO offlineCollectionInfo(OfflinePayInfoRequestQuery query) throws Exception;

    /**
     * @Description: 保存线下付款的收款信息.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    Boolean saveOfflinePayInfo(OfflinePayInfoRequestDTO requestDTO) throws Exception;

    /**
     * @Description: 确认订单的订单信息.
     * @Param: [query]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo.SaleOrderAscertainResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    SaleOrderAscertainResponseDTO ascertainOrderInfo(SaleOrderAscertainRequestQuery query) throws Exception;

    /**
     * @Description: 支付完成后的订单信息.
     * @Param: [saleOrderId]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.PaymentCompletedResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/28
     */
    PaymentCompletedResponseDTO paymentCompletedInfo(Long saleOrderId,String code) throws Exception;

    /**
     * 分页查询销售订单表列表(sellerId是当前用户id)
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<SaleOrderBtnResponseDTO> exportSaleOrderPage(SaleOrderInfoQuery query) throws Exception;

    /**
     * 根据id删除订购计划
     *
     * @param id
     * @return
     * @throws Exception
     */
    Payload<Boolean> deletePlanById(Long id) throws Exception;

    /***
     * 审核
     * @param saleOrderReceivingRequestDTO
     * @return
     * @throws Exception
     */
    Boolean orderToExamine(SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception;

    /***
     * 审核驳回
     * @param saleOrderRejectedRequestDTO
     * @return
     * @throws Exception
     */
    Boolean orderRejectedToExamine(SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception;

    /**
     * 提货计划 线上预支付
     *
     * @param saleOrderPayRequestDTO
     * @return
     * @throws Exception
     */
    SaleOrderPayResponseDTO salePickPaySaleOrder(SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;

    /**
     * 线上支付-还款单
     * @param saleOrderPayRequestDTO
     * @return
     * @throws Exception
     */
     SaleOrderPayResponseDTO financeCreditPaySaleOrder(SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;
    /**
     * 提货计划 线下支付
     *
     * @param dto
     * @return
     * @throws Exception
     */
    Boolean saveSalePickOfflinePayInfo(@Valid OfflinePayInfoRequestDTO dto) throws Exception;

    /**
     * 还款保存线下付款的收款信息
     *
     * @param requestDTO
     * @return
     */
     Boolean saveRepaymentOfflinePayInfo(@Valid OfflinePayInfoRequestDTO requestDTO) throws Exception;

    /**
     * 支付完成后的支付信息
     *
     * @param salePickId
     * @return
     */
    PaymentCompletedResponseDTO paymentSalePickInfo(Long salePickId);

    /***
     * 查询实时库存列表
     * @return
     */
    List<StockResponseDTO> getRealTimeStock(StockFindAllPostQuery stockFindAllPostQuery) throws Exception;

    /***
     * 获取当前账号可操作仓库（目前用于提货订单的审核）
     */
    public List<DepotFindPagePostResponseDTODepot> getOperateDepot(StockFindAllPostQuery stockFindAllPostQuery) throws Exception ;


    /**
     * 根据Id和Code查找销售订单或提货计划订单
     * @throws Exception
     */
    SaleOrPinkOrderResponseDTO findOrderByIdAndCode(SaleOrPinkOrderRequestDTO req) throws Exception;

    PageBean<StockDetailDTO> getStockDetailPage(StockDetailQuery query) throws Exception;

    Boolean closePreMonthPlanOrder() throws Exception;
}
