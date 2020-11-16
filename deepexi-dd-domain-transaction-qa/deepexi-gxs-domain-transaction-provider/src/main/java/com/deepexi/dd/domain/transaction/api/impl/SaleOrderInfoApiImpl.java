package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolCategoryRequestQuery;
import com.deepexi.dd.domain.transaction.api.SaleOrderInfoApi;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.add.SaleOrderItemAddRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflineCollectionResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo.SaleOrderAscertainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.*;
import com.deepexi.dd.domain.transaction.domain.responseDto.SaleOrderInfoTransactionOLResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.SaleOrderInfoTransactionResponseDTO;
import com.deepexi.dd.domain.transaction.enums.BuyerTypeEnum;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.enums.TicketTypeEnum;
import com.deepexi.dd.domain.transaction.remote.order.BusinessPartnerClient;
import com.deepexi.dd.domain.transaction.remote.order.ToolCommoditytypeAuthorizeClient;
import com.deepexi.dd.domain.transaction.service.*;
import com.deepexi.dd.domain.transaction.util.SaleOrderInfoDtoHelper;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import domain.dto.BusinessPartnerResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-03 15:08
 */
@RestController
public class SaleOrderInfoApiImpl implements SaleOrderInfoApi {
    Logger logger = LoggerFactory.getLogger(SaleOrderInfoApiImpl.class);
    private static final Integer isActivity = 0;
    @Autowired
    SaleOrderInfoService saleOrderInfoService;

    @Autowired
    SaleOrderItemService saleOrderItemService;

    @Autowired
    SaleOrderOperationService saleOrderOperationService;

    @Autowired
    private IamUserService iamUserService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private SaleOrderOperationRecordService saleOrderOperationRecordService;

    @Value("${appId}")
    private Long appId;

    @Autowired
    BusinessPartnerClient businessPartnerClient;

    @Autowired
    ToolCommoditytypeAuthorizeClient toolCommoditytypeAuthorizeClient;

    @Override
    @ApiOperation("分页查询销售订单表列表")
    public Payload<PageBean<SaleOrderInfoTransactionResponseDTO>> listSaleOrderInfosPage(@Valid SaleOrderInfoRequestQuery query) throws Exception {
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        model.setListType("SalesOrder");
        PageBean<SaleOrderInfoTransactionResponseDTO> pageBean =
                GeneralConvertUtils.convert2PageBean(saleOrderInfoService.listSaleOrderInfosPage(model),
                        SaleOrderInfoTransactionResponseDTO.class);

        return new Payload<>(pageBean);
    }

    @Override
    @ApiOperation("分页查询销售订单表列表 按单收款")
    public Payload<PageBean<SaleOrderInfoReceiptResponseDTO>> listSaleOrderInfosPageForReceipt(@Valid SaleOrderInfoReceiptRequestQuery query) throws Exception {
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        model.setListType("ListCollection");
        PageBean<SaleOrderBtnResponseDTO> saleOrderInfoResponseDTOPageBean =
                saleOrderInfoService.listSaleOrderInfosPage(model);
        PageBean<SaleOrderInfoReceiptResponseDTO> pageBean =
                GeneralConvertUtils.convert2PageBean(saleOrderInfoResponseDTOPageBean,
                        SaleOrderInfoReceiptResponseDTO.class);

        for (SaleOrderBtnResponseDTO saleOrderBtnResponseDTO : saleOrderInfoResponseDTOPageBean.getContent()) {
            for (SaleOrderInfoReceiptResponseDTO saleOrderInfoReceiptResponseDTO : pageBean.getContent()) {
                if (saleOrderBtnResponseDTO.getId().equals(saleOrderInfoReceiptResponseDTO.getId())) {
                    saleOrderInfoReceiptResponseDTO.setOrderNo(saleOrderBtnResponseDTO.getCode());
                    saleOrderInfoReceiptResponseDTO.setAmount(saleOrderBtnResponseDTO.getTotalAmount());
                    saleOrderInfoReceiptResponseDTO.setCustomerId(saleOrderBtnResponseDTO.getPartnerId());
                    saleOrderInfoReceiptResponseDTO.setCustomerName(saleOrderBtnResponseDTO.getPartnerName());
                    saleOrderInfoReceiptResponseDTO.setSettlementId(saleOrderBtnResponseDTO.getPartnerId());
                    saleOrderInfoReceiptResponseDTO.setSettlementName(saleOrderBtnResponseDTO.getPartnerName());
                    saleOrderInfoReceiptResponseDTO.setReceiptsTime(saleOrderBtnResponseDTO.getTicketDate());
                    saleOrderInfoReceiptResponseDTO.setPaymentAmount(saleOrderBtnResponseDTO.getPayAmount());
                }
            }
        }

        return new Payload<>(pageBean);
    }


    @Override
    @ApiOperation("分页查询销售订单表列表(在线订购)")
    public Payload<PageBean<SaleOrderInfoTransactionOLResponseDTO>> listSaleOrderPage(@Valid SaleOrderInfoRequestQuery query) throws Exception {
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        PageBean<SaleOrderBtnResponseDTO> result = saleOrderInfoService.listSaleOrderInfosPageForApi(model);

        for (SaleOrderBtnResponseDTO saleOrderInfoOLResponseDTO : result.getContent()) {
            if (saleOrderInfoOLResponseDTO.getSaleOutTaskList() != null) {
                saleOrderInfoOLResponseDTO.setSaleOutTaskAmount(saleOrderInfoOLResponseDTO.getSaleOutTaskList().size());
            } else {
                saleOrderInfoOLResponseDTO.setSaleOutTaskAmount(0);
            }
        }
        PageBean<SaleOrderInfoTransactionOLResponseDTO> pageBean = GeneralConvertUtils.convert2PageBean(result,
                SaleOrderInfoTransactionOLResponseDTO.class);
        return new Payload<>(pageBean);
    }

    @Override
    @ApiOperation("新增销售订单")
    public Payload<SaleOrderInfoAddResponseDTO> createOrder(@RequestBody @Valid SaleOrderInfoRequestDTO record) throws Exception {
        if (TicketTypeEnum.PLAN.getValue().equals(record.getTicketType())) {
            return createPlanOrder(record);
        }
        if (null == BuyerTypeEnum.getBuyerTypeEnum(record.getBuyerType())) {
            throw new ApplicationException(ResultEnum.BUYTYPE_ERROR);
        }
        if (CollectionUtil.isEmpty(record.getItems())) {
            throw new ApplicationException(ResultEnum.COMMODITY_NULL_ERROR);
        }
        Long orgId = null;
        String orgName = null;
        //获取所有商品的组织
        Set<Long> orgIds =
                record.getItems().stream().map(SaleOrderItemAddRequestDTO::getOrgId).collect(Collectors.toSet());
        if (orgIds.size() > 1) {
            throw new ApplicationException((ResultEnum.ORDER_ORGID_ERROR));
        }
        orgId = orgIds.iterator().next();
        if (null == orgId) {
            throw new ApplicationException((ResultEnum.ORDER_SUPPLIER_ERROR));
        }
        //根据商品组织获取具体组织信息
        GroupResultVO group = iamUserService.getGroup(orgId, appRuntimeEnv.getTenantId(), appRuntimeEnv.getUserId(),
                appRuntimeEnv.getUsername());
        if (group != null) {
            orgName = group.getName();
        }
        //获取客户信息
        if (!isActivity.equals(iamUserService.getUserInfo().getStatus())) {
            throw new ApplicationException(ResultEnum.USER_NOT_ACTIVITY);
        }
        //验证订单类型
        SaleOrderInfoDTO saleOrder = SaleOrderInfoDtoHelper.convertToSaleOrderInfoDTO(record);
        saleOrder.setBuyerId(appRuntimeEnv.getTopOrganization().getId());
        saleOrder.setBuyerName(appRuntimeEnv.getTopOrganization().getName());
        //获取供应商信息
        saleOrder.setSellerName(orgName);
        saleOrder.setSellerId(orgId);
        //设置隔离数据
        saleOrder.setIsolationId(saleOrder.getBuyerId()+"");
        //设置partnerId和partnerName
        Payload<BusinessPartnerResponseDTO> businessPartnerResponseDTOPayload = null;
        try {
            BusinessPartnerRequestQuery businessPartnerRequestQuery = new BusinessPartnerRequestQuery();
            businessPartnerRequestQuery.setTenantId(appRuntimeEnv.getTenantId());
            businessPartnerRequestQuery.setAppId(appId);
            businessPartnerRequestQuery.setOrgId(Long.valueOf(appRuntimeEnv.getTopOrganization().getId()));

            businessPartnerResponseDTOPayload = businessPartnerClient.getPartner(businessPartnerRequestQuery);
            logger.info("获取伙伴id:{}", JsonUtil.bean2JsonString(businessPartnerResponseDTOPayload));
            if (!businessPartnerResponseDTOPayload.getCode().equals("0")) {
                throw new ApplicationException("获取伙伴ID失败");
            }
        } catch (Exception e) {
            logger.error("获取伙伴ID失败:", e);
            throw new ApplicationException("获取伙伴ID失败");
        }
        BusinessPartnerResponseDTO businessPartner =
                GeneralConvertUtils.conv(businessPartnerResponseDTOPayload.getPayload(),
                        BusinessPartnerResponseDTO.class);
        saleOrder.setPartnerId(businessPartner.getId());
        saleOrder.setPartnerName(businessPartner.getName());

        SaleOrderInfoDTO saleOrderInfoDTOResult = saleOrderInfoService.createOrder(saleOrder);

        return new Payload<>(saleOrderInfoDTOResult.clone(SaleOrderInfoAddResponseDTO.class));
    }


    @ApiOperation("根据ID查询销售订单表")
    @GetMapping("/{id}")
    public Payload<SaleOrderInfoResponseDTO> selectById(@PathVariable("id") Long id) {
        try {
            return new Payload(GeneralConvertUtils.conv(saleOrderInfoService.selectById(id),SaleOrderInfoResponseDTO.class));
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        }
    }

    @ApiOperation("查询分页订单明细信息")
    @GetMapping("/itemPage")
    public Payload<PageBean<SaleOrderItemResponseDTO>> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) {
        try {
            PageBean<SaleOrderItemResponseDTO> pageBean = saleOrderItemService.listSaleOrderItemsPage(query);
            return new Payload<>(pageBean);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }

    }

    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    public Payload<Boolean> cancelSaleOrder(@RequestBody @Valid SaleOrderCancelRequestDTO saleOrderCancelRequestDTO) {
        return new Payload<>(saleOrderInfoService.cancelSaleOrder(saleOrderCancelRequestDTO));
    }

    @ApiOperation("关闭订单")
    @PostMapping("/close")
    public Payload<Boolean> closeSaleOrder(@RequestBody @Valid SaleOrderCloseRequestDTO saleOrderCloseRequestDTO) {
        return new Payload<>(saleOrderInfoService.closeSaleOrder(saleOrderCloseRequestDTO));
    }

    @ApiOperation("接单")
    @PostMapping("/orderReceiving")
    public Payload<Boolean> orderReceiving(@RequestBody @Valid SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) {
        try {
            return new Payload<>(saleOrderInfoService.orderReceiving(saleOrderReceivingRequestDTO));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("支付(中台预下单)")
    @PostMapping("/pay")
    public Payload<SaleOrderPayResponseDTO> paySaleOrder(@RequestBody @Valid SaleOrderPayRequestDTO saleOrderPayRequestDTO) {
        try {
            return new Payload<>(saleOrderInfoService.paySaleOrder(saleOrderPayRequestDTO));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }

    }

    @Override
    @ApiOperation("线上支付-还款单(中台预下单)")
    @PostMapping("/financeCreditPaySaleOrder")
    public Payload<SaleOrderPayResponseDTO> financeCreditPaySaleOrder(@Valid @RequestBody com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        try {
            return new Payload<>(saleOrderInfoService.financeCreditPaySaleOrder(saleOrderPayRequestDTO.clone(SalePickOrderPayRequestDTO.class)));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }


    @ApiOperation("支付回调")
    @PostMapping("/payCallBack")
    public Payload<Boolean> payCallBack(@RequestBody @Valid PayCallbackRequestDTO payCallbackRequestDTO) {
        try {
            return new Payload<>(saleOrderInfoService.onlinePayCallBack(payCallbackRequestDTO));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }

    }

    /**
     * @Description: 订单驳回.
     * @Param: [id]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    @Override
    public Payload<Boolean> orderRejected(@RequestBody @Valid SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.orderRejected(saleOrderRejectedRequestDTO));
    }

    /**
     * @Description: 查询线下付款的收款和付款信息.
     * @Param: [query]
     * @return: com.deepexi.util.config.Payload<com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    @Override
    public Payload<OfflinePayInfoResponseDTO> offlinePayInfo(@Valid OfflinePayInfoRequestQuery query) throws Exception {
        return new Payload<>(saleOrderInfoService.offlinePayInfo(query));
    }

    /**
     * @Description: 查询线下付款的收款信息.
     * @Param: [query]
     * @return: com.deepexi.util.config.Payload<com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflineCollectionResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/5
     */
    @Override
    public Payload<OfflineCollectionResponseDTO> offlineCollectionInfo(@Valid OfflinePayInfoRequestQuery query) throws Exception {
        return new Payload<>(saleOrderInfoService.offlineCollectionInfo(query));
    }

    /**
     * @Description: 线下付款.
     * @Param: [requestDTO]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    @Override
    public Payload<Boolean> saveOfflinePayInfo(@RequestBody @Valid OfflinePayInfoRequestDTO requestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.saveOfflinePayInfo(requestDTO));
    }

    /**
     * 线下支付-还款单
     * @param requestDTO
     * @return
     * @throws Exception
     */
    @Override
    public Payload<Boolean> saveRepaymentOfflinePayInfo(@Valid @RequestBody OfflinePayInfoRequestDTO requestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.saveRepaymentOfflinePayInfo(requestDTO));
    }

    /**
     * @Description: 确认订单的订单信息.
     * @Param: [query]
     * @return: com.deepexi.util.config.Payload<com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo.SaleOrderAscertainResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    @Override
    public Payload<SaleOrderAscertainResponseDTO> ascertainOrderInfo(@Valid SaleOrderAscertainRequestQuery query) throws Exception {
        return new Payload<>(saleOrderInfoService.ascertainOrderInfo(query));
    }

    /**
     * @Description: 根据ID更新销售订单表.
     * @Param: [id, record]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/7/28
     */
    @Override
    public Payload<SaleOrderInfoAddResponseDTO> updateById(@PathVariable("id") Long id,
                                       @RequestBody @Valid SaleOrderInfoRequestDTO record) throws Exception {

        if (null == BuyerTypeEnum.getBuyerTypeEnum(record.getBuyerType())) {
            throw new ApplicationException(ResultEnum.BUYTYPE_ERROR);
        }
        if (CollectionUtil.isEmpty(record.getItems())) {
            throw new ApplicationException(ResultEnum.COMMODITY_NULL_ERROR);
        }
        Long orgId = null;
        String orgName = null;
        //获取所有商品的组织
        Set<Long> orgIds =
                record.getItems().stream().map(SaleOrderItemAddRequestDTO::getOrgId).collect(Collectors.toSet());
        if (orgIds.size() > 1) {
            throw new ApplicationException((ResultEnum.ORDER_ORGID_ERROR));
        }
        orgId = orgIds.iterator().next();
        if (null == orgId) {
            throw new ApplicationException((ResultEnum.ORDER_SUPPLIER_ERROR));
        }
        //根据商品组织获取具体组织信息
        GroupResultVO group = iamUserService.getGroup(orgId, appRuntimeEnv.getTenantId(), appRuntimeEnv.getUserId(),
                appRuntimeEnv.getUsername());
        if (group != null) {
            orgName = group.getName();
        }
        //获取客户信息
        if (!isActivity.equals(iamUserService.getUserInfo().getStatus())) {
            throw new ApplicationException(ResultEnum.USER_NOT_ACTIVITY);
        }
        //验证订单类型
        SaleOrderInfoDTO saleOrder = SaleOrderInfoDtoHelper.convertToSaleOrderInfoDTO(record);
        saleOrder.setBuyerId(appRuntimeEnv.getTopOrganization().getId());
        saleOrder.setBuyerName(appRuntimeEnv.getTopOrganization().getName());
        //获取供应商信息
        saleOrder.setSellerName(orgName);
        saleOrder.setSellerId(orgId);
        //设置隔离数据
        saleOrder.setIsolationId(saleOrder.getBuyerId()+"");
        //设置partnerId和partnerName
        Payload<BusinessPartnerResponseDTO> businessPartnerResponseDTOPayload = null;
        try {
            BusinessPartnerRequestQuery businessPartnerRequestQuery = new BusinessPartnerRequestQuery();
            businessPartnerRequestQuery.setTenantId(appRuntimeEnv.getTenantId());
            businessPartnerRequestQuery.setAppId(appId);
            businessPartnerRequestQuery.setOrgId(Long.valueOf(appRuntimeEnv.getTopOrganization().getId()));

            businessPartnerResponseDTOPayload = businessPartnerClient.getPartner(businessPartnerRequestQuery);
            logger.info("获取伙伴id:{}", JsonUtil.bean2JsonString(businessPartnerResponseDTOPayload));
            if (!businessPartnerResponseDTOPayload.getCode().equals("0")) {
                throw new ApplicationException("获取伙伴ID失败");
            }
        } catch (Exception e) {
            logger.error("获取伙伴ID失败:", e);
            throw new ApplicationException("获取伙伴ID失败");
        }
        BusinessPartnerResponseDTO businessPartner =
                GeneralConvertUtils.conv(businessPartnerResponseDTOPayload.getPayload(),
                        BusinessPartnerResponseDTO.class);
        saleOrder.setPartnerId(businessPartner.getId());
        saleOrder.setPartnerName(businessPartner.getName());
        SaleOrderInfoDTO saleOrderInfoDTO=  saleOrderInfoService.updateById(id, saleOrder);
        SaleOrderInfoAddResponseDTO responseDTO= saleOrderInfoDTO.clone(SaleOrderInfoAddResponseDTO.class);
        responseDTO.setSubOrderCode(saleOrderInfoDTO.getCode());
        List<Long> list=new ArrayList<>();
        list.add(id);
        responseDTO.setSubOrderId(list);
        return new Payload<SaleOrderInfoAddResponseDTO>(responseDTO);
    }

    @Override
    public Payload<SaleOrderInfoResponseDTO> updatePlanById(@PathVariable("id") Long id,
                                                            @RequestBody @Valid SaleOrderInfoRequestDTO record) throws Exception {
        return new Payload<>(saleOrderInfoService.updatePlanById(id, record));
    }

    /**
     * @Description: 支付完成后的支付信息.
     * @Param: [saleOrderId,code]
     * @return: com.deepexi.util.config.Payload<com.deepexi.dd.domain.transaction.domain.dto.PaymentCompletedResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/28
     */
    @Override
    public Payload<PaymentCompletedResponseDTO> paymentCompletedInfo(@PathVariable("id") Long saleOrderId, @PathVariable("code") String code) throws Exception {
        return new Payload<>(saleOrderInfoService.paymentCompletedInfo(saleOrderId,code));
    }

    /**
     * @Description: 发货.
     * @Param: [saleOrderInfoDeliverGoodsRequestDTO]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/6
     */
    @Override
    public Payload<Boolean> deliveryGoodsOrder(@RequestBody @Valid SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.deliveryGoodsOrder(saleOrderInfoDeliverGoodsRequestDTO));
    }

    @Override
    public Payload<Boolean> deletePlanById(@PathVariable("id") Long id) throws Exception {
        return saleOrderInfoService.deletePlanById(id);
    }


    public Payload<SaleOrderInfoAddResponseDTO> createPlanOrder(@RequestBody @Valid SaleOrderInfoRequestDTO record) throws Exception {
        if (null == BuyerTypeEnum.getBuyerTypeEnum(record.getBuyerType())) {
            throw new ApplicationException(ResultEnum.BUYTYPE_ERROR);
        }
        if (CollectionUtil.isEmpty(record.getItems())) {
            throw new ApplicationException(ResultEnum.COMMODITY_NULL_ERROR);
        }
        Long orgId = null;
        String orgName = null;
        //获取所有商品的组织
        Set<Long> orgIds =
                record.getItems().stream().map(SaleOrderItemAddRequestDTO::getOrgId).collect(Collectors.toSet());
        if (orgIds.size() > 1) {
            throw new ApplicationException((ResultEnum.ORDER_ORGID_ERROR));
        }
        orgId = orgIds.iterator().next();
        if (null == orgId) {
            throw new ApplicationException((ResultEnum.ORDER_SUPPLIER_ERROR));
        }
        //根据商品组织获取具体组织信息
        GroupResultVO group = iamUserService.getGroup(orgId, appRuntimeEnv.getTenantId(), appRuntimeEnv.getUserId(),
                appRuntimeEnv.getUsername());
        if (group != null) {
            orgName = group.getName();
        }
        //获取客户信息
        if (!isActivity.equals(iamUserService.getUserInfo().getStatus())) {
            throw new ApplicationException(ResultEnum.USER_NOT_ACTIVITY);
        }
        //验证订单类型
        SaleOrderInfoDTO saleOrder = SaleOrderInfoDtoHelper.convertToSaleOrderInfoDTO(record);
        saleOrder.setBuyerId(appRuntimeEnv.getTopOrganization().getId());
        saleOrder.setBuyerName(appRuntimeEnv.getTopOrganization().getName());
        //获取供应商信息
        saleOrder.setSellerName(orgName);
        saleOrder.setSellerId(orgId);//为了验证通过，后续会删除
        if (!TicketTypeEnum.PLAN.getValue().equals(saleOrder.getTicketType())) {
            saleOrder.setSellerName(orgName);
            saleOrder.setSellerId(orgId);
        }
        //设置隔离数据
        saleOrder.setIsolationId(orgId + "");
        //设置partnerId和partnerName
        Payload<BusinessPartnerResponseDTO> businessPartnerResponseDTOPayload = null;
        try {
            BusinessPartnerRequestQuery businessPartnerRequestQuery = new BusinessPartnerRequestQuery();
            businessPartnerRequestQuery.setTenantId(appRuntimeEnv.getTenantId());
            businessPartnerRequestQuery.setAppId(appId);
            businessPartnerRequestQuery.setOrgId(Long.valueOf(appRuntimeEnv.getTopOrganization().getId()));

            businessPartnerResponseDTOPayload = businessPartnerClient.getPartner(businessPartnerRequestQuery);
            logger.info("获取伙伴id:{}", JsonUtil.bean2JsonString(businessPartnerResponseDTOPayload));
            if (!businessPartnerResponseDTOPayload.getCode().equals("0")) {
                throw new ApplicationException("获取伙伴ID失败");
            }
        } catch (Exception e) {
            logger.error("获取伙伴ID失败:", e);
            throw new ApplicationException("获取伙伴ID失败");
        }
        BusinessPartnerResponseDTO businessPartner =
                GeneralConvertUtils.conv(businessPartnerResponseDTOPayload.getPayload(),
                        BusinessPartnerResponseDTO.class);
        saleOrder.setPartnerId(businessPartner.getId());
        saleOrder.setPartnerName(businessPartner.getName());

        SaleOrderInfoDTO saleOrderInfoDTOResult = saleOrderInfoService.createPlanOrder(saleOrder);
        return new Payload<>(saleOrderInfoDTOResult.clone(SaleOrderInfoAddResponseDTO.class));
    }


    /**
     * 根据Id和Code查找销售订单或提货计划订单
     * @throws Exception
     */
    @Override
    public Payload<SaleOrPinkOrderResponseDTO> findOrderByIdAndCode(@RequestBody SaleOrPinkOrderRequestDTO req) throws Exception {
        SaleOrPinkOrderResponseDTO dto = saleOrderInfoService.findOrderByIdAndCode(req);
        Payload<SaleOrPinkOrderResponseDTO> payload = new Payload<>(dto);
        return payload;
    }

    @Override
    public Payload<PageBean<OrderOperationRecordResponseDTO>> getOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) throws Exception {
        return new Payload<>(saleOrderOperationRecordService.getOrderOperationRecord(orderOperationRecordRequestDTO));
    }
}
