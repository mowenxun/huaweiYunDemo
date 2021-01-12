package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.common.domain.dto.CommonPaymentMallSettingResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolPaySettingResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.SalePickOrderPayRequestDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoReceiptRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.SaleOrderInfoTransactionOLResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.*;
import com.deepexi.dd.system.mall.domain.dto.order.plan.SaleOrderInfoPlanAddRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.GetPaySettingRequestDTO;
import com.deepexi.dd.system.mall.domain.query.OfflinePaymentInfoRequestQuery;
import com.deepexi.dd.system.mall.domain.query.SaleOrderAscertainInfoRequestQuery;
import com.deepexi.dd.system.mall.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.system.mall.domain.query.SaleOrderItemAppRequestQuery;
import com.deepexi.dd.system.mall.domain.query.saleorder.DefualtOrderRequestQuery;
import com.deepexi.dd.system.mall.domain.query.saleorder.PlanCommodityRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.saleorder.DefualtOrderResponseVO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.ShopPlanItemDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-03 10:54
 */
public interface SaleOrderInfoService {


    /**
     * 创建销售订单
     *
     * @param saleOrder
     * @return
     */
    public SaleOrderInfoAppAddResponseDTO createOrder(SaleOrderInfoAppAddRequestDTO saleOrder) throws Exception;

    /**
     * 查询销售订单表列表
     *
     * @return
     */
    //List<SaleOrderInfoDTO> listSaleOrderInfos(SaleOrderInfoQuery query);

    /**
     * 分页查询销售订单表列表
     *
     * @return
     */
    PageBean<SaleOrderInfoResponseDTO> listSaleOrderInfosPage(SaleOrderInfoRequestQuery query) throws Exception;


    /**
     * 分页查询销售订单表列表(buyerId是当前用户id)
     *
     * @return
     */
    PageBean<SaleOrderInfoTransactionOLResponseDTO> listSaleOrderInfosPageForApi(SaleOrderInfoQuery query) throws Exception;

    /**
     * 分页查询销售订单表列表 按单收款
     *
     * @return
     */
    PageBean<SaleOrderInfoReceiptResponseDTO> listSaleOrderInfosPageForReceipt(SaleOrderInfoReceiptRequestQuery query) throws Exception;

    /**
     * 根据ID删除销售订单表
     *
     * @param id
     * @return
     */
    //Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单表
     *
     * @param record
     * @return
     */
    //SaleOrderInfoDTO insert(SaleOrderInfoDTO record);

    /**
     * 查询销售订单表详情
     *
     * @param id
     * @return
     */
    SaleOrderInfoResponseDTO selectById(Long id, Long appId) throws Exception;

    Boolean cancelSaleOrder(SaleOrderCancelAppRequestDTO saleOrderCancelAppRequestDTO);

    Boolean closeSaleOrder(SaleOrderCloseAppRequestDTO saleOrderCloseAppRequestDTO);

    Boolean orderReceiving(SaleOrderReceivingAppRequestDTO saleOrderReceivingAppRequestDTO) throws Exception;

    PageBean<SaleOrderItemAppResponseDTO> listSaleOrderItemsPage(SaleOrderItemAppRequestQuery query);

    /**
     * 在线支付回调
     *
     * @param payCallbackRequestDTO
     * @return
     */
    Boolean onlinePayCallBack(PayCallbackRequestDTO payCallbackRequestDTO);

    SaleOrderPayResponseDTO paySaleOrder(SaleOrderPayRequestDTO saleOrderPayRequestDTO);

    /**
     * 在线支付-还款单
     * @param saleOrderPayRequestDTO
     * @return
     * @throws Exception
     */
    SaleOrderPayResponseDTO financeCreditPaySaleOrder(SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;

    /**
     * @Description: 订单驳回.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    Boolean orderRejected(SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception;

    /**
     * @Description: 查询线下付款的付款信息.
     * @Param: [query]
     * @return: com.deepexi.dd.system.mall.domain.dto.OfflinePaymentInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    OfflinePaymentInfoResponseDTO offlinePayInfo(OfflinePaymentInfoRequestQuery query) throws Exception;

    /**
     * @Description: 查询线下付款的收款信息.
     * @Param: [query]
     * @return: com.deepexi.dd.system.mall.domain.dto.OfflineCollectionInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/8/5
     */
    OfflineCollectionInfoResponseDTO offlineCollectionInfo(OfflinePaymentInfoRequestQuery query) throws Exception;

    /**
     * @Description: 保存线下付款的收款信息.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    Boolean saveOfflinePayInfo(OfflinePaymentInfoRequestDTO requestDTO) throws Exception;

    /**
     * 线下付款-还款单
     * @param requestDTO
     * @return
     * @throws Exception
     */
     Boolean saveRepaymentOfflinePayInfo(OfflinePaymentInfoRequestDTO requestDTO) throws Exception;
    /**
     * @Description: 获取确认订单的订单信息.
     * @Param: [query]
     * @return: com.deepexi.dd.system.mall.domain.dto.SaleOrderAscertainInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    SaleOrderAscertainInfoResponseDTO ascertainOrderInfo(SaleOrderAscertainInfoRequestQuery query) throws Exception;

    /**
     * @Description: 根据ID更新销售订单表.
     * @Param: [id, record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/28
     */
    Payload<Boolean> updateById(Long id, SaleOrderInfoEditRequestDTO record) throws Exception;

    SaleOrderInfoResponseDTO updatePlanById(Long id, SaleOrderInfoPlanAddRequestDTO record) throws Exception;

    /**
     * @Description: 支付完成后的支付信息.
     * @Param: [id, code]
     * @return: com.deepexi.dd.system.mall.domain.dto.PaymentCompletedInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/28
     */
    PaymentCompletedInfoResponseDTO paymentCompletedInfo(Long id, String code) throws Exception;

    /**
     * @Description: 获取默认订单信息
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/5
     */
    DefualtOrderResponseVO getDefualtOrderInfo(DefualtOrderRequestQuery requestQuery) throws Exception;

    /**
     * @Description: 发货.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/6
     */
    Boolean deliveryGoodsOrder(SaleOrderInfoDeliverGoodsInfoRequestDTO dto) throws Exception;

    /**
     * 订货计划单选择商品接口
     *
     * @param planCommodityRequestQuery
     * @return
     * @throws Exception
     */
    PageBean<ShopPlanItemDTO> planCommodutyPage(PlanCommodityRequestQuery planCommodityRequestQuery) throws Exception;

    Boolean deletePlanByIdIn(Long id) throws Exception;

    /**
     * 获取线下支付时，是否需要 上传支付凭证
     *
     * @param requestDTO
     * @return
     * @throws Exception
     */
    @Deprecated
    Payload<Boolean> getOfflinePayUploadPaySetting(GetPaySettingRequestDTO requestDTO) throws Exception;

    /**
     * 获取业务链路的支付设置
     *
     * @param requestDTO
     * @return
     * @throws Exception
     */
    Payload<ToolPaySettingResponseDTO> getToolLinkPaySetting(GetPaySettingRequestDTO requestDTO) throws Exception;

    /**
     * 获取商城设置的支付配置
     *
     * @param requestDTO
     * @return
     * @throws Exception
     */
    Payload<List<CommonPaymentMallSettingResponseDTO>> getCommonPaymentMallSetting(GetPaySettingRequestDTO requestDTO) throws Exception;

    /**
     * 根据Id和Code查找销售订单或提货计划订单
     *
     * @param req
     * @return
     * @throws Exception
     */
    SaleOrPinkOrderResponseDTO findOrderByIdAndCode(@RequestBody SaleOrPinkOrderRequestDTO req) throws Exception;

}