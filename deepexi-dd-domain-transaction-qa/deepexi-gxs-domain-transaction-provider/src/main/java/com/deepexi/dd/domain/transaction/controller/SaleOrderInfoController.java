package com.deepexi.dd.domain.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.deepexi.clientiam.model.GetUserIdGroupResultVO;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.add.SaleOrderItemAddRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflineCollectionResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.OfflinePayInfoRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoReceiptRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.domain.transaction.enums.BuyerTypeEnum;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.export.PlanSaleOrderExportService;
import com.deepexi.dd.domain.transaction.export.SaleOrderExportService;
import com.deepexi.dd.domain.transaction.export.SaleOrderReportService;
import com.deepexi.dd.domain.transaction.remote.order.*;
import com.deepexi.dd.domain.transaction.service.*;
import com.deepexi.dd.domain.transaction.service.impl.send.SaleOrderSendServiceImpl;
import com.deepexi.dd.domain.transaction.util.SaleOrderInfoDtoHelper;
import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditPaymentRequestDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import domain.dto.BusinessPartnerResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : SalesOrderController
 * @Description : 销售订单
 * @Author : yuanzaishun
 * @Date: 2020-06-23 11:47
 */
@RestController
@Api(tags = "销售订单接口")
@RequestMapping("/admin-api/v1/domain/transaction/saleOrderInfo")
public class SaleOrderInfoController {
    Logger logger = LoggerFactory.getLogger(SaleOrderInfoController.class);
    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    @Autowired
    SaleOrderOperationService saleOrderOperationService;
    @Autowired
    private IamUserService iamUserService;
    @Autowired
    private SaleOrderExportService saleOrderExportService;
    @Autowired
    private SaleOrderReportService saleOrderReportService;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private SaleOrderOperationRecordService saleOrderOperationRecordService;
    @Autowired
    private FinanceCreditService financeCreditService;
    @Autowired
    ToolCommoditytypeAuthorizeClient toolCommoditytypeAuthorizeClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Value("${appId}")
    private Long appId;

    @Autowired
    BusinessPartnerClient businessPartnerClient;
    @Autowired
    SaleOrderSendServiceImpl saleOrderSendService;
    @Autowired
    FinancePaymentRecordClient financePaymentRecordClient;
    @Autowired
    SalePickGoodsOrderSkuClient salePickGoodsOrderSkuClient;
    @Autowired
    OrderOperationRecordClient orderOperationRecordClient;

    @Autowired
    PlanSaleOrderExportService planSaleOrderExportService;


    @ApiOperation("查询销售订单表列表")
    @GetMapping("/list")
    public Payload<SaleOrderInfoResponseDTO> listSaleOrderInfos(@RequestBody @Valid SaleOrderInfoRequestQuery query) {

        return new Payload();
    }

    @ApiOperation("分页查询销售订单表列表（报表）")
    @GetMapping("/pageReport")
    public Payload<PageBean<SaleOrderBtnResponseDTO>> listSaleOrderInfosPageReport(@Valid SaleOrderInfoRequestQuery query) throws Exception {
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        model.setListType("SalesOrder");
        PageBean<SaleOrderBtnResponseDTO> pageBean =
                GeneralConvertUtils.convert2PageBean(saleOrderInfoService.listSaleOrderInfosPage(model),
                        SaleOrderBtnResponseDTO.class);
        //packOperationButtons(pageBean);

        List<SaleOrderBtnResponseDTO> content = pageBean.getContent();

        //取所有网批订单Id
        List<Long> WPSaleOrderIds =
                content.stream().filter(f -> f.getTicketType() == 0).map(SaleOrderBtnResponseDTO::getId).collect(Collectors.toList());

        //网批订单payOrderCode
        List<String> WPPayOrderCodes = content.stream().filter(f -> f.getTicketType() == 0&& StringUtil.isNotEmpty(f.getPayOrderCode())).map(SaleOrderBtnResponseDTO::getPayOrderCode).collect(Collectors.toList());

        //网批订单id对应payOrderCode
        Map<Long,String> WPOrderIdPayCodeMap = content.stream().filter(f -> f.getTicketType() == 0&& StringUtil.isNotEmpty(f.getPayOrderCode())).collect(Collectors.toMap(SaleOrderBtnResponseDTO::getId,SaleOrderBtnResponseDTO::getPayOrderCode));

        Map<Long, BigDecimal> WPOrderMapResult = new HashMap<>();

        if (CollectionUtil.isNotEmpty(WPPayOrderCodes)&&CollectionUtil.isNotEmpty(WPSaleOrderIds)) {
            FinancePaymentRecordsRequestQuery financePaymentRecordsRequestQuery = new FinancePaymentRecordsRequestQuery();
            financePaymentRecordsRequestQuery.setOrderCodeList(WPPayOrderCodes);
            //批量查出网批订单的支付记录
            List<FinancePaymentRecordsResponseDTO> financePaymentRecordsWPResponseDTOS =
                    GeneralConvertUtils.convert2List(financePaymentRecordClient.byOrderCodeList(financePaymentRecordsRequestQuery).getPayload(), FinancePaymentRecordsResponseDTO.class);
            //拿出状态是已确认的
            List<FinancePaymentRecordsResponseDTO> WPRecordList =
                    financePaymentRecordsWPResponseDTOS.stream().filter(f -> f.getStatus() == 25&&f.getOrderType()==0).collect(Collectors.toList());

            //orderPayCode对应支付金额
            Map<String, BigDecimal> WPOrderMap = new HashMap<>();
            WPRecordList.forEach(i -> {
                if (!WPOrderMap.containsKey(i.getOrderCode())) {
                    WPOrderMap.put(i.getOrderCode(), i.getAmount());
                } else {
                    WPOrderMap.put(i.getOrderCode(), WPOrderMap.get(i.getOrderCode()).add(i.getAmount()));
                }
            });

            WPSaleOrderIds.forEach(i -> {

                if(WPOrderIdPayCodeMap.get(i)!=null){
                    WPOrderMapResult.put(i, WPOrderMap.get(WPOrderIdPayCodeMap.get(i)) != null ? WPOrderMap.get(WPOrderIdPayCodeMap.get(i)) : new BigDecimal(0));
                }
                else{
                    WPOrderMapResult.put(i,new BigDecimal(0));
                }
            });
        }

        //取所有标准订单Id
        List<Long> BZSaleOrderIds =
                content.stream().filter(f -> f.getTicketType() == 1).map(SaleOrderBtnResponseDTO::getId).collect(Collectors.toList());

        //订单id对应订单明细
        Map<Long,List<SaleOrderItemResponseDTO>> orderItemMap = content.stream().filter(f -> f.getTicketType() == 1&&CollectionUtil.isNotEmpty(f.getItems())).collect(Collectors.toMap
                (SaleOrderBtnResponseDTO::getId,SaleOrderBtnResponseDTO::getItems));

        Map<Long, BigDecimal> paymentMapResult = new HashMap<>();

        if (CollectionUtil.isNotEmpty(BZSaleOrderIds)) {
            //取标准订单的所有明细id
            List<Long> ids =
                    content.stream().filter(f -> f.getTicketType() == 1 && CollectionUtil.isNotEmpty(f.getItems())).flatMap(m -> m.getItems().stream()).map(SaleOrderItemResponseDTO::getId).collect(Collectors.toList());

            //所有skuId 对应 订单Id
            Map<Long,Long> skuMainOrderIdMap = content.stream().filter(f -> f.getTicketType() == 1 && CollectionUtil.isNotEmpty(f.getItems())).flatMap(m -> m.getItems().stream()).collect(Collectors.toMap(SaleOrderItemResponseDTO::getId,SaleOrderItemResponseDTO::getSaleOrderId));

            if (CollectionUtil.isNotEmpty(ids)) {
                //批量查提货计划
                SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery = new SalePickGoodsOrderSkuRequestQuery();
                salePickGoodsOrderSkuRequestQuery.setSaleOrderItemIdList(ids);
                List<SalePickGoodsOrderSkuResponseDTO> salePickGoodsOrderSkuResponseDTOS = salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkus(salePickGoodsOrderSkuRequestQuery);
                if(CollectionUtil.isNotEmpty(salePickGoodsOrderSkuResponseDTOS)){
                    List<Long> pickGoodInfoIds = salePickGoodsOrderSkuResponseDTOS.stream().map(SalePickGoodsOrderSkuResponseDTO::getPickGoodsInfoId).collect(Collectors.toList());

                    //批量查出标准订单的支付记录
                    List<FinancePaymentRecordsResponseDTO> financePaymentRecordsBZResponseDTOS =
                            GeneralConvertUtils.convert2List(financePaymentRecordClient.listFinancePaymentRecordsByOrderIds(pickGoodInfoIds).getPayload(), FinancePaymentRecordsResponseDTO.class);

                    if (CollectionUtil.isNotEmpty(financePaymentRecordsBZResponseDTOS)) {
                        //拿出状态是已确认的而且orderType是提货计划的
                        List<FinancePaymentRecordsResponseDTO> BZRecordList =
                                financePaymentRecordsBZResponseDTOS.stream().filter(f -> f.getOrderType() == 1).filter(f -> f.getStatus() == 25).collect(Collectors.toList());
                        if (CollectionUtil.isNotEmpty(BZRecordList)) {
                            //拿到所有状态已确认的提货计划Id
                            List<Long> confirmedPickIds = BZRecordList.stream().map(FinancePaymentRecordsResponseDTO::getOrderId).collect(Collectors.toList());

                            //拿到所有状态已确认的提货计划
                            List<SalePickGoodsOrderSkuResponseDTO> confirmedPickGoodsInfos = salePickGoodsOrderSkuResponseDTOS.stream().filter(f->confirmedPickIds.contains(f.getPickGoodsInfoId())).collect(Collectors.toList());

                            //itemid 对应 提货计划list
                            Map<Long,List<SalePickGoodsOrderSkuResponseDTO>> pickMap = confirmedPickGoodsInfos.stream().collect(Collectors.groupingBy(SalePickGoodsOrderSkuResponseDTO::getSaleOrderItemId));

                            //提货计划id对应金额
                            Map<Long,List<FinancePaymentRecordsResponseDTO>> recordMap = BZRecordList.stream().collect(Collectors.groupingBy(FinancePaymentRecordsResponseDTO::getOrderId));

                            //每个sku对应的已确认的金额  提货计划的提货数 x 价格
//                        Map<Long,BigDecimal> skuAmount = new HashMap<>();
//                        confirmedPickGoodsInfos.forEach(i->{
//
//
//
////                            if(!skuAmount.containsKey(i)){
////                                skuAmount.put(i.getSkuId(),new BigDecimal(i.getPickNum()).multiply(i.getPrice()));
////                            }
////                            else {
////                                skuAmount.put(i.getSkuId(),new BigDecimal(i.getPickNum()).multiply(i.getPrice()).add(skuAmount.get(i.getSkuId())));
////                            }
//                        });

                            orderItemMap.forEach((k,v)->{
                                BigDecimal amount = new BigDecimal(0);
                                for(SaleOrderItemResponseDTO saleOrderItemResponseDTO : orderItemMap.get(k)){
                                    List<SalePickGoodsOrderSkuResponseDTO> list = pickMap.get(saleOrderItemResponseDTO.getId());
                                    if(CollectionUtil.isNotEmpty(list)){
                                        for(SalePickGoodsOrderSkuResponseDTO salePickGoodsOrderSkuResponseDTO : pickMap.get(saleOrderItemResponseDTO.getId())){
                                            amount = amount.add(new BigDecimal(salePickGoodsOrderSkuResponseDTO.getPickNum()).multiply(salePickGoodsOrderSkuResponseDTO.getPrice()));
                                        }
                                    }
                                }
                                paymentMapResult.put(k,amount);
                            });
                        } else {
                            BZSaleOrderIds.forEach(i -> {
                                paymentMapResult.put(i, new BigDecimal(0));
                            });
                        }
                    }
                }
            }
                }
                //提货计划id对应提货计划
//                Map<Long,SalePickGoodsOrderSkuResponseDTO> pickMap = salePickGoodsOrderSkuResponseDTOS.stream().collect
//                        (Collectors.toMap(SalePickGoodsOrderSkuResponseDTO::getPickGoodsInfoId,e->e));

        content.forEach(i -> {
            if (i.getTicketType() == 0) {
                i.setPayAmount(WPOrderMapResult.get(i.getId()) != null ? WPOrderMapResult.get(i.getId()) :
                        new BigDecimal(0));
            }
            if (i.getTicketType() == 1) {
                i.setPayAmount(paymentMapResult.get(i.getId()) != null ? paymentMapResult.get(i.getId()) :
                        new BigDecimal(0));
            }
        });


        return new Payload<>(pageBean);
    }

    @ApiOperation("分页查询销售订单表列表")
    @GetMapping("/page")
    public Payload<PageBean<SaleOrderBtnResponseDTO>> listSaleOrderInfosPage(@Valid SaleOrderInfoRequestQuery query) throws Exception {
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        model.setListType("SalesOrder");
        PageBean<SaleOrderBtnResponseDTO> pageBean =
                GeneralConvertUtils.convert2PageBean(saleOrderInfoService.listSaleOrderInfosPage(model),
                        SaleOrderBtnResponseDTO.class);
        //packOperationButtons(pageBean);
        return new Payload<>(pageBean);
    }

    @ApiOperation("分页查询销售订单表列表（在线订购）")
    @GetMapping("/oLpage")
    public Payload<PageBean<SaleOrderInfoOLResponseDTO>> listSaleOrdersPage(@Valid SaleOrderInfoRequestQuery query) throws Exception {
        logger.info("销售订单列表接口参数：" + JSON.toJSONString(query));
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        PageBean<SaleOrderBtnResponseDTO> result = saleOrderInfoService.listSaleOrderInfosPageForApi(model);

        for (SaleOrderBtnResponseDTO saleOrderInfoOLResponseDTO : result.getContent()) {
            if (saleOrderInfoOLResponseDTO.getSaleOutTaskList() != null) {
                saleOrderInfoOLResponseDTO.setSaleOutTaskAmount(saleOrderInfoOLResponseDTO.getSaleOutTaskList().size());
            } else {
                saleOrderInfoOLResponseDTO.setSaleOutTaskAmount(0);
            }
        }

        PageBean<SaleOrderInfoOLResponseDTO> pageBean = GeneralConvertUtils.convert2PageBean(result,
                SaleOrderInfoOLResponseDTO.class);

        return new Payload<>(pageBean);
    }

    @ApiOperation("按单收款分页查询销售订单表列表")
    @GetMapping("/receiptPage")
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

    @ApiOperation("批量删除销售订单表")
    @DeleteMapping("")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) {
        return new Payload<Boolean>(saleOrderInfoService.deleteByIdIn(id));
    }

    @ApiOperation("代客下单")
    @PostMapping("")
    public Payload<SaleOrderInfoAddResponseDTO> insert(@RequestBody @Valid SaleOrderInfoAgentRequestDTO record) throws Exception {
        if (null == BuyerTypeEnum.getBuyerTypeEnum(record.getBuyerType())) {
            throw new ApplicationException(ResultEnum.BUYTYPE_ERROR);
        }
        if (CollectionUtil.isEmpty(record.getItems())) {
            throw new ApplicationException(ResultEnum.COMMODITY_NULL_ERROR);
        }
        //TODO 验证订单类型
        SaleOrderInfoDTO saleOrder = SaleOrderInfoDtoHelper.convertToSaleOrderInfoDTO(record);
        saleOrder.setBuyerId(record.getBuyerId());
        saleOrder.setBuyerName(record.getBuyerName());

        //获取供应商信息
        GetUserIdGroupResultVO userIdGroupResultVO = iamUserService.getUserGroupInfo();
        GroupResultVO groupResultVO = userIdGroupResultVO.getGroupInfo().get(0);
        saleOrder.setSellerName(groupResultVO.getName());
        saleOrder.setSellerId(groupResultVO.getId());

        SaleOrderInfoDTO saleOrderInfoDTOResult = saleOrderInfoService.createOrder(saleOrder);

        return new Payload<>(saleOrderInfoDTOResult.clone(SaleOrderInfoAddResponseDTO.class));
    }

    @ApiOperation("根据ID查询销售订单表")
    @GetMapping("/{id}")
    public Payload<SaleOrderInfoResponseDTO> selectById(@PathVariable("id") Long id) {
        return new Payload<>(GeneralConvertUtils.conv(saleOrderInfoService.selectById(id),
                SaleOrderInfoResponseDTO.class));
    }

    @ApiOperation("根据ID查询销售订单表(只查订单表)")
    @GetMapping("/getSaleOrder/{id}")
    public Payload<SaleOrderInfoReceiptResponseDTO> selectSaleOrder(@PathVariable("id") Long id) {
        SaleOrderResponseDTO saleOrderResponseDTO = saleOrderInfoService.selectSaleOrder(id);

        SaleOrderInfoReceiptResponseDTO result = saleOrderResponseDTO.clone(SaleOrderInfoReceiptResponseDTO.class);

        result.setOrderNo(saleOrderResponseDTO.getCode());
        result.setAmount(saleOrderResponseDTO.getTotalAmount());
        result.setCustomerId(saleOrderResponseDTO.getBuyerId());
        result.setCustomerName(saleOrderResponseDTO.getBuyerName());
        result.setSettlementId(saleOrderResponseDTO.getBuyerId());
        result.setSettlementName(saleOrderResponseDTO.getBuyerName());
        result.setReceiptsTime(saleOrderResponseDTO.getTicketDate());
        result.setPaymentAmount(saleOrderResponseDTO.getPayAmount());

        return new Payload<>(result);
    }

    @ApiOperation("根据ID更新销售订单表")
    @PutMapping("/{id}")
    public Payload<SaleOrderInfoAddResponseDTO> updateById(@PathVariable("id") Long id,
                                                           @RequestBody @Valid SaleOrderInfoRequestDTO record) throws Exception {
        if (null == BuyerTypeEnum.getBuyerTypeEnum(record.getBuyerType())) {
            return new Payload(new SaleOrderInfoAddResponseDTO(), "1000002", "下单类型错误");
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
        //验证订单类型
        SaleOrderInfoDTO saleOrder = SaleOrderInfoDtoHelper.convertToSaleOrderInfoDTO(record);
        saleOrder.setBuyerId(appRuntimeEnv.getTopOrganization().getId());
        saleOrder.setBuyerName(appRuntimeEnv.getTopOrganization().getName());
        //获取供应商信息
        saleOrder.setSellerName(orgName);
        saleOrder.setSellerId(orgId);
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
        SaleOrderInfoDTO saleOrderInfoDTO = saleOrderInfoService.updateById(id, saleOrder);
        return new Payload<>(saleOrderInfoDTO.clone(SaleOrderInfoAddResponseDTO.class));
    }

    @ApiOperation("更新订单支付金额")
    @PostMapping("/updatePayAmount")
    public Payload<Boolean> updatePayAmount(@RequestBody @Valid SaleOrderInfoUpdatePayAmountRequestDTO record) {
        return new Payload<>(saleOrderInfoService.updatePayAmount(record));
    }

    @ApiOperation("更新订单状态")
    @PostMapping("/updateOrderStatus")
    public Payload<Boolean> updateOrderStatus(@RequestBody @Valid SaleOrderInfoStatusEditRequestDTO record) {
        return new Payload<>(saleOrderInfoService.updateOrderStatus(record));
    }

//    @ApiOperation("接单")
//    @PutMapping("/acceptance")
//    public Payload<Boolean> acceptSaleOrder(@RequestBody @Valid SaleOrderAcceptRequestDTO record){
//        return new Payload<>(saleOrderInfoService.acceptSaleOrder(record));
//    }

    @ApiOperation("发货")
    @PostMapping("/deliveryGoodsOrder")
    public Payload<Boolean> deliveryGoodsOrder(@RequestBody @Valid SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.deliveryGoodsOrder(saleOrderInfoDeliverGoodsRequestDTO));
    }

    @ApiOperation("取消")
    @PutMapping("/cancel")
    public Payload<Boolean> cancelSaleOrder(@RequestBody @Valid SaleOrderCancelRequestDTO saleOrderCancelRequestDTO) {
        return new Payload<>(saleOrderInfoService.cancelSaleOrder(saleOrderCancelRequestDTO));
    }

    @ApiOperation("关闭")
    @PutMapping("/close")
    public Payload<Boolean> closeSaleOrder(@RequestBody @Valid SaleOrderCloseRequestDTO saleOrderCloseRequestDTO) {
        return new Payload<>(saleOrderInfoService.closeSaleOrder(saleOrderCloseRequestDTO));
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

//    private void packOperationButtons(PageBean<SaleOrderInfoResponseDTO> pageBean){
//        if(null==pageBean||pageBean.getNumberOfElements()<=0){
//            return;
//        }
//
//        List<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOS = pageBean.getContent();
//
//        List<Integer> status =  saleOrderInfoResponseDTOS.stream().map(SaleOrderInfoResponseDTO::getStatus).collect
//        (Collectors.toList());
//
//        List<OrderStatusOperationDTO> orderStatusOperationDTOS = saleOrderOperationService
//        .listOrderStatusOperations(status,"CENTER");   //待确认。。 先默认protal都是center
//
//        for(SaleOrderInfoResponseDTO saleOrder : saleOrderInfoResponseDTOS){
//            List<OrderOperationDTO> operationDTOList = new ArrayList<>();
//            for(OrderStatusOperationDTO operationDTO : orderStatusOperationDTOS){
//                if(operationDTO.getStatusIdentity().equals(saleOrder.getStatus())){
//                    operationDTOList.add(operationDTO.getOperationDTO().clone(OrderOperationDTO.class));
//                }
//            }
//            saleOrder.setOrderOperationList(operationDTOList);
//        }
//
//    }

    @ApiOperation("接单")
    @PostMapping("/orderReceiving")
    public Payload<Boolean> orderReceiving(@RequestBody @Valid SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.orderReceiving(saleOrderReceivingRequestDTO));

    }

    @ApiOperation("订单驳回")
    @PostMapping("/orderRejected")
    public Payload<Boolean> orderRejected(@RequestBody @Valid SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.orderRejected(saleOrderRejectedRequestDTO));
    }

    @ApiOperation("审核")
    @PostMapping("/orderToExamine")
    public Payload<Boolean> orderToExamine(@RequestBody @Valid SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.orderToExamine(saleOrderReceivingRequestDTO));
    }

    @ApiOperation("审核驳回")
    @PostMapping("/orderRejectedToExamine")
    public Payload<Boolean> orderRejectedToExamine(@RequestBody @Valid SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.orderRejectedToExamine(saleOrderRejectedRequestDTO));
    }

    @ApiOperation("线下付款的付款信息")
    @GetMapping("/offlinePayInfo")
    public Payload<OfflinePayInfoResponseDTO> offlinePayInfo(@Valid OfflinePayInfoRequestQuery query) throws Exception {
        return new Payload<>(saleOrderInfoService.offlinePayInfo(query));
    }

    @ApiOperation("线下付款的收款信息")
    @GetMapping("/offlineCollectionInfo")
    public Payload<OfflineCollectionResponseDTO> offlineCollectionInfo(@Valid OfflinePayInfoRequestQuery query) throws Exception {
        return new Payload<>(saleOrderInfoService.offlineCollectionInfo(query));
    }

    @ApiOperation("线下支付")
    @PostMapping("/saveOfflinePayInfo")
    public Payload<Boolean> saveOfflinePayInfo(@RequestBody @Valid OfflinePayInfoRequestDTO dto) throws Exception {
        return new Payload<>(saleOrderInfoService.saveOfflinePayInfo(dto));
    }

    @GetMapping("/exportSaleOrder")
    @ApiOperation(value = "导出销售订单列表", nickname = "export")
    public void exportSaleOrder(SaleOrderInfoQuery query) throws Exception {
        saleOrderExportService.export(response, query);
    }

    @GetMapping("/exportSaleOrderReport")
    @ApiOperation(value = "报表导出销售订单列表", nickname = "exportReport")
    public void exportSaleOrderReport(SaleOrderInfoQuery query) throws Exception {
        saleOrderReportService.export(response, query);
    }

    @GetMapping("/getOrderOperationRecord")
    @ApiOperation("查询操作记录")
    public Payload<PageBean<OrderOperationRecordResponseDTO>> getOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        return new Payload<>(saleOrderOperationRecordService.getOrderOperationRecord(orderOperationRecordRequestDTO));
    }

    @GetMapping("/getOrderRejectOperationRecord")
    @ApiOperation("查询驳回操作记录")
    public Payload<OrderOperationRecordResponseDTO> getOrderRejectOperationRecord(OrderRejectOperationRecordRequestDTO orderRejectOperationRecordRequestDTO) {
        OrderOperationRecordRequestDTO orderOperationRecordRequestDTO =
                orderRejectOperationRecordRequestDTO.clone(OrderOperationRecordRequestDTO.class);
        return new Payload(saleOrderOperationRecordService.getOrderRejectOperationRecord(orderOperationRecordRequestDTO));
    }

    @ApiOperation("线上订单支付(中台预下单)【提货单】")
    @PostMapping("/salePickPay")
    public Payload<SaleOrderPayResponseDTO> salePrderPay(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        try {
            return new Payload<>(saleOrderInfoService.salePickPaySaleOrder(saleOrderPayRequestDTO));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("线下支付【提货单】")
    @PostMapping("/saveSalePickOfflinePayInfo")
    public Payload<Boolean> saveSalePickOfflinePayInfo(@RequestBody @Valid OfflinePayInfoRequestDTO dto) throws Exception {
        try {
            return new Payload<>(saleOrderInfoService.saveSalePickOfflinePayInfo(dto));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("查询账户信用额度")
    @PostMapping("/findFinanceCredit")
    public Payload<FinanceCreditResponseDTO> findCredit(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        try {
            return new Payload<>(financeCreditService.queryCreditBySalePickOrderId(saleOrderPayRequestDTO));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("查询账户余额")
    @PostMapping("/findFinanceAmount")
    public Payload<FinanceAmountResponseDTO> findFinanceAmount(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        try {
            return new Payload<>(financeCreditService.queryAmountBySalePickOrderId(saleOrderPayRequestDTO));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("扣减账户信用额度，生成信用明细")
    @PostMapping("/deductionFinanceCredit")
    public Payload<Boolean> creditPayment(@RequestBody FinanceCreditPaymentRequestDTO dto) throws Exception {
        try {
            return new Payload<>(financeCreditService.creditPayment(dto));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    /*  @ApiOperation("扣减账户余额，生成余额明细【提货单】")
      @PostMapping("/deductionFinanceAmount")
      public Payload<Boolean> creditPayment(@RequestBody FinanceCreditDetailRequestDTO dto) throws Exception {
        TODO 财务中心暂无接口...
      }*/

    @ApiOperation("支付成功页面【提货单】")
    @GetMapping("/salePickPaymentCompleted")
    public Payload<PaymentCompletedResponseDTO> paymentSalePickInfo(@PathVariable("id") Long salePickId) throws Exception {
        try {
            return new Payload<>(saleOrderInfoService.paymentSalePickInfo(salePickId));
        } catch (Exception e) {
            return new Payload(null, "500", e.getMessage());
        }
    }

    @ApiOperation("发送网批订单到oms系统")
    @GetMapping("/sendMqToOms/{id}")
    public Payload<Boolean> sendMqToOms(@PathVariable("id") Long id) throws Exception {
        saleOrderSendService.sendOrderToGl(id);
        return new Payload<>(true);

    }


    /**
     * 根据Id和Code查找销售订单或提货计划订单
     *
     * @throws Exception
     */
    @ApiOperation("根据Id和Code查找销售订单或提货计划订单")
    @PostMapping("/findOrderByIdAndCode")
    public Payload<SaleOrPinkOrderResponseDTO> findOrderByIdAndCode(@RequestBody SaleOrPinkOrderRequestDTO req) throws Exception {
        SaleOrPinkOrderResponseDTO dto = saleOrderInfoService.findOrderByIdAndCode(req);
        Payload<SaleOrPinkOrderResponseDTO> payload = new Payload<>(dto);
        return payload;
    }

    @ApiOperation("订货计划导出（商品维度）")
    @GetMapping("/export/planorder")
    public void exportPlanOrder(@Valid SaleOrderInfoRequestQuery query) throws Exception {
        planSaleOrderExportService.export(response, query);
    }

}
