package com.deepexi.dd.system.mall.controller;

import com.deepexi.dd.domain.common.domain.dto.CommonPaymentMallSettingResponseDTO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.tool.domain.dto.ToolPaySettingResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.finance.*;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflineCollectionInfoDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.SalePickOrderPayRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoReceiptRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.domain.transaction.enums.TicketTypeEnum;
import com.deepexi.dd.system.mall.domain.dto.*;
import com.deepexi.dd.system.mall.domain.dto.order.plan.SaleOrderInfoPlanAddRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.GetPaySettingRequestDTO;
import com.deepexi.dd.system.mall.domain.query.*;
import com.deepexi.dd.system.mall.domain.query.saleorder.DefualtOrderRequestQuery;
import com.deepexi.dd.system.mall.domain.query.saleorder.PlanCommodityRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.saleorder.DefualtOrderResponseVO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.ShopPlanItemDTO;
import com.deepexi.dd.system.mall.remote.FinanceCreditRemote;
import com.deepexi.dd.system.mall.remote.order.OrderRefundReasonRemote;
import com.deepexi.dd.system.mall.remote.order.SalePickGoodsInfoRemote;
import com.deepexi.dd.system.mall.service.SaleOrderInfoService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-03 10:54
 */
@RestController
@Api(tags = "销售订单接口")
@RequestMapping("/admin-api/v1/domain/transaction/saleOrderInfo")
public class SaleOrderInfoController {
    Logger logger = LoggerFactory.getLogger(SaleOrderInfoController.class);
    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    @Autowired
    private FinanceCreditRemote financeCreditRemote;
    @Autowired
    private SalePickGoodsInfoRemote salePickPaySaleOrder;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private OrderRefundReasonRemote orderRefundReasonRemote;

    @ApiOperation("分页查询销售订单表列表")
    @GetMapping("/page")
    public Payload<PageBean<SaleOrderInfoAppResponseDTO>> listSaleOrderInfosPage(@Valid SaleOrderInfoAppRequestQuery query) {

        SaleOrderInfoRequestQuery model = query.clone(SaleOrderInfoRequestQuery.class);
        try {
            PageBean<SaleOrderInfoResponseDTO> result = saleOrderInfoService.listSaleOrderInfosPage(model);
            return new Payload<>(GeneralConvertUtils.convert2PageBean(result, SaleOrderInfoAppResponseDTO.class));
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("分页查询销售订单表列表（在线订购 app）")
    @GetMapping("/oLpage")
    public Payload<PageBean<SaleOrderInfoOLResponseDTO>> listSaleOrdersPage(@Valid SaleOrderInfoRequestQuery query) {
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        try {
            PageBean<SaleOrderInfoOLResponseDTO> pageBean =
                    GeneralConvertUtils.convert2PageBean(saleOrderInfoService.listSaleOrderInfosPageForApi(model),
                            SaleOrderInfoOLResponseDTO.class);
            if (pageBean != null) {
                if (CollectionUtil.isNotEmpty(pageBean.getContent())) {

                    //退款临时方案
                    List<String> orderCodes =
                            pageBean.getContent().stream().map(SaleOrderInfoOLResponseDTO::getCode).collect(Collectors.toList());

                    Payload<List<RefundReasonFindResponseDTO>> refundReasonsPayload =
                            orderRefundReasonRemote.findOrderRefundReason(orderCodes);

                    List<RefundReasonFindResponseDTO> refundReasons =
                            GeneralConvertUtils.convert2List(refundReasonsPayload.getPayload(),
                                    RefundReasonFindResponseDTO.class);

                    for (SaleOrderInfoOLResponseDTO dto : pageBean.getContent()) {
                        if (CollectionUtil.isNotEmpty(dto.getItems())) {
                            for (SaleOrderItemResponseDTO item : dto.getItems()) {
                                item.setOrgId(dto.getSellerId());
                            }
                        }
                        List<Long> subOrderId = new ArrayList<>();
                        subOrderId.add(dto.getId());
                        dto.setSubOrderId(subOrderId);
                        dto.setSubOrderCode(dto.getCode());

                        Optional<RefundReasonFindResponseDTO> refundReason =
                                refundReasons.stream().filter(f -> dto.getCode().equals(f.getOrderCode())).findAny();

                        if (refundReason.isPresent()) {
                            dto.setHasRefund(true);
                        } else {
                            dto.setHasRefund(false);
                        }
                    }
                }
            }
            return new Payload<>(pageBean);
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("分页查询销售订单表列表 按单收款")
    @GetMapping("/receiptPage")
    public Payload<PageBean<SaleOrderInfoReceiptResponseDTO>> listSaleOrderInfosPageForReceipt(@Valid SaleOrderInfoReceiptRequestQuery query) {
        try {
            PageBean<SaleOrderInfoReceiptResponseDTO> result =
                    saleOrderInfoService.listSaleOrderInfosPageForReceipt(query);
            return new Payload<>(GeneralConvertUtils.convert2PageBean(result, SaleOrderInfoReceiptResponseDTO.class));
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("支付(中台预下单)")
    @PostMapping("/pay")
    public Payload<SaleOrderPayResponseDTO> paySaleOrder(@RequestBody @Valid SaleOrderPayRequestDTO saleOrderPayRequestDTO) {


        try {
            switch (saleOrderPayRequestDTO.getOrderType()) {
                case (0):
                    return new Payload<>(saleOrderInfoService.paySaleOrder(saleOrderPayRequestDTO));
                case (1):
                    return salePickPaySaleOrder.salePickPaySaleOrder(saleOrderPayRequestDTO.clone(SalePickOrderPayRequestDTO.class));
                case (2):
                    return new Payload<>(saleOrderInfoService.financeCreditPaySaleOrder(saleOrderPayRequestDTO.clone(SalePickOrderPayRequestDTO.class)));
            }

        } catch (Exception e) {
            logger.error("提货单支付失败:", e);
            throw new ApplicationException("提货单支付失败");
        }


        return new Payload<>();
    }

    @ApiOperation("批量删除销售订单表")
    @DeleteMapping("")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) {
        return new Payload<Boolean>();
    }

    @ApiOperation("新增订货计划单")
    @PostMapping("/plan")
    public Payload<SaleOrderInfoAppAddResponseDTO> planInsert(@RequestBody @Valid SaleOrderInfoPlanAddRequestDTO record) throws Exception {
        if (null == com.deepexi.dd.domain.transaction.enums.BuyerTypeEnum.getBuyerTypeEnum(record.getBuyerType())) {
            return new Payload(new SaleOrderInfoAddResponseDTO(), "60014", "下单类型错误");
        }
        if (CollectionUtil.isEmpty(record.getItems())) {
            throw new ApplicationException("60015", "未选择任何商品");
        }
        record.getItems().forEach(item -> {
            item.setOrgId(record.getSellerId());
        });
        SaleOrderInfoAppAddResponseDTO reponseDTO =
                saleOrderInfoService.createOrder(record.clone(SaleOrderInfoAppAddRequestDTO.class));
        reponseDTO.setCommodityType(record.getItems().size());
        reponseDTO.setCommodityNum(record.getItems().stream().mapToLong(a -> a.getSkuQuantity()).sum());
        return new Payload<>(reponseDTO);
    }

    @ApiOperation("新增销售单")
    @PostMapping("")
    public Payload<SaleOrderInfoAppAddResponseDTO> insert(@RequestBody @Valid SaleOrderInfoAppAddRequestDTO record) throws Exception {
        if (null == com.deepexi.dd.domain.transaction.enums.BuyerTypeEnum.getBuyerTypeEnum(record.getBuyerType())) {
            return new Payload(new SaleOrderInfoAddResponseDTO(), "60014", "下单类型错误");
        }
        if (CollectionUtil.isEmpty(record.getItems())) {
            throw new ApplicationException("60015", "未选择任何商品");
        }
        SaleOrderInfoAppAddResponseDTO reponseDTO = saleOrderInfoService.createOrder(record);
        return new Payload<>(reponseDTO);
    }

    @ApiOperation("根据ID查询销售订单表")
    @GetMapping("/{id}")
    public Payload<SaleOrderDetailAppResponseDTO> selectById(@PathVariable("id") Long id, @RequestParam(value =
            "appId") Long appId) {
        try {
            SaleOrderDetailAppResponseDTO saleOrderDetailAppResponseDTO =
                    GeneralConvertUtils.conv(saleOrderInfoService.selectById(id, appId),
                            SaleOrderDetailAppResponseDTO.class);

            if (saleOrderDetailAppResponseDTO != null) {
                if (CollectionUtil.isNotEmpty(saleOrderDetailAppResponseDTO.getItems())) {
                    for (SaleOrderItemAppResponseDTO item : saleOrderDetailAppResponseDTO.getItems()) {
                        item.setOrgId(saleOrderDetailAppResponseDTO.getSellerId());
                    }
                    List<Long> subOrderId = new ArrayList<>();
                    subOrderId.add(saleOrderDetailAppResponseDTO.getId());
                    saleOrderDetailAppResponseDTO.setSubOrderId(subOrderId);
                    saleOrderDetailAppResponseDTO.setSubOrderCode(saleOrderDetailAppResponseDTO.getCode());
                }
            }
            saleOrderDetailAppResponseDTO.setCommodityNum(saleOrderDetailAppResponseDTO.getItems().stream().mapToLong(a -> a.getSkuQuantity()).sum());
            return new Payload<>(saleOrderDetailAppResponseDTO);
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("查询分页订单明细信息")
    @GetMapping("/itemPage")
    public Payload<PageBean<SaleOrderItemAppResponseDTO>> listSaleOrderItemsPage(SaleOrderItemAppRequestQuery query) {
        try {
            PageBean<SaleOrderItemAppResponseDTO> pageBean = saleOrderInfoService.listSaleOrderItemsPage(query);
            return new Payload<>(pageBean);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }

    }

    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    public Payload<Boolean> cancelSaleOrder(@RequestBody @Valid SaleOrderCancelAppRequestDTO saleOrderCancelAppRequestDTO) {
        try {
            return new Payload<>(saleOrderInfoService.cancelSaleOrder(saleOrderCancelAppRequestDTO));
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("关闭订单")
    @PostMapping("/close")
    public Payload<Boolean> closeSaleOrder(@RequestBody @Valid SaleOrderCloseAppRequestDTO saleOrderCloseAppRequestDTO) {
        try {
            return new Payload<>(saleOrderInfoService.closeSaleOrder(saleOrderCloseAppRequestDTO));
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("接单")
    @PostMapping("/orderReceiving")
    public Payload<Boolean> orderReceiving(@RequestBody @Valid SaleOrderReceivingAppRequestDTO saleOrderReceivingAppRequestDTO) {
        try {
            return new Payload<>(saleOrderInfoService.orderReceiving(saleOrderReceivingAppRequestDTO));
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("支付回调")
    @PostMapping("/payCallback")
    public Payload<Boolean> payCallBack(@RequestBody @Valid PayCallbackRequestDTO payCallbackRequestDTO) {
        try {
            return new Payload<>(saleOrderInfoService.onlinePayCallBack(payCallbackRequestDTO));
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("订单驳回")
    @PostMapping("/orderRejected")
    public Payload<Boolean> orderRejected(@RequestBody @Valid SaleOrderRejectedRequestDTO dto) throws Exception {
        return new Payload<>(saleOrderInfoService.orderRejected(dto));
    }

    @ApiOperation("线下付款的付款信息")
    @GetMapping("/offlinePayInfo")
    public Payload<OfflinePaymentInfoResponseDTO> offlinePayInfo(@Valid OfflinePaymentInfoRequestQuery query) throws Exception {

        return new Payload<>(saleOrderInfoService.offlinePayInfo(query));
    }

    @ApiOperation("线下付款的收款信息")
    @GetMapping("/offlineCollectionInfo")
    public Payload<OfflineCollectionInfoResponseDTO> offlineCollectionInfo(@Valid OfflinePaymentInfoRequestQuery query) throws Exception {
        return new Payload<>(saleOrderInfoService.offlineCollectionInfo(query));
    }

    @ApiOperation("线下支付")
    @PostMapping("/saveOfflinePayInfo")
    public Payload<Boolean> saveOfflinePayInfo(@RequestBody @Valid OfflinePaymentInfoRequestDTO dto) throws Exception {
        if (dto.getOrderType().equals(0) && dto.getOrderType().equals(1)) {
            throw new ApplicationException("订单类型错误");
        }
        if (dto.getCollectMoneyInfo() == null || (dto.getCollectMoneyInfo() != null && (StringUtil.isEmpty(dto.getCollectMoneyInfo().getBankAccount()) || StringUtil.isEmpty(dto.getCollectMoneyInfo().getName())))) {
            throw new ApplicationException("收款信息为空");
        }
        if (dto.getPayInfo() == null || (dto.getPayInfo() != null && (StringUtil.isEmpty(dto.getPayInfo().getBankAccount()) || StringUtil.isEmpty(dto.getPayInfo().getName())))) {
            throw new ApplicationException("付款信息为空");
        }
        //如果是提货单
        if (dto.getOrderType().equals(1)) {
            OfflinePayInfoRequestDTO payInfoRequestDTO = dto.clone(OfflinePayInfoRequestDTO.class);
            if (!ObjectUtils.isEmpty(dto.getCollectMoneyInfo())) {
                payInfoRequestDTO.setCollectMoneyInfo(dto.getCollectMoneyInfo().clone(OfflineCollectionInfoDTO.class));
            }
            if (!ObjectUtils.isEmpty(dto.getPayInfo())) {
                payInfoRequestDTO.setPayInfo(dto.getPayInfo().clone(OfflinePayInfoDTO.class));
            }
            logger.info("提货单线下付款请求信息:{}", JsonUtil.bean2JsonString(payInfoRequestDTO));
            return salePickPaySaleOrder.saveSalePickOfflinePayInfo(payInfoRequestDTO);
        } else if (dto.getOrderType().equals(0)) {
            logger.info("普通单线下付款请求信息:{}", JsonUtil.bean2JsonString(dto));
            return new Payload<>(saleOrderInfoService.saveOfflinePayInfo(dto));
        } else if (dto.getOrderType().equals(2)) {
            logger.info("付款单线下付款请求信息:{}", JsonUtil.bean2JsonString(dto));
            return new Payload<>(saleOrderInfoService.saveRepaymentOfflinePayInfo(dto));
        }
        return new Payload<>(false);
    }

    @ApiOperation("余额支付")
    @PostMapping("/financeAmount")
    public Payload<Boolean> financeAmount(@RequestBody @Valid FinanceAmountPayRequestDTO dto) throws Exception {
        FinanceAmountPaymentReqDTO financeAmountPaymentReqDTO = new FinanceAmountPaymentReqDTO();
        FinanceAmountDetailRequestDTO detailRequestDTO = dto.clone(FinanceAmountDetailRequestDTO.class);
        financeAmountPaymentReqDTO.setFinanceAmountDetailRequestDTO(detailRequestDTO);
        return financeCreditRemote.financeAmount(financeAmountPaymentReqDTO);
    }

    @ApiOperation("获取确认订单的订单信息")
    @GetMapping("ascertainOrderInfo")
    public Payload<SaleOrderAscertainInfoResponseDTO> ascertainOrderInfo(@Valid SaleOrderAscertainInfoRequestQuery query) throws Exception {
        return new Payload<>(saleOrderInfoService.ascertainOrderInfo(query));
    }

    @ApiOperation("根据ID更新销售订单表")
    @PutMapping("/{id}")
    public Payload<Boolean> updateById(@PathVariable("id") Long id,
                                       @RequestBody @Valid SaleOrderInfoEditRequestDTO record) throws Exception {
        saleOrderInfoService.updateById(id, record);
        return new Payload<>(true);
    }

    @ApiOperation("根据ID更新订货计划订单表")
    @PutMapping("/plan/{id}")
    public Payload<SaleOrderInfoResponseDTO> updatePlanById(@PathVariable("id") Long id,
                                                            @RequestBody @Valid SaleOrderInfoPlanAddRequestDTO record) throws Exception {
        return new Payload<>(saleOrderInfoService.updatePlanById(id, record));
    }

    @ApiOperation("支付完成后的支付信息")
    @GetMapping("/paymentCompletedInfo/{id}/{code}")
    public Payload<PaymentCompletedInfoResponseDTO> paymentCompletedInfo(@PathVariable("id") Long saleOrderId,
                                                                         @PathVariable("code") String code) throws Exception {
        return new Payload<>(saleOrderInfoService.paymentCompletedInfo(saleOrderId, code));
    }

    @ApiOperation("获取订单的基本信息:发票信息,收货信息,单据信息")
    @GetMapping("/getDefualtOrderInfo")
    public Payload<DefualtOrderResponseVO> getDefualtOrderInfo(@Valid DefualtOrderRequestQuery requestQuery) throws Exception {
        return new Payload<>(saleOrderInfoService.getDefualtOrderInfo(requestQuery));
    }

    @ApiOperation("发货")
    @PostMapping("/deliveryGoodsOrder")
    public Payload<Boolean> deliveryGoodsOrder(@RequestBody @Valid SaleOrderInfoDeliverGoodsInfoRequestDTO dto) throws Exception {
        return new Payload<>(saleOrderInfoService.deliveryGoodsOrder(dto));
    }

    @ApiOperation("订货计划选择商品接口")
    @PostMapping("/plan/commoduty/page")
    public Payload<PageBean<ShopPlanItemDTO>> planCommodutyPage(@RequestBody @Valid PlanCommodityRequestQuery planCommodityRequestQuery) throws Exception {
        PageBean<ShopPlanItemDTO> pageBean = saleOrderInfoService.planCommodutyPage(planCommodityRequestQuery);
        pageBean.setSize(planCommodityRequestQuery.getSize());
        if (pageBean.getNumber() == 0) {
            pageBean.setNumber(1);
        }
        return new Payload<>(pageBean);
    }

    @ApiOperation("删除订货计划订单")
    @DeleteMapping("/plan/delete")
    public Payload<Boolean> planDelete(@RequestBody @Valid SaleOrderCancelAppRequestDTO saleOrderCancelAppRequestDTO) {
        try {
            return new Payload<>(saleOrderInfoService.deletePlanByIdIn(saleOrderCancelAppRequestDTO.getId()));
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    @ApiOperation("线上订单支付(中台预下单)【提货单】")
    @PostMapping("/salePickPay")
    public Payload<SaleOrderPayResponseDTO> salePrderPay(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        try {
            return salePickPaySaleOrder.salePickPaySaleOrder(saleOrderPayRequestDTO);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("线下支付【提货单】")
    @PostMapping("/saveSalePickOfflinePayInfo")
    public Payload<Boolean> saveSalePickOfflinePayInfo(@RequestBody @Valid OfflinePayInfoRequestDTO dto) throws Exception {
        try {
            return salePickPaySaleOrder.saveSalePickOfflinePayInfo(dto);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("查询账户信用额度")
    @PostMapping("/findFinanceCredit")
    public Payload<FinanceCreditResponseDTO> findCredit(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        try {
            return financeCreditRemote.queryCreditBySalePickOrderId(saleOrderPayRequestDTO);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("查询账户余额")
    @PostMapping("/findFinanceAmount")
    public Payload<FinanceAmountResponseDTO> findFinanceAmount(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        try {
            return financeCreditRemote.queryAmountBySalePickOrderId(saleOrderPayRequestDTO);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("扣减账户信用额度，生成信用明细")
    @PostMapping("/deductionFinanceCredit")
    public Payload<Boolean> creditPayment(@RequestBody FinanceCreditPaymentRequestDTO dto) throws Exception {
        try {
            dto.setUserName(appRuntimeEnv.getUsername());
            dto.setAppId(appRuntimeEnv.getAppId());
            dto.setTenantId(appRuntimeEnv.getTenantId());
            dto.setUserId(appRuntimeEnv.getUserId());
            dto.setType(3);
            return financeCreditRemote.creditPayment(dto);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("支付成功页面【提货单】")
    @GetMapping("/salePickPaymentCompleted/{id}")
    public Payload<PaymentCompletedResponseDTO> paymentSalePickInfo(@PathVariable("id") Long salePickId) throws Exception {
        try {
            return salePickPaySaleOrder.paymentSalePickInfo(salePickId);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    //@ApiOperation("获取线下支付时，是否需要 上传支付凭证")
    //@GetMapping("/getOfflinePayUploadPaySetting")
    public Payload<Boolean> getOfflinePayUploadPaySetting(@RequestBody GetPaySettingRequestDTO requestDTO) throws Exception {
        try {
            return saleOrderInfoService.getOfflinePayUploadPaySetting(requestDTO);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("获取业务链路的支付设置")
    @GetMapping("/getPaySetting")
    public Payload<ToolPaySettingResponseDTO> getToolLinkPaySetting(GetPaySettingRequestDTO requestDTO) throws Exception {
        try {
            return saleOrderInfoService.getToolLinkPaySetting(requestDTO);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("获取商城设置的支付配置")
    @GetMapping("/getCommonPaymentMallSetting")
    public Payload<List<CommonPaymentMallSettingResponseDTO>> getCommonPaymentMallSetting(GetPaySettingRequestDTO requestDTO) throws Exception {
        try {
            return saleOrderInfoService.getCommonPaymentMallSetting(requestDTO);
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("根据Id和Code查找销售订单或提货计划订单")
    @PostMapping("/findOrderByIdAndCode")
    public Payload<SaleOrPinkOrderResponseDTO> findOrderByIdAndCode(@RequestBody SaleOrPinkOrderRequestDTO req) throws Exception {
        return new Payload<>(saleOrderInfoService.findOrderByIdAndCode(req));
    }
}
