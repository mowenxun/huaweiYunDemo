package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.clientiam.api.GroupControllerApi;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.clientiam.model.PayloadGroupResultVO;
import com.deepexi.dd.domain.common.domain.dto.CommonPaymentMallSettingResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonPaymentMallSettingRequestQuery;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.common.util.ObjectConvertUtil;
import com.deepexi.dd.domain.tool.domain.dto.ToolCategoryRequestQuery;
import com.deepexi.dd.domain.tool.domain.dto.ToolPaySettingResponseDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolPaySettingRequestQuery;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.AuthorizedPriceRquestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.DirectSkuPriceRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.SkuStockRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderConsigneeInfoAddRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderExpenseInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderInvoiceInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderPromotionInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.add.SaleOrderItemAddRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.*;
import com.deepexi.dd.domain.transaction.domain.dto.orderDeliveryConsigneeInfo.OrderDeliveryConsigneeInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo.SaleOrderAscertainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.*;
import com.deepexi.dd.domain.transaction.domain.responseDto.AuthorizedPrice.AuthorizedPriceResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.SaleOrderInfoTransactionOLResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.SaleOrderInfoTransactionResponseDTO;
import com.deepexi.dd.domain.transaction.enums.BuyerTypeEnum;
import com.deepexi.dd.domain.transaction.enums.TicketTypeEnum;
import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.*;
import com.deepexi.dd.system.mall.domain.dto.order.plan.SaleOrderInfoPlanAddRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.GetPaySettingRequestDTO;
import com.deepexi.dd.system.mall.domain.query.OfflinePaymentInfoRequestQuery;
import com.deepexi.dd.system.mall.domain.query.SaleOrderAscertainInfoRequestQuery;
import com.deepexi.dd.system.mall.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.system.mall.domain.query.SaleOrderItemAppRequestQuery;
import com.deepexi.dd.system.mall.domain.query.saleorder.DefualtOrderRequestQuery;
import com.deepexi.dd.system.mall.domain.query.saleorder.PlanCommodityRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAddressResponseVO;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantInvoiceResponseVO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.DefualtOrderResponseVO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.OrderInfoResponseVO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.ShopPlanItemDTO;
import com.deepexi.dd.system.mall.enums.CommodityRequestEnum;
import com.deepexi.dd.system.mall.enums.ShopTypeEnum;
import com.deepexi.dd.system.mall.remote.commodity.ToolCommoditytypeAuthorizeApiRemote;
import com.deepexi.dd.system.mall.remote.common.CommonPaymentMallSettingRemote;
import com.deepexi.dd.system.mall.remote.order.AuthorizedPriceApiRemote;
import com.deepexi.dd.system.mall.remote.order.SaleOrderInfoRemote;
import com.deepexi.dd.system.mall.remote.order.SaleOutTaskRemote;
import com.deepexi.dd.system.mall.remote.tool.ToolBilltypeRemote;
import com.deepexi.dd.system.mall.remote.tool.ToolLinkRemote;
import com.deepexi.dd.system.mall.service.SaleOrderInfoService;
import com.deepexi.dd.system.mall.service.customer.MerchantAddressService;
import com.deepexi.dd.system.mall.service.customer.MerchantInvoiceService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.sdk.commodity.api.ActivityStockOpenApi;
import com.deepexi.sdk.commodity.api.SaleStockOpenApi;
import com.deepexi.sdk.commodity.api.ShopItemOpenApi;
import com.deepexi.sdk.commodity.model.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import domain.query.MerchantAddressRequestQuery;
import domain.query.MerchantInvoiceRequestQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-03 10:55
 */
@Slf4j
@Service
public class SaleOrderInfoServiceImpl implements SaleOrderInfoService {
    // log log = logFactory.getlog(SaleOrderInfoServiceImpl.class);
    private static final String SUCCESS = "0";

    @Autowired
    SaleOrderInfoRemote saleOrderInfoRemote;

    @Autowired
    SaleOutTaskRemote saleOutTaskRemote;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private GroupControllerApi groupControllerApi;

    @Autowired
    private MerchantInvoiceService invoiceService;

    @Autowired
    private MerchantAddressService addressService;

    @Autowired
    private ShopItemOpenApi shopItemOpenApi;

    @Autowired
    private ActivityStockOpenApi activityStockOpenApi;

    @Autowired
    private SaleStockOpenApi saleStockOpenApi;

    @Autowired
    private ToolCommoditytypeAuthorizeApiRemote toolCommoditytypeAuthorizeApiRemote;
    @Autowired
    private AuthorizedPriceApiRemote authorizedPriceApiRemote;

    @Autowired
    private ToolLinkRemote toolLinkRemote;
    @Autowired
    private CommonPaymentMallSettingRemote commonPaymentMallSettingRemote;

    @Autowired
    private ToolBilltypeRemote toolBilltypeRemote;

    @Override
    public PageBean<SaleOrderInfoResponseDTO> listSaleOrderInfosPage(SaleOrderInfoRequestQuery query) throws Exception {

        SaleOrderInfoRequestQuery model = query.clone(SaleOrderInfoRequestQuery.class);
        Payload<PageBean<SaleOrderInfoTransactionResponseDTO>> result =
                saleOrderInfoRemote.listSaleOrderInfosPage(model);
        PageBean<SaleOrderInfoResponseDTO> pageBean = GeneralConvertUtils.convert2PageBean(result.getPayload(),
                                                                                           SaleOrderInfoResponseDTO.class);
        if (CollectionUtils.isNotEmpty(pageBean.getContent())) {//查询出订单关联的多个出库单id 供app调用 SongTao 2020/08/10
            List<Long> list =
                    pageBean.getContent().stream().map(SaleOrderInfoResponseDTO::getId).collect(Collectors.toList());
            //订单id集合
            SaleOutTaskRequestQuery saleOutTaskRequestQuery = new SaleOutTaskRequestQuery();
            saleOutTaskRequestQuery.setSaleOrderIdList(list);
            List<SaleOutTaskDetailResponseDTO> saleOutTaskDetailResponseDTOS =
                    saleOutTaskRemote.listSaleOutTasksForApp(saleOutTaskRequestQuery);
            List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO> saleOutTaskList = GeneralConvertUtils.convert2List(saleOutTaskDetailResponseDTOS, com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO.class);
            Map<Long, List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO>> map
                    =
                    saleOutTaskList.stream().collect(Collectors.groupingBy(com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO::getSaleOrderId));
            pageBean.getContent().forEach(f -> {
                f.setSaleOutTaskList(map.get(f.getId()));
            });
        }
        return pageBean;
    }

    @Override
    public PageBean<SaleOrderInfoTransactionOLResponseDTO> listSaleOrderInfosPageForApi(SaleOrderInfoQuery query) throws Exception {
        SaleOrderInfoRequestQuery model = query.clone(SaleOrderInfoRequestQuery.class);
        Payload<PageBean<SaleOrderInfoTransactionOLResponseDTO>> result = saleOrderInfoRemote.listSaleOrderPage(model);
        PageBean<SaleOrderInfoTransactionOLResponseDTO> pageBean =
                GeneralConvertUtils.convert2PageBean(result.getPayload(), SaleOrderInfoTransactionOLResponseDTO.class);
        if (CollectionUtils.isNotEmpty(pageBean.getContent())) {//查询出订单关联的多个出库单id 供app调用 SongTao 2020/08/10
            List<Long> list =
                    pageBean.getContent().stream().map(SaleOrderInfoTransactionOLResponseDTO::getId).collect(Collectors.toList());//订单id集合
            SaleOutTaskRequestQuery saleOutTaskRequestQuery = new SaleOutTaskRequestQuery();
            saleOutTaskRequestQuery.setSaleOrderIdList(list);
            List<SaleOutTaskDetailResponseDTO> saleOutTaskDetailResponseDTOS =
                    saleOutTaskRemote.listSaleOutTasksForApp(saleOutTaskRequestQuery);
            List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO> saleOutTaskList = GeneralConvertUtils.convert2List(saleOutTaskDetailResponseDTOS, com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO.class);
            Map<Long, List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO>> map
                    =
                    saleOutTaskList.stream().collect(Collectors.groupingBy(SaleOutTaskInfoResponseDTO::getSaleOrderId));
            pageBean.getContent().forEach(f -> {
                f.setSaleOutTaskList(map.get(f.getId()));
            });
        }
        return pageBean;
    }

    @Override
    public PageBean<SaleOrderInfoReceiptResponseDTO> listSaleOrderInfosPageForReceipt(SaleOrderInfoReceiptRequestQuery query) throws Exception {

        Payload<PageBean<SaleOrderInfoReceiptResponseDTO>> result =
                saleOrderInfoRemote.listSaleOrderInfosPageForReceipt(query);

        return GeneralConvertUtils.convert2PageBean(result.getPayload(), SaleOrderInfoReceiptResponseDTO.class);
    }

    @Override
    public SaleOrderInfoAppAddResponseDTO createOrder(SaleOrderInfoAppAddRequestDTO saleOrder) throws Exception {
        SaleOrderInfoRequestDTO domainSaleOrder = getDomainSaleOrderInfoRequestDTO(saleOrder);
        domainSaleOrder.setBuyerType(BuyerTypeEnum.MALL.getValue());
        Payload<SaleOrderInfoAddResponseDTO> result = saleOrderInfoRemote.createOrder(domainSaleOrder);
        if (SUCCESS.equals(result.getCode())) {
            try {
                SaleOrderInfoAddResponseDTO responseDTO = GeneralConvertUtils.conv(result.getPayload(),
                                                                                   SaleOrderInfoAddResponseDTO.class);
                return getSaleOrderInfoAppAddResponseDTO(responseDTO);
            } catch (Exception e) {
                log.error("获取创建订单结果失败", e);
                throw new ApplicationException("获取创建订单结果失败");
            }
        } else {
            throw new ApplicationException(result.getMsg());
        }

    }

    private SaleOrderInfoRequestDTO getDomainSaleOrderInfoRequestDTO(SaleOrderInfoAppAddRequestDTO saleOrder) {
        SaleOrderInfoRequestDTO requestDTO = saleOrder.clone(SaleOrderInfoRequestDTO.class);
        requestDTO.setItems(ObjectConvertUtil.getList(saleOrder.getItems(), SaleOrderItemAddRequestDTO.class));
        if (!TicketTypeEnum.PLAN.getValue().equals(saleOrder.getTicketType())) {//非订货计划订单才需要这些信息
            if (!ObjectUtils.isEmpty(saleOrder.getOrderSelfRaisingInfoAddInfo())) {
                //自提地址
                requestDTO.setOrderSelfRaisingInfoAddInfo(saleOrder.getOrderSelfRaisingInfoAddInfo().clone(OrderSelfRaisingInfoAddRequestDTO.class));
            }
            if (!ObjectUtils.isEmpty(saleOrder.getOrderConsigneeInfo())) {
                //送货地址
                requestDTO.setOrderConsigneeInfo(saleOrder.getOrderConsigneeInfo().clone(OrderConsigneeInfoAddRequestDTO.class));
            }
            requestDTO.setOrderCouponIds(saleOrder.getOrderCouponIds());
            requestDTO.setOrderExpenseInfo(ObjectConvertUtil.getList(saleOrder.getOrderExpenseInfo(),
                                                                     OrderExpenseInfoRequestDTO.class));
            requestDTO.setOrderInvoiceInfo(saleOrder.getOrderInvoiceInfo().clone(OrderInvoiceInfoRequestDTO.class));
            requestDTO.setOrderPromotionIds(saleOrder.getOrderPromotionIds());
        }
        return requestDTO;
    }

    private SaleOrderInfoAppAddResponseDTO getSaleOrderInfoAppAddResponseDTO(SaleOrderInfoAddResponseDTO responseDTO) {
        SaleOrderInfoAppAddResponseDTO appAddResponseDTO = responseDTO.clone(SaleOrderInfoAppAddResponseDTO.class);

        return appAddResponseDTO;
    }

    @Override
    public SaleOrderInfoResponseDTO selectById(Long id, Long appId) throws Exception {
        Payload<SaleOrderInfoResponseDTO> payload = saleOrderInfoRemote.selectById(id);

        if (SUCCESS.equals(payload.getCode())) {
            try {
                SaleOrderInfoResponseDTO result = GeneralConvertUtils.conv(payload.getPayload(),
                                                                           SaleOrderInfoResponseDTO.class);
                setStock(result, appId);
                return result;
            } catch (Exception e) {
                log.error("获取销售订单详情失败", e);
                throw new ApplicationException("获取销售订单详情失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    /**
     * @Description: 设置商品库存.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/3
     */
    private void setStock(SaleOrderInfoResponseDTO infoResponseDTO, Long appId) {
        if (Objects.isNull(infoResponseDTO) || CommonUtils.isEmpty(infoResponseDTO.getItems())) {
            return;
        }
        // 通过是否有活动进行分组.
        Map<Boolean, List<SaleOrderItemResponseDTO>> map =
                infoResponseDTO.getItems().stream().collect(Collectors.groupingBy(s -> Objects.nonNull(s.getActivitiesId())));
        // 非活动的库存到商品中台获取库存.
        List<SaleOrderItemResponseDTO> activitiesList = map.get(Boolean.TRUE);
        setActivitiesStock(activitiesList, appId);
        // 活动的去商品活动里面获取库存.
        List<SaleOrderItemResponseDTO> commodityList = map.get(Boolean.FALSE);
        setCommodityStock(commodityList, appId);
    }

    /**
     * @Description: 设置商品库存.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/3
     */
    private void setCommodityStock(List<SaleOrderItemResponseDTO> commodityList, Long appId) {
        if (CommonUtils.isEmpty(commodityList)) {
            return;
        }
        // 通过门店对商品进行分组.
        Map<Long, List<SaleOrderItemResponseDTO>> map =
                commodityList.stream().collect(Collectors.groupingBy(SaleOrderItemResponseDTO::getShopId));
        // 循环查询对应门店下面的库存.
        map.forEach((k, v) -> {
            ListSaleStockRequestDTO requestDTO = new ListSaleStockRequestDTO();
            requestDTO.setAppId(appId);
            requestDTO.setTenantId(appRuntimeEnv.getTenantId());
            requestDTO.setShopId(k);
            requestDTO.setSkuIdList(v.stream().map(SaleOrderItemResponseDTO::getSkuId).collect(Collectors.toList()));
            PayloadListListSaleStockResponseDTO responseDTO = saleStockOpenApi.listSaleStocks(requestDTO);
            if (CommonUtils.isNotEmpty(responseDTO.getPayload())) {
                Map<Long, Integer> stockMap =
                        responseDTO.getPayload().stream().collect(Collectors.toMap(ListSaleStockResponseDTO::getSkuId
                                , s -> Objects.nonNull(s.getSalableStockQty()) ? s.getSalableStockQty() : 0));
                v.stream().forEach(sku -> {
                    sku.setStockNum(stockMap.get(sku.getSkuId()));
                });
            }
        });
    }

    /**
     * @Description: 设置活动库存.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/3
     */
    private void setActivitiesStock(List<SaleOrderItemResponseDTO> activitiesList, Long appId) {
        if (CommonUtils.isEmpty(activitiesList)) {
            return;
        }
        ListActivityStockRequestDTO requestDTO = new ListActivityStockRequestDTO();
        requestDTO.setAppId(appId);
        requestDTO.setTenantId(appRuntimeEnv.getTenantId());
        List<String> unionIdList = CollectionUtil.createArrayList();
        activitiesList.forEach(sku -> {
            String unionId = 3 + "_" + sku.getSkuId() + "_" + sku.getActivitiesId();
            unionIdList.add(unionId);
        });
        requestDTO.setUnionIdList(unionIdList);
        PayloadListListActivityStockResponseDTO payload = activityStockOpenApi.listActivityStocksDetail(requestDTO);
        if (CommonUtils.isNotEmpty(payload.getPayload())) {
            Map<Long, Map<Long, Integer>> map =
                    payload.getPayload().stream().collect(Collectors.groupingBy(ListActivityStockResponseDTO::getActivityId, Collectors.toMap(ListActivityStockResponseDTO::getSkuId, ListActivityStockResponseDTO::getActivityStockQty)));
            activitiesList.stream().forEach(sku -> {
                Map<Long, Integer> stockMap = map.get(sku.getActivitiesId());
                if (CommonUtils.isNotEmpty(stockMap)) {
                    sku.setStockNum(stockMap.get(sku.getSkuId()));
                }
            });
        }
    }

    @Override
    public Boolean orderReceiving(SaleOrderReceivingAppRequestDTO saleOrderReceivingAppRequestDTO) throws Exception {
        Payload<Boolean> payload =
                saleOrderInfoRemote.orderReceiving(saleOrderReceivingAppRequestDTO.clone(SaleOrderReceivingRequestDTO.class));
        if (SUCCESS.equals(payload.getCode())) {
            return payload.getPayload();
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public PageBean<SaleOrderItemAppResponseDTO> listSaleOrderItemsPage(SaleOrderItemAppRequestQuery query) {
        Payload<PageBean<SaleOrderItemResponseDTO>> payload =
                saleOrderInfoRemote.listSaleOrderItemsPage(query.clone(SaleOrderItemRequestQuery.class));
        if (SUCCESS.equals(payload.getCode())) {
            try {

                Payload<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOPayload =
                        saleOrderInfoRemote.selectById(query.getSaleOrderId());

                SaleOrderInfoResponseDTO saleOrderInfoResponseDTO =
                        GeneralConvertUtils.conv(saleOrderInfoResponseDTOPayload.getPayload(),
                                                 SaleOrderInfoResponseDTO.class);

                Map<String, String> map = saleOrderInfoResponseDTO.getOrderPromotionList().stream()
                                                                  .filter(m -> StringUtil.isNotEmpty(m.getSkuIds())).collect(Collectors.toMap(OrderPromotionInfoResponseDTO::getSkuIds, OrderPromotionInfoResponseDTO::getPromotionName));


                PageBean<SaleOrderItemAppResponseDTO> result =
                        GeneralConvertUtils.convert2PageBean(payload.getPayload(), SaleOrderItemAppResponseDTO.class);

                for (SaleOrderItemAppResponseDTO saleOrderItemAppResponseDTO : result.getContent()) {
                    map.keySet().forEach(i -> {
                        if (i.contains(saleOrderItemAppResponseDTO.getSkuId() + "")) {
                            saleOrderItemAppResponseDTO.setPromotionName(map.get(i));
                        }
                    });
                }

                return result;
            } catch (Exception e) {
                log.error("获取销售订单明细失败", e);
                throw new ApplicationException("获取销售订单明细失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public Boolean cancelSaleOrder(SaleOrderCancelAppRequestDTO saleOrderCancelRequestDTO) {
        Payload<Boolean> payload =
                saleOrderInfoRemote.cancelSaleOrder(saleOrderCancelRequestDTO.clone(SaleOrderCancelRequestDTO.class));
        if (SUCCESS.equals(payload.getCode())) {
            try {
                return payload.getPayload();
            } catch (Exception e) {
                log.error("取消订单失败", e);
                throw new ApplicationException("取消订单失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public Boolean closeSaleOrder(SaleOrderCloseAppRequestDTO saleOrderCloseRequestDTO) {
        Payload<Boolean> payload =
                saleOrderInfoRemote.closeSaleOrder(saleOrderCloseRequestDTO.clone(SaleOrderCloseRequestDTO.class));
        if (SUCCESS.equals(payload.getCode())) {
            try {
                return payload.getPayload();
            } catch (Exception e) {
                log.error("关闭订单失败", e);
                throw new ApplicationException("关闭订单失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public Boolean onlinePayCallBack(PayCallbackRequestDTO payCallbackRequestDTO) {
        Payload<Boolean> payload = saleOrderInfoRemote.payCallBack(payCallbackRequestDTO);
        if (SUCCESS.equals(payload.getCode())) {
            try {
                return payload.getPayload();
            } catch (Exception e) {
                log.error("支付回调失败", e);
                throw new ApplicationException("支付回调失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public SaleOrderPayResponseDTO paySaleOrder(SaleOrderPayRequestDTO saleOrderPayRequestDTO) {
        Payload<SaleOrderPayResponseDTO> payload = saleOrderInfoRemote.paySaleOrder(saleOrderPayRequestDTO);
        if (SUCCESS.equals(payload.getCode())) {
            try {
                return GeneralConvertUtils.conv(payload.getPayload(), SaleOrderPayResponseDTO.class);
            } catch (Exception e) {
                log.error("支付失败", e);
                throw new ApplicationException("支付失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public SaleOrderPayResponseDTO financeCreditPaySaleOrder(SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        Payload<SaleOrderPayResponseDTO> payload =
                saleOrderInfoRemote.financeCreditPaySaleOrder(saleOrderPayRequestDTO);
        if (SUCCESS.equals(payload.getCode())) {
            try {
                return GeneralConvertUtils.conv(payload.getPayload(), SaleOrderPayResponseDTO.class);
            } catch (Exception e) {
                log.error("支付失败", e);
                throw new ApplicationException("支付失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    /**
     * @param saleOrderRejectedRequestDTO
     * @Description: 订单驳回.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    @Override
    public Boolean orderRejected(SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception {
        Payload<Boolean> payload = saleOrderInfoRemote.orderRejected(saleOrderRejectedRequestDTO);
        return payload.getPayload();
    }

    /**
     * @param query
     * @Description: 查询线下付款的收款/付款信息.
     * @Param: [query]
     * @return: com.deepexi.dd.system.mall.domain.dto.OfflinePaymentInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    @Override
    public OfflinePaymentInfoResponseDTO offlinePayInfo(OfflinePaymentInfoRequestQuery query) throws Exception {
        Payload<OfflinePayInfoResponseDTO> payload =
                saleOrderInfoRemote.offlinePayInfo(query.clone(OfflinePayInfoRequestQuery.class));
        return GeneralConvertUtils.conv(payload.getPayload(), OfflinePaymentInfoResponseDTO.class);
    }

    /**
     * @param query
     * @Description: 查询线下付款的收款信息.
     * @Param: [query]
     * @return: com.deepexi.dd.system.mall.domain.dto.OfflineCollectionInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/8/5
     */
    @Override
    public OfflineCollectionInfoResponseDTO offlineCollectionInfo(OfflinePaymentInfoRequestQuery query) throws Exception {
        Payload<OfflineCollectionResponseDTO> payload =
                saleOrderInfoRemote.offlineCollectionInfo(query.clone(OfflinePayInfoRequestQuery.class));
        return GeneralConvertUtils.conv(payload.getPayload(), OfflineCollectionInfoResponseDTO.class);
    }

    /**
     * @param requestDTO
     * @Description: 保存线下付款的收款信息.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    @Override
    public Boolean saveOfflinePayInfo(OfflinePaymentInfoRequestDTO requestDTO) throws Exception {
        OfflinePayInfoRequestDTO dto = requestDTO.clone(OfflinePayInfoRequestDTO.class);
        dto.setCollectMoneyInfo(requestDTO.getCollectMoneyInfo().clone(OfflineCollectionInfoDTO.class));
        dto.setPayInfo(requestDTO.getPayInfo().clone(OfflinePayInfoDTO.class));
        Payload<Boolean> payload = saleOrderInfoRemote.saveOfflinePayInfo(dto);
        return payload.getPayload();
    }

    /**
     * @param requestDTO
     * @Description: 线下支付-还款.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    @Override
    public Boolean saveRepaymentOfflinePayInfo(OfflinePaymentInfoRequestDTO requestDTO) throws Exception {
        OfflinePayInfoRequestDTO dto = requestDTO.clone(OfflinePayInfoRequestDTO.class);
        dto.setCollectMoneyInfo(requestDTO.getCollectMoneyInfo().clone(OfflineCollectionInfoDTO.class));
        dto.setPayInfo(requestDTO.getPayInfo().clone(OfflinePayInfoDTO.class));
        Payload<Boolean> payload = saleOrderInfoRemote.saveRepaymentOfflinePayInfo(dto);
        return payload.getPayload();
    }

    /**
     * @Description: 获取确认订单的订单信息.
     * @Param: [query]
     * @return: com.deepexi.dd.system.mall.domain.dto.SaleOrderAscertainInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    @Override
    public SaleOrderAscertainInfoResponseDTO ascertainOrderInfo(SaleOrderAscertainInfoRequestQuery query) throws Exception {
        Payload<SaleOrderAscertainResponseDTO> payload =
                saleOrderInfoRemote.ascertainOrderInfo(query.clone(SaleOrderAscertainRequestQuery.class));
        return GeneralConvertUtils.conv(payload.getPayload(), SaleOrderAscertainInfoResponseDTO.class);
    }

    /**
     * @param id
     * @param record
     * @Description: 根据ID更新销售订单表.
     * @Param: [id, record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/28
     */
    @Override
    public Payload<Boolean> updateById(Long id, SaleOrderInfoEditRequestDTO record) throws Exception {
        SaleOrderInfoRequestDTO dto = record.clone(SaleOrderInfoRequestDTO.class);
        dto.setOrderConsigneeInfo(record.getOrderConsigneeInfo().clone(OrderConsigneeInfoAddRequestDTO.class));
        //订单送货地址对象
        if (!ObjectUtils.isEmpty(record.getItems())) {
            List<SaleOrderItemAddRequestDTO> saleOrderItemAddRequestDTOS =
                    GeneralConvertUtils.convert2List(record.getItems(), SaleOrderItemAddRequestDTO.class);
            dto.setItems(saleOrderItemAddRequestDTOS);//商品信息

        }
        if (!ObjectUtils.isEmpty(record.getOrderInvoiceInfo())) {
            dto.setOrderInvoiceInfo(record.getOrderInvoiceInfo().clone(OrderInvoiceInfoRequestDTO.class));//开票信息
        }
        if (!ObjectUtils.isEmpty(record.getOrderExpenseInfo())) {
            List<OrderExpenseInfoRequestDTO> expenseInfoRequestDTOS =
                    GeneralConvertUtils.convert2List(record.getOrderExpenseInfo(), OrderExpenseInfoRequestDTO.class);
            dto.setOrderExpenseInfo(expenseInfoRequestDTOS);//订单费用信息列表
        }
        //设置自提信息
        if (record.getOrderSelfRaisingInfoAddInfo() != null && record.getOrderSelfRaisingInfoAddInfo().getDeliveryWareHouseId() != null) {
            dto.setOrderSelfRaisingInfoAddInfo(record.getOrderSelfRaisingInfoAddInfo().clone(OrderSelfRaisingInfoAddRequestDTO.class));
        }
        Payload<SaleOrderInfoAddResponseDTO> payload = saleOrderInfoRemote.updateById(id, dto);
        return new Payload<>(true);
    }

    @Override
    public SaleOrderInfoResponseDTO updatePlanById(Long id, SaleOrderInfoPlanAddRequestDTO record) throws Exception {
        SaleOrderInfoRequestDTO dto = record.clone(SaleOrderInfoRequestDTO.class);
        List<SaleOrderItemAddRequestDTO> saleOrderItemAddRequestDTOS =
                GeneralConvertUtils.convert2List(record.getItems(), SaleOrderItemAddRequestDTO.class);
        dto.setItems(saleOrderItemAddRequestDTOS);//商品信息
        Payload<SaleOrderInfoResponseDTO> payload = saleOrderInfoRemote.updatePlanById(id, dto);
        SaleOrderInfoResponseDTO saleOrderInfoAppAddResponseDTO = GeneralConvertUtils.conv(payload.getPayload(),
                                                                                           SaleOrderInfoResponseDTO.class);
        saleOrderInfoAppAddResponseDTO.setCommodityType(saleOrderItemAddRequestDTOS.size());
        Long sum = saleOrderItemAddRequestDTOS.stream().mapToLong(a -> a.getSkuQuantity()).sum();
        saleOrderInfoAppAddResponseDTO.setCommodityNum(sum);
        return saleOrderInfoAppAddResponseDTO;
    }

    /**
     * @param id
     * @Description: 支付完成后的支付信息.
     * @Param: [id, code]
     * @return: com.deepexi.dd.system.mall.domain.dto.PaymentCompletedInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/28
     */
    @Override
    public PaymentCompletedInfoResponseDTO paymentCompletedInfo(Long id, String code) throws Exception {
        Payload<PaymentCompletedResponseDTO> payload = saleOrderInfoRemote.paymentCompletedInfo(id, code);
        return GeneralConvertUtils.conv(payload.getPayload(), PaymentCompletedInfoResponseDTO.class);
    }

    /**
     * @Description: 获取默认订单信息
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/5
     */
    @Override
    public DefualtOrderResponseVO getDefualtOrderInfo(DefualtOrderRequestQuery requestQuery) throws Exception {
        DefualtOrderResponseVO result = new DefualtOrderResponseVO();
        // 设置订单信息.
        result.setOrderInfo(getOrderInfo(requestQuery.getTicketType(), requestQuery.getOrgId()));
        // 设置收货地址信息.
        result.setInvoiceInfo(getInvoiceInfo(requestQuery.getOrgId()));
        // 设置发票信息.
        result.setAddressInfo(getAddressInfo(requestQuery.getOrgId()));
        // 设置订单类型信息.
        result.setBillTypeInfo(getBillTypeInfo(requestQuery));
        return result;
    }

    /**
     * @Description: 获取订单类型.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/8
     */
    private ToolBilltypeResponseDTO getBillTypeInfo(DefualtOrderRequestQuery requestQuery) throws Exception {
        Payload<ToolBilltypeResponseDTO> payload =
                toolBilltypeRemote.selectById(BigDecimal.valueOf(requestQuery.getTicketType()).longValue());
        if (Objects.nonNull(payload.getPayload())) {
            return GeneralConvertUtils.conv(payload.getPayload(), ToolBilltypeResponseDTO.class);
        }
        return null;
    }

    /**
     * @Description: 设置收货地址信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/5
     */
    private MerchantAddressResponseVO getAddressInfo(Long orgId) throws Exception {
        MerchantAddressRequestQuery requestQuery = new MerchantAddressRequestQuery();
        requestQuery.setTenantId(appRuntimeEnv.getTenantId());
        List<MerchantAddressResponseVO> address = addressService.findList(requestQuery);
        if (CommonUtils.isNotEmpty(address)) {
            // 首先获取默认的,没有默认的获取第一条数据.
            List<MerchantAddressResponseVO> result =
                    address.stream().filter(s -> s.getIsDefault().booleanValue()).collect(Collectors.toList());
            if (CommonUtils.isNotEmpty(result)) {
                return result.get(0);
            } else {
                return address.get(0);
            }
        }
        return new MerchantAddressResponseVO();
    }

    /**
     * @Description: 设置发票新.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/5
     */
    private MerchantInvoiceResponseVO getInvoiceInfo(Long orgId) throws Exception {
        MerchantInvoiceRequestQuery requestQuery = new MerchantInvoiceRequestQuery();
        requestQuery.setTenantId(appRuntimeEnv.getTenantId());
        List<MerchantInvoiceResponseVO> invoices = invoiceService.findAll(requestQuery);
        if (CommonUtils.isNotEmpty(invoices)) {
            // 首先获取默认的,没有默认的获取第一条数据.
            List<MerchantInvoiceResponseVO> result =
                    invoices.stream().filter(s -> s.getIsDefault().booleanValue()).collect(Collectors.toList());
            if (CommonUtils.isNotEmpty(result)) {
                return result.get(0);
            } else {
                return invoices.get(0);
            }
        }
        return new MerchantInvoiceResponseVO();
    }

    /**
     * @Description: 获取订单信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/5
     */
    private OrderInfoResponseVO getOrderInfo(Integer ticketType, Long orgId) {
        OrderInfoResponseVO orderInfo = new OrderInfoResponseVO();
        orderInfo.setTicketType(ticketType);
        orderInfo.setTicketTypeName(ticketType.equals(0) ? "直供订单" : "普通销售单");
        if (ticketType.equals(0)) {
            GroupResultVO vo = appRuntimeEnv.getTopOrganization();
            orderInfo.setSupplierName(vo.getName());
        } else {
            if (Objects.isNull(orgId)) {
                throw new ApplicationException("组织id不允许为空.");
            }
            PayloadGroupResultVO resultVO = groupControllerApi.getGroup(orgId, appRuntimeEnv.getTenantId(),
                                                                        appRuntimeEnv.getUserId(), appRuntimeEnv.getUsername());
            if (SUCCESS.equals(resultVO.getCode())) {
                GroupResultVO grouVO = resultVO.getPayload();
                orderInfo.setSupplierName(grouVO.getName());
            }
        }
        return orderInfo;
    }

    /**
     * @param dto
     * @Description: 发货.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/6
     */
    @Override
    public Boolean deliveryGoodsOrder(SaleOrderInfoDeliverGoodsInfoRequestDTO dto) throws Exception {
        SaleOrderInfoDeliverGoodsRequestDTO requestDTO = dto.clone(SaleOrderInfoDeliverGoodsRequestDTO.class);
        requestDTO.setOrderDeliveryInfo(dto.getOrderDeliveryInfo().clone(OrderDeliveryInfoAddRequestDTO.class));//发货物流信息
        requestDTO.setOrderDeliveryConsigneeInfo(dto.getOrderDeliveryConsigneeInfo().clone(OrderDeliveryConsigneeInfoRequestDTO.class));//订单送货地址信息
        Payload<Boolean> payload = saleOrderInfoRemote.deliveryGoodsOrder(requestDTO);
        return payload.getPayload();
    }

    @Override
    public PageBean<ShopPlanItemDTO> planCommodutyPage(PlanCommodityRequestQuery planCommodityRequestQuery) throws Exception {
        List<Long> skuIds = new ArrayList<>();
        if (planCommodityRequestQuery.getCommodityRequestEnum().equals(CommodityRequestEnum.PLAN_ORDER_ADD)) {
            ToolCategoryRequestQuery toolCategoryRequestQuery =
                    planCommodityRequestQuery.clone(ToolCategoryRequestQuery.class);
            //产品线为空，则说明，传进来的是其他产品线
            if (planCommodityRequestQuery.getProductId() != null && !"".equals(planCommodityRequestQuery.getProductId())) {
                //如果分类categoryId不为空，则拿这个分类来查，为空拿productId来查
                if (planCommodityRequestQuery.getCategoryId() == null || planCommodityRequestQuery.getCategoryId() == 0) {
                    toolCategoryRequestQuery.setCategoryId(planCommodityRequestQuery.getProductId().toString());
                } else {
                    toolCategoryRequestQuery.setCategoryId(planCommodityRequestQuery.getCategoryId().toString());
                }
                Payload<List<Long>> payload =
                        toolCommoditytypeAuthorizeApiRemote.getCommodityListByCategoryId(toolCategoryRequestQuery);
                //产品线下的sku集合
                //很无奈不转就会报错
                List<Long> skuIdList1 = payload.getPayload();
                if (CollectionUtils.isEmpty(skuIdList1)) {
                    return new PageBean<>();
                }
                List<Long> skuIdList = new ArrayList<>();
                for (int x = 0; x < skuIdList1.size(); x++) {
                    Object o = skuIdList1.get(x);
                    Long vid = Long.valueOf(o.toString());
                    skuIdList.add(vid);
                }

                if (CollectionUtils.isEmpty(skuIdList) || planCommodityRequestQuery.getCommodityAuthorized() == null) {
                    return new PageBean<>();
                } else {
                    //授权商品以及产品线并集的商品
                    List<Long> authorizedIds = new ArrayList<>();
                    authorizedIds.addAll(planCommodityRequestQuery.getCommodityAuthorized().getAuthorizedskuId());
                    if (CollectionUtils.isEmpty(authorizedIds) || authorizedIds.get(0) == null) {
                        return new PageBean<>();
                    }
                    skuIdList.forEach(item -> {
                        authorizedIds.forEach(id -> {
                            if (item.equals(id)) {
                                skuIds.add(id);
                            }
                        });
                    });

                }
                if (CollectionUtils.isEmpty(skuIds) || skuIds.get(0) == null) {
                    return new PageBean<>();
                }
            } else {//只要授权的商品
                List<Long> authorizedIds = new ArrayList<>();
                authorizedIds.addAll(planCommodityRequestQuery.getCommodityAuthorized().getAuthorizedskuId());
                if (CollectionUtils.isEmpty(authorizedIds) || authorizedIds.get(0) == null) {
                    return new PageBean<>();
                }
                skuIds.addAll(authorizedIds);
            }
            PageBean<ShopPlanItemDTO> pageBean = getShopItemDTOListByPage(planCommodityRequestQuery, skuIds);
            if (CollectionUtils.isNotEmpty(pageBean.getContent())) {
                setAuthorizedPrice(pageBean.getContent(), planCommodityRequestQuery);
            } else {
                return pageBean;
            }
            return pageBean;
        } else {//订单修改选择商品,直供单查询所有的直供商品，非直供单查询的是授权的商品
            if (CommodityRequestEnum.ORDER_UPDATE.equals(planCommodityRequestQuery.getCommodityRequestEnum())
                    && planCommodityRequestQuery.getOrderType() != 0) {//非直供单
                List<Long> authIds = new ArrayList<>();
                if (planCommodityRequestQuery.getCommodityAuthorized() != null) {
                    authIds.addAll(planCommodityRequestQuery.getCommodityAuthorized().getAuthorizedskuId());
                }
                if (CollectionUtils.isEmpty(authIds) || authIds.get(0) == null) {
                    return new PageBean<>();
                }
                skuIds.addAll(authIds);
            }
            PageBean<ShopPlanItemDTO> pageBean = getShopItemDTOListByPage(planCommodityRequestQuery, skuIds);
            if (planCommodityRequestQuery.getOrderType() == 0) {//直供单查询库存;其他的显示有货
                setSkuStock(pageBean.getContent(), planCommodityRequestQuery);
            }
            if (CollectionUtils.isNotEmpty(pageBean.getContent())) {
                setAuthorizedPrice(pageBean.getContent(), planCommodityRequestQuery);
            } else {
                return pageBean;
            }
            if (CollectionUtils.isNotEmpty(pageBean.getContent())) {
                setAuthorizedPrice(pageBean.getContent(), planCommodityRequestQuery);
            } else {
                return pageBean;
            }
            pageBean.setSize(planCommodityRequestQuery.getSize());
            if (pageBean.getNumber() == 0) {
                pageBean.setNumber(1);
            }
            return pageBean;
        }
    }

    @Override
    public Boolean deletePlanByIdIn(Long id) throws Exception {
        Payload<Boolean> payload = saleOrderInfoRemote.deletePlanById(id);
        return payload.getPayload();
    }

    /**
     * 处理授权价格,修改过来的请求：直供单商品取的是直供价格（位置在网批->常规网批->查看），其他单授权价格；订货计划单也是取授权价格
     *
     * @Description: 如果价格为null, 默认初始化为0.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/3
     */
    private void setAuthorizedPrice(List<ShopPlanItemDTO> shopItemDTOList,
                                    PlanCommodityRequestQuery planCommodityRequestQuery) throws Exception {
        List<Long> skuIds = shopItemDTOList.stream().map(ShopPlanItemDTO::getSkuId).collect(Collectors.toList());
        //授权价格
        if (CommodityRequestEnum.ORDER_UPDATE.equals(planCommodityRequestQuery.getCommodityRequestEnum())
                && planCommodityRequestQuery.getOrderType() == 0) {//直供，取直供价格
            DirectSkuPriceRequestDTO directSkuPriceRequestDTO = new DirectSkuPriceRequestDTO();
            directSkuPriceRequestDTO.setAppId(planCommodityRequestQuery.getAppId());
            directSkuPriceRequestDTO.setOnStatus(1);
            directSkuPriceRequestDTO.setTenantId(planCommodityRequestQuery.getTenantId());
            List<String> skuIdstr =
                    shopItemDTOList.stream().map(a -> a.getSkuId().toString()).collect(Collectors.toList());
            directSkuPriceRequestDTO.setSkuIdList(skuIdstr);
            Map<Long, BigDecimal> result = authorizedPriceApiRemote.getDirectSkuPrice(directSkuPriceRequestDTO);
            if (result != null && result.size() > 0) {
                shopItemDTOList.forEach(shopPlanItemDTO -> {
                    Object price = result.get(shopPlanItemDTO.getSkuId());
                    if (price == null) {
                        shopPlanItemDTO.setAuthorizedPrice(null);
                    } else {
                        shopPlanItemDTO.setAuthorizedPrice(new BigDecimal(price.toString()));
                    }
                });
            }

        } else {//授权价格
            List<AuthorizedPriceRquestDTO> list = new ArrayList<>();
            List<Long> commodityAuthorizeds = planCommodityRequestQuery.getCommodityAuthorized().getAuthorizedskuId();
            skuIds.forEach(item -> {
                commodityAuthorizeds.forEach(authorized -> {
                    if (authorized.equals(item)) {
                        AuthorizedPriceRquestDTO authorizedPriceRquestDTO = new AuthorizedPriceRquestDTO();
                        authorizedPriceRquestDTO.setSellerId(planCommodityRequestQuery.getCommodityAuthorized().getAuthorizeBy());
                        authorizedPriceRquestDTO.setSkuId(item);
                        authorizedPriceRquestDTO.setAppId(planCommodityRequestQuery.getAppId());
                        authorizedPriceRquestDTO.setTenantId(planCommodityRequestQuery.getTenantId());
                        list.add(authorizedPriceRquestDTO);
                    }

                });

            });
            Payload<List<AuthorizedPriceResponseDTO>> payload =
                    authorizedPriceApiRemote.getAuthorizedPrice(list);
            List<AuthorizedPriceResponseDTO> authorizedPriceResponseDTOList =
                    GeneralConvertUtils.convert2List(payload.getPayload(), AuthorizedPriceResponseDTO.class);
            if (CollectionUtils.isNotEmpty(authorizedPriceResponseDTOList)) {
                shopItemDTOList.forEach(shopItemDTO -> {
                    authorizedPriceResponseDTOList.forEach(authorizedPriceResponseDTO -> {
                        if (shopItemDTO.getSkuId().equals(authorizedPriceResponseDTO.getSkuId())) {
                            shopItemDTO.setAuthorizedPrice(authorizedPriceResponseDTO.getAuthorizedPrice());
                        }
                    });
                });
            }
        }

    }

    public PageBean<ShopPlanItemDTO> getShopItemDTOListByPage(PlanCommodityRequestQuery planCommodityRequestQuery,
                                                              List<Long> skuIds) {
        //表示上架的商品
        EsGoodOnlineRequestDTO query = new EsGoodOnlineRequestDTO();
        query.setSkuIds(skuIds);
        query.setOnlineState(1);
        query.setAppId(planCommodityRequestQuery.getAppId());
        query.setPage(planCommodityRequestQuery.getPage());
        query.setSize(planCommodityRequestQuery.getSize());
        query.setTenantId(planCommodityRequestQuery.getTenantId());
        // query.setSkuName(planCommodityRequestQuery.getSkuName());
        if (planCommodityRequestQuery.getSkuName() != null && !"".equals(planCommodityRequestQuery.getSkuName())) {
            query.setItemNameFuzzy(planCommodityRequestQuery.getSkuName());
        }
        if (planCommodityRequestQuery.getSkuCode() != null && !"".equals(planCommodityRequestQuery.getSkuCode())) {
            query.setSkuCodeFuzzy(planCommodityRequestQuery.getSkuCode());
        }
        //query.setCategoryId(planCommodityRequestQuery.getCategoryId());
        if (CommodityRequestEnum.ORDER_UPDATE.equals(planCommodityRequestQuery.getCommodityRequestEnum())
                && planCommodityRequestQuery.getOrderType() == 0) {//上架的直供的全部商品
            //1 直供 2非直供  3 直供活动
            query.setShopId(ShopTypeEnum.STORE.getType());
            query.setSkuIds(null);
        } else {
            query.setShopId(ShopTypeEnum.NOSTORE.getType());
        }
        PageBean<ShopPlanItemDTO> pageBean = new PageBean<>();
        List<ShopPlanItemDTO> shopItemDTOList = new ArrayList<>();
        PayloadPageBeanEsGoodOnlineResponseDTO shopItemUpShelf = shopItemOpenApi.pageEsGoodOnline(query);
        PageBeanEsGoodOnlineResponseDTO list = shopItemUpShelf.getPayload();
        if (!StringUtils.isEmpty(list)) {
            pageBean.setNumber(list.getNumber());
            pageBean.setNumberOfElements(list.getNumberOfElements());
            pageBean.setTotalElements(list.getTotalElements());
            pageBean.setSize(list.getSize());
            pageBean.setTotalPages(list.getTotalPages());
            List<EsGoodOnlineResponseDTO> esGoodList = list.getContent();
            if (null != esGoodList) {
                esGoodList.stream().forEach(dto -> {
                    EsGoodOnlineGroupSkuResponseDTO sku = dto.getSku();
                    EsGoodOnlineItemWholeResponseDTO itemWhole = dto.getItemWhole();
                    ShopPlanItemDTO shopItemDTO = new ShopPlanItemDTO();
                    shopItemDTO.setGoodId(dto.getId());
                    shopItemDTO.setStatus(dto.getStatus());
                    shopItemDTO.setSkuId(dto.getSkuId());
                    shopItemDTO.setShopId(dto.getShopId());
                    shopItemDTO.setChannelType(dto.getChannelId());
                    shopItemDTO.setItemId(dto.getItemId());
                    if (!StringUtils.isEmpty(sku)) {
                        shopItemDTO.setCode(sku.getCode());
                        List<EsGoodOnlineGroupSkuPropertyValueResponseDTO> responseDTOS =
                                ObjectCloneUtils.convertList(sku.getSkuPropertyValueList(),
                                                             EsGoodOnlineGroupSkuPropertyValueResponseDTO.class);
                        shopItemDTO.setSkuPropertyValueList(responseDTOS);
                        shopItemDTO.setUnitId(sku.getUnitId());
                        shopItemDTO.setUnitName(sku.getUnitName());
                        shopItemDTO.setAuthorizedPrice(sku.getPrice());
                        shopItemDTO.setMajorPicture(sku.getMajorPicture());

                    }
                    if (null != itemWhole) {
                        shopItemDTO.setName(itemWhole.getName());
                        shopItemDTO.setItemCode(itemWhole.getCode());
                        // shopItemDTO.setMajorPicture(itemWhole.getMajorPicture());
                    }
                    shopItemDTOList.add(shopItemDTO);
                });
            }
        }
        pageBean.setContent(shopItemDTOList);
        return pageBean;
    }

    private void setSkuStock(List<ShopPlanItemDTO> shopItemDTOList,
                             PlanCommodityRequestQuery planCommodityRequestQuery) throws Exception {
        List<Long> skuIds = shopItemDTOList.stream().map(ShopPlanItemDTO::getSkuId).collect(Collectors.toList());
        SkuStockRequestDTO skuStockRequestDTO = new SkuStockRequestDTO();
        skuStockRequestDTO.setAppId(planCommodityRequestQuery.getAppId());
        skuStockRequestDTO.setShopId(ShopTypeEnum.STORE.getType());
        skuStockRequestDTO.setTenantId(planCommodityRequestQuery.getTenantId());
        skuStockRequestDTO.setSkuIdList(skuIds);
        Map<Long, Long> result = authorizedPriceApiRemote.getSkuStock(skuStockRequestDTO);
        shopItemDTOList.forEach(shopPlanItemDTO -> {
            shopPlanItemDTO.setStockQty(result.get(shopPlanItemDTO.getSkuId()).intValue());
        });

    }

    @Override
    public Payload<Boolean> getOfflinePayUploadPaySetting(GetPaySettingRequestDTO requestDTO) throws Exception {
        boolean flag = false;
        ToolPaySettingRequestQuery toolQuery = new ToolPaySettingRequestQuery();
        toolQuery.setAppId(requestDTO.getAppId());
        toolQuery.setTenantId(requestDTO.getTenantId());
        toolQuery.setListType(requestDTO.getListType());
        toolQuery.setBusinessCode(requestDTO.getBusinessCode());
        Payload<ToolPaySettingResponseDTO> toolPayPayload = toolLinkRemote.getPaySetting(toolQuery);
        if (!Objects.isNull(toolPayPayload.getPayload()) && 1 == toolPayPayload.getPayload().getIsRequireCertificate()) {
            return new Payload<>(true);
        }
        CommonPaymentMallSettingRequestQuery query = new CommonPaymentMallSettingRequestQuery();
        query.setAppId(requestDTO.getAppId());
        query.setTenantId(requestDTO.getTenantId());
        query.setType(1);//类型:0-线上支付，1-线下支付、2-余额支付、3-信用支付
        query.setIsUpload(1);
        Payload<List<CommonPaymentMallSettingResponseDTO>> mallSettingPayload =
                commonPaymentMallSettingRemote.listFinanceCredits(query);
        if (!Objects.isNull(mallSettingPayload.getPayload()) && CollectionUtil.isNotEmpty(mallSettingPayload.getPayload())) {
            flag = true;
        }
        return new Payload<>(flag);
    }

    @Override
    public Payload<ToolPaySettingResponseDTO> getToolLinkPaySetting(GetPaySettingRequestDTO requestDTO) throws Exception {
        ToolPaySettingRequestQuery toolQuery = requestDTO.clone(ToolPaySettingRequestQuery.class);
        return toolLinkRemote.getPaySetting(toolQuery);
    }

    @Override
    public Payload<List<CommonPaymentMallSettingResponseDTO>> getCommonPaymentMallSetting(GetPaySettingRequestDTO requestDTO) throws Exception {
/*        CommonPaymentMallSettingRequestQuery query = new CommonPaymentMallSettingRequestQuery();
        query.setAppId(requestDTO.getAppId());
        query.setTenantId(requestDTO.getTenantId());
        query.setPartnerId(requestDTO.getPartnerId());*/
        CommonPaymentMallSettingRequestQuery query = requestDTO.clone(CommonPaymentMallSettingRequestQuery.class);
        return commonPaymentMallSettingRemote.listFinanceCredits(query);
    }

    @Override
    public SaleOrPinkOrderResponseDTO findOrderByIdAndCode(SaleOrPinkOrderRequestDTO req) throws Exception {
        Payload<SaleOrPinkOrderResponseDTO> payload = saleOrderInfoRemote.findOrderByIdAndCode(req);
        if (SUCCESS.equals(payload.getCode())) {
            try {
                return GeneralConvertUtils.conv(payload.getPayload(), SaleOrPinkOrderResponseDTO.class);
            } catch (Exception e) {
                log.error("根据Id和Code查找销售订单或提货计划订单失败", e);
                throw new ApplicationException("查找销售订单或提货计划订单失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }
}
