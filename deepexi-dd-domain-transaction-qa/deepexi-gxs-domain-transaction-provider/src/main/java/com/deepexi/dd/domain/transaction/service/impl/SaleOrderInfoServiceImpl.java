package com.deepexi.dd.domain.transaction.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.deepexi.clientiam.api.UserControllerApi;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.domain.dto.OnlineActivitiesGoodResponseDTO;
import com.deepexi.dd.domain.common.domain.dto.OnlineActivitiesResponseDTO;
import com.deepexi.dd.domain.common.domain.query.OnlineActivitiesGoodQuery;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.common.util.OrganizationUtils;
import com.deepexi.dd.domain.tool.domain.dto.*;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusActionRequestQuery;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusRequestQuery;
import com.deepexi.dd.domain.tool.enums.ActionCodeEnum;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.domain.SaleOrPinkOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.ActivitySkuOrderQuantityDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderConsigneeInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoStatusEditRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickOrderPayRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceRepaymentNotifyDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.*;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo.SaleOrderAscertainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartCommodityDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartUserInfoDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.*;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.enums.TicketTypeEnum;
import com.deepexi.dd.domain.transaction.manager.CommodityManager;
import com.deepexi.dd.domain.transaction.manager.ToolActionExcutor;
import com.deepexi.dd.domain.transaction.mq.producter.MqMessageProducter;
import com.deepexi.dd.domain.transaction.mq.producter.MqProducter;
import com.deepexi.dd.domain.transaction.mq.producter.OrderMqMsg;
import com.deepexi.dd.domain.transaction.remote.common.OnlineActivitiesClient;
import com.deepexi.dd.domain.transaction.remote.order.*;
import com.deepexi.dd.domain.transaction.service.*;
import com.deepexi.dd.domain.transaction.service.common.ToolLinkService;
import com.deepexi.dd.domain.transaction.service.impl.send.SaleOrderSendServiceImpl;
import com.deepexi.dd.domain.transaction.util.SaleOrderInfoDtoHelper;
import com.deepexi.dd.domain.transaction.util.SaleOrderInfoUtils;
import com.deepexi.dd.middle.finance.api.FinanceBankAccountApi;
import com.deepexi.dd.middle.finance.api.FinanceCollectionApi;
import com.deepexi.dd.middle.finance.domain.dto.*;
import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRepaymentBillRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoOLResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.*;
import com.deepexi.dd.middle.tool.domain.dto.ToolAuthorizeCommodityResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolAuthorizeCommodityInfoQuery;
import com.deepexi.middle.pay.sdk.config.MerchantConfig;
import com.deepexi.middle.pay.sdk.domain.GetPayStatusRequest;
import com.deepexi.middle.pay.sdk.domain.GetPayStatusResponse;
import com.deepexi.middle.pay.sdk.domain.PrePlaceOrderRequest;
import com.deepexi.middle.pay.sdk.domain.PrePlaceOrderResponse;
import com.deepexi.middle.pay.sdk.exception.HttpClientException;
import com.deepexi.middle.pay.sdk.manager.impl.PayRequestManager;
import com.deepexi.middle.pay.sdk.manager.impl.PayRequestManagerFactory;
import com.deepexi.sdk.schedule.api.ScheduleDomainSdkApi;
import com.deepexi.sdk.schedule.model.*;
import com.deepexi.sdk.storage.api.DepotSdkApi;
import com.deepexi.sdk.storage.api.StockChangeSdkApi;
import com.deepexi.sdk.storage.api.StockSdkApi;
import com.deepexi.sdk.storage.model.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.DateUtils;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.MerchantResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import domain.query.MerchantRequestQuery;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * SaleOrderInfoServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
 */
@Service
@Slf4j
public class SaleOrderInfoServiceImpl implements SaleOrderInfoService {
    Logger logger = LoggerFactory.getLogger(SaleOrderInfoServiceImpl.class);
    private static final Integer SUBMIT = 1;
    private static final Integer DRAFT = 0;
    private static final String YYYYMMDD = "yyyyMMdd";

    private static final String TOPOIC = "DEEPEXI_DD";

    private static final String FINANCE_CREDIT_REPAYMENT = "FINANCE_CREDIT_REPAYMENT";

    @Autowired
    MqMessageProducter mqMessageProducter;
    @Value("${appId}")
    private Long appId;
    /**
     * 渠道号,当前只有一个渠道
     */

    @Autowired
    ToolAuthorizeClient toolAuthorizeApi;
    @Autowired
    ToolCommoditytypeAuthorizeClient toolCommoditytypeAuthorizeClient;

    private static final Long CHANNEL = 1L;

    private static final String SUCCESS = "0";
    @Autowired
    SaleOrderInfoClient saleOrderInfoClient;

    @Autowired
    SaleOrderItemClient saleOrderItemClient;

    @Autowired
    OrderConsigneeInfoClient orderConsigneeInfoClient;

    @Autowired
    OrderCouponInfoClient orderCouponInfoClient;

    @Autowired
    OrderExpenseInfoClient orderExpenseInfoClient;

    @Autowired
    OrderInvoiceInfoClient orderInvoiceInfoClient;

    @Autowired
    OrderPromotionInfoClient orderPromotionInfoClient;

    @Autowired
    SaleOutTaskClient saleOutTaskClient;

    @Autowired
    OrderDeliveryInfoClient orderDeliveryInfoClient;

    @Autowired
    IdentifierGenerator identifierGenerator;

    @Autowired
    private ToolLinkClient toolLinkClient;

    @Autowired
    SaleOrderPayNotifyClient saleOrderPayNotifyClient;

    @Autowired
    SaleOrderCollectionNotifyClient saleOrderCollectionNotifyClient;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    CommodityManager commodityManager;

    @Autowired
    IamUserService iamUserService;

    @Autowired
    ToolLinkService toolLinkService;

    @Autowired
    private ToolStatusClient toolStatusClient;

    @Autowired
    ScheduleDomainSdkApi scheduleDomainSdkApi;

    @Autowired
    FinancePayInfoClient financePayInfoClient;

    @Autowired
    FinancePaymentRecordClient financePaymentRecordClient;
    @Autowired
    FinanceCollectionClient financeCollectionClient;

    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    private DepotSdkApi depotSdkApi;

    @Autowired
    FinanceCollectionApi financeCollectionApi;

    @Autowired
    FinanceBankAccountApi financeBankAccountApi;
    @Autowired
    private FinanceCreditRepaymentBillClient financeCreditRepaymentBillClient;


    @Autowired
    private SaleOrderSplitRecordClient saleOrderSplitRecordClient;

    @Autowired
    private SaleOrderOperationRecordService saleOrderOperationRecordService;

    @Value("${expiretime}")
    private Integer addMinute;

    @Autowired
    BusinessPartnerClient businessPartnerClient;

    @Autowired
    MerchantClient merchantClient;
    @Resource
    private MqProducter mqProducter;

    @Value("${deepxi.realTimeStock.switch}")
    private boolean stockSwitch;

    @Autowired
    private StockSdkApi stockSdkApi;

    @Autowired
    private StockChangeSdkApi stockChangeSdkApi;

    @Autowired
    SaleOrderInfoUtils saleOrderInfoUtils;

    @Autowired
    SaleOrderSendServiceImpl saleOrderSendService;

    @Autowired
    private OnlineActivitiesClient onlineActivitiesClient;
    @Value("${deepexi.pay.middleHost}")
    private String middleHost;
    @Value("${deepexi.pay.clientIp}")
    private String clientIp;
    @Value("${deepexi.online.payment.host}")
    private String onlinePaymentHost;
    @Autowired
    private ToolActionExcutor<SaleOrderResponseDTO> toolActionExcutor;

    @Autowired
    OrderPayBackClient orderPayBackClient;

    @Autowired
    SalePickGoodsInfoClient salePickGoodsInfoClient;

    @Autowired
    SalePickGoodsOrderSkuClient salePickGoodsOrderSkuClient;
    @Autowired
    private ActivitySkuOrderQuantityClient activitySkuOrderQuantityClient;
    @Autowired
    OrderOperationRecordClient orderOperationRecordClient;

    @Autowired
    private RestTemplate restTemplate;

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);
    @Value("${deepexi.yuncang.url}")
    private String url;
    @Value("${deepexi.yuncang.appKey}")
    private String appKey;
    @Value("${deepexi.yuncang.appSecret}")
    private String appSecret;

    @Override
    public List<SaleOrderInfoResponseDTO> listSaleOrderInfos(SaleOrderInfoRequestQuery query) throws Exception {
        Payload<List<SaleOrderInfoResponseDTO>> saleOrderInfos = saleOrderInfoClient.listSaleOrderInfos(query);

        return GeneralConvertUtils.convert2List(saleOrderInfos.getPayload(), SaleOrderInfoResponseDTO.class);
    }

    @Autowired
    private UserControllerApi userControllerApi;

    @Autowired
    private FinanceBankAccountInfoClient financeBankAccountInfoClient;

    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;

    /**
     * 销售订单分页    listType:SalesOrder 运营中心   ListCollection 按单收款
     *
     * @param query
     * @return
     */
    @Override
    public PageBean<SaleOrderBtnResponseDTO> listSaleOrderInfosPage(SaleOrderInfoQuery query) throws Exception {
        PageMethod.startPage(query.getPage(), query.getSize(), "payment_status asc, ticket_date desc");
        //userId找到组织    组织=隔离

        SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = query.clone(SaleOrderInfoRequestQuery.class);
//        GetUserIdGroupResultVO groupResultVO = iamUserService.getUserGroupInfo();
//        if (CollectionUtil.isNotEmpty(groupResultVO.getGroupInfo())) {
//            String idPath = groupResultVO.getGroupInfo().get(0).getIdPath();
//            String[] idArray = idPath.split("\\/");
//            if (idArray.length > 2) {
//                String orgId = idArray[2];
////                saleOrderInfoRequestQuery.setSellerId(Long.valueOf(orgId));
//            }
//        } else {
//            throw new ApplicationException("查不到用户");
//        }

        if (appRuntimeEnv.getTopOrganization().getId().equals(appRuntimeEnv.getUserOrganization().getId())) {
            saleOrderInfoRequestQuery.setSellerId(appRuntimeEnv.getUserOrganization().getId());
        } else {
            saleOrderInfoRequestQuery.setAscriptionOrgId(appRuntimeEnv.getUserOrganization().getId());
        }

        if (query.getListType().equals("ListCollection")) {
            List<Integer> exceptStatusList = new ArrayList<>();
            exceptStatusList.add(1);    //过滤草稿状态
            saleOrderInfoRequestQuery.setExceptStatusList(exceptStatusList);
            List<Integer> paymentStatusList = new ArrayList<>();
            paymentStatusList.add(26);   //只显示 待付款 部分付款 已收讫
            paymentStatusList.add(27);
            paymentStatusList.add(28);
            saleOrderInfoRequestQuery.setPaymentStatusList(paymentStatusList);
            saleOrderInfoRequestQuery.setTicketType(0);//只读取网批订单
        }
        //如果seller_id没传取顶级组织id
        if (null == saleOrderInfoRequestQuery.getSellerId()) {
            saleOrderInfoRequestQuery.setSellerId(appRuntimeEnv.getTopOrganization().getId());
        }
        Payload<PageBean<SaleOrderInfoResponseDTO>> payload =
                saleOrderInfoClient.listSaleOrderInfosPage(saleOrderInfoRequestQuery);
        PageBean<SaleOrderBtnResponseDTO> result = GeneralConvertUtils.convert2PageBean(payload.getPayload(),
                SaleOrderBtnResponseDTO.class);

        //拼装按钮actions
        ToolStatusActionRequestQuery toolStatusActionRequestQuery = new ToolStatusActionRequestQuery();
        toolStatusActionRequestQuery.setAppId(query.getAppId());
        toolStatusActionRequestQuery.setTenantId(query.getTenantId());
        toolStatusActionRequestQuery.setListType(query.getListType());
        toolStatusActionRequestQuery.setItems(getItemList(result, query.getListType(), query.getTicketType()));
        Payload<List<ToolStatusActionResponesDTO>> listPayload =
                toolStatusClient.listToolStatusAction(toolStatusActionRequestQuery);

        List<ToolStatusActionResponesDTO> toolStatusActionResponesDTOS =
                GeneralConvertUtils.convert2List(listPayload.getPayload(), ToolStatusActionResponesDTO.class);

        for (int i = 0; i < result.getContent().size(); i++) {
            result.getContent().get(i).setActions(GeneralConvertUtils.convert2List(toolStatusActionResponesDTOS.get(i).getActions(), OrderActionResponseDTO.class));
        }

        return result;
    }

    /**
     * 销售订单分页 在线订购  过滤掉草稿状态
     *
     * @param query
     * @return
     */
    @Override
    public PageBean<SaleOrderBtnResponseDTO> listSaleOrderInfosPageForApi(SaleOrderInfoQuery query) throws Exception {
        //根据产品线增加筛选条件一级组织id（同时去除产品线id的查询）
        if (query.getProductId() != null) {
            ToolCategoryRequestQuery toolCategoryRequestQuery = new ToolCategoryRequestQuery();
            toolCategoryRequestQuery.setAppId(appId);
            toolCategoryRequestQuery.setTenantId(appRuntimeEnv.getTenantId());
            toolCategoryRequestQuery.setCategoryId(query.getProductId() + "");
            Payload<Long> payload = toolCommoditytypeAuthorizeClient.getAuthorizeOrgByCategoryId(toolCategoryRequestQuery);
            Long ascriptionOrgId = payload.getPayload();
            if(ascriptionOrgId == null){
                query.setAscriptionOrgId(-999L);
            }else{
                query.setAscriptionOrgId(ascriptionOrgId);
            }
            query.setProductId(null);
        }
        //封装筛选条件"排除的订单类型"
        String notTickTypeStr = query.getNotTickTypeStr();
        if(org.apache.commons.lang3.StringUtils.isNotBlank(notTickTypeStr)){
            String[] split = notTickTypeStr.split(",");
            List<Integer> nums = Arrays.stream(split).map(str -> Integer.valueOf(str)).collect(Collectors.toList());
            query.setNotTickType(nums);
        }

        List<Long> orderIds = new ArrayList<>();
        if (StringUtil.isNotEmpty(query.getSkuName()) || StringUtil.isNotEmpty(query.getSkuCode()) || StringUtil.isNotEmpty(query.getLikeSkuNameOrSkuFormat()) ) {
            SaleOrderItemMiddleRequestQuery saleOrderItemMiddleRequestQuery = new SaleOrderItemMiddleRequestQuery();
            saleOrderItemMiddleRequestQuery.setSkuCode(query.getSkuCode());
            saleOrderItemMiddleRequestQuery.setSkuName(query.getSkuName());
            saleOrderItemMiddleRequestQuery.setLikeSkuNameOrSkuFormat(query.getLikeSkuNameOrSkuFormat());
            List<SaleOrderItemMiddleResponseDTO> saleOrderItemMiddleResponseDTOS = saleOrderItemClient.listSaleOrderItems(saleOrderItemMiddleRequestQuery);
            orderIds = saleOrderItemMiddleResponseDTOS.stream().map(SaleOrderItemMiddleResponseDTO::getSaleOrderId).collect(Collectors.toList());
            if (orderIds.size() == 0) {
                return new PageBean<>(new ArrayList<>());
            }
        }

        PageMethod.startPage(query.getPage(), query.getSize());
        if (query.getBuyerId() != null) {
            query.setBuyerId(query.getBuyerId());
        } else {
            query.setBuyerId(appRuntimeEnv.getTopOrganization().getId());
        }
        // query.setHandler(appRuntimeEnv.getUserId());
        SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = query.clone(SaleOrderInfoRequestQuery.class);
        saleOrderInfoRequestQuery.setIds(orderIds);
        saleOrderInfoRequestQuery.setYear(query.getYear());
        List<Integer> exceptStatusList = new ArrayList<>();
        exceptStatusList.add(1);  //过滤草稿状态
        saleOrderInfoRequestQuery.setExceptStatusList(exceptStatusList);
        Payload<PageBean<SaleOrderInfoOLResponseDTO>> payload =
                saleOrderInfoClient.listSaleOrdersPage(saleOrderInfoRequestQuery);
        PageBean<SaleOrderBtnResponseDTO> result = GeneralConvertUtils.convert2PageBean(payload.getPayload(),
                SaleOrderBtnResponseDTO.class);


        if (result != null) {
            if (CollectionUtil.isNotEmpty(result.getContent())) {
                for (SaleOrderBtnResponseDTO dto : result.getContent()) {
                    if (CollectionUtil.isNotEmpty(dto.getItems())) {
                        BigDecimal outMoney = new BigDecimal(0);
                        for (SaleOrderItemResponseDTO item : dto.getItems()) {
                            item.setOrgId(dto.getSellerId());
                            // 出库总数为空，就是还没有出库
                            if (Objects.nonNull(item.getSkuTotalQuantity())) {
                                // 出库总数 * 单价 = 已出库金额
                                outMoney =
                                        outMoney.add(new BigDecimal(item.getSkuTotalQuantity()).multiply(item.getPrice()));
                            }
                        }
                        // 已付金额 - 已出库金额 = 可退金额
                        dto.setRefundableAmount(dto.getPayAmount().subtract(outMoney));
                    }
                }
            }
        }


        ToolStatusActionRequestQuery toolStatusActionRequestQuery = new ToolStatusActionRequestQuery();
        toolStatusActionRequestQuery.setAppId(query.getAppId());
        toolStatusActionRequestQuery.setTenantId(query.getTenantId());
        //select * from tool_status_action where list_type='PlannedOrder'
        if (TicketTypeEnum.PLAN.getValue().equals(query.getTicketType())) {
            toolStatusActionRequestQuery.setListType("PlannedOrder");
        } else {
            toolStatusActionRequestQuery.setListType("MallOrder");
        }
        //        toolStatusActionRequestQuery.setListType("MallOrder");
        toolStatusActionRequestQuery.setItems(getItemList(result, "MallOrder", query.getTicketType()));
        Payload<List<ToolStatusActionResponesDTO>> listPayload =
                toolStatusClient.listToolStatusAction(toolStatusActionRequestQuery);

        List<ToolStatusActionResponesDTO> toolStatusActionResponesDTOS =
                GeneralConvertUtils.convert2List(listPayload.getPayload(), ToolStatusActionResponesDTO.class);


        for (int i = 0; i < result.getContent().size(); i++) {
            result.getContent().get(i).setActions(GeneralConvertUtils.convert2List(toolStatusActionResponesDTOS.get(i).getActions(), OrderActionResponseDTO.class));
        }

        return result;
    }

    /**
     * 拼装业务链路接口的items参数
     *
     * @param result
     */
    private List<ToolStatusActionRequestQuery.Items> getItemList(PageBean<SaleOrderBtnResponseDTO> result,
                                                                 String listType, Integer ticketType) {
        //根据id排序
//        Collections.sort(result.getContent());

        List<ToolStatusActionRequestQuery.Items> itemsList = new ArrayList<>();
        for (SaleOrderBtnResponseDTO dto : result.getContent()) {
            ToolStatusActionRequestQuery.Items item = new ToolStatusActionRequestQuery.Items();
            item.setStatusCode(dto.getStatus());
            if (!TicketTypeEnum.PLAN.getValue().equals(ticketType)) {
                item.setStatusType("OrderStatus");
            }

            if (listType.equals("ListCollection")) {
                item.setStatusCode(dto.getPaymentStatus());
                item.setStatusType("PaymentStatus");
            }

            if (dto.getQuantity() == null) {
                dto.setQuantity(0L);
            }
            if (dto.getTotalQuantity() == null) {
                dto.setTotalQuantity(0L);
            }
            if (dto.getTotalSignQuantity() == null) {
                dto.setTotalSignQuantity(0L);
            }
            if (dto.getPayAmount() == null) {
                dto.setPayAmount(new BigDecimal(0));
            }
            if (dto.getPickQuantity() == null) {
                dto.setPickQuantity(0L);
            }

            if (dto.getRefundableAmount() == null) {
                dto.setRefundableAmount(new BigDecimal(0));
            }


            HashMap<String, Object> map = new HashMap();
            map.put("pickQuantity", dto.getPickQuantity());  //总提货数
            map.put("totalQuantity", dto.getTotalQuantity()); //总发货数
            map.put("paymentStatus", dto.getPaymentStatus()); //支付状态
            map.put("acceptStatus", dto.getAcceptStatus()); //接单状态
            map.put("unDeliverQuantity", dto.getQuantity() - dto.getTotalQuantity()); //未发货=商品总数-已发货总数
            map.put("unSignQuantity", dto.getTotalQuantity() - dto.getTotalSignQuantity());//未签收=商品总数-已签收总数
            map.put("unPaidAmount", dto.getAccrueAmount().subtract(dto.getPayAmount()));
            map.put("verifyStatus", dto.getVerifyStatus());//审核状态
            map.put("refundableAmount", dto.getRefundableAmount());//可退金额

            log.info("工具类参数：" + JsonUtil.bean2JsonString(map));
            item.setRuleData(JsonUtil.bean2JsonString(map));
            item.setListId(dto.getId());
            itemsList.add(item);
        }
        return itemsList;
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售订单表,传入ID为空");
            return false;
        }
        Payload<Boolean> result = saleOrderInfoClient.deleteByIdIn(id);
        if (SUCCESS.equals(result.getCode())) {
            return true;
        }
        return false;
    }

    /**
     * 创建销售订单
     *
     * @param saleOrder
     * @return
     */
    @Override
    public SaleOrderInfoDTO createOrder(SaleOrderInfoDTO saleOrder) throws Exception {
        //验证商品是否被授权
//            if (!validComodityAuth(saleOrder)) {
//                throw new ApplicationException(ResultEnum.ORDER_ITEM_NOT_AUTH);
//            }
        //设置创建人
        saleOrder.setCreatedBy(appRuntimeEnv.getUserId().toString());
        saleOrder.setTicketDate(new Date());
        saleOrder.setHandler(appRuntimeEnv.getUserId());
        saleOrder.setHandlerName(appRuntimeEnv.getUsername());

        //获取商品明细信息,从商品域获取
        List<SaleOrderItemDTO> items = getItems(saleOrder);
        //
        items.forEach(item -> {
            if (item.getPrice() == null) {
                saleOrder.getItems().forEach(p -> {
                    if (item.getSkuId().equals(p.getSkuId())) {
                        item.setPrice(p.getPrice());
                    }
                });
            }
            //活动id和直供id set进去
            saleOrder.getItems().forEach(sku -> {
                if (item.getSkuId().equals(sku.getSkuId())) {
                    item.setActivitiesId(sku.getActivitiesId());
                    item.setDirectId(sku.getDirectId());
                }
            });
        });
        //
        //验证商品库存和商品是否已下架
        validCommodityStock(items, saleOrder.getItems());
        //校验商品活动,返回参加活动的商品信息
        validSkuActivity(saleOrder);
        //根据从商品域获取的商品组装成新商品明细对象
        //设置合单的商品
        saleOrder.setItems(getSaleOrderItems(saleOrder, items));

        List<SaleOrderInfoDTO> orders = null;
        //拆分订单
        try {
            orders = spitOrder(saleOrder, items);
        } catch (Exception e) {
            logger.error("拆单异常:", e);
            throw new ApplicationException("拆单异常");
        }
        //提交订单
        if (CollectionUtil.isNotEmpty(orders)) {
            //生成主订单信息
            String code = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(),
                    IdentifierTypeEnum.ORDER.getPrefix() + DateUtils.format(new Date(), YYYYMMDD),
                    IdentifierTypeEnum.ORDER.getLen());
            logger.info("父单编号:{}", code);
            //保存合单信息
            saleOrder.setCode(code);
            saleOrder.setStatus(StatusCodeEnum.Draft.getCode());
            //设置合单的金额信息
            computeOrderAmount(saleOrder, orders);
            SaleOrderSplitRecordRequestDTO recordRequestDTO = saleOrder.clone(SaleOrderSplitRecordRequestDTO.class);
            recordRequestDTO.setPaymentStatus(StatusCodeEnum.Blank.getCode());
            SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO =
                    saleOrderSplitRecordClient.insert(recordRequestDTO);
            saleOrder.setId(saleOrderSplitRecordResponseDTO.getId());
            List<ActivitySkuOrderQuantityDTO> list = new ArrayList<>();
            List<Long> subOrderIds = new ArrayList<>();
            StringBuilder subCodes = new StringBuilder();
            //保存子订单
            for (SaleOrderInfoDTO order : orders) {
                order.setParentSaleOrderId(saleOrderSplitRecordResponseDTO.getId());
                order.setParentSaleOrderCode(code);
                String subCode = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(),
                        IdentifierTypeEnum.ORDER.getPrefix() + DateUtils.format(new Date(), YYYYMMDD),
                        IdentifierTypeEnum.ORDER.getLen());
                logger.info("生成子单编号:{}", subCode);
                order.setCode(subCode);
                submitOrder(order);
                //保存操作记录
                try{
                    recordOrder(order);
                }
                catch (Exception e){
                    log.info("保存操作记录出错");
                }

                subOrderIds.add(order.getId());
                subCodes.append("|").append(subCode);
                saleOrder.setStatus(order.getStatus());
                list.addAll(getActivitySku(order));
            }
            saleOrder.setSubOrderId(subOrderIds);
            saleOrder.setSubOrderCode(subCodes.substring(1));
            //保存活动数据
            if (CollectionUtil.isNotEmpty(list)) {
                activitySkuOrderQuantityClient.batchInsert(GeneralConvertUtils.convert2List(list,
                        com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO.class));
            }
        }
        //发送mq
        handerMq(orders, 0, saleOrder.getOrderType());
        return saleOrder;
    }

    private void recordOrder(SaleOrderInfoDTO order){
        //记录操作记录
        OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
        orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
        orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
        orderOperationRecordRequestDTO.setCreatedTime(new Date());
        orderOperationRecordRequestDTO.setOrderId(order.getId());
        orderOperationRecordRequestDTO.setOperation("创建订单");
        orderOperationRecordRequestDTO.setOperationType(1);
        orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
        orderOperationRecordRequestDTO.setRemark(order.getRemark());
        saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
    }

    /**
     * 生成需要保存的商品活动信息
     *
     * @param saleOrder
     * @return
     */
    private List<ActivitySkuOrderQuantityDTO> getActivitySku(SaleOrderInfoDTO saleOrder) {

        List<ActivitySkuOrderQuantityDTO> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(saleOrder.getItems())) {
            for (SaleOrderItemDTO shop : saleOrder.getItems()) {
                ActivitySkuOrderQuantityDTO activitySkuOrderQuantityDTO = new ActivitySkuOrderQuantityDTO();
                activitySkuOrderQuantityDTO.setSkuId(shop.getSkuId());
                activitySkuOrderQuantityDTO.setActivitiesId(shop.getActivitiesId());
                activitySkuOrderQuantityDTO.setPartnerId(saleOrder.getPartnerId());
                activitySkuOrderQuantityDTO.setQuantityOrdered(shop.getSkuQuantity());
                activitySkuOrderQuantityDTO.setOrderCode(saleOrder.getCode());
                activitySkuOrderQuantityDTO.setCreatedTime(saleOrder.getCreatedTime());
                activitySkuOrderQuantityDTO.setTenantId(saleOrder.getTenantId());
                activitySkuOrderQuantityDTO.setAppId(saleOrder.getAppId());
                list.add(activitySkuOrderQuantityDTO);
            }
        }
        return list;
    }

    private String getCustActivity(Long partnerId, Long activityId) {
        return partnerId + "@_@" + activityId;
    }

    private List<com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO> getActivitySku(Long partnerId,
                                                                                                    Map<Long,
                                                                                                            List<Long>> skuMap) {
        List<com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO> result = new ArrayList<>();
        for (Map.Entry<Long, List<Long>> entry : skuMap.entrySet()) {
            ActivitySkuOrderQuantityQuery query = new ActivitySkuOrderQuantityQuery();
            query.setActivitiesId(entry.getKey());
            query.setSkuIds(entry.getValue());
            query.setPartnerId(partnerId);
            Payload<List<com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO>> payload =
                    activitySkuOrderQuantityClient.queryActivitySku(query);
            if (SUCCESS.equals(payload.getCode())) {
                try {
                    if (CollectionUtil.isNotEmpty(payload.getPayload())) {
                        List<com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO> list =
                                GeneralConvertUtils.convert2List(payload.getPayload(),
                                        com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO.class);
                        if (CollectionUtil.isNotEmpty(list)) {
                            result.addAll(list);
                        }
                    }
                } catch (Exception e) {
                    logger.error("获取活动商品异常:", e);
                    throw new ApplicationException("获取活动商品异常");
                }
            }
        }
        return result;
    }

    private Long getLong(Long value) {
        return value == null ? 0L : value;
    }

    private void validSkuActivity(SaleOrderInfoDTO saleOrderInfoDTO) throws ApplicationException {
        // 查询活动下商品限购信息
        List<SaleOrderItemDTO> shops = saleOrderInfoDTO.getItems();
        shops = shops.stream().filter(a -> a.getActivitiesId() != null).collect(Collectors.toList());
        //当前提交的商品数
        Map<Long, Map<Long, Long>> skuMap =
                shops.stream().collect(Collectors.groupingBy(SaleOrderItemDTO::getActivitiesId,
                        Collectors.toMap(SaleOrderItemDTO::getSkuId, SaleOrderItemDTO::getSkuQuantity)));
        Map<Long, List<Long>> shopsMap =
                shops.stream().collect(Collectors.groupingBy(SaleOrderItemDTO::getActivitiesId,
                        Collectors.mapping(SaleOrderItemDTO::getSkuId, Collectors.toList())));
        //获用户取已参加活动的商品数
        List<com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO> result =
                getActivitySku(saleOrderInfoDTO.getPartnerId(), shopsMap);

        Map<Long, Map<Long, Long>> activitySkuMap =
                result.stream().collect(Collectors.groupingBy(com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO::getActivitiesId, Collectors.groupingBy(com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO::getSkuId, Collectors.summingLong(a -> getLong(a.getQuantityOrdered())))));
        for (Map.Entry<Long, List<Long>> shop : shopsMap.entrySet()) {
            OnlineActivitiesGoodQuery onlineActivitiesGoodQuery = new OnlineActivitiesGoodQuery();
            onlineActivitiesGoodQuery.setSkuIds(shop.getValue());
            onlineActivitiesGoodQuery.setActivitiesId(shop.getKey());
            try {
                Payload<OnlineActivitiesResponseDTO> payload =
                        onlineActivitiesClient.getGoodByActivities(onlineActivitiesGoodQuery);
                if (SUCCESS.equals(payload.getCode()) && payload.getPayload() != null) {
                    OnlineActivitiesResponseDTO onlineActivitiesGoodResponseDTO =
                            GeneralConvertUtils.conv(payload.getPayload(), OnlineActivitiesResponseDTO.class);
                    //判断是否限购
                    if (CollectionUtil.isNotEmpty(onlineActivitiesGoodResponseDTO.getGoodList())) {
                        //获取当前活动已参加的商品信息
                        Map<Long, Long> activiSkuMap = activitySkuMap.get(shop.getKey());
                        Map<Long, Long> currentSkuMap = skuMap.get(shop.getKey());
                        for (OnlineActivitiesGoodResponseDTO onLine : onlineActivitiesGoodResponseDTO.getGoodList()) {
                            //获取当前提交商品的数量
                            Long skuQuantity = getLong(currentSkuMap.get(onLine.getSkuId()));
                            if (activiSkuMap != null) {
                                Long sku = activiSkuMap.get(onLine.getSkuId());
                                //加上已购买商品的数量
                                if (sku != null) {
                                    skuQuantity = skuQuantity + sku;
                                }
                            }
                            //判断限购数量和上架状态
                            if (skuQuantity > onLine.getLimitNum()
                                    && onLine.getOnStatus() == 1 && onLine.getIsLimited()) {
                                throw new ApplicationException("商品购买数量大于限购数量或商品未上架");
                            }
                        }

                    }
                }
            } catch (Exception e) {
                logger.error("校验活动商品限购数量失败:", e);
                throw new ApplicationException("校验活动商品限购数量失败");
            }

        }

    }

    @Override
    public SaleOrderInfoDTO createPlanOrder(SaleOrderInfoDTO saleOrder) throws Exception {
        //验证商品是否被授权
           /* if (!validComodityAuth(saleOrder)) {
                throw new ApplicationException(ResultEnum.ORDER_ITEM_NOT_AUTH);
            }*/
        //设置创建人
        saleOrder.setCreatedBy(appRuntimeEnv.getUserId().toString());
        saleOrder.setTicketDate(new Date());
        saleOrder.setHandler(appRuntimeEnv.getUserId());
        saleOrder.setHandlerName(appRuntimeEnv.getUsername());

        //获取商品明细信息,从商品域获取
        List<SaleOrderItemDTO> items = getItems(saleOrder);
        //特殊处理一下，后续要删除start
        items.forEach(item -> {
            if (item.getPrice() == null) {
                saleOrder.getItems().forEach(p -> {
                    if (item.getSkuId().equals(p.getSkuId())) {
                        item.setPrice(p.getPrice());
                    }
                });
            }
        });
        //删除end
        //验证商品库存和商品是否已下架,订货计划不需要验证库存
        //validCommodityStock(items, saleOrder.getItems());
        //根据从商品域获取的商品组装成新商品明细对象
        //设置合单的商品
        saleOrder.setItems(getSaleOrderItems(saleOrder, items));
        //计算合单的金额信息
        computeOrderAmount(saleOrder);

        //根据当前用户的业务伙伴ID，供应商直属组织ID
        List<MerchantResponseDTO> merchatList = queryMerchatByPartners(saleOrder.getTenantId(), saleOrder.getAppId(),
                saleOrder.getPartnerId());
        Map<String, MerchantResponseDTO> merchatMap =
                merchatList.stream().collect(Collectors.toMap(MerchantResponseDTO::getOrgId, Function.identity(),
                        (k1, k2) -> k1));
        List<Long> skuIds = items.stream().map(it -> it.getSkuId()).collect(Collectors.toList());
        Map<Long, List<Long>> skuOrgMap = getSkuOrgMap(saleOrder.getAppId(), saleOrder.getSellerId(), skuIds);
        setOrder(skuOrgMap, saleOrder, merchatMap);
        String code = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(),
                IdentifierTypeEnum.ORDER.getPrefix() + DateUtils.format(new Date(), YYYYMMDD),
                IdentifierTypeEnum.ORDER.getLen());
        saleOrder.setCode(code);
        SaleOrderInfoAddRequestDTO dto = SaleOrderInfoDtoHelper.convertToMiddleSaleOrderInfoRequestDto(saleOrder);
        dto.setStatus(StatusCodeEnum.ToTakeOrder.getCode());
        dto.setPaymentStatus(StatusCodeEnum.Blank.getCode());
        try {
            Payload<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTO =
                    saleOrderInfoClient.insert(dto);
            if (SUCCESS.equals(saleOrderInfoResponseDTO.getCode())) {
                SaleOrderInfoResponseDTO order = GeneralConvertUtils.conv(saleOrderInfoResponseDTO.getPayload(),
                        SaleOrderInfoResponseDTO.class);
                saleOrder.setId(order.getId());
                if (SUBMIT.equals(saleOrder.getType())) {
                    //启动流程
                    startProcess(saleOrder);
                }
                SaleOrderInfoResponseDTO saleOrderInfoResponseDTO1 =
                        GeneralConvertUtils.conv(saleOrderInfoResponseDTO.getPayload(),
                                SaleOrderInfoResponseDTO.class);
                saleOrder.setId(saleOrderInfoResponseDTO1.getId());
            } else {
                logger.error("订单创建失败:{}", JsonUtil.bean2JsonString(dto));
                throw new ApplicationException(ResultEnum.ORDER_CREATE_ERROR);
            }
        } catch (Exception e) {
            logger.error("订单创建失败:", e);
            throw new ApplicationException(ResultEnum.ORDER_CREATE_ERROR);
        }

        return saleOrder;
    }

    /**
     * 拆分订单
     *
     * @param saleOrder
     * @param items
     * @return
     */
    public List<SaleOrderInfoDTO> spitOrder(SaleOrderInfoDTO saleOrder, List<SaleOrderItemDTO> items) throws Exception {
        List<SaleOrderInfoDTO> resultList = new ArrayList<>();
        Map<Long, SaleOrderItemDTO> itemMap = items.stream().collect(Collectors.toMap(SaleOrderItemDTO::getSkuId,
                Function.identity(), (key1, key2) -> key1));
        //根据当前用户的业务伙伴ID，供应商直属组织ID
        List<MerchantResponseDTO> merchatList = queryMerchatByPartners(saleOrder.getTenantId(), saleOrder.getAppId(),
                saleOrder.getPartnerId());
        Map<String, MerchantResponseDTO> merchatMap =
                merchatList.stream().collect(Collectors.toMap(MerchantResponseDTO::getOrgId, Function.identity(),
                        (k1, k2) -> k1));
        List<Long> skuIds = items.stream().map(SaleOrderItemDTO::getSkuId).collect(Collectors.toList());
        //查询商品对应的一级组织
        Map<Long, List<Long>> skuOrgMap = getSkuOrgMap(saleOrder.getAppId(), saleOrder.getSellerId(), skuIds);
        Set<Long> skuSets = new HashSet<>();
        if (skuOrgMap.size() > 0) {
            for (Map.Entry<Long, List<Long>> entry : skuOrgMap.entrySet()) {
                SaleOrderInfoDTO saleOrderInfoDTO = saleOrder.clone(SaleOrderInfoDTO.class);
                saleOrderInfoDTO.setAscriptionOrgId(entry.getKey().equals(-9999L) ? saleOrder.getSellerId() :
                        entry.getKey());
                MerchantResponseDTO merchant=null;
                //如果组织id不为空，则获取客户信息
                if (!entry.getKey().equals(-9999L)) {
                    merchant = merchatMap.get(entry.getKey() + "");
                    if (merchant == null) {
                        merchant = merchatMap.get(saleOrder.getSellerId() + "");
                    }

                } else {
                    merchatMap.get(saleOrder.getSellerId() + "");
                    merchant = merchatMap.get(saleOrder.getSellerId() + "");
                }
                //如果从1级组织和顶级组织都获取不到客户信息，则取第一个客户
                if (merchant == null) {
                    merchant=merchatList.get(0);
                }
                saleOrderInfoDTO.setCustomerId(merchant.getId());
                saleOrderInfoDTO.setCustomerName(merchant.getName());
                saleOrderInfoDTO.setCustomerType(merchant.getTypeId());

                List<SaleOrderItemDTO> subItems = new ArrayList<>();
                for (Long skuId : entry.getValue()) {
                    if (skuSets.contains(skuId)) {
                        continue;
                    }
                    SaleOrderItemDTO item = itemMap.get(skuId);
                    if (item != null) {
                        subItems.add(item);
                        skuSets.add(skuId);
                    }
                }

                //设置订单商品信息
                saleOrderInfoDTO.setItems(getSaleOrderItems(saleOrder, subItems));
                setCustPrice(saleOrderInfoDTO);
                setOrderBaseInfo(saleOrderInfoDTO);
                resultList.add(saleOrderInfoDTO);
            }
        } else {
            MerchantResponseDTO merchant = merchatMap.get(saleOrder.getSellerId() + "");
            if (merchant != null) {
                saleOrder.setCustomerId(merchant.getId());
                saleOrder.setCustomerName(merchant.getName());
                saleOrder.setCustomerType(merchant.getTypeId());
            }
            //设置订单商品信息
            saleOrder.setItems(getSaleOrderItems(saleOrder, items));
            setCustPrice(saleOrder);
            setOrderBaseInfo(saleOrder);
            resultList.add(saleOrder);
        }
        return resultList;
    }

    private void setCustPrice(SaleOrderInfoDTO saleOrderInfoDTO) throws Exception {
        List<SaleOrderItemDTO> result = new ArrayList<>();
        //获取授权价格
        if (!TicketTypeEnum.DIRECT.getValue().equals(saleOrderInfoDTO.getTicketType()) && saleOrderInfoDTO.getCustomerId() != null) {
            Map<Long, List<Long>> shopSkuMap =
                    saleOrderInfoDTO.getItems().stream().collect(Collectors.groupingBy(SaleOrderItemDTO::getShopId,
                            Collectors.mapping(SaleOrderItemDTO::getSkuId, Collectors.toList())));

            if (shopSkuMap != null) {
                for (Map.Entry<Long, List<Long>> entry : shopSkuMap.entrySet()) {
                    List<SaleOrderItemDTO> list = getCustItemList(entry.getKey(), saleOrderInfoDTO, entry.getValue());
                    if (CollectionUtil.isNotEmpty(list)) {
                        result.addAll(list);
                    }
                }
            }

        }
        Map<Long, BigDecimal> priceMap = result.stream().collect(Collectors.toMap(SaleOrderItemDTO::getSkuId,
                a -> getBigDecimal(a.getPrice()), (v1, v2) -> v1));
        if (priceMap != null) {
            for (SaleOrderItemDTO item : saleOrderInfoDTO.getItems()) {
                BigDecimal price = priceMap.get(item.getSkuId());
                if (price != null) {
                    item.setPrice(price);
                }
            }
        }
    }

    /**
     * 获取商品对应的一级组织
     *
     * @param appId
     * @param skuIds
     * @return
     * @throws Exception
     */
    private Map<Long, List<Long>> getSkuOrgMap(Long appId, Long orgId, List<Long> skuIds) throws Exception {
        ToolSkuRequestDTO requestDTO = new ToolSkuRequestDTO();
        requestDTO.setAppId(appId);
        requestDTO.setTenantId(appRuntimeEnv.getTenantId());
        requestDTO.setOrgId(orgId);
        String ids = StringUtils.join(skuIds, ",");
        requestDTO.setSkuIds(ids);
        log.info("=============即将调用skuIds反查授权组织方法=============参数requestDTO：" + JSON.toJSONString(requestDTO));

        Payload<List<ToolSkuAuthorizeOrgResponseDTO>> payloadSkuOrgs =
                toolCommoditytypeAuthorizeClient.getAuthorizeOrgBySkuId(requestDTO);
        log.info("获取skuids反查授权组织结果payloadSkuOrgs.getCode()：" + payloadSkuOrgs.getCode());
        if (SUCCESS.equals(payloadSkuOrgs.getCode())) {
            List<ToolSkuAuthorizeOrgResponseDTO> resultList =
                    GeneralConvertUtils.convert2List(payloadSkuOrgs.getPayload(), ToolSkuAuthorizeOrgResponseDTO.class);
            log.info("获取skuids反查授权组织结果resultList：" + JSON.toJSONString(resultList));
            if (CollectionUtil.isNotEmpty(resultList)) {
                return resultList.stream().collect(Collectors.groupingBy(a -> getDefaultKey(a.getOrgId()),
                        Collectors.mapping(ToolSkuAuthorizeOrgResponseDTO::getSkuId, Collectors.toList())));
            }
        }
        return new HashMap<>();
    }

    private Long getDefaultKey(Long key) {
        return key == null ? -9999L : key;
    }

    /**
     * 根据业务伙伴ID查询所有客户信息
     *
     * @param partnerId
     * @return
     */
    private List<MerchantResponseDTO> queryMerchatByPartners(String tenantId, Long appId, Long partnerId) throws ApplicationException {
        MerchantRequestQuery query = new MerchantRequestQuery();
        query.setTenantId(tenantId);
        query.setAppId(appId);
        query.setPartnerId(partnerId);
        try {
            Payload<List<MerchantResponseDTO>> payload = merchantClient.findList(query);
            return GeneralConvertUtils.convert2List(payload.getPayload(), MerchantResponseDTO.class);
        } catch (Exception e) {
            logger.error("查询客户信息失败:", e);
            throw new ApplicationException("查询客户信息失败:{}", JsonUtil.bean2JsonString(query));
        }
    }

    /**
     * 设置订单基础信息(订单编号、创建人、经手人、价格信息)
     *
     * @param saleOrder
     */
    private void setOrderBaseInfo(SaleOrderInfoDTO saleOrder) {
        //生成订单编号
        String orderNo = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(),
                IdentifierTypeEnum.ORDER.getPrefix() + DateUtils.format(new Date(), YYYYMMDD),
                IdentifierTypeEnum.ORDER.getLen());
        saleOrder.setCode(orderNo);
        //设置创建人
        saleOrder.setCreatedBy(appRuntimeEnv.getUserId().toString());
        saleOrder.setTicketDate(new Date());
        saleOrder.setHandler(appRuntimeEnv.getUserId());
        saleOrder.setHandlerName(appRuntimeEnv.getUsername());
        //计算订单价格信息
        computeOrderAmount(saleOrder);
    }

    /**
     * 提交订单
     *
     * @param saleOrder
     */
    private SaleOrderInfoDTO submitOrder(SaleOrderInfoDTO saleOrder) {
        //提交下单
        SaleOrderInfoAddRequestDTO dto = SaleOrderInfoDtoHelper.convertToMiddleSaleOrderInfoRequestDto(saleOrder);
        dto.setStatus(StatusCodeEnum.Draft.getCode());
        if (TicketTypeEnum.PLAN.getValue().equals(saleOrder.getTicketType())) {
            dto.setStatus(StatusCodeEnum.ToTakeOrder.getCode());
        }
        dto.setPaymentStatus(StatusCodeEnum.Blank.getCode());
        try {
            Payload<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTO = saleOrderInfoClient.insert(dto);
            if (SUCCESS.equals(saleOrderInfoResponseDTO.getCode())) {
                SaleOrderInfoResponseDTO order = GeneralConvertUtils.conv(saleOrderInfoResponseDTO.getPayload(),
                        SaleOrderInfoResponseDTO.class);
                saleOrder.setId(order.getId());
                if (SUBMIT.equals(saleOrder.getType())) {
                    if (!TicketTypeEnum.PLAN.getValue().equals(saleOrder.getTicketType())) {
                        //扣减可售库存
                        incLockDecSalableStock(saleOrder);
                        //清除购物车商品
                        cancalShoppingCart(saleOrder);
                    }
                    //启动流程
                    startProcess(saleOrder);
                }
            } else {
                logger.error("订单创建失败:{}", JsonUtil.bean2JsonString(dto));
                throw new ApplicationException(ResultEnum.ORDER_CREATE_ERROR);
            }
        } catch (Exception e) {
            logger.error("订单创建失败:", e);
            throw new ApplicationException(ResultEnum.ORDER_CREATE_ERROR);
        }

        return saleOrder;
    }

    private boolean cancalShoppingCart(SaleOrderInfoDTO saleOrder) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setOrderTypeId(saleOrder.getTicketType());
        List<ShoppingCartCommodityDTO> shoppingCartCommodityDTOList = saleOrder.getItems().stream().map(s -> {
            ShoppingCartCommodityDTO cartCommodityDTO = new ShoppingCartCommodityDTO();
            cartCommodityDTO.setSkuId(s.getSkuId());
            cartCommodityDTO.setShopId(s.getShopId());
            cartCommodityDTO.setActivitiesId(s.getActivitiesId());
            cartCommodityDTO.setDirectId(s.getDirectId());
            cartCommodityDTO.setNum(converLongToInteger(s.getSkuQuantity()));
            return cartCommodityDTO;
        }).collect(Collectors.toList());
        shoppingCartDTO.setShoppingCartCommodityDTOList(shoppingCartCommodityDTOList);
        ShoppingCartUserInfoDTO shoppingCartUserInfoDTO = new ShoppingCartUserInfoDTO();
        shoppingCartUserInfoDTO.setUserId(appRuntimeEnv.getUserId());
        shoppingCartUserInfoDTO.setTenantId(appRuntimeEnv.getTenantId());
        shoppingCartUserInfoDTO.setAppId(saleOrder.getAppId());
        log.info("cancalShoppingCart:{}", JSON.toJSONString(shoppingCartDTO));
        return shoppingCartService.delCommodity(shoppingCartUserInfoDTO, shoppingCartDTO, true);
    }

    private Integer converLongToInteger(Long number) {
        if (Objects.isNull(number)) {
            return 0;
        }
        return BigDecimal.valueOf(number).intValue();
    }

    /**
     * 启动流程
     *
     * @param saleOrder
     * @return
     * @throws Exception
     */
    private boolean startProcess(SaleOrderInfoDTO saleOrder) throws Exception {
        ToolLinkStartLinkRequestDTO toolLinkStartLinkRequestDTO = new ToolLinkStartLinkRequestDTO();
        toolLinkStartLinkRequestDTO.setListId(saleOrder.getId());
        toolLinkStartLinkRequestDTO.setLinkType(saleOrder.getBuyerType());
        toolLinkStartLinkRequestDTO.setListNo(saleOrder.getCode());
        toolLinkStartLinkRequestDTO.setTenantId(saleOrder.getTenantId());
        toolLinkStartLinkRequestDTO.setAppId(saleOrder.getAppId());
        toolLinkStartLinkRequestDTO.setSellerOrgId(saleOrder.getSellerId());
        //拼装json
        HashMap<String, Object> map = new HashMap();
        map.put("ListType", saleOrder.getTicketType());
        map.put("Customer", saleOrder.getBuyerId());
//        map.put("DeliveryMethod", saleOrder.getShippingType());
        BusinessPartnerResponseDTO businessPartnerResponseDTO = getPartner(saleOrder.getTenantId(),
                saleOrder.getAppId(), appRuntimeEnv.getTopOrganization().getId());
        map.put("EnterpriseType", businessPartnerResponseDTO != null ? businessPartnerResponseDTO.getCompanyTypeId()
                : null);
        map.put("CommodityAmount", saleOrder.getTotalAmount());
        map.put("CustomerType", saleOrder.getCustomerType());
        String json = JsonUtil.bean2JsonString(map);
        toolLinkStartLinkRequestDTO.setListFormData(json);
        //统一使用SalesOrder类型
        toolLinkStartLinkRequestDTO.setListType(ListTypeEnum.SalesOrder.name());
//        if (TicketTypeEnum.PLAN.getValue().equals(saleOrder.getTicketType())) {
//            toolLinkStartLinkRequestDTO.setListType("OrderPlan");
//        } else {
//            toolLinkStartLinkRequestDTO.setListType(ListTypeEnum.SalesOrder.name());
//        }

        Payload<ToolLinkStartLinkResponseDTO> responseDTO = toolLinkClient.startLink(toolLinkStartLinkRequestDTO);
        ToolLinkStartLinkResponseDTO toolLinkStartLinkResponseDTO = GeneralConvertUtils.conv(responseDTO.getPayload()
                , ToolLinkStartLinkResponseDTO.class);

        com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO saleOrderInfoAddRequestDTO =
                new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO();
        saleOrderInfoAddRequestDTO.setId(saleOrder.getId());
        //根据
        if (!toolLinkStartLinkResponseDTO.getDisabledBusinessCodes().contains(LinkBusinessCodeEnum.BuyerPayment.name())) {
            saleOrderInfoAddRequestDTO.setPaymentStatus(StatusCodeEnum.ToPayment.getCode());
        } else {
            saleOrderInfoAddRequestDTO.setPaymentStatus(StatusCodeEnum.Blank.getCode());
        }
        if (!toolLinkStartLinkResponseDTO.getDisabledBusinessCodes().contains(LinkBusinessCodeEnum.ArtificialOrder.name())) {
            saleOrderInfoAddRequestDTO.setAcceptStatus(StatusCodeEnum.Blank.getCode());
        }
        if (null != saleOrderInfoAddRequestDTO.getPaymentStatus() || null != saleOrderInfoAddRequestDTO.getAcceptStatus()) {
            saleOrderInfoClient.updateMainOrderById(saleOrder.getId(), saleOrderInfoAddRequestDTO);
        }
        com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO =
                new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO();
        saleOrderInfoStatusEditRequestDTO.setStatus(toolLinkStartLinkResponseDTO.getStatus());
        saleOrder.setStatus(toolLinkStartLinkResponseDTO.getStatus());
        saleOrderInfoStatusEditRequestDTO.setId(saleOrder.getId());
        return saleOrderInfoClient.updateOrderStatus(saleOrderInfoStatusEditRequestDTO).getPayload();
    }

    /**
     * 验证商品授权
     *
     * @param saleOrder
     * @return
     */
    private boolean validComodityAuth(SaleOrderInfoDTO saleOrder) throws ApplicationException {
        if (CollectionUtil.isEmpty(saleOrder.getItems())) {
            throw new ApplicationException(ResultEnum.ORDER_ITEM_NOT_FOUNT);
        }
        Map<Long, BigDecimal> commodityMap = null;
        if (TicketTypeEnum.DIRECT.getValue().equals(saleOrder.getTicketType())) {
            commodityMap = getDirectCommodity(saleOrder);
        } else {
            commodityMap = getAuthCommodity(saleOrder.getSellerId(), saleOrder.getAppId(), saleOrder.getTicketType());
        }
        if (commodityMap == null) {
            return false;
        }
        for (SaleOrderItemDTO item : saleOrder.getItems()) {
            if (commodityMap.get(item.getSkuId()) == null) {
                return false;
            }
        }
        return true;
    }

    private Map<Long, BigDecimal> getAuthCommodity(Long orgId, Long appId, Integer ticketType) {
        ToolAuthorizeCommodityInfoQuery authorizeRequestQuery = new ToolAuthorizeCommodityInfoQuery();
        // authorizeRequestQuery.setAuthorizeBy(orgId);
        authorizeRequestQuery.setAppId(appId);
        authorizeRequestQuery.setTenantId(appRuntimeEnv.getTenantId());
        Map<Long, BigDecimal> map = new HashMap<>();
        try {
            Payload<List<ToolAuthorizeCommodityResponseDTO>> payload =
                    toolAuthorizeApi.listAuthorizeCommodityInfo(authorizeRequestQuery);
            if (SUCCESS.equals(payload.getCode())) {
                List<ToolAuthorizeCommodityResponseDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),
                        ToolAuthorizeCommodityResponseDTO.class);
                if (list != null) {
                    Map<Integer, ToolAuthorizeCommodityResponseDTO> orderMap =
                            list.stream().collect(Collectors.toMap(ToolAuthorizeCommodityResponseDTO::getOrderType,
                                    Function.identity(), (k1, k2) -> k1));
                    ToolAuthorizeCommodityResponseDTO commodityResponseDTO = orderMap.get(ticketType);
                    if (commodityResponseDTO != null) {
                        if (CollectionUtil.isNotEmpty(commodityResponseDTO.getAuthorizeInfo())) {
                            for (ToolAuthorizeCommodityResponseDTO.Item item :
                                    commodityResponseDTO.getAuthorizeInfo()) {
                                if (CollectionUtil.isNotEmpty(item.getSkuIds())) {
                                    item.getSkuIds().forEach(a -> {
                                        map.put(Long.valueOf(a), BigDecimal.valueOf(0));
                                    });
                                }

                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            logger.info("被授权id:{},订单类型:{}", orgId, appId);
            logger.error("查询认证商品错误:", e);
        }
        return map;
    }

    /**
     * 获取直供商品
     *
     * @param saleOrder
     * @return
     */
    private Map<Long, BigDecimal> getDirectCommodity(SaleOrderInfoDTO saleOrder) {
        CommodityQuery commodityQuery = new CommodityQuery();
        commodityQuery.setTenantId(saleOrder.getTenantId());
        commodityQuery.setAppId(saleOrder.getAppId());
        commodityQuery.setSkuIds(saleOrder.getItems().stream().map(SaleOrderItemDTO::getSkuId).collect(Collectors.toList()));
        return commodityManager.getDirectSkuPrice(commodityQuery);
    }

    /**
     * 增加锁定库存，减少可售库存
     *
     * @param saleOrder
     */
    private void incLockDecSalableStock(SaleOrderInfoDTO saleOrder) {

        //处理普通商品库存
        SaleOrderInfoDTO infoDTO = saleOrder.clone(SaleOrderInfoDTO.class);
        infoDTO.setItems(saleOrder.getItems().stream().filter(a -> a.getActivitiesId() == null).collect(Collectors.toList()));
        commodityManager.incLockDecSalableStock(getCommodityQuantityList(infoDTO));
        //处理活动库存
        SaleOrderInfoDTO act = saleOrder.clone(SaleOrderInfoDTO.class);
        act.setItems(saleOrder.getItems().stream().filter(a -> a.getActivitiesId() != null).collect(Collectors.toList()));
        if (CollectionUtil.isNotEmpty(act.getItems())) {
            commodityManager.incLockDecSalableActivityStock(getActivityCommodityQuantityList(act));
        }
    }

    /**
     * 减少锁定库存，增加可售库存
     *
     * @param saleOrder
     */
    private void decLockIncSalableStock(SaleOrderInfoDTO saleOrder) {
        //处理普通商品库存
        SaleOrderInfoDTO infoDTO = saleOrder.clone(SaleOrderInfoDTO.class);
        infoDTO.setItems(saleOrder.getItems().stream().filter(a -> a.getActivitiesId() == null).collect(Collectors.toList()));
        commodityManager.decLockIncSalableStock(getCommodityQuantityList(infoDTO));

        //处理活动库存
        SaleOrderInfoDTO act = saleOrder.clone(SaleOrderInfoDTO.class);
        act.setItems(saleOrder.getItems().stream().filter(a -> a.getActivitiesId() != null).collect(Collectors.toList()));
        if (CollectionUtil.isNotEmpty(act.getItems())) {
            commodityManager.decLockIncSalableActivityStock(getActivityCommodityQuantityList(act));
        }
    }

    /**
     * 获取操作商品库存的参数
     *
     * @param saleOrder
     * @return
     */
    private List<CommodityStockOperationDTO> getCommodityQuantityList(SaleOrderInfoDTO saleOrder) {
        List<CommodityStockOperationDTO> commodityStockOperationDTOList = new ArrayList<>();
        Map<Long, List<SaleOrderItemDTO>> mapList =
                saleOrder.getItems().stream().collect(Collectors.groupingBy(SaleOrderItemDTO::getShopId));

        for (Map.Entry<Long, List<SaleOrderItemDTO>> entry : mapList.entrySet()) {
            CommodityStockOperationDTO commodityStockOperationDTO = new CommodityStockOperationDTO();
            commodityStockOperationDTO.setTenantId(saleOrder.getTenantId());
            commodityStockOperationDTO.setAppId(saleOrder.getAppId());
            commodityStockOperationDTO.setChannel(CHANNEL);
            commodityStockOperationDTO.setExtendId(saleOrder.getId());
            commodityStockOperationDTO.setExtendNo(saleOrder.getCode());
            commodityStockOperationDTO.setExtendType(Integer.valueOf(saleOrder.getTicketType()));
            List<CommodityQuantityDTO> comQtys = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(entry.getValue())) {
                for (SaleOrderItemDTO item : entry.getValue()) {
                    CommodityQuantityDTO qty = new CommodityQuantityDTO();
                    qty.setSkuId(item.getSkuId());
                    qty.setSkuQty(item.getSkuQuantity().intValue());
                    comQtys.add(qty);
                }
            }
            //目前只考虑一个订单中只存在一个店铺的情况,如果存在一单中有多个店铺，需要前面拆单
            commodityStockOperationDTO.setShopId(entry.getKey());
            commodityStockOperationDTO.setSkuQty(comQtys);
            commodityStockOperationDTOList.add(commodityStockOperationDTO);
        }
        return commodityStockOperationDTOList;
    }

    /**
     * 获取操作商品库存的参数
     *
     * @param saleOrder
     * @return
     */
    private List<CommodityStockOperationDTO> getActivityCommodityQuantityList(SaleOrderInfoDTO saleOrder) {
        List<CommodityStockOperationDTO> commodityStockOperationDTOList = new ArrayList<>();
        Map<Long, List<SaleOrderItemDTO>> mapList =
                saleOrder.getItems().stream().collect(Collectors.groupingBy(SaleOrderItemDTO::getShopId));

        for (Map.Entry<Long, List<SaleOrderItemDTO>> entry : mapList.entrySet()) {
            Map<Long, List<SaleOrderItemDTO>> activityMap =
                    entry.getValue().stream().collect(Collectors.groupingBy(SaleOrderItemDTO::getActivitiesId));
            for (Map.Entry<Long, List<SaleOrderItemDTO>> activityEntry : activityMap.entrySet()) {
                CommodityStockOperationDTO commodityStockOperationDTO = new CommodityStockOperationDTO();
                commodityStockOperationDTO.setTenantId(saleOrder.getTenantId());
                commodityStockOperationDTO.setAppId(saleOrder.getAppId());
                commodityStockOperationDTO.setChannel(CHANNEL);
                commodityStockOperationDTO.setExtendId(saleOrder.getId());
                commodityStockOperationDTO.setExtendNo(saleOrder.getCode());
                commodityStockOperationDTO.setExtendType(Integer.valueOf(saleOrder.getTicketType()));
                //设置活动id
                commodityStockOperationDTO.setActivityId(activityEntry.getKey());
                List<CommodityQuantityDTO> comQtys = new ArrayList<>();
                if (CollectionUtil.isNotEmpty(entry.getValue())) {
                    for (SaleOrderItemDTO item : entry.getValue()) {
                        CommodityQuantityDTO qty = new CommodityQuantityDTO();
                        qty.setSkuId(item.getSkuId());
                        qty.setSkuQty(item.getSkuQuantity().intValue());
                        comQtys.add(qty);
                    }
                }
                //目前只考虑一个订单中只存在一个店铺的情况,如果存在一单中有多个店铺，需要前面拆单
                commodityStockOperationDTO.setShopId(entry.getKey());
                commodityStockOperationDTO.setSkuQty(comQtys);
                commodityStockOperationDTOList.add(commodityStockOperationDTO);
            }
        }
        return commodityStockOperationDTOList;
    }

    /**
     * 验证商品库存和商品是否已下架
     *
     * @param items
     * @param submitItems 提交的商品
     * @return
     */
    private void validCommodityStock(List<SaleOrderItemDTO> items, List<SaleOrderItemDTO> submitItems) throws ApplicationException {

        if (CollectionUtil.isNotEmpty(items)) {
            Map<String, SaleOrderItemDTO> commodityMap =
                    items.stream().collect(Collectors.toMap(SaleOrderItemDTO::getShopIdAndSkuId, Function.identity(),
                            (k1,
                             k2) -> k1));
            for (SaleOrderItemDTO d : submitItems) {
                SaleOrderItemDTO dto = commodityMap.get(d.getShopIdAndSkuId());
                if (dto == null) {
                    throw new ApplicationException(ResultEnum.COMMODITY_SHUTDOWN_ERROR);
                } else {
                    logger.info("sku{}库存数:{}", dto.getSkuName(), dto.getStock());
                    if (dto.getStock() == null || (dto.getStock() != null && dto.getStock() < 1)) {
                        throw new ApplicationException(ResultEnum.STOCK_ERROR);
                    }
                }
            }
        } else {
            throw new ApplicationException(ResultEnum.COMMODITY_SHUTDOWN_ERROR);
        }
    }

    private List<OrderCouponInfoDTO> getOrderCouponInfoList(SaleOrderInfoRequestDTO saleOrderInfoRequestDTO) {
        List<OrderCouponInfoDTO> list = new ArrayList<>();
        OrderCouponInfoDTO orderCouponInfoDTO = new OrderCouponInfoDTO();
        orderCouponInfoDTO.setCouponCode("XXDSDS");
        orderCouponInfoDTO.setCouponId(23322L);
        orderCouponInfoDTO.setCouponName("测试优惠券");
        orderCouponInfoDTO.setCouponQuntity(1);
        return list;
        //orderCouponInfoDTO.set
    }

    /**
     * 获取商品明细信息
     *
     * @param saleOrder
     */
    private List<SaleOrderItemDTO> getItems(SaleOrderInfoDTO saleOrder) throws Exception {
        Map<Long, List<SaleOrderItemDTO>> itemMap =
                saleOrder.getItems().stream().collect(Collectors.groupingBy(SaleOrderItemDTO::getShopId));
        //获取商品明细和库存
        return getSaleOrderItems(saleOrder, itemMap);
    }

    /**
     * 根据从商品域获取的商品组装成新商品明细对象
     *
     * @param saleOrder
     * @param comoditys
     * @return
     */
    private List<SaleOrderItemDTO> getSaleOrderItems(SaleOrderInfoDTO saleOrder, List<SaleOrderItemDTO> comoditys) {
        Map<String, SaleOrderItemDTO> itemMap =
                comoditys.stream().collect(Collectors.toMap(SaleOrderItemDTO::getShopIdAndSkuId, Function.identity(),
                        (key1,
                         key2) -> key1));
        List<SaleOrderItemDTO> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(saleOrder.getItems())) {
            saleOrder.getItems().forEach(a -> {
                        SaleOrderItemDTO item = itemMap.get(a.getShopId() + "@_@" + a.getSkuId());
                        if (item != null) {
                            item.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER_ITEM));
                            item.setSkuQuantity(a.getSkuQuantity());
                            item.setTaxRate(a.getTaxRate());
                            item.setTenantId(saleOrder.getTenantId());
                            item.setAppId(saleOrder.getAppId());
                            list.add(item);
                        }
                    }
            );
        }
        return list;
    }

    /**
     * 从中台获取商品信息
     *
     * @param listMap
     * @return
     */
    private List<SaleOrderItemDTO> getSaleOrderItems(SaleOrderInfoDTO saleOrder,
                                                     Map<Long, List<SaleOrderItemDTO>> listMap) throws Exception {
        List<SaleOrderItemDTO> result = new ArrayList<>();
        for (Map.Entry<Long, List<SaleOrderItemDTO>> entry : listMap.entrySet()) {
            //过滤出活动sku
            List<SaleOrderItemDTO> activitySkus =
                    entry.getValue().stream().filter(item -> item.getActivitiesId() != null).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(activitySkus)) {
                Map<Long, List<SaleOrderItemDTO>> activityMap =
                        activitySkus.stream().collect(Collectors.groupingBy(SaleOrderItemDTO::getActivitiesId));
                for (Map.Entry<Long, List<SaleOrderItemDTO>> acEntry : activityMap.entrySet()) {
                    List<SaleOrderItemDTO> activitySkuList = getActivityItemList(acEntry.getKey(), entry.getKey(),
                            acEntry.getValue().stream().map(SaleOrderItemDTO::getSkuId).collect(Collectors.toList()),
                            saleOrder.getTenantId(), saleOrder.getAppId(), saleOrder.getTicketType());
                    if (CollectionUtil.isNotEmpty(activitySkuList)) {
                        result.addAll(activitySkuList);
                    }
                }
                removeList(entry.getValue(), activitySkus);
            }
            List<SaleOrderItemDTO> skuList = getItemList(entry.getKey(), saleOrder,
                    entry.getValue().stream().map(SaleOrderItemDTO::getSkuId).collect(Collectors.toList()));
            if (CollectionUtil.isNotEmpty(skuList)) {
                result.addAll(skuList);
            }

        }
        return result;
    }

    private void removeList(List<SaleOrderItemDTO> list, List<SaleOrderItemDTO> removeList) {
        Map<Long, SaleOrderItemDTO> map = list.stream().collect(Collectors.toMap(SaleOrderItemDTO::getSkuId,
                Function.identity(), (k1, k2) -> k1));
        for (SaleOrderItemDTO item : removeList) {
            SaleOrderItemDTO dto = map.get(item.getSkuId());
            if (dto != null) {
                list.remove(dto);
            }
        }
    }

    private List<SaleOrderItemDTO> getItemList(Long shopId, SaleOrderInfoDTO saleOrder, List<Long> skuList) throws Exception {
        if (CollectionUtil.isEmpty(skuList)) {
            return new ArrayList<>();
        }
        CommodityQuery commodityQuery = new CommodityQuery();
        Map<Long, String> storeMap = new HashMap<>();
        storeMap.put(shopId, saleOrder.getSellerId() + "");
        Map<String, List<Long>> merchantMap = new HashMap<>();
        List<Long> customerIdList = new ArrayList<>();
        customerIdList.add(saleOrder.getCustomerId());
        merchantMap.put(saleOrder.getSellerId() + "", customerIdList);
        commodityQuery.setShopId(shopId);
        commodityQuery.setMerchantMap(merchantMap);
        commodityQuery.setStoreMap(storeMap);
        commodityQuery.setSkuIds(skuList);
        commodityQuery.setAppId(saleOrder.getAppId());
        commodityQuery.setTenantId(saleOrder.getTenantId());
        commodityQuery.setTicketType(saleOrder.getTicketType());
        commodityQuery.setType(0);
        return commodityManager.getCommoditys(commodityQuery);
    }

    private List<SaleOrderItemDTO> getCustItemList(Long shopId, SaleOrderInfoDTO saleOrder, List<Long> skuList) throws Exception {
        if (CollectionUtil.isEmpty(skuList)) {
            return new ArrayList<>();
        }
        CommodityQuery commodityQuery = new CommodityQuery();
        Map<Long, String> storeMap = new HashMap<>();
        storeMap.put(shopId, saleOrder.getSellerId() + "");
        Map<String, List<Long>> merchantMap = new HashMap<>();
        List<Long> customerIdList = new ArrayList<>();
        customerIdList.add(saleOrder.getCustomerId());
        merchantMap.put(saleOrder.getSellerId() + "", customerIdList);
        commodityQuery.setShopId(shopId);
        commodityQuery.setMerchantMap(merchantMap);
        commodityQuery.setStoreMap(storeMap);
        commodityQuery.setSkuIds(skuList);
        commodityQuery.setAppId(saleOrder.getAppId());
        commodityQuery.setTenantId(saleOrder.getTenantId());
        commodityQuery.setTicketType(saleOrder.getTicketType());

        return commodityManager.getCommoditys(commodityQuery);
    }

    private List<SaleOrderItemDTO> getActivityItemList(Long activitiesId, Long shopId, List<Long> skuList,
                                                       String tenantId, Long appId, Integer ticketType) throws Exception {
        if (CollectionUtil.isEmpty(skuList)) {
            return new ArrayList<>();
        }
        CommodityQuery commodityQuery = new CommodityQuery();
        if (activitiesId != null) {
            commodityQuery.setActivitiesId(activitiesId);
        }
        commodityQuery.setShopId(shopId);
        commodityQuery.setSkuIds(skuList);
        commodityQuery.setAppId(appId);
        commodityQuery.setTenantId(tenantId);
        commodityQuery.setTicketType(ticketType);
        return commodityManager.getCommoditys(commodityQuery);
    }

    /**
     * 计算订单总金额
     *
     * @param saleOrder
     */
    private void computeOrderAmount(SaleOrderInfoDTO saleOrder) {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        BigDecimal discountAmount = BigDecimal.valueOf(0);
        BigDecimal accrueAmount = BigDecimal.valueOf(0);
        BigDecimal totalQuantity = BigDecimal.valueOf(0);
        BigDecimal totalExpense = getOrderExpenseTotalAmount(saleOrder.getOrderExpenseInfoList());
        //设置商品明细
        setItemPrice(saleOrder.getItems(), saleOrder.getOrderCouponInfoList(), saleOrder.getOrderPromotionInfoList());
        if (CollectionUtil.isNotEmpty(saleOrder.getItems())) {
            for (SaleOrderItemDTO item : saleOrder.getItems()) {
                totalAmount = totalAmount.add(getBigDecimal(item.getTotalAmount()));
                discountAmount = discountAmount.add(getBigDecimal(item.getDiscountAmount()));
                accrueAmount = accrueAmount.add(getBigDecimal(item.getAccrueAmount()));
                totalQuantity = totalQuantity.add(BigDecimal.valueOf(item.getSkuQuantity() == null ? 0L :
                        item.getSkuQuantity()));
            }
        }
        //应用金额=商品应收+其他费用
        accrueAmount = accrueAmount.add(totalExpense);
        saleOrder.setAccrueAmount(accrueAmount);
        saleOrder.setDiscountAmount(discountAmount);
        saleOrder.setTotalAmount(totalAmount);
        saleOrder.setTotalExpense(totalExpense);
        saleOrder.setQuantity(totalQuantity.longValue());
    }

    /**
     * 计算订单总金额
     *
     * @param saleOrder
     */
    private void computeOrderAmount(SaleOrderInfoDTO saleOrder, List<SaleOrderInfoDTO> subSaleOrders) {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        BigDecimal discountAmount = BigDecimal.valueOf(0);
        BigDecimal accrueAmount = BigDecimal.valueOf(0);
        Long totalQuantity = 0L;
        BigDecimal totalExpense = getOrderExpenseTotalAmount(saleOrder.getOrderExpenseInfoList());
        //设置商品明细
        if (CollectionUtil.isNotEmpty(subSaleOrders)) {
            for (SaleOrderInfoDTO item : subSaleOrders) {
                totalAmount = totalAmount.add(getBigDecimal(item.getTotalAmount()));
                discountAmount = discountAmount.add(getBigDecimal(item.getDiscountAmount()));
                accrueAmount = accrueAmount.add(getBigDecimal(item.getAccrueAmount()));
                if (CollectionUtil.isNotEmpty(item.getItems())) {
                    for (SaleOrderItemDTO d : item.getItems()) {
                        totalQuantity = totalQuantity + getLong(d.getSkuQuantity());
                    }
                }

            }
        }
        //应用金额=商品应收+其他费用
        accrueAmount = accrueAmount.add(totalExpense);
        saleOrder.setAccrueAmount(accrueAmount);
        saleOrder.setDiscountAmount(discountAmount);
        saleOrder.setTotalAmount(totalAmount);
        saleOrder.setTotalExpense(totalExpense);
        saleOrder.setQuantity(totalQuantity);
    }

    private BigDecimal getBigDecimal(BigDecimal value) {
        if (value == null) {
            return BigDecimal.valueOf(0);
        }
        return value;
    }

    /**
     * 获取其他费用合计
     *
     * @param orderExpenseInfoList
     * @return
     */
    private BigDecimal getOrderExpenseTotalAmount(List<OrderExpenseInfoDTO> orderExpenseInfoList) {
        BigDecimal orderExpenseTotalAmount = BigDecimal.valueOf(0);
        if (CollectionUtil.isNotEmpty(orderExpenseInfoList)) {
            orderExpenseInfoList.forEach(ex -> {
                orderExpenseTotalAmount.add(ex.getAmount());
            });
        }
        return orderExpenseTotalAmount;
    }

    /**
     * 设置商品明细总金额，优惠后金额,优惠分摊金额
     *
     * @param items
     * @param orderCouponList    优惠券
     * @param orderPromotionList 活动
     */
    private void setItemPrice(List<SaleOrderItemDTO> items, List<OrderCouponInfoDTO> orderCouponList,
                              List<OrderPromotionInfoDTO> orderPromotionList) {
        BigDecimal totalAmount = new BigDecimal(0);
        if (CollectionUtil.isNotEmpty(items)) {
            items.forEach(item -> {
                BigDecimal price=item.getPrice()==null?new BigDecimal(0):item.getPrice();
                //合计金额
                item.setTotalAmount(price.multiply(BigDecimal.valueOf(item.getSkuQuantity())));
                //TODO 优惠分摊金额
                item.setDiscountAmount(BigDecimal.valueOf(0));
                //优惠后金额
                item.setAccrueAmount(item.getTotalAmount().subtract(item.getDiscountAmount() == null ?
                        BigDecimal.valueOf(0) : item.getDiscountAmount()));
            });
        }
    }

    /**
     * 查看详情
     *
     * @param id
     */
    @SneakyThrows
    @Override
    public SaleOrderInfoResponseDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售订单表,传入ID为空");

        }
        SaleOrderInfoResponseDTO responseDTO = null;
        try {
            Payload<SaleOrderInfoResponseDTO> responseDTOPayload = saleOrderInfoClient.selectById(id);
            if (SUCCESS.equals(responseDTOPayload.getCode())) {
                responseDTO = GeneralConvertUtils.conv(responseDTOPayload.getPayload(), SaleOrderInfoResponseDTO.class);
                OrderOperationRecordRequestQuery query = new OrderOperationRecordRequestQuery();
                query.setOrderId(id);
                query.setOperationType(1);
                List<OrderOperationRecordResponseDTO> orderOperationRecordResponseDTOS =
                        orderOperationRecordClient.listOrderOperationRecords(query);
                responseDTO.setOrderOperationRecordResponseDTOs(orderOperationRecordResponseDTOS);
                //根据商品组织获取具体组织信息,当顶级组织和一级组织部相等时，查询一级组织的名称
                if(responseDTO.getAscriptionOrgId()!=null && !responseDTO.getSellerId().equals(responseDTO.getAscriptionOrgId())) {
                    GroupResultVO group = iamUserService.getGroup(responseDTO.getAscriptionOrgId(), responseDTO.getTenantId(), appRuntimeEnv.getUserId(),
                            appRuntimeEnv.getUsername());
                    if (group != null) {
                        String  orgName = responseDTO.getSellerName()+"("+group.getName()+")";
                        responseDTO.setSellerName(orgName);
                    }
                }
                setDeliveryWareHouse(responseDTO);
            }
        } catch (Exception e) {
            logger.info("订单查询失败:{}", id);
            throw new ApplicationException(ResultEnum.ORDER_QUERY_ERROR);
        }
        return responseDTO;
    }

    /**
     * 设置自提信息
     * @param saleOrder
     */
    private void setDeliveryWareHouse(SaleOrderInfoResponseDTO saleOrder){
        if(null!=saleOrder.getOrderDeliverySelfRaisingInfoResponseDTO() && null!=saleOrder.getOrderDeliverySelfRaisingInfoResponseDTO().getDeliveryWareHouseId()){
            DepotFindPagePostByTypeRequestDTO requestDTO=new DepotFindPagePostByTypeRequestDTO();
            requestDTO.setAppId(saleOrder.getAppId());
            requestDTO.setTenantId(saleOrder.getTenantId());
            List<Long> ids=new ArrayList<>();
            ids.add(saleOrder.getOrderDeliverySelfRaisingInfoResponseDTO().getDeliveryWareHouseId());
            requestDTO.setDepotIdList(ids);
            PayloadDepotFindPagePostResponseDTO responseDTO= depotSdkApi.findDepotPageByType(requestDTO);
            if(null!=responseDTO && SUCCESS.equals(responseDTO.getCode())){
                DepotFindPagePostResponseDTO depotResponse=    responseDTO.getPayload();
                if(depotResponse!=null && depotResponse.getDepotList()!=null){
                    PageBeanDepotFindPagePostResponseDTODepot pageDepotList=depotResponse.getDepotList();
                    if(pageDepotList!=null && CollectionUtil.isNotEmpty(pageDepotList.getContent())){
                        List<DepotFindPagePostResponseDTODepot> list=    pageDepotList.getContent();
                        DepotFindPagePostResponseDTODepot depot=list.get(0);
                        saleOrder.getOrderDeliverySelfRaisingInfoResponseDTO().setDeliveryWareHouseName(depot.getName());
                    }
                }
            }
        }
    }

    /**
     * 查看订单（只返回主表信息）
     *
     * @param id
     */
    @SneakyThrows
    @Override
    public SaleOrderResponseDTO selectSaleOrder(Long id) {
        if (id == null) {
            log.error("查询销售订单表,传入ID为空");

        }
        return GeneralConvertUtils.conv(saleOrderInfoClient.selectSaleOrder(id).getPayload(),
                SaleOrderResponseDTO.class);
    }

    /**
     * 查看订单（只返回主表信息）
     *
     * @param code
     */
    @SneakyThrows
    public SaleOrderResponseDTO selectSaleOrder(Long id, String code) {
        if (StringUtil.isEmpty(code)) {
            log.error("查询销售订单表,传入Code为空");
        }
        SaleOrderInfoRequestQuery query = new SaleOrderInfoRequestQuery();
        query.setId(id);
        query.setCode(code);
        Payload<SaleOrderResponseDTO> payload = saleOrderInfoClient.selectSaleOrder(query);
        if (SUCCESS.equals(payload.getCode())) {
            return GeneralConvertUtils.conv(payload.getPayload(), SaleOrderResponseDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public SaleOrderInfoDTO updateById(Long id, SaleOrderInfoDTO saleOrder) throws Exception {
        if (id == null) {
            log.error("修改销售订单表,传入ID为空");
            throw new ApplicationException(ResultEnum.ORDER_QUERY_ERROR);
        }
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = selectById(id);
        saleOrder.setId(saleOrderInfoResponseDTO.getId());
        saleOrder.setCode(saleOrderInfoResponseDTO.getCode());
        saleOrder.setCustomerId(saleOrderInfoResponseDTO.getCustomerId());
        saleOrder.setCustomerName(saleOrderInfoResponseDTO.getCustomerName());
        if (saleOrderInfoResponseDTO == null) {
            throw new ApplicationException(ResultEnum.ORDER_QUERY_ERROR);
        }
        //获取商品明细信息,从商品域获取
        List<SaleOrderItemDTO> items = getItems(saleOrder);
        //特殊处理一下，后续要删除start
        items.forEach(item -> {
            if (item.getPrice() == null) {
                saleOrder.getItems().forEach(p -> {
                    if (item.getSkuId().equals(p.getSkuId())) {
                        item.setPrice(p.getPrice());
                    }
                });
            }
            //活动id和直供id set进去
            saleOrder.getItems().forEach(sku -> {
                if (item.getSkuId().equals(sku.getSkuId())) {
                    item.setActivitiesId(sku.getActivitiesId());
                    item.setDirectId(sku.getDirectId());
                }
            });
        });
        //删除end
        //验证商品库存和商品是否已下架
        validCommodityStock(items, saleOrder.getItems());
        //校验商品活动,返回参加活动的商品信息
        validSkuActivity(saleOrder);
        //验证促销活动、优惠券
        saleOrder.setItems(getSaleOrderItems(saleOrder, items));

        //设置更新人
        saleOrder.setUpdatedBy(appRuntimeEnv.getUserId().toString());
        setCustPrice(saleOrder);
        //计算订单价格信息
        computeOrderAmount(saleOrder);
        //提交下单
        SaleOrderInfoAddRequestDTO dto = SaleOrderInfoDtoHelper.convertToMiddleSaleOrderInfoRequestDto(saleOrder);
        try {
            Payload<Boolean> result = saleOrderInfoClient.updateById(id, dto);
            if (SUBMIT.equals(saleOrder.getType())) {
                //扣减可售，增加锁定
                incLockDecSalableStock(saleOrder);
                //启动订单流程
                startProcess(saleOrder);
            }
        } catch (Exception e) {
            logger.error("订单创建失败:{}", JsonUtil.bean2JsonString(dto));
            logger.error("订单创建异常:", e);
            throw new ApplicationException(ResultEnum.ORDER_CREATE_ERROR);
        }
        Payload<SaleOrderResponseDTO> payload = saleOrderInfoClient.selectSaleOrder(id);
        SaleOrderResponseDTO responseDTO = GeneralConvertUtils.conv(payload.getPayload(), SaleOrderResponseDTO.class);
        SaleOrderInfoDTO saleOrderInfoDTO = responseDTO.clone(SaleOrderInfoDTO.class);
        saleOrderInfoDTO.setOrderType(saleOrder.getOrderType());
        return saleOrderInfoDTO;
    }

    /**
     * 根据id修改订货计划单
     *
     * @param id
     * @param saleOrderInfoRequestDTO
     * @return
     */
    @Override
    public com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoResponseDTO updatePlanById(Long id,
                                                                                                SaleOrderInfoRequestDTO saleOrderInfoRequestDTO) throws Exception {
        if (id == null) {
            throw new ApplicationException(ResultEnum.ORDER_ID_IS_NULL);
        }
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = selectById(id);
        if (saleOrderInfoResponseDTO == null) {
            throw new ApplicationException(ResultEnum.ORDER_QUERY_ERROR);
        }
        if(StatusCodeEnum.ToTakeOrder.getCode().equals(saleOrderInfoResponseDTO.getStatus())||StatusCodeEnum.Rejected.getCode().equals(saleOrderInfoResponseDTO.getStatus())){
        }else{
            throw new ApplicationException(ResultEnum.PLAN_STATUS_NOT_ToTakeOrder);
        }
        SaleOrderInfoDTO saleOrder = SaleOrderInfoDtoHelper.convertToSaleOrderInfoDTO(saleOrderInfoRequestDTO,
                saleOrderInfoResponseDTO);
        //验证商品是否被授权
        if (!TicketTypeEnum.PLAN.getValue().equals(saleOrderInfoResponseDTO.getTicketType())) {
            if (!validComodityAuth(saleOrder)) {
                throw new ApplicationException(ResultEnum.ORDER_ITEM_NOT_AUTH);
            }
        }

        //获取商品明细信息,从商品域获取
        List<SaleOrderItemDTO> items = getItems(saleOrder);
        //特殊处理一下，后续要删除start
        items.forEach(item -> {
            if (item.getPrice() == null) {
                saleOrder.getItems().forEach(p -> {
                    if (item.getSkuId().equals(p.getSkuId())) {
                        item.setPrice(p.getPrice());
                    }
                });
            }
        });
        //删除end
        //验证商品库存和商品是否已下架
        //validCommodityStock(items, saleOrder.getItems());
        saleOrder.setItems(getSaleOrderItems(saleOrder, items));
        //设置更新人
        saleOrder.setUpdatedBy(appRuntimeEnv.getUserId().toString());
        //计算订单价格信息
        computeOrderAmount(saleOrder);
        //提交下单
        SaleOrderInfoAddRequestDTO dto = SaleOrderInfoDtoHelper.convertToMiddleSaleOrderInfoRequestDto(saleOrder);
        try {
            Payload<Boolean> result = saleOrderInfoClient.updateById(id, dto);
            if (SUBMIT.equals(saleOrderInfoRequestDTO.getType())) {
                //扣减可售，增加锁定
                // incLockDecSalableStock(saleOrder);
                //启动订单流程
                startProcess(saleOrder);
            }
        } catch (Exception e) {
            logger.error("订单创建失败:{}", JsonUtil.bean2JsonString(dto));
            logger.error("订单创建异常:", e);
            throw new ApplicationException(ResultEnum.ORDER_CREATE_ERROR);
        }
        return dto.clone(com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoResponseDTO.class);
    }

    @SneakyThrows
    @Override
    public Boolean updatePayAmount(SaleOrderInfoUpdatePayAmountRequestDTO record) {

        SaleOrderInfoAddRequestDTO saleOrderInfoAddRequestDTO = new SaleOrderInfoAddRequestDTO();
        saleOrderInfoAddRequestDTO.setId(record.getId());
        saleOrderInfoAddRequestDTO.setPayAmount(record.getPayAmount());
        return saleOrderInfoClient.updatePayAmountById(saleOrderInfoAddRequestDTO).getPayload();
    }

    /**
     * 更新订单状态
     *
     * @param saleOrderInfoStatusEditRequestDTO
     */
    @SneakyThrows
    @Override
    public Boolean updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO) {

        com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO statusEditRequestDTO =
                saleOrderInfoStatusEditRequestDTO.clone(com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO.class);
        return saleOrderInfoClient.updateOrderStatus(statusEditRequestDTO).getPayload();
    }

    /**
     * 接单
     *
     * @param saleOrderAccept
     */
    @SneakyThrows
    @Override
    public Boolean acceptSaleOrder(SaleOrderAcceptRequestDTO saleOrderAccept) {
        ToolLinkNextBusinessRequestDTO toolLinkNextBusinessRequestDTO = new ToolLinkNextBusinessRequestDTO();
        toolLinkNextBusinessRequestDTO.setListId(saleOrderAccept.getId());
        Payload<SaleOrderInfoResponseDTO> salesOrderInfoDetailPayload =
                saleOrderInfoClient.selectById(saleOrderAccept.getId());
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO =
                GeneralConvertUtils.conv(salesOrderInfoDetailPayload.getPayload(), SaleOrderInfoResponseDTO.class);
        toolLinkNextBusinessRequestDTO.setVersion(saleOrderInfoResponseDTO.getVersion());
        ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO =
                GeneralConvertUtils.conv(toolLinkClient.nextLinkBusiness(toolLinkNextBusinessRequestDTO).getPayload(),
                        ToolLinkNextBusinessResponseDTO.class);
        com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO =
                new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO();
        saleOrderInfoStatusEditRequestDTO.setStatus(toolLinkNextBusinessResponseDTO.getStatus());
        saleOrderInfoStatusEditRequestDTO.setId(saleOrderAccept.getId());
        return saleOrderInfoClient.updateOrderStatus(saleOrderInfoStatusEditRequestDTO).getPayload();
    }

    /**
     * 取消订单
     *
     * @param saleOrderCancelRequestDTO
     */
    @SneakyThrows
    @Override
    public Boolean cancelSaleOrder(SaleOrderCancelRequestDTO saleOrderCancelRequestDTO) {

        Payload<SaleOrderInfoResponseDTO> salesOrderInfoDetailPayload =
                saleOrderInfoClient.selectById(saleOrderCancelRequestDTO.getId());
        if (salesOrderInfoDetailPayload == null) {
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO =
                GeneralConvertUtils.conv(salesOrderInfoDetailPayload.getPayload(), SaleOrderInfoResponseDTO.class);
        if (saleOrderInfoResponseDTO == null) {
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        if (TicketTypeEnum.PLAN.getValue().equals(saleOrderInfoResponseDTO.getTicketType())) {//订货计划单取消接口
            return cancelPlanOrder(saleOrderCancelRequestDTO, saleOrderInfoResponseDTO);
        } else {
            ToolStatusRequestQuery query = new ToolStatusRequestQuery();
            query.setActionCode(saleOrderCancelRequestDTO.getActionCode());
            query.setAppId(saleOrderCancelRequestDTO.getAppId());
            query.setTenantId(saleOrderCancelRequestDTO.getTenantId());
            query.setListType(String.valueOf(ListTypeEnum.SalesOrder));
            Payload<Integer> codePayload = toolStatusClient.getStatusCode(query);

            if ("0".equals(codePayload.getCode())) {
                Integer code = codePayload.getPayload();
                //清除商品记录
                activitySkuOrderQuantityClient.updateActivitySkuOrder(saleOrderCancelRequestDTO.getId());
                //减少锁定库存 增加可销售库存
                SaleOrderInfoDTO saleOrderInfoDTO = GeneralConvertUtils.conv(saleOrderInfoResponseDTO,
                        SaleOrderInfoDTO.class);
                decLockIncSalableStock(saleOrderInfoDTO);
                com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO = new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO();
                saleOrderInfoStatusEditRequestDTO.setStatus(code);
                saleOrderInfoStatusEditRequestDTO.setId(saleOrderCancelRequestDTO.getId());
                //发mq
                List<SaleOrderInfoDTO> orders = new ArrayList<>();
                SaleOrderInfoDTO saleOrderInfoDTO1 = new SaleOrderInfoDTO();
                saleOrderInfoDTO1.setItems(ObjectCloneUtils.convertList(saleOrderInfoResponseDTO.getItems(),
                        SaleOrderItemDTO.class));
                orders.add(saleOrderInfoDTO1);
                handerMq(orders, 1, null);
                Boolean result = saleOrderInfoClient.updateOrderStatus(saleOrderInfoStatusEditRequestDTO).getPayload();
                //记录操作记录
                OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
                orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
                orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
                orderOperationRecordRequestDTO.setCreatedTime(new Date());
                orderOperationRecordRequestDTO.setOrderId(saleOrderCancelRequestDTO.getId());
                orderOperationRecordRequestDTO.setOperation("取消订单成功");
                orderOperationRecordRequestDTO.setOperationType(1);
                orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
                orderOperationRecordRequestDTO.setRemark(saleOrderCancelRequestDTO.getRemark());
                orderOperationRecordRequestDTO.setActionCode(saleOrderCancelRequestDTO.getActionCode());
                orderOperationRecordRequestDTO.setRadioName(saleOrderCancelRequestDTO.getRadioName());
                saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
                return result;
            } else {
                throw new ApplicationException("取消订单失败");
            }
        }
    }

    /**
     * 关闭订单
     *
     * @param saleOrderCloseRequestDTO
     */
    @SneakyThrows
    @Override
    public Boolean closeSaleOrder(SaleOrderCloseRequestDTO saleOrderCloseRequestDTO) {

        ToolStatusRequestQuery query = new ToolStatusRequestQuery();
        query.setActionCode(saleOrderCloseRequestDTO.getActionCode());
        query.setAppId(saleOrderCloseRequestDTO.getAppId());
        query.setTenantId(saleOrderCloseRequestDTO.getTenantId());
        query.setListType("SalesOrder");
        Payload<Integer> codePayload = toolStatusClient.getStatusCode(query);
        if ("0".equals(codePayload.getCode())) {
            Integer code = codePayload.getPayload();
            com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO = new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO();
            saleOrderInfoStatusEditRequestDTO.setStatus(code);
            saleOrderInfoStatusEditRequestDTO.setId(saleOrderCloseRequestDTO.getId());
            //发mq
            Payload<SaleOrderInfoResponseDTO> salesOrderInfoDetailPayload =
                    saleOrderInfoClient.selectById(saleOrderCloseRequestDTO.getId());
            if (salesOrderInfoDetailPayload == null) {
                throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
            }
            SaleOrderInfoResponseDTO saleOrderInfoResponseDTO =
                    GeneralConvertUtils.conv(salesOrderInfoDetailPayload.getPayload(), SaleOrderInfoResponseDTO.class);
            if (saleOrderInfoResponseDTO == null) {
                throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
            }
            List<SaleOrderInfoDTO> orders = new ArrayList<>();
            SaleOrderInfoDTO saleOrderInfoDTO1 = new SaleOrderInfoDTO();
            saleOrderInfoDTO1.setItems(ObjectCloneUtils.convertList(saleOrderInfoResponseDTO.getItems(),
                    SaleOrderItemDTO.class));
            orders.add(saleOrderInfoDTO1);
            handerMq(orders, 1, null);
            Boolean result = saleOrderInfoClient.updateOrderStatus(saleOrderInfoStatusEditRequestDTO).getPayload();
            //记录操作记录
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(saleOrderCloseRequestDTO.getId());
            orderOperationRecordRequestDTO.setOperation("关闭订单");
            orderOperationRecordRequestDTO.setOperationType(1);
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            orderOperationRecordRequestDTO.setRemark(saleOrderCloseRequestDTO.getRemark());
            saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
            return result;
        } else {
            throw new ApplicationException("关闭订单失败");
        }
    }

    /**
     * 普通订单-支付订单（线上）
     *
     * @param saleOrderPayRequestDTO
     */
    @SneakyThrows
    @Override

    public SaleOrderPayResponseDTO paySaleOrder(SaleOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        logger.info("进入预下单:" + JsonUtil.bean2JsonString(saleOrderPayRequestDTO));
        //保存伙伴信息
        BusinessPartnerResponseDTO businessPartner = new BusinessPartnerResponseDTO();
        //保存订单信息
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = new SaleOrderInfoResponseDTO();
        //1先搜子单表 根据订单 code+id
        SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = new SaleOrderInfoRequestQuery();
        saleOrderInfoRequestQuery.setCode(saleOrderPayRequestDTO.getOrderCode());
        saleOrderInfoRequestQuery.setId(saleOrderPayRequestDTO.getId());
        Payload<SaleOrderResponseDTO> salesOrderInfoDetailPayload =
                saleOrderInfoClient.selectSaleOrder(saleOrderInfoRequestQuery);
        if (SUCCESS.equals(salesOrderInfoDetailPayload.getCode()) && salesOrderInfoDetailPayload.getPayload() != null) {
            saleOrderInfoResponseDTO =
                    GeneralConvertUtils.conv(salesOrderInfoDetailPayload.getPayload(), SaleOrderInfoResponseDTO.class);
            if (saleOrderInfoResponseDTO == null) {
                throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
            }
            //获取伙伴信息
            businessPartner = saleOrderInfoUtils.getBusinessPartner(saleOrderPayRequestDTO.getTenantId(),
                    saleOrderPayRequestDTO.getAppId(), saleOrderInfoResponseDTO.getSellerId());
            if (null == businessPartner) {
                throw new ApplicationException("获取伙伴信息失败");
            }
        } else {
            //2 搜索父订单
            SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO =
                    saleOrderSplitRecordClient.selectById(saleOrderPayRequestDTO.getId());
            Optional.ofNullable(saleOrderSplitRecordResponseDTO).orElseThrow(() -> new ApplicationException("订单不存在"));
            saleOrderInfoResponseDTO = saleOrderSplitRecordResponseDTO.clone(SaleOrderInfoResponseDTO.class);
            saleOrderInfoResponseDTO.setCode(saleOrderSplitRecordResponseDTO.getCode());
            businessPartner = saleOrderInfoUtils.getBusinessPartner(saleOrderInfoResponseDTO.getTenantId(),
                    saleOrderInfoResponseDTO.getAppId(), saleOrderSplitRecordResponseDTO.getSellerId());
            if (null == businessPartner) {
                throw new ApplicationException("获取伙伴信息失败");
            }
        }
        if (!Objects.isNull(saleOrderInfoResponseDTO)) {
            Long partnerId = businessPartner.getId();
            Payload<FinancePayInfoResponseDTO> financePayInfoResponseDTOPayload =
                    financePayInfoClient.getByPartneId(partnerId);
            if (!SUCCESS.equals(financePayInfoResponseDTOPayload.getCode()) || (SUCCESS.equals(financePayInfoResponseDTOPayload.getCode()) && financePayInfoResponseDTOPayload.getPayload() == null)) {
                throw new ApplicationException("未找到收款账户");
            }
            FinancePayInfoResponseDTO financePayInfoResponseDTO =
                    GeneralConvertUtils.conv(financePayInfoResponseDTOPayload.getPayload(),
                            FinancePayInfoResponseDTO.class);
            MerchantConfig merchantConfig = MerchantConfig
                    .builder()
                    .merchantCode(financePayInfoResponseDTO.getMerchantCode()) // 支付中心提供的商户code
                    .middleHost(middleHost) // 支付中心请求地址
                    .middlePayPublicKey(financePayInfoResponseDTO.getPublicKey())
                    .merchantPrivateKey(financePayInfoResponseDTO.getMerchantPrivateKey())
                    .tenantId(partnerId + "") // 租户Id，多租户环境下的租户唯一标识
                    .build();
            logger.info("组装请求参数:{}", JsonUtil.bean2JsonString(merchantConfig));
            PayRequestManager payRequestManager = PayRequestManagerFactory.create(merchantConfig);
            GetPayStatusRequest getPayStatusRequest = new GetPayStatusRequest();
            getPayStatusRequest.setOutOrderNo(saleOrderInfoResponseDTO.getCode());
            getPayStatusRequest.setClientIp(clientIp);
            getPayStatusRequest.setTenantId(partnerId + "");
            getPayStatusRequest.setMerchantCode(financePayInfoResponseDTO.getMerchantCode());

            //组装预下单的参数
            PrePlaceOrderRequest request = new PrePlaceOrderRequest();
            request.setOutOrderNo(saleOrderInfoResponseDTO.getCode());
            request.setAmount(saleOrderInfoResponseDTO.getAccrueAmount());
            request.setBody("商品描述");
            request.setClientIp(clientIp);
            request.setDescription("订单详细信息");
            request.setSubject("商品共" + saleOrderInfoResponseDTO.getQuantity() + "件");
            request.setTenantId(partnerId + "");  //伙伴Id
            request.setMerchantCode(financePayInfoResponseDTO.getMerchantCode()); //商户code
            request.setPayExpireTime(DateUtils.addMinutes(new Date(), addMinute));
            request.setCurrencyType("CNY");
            request.setReturnUrl(appendUrl(financePayInfoResponseDTO.getMerchantCode(),
                    saleOrderInfoResponseDTO.getCode(), partnerId));

            logger.info("组装请求参数2:{}", JsonUtil.bean2JsonString(request));
            SaleOrderPayResponseDTO result = new SaleOrderPayResponseDTO();
            // 设置收银台的地址.
            result.setReturnUrl(request.getReturnUrl());
            //TODO 获取订单状态
            SaleOrderPayResponseDTO saleOrderPayResponseDTO = this.torsionStatus(request, result, getPayStatusRequest
                    , financePayInfoResponseDTO, saleOrderInfoResponseDTO, payRequestManager);
            return saleOrderPayResponseDTO;
        }
        return null;
    }

    /**
     * @Description: 根据参数动态拼接URL, 进行跳转.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/31
     */
    private String appendUrl(String merchantCode, String outPayOrderNo, Long tenantId) {
        StringBuilder sb = new StringBuilder();
        sb.append(onlinePaymentHost);
        sb.append("?");
        sb.append("merchantCode=");
        sb.append(merchantCode);
        sb.append("&");
        sb.append("outPayOrderNo=");
        sb.append(outPayOrderNo);
        sb.append("&");
        sb.append("terminalType=");
        sb.append(4);
        sb.append("&");
        sb.append("tenantId=");
        sb.append(tenantId);
        sb.append("&");
        sb.append("productId=");
        sb.append(1);
        return sb.toString();
    }

    private BusinessPartnerResponseDTO getPartner(String tenanId, Long appId, Long orgId) {
        //获取伙伴ID
        Payload<BusinessPartnerResponseDTO> businessPartnerResponseDTOPayload = null;
        try {
            BusinessPartnerRequestQuery businessPartnerRequestQuery = new BusinessPartnerRequestQuery();
            businessPartnerRequestQuery.setTenantId(tenanId);
            businessPartnerRequestQuery.setAppId(appId);
            businessPartnerRequestQuery.setOrgId(Long.valueOf(orgId));

            businessPartnerResponseDTOPayload = businessPartnerClient.getPartner(businessPartnerRequestQuery);
            logger.info("获取伙伴id:{}", JsonUtil.bean2JsonString(businessPartnerResponseDTOPayload));
            if (!businessPartnerResponseDTOPayload.getCode().equals("0")) {
                throw new ApplicationException("获取伙伴ID失败");
            }
            return GeneralConvertUtils.conv(businessPartnerResponseDTOPayload.getPayload(),
                    BusinessPartnerResponseDTO.class);
        } catch (Exception e) {
            logger.error("获取伙伴ID失败:", e);
            throw new ApplicationException("获取伙伴ID失败");
        }
    }

    /**
     * @Description: 订单发货.
     * @Param: [saleOrderInfoDeliverGoodsRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    @SneakyThrows
    @Override
    public Boolean deliveryGoodsOrder(SaleOrderInfoDeliverGoodsRequestDTO dto) throws Exception {
        log.info("发货--入参: {}", JSON.toJSONString(dto));
        long skuTotalQuantity = dto.getItems().stream().mapToLong(map -> map.getSkuShipmentQuantity()).sum();
        if (skuTotalQuantity == 0) {
            throw new ApplicationException("所有商品的【本次出库数量】之合必须大于0");
        }
        //查询订单信息
        SaleOrderResponseDTO saleOrderResponse = selectSaleOrder(dto.getSaleOrderId());
        String orderNo = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER_OUT_CODE.getType(),
                IdentifierTypeEnum.ORDER_OUT_CODE.getPrefix()
                        + DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.ORDER_OUT_CODE.getLen());//出库单编号
        SaleOrderInfoDeliverGoodsMiddleRequestDTO requestDTO =
                dto.clone(SaleOrderInfoDeliverGoodsMiddleRequestDTO.class);
        requestDTO.setItems(ObjectCloneUtils.convertList(dto.getItems(), SaleOrderItemMiddleRequestDTO.class));//出库单商品信息
        requestDTO.setOrderDeliveryInfo(dto.getOrderDeliveryInfo().clone(OrderDeliveryInfoRequestDTO.class));//出库单物流信息
        requestDTO.setOrderDeliveryConsigneeInfo(dto.getOrderDeliveryConsigneeInfo().clone(OrderDeliveryConsigneeInfoMiddleRequestDTO.class));//出库单送货地址信息
        requestDTO.setSaleOutTaskCode(orderNo);
        requestDTO.setCreatedBy(appRuntimeEnv.getUsername());
        requestDTO.setUpdateBy(appRuntimeEnv.getUsername());
        requestDTO.setIsolationId(appRuntimeEnv.getTopOrganization().getId() + "");//顶级组织id
        requestDTO.setAscriptionOrgId(saleOrderResponse.getAscriptionOrgId());//一级组织ID
        saleOrderInfoClient.deliveryGoodsOrder(requestDTO).getPayload();
        //查询更新后的订单信息
        SaleOrderResponseDTO saleOrderResponseDTO = selectSaleOrder(dto.getSaleOrderId());
        log.info("发货--总数量数量: {}--已发货数量: {}", saleOrderResponseDTO.getQuantity(),
                saleOrderResponseDTO.getTotalQuantity());
        Long unTotalQuantity =
                saleOrderResponseDTO.getQuantity().longValue() - saleOrderResponseDTO.getTotalQuantity().longValue();
        //未发货数量
        LinkBusinessRequestDTO linkBusinessRequestDTO = new LinkBusinessRequestDTO();
        linkBusinessRequestDTO.setId(saleOrderResponseDTO.getId());
        if (ObjectUtils.equals(unTotalQuantity, 0L)) {//未发货数量为0 则全部发完货
            linkBusinessRequestDTO.setListType("SalesOrder");
            linkBusinessRequestDTO.setBusinessCode(LinkBusinessCodeEnum.Delivery.name());
            toolLinkService.nextLinkBusiness(linkBusinessRequestDTO);//业务链路下一步
            //记录发货操作
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(dto.getId());
            orderOperationRecordRequestDTO.setOperation("全部发货成功");
            orderOperationRecordRequestDTO.setOperationType(1);
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            orderOperationRecordRequestDTO.setRemark(dto.getRemark());
            saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
            return true;
        } else {//未发完货 则获取状态更新
            linkBusinessRequestDTO.setActionCode("Delivery");
            linkBusinessRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            linkBusinessRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            JSONObject json = new JSONObject();
            json.put("deliveryGoodsQuantity", unTotalQuantity);
            linkBusinessRequestDTO.setRuleData(json.toString());
            Integer status = toolLinkService.getLinkBusinessStatus(linkBusinessRequestDTO);
            return toolLinkService.updateSaleOrderStatus(saleOrderResponseDTO.getId(), status);
        }
    }

    /**
     * @Description: 调用调度域订单配货接口.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/14
     */
    private Boolean saleOrder(SaleOrderInfoDeliverGoodsRequestDTO dto) throws Exception {
        SaleOrderPostRequestDTO saleOrderPostRequestDTO = saleOrderConverterDTO(dto);
        log.info("调度域配货接口传入参数: {}", JSON.toJSONString(saleOrderPostRequestDTO));
        PayloadSaleOrderPostResponseDTO saleOrderPostResponseDTO =
                scheduleDomainSdkApi.scheduleDomainSaleOrder(saleOrderPostRequestDTO);
        log.info("调度域配货接口返回信息: {}", JSON.toJSONString(saleOrderPostResponseDTO));
        return Objects.isNull(saleOrderPostResponseDTO.getPayload()) ? Boolean.FALSE :
                saleOrderPostResponseDTO.getPayload().getResult();
    }

    /**
     * @Description: 调用调度域发货接口.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/14
     */
    private Boolean deliverGoods(SaleOrderInfoDeliverGoodsRequestDTO dto) throws Exception {
        DeliverGoodsPostRequestDTO deliverGoodsPostRequestDTO = deliverGoodsConverterDTO(dto);
        log.info("调度域发货接口传入参数: {}", JSON.toJSONString(deliverGoodsPostRequestDTO));
        PayloadDeliverGoodsPostResponseDTO deliverGoods =
                scheduleDomainSdkApi.scheduleDomainDeliverGoods(deliverGoodsPostRequestDTO);
        log.info("调度域发货接口返回信息: {}", JSON.toJSONString(deliverGoods));
        return Objects.isNull(deliverGoods.getPayload()) ? Boolean.FALSE : deliverGoods.getPayload().getResult();
    }

    /**
     * @Description: 订单发货DTO转换..
     * @Param: [addRequestDTO]
     * @return: com.deepexi.domain.schedule.domain.sale.DeliverGoodsPostRequestDTO
     * @Author: SongTao
     * @Date: 2020/7/14
     */
    private DeliverGoodsPostRequestDTO deliverGoodsConverterDTO(SaleOrderInfoDeliverGoodsRequestDTO deliverGoodsRequestDTO) {
        DeliverGoodsPostRequestDTO deliverGoodsPostResponseDTO = new DeliverGoodsPostRequestDTO();
        if (Objects.isNull(deliverGoodsRequestDTO)) {
            return deliverGoodsPostResponseDTO;
        }
        List<DeliverGoodsPostRequestDTOSaleOrderSkuWholeDTO> list = Lists.newArrayList();
        DeliverGoodsPostRequestDTOSaleOrderSkuWholeDTO dto = new DeliverGoodsPostRequestDTOSaleOrderSkuWholeDTO();
        deliverGoodsPostResponseDTO.setAppId(deliverGoodsRequestDTO.getAppId());//appId
        deliverGoodsPostResponseDTO.setTenantId(deliverGoodsRequestDTO.getTenantId());//租户Id
        deliverGoodsPostResponseDTO.setSynOrder(Boolean.FALSE);//是否同步订单状态
        dto.setAppId(deliverGoodsRequestDTO.getAppId());//Body 参数的appId
        dto.setTenantId(deliverGoodsRequestDTO.getTenantId());
        dto.setSaleOrderId(deliverGoodsRequestDTO.getSaleOrderId());//订单ID
        dto.setDeliveryType(deliverGoodsRequestDTO.getDeliveryType());//发货方式
        dto.setExtendNo(deliverGoodsRequestDTO.getSaleOrderCode());//编码
        dto.setPushStatus(1);//发货未分配库存 先写死
        if (Objects.nonNull(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo())) {//收货信息
            dto.setDeliveryConsignee(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getConsignee());//收货人
            dto.setDeliveryMobilePhone(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getMobile());//收货电话号码
            dto.setProvinceId(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getProvinceName());//收货省份名称
            dto.setCityId(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getCityName());//收货城市名称
            dto.setDistrictId(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getAreaName());//收货区域
            dto.setStreetId(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getStreetName());//收货街道信息
        }
        if (CollectionUtil.isNotEmpty(deliverGoodsRequestDTO.getItems())) {//商品信息
            List<DeliverGoodsPostRequestDTOSaleOrderSkuWholeDTOSkuDTO> skuDTOList =
                    deliverGoodsRequestDTO.getItems().stream().map(map -> {
                        DeliverGoodsPostRequestDTOSaleOrderSkuWholeDTOSkuDTO skuDTO =
                                new DeliverGoodsPostRequestDTOSaleOrderSkuWholeDTOSkuDTO();
                        skuDTO.setSkuId(map.getSkuId());//商品ID
                        skuDTO.setSkuName(map.getSkuName());//商品名称
                        skuDTO.setSkuPrice(map.getPrice());//商品价格
                        skuDTO.setSkuQty(Integer.valueOf(Math.toIntExact(map.getSkuQuantity())));//商品总数量
                        skuDTO.setShopId(map.getShopId());//店铺ID
                        skuDTO.setChannelId(1L);//渠道ID
                        return skuDTO;
                    }).collect(Collectors.toList());
            dto.setSku(skuDTOList);
        }
        list.add(dto);
        deliverGoodsPostResponseDTO.setBody(list);
        return deliverGoodsPostResponseDTO;
    }

    /**
     * @Description: 订单配货DTO转换.
     * @Param: [saleOrderInfoAddRequestDTO]
     * @return: com.deepexi.domain.schedule.domain.sale.SaleOrderPostRequestDTO
     * @Author: SongTao
     * @Date: 2020/7/14
     */
    private SaleOrderPostRequestDTO saleOrderConverterDTO(SaleOrderInfoDeliverGoodsRequestDTO deliverGoodsRequestDTO) {
        SaleOrderPostRequestDTO saleOrderPostRequestDTO = new SaleOrderPostRequestDTO();
        if (Objects.isNull(deliverGoodsRequestDTO)) {
            return saleOrderPostRequestDTO;
        }
        List<SaleOrderPostRequestDTOSaleOrderSkuWholeDTO> list = Lists.newArrayList();
        SaleOrderPostRequestDTOSaleOrderSkuWholeDTO dto = new SaleOrderPostRequestDTOSaleOrderSkuWholeDTO();
        saleOrderPostRequestDTO.setAppId(deliverGoodsRequestDTO.getAppId());//appId
        saleOrderPostRequestDTO.setTenantId(deliverGoodsRequestDTO.getTenantId());//租户Id
        saleOrderPostRequestDTO.setSynOrder(Boolean.FALSE);//是否同步订单状态
        dto.setAppId(deliverGoodsRequestDTO.getAppId());//Body 参数的appId
        dto.setTenantId(deliverGoodsRequestDTO.getTenantId());
        dto.setSaleOrderId(deliverGoodsRequestDTO.getSaleOrderId());//订单ID
        dto.setDeliveryType(deliverGoodsRequestDTO.getDeliveryType());//发货方式
        dto.setExtendNo(deliverGoodsRequestDTO.getSaleOrderCode());//编码
        dto.setPushStatus(1);//发货未分配库存 先写死
        if (Objects.nonNull(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo())) {//收货信息
            dto.setDeliveryConsignee(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getConsignee());//收货人
            dto.setDeliveryMobilePhone(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getMobile());//收货电话号码
            dto.setProvinceId(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getProvinceName());//收货省份名称
            dto.setCityId(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getCityName());//收货城市名称
            dto.setDistrictId(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getAreaName());//收货区域
            dto.setStreetId(deliverGoodsRequestDTO.getOrderDeliveryConsigneeInfo().getStreetName());//收货街道信息
        }
        if (CollectionUtil.isNotEmpty(deliverGoodsRequestDTO.getItems())) {//商品信息
            List<SaleOrderPostRequestDTOSaleOrderSkuWholeDTOSkuDTO> skuDTOList =
                    deliverGoodsRequestDTO.getItems().stream().map(map -> {
                        SaleOrderPostRequestDTOSaleOrderSkuWholeDTOSkuDTO skuDTO =
                                new SaleOrderPostRequestDTOSaleOrderSkuWholeDTOSkuDTO();
                        skuDTO.setSkuId(map.getSkuId());//商品ID
                        skuDTO.setSkuName(map.getSkuName());//商品名称
                        skuDTO.setSkuPrice(map.getPrice());//商品价格
                        skuDTO.setSkuQty(Integer.valueOf(Math.toIntExact(map.getSkuQuantity())));//商品总数量
                        skuDTO.setShopId(map.getShopId());//店铺ID
                        skuDTO.setChannelId(1L);//渠道ID
                        return skuDTO;
                    }).collect(Collectors.toList());
            dto.setSku(skuDTOList);
        }
        list.add(dto);
        saleOrderPostRequestDTO.setBody(list);
        return saleOrderPostRequestDTO;
    }

    /**
     * 接单
     *
     * @param saleOrderReceivingRequestDTO
     */
    @Override
    public Boolean orderReceiving(SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception {
        SaleOrderResponseDTO saleOrderResponseDTO = selectSaleOrder(saleOrderReceivingRequestDTO.getId());
        //接单状态（4未接单 16已接单）
        Integer integer = saleOrderResponseDTO.getAcceptStatus();
        if (!ObjectUtils.equals(integer, 16)) {
            com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO dto =
                    new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO();
            dto.setId(saleOrderReceivingRequestDTO.getId());
            dto.setAcceptStatus(StatusCodeEnum.TakedOrder.getCode());
            saleOrderInfoClient.updateMainOrderById(saleOrderReceivingRequestDTO.getId(), dto);
            //接单
            toolActionExcutor.excute(LinkBusinessCodeEnum.ArtificialOrder.name(), ListTypeEnum.SalesOrder.name(),
                    saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTenantId(), saleOrderResponseDTO.getAppId());
            //记录接单操作
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(saleOrderReceivingRequestDTO.getId());
            orderOperationRecordRequestDTO.setOperation("接单成功");
            orderOperationRecordRequestDTO.setOperationType(1);
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            orderOperationRecordRequestDTO.setRemark(saleOrderReceivingRequestDTO.getRemark());
            saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
            return true;
        } else {
            //已接单
            throw new ApplicationException(ResultEnum.PENDING_ORDERS);
        }
    }

    /**
     * 审核
     *
     * @param saleOrderReceivingRequestDTO
     * @return
     * @throws Exception
     */
    @Override
    public Boolean orderToExamine(SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception {
        SaleOrderResponseDTO saleOrderResponseDTO = selectSaleOrder(saleOrderReceivingRequestDTO.getId());
        //接单状态（13未审核 14已审核）
        Integer integer = saleOrderResponseDTO.getVerifyStatus();
        if (!ObjectUtils.equals(integer, 14)) {


            LinkBusinessRequestDTO linkNextBusinessRequestDTO = new LinkBusinessRequestDTO();
            linkNextBusinessRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            linkNextBusinessRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            linkNextBusinessRequestDTO.setBusinessCode(LinkBusinessCodeEnum.TakeOrderApproval.name());
            linkNextBusinessRequestDTO.setId(saleOrderResponseDTO.getId());
            linkNextBusinessRequestDTO.setActionCode(ActionCodeEnum.Approval.name());
            linkNextBusinessRequestDTO.setListType(ListTypeEnum.SalesOrder.name());


            String code = toolLinkService.nextLinkBusiness(linkNextBusinessRequestDTO);
            if (!LinkBusinessCodeEnum.TakeOrderApproval.name().equals(code)) {
                com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO saleOrderInfoRequestDTO =
                        new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO();
                SaleOrderResponseDTO order = selectSaleOrder(saleOrderReceivingRequestDTO.getId());
                saleOrderInfoRequestDTO.setStatus(order.getStatus());
                saleOrderInfoRequestDTO.setVerifyStatus(14);
                saleOrderInfoClient.updateMainOrderById(saleOrderReceivingRequestDTO.getId(), saleOrderInfoRequestDTO);
                if (StatusCodeEnum.ToDelivery.getCode().equals(order.getStatus())) {
                    //待发货状态下把网批订单同步到OMS
                    saleOrderSendService.sendOrderToGl(saleOrderResponseDTO.getId());
                }
            }
            //记录审核操作
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(saleOrderReceivingRequestDTO.getId());
            orderOperationRecordRequestDTO.setOperation("接单审核成功");
            orderOperationRecordRequestDTO.setOperationType(1);
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            orderOperationRecordRequestDTO.setRemark(saleOrderReceivingRequestDTO.getRemark());
            saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);

            return true;
        } else {
            //已审核
            throw new ApplicationException(ResultEnum.ToBeReviewed_ORDERS);
        }
    }

    /***
     * 审核驳回
     * @param saleOrderRejectedRequestDTO
     * @return
     * @throws Exception
     */
    @Override
    public Boolean orderRejectedToExamine(SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception {
        SaleOrderResponseDTO saleOrderResponseDTO = selectSaleOrder(saleOrderRejectedRequestDTO.getId());
        LinkBusinessRequestDTO linkNextBusinessRequestDTO = new LinkBusinessRequestDTO();
        linkNextBusinessRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
        linkNextBusinessRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
        linkNextBusinessRequestDTO.setBusinessCode(LinkBusinessCodeEnum.TakeOrderApproval.name());
        linkNextBusinessRequestDTO.setId(saleOrderResponseDTO.getId());
        linkNextBusinessRequestDTO.setActionCode(ActionCodeEnum.Reject.name());
        linkNextBusinessRequestDTO.setListType(ListTypeEnum.SalesOrder.name());
        toolLinkService.nextLinkBusiness(linkNextBusinessRequestDTO);
        //记录审批驳回操作
        OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
        orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
        orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
        orderOperationRecordRequestDTO.setCreatedTime(new Date());
        orderOperationRecordRequestDTO.setOrderId(saleOrderRejectedRequestDTO.getId());
        orderOperationRecordRequestDTO.setOperation("审批驳回成功");
        orderOperationRecordRequestDTO.setOperationType(1);
        orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
        orderOperationRecordRequestDTO.setRemark(saleOrderRejectedRequestDTO.getRemark());
        orderOperationRecordRequestDTO.setActionCode(saleOrderRejectedRequestDTO.getActionCode());
        saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
        return true;
    }

    @Override
    public Boolean orderRejected(SaleOrderRejectedRequestDTO saleOrderRejectedRequestDTO) throws Exception {
        SaleOrderResponseDTO saleOrderResponseDTO = selectSaleOrder(saleOrderRejectedRequestDTO.getId());
        LinkBusinessRequestDTO businessRequestDTO = new LinkBusinessRequestDTO();
        businessRequestDTO.setActionCode("TakeOrderReject");
        businessRequestDTO.setId(saleOrderRejectedRequestDTO.getId());
        businessRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
        businessRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
        businessRequestDTO.setRemark(saleOrderRejectedRequestDTO.getRemark());
        Integer status = toolLinkService.getLinkBusinessStatus(businessRequestDTO);
        //记录接单驳回操作
        OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
        orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
        orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
        orderOperationRecordRequestDTO.setCreatedTime(new Date());
        orderOperationRecordRequestDTO.setOrderId(saleOrderRejectedRequestDTO.getId());
        orderOperationRecordRequestDTO.setOperation("接单驳回成功");
        orderOperationRecordRequestDTO.setOperationType(1);
        orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
        orderOperationRecordRequestDTO.setRemark(saleOrderRejectedRequestDTO.getRemark());
        orderOperationRecordRequestDTO.setActionCode(saleOrderRejectedRequestDTO.getActionCode());
        saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
        return toolLinkService.updateSaleOrderStatus(saleOrderRejectedRequestDTO.getId(), status);
    }

    private GroupResultVO getUserGroup(SaleOrderResponseDTO saleOrderResponseDTO) {
        logger.info("获取组织id参数:{}", JsonUtil.bean2JsonString(saleOrderResponseDTO));
        GroupResultVO payloadListGetUserIdGroupResultVO = iamUserService.getGroup(saleOrderResponseDTO.getSellerId(),
                saleOrderResponseDTO.getTenantId(), saleOrderResponseDTO.getSellerId(),
                saleOrderResponseDTO.getBuyerName());
        logger.info("获取组织id结果:{}", JsonUtil.bean2JsonString(payloadListGetUserIdGroupResultVO));
        if (payloadListGetUserIdGroupResultVO != null) {
            return payloadListGetUserIdGroupResultVO;
        }
        return null;
    }

    /**
     * 在线支付回调
     *
     * @param payCallbackRequestDTO
     * @return
     */
    @Override
    public Boolean onlinePayCallBack(PayCallbackRequestDTO payCallbackRequestDTO) throws Exception {
        OrderPayBackRequestQuery orderPayBackRequestQuery = new OrderPayBackRequestQuery();
        orderPayBackRequestQuery.setOutPayOrderNo(payCallbackRequestDTO.getOutPayOrderNo());
        orderPayBackRequestQuery.setPayTradeNo(payCallbackRequestDTO.getPayTradeNo());
        if (orderPayBackClient.listOrderPayBacks(orderPayBackRequestQuery).size() > 0) {
            throw new ApplicationException("支付回调信息已存在");
        }
        //根据订单号查询父订单信息
        Payload<SaleOrderSplitRecordResponseDTO> payload =
                saleOrderSplitRecordClient.selectByCode(payCallbackRequestDTO.getOutPayOrderNo());
        if (SUCCESS.equals(payload.getCode()) && payload.getPayload() != null) {
            SaleOrderSplitRecordResponseDTO recordResponseDTO = GeneralConvertUtils.conv(payload.getPayload(),
                    SaleOrderSplitRecordResponseDTO.class);
            Payload<List<SaleOrderResponseDTO>> parentPayload =
                    saleOrderInfoClient.selectSaleOrderByParentCode(payCallbackRequestDTO.getOutPayOrderNo());
            List<SaleOrderResponseDTO> list = GeneralConvertUtils.convert2List(parentPayload.getPayload(),
                    SaleOrderResponseDTO.class);
            if (CollectionUtil.isNotEmpty(list)) {
                //回调父订单信息
                callBackParentSaleOrderInfo(payCallbackRequestDTO, recordResponseDTO, list);
            }
        } else {
            //根据订单号直接查询子订单信息
            Payload<SaleOrderResponseDTO> saleOrderPayload =
                    saleOrderInfoClient.selectSaleOrder(payCallbackRequestDTO.getOutPayOrderNo());
            if (SUCCESS.equals(saleOrderPayload.getCode()) && saleOrderPayload.getPayload()!=null) {
                //回调子订单信息
                callBackSaleOrderInfo(payCallbackRequestDTO, GeneralConvertUtils.conv(saleOrderPayload.getPayload(),
                        SaleOrderResponseDTO.class));
            } else {
                //根据订单号查询订单信息
                Payload<SalePickGoodsInfoResponseDTO> payloadSalePick =
                        salePickGoodsInfoClient.getSalePickGoodsInfoByCode(payCallbackRequestDTO.getOutPayOrderNo());
                if (SUCCESS.equals(payload.getCode()) && null!=payloadSalePick.getPayload()) {

                    SalePickGoodsInfoResponseDTO saleOrderResponseDTO =
                            GeneralConvertUtils.conv(payloadSalePick.getPayload(), SalePickGoodsInfoResponseDTO.class);
                    if (saleOrderResponseDTO != null) {
                        callBackSalePickGoodsInfo(payCallbackRequestDTO, saleOrderResponseDTO);
                    }
                } else //还款单
                {
                    FinanceCreditRepaymentBillResponseDTO bill =
                            getFinanceCreditBill(payCallbackRequestDTO.getOutPayOrderNo());
                    if (bill != null) {
                        if(bill.getStatus().equals(34) || bill.getStatus().equals(24)){
                            throw new ApplicationException("还款失败,该账单已还款");
                        }
                        //还款单回调
                        callBackFinanceRePayment(payCallbackRequestDTO, bill);
                    }else{
                        throw new ApplicationException("未找到账单信息");
                    }
                }
            }

        }
        //保存回调的数据
        OrderPayBackRequestDTO orderPayBackRequestDTO = payCallbackRequestDTO.clone(OrderPayBackRequestDTO.class);
        OrderPayBackResponseDTO orderPayBackResponseDTO = orderPayBackClient.insert(orderPayBackRequestDTO);
        return true;
    }

    /**
     * 根据code获取信用账单
     *
     * @param code
     * @return
     */
    private FinanceCreditRepaymentBillResponseDTO getFinanceCreditBill(String code) throws Exception {
        FinanceCreditRepaymentBillRequestQuery query = new FinanceCreditRepaymentBillRequestQuery();
        query.setCode(code);
        Payload<List<FinanceCreditRepaymentBillResponseDTO>> billPayload =
                financeCreditRepaymentBillClient.listFinanceCreditRepaymentBills(query);
        if (SUCCESS.equals(billPayload.getCode())) {
            List<FinanceCreditRepaymentBillResponseDTO> list =
                    GeneralConvertUtils.convert2List(billPayload.getPayload(),
                            FinanceCreditRepaymentBillResponseDTO.class);
            if (CollectionUtil.isNotEmpty(list)) {
                return list.get(0);
            }
        }
        return null;
    }

    /**
     * 提货计划订单回调
     *
     * @param payCallbackRequestDTO
     */
    private void callBackSalePickGoodsInfo(PayCallbackRequestDTO payCallbackRequestDTO,
                                           SalePickGoodsInfoResponseDTO saleOrderResponseDTO) throws Exception {

        //更新订单状态
        ToolStatusRequestQuery query = new ToolStatusRequestQuery();
        query.setActionCode(ActionCodeEnum.OrderCollection.name());
        query.setAppId(saleOrderResponseDTO.getAppId());
        query.setTenantId(saleOrderResponseDTO.getTenantId());
        query.setListType("SalesOrder");
        //支付金额=已支付金额+支付回调的金额
        BigDecimal payAmount = saleOrderResponseDTO.getPayAmount().add(payCallbackRequestDTO.getBuyerPayAmount());
        if (saleOrderResponseDTO.getTotalGoodsMoney().compareTo(payAmount) < 0) {
            payAmount = saleOrderResponseDTO.getTotalGoodsMoney();
        }
        HashMap<String, BigDecimal> map = new HashMap();
        map.put("collectionAmount", payAmount);
        map.put("totalAmount", saleOrderResponseDTO.getTotalGoodsMoney());
        query.setRuleData(JsonUtil.bean2JsonString(map));
        Payload<Integer> codePayload = toolStatusClient.getStatusCode(query);


        //更新支付金额
        SalePickGoodsInfoAmountEditDTO saleOrderInfoAmountEditDTO = new SalePickGoodsInfoAmountEditDTO();
        saleOrderInfoAmountEditDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
        saleOrderInfoAmountEditDTO.setId(saleOrderResponseDTO.getId());
        saleOrderInfoAmountEditDTO.setStatus(codePayload.getPayload());
        saleOrderInfoAmountEditDTO.setTenantId(saleOrderResponseDTO.getTenantId());
        saleOrderInfoAmountEditDTO.setAppId(saleOrderResponseDTO.getAppId());
        saleOrderInfoAmountEditDTO.setVersion(saleOrderResponseDTO.getVersion());
        if (!salePickGoodsInfoClient.updateOrderAmount(saleOrderInfoAmountEditDTO).getPayload()) {
            throw new ApplicationException("更新支付金额失败");
        }


        //组织id查收款银行信息
        FinanceBankAccountRequestQuery financeBankAccountRequestQuery = new FinanceBankAccountRequestQuery();
        financeBankAccountRequestQuery.setAppId(saleOrderResponseDTO.getAppId());
        financeBankAccountRequestQuery.setTenantId(saleOrderResponseDTO.getTenantId());
        financeBankAccountRequestQuery.setIsProceedsAccount(0);
        GroupResultVO groupResultVO = getUserGroup(saleOrderResponseDTO.clone(SaleOrderResponseDTO.class));
        Long orgId = OrganizationUtils.getOrganizationByLevel(groupResultVO.getIdPath(), 2);
        BusinessPartnerResponseDTO partnerResponseDTO = getPartner(saleOrderResponseDTO.getTenantId(),
                saleOrderResponseDTO.getAppId(), orgId);
        financeBankAccountRequestQuery.setIsolationId(orgId + "");
        Payload<List<FinanceBankAccountResponseDTO>> financeBankAccountPayload =
                financeBankAccountApi.listByOrganIds(financeBankAccountRequestQuery);
        List<FinanceBankAccountResponseDTO> financeBankAccountResponseDTOS =
                GeneralConvertUtils.convert2List(financeBankAccountPayload.getPayload(),
                        FinanceBankAccountResponseDTO.class);

        List<FinanceBankAccountResponseDTO> financeBankAccountResponseDTOList =
                financeBankAccountResponseDTOS.stream().filter(e -> e.getIsProceedsAccount() == 0).collect(Collectors.toList());
        if (financeBankAccountResponseDTOList.size() == 0) {
            throw new ApplicationException("支付异常");
        }
        if (CollectionUtil.isNotEmpty(financeBankAccountResponseDTOList)) {
            FinanceBankAccountResponseDTO financeBankAccountResponseDTO = financeBankAccountResponseDTOS.get(0);

            //收款单对象
            FinanceCollectionAdminRequestDTO financeCollectionAdminRequestDTO = new FinanceCollectionAdminRequestDTO();
            financeCollectionAdminRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            financeCollectionAdminRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            financeCollectionAdminRequestDTO.setIsPaymentRecords(0);
            financeCollectionAdminRequestDTO.setCustomerId(partnerResponseDTO.getId());
            financeCollectionAdminRequestDTO.setCustomerName(partnerResponseDTO.getName());
            financeCollectionAdminRequestDTO.setHandlersId(saleOrderResponseDTO.getHandler());
            financeCollectionAdminRequestDTO.setHandlersName(saleOrderResponseDTO.getHandlerName());
            financeCollectionAdminRequestDTO.setAccountId(financeBankAccountResponseDTO.getId());
//            financeCollectionAdminRequestDTO.setAccountName(financeBankAccountResponseDTO.getName());
//            financeCollectionAdminRequestDTO.setAccountNumber(financeBankAccountResponseDTO.getBankAccount());
            financeCollectionAdminRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getType(), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getPrefix(), DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getLen()));
            financeCollectionAdminRequestDTO.setType(0);
            financeCollectionAdminRequestDTO.setWay(2);
            financeCollectionAdminRequestDTO.setReceiptsTime(new Date());
            financeCollectionAdminRequestDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financeCollectionAdminRequestDTO.setSettlementId(partnerResponseDTO.getId());
            financeCollectionAdminRequestDTO.setSettlementName(partnerResponseDTO.getName());
            financeCollectionAdminRequestDTO.setIsolationId(orgId + "");

            List<FinanceCollectionOrderAdminRequesDTO> financeCollectionOrderAdminRequesDTOList = new ArrayList<>();
            FinanceCollectionOrderAdminRequesDTO financeCollectionOrderAdminRequesDTO =
                    new FinanceCollectionOrderAdminRequesDTO();
            financeCollectionOrderAdminRequesDTO.setOrderCode(saleOrderResponseDTO.getPickGoodsCode());
            financeCollectionOrderAdminRequesDTO.setOrderId(saleOrderResponseDTO.getId());
            financeCollectionOrderAdminRequesDTO.setSettlementAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financeCollectionOrderAdminRequesDTO.setType(0);
            financeCollectionOrderAdminRequesDTOList.add(financeCollectionOrderAdminRequesDTO);

            financeCollectionAdminRequestDTO.setDatas(financeCollectionOrderAdminRequesDTOList);

            //支付流水对象
            FinancePaymentRecordsRequestDTO financePaymentRecordsRequestDTO = new FinancePaymentRecordsRequestDTO();
            financePaymentRecordsRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            financePaymentRecordsRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            financePaymentRecordsRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getType(),
                    IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getPrefix()
                    , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getLen()));
            financePaymentRecordsRequestDTO.setOrderId(saleOrderResponseDTO.getId());
            financePaymentRecordsRequestDTO.setOrderCode(saleOrderResponseDTO.getPickGoodsCode());
            financePaymentRecordsRequestDTO.setCustomerName(saleOrderResponseDTO.getCustomerName());
            financePaymentRecordsRequestDTO.setPayType(2);
            financePaymentRecordsRequestDTO.setReceiptsTime(payCallbackRequestDTO.getPaySuccessTime());
            financePaymentRecordsRequestDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financePaymentRecordsRequestDTO.setIsolationId(orgId + "");
//            financePaymentRecordsRequestDTO.setProceedsAccount(financeBankAccountResponseDTO.getName());
//            financePaymentRecordsRequestDTO.setProceedsBankAccount(financeBankAccountResponseDTO.getBankAccount());
            financePaymentRecordsRequestDTO.setPayChannel(this.codeTranNumber(payCallbackRequestDTO.getPayChannelCode()));
            financePaymentRecordsRequestDTO.setOrderType(1);
            financePaymentRecordsRequestDTO.setOrgId(saleOrderResponseDTO.getAscriptionOrgId());

            //生成支付流水、收款单
            FinanceCollectionPaymentRecordsDTO financeCollectionPaymentRecordsDTO =
                    new FinanceCollectionPaymentRecordsDTO();
            financeCollectionPaymentRecordsDTO.setFinanceCollectionAdminRequestDTO(financeCollectionAdminRequestDTO);
            financeCollectionPaymentRecordsDTO.setFinancePaymentRecordsRequestDTO(financePaymentRecordsRequestDTO);
            Payload<Boolean> financePayload =
                    financeCollectionApi.addPaymentRecords(financeCollectionPaymentRecordsDTO, 0);

            if (!financePayload.getPayload()) {
                throw new ApplicationException("生成支付流水、收款单失败");
            }
        }
        //更新提货状态为待确认
        com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO statusEditRequestDTO =
                new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO();
        statusEditRequestDTO.setStatus(StatusCodeEnum.ToConfirm.getCode());
        statusEditRequestDTO.setId(saleOrderResponseDTO.getId());
        statusEditRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
        statusEditRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
        salePickGoodsInfoClient.updateOrderStatus(statusEditRequestDTO);
        saleOrderInfoUtils.updatePaymentType(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getPickGoodsCode(), 1
                , 2);
    }

    /**
     * 还款单回调
     *
     * @param payCallbackRequestDTO
     */
    private void callBackFinanceRePayment(PayCallbackRequestDTO payCallbackRequestDTO,
                                          FinanceCreditRepaymentBillResponseDTO bill) throws Exception {

        if (payCallbackRequestDTO.getBuyerPayAmount().compareTo(bill.getAmount()) < 0) {
            throw new ApplicationException("支付金额与账单金额不一致");
        }
        //组织id查收款银行信息
        FinanceBankAccountRequestQuery financeBankAccountRequestQuery = new FinanceBankAccountRequestQuery();
        financeBankAccountRequestQuery.setAppId(bill.getAppId());
        financeBankAccountRequestQuery.setTenantId(bill.getTenantId());
        financeBankAccountRequestQuery.setIsProceedsAccount(0);
        BusinessPartnerRequestQuery partnerRequestQuery=new BusinessPartnerRequestQuery();
        partnerRequestQuery.setTenantId(bill.getTenantId());
        partnerRequestQuery.setAppId(bill.getAppId());
        partnerRequestQuery.setId(bill.getPartnerId());
        Payload<BusinessPartnerResponseDTO> partnerPayload=  businessPartnerClient.getPartner(partnerRequestQuery);
        if(!SUCCESS.equals(partnerPayload.getCode()) || null==partnerPayload.getPayload()){
            throw new ApplicationException("未查询到买家业务伙伴");
        }
        BusinessPartnerResponseDTO partnerResponseDTO =GeneralConvertUtils.conv(partnerPayload.getPayload(),BusinessPartnerResponseDTO.class);
        financeBankAccountRequestQuery.setIsolationId(bill.getIsolationId());
        Payload<List<FinanceBankAccountResponseDTO>> financeBankAccountPayload =
                financeBankAccountApi.listByOrganIds(financeBankAccountRequestQuery);
        List<FinanceBankAccountResponseDTO> financeBankAccountResponseDTOS =
                GeneralConvertUtils.convert2List(financeBankAccountPayload.getPayload(),
                        FinanceBankAccountResponseDTO.class);

        List<FinanceBankAccountResponseDTO> financeBankAccountResponseDTOList =
                financeBankAccountResponseDTOS.stream().filter(e -> e.getIsProceedsAccount() == 0).collect(Collectors.toList());
        if (financeBankAccountResponseDTOList.size() == 0) {
            throw new ApplicationException("支付异常");
        }
        if (CollectionUtil.isNotEmpty(financeBankAccountResponseDTOList)) {
            FinanceBankAccountResponseDTO financeBankAccountResponseDTO = financeBankAccountResponseDTOS.get(0);

            //收款单对象
            FinanceCollectionAdminRequestDTO financeCollectionAdminRequestDTO = new FinanceCollectionAdminRequestDTO();
            financeCollectionAdminRequestDTO.setAppId(bill.getAppId());
            financeCollectionAdminRequestDTO.setTenantId(bill.getTenantId());
            financeCollectionAdminRequestDTO.setIsPaymentRecords(0);
            financeCollectionAdminRequestDTO.setCustomerId(partnerResponseDTO.getId());
            financeCollectionAdminRequestDTO.setCustomerName(partnerResponseDTO.getName());
            financeCollectionAdminRequestDTO.setHandlersId(bill.getUserId());
            financeCollectionAdminRequestDTO.setHandlersName(bill.getUserName());
            financeCollectionAdminRequestDTO.setAccountId(financeBankAccountResponseDTO.getId());
//            financeCollectionAdminRequestDTO.setAccountName(financeBankAccountResponseDTO.getName());
//            financeCollectionAdminRequestDTO.setAccountNumber(financeBankAccountResponseDTO.getBankAccount());
            financeCollectionAdminRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getType(), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getPrefix(), DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getLen()));
            financeCollectionAdminRequestDTO.setType(0);
            financeCollectionAdminRequestDTO.setWay(2);
            financeCollectionAdminRequestDTO.setReceiptsTime(new Date());
            financeCollectionAdminRequestDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financeCollectionAdminRequestDTO.setSettlementId(partnerResponseDTO.getId());
            financeCollectionAdminRequestDTO.setSettlementName(partnerResponseDTO.getName());
            financeCollectionAdminRequestDTO.setIsolationId(bill.getIsolationId());

            List<FinanceCollectionOrderAdminRequesDTO> financeCollectionOrderAdminRequesDTOList = new ArrayList<>();
            FinanceCollectionOrderAdminRequesDTO financeCollectionOrderAdminRequesDTO =
                    new FinanceCollectionOrderAdminRequesDTO();
            financeCollectionOrderAdminRequesDTO.setOrderCode(bill.getCode());
            financeCollectionOrderAdminRequesDTO.setOrderId(bill.getId());
            financeCollectionOrderAdminRequesDTO.setSettlementAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financeCollectionOrderAdminRequesDTO.setType(0);
            financeCollectionOrderAdminRequesDTOList.add(financeCollectionOrderAdminRequesDTO);

            financeCollectionAdminRequestDTO.setDatas(financeCollectionOrderAdminRequesDTOList);

            //支付流水对象
            FinancePaymentRecordsRequestDTO financePaymentRecordsRequestDTO = new FinancePaymentRecordsRequestDTO();
            financePaymentRecordsRequestDTO.setTenantId(bill.getTenantId());
            financePaymentRecordsRequestDTO.setAppId(bill.getAppId());
            financePaymentRecordsRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getType(),
                    IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getPrefix()
                    , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getLen()));
            financePaymentRecordsRequestDTO.setOrderId(bill.getId());
            financePaymentRecordsRequestDTO.setOrderCode(bill.getCode());
            financePaymentRecordsRequestDTO.setCustomerName(partnerResponseDTO.getName());
            financePaymentRecordsRequestDTO.setPayType(2);
            financePaymentRecordsRequestDTO.setReceiptsTime(payCallbackRequestDTO.getPaySuccessTime());
            financePaymentRecordsRequestDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financePaymentRecordsRequestDTO.setIsolationId(bill.getIsolationId());
            financePaymentRecordsRequestDTO.setPartnerId(partnerResponseDTO.getId());
//            financePaymentRecordsRequestDTO.setProceedsAccount(financeBankAccountResponseDTO.getName());
//            financePaymentRecordsRequestDTO.setProceedsBankAccount(financeBankAccountResponseDTO.getBankAccount());
            financePaymentRecordsRequestDTO.setPayChannel(this.codeTranNumber(payCallbackRequestDTO.getPayChannelCode()));
            financePaymentRecordsRequestDTO.setOrderType(2);


            //生成支付流水、收款单
            FinanceCollectionPaymentRecordsDTO financeCollectionPaymentRecordsDTO =
                    new FinanceCollectionPaymentRecordsDTO();
            financeCollectionPaymentRecordsDTO.setFinanceCollectionAdminRequestDTO(financeCollectionAdminRequestDTO);
            financeCollectionPaymentRecordsDTO.setFinancePaymentRecordsRequestDTO(financePaymentRecordsRequestDTO);
            Payload<Boolean> financePayload =
                    financeCollectionApi.addPaymentRecords(financeCollectionPaymentRecordsDTO, 0);

            if (!financePayload.getPayload()) {
                throw new ApplicationException("生成支付流水、收款单失败");
            }
            //发送还款成功通知
            FinanceRepaymentNotifyDTO notifyDTO = new FinanceRepaymentNotifyDTO();
            notifyDTO.setAmount(payCallbackRequestDTO.getAmount());
            notifyDTO.setCreditRepaymentId(bill.getId());
            notifyDTO.setPayNo(financePaymentRecordsRequestDTO.getCode());
            //发送支付成功MQ
            mqMessageProducter.sendMsg(TOPOIC, FINANCE_CREDIT_REPAYMENT, JsonUtil.bean2JsonString(notifyDTO));
        }

    }

    /**
     * 销售订单回调
     *
     * @param payCallbackRequestDTO
     */
    private void callBackSaleOrderInfo(PayCallbackRequestDTO payCallbackRequestDTO,
                                       SaleOrderResponseDTO saleOrderResponseDTO) throws Exception {
        //更新订单状态
        ToolStatusRequestQuery query = new ToolStatusRequestQuery();
        query.setActionCode(ActionCodeEnum.OrderCollection.name());
        query.setAppId(saleOrderResponseDTO.getAppId());
        query.setTenantId(saleOrderResponseDTO.getTenantId());
        query.setListType("SalesOrder");
        //支付金额=已支付金额+支付回调的金额
        BigDecimal payAmount = saleOrderResponseDTO.getPayAmount().add(payCallbackRequestDTO.getBuyerPayAmount());
        if (saleOrderResponseDTO.getAccrueAmount().compareTo(payAmount) < 0) {
            payAmount = saleOrderResponseDTO.getAccrueAmount();
        }
        HashMap<String, BigDecimal> map = new HashMap();
        map.put("collectionAmount", payAmount);
        map.put("totalAmount", saleOrderResponseDTO.getAccrueAmount());
        query.setRuleData(JsonUtil.bean2JsonString(map));
        Payload<Integer> codePayload = toolStatusClient.getStatusCode(query);


        //更新支付金额
        SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO = new SaleOrderInfoAmountEditDTO();
        saleOrderInfoAmountEditDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
        saleOrderInfoAmountEditDTO.setId(saleOrderResponseDTO.getId());
        saleOrderInfoAmountEditDTO.setStatus(codePayload.getPayload());
        saleOrderInfoAmountEditDTO.setTenantId(saleOrderResponseDTO.getTenantId());
        saleOrderInfoAmountEditDTO.setAppId(saleOrderResponseDTO.getAppId());
        saleOrderInfoAmountEditDTO.setVersion(saleOrderResponseDTO.getVersion());
        if (!saleOrderInfoClient.updateOrderAmount(saleOrderInfoAmountEditDTO).getPayload()) {
            throw new ApplicationException("更新支付金额失败");
        }


        //组织id查收款银行信息
        FinanceBankAccountRequestQuery financeBankAccountRequestQuery = new FinanceBankAccountRequestQuery();
        financeBankAccountRequestQuery.setAppId(saleOrderResponseDTO.getAppId());
        financeBankAccountRequestQuery.setTenantId(saleOrderResponseDTO.getTenantId());
        financeBankAccountRequestQuery.setIsProceedsAccount(0);

        GroupResultVO groupResultVO = getUserGroup(saleOrderResponseDTO.clone(SaleOrderResponseDTO.class));
        Long orgId = OrganizationUtils.getOrganizationByLevel(groupResultVO.getIdPath(), 2);

        financeBankAccountRequestQuery.setIsolationId(orgId + "");
        Payload<List<FinanceBankAccountResponseDTO>> financeBankAccountPayload =
                financeBankAccountApi.listByOrganIds(financeBankAccountRequestQuery);
        List<FinanceBankAccountResponseDTO> financeBankAccountResponseDTOS =
                GeneralConvertUtils.convert2List(financeBankAccountPayload.getPayload(),
                        FinanceBankAccountResponseDTO.class);

        List<FinanceBankAccountResponseDTO> financeBankAccountResponseDTOList =
                financeBankAccountResponseDTOS.stream().filter(e -> e.getIsProceedsAccount() == 0).collect(Collectors.toList());
        if (financeBankAccountResponseDTOList.size() == 0) {
            throw new ApplicationException("支付异常");
        }
        if (CollectionUtil.isNotEmpty(financeBankAccountResponseDTOList)) {
            FinanceBankAccountResponseDTO financeBankAccountResponseDTO = financeBankAccountResponseDTOS.get(0);

            //收款单对象
            FinanceCollectionAdminRequestDTO financeCollectionAdminRequestDTO = new FinanceCollectionAdminRequestDTO();
            financeCollectionAdminRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            financeCollectionAdminRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            financeCollectionAdminRequestDTO.setIsPaymentRecords(0);
            financeCollectionAdminRequestDTO.setCustomerId(saleOrderResponseDTO.getPartnerId());
            financeCollectionAdminRequestDTO.setCustomerName(saleOrderResponseDTO.getPartnerName());
            financeCollectionAdminRequestDTO.setHandlersId(saleOrderResponseDTO.getHandler());
            financeCollectionAdminRequestDTO.setHandlersName(saleOrderResponseDTO.getHandlerName());
            financeCollectionAdminRequestDTO.setAccountId(financeBankAccountResponseDTO.getId());
//            financeCollectionAdminRequestDTO.setAccountName(financeBankAccountResponseDTO.getName());
//            financeCollectionAdminRequestDTO.setAccountNumber(financeBankAccountResponseDTO.getBankAccount());
            financeCollectionAdminRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getType(), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getPrefix(), DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getLen()));
            financeCollectionAdminRequestDTO.setType(0);
            financeCollectionAdminRequestDTO.setWay(0);
            financeCollectionAdminRequestDTO.setReceiptsTime(new Date());
            financeCollectionAdminRequestDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financeCollectionAdminRequestDTO.setSettlementId(saleOrderResponseDTO.getPartnerId());
            financeCollectionAdminRequestDTO.setSettlementName(saleOrderResponseDTO.getPartnerName());
            financeCollectionAdminRequestDTO.setIsolationId(orgId + "");

            List<FinanceCollectionOrderAdminRequesDTO> financeCollectionOrderAdminRequesDTOList = new ArrayList<>();
            FinanceCollectionOrderAdminRequesDTO financeCollectionOrderAdminRequesDTO =
                    new FinanceCollectionOrderAdminRequesDTO();
            financeCollectionOrderAdminRequesDTO.setOrderCode(saleOrderResponseDTO.getCode());
            financeCollectionOrderAdminRequesDTO.setOrderId(saleOrderResponseDTO.getId());
            financeCollectionOrderAdminRequesDTO.setSettlementAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financeCollectionOrderAdminRequesDTO.setType(0);
            financeCollectionOrderAdminRequesDTOList.add(financeCollectionOrderAdminRequesDTO);

            financeCollectionAdminRequestDTO.setDatas(financeCollectionOrderAdminRequesDTOList);

            //支付流水对象
            FinancePaymentRecordsRequestDTO financePaymentRecordsRequestDTO = new FinancePaymentRecordsRequestDTO();
            financePaymentRecordsRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            financePaymentRecordsRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            financePaymentRecordsRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getType(),
                    IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getPrefix()
                    , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getLen()));
            financePaymentRecordsRequestDTO.setOrderId(saleOrderResponseDTO.getId());
            financePaymentRecordsRequestDTO.setOrderCode(saleOrderResponseDTO.getCode());
            financePaymentRecordsRequestDTO.setCustomerName(saleOrderResponseDTO.getCustomerName());
            financePaymentRecordsRequestDTO.setPayType(2);
            financePaymentRecordsRequestDTO.setReceiptsTime(payCallbackRequestDTO.getPaySuccessTime());
            financePaymentRecordsRequestDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financePaymentRecordsRequestDTO.setIsolationId(orgId + "");
//            financePaymentRecordsRequestDTO.setProceedsAccount(financeBankAccountResponseDTO.getName());
//            financePaymentRecordsRequestDTO.setProceedsBankAccount(financeBankAccountResponseDTO.getBankAccount());
            financePaymentRecordsRequestDTO.setPayChannel(this.codeTranNumber(payCallbackRequestDTO.getPayChannelCode()));
            financePaymentRecordsRequestDTO.setOrderType(0);
            financePaymentRecordsRequestDTO.setOrgId(saleOrderResponseDTO.getAscriptionOrgId());

            //生成支付流水、收款单
            FinanceCollectionPaymentRecordsDTO financeCollectionPaymentRecordsDTO =
                    new FinanceCollectionPaymentRecordsDTO();
            financeCollectionPaymentRecordsDTO.setFinanceCollectionAdminRequestDTO(financeCollectionAdminRequestDTO);
            financeCollectionPaymentRecordsDTO.setFinancePaymentRecordsRequestDTO(financePaymentRecordsRequestDTO);
            Payload<Boolean> financePayload =
                    financeCollectionApi.addPaymentRecords(financeCollectionPaymentRecordsDTO, 0);

            if (!financePayload.getPayload()) {
                throw new ApplicationException("生成支付流水、收款单失败");
            }
        }
        //扭转订单状态
        toolActionExcutor.excute(LinkBusinessCodeEnum.BuyerPayment.name(), ListTypeEnum.SalesOrder.name(),
                saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTenantId(), saleOrderResponseDTO.getAppId());
        //更新订单支付记录所关联订单编号
        saleOrderInfoUtils.updatePayOrderCode(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getCode());
        saleOrderInfoUtils.addPaySuccess(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTicketType(), "在线支付成功");
        saleOrderInfoUtils.updatePaymentType(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getCode(), 0, 2);
    }

    /**
     * 扭转子订单业务链路
     *
     * @param list
     */
    private void nextStepBussiness(List<SaleOrderResponseDTO> list) throws Exception {
        if (CollectionUtil.isNotEmpty(list)) {
            //更新订单状态
            for (SaleOrderResponseDTO saleOrderResponseDTO : list) {
                toolActionExcutor.excute(LinkBusinessCodeEnum.BuyerPayment.name(), ListTypeEnum.SalesOrder.name(),
                        saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTenantId(),
                        saleOrderResponseDTO.getAppId());
            }
        }
    }

    /**
     * 获取子订单支付信息和支付状态信息
     *
     * @param saleOrderList
     * @return
     * @throws Exception
     */
    private List<SaleOrderInfoAmountEditDTO> getSubOrderAmountAndStatus(List<SaleOrderResponseDTO> saleOrderList) throws Exception {
        List<SaleOrderInfoAmountEditDTO> result = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(saleOrderList)) {
            for (SaleOrderResponseDTO saleOrderResponseDTO : saleOrderList) {
                //应付金额
                BigDecimal orderPayAmount = saleOrderResponseDTO.getAccrueAmount();
                ToolStatusRequestQuery query = new ToolStatusRequestQuery();
                query.setActionCode(ActionCodeEnum.OrderCollection.name());
                query.setAppId(saleOrderResponseDTO.getAppId());
                query.setTenantId(saleOrderResponseDTO.getTenantId());
                query.setListType("SalesOrder");
                //支付金额=已支付金额+订单应付金额
                BigDecimal payAmount = saleOrderResponseDTO.getPayAmount().add(orderPayAmount);

                HashMap<String, BigDecimal> map = new HashMap();
                map.put("collectionAmount", payAmount);
                map.put("totalAmount", saleOrderResponseDTO.getAccrueAmount());
                query.setRuleData(JsonUtil.bean2JsonString(map));
                Payload<Integer> codePayload = toolStatusClient.getStatusCode(query);


                SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO = new SaleOrderInfoAmountEditDTO();
                saleOrderInfoAmountEditDTO.setAmount(payAmount);
                saleOrderInfoAmountEditDTO.setId(saleOrderResponseDTO.getId());
                saleOrderInfoAmountEditDTO.setStatus(codePayload.getPayload());
                saleOrderInfoAmountEditDTO.setTenantId(saleOrderResponseDTO.getTenantId());
                saleOrderInfoAmountEditDTO.setAppId(saleOrderResponseDTO.getAppId());
                saleOrderInfoAmountEditDTO.setVersion(saleOrderResponseDTO.getVersion());

                result.add(saleOrderInfoAmountEditDTO);
            }
        }
        return result;
    }

    /**
     * 销售父订单回调
     *
     * @param payCallbackRequestDTO
     */
    private void callBackParentSaleOrderInfo(PayCallbackRequestDTO payCallbackRequestDTO,
                                             SaleOrderSplitRecordResponseDTO saleOrderResponseDTO,
                                             List<SaleOrderResponseDTO> saleOrderList) throws Exception {
        //更新订单状态
        ToolStatusRequestQuery query = new ToolStatusRequestQuery();
        query.setActionCode(ActionCodeEnum.OrderCollection.name());
        query.setAppId(saleOrderResponseDTO.getAppId());
        query.setTenantId(saleOrderResponseDTO.getTenantId());
        query.setListType("SalesOrder");
        //支付金额=已支付金额+支付回调的金额
        BigDecimal payAmount = saleOrderResponseDTO.getPayAmount().add(payCallbackRequestDTO.getBuyerPayAmount());

        HashMap<String, BigDecimal> map = new HashMap();
        map.put("collectionAmount", payAmount);
        map.put("totalAmount", saleOrderResponseDTO.getAccrueAmount());
        query.setRuleData(JsonUtil.bean2JsonString(map));
        Payload<Integer> codePayload = toolStatusClient.getStatusCode(query);
        //更新支付金额
        SaleOrderAmountStatuEditDTO splitRecordRequestDTO = new SaleOrderAmountStatuEditDTO();
        splitRecordRequestDTO.setId(saleOrderResponseDTO.getId());
        splitRecordRequestDTO.setPayAmount(payAmount);
        splitRecordRequestDTO.setPaymenetStatus(codePayload.getPayload());
        splitRecordRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
        splitRecordRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
        //获取子订单支付信息
        splitRecordRequestDTO.setSubList(getSubOrderAmountAndStatus(saleOrderList));
        if (!saleOrderSplitRecordClient.updateOrderAmountAndStatus(splitRecordRequestDTO)) {
            throw new ApplicationException("更新支付金额失败");
        }
        //组织id查收款银行信息
        FinanceBankAccountRequestQuery financeBankAccountRequestQuery = new FinanceBankAccountRequestQuery();
        financeBankAccountRequestQuery.setAppId(saleOrderResponseDTO.getAppId());
        financeBankAccountRequestQuery.setTenantId(saleOrderResponseDTO.getTenantId());
        financeBankAccountRequestQuery.setIsProceedsAccount(0);

        GroupResultVO groupResultVO = getUserGroup(saleOrderResponseDTO.clone(SaleOrderResponseDTO.class));
        Long orgId = OrganizationUtils.getOrganizationByLevel(groupResultVO.getIdPath(), 2);

        financeBankAccountRequestQuery.setIsolationId(orgId + "");
        Payload<List<FinanceBankAccountResponseDTO>> financeBankAccountPayload =
                financeBankAccountApi.listByOrganIds(financeBankAccountRequestQuery);
        List<FinanceBankAccountResponseDTO> financeBankAccountResponseDTOS =
                GeneralConvertUtils.convert2List(financeBankAccountPayload.getPayload(),
                        FinanceBankAccountResponseDTO.class);

        List<FinanceBankAccountResponseDTO> financeBankAccountResponseDTOList =
                financeBankAccountResponseDTOS.stream().filter(e -> e.getIsProceedsAccount() == 0).collect(Collectors.toList());
        if (financeBankAccountResponseDTOList.size() == 0) {
            throw new ApplicationException("支付异常");
        }
        if (CollectionUtil.isNotEmpty(financeBankAccountResponseDTOList)) {
            FinanceBankAccountResponseDTO financeBankAccountResponseDTO = financeBankAccountResponseDTOS.get(0);

            //收款单对象
            FinanceCollectionAdminRequestDTO financeCollectionAdminRequestDTO = new FinanceCollectionAdminRequestDTO();
            financeCollectionAdminRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            financeCollectionAdminRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            financeCollectionAdminRequestDTO.setIsPaymentRecords(0);
            financeCollectionAdminRequestDTO.setCustomerId(saleOrderResponseDTO.getPartnerId());
            financeCollectionAdminRequestDTO.setCustomerName(saleOrderResponseDTO.getPartnerName());
            financeCollectionAdminRequestDTO.setHandlersId(saleOrderResponseDTO.getHandler());
            financeCollectionAdminRequestDTO.setHandlersName(saleOrderResponseDTO.getHandlerName());
            financeCollectionAdminRequestDTO.setAccountId(financeBankAccountResponseDTO.getId());
//            financeCollectionAdminRequestDTO.setAccountName(financeBankAccountResponseDTO.getName());
//            financeCollectionAdminRequestDTO.setAccountNumber(financeBankAccountResponseDTO.getBankAccount());
            financeCollectionAdminRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getType(), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getPrefix(), DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getLen()));
            financeCollectionAdminRequestDTO.setType(0);
            financeCollectionAdminRequestDTO.setWay(0);
            financeCollectionAdminRequestDTO.setReceiptsTime(new Date());
            financeCollectionAdminRequestDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financeCollectionAdminRequestDTO.setSettlementId(saleOrderResponseDTO.getPartnerId());
            financeCollectionAdminRequestDTO.setSettlementName(saleOrderResponseDTO.getPartnerName());
            financeCollectionAdminRequestDTO.setIsolationId(orgId + "");

            List<FinanceCollectionOrderAdminRequesDTO> financeCollectionOrderAdminRequesDTOList = new ArrayList<>();
            FinanceCollectionOrderAdminRequesDTO financeCollectionOrderAdminRequesDTO =
                    new FinanceCollectionOrderAdminRequesDTO();
            financeCollectionOrderAdminRequesDTO.setOrderCode(saleOrderResponseDTO.getCode());
            financeCollectionOrderAdminRequesDTO.setOrderId(saleOrderResponseDTO.getId());
            financeCollectionOrderAdminRequesDTO.setSettlementAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financeCollectionOrderAdminRequesDTO.setType(0);
            financeCollectionOrderAdminRequesDTOList.add(financeCollectionOrderAdminRequesDTO);

            financeCollectionAdminRequestDTO.setDatas(financeCollectionOrderAdminRequesDTOList);

            //支付流水对象
            FinancePaymentRecordsRequestDTO financePaymentRecordsRequestDTO = new FinancePaymentRecordsRequestDTO();
            financePaymentRecordsRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            financePaymentRecordsRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            financePaymentRecordsRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getType(),
                    IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getPrefix()
                    , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getLen()));
            financePaymentRecordsRequestDTO.setOrderId(saleOrderResponseDTO.getId());
            financePaymentRecordsRequestDTO.setOrderCode(saleOrderResponseDTO.getCode());
            financePaymentRecordsRequestDTO.setCustomerName(saleOrderResponseDTO.getCustomerName());
            financePaymentRecordsRequestDTO.setPayType(2);
            financePaymentRecordsRequestDTO.setReceiptsTime(payCallbackRequestDTO.getPaySuccessTime());
            financePaymentRecordsRequestDTO.setAmount(payCallbackRequestDTO.getBuyerPayAmount());
            financePaymentRecordsRequestDTO.setIsolationId(orgId + "");
//            financePaymentRecordsRequestDTO.setProceedsAccount(financeBankAccountResponseDTO.getName());
//            financePaymentRecordsRequestDTO.setProceedsBankAccount(financeBankAccountResponseDTO.getBankAccount());
            financePaymentRecordsRequestDTO.setPayChannel(this.codeTranNumber(payCallbackRequestDTO.getPayChannelCode()));
            financePaymentRecordsRequestDTO.setOrderType(0);
            //生成支付流水、收款单
            FinanceCollectionPaymentRecordsDTO financeCollectionPaymentRecordsDTO =
                    new FinanceCollectionPaymentRecordsDTO();
            financeCollectionPaymentRecordsDTO.setFinanceCollectionAdminRequestDTO(financeCollectionAdminRequestDTO);
            financeCollectionPaymentRecordsDTO.setFinancePaymentRecordsRequestDTO(financePaymentRecordsRequestDTO);
            Payload<Boolean> financePayload =
                    financeCollectionApi.addPaymentRecords(financeCollectionPaymentRecordsDTO, 0);

            if (!financePayload.getPayload()) {
                throw new ApplicationException("生成支付流水、收款单失败");
            }
            //扭转业务链路
            nextStepBussiness(saleOrderList);

            //销售父单更新支付记录所关联订单编号、支付类型、操作记录
            saleOrderInfoUtils.updateSaleOrderInfoCode(3, saleOrderResponseDTO.getCode(),
                    saleOrderResponseDTO.getCode());
            saleOrderInfoUtils.updatePaymentType(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getCode(), 0, 2);
        }
    }

    //支付后进行链路扭转
    @Override
    public void payOrderNextStep(SaleOrderPayNotifyDto saleOrderPayNotifyDto) throws Exception {
        if (saleOrderPayNotifyDto.getOrderType().equals(0)) {       //查询网批订单信息

            Payload<SaleOrderResponseDTO> payload =
                    saleOrderInfoClient.selectSaleOrder(saleOrderPayNotifyDto.getOrderCode());
            if (SUCCESS.equals(payload.getCode()) && payload.getPayload() != null) {
                SaleOrderResponseDTO saleOrderResponseDTO = GeneralConvertUtils.conv(payload.getPayload(),
                        SaleOrderResponseDTO.class);
                BigDecimal payAmount = getOrderSureAmount(saleOrderResponseDTO.getId(),saleOrderResponseDTO.getAppId(),saleOrderResponseDTO.getTenantId(),saleOrderResponseDTO.getCode());
                saleOrderResponseDTO.setPayAmount(payAmount);
                //如果直接是子订单则直接更新
                updateSaleOrderPayInfo(saleOrderResponseDTO);
            } else {
                SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO =
                        saleOrderSplitRecordClient.selectById(saleOrderPayNotifyDto.getOrderId());
                if (saleOrderSplitRecordResponseDTO != null) {
                    BigDecimal payAmount = getOrderSureAmount(saleOrderSplitRecordResponseDTO.getId(),saleOrderSplitRecordResponseDTO.getAppId(),saleOrderSplitRecordResponseDTO.getTenantId(),saleOrderSplitRecordResponseDTO.getCode());
                    //更新父订单已付金额和状态
                    PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
                    payStatusQuery.setAppId(saleOrderSplitRecordResponseDTO.getAppId());
                    payStatusQuery.setTenantId(saleOrderSplitRecordResponseDTO.getTenantId());
                    //暂时没有用到
                    payStatusQuery.setId(saleOrderSplitRecordResponseDTO.getId());
                    payStatusQuery.setTotalCollectionAmount(saleOrderSplitRecordResponseDTO.getAccrueAmount());//营收金额
                    payStatusQuery.setCollectionAmount(payAmount);//已付款金额
                    Integer status = getPayStatus(payStatusQuery);//支付状态
                    saleOrderSplitRecordResponseDTO.setPayAmount(saleOrderSplitRecordResponseDTO.getPayAmount());
                    saleOrderSplitRecordResponseDTO.setPaymentStatus(status);
                    //更新父单付款状态和支付金额
                    saleOrderSplitRecordClient.updateById(saleOrderSplitRecordResponseDTO.getId(),
                            saleOrderSplitRecordResponseDTO.clone(SaleOrderSplitRecordRequestDTO.class));
                    //如果确认金额大于等于0，即代付金额少于0
                    if (payAmount.compareTo(saleOrderSplitRecordResponseDTO.getAccrueAmount()) >= 0) {
                        //根据父单查询订单列表
                        Payload<List<SaleOrderResponseDTO>> payloadOrders =
                                saleOrderInfoClient.selectSaleOrderByParentCode(saleOrderSplitRecordResponseDTO.getCode());
                        if (SUCCESS.equals(payloadOrders.getCode())) {
                            List<SaleOrderResponseDTO> list =
                                    GeneralConvertUtils.convert2List(payloadOrders.getPayload(),
                                            SaleOrderResponseDTO.class);
                            if (CollectionUtil.isNotEmpty(list)) {
                                for (SaleOrderResponseDTO dto : list) {
                                    //如果父订单已支付完成，则子订单支付金额=应收金额
                                    dto.setPayAmount(dto.getAccrueAmount());
                                    updateSaleOrderPayInfo(dto);
                                }
                            }
                        }
                    }
                }
            }//当是提货订单支付时
        } else if (saleOrderPayNotifyDto.getOrderType().equals(1)) {
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                    salePickGoodsInfoClient.selectById(saleOrderPayNotifyDto.getOrderId());
            if (salePickGoodsInfoResponseDTO != null) {
                BigDecimal payAmount = salePickGoodsInfoResponseDTO.getPayAmount();
                payAmount = payAmount == null ? BigDecimal.valueOf(0) : payAmount;
                //获取支付状态
                PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
                payStatusQuery.setAppId(salePickGoodsInfoResponseDTO.getAppId());
                payStatusQuery.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
                payStatusQuery.setId(salePickGoodsInfoResponseDTO.getId());
                payStatusQuery.setTotalCollectionAmount(salePickGoodsInfoResponseDTO.getTotalGoodsMoney());//营收金额
                payStatusQuery.setCollectionAmount(payAmount);//已付款金额
                Integer status = getPayStatus(payStatusQuery);//支付状态
                salePickGoodsInfoResponseDTO.setPayAmount(payAmount);
                salePickGoodsInfoResponseDTO.setPaymentStatus(status);
                if (payAmount.compareTo(salePickGoodsInfoResponseDTO.getTotalGoodsMoney()) >= 0) {
                    salePickGoodsInfoResponseDTO.setStatus(StatusCodeEnum.ToConfirm.getCode());
                }
                //更提货申请状态、支付状态、支付金额
                salePickGoodsInfoClient.updateById(salePickGoodsInfoResponseDTO.getId(),
                        salePickGoodsInfoResponseDTO.clone(SalePickGoodsInfoRequestDTO.class));
            }
        }

        //保存消息记录
        addSaleOrderPayNotify(saleOrderPayNotifyDto);
    }


    /**
     * 获取订单已确认的订单金额
     * @param orderId
     * @return
     * @throws Exception
     */
    public BigDecimal getOrderSureAmount(Long orderId,Long appId,String tenanId,String orderCode) throws Exception{

        //查询当前订单支付记录
        FinancePaymentRecordsRequestQuery query=new FinancePaymentRecordsRequestQuery();
        query.setAppId(appId);
        query.setTenantId(tenanId);
        query.setOrderCode(orderCode);
        query.setOrderId(orderId);
        Payload<List<FinancePaymentRecordsResponseDTO>> payload= financePaymentRecordClient.listFinancePaymentRecords(query);
        List<FinancePaymentRecordsResponseDTO> list=   GeneralConvertUtils.convert2List(payload.getPayload(),FinancePaymentRecordsResponseDTO.class);
        if(SUCCESS.equals(payload.getCode()) && CollectionUtil.isNotEmpty(list)) {

            BigDecimal totalPayAmount = BigDecimal.valueOf(0);
            for (FinancePaymentRecordsResponseDTO dto : list) {
                if (StatusCodeEnum.Confirmed.getCode().equals(dto.getStatus())) {
                    //确认金额
                    totalPayAmount = totalPayAmount.add(dto.getAmount());
                }
            }
            //已确认金额
            return totalPayAmount;
        }
        return BigDecimal.valueOf(0);
    }



    /**
     * 更新订单 付款信息
     *
     * @param saleOrderResponseDTO
     * @throws Exception
     */
    private void updateSaleOrderPayInfo(SaleOrderResponseDTO saleOrderResponseDTO) throws Exception {
        BigDecimal payAmount = saleOrderResponseDTO.getPayAmount();
        //获取业务链路状态
        PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
        payStatusQuery.setAppId(saleOrderResponseDTO.getAppId());
        payStatusQuery.setTenantId(saleOrderResponseDTO.getTenantId());
        payStatusQuery.setId(saleOrderResponseDTO.getId());
        payStatusQuery.setTotalCollectionAmount(saleOrderResponseDTO.getAccrueAmount());//营收金额
        payStatusQuery.setCollectionAmount(payAmount);//已付款金额
        Integer status = getPayStatus(payStatusQuery);//支付状态

        //更新订单付款状态
        com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO requestDTO=new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO();
        requestDTO.setPaymentStatus(status);
        requestDTO.setId(saleOrderResponseDTO.getId());
        saleOrderInfoClient.updateMainOrderById(saleOrderResponseDTO.getId(),requestDTO);

        toolActionExcutor.excute(LinkBusinessCodeEnum.BuyerPayment.name(), ListTypeEnum.SalesOrder.name(),
                saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTenantId(), saleOrderResponseDTO.getAppId());
    }

    /**
     * 更新线下子订单 付款信息
     *
     * @param saleOrderResponseDTO
     * @throws Exception
     */
    private void updateOfflineOrderPayInfo(SaleOrderResponseDTO saleOrderResponseDTO) throws Exception {
        //更新订单已付金额和订单状态
        SaleOrderInfoAmountEditDTO editDTO = new SaleOrderInfoAmountEditDTO();
        editDTO.setId(saleOrderResponseDTO.getId());
        editDTO.setAmount(saleOrderResponseDTO.getPayAmount());
        editDTO.setVersion(saleOrderResponseDTO.getVersion());
        editDTO.setStatus(saleOrderResponseDTO.getStatus());
        saleOrderInfoClient.updateOrderAmount(editDTO);
        toolActionExcutor.excute(LinkBusinessCodeEnum.BuyerPayment.name(), ListTypeEnum.SalesOrder.name(),
                saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTenantId(), saleOrderResponseDTO.getAppId());
    }

    /**
     * 验证订单交易记录是否已经全部确认并且已确认的金额大于等于应付金额
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    private boolean validPayRecordSure(Long orderId) throws Exception {
        SaleOrderResponseDTO order = getSaleOrderById(orderId);
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(orderId);
        //查询支付记录
        Payload<List<FinancePaymentRecordsResponseDTO>> payload =
                financePaymentRecordClient.listFinancePaymentRecordsByOrderIds(orderIds);
        if (SUCCESS.equals(payload.getCode())) {
            List<FinancePaymentRecordsResponseDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),
                    FinancePaymentRecordsResponseDTO.class);
            if (CollectionUtil.isNotEmpty(list)) {
                int count = list.size();
                int sureCount = 0;
                BigDecimal totalPayAmount = BigDecimal.valueOf(0);
                for (FinancePaymentRecordsResponseDTO dto : list) {
                    if (StatusCodeEnum.Confirmed.getCode().equals(dto.getStatus())) {
                        //确认数量
                        sureCount++;
                        //确认金额
                        totalPayAmount = totalPayAmount.add(dto.getAmount());
                    }
                }
                //应收金额-已付金额<=0时才进行扭转
                return order.getAccrueAmount().subtract(totalPayAmount).compareTo(BigDecimal.valueOf(0)) <= 0;
            }
        }
        return false;
    }

    private SaleOrderResponseDTO getSaleOrderById(Long id) {
        Payload<SaleOrderResponseDTO> payload = saleOrderInfoClient.selectSaleOrder(id);
        if (SUCCESS.equals(payload.getCode())) {
            try {
                SaleOrderResponseDTO order = GeneralConvertUtils.conv(payload.getPayload(), SaleOrderResponseDTO.class);
                return order;
            } catch (Exception e) {
                logger.error("转换订单异常:", e);
                return null;
            }
        }
        return null;
    }

    @Override
    public List<DepotResponseDTO> getAllDepotList(Long appId, String isolationId) {
        List<DepotResponseDTO> resultList = new ArrayList<>();
        DepotFindAllPostRequestDTO requestDTO = new DepotFindAllPostRequestDTO();
        requestDTO.setTenantId(appRuntimeEnv.getTenantId());
        requestDTO.setAppId(appId);
        if (Objects.nonNull(isolationId)) {
            requestDTO.setIsolationId(isolationId);
        }
        PayloadDepotFindAllPostResponseDTO payload = depotSdkApi.findDepotAll(requestDTO);
        if (SUCCESS.equals(payload.getCode())) {
            DepotFindAllPostResponseDTO depotFindAllPostResponseDTO = payload.getPayload();
            List<DepotFindAllPostResponseDTODepot> depotList = depotFindAllPostResponseDTO.getDepotList();
            if (CollectionUtil.isNotEmpty(depotList)) {
                for (DepotFindAllPostResponseDTODepot dto : depotList) {
                    DepotResponseDTO depot = GeneralConvertUtils.conv(dto, DepotResponseDTO.class);
                    depot.setId(dto.getId().toString());
                    resultList.add(depot);
                }
            }
        }
        return resultList;
    }

    /**
     * 获取按单收款订单ID
     *
     * @param payStatusQuery
     * @return
     * @throws Exception
     */
    private Integer getPayStatus(PayStatusQueryDTO payStatusQuery) throws Exception {
        LinkBusinessRequestDTO businessRequestDTO = new LinkBusinessRequestDTO();
        businessRequestDTO.setActionCode("OrderCollection");
        businessRequestDTO.setId(payStatusQuery.getId());
        businessRequestDTO.setTenantId(payStatusQuery.getTenantId());
        businessRequestDTO.setAppId(payStatusQuery.getAppId());
        businessRequestDTO.setListType("SalesOrder");
        Map<String, BigDecimal> ruleMap = new HashMap<>();
        ruleMap.put("collectionAmount", payStatusQuery.getCollectionAmount());//已付款金额
        ruleMap.put("totalAmount", payStatusQuery.getTotalCollectionAmount());//总金额
        businessRequestDTO.setRuleData(JsonUtil.bean2JsonString(ruleMap));
        return toolLinkService.getLinkBusinessStatus(businessRequestDTO);
    }

    private List<SaleOrderCollectionNotifyResponseDTO> getSaleOrderCollectionNotify(SaleOrderCollectionNotifyDto payNotifyDto) {
        SaleOrderCollectionNotifySearchQuery query = new SaleOrderCollectionNotifySearchQuery();
        query.setOrderId(payNotifyDto.getOrderId());
        query.setPayNo(payNotifyDto.getPayNo());
        try {
            Payload<List<SaleOrderCollectionNotifyResponseDTO>> payload =
                    saleOrderCollectionNotifyClient.querySaleOrderCollectionNotify(query);
            if (SUCCESS.equals(payload.getCode())) {
                return GeneralConvertUtils.convert2List(payload.getPayload(),
                        SaleOrderCollectionNotifyResponseDTO.class);
            }
        } catch (Exception e) {
            logger.error("查询按单支付记录异常:{}", JsonUtil.bean2JsonString(payNotifyDto));
            logger.error("查询按单支付记录异常:", e);
        }
        return null;
    }

    @Override
    public void cancelOrderPayAmount(SaleOrderPayNotifyDto payNotifyDto) throws Exception {


        if (payNotifyDto.getOrderType().equals(0)) {       //查询网批订单信息

            Payload<SaleOrderResponseDTO> payload =
                    saleOrderInfoClient.selectSaleOrder(payNotifyDto.getOrderCode());
            if (SUCCESS.equals(payload.getCode()) && payload.getPayload() != null) {
                SaleOrderResponseDTO saleOrderResponseDTO = GeneralConvertUtils.conv(payload.getPayload(),
                        SaleOrderResponseDTO.class);
                //撤销订单金额
                cancelOrderAmount(saleOrderResponseDTO, payNotifyDto.getAmount());
            } else {
                SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO =
                        saleOrderSplitRecordClient.selectById(payNotifyDto.getOrderId());
                if (saleOrderSplitRecordResponseDTO != null) {
                    //已付款金额
                    BigDecimal payAmount = saleOrderSplitRecordResponseDTO.getPayAmount().subtract(payNotifyDto.getAmount());

                    //更新父订单已付金额和状态
                    PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
                    payStatusQuery.setAppId(saleOrderSplitRecordResponseDTO.getAppId());
                    payStatusQuery.setTenantId(saleOrderSplitRecordResponseDTO.getTenantId());
                    //暂时没有用到
                    payStatusQuery.setId(saleOrderSplitRecordResponseDTO.getId());
                    payStatusQuery.setTotalCollectionAmount(saleOrderSplitRecordResponseDTO.getAccrueAmount());//营收金额
                    payStatusQuery.setCollectionAmount(payAmount);//已付款金额
                    Integer status = getPayStatus(payStatusQuery);//支付状态
                    saleOrderSplitRecordResponseDTO.setPayAmount(payAmount);
                    saleOrderSplitRecordResponseDTO.setPaymentStatus(status);
                    //更新父单付款状态和支付金额
                    saleOrderSplitRecordClient.updateById(saleOrderSplitRecordResponseDTO.getId(),
                            saleOrderSplitRecordResponseDTO.clone(SaleOrderSplitRecordRequestDTO.class));


                    //根据父单查询订单列表
                    Payload<List<SaleOrderResponseDTO>> payloadOrders =
                            saleOrderInfoClient.selectSaleOrderByParentCode(saleOrderSplitRecordResponseDTO.getCode());
                    logger.info("查询子订单列表:{}",payload);
                    if (SUCCESS.equals(payloadOrders.getCode())) {
                        List<SaleOrderResponseDTO> list =
                                GeneralConvertUtils.convert2List(payloadOrders.getPayload(), SaleOrderResponseDTO.class);
                        if (CollectionUtil.isNotEmpty(list)) {
                            for (SaleOrderResponseDTO dto : list) {
                                //如果父订单被撤销，则子订单也要全部撤销
                                cancelOrderAmount(dto,dto.getAccrueAmount());
                            }
                        }

                    }
                }
            }//当是提货订单支付时
        } else if (payNotifyDto.getOrderType().equals(1)) {
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                    salePickGoodsInfoClient.selectById(payNotifyDto.getOrderId());
            if (salePickGoodsInfoResponseDTO != null) {
                BigDecimal payAmount = salePickGoodsInfoResponseDTO.getPayAmount().subtract(payNotifyDto.getAmount());

                //获取支付状态
                PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
                payStatusQuery.setAppId(salePickGoodsInfoResponseDTO.getAppId());
                payStatusQuery.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
                payStatusQuery.setId(salePickGoodsInfoResponseDTO.getId());
                payStatusQuery.setTotalCollectionAmount(salePickGoodsInfoResponseDTO.getTotalGoodsMoney());//营收金额
                payStatusQuery.setCollectionAmount(payAmount);//已付款金额
                Integer status = getPayStatus(payStatusQuery);//支付状态
                salePickGoodsInfoResponseDTO.setPayAmount(payAmount);
                salePickGoodsInfoResponseDTO.setPaymentStatus(status);

                //更提货申请状态、支付状态、支付金额
                salePickGoodsInfoClient.updateById(salePickGoodsInfoResponseDTO.getId(),
                        salePickGoodsInfoResponseDTO.clone(SalePickGoodsInfoRequestDTO.class));
            }
        }
        //保存取消付款记录
        addSaleOrderPayNotify(payNotifyDto);

    }

    /**
     * 撤销订单付款金额
     *
     * @param order
     * @param amount
     * @throws Exception
     */
    private void cancelOrderAmount(SaleOrderResponseDTO order, BigDecimal amount) throws Exception {
        logger.info("撤销订单:{}金额:{}", order.getCode(), amount);
        //已付款金额
        BigDecimal payAmount = order.getPayAmount().subtract(amount);
        //代收款金额
        BigDecimal unCollectionAmount = order.getAccrueAmount().subtract(payAmount);
        PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
        payStatusQuery.setAppId(order.getAppId());
        payStatusQuery.setTenantId(order.getTenantId());
        payStatusQuery.setId(order.getId());
        payStatusQuery.setTotalCollectionAmount(order.getAccrueAmount());
        payStatusQuery.setCollectionAmount(payAmount);
        logger.info("获取支付状态:{}", JsonUtil.bean2JsonString(payStatusQuery));
        Integer status = getPayStatus(payStatusQuery);
        SaleOrderInfoAmountEditDTO editDTO = new SaleOrderInfoAmountEditDTO();
        editDTO.setId(order.getId());
        editDTO.setAmount(payAmount);
        editDTO.setVersion(order.getVersion());
        editDTO.setStatus(status);
        //更新订单已付金额和订单状态
        saleOrderInfoClient.updateOrderAmount(editDTO);

    }

    /**
     * 保存消息记录
     *
     * @param payNotifyDto
     */
    private void addSaleOrderPayNotify(SaleOrderPayNotifyDto payNotifyDto) {
        SaleOrderPayNotifyRequestDTO requestDTO = new SaleOrderPayNotifyRequestDTO();
        requestDTO.setPayNo(payNotifyDto.getPayNo());
        requestDTO.setAmount(payNotifyDto.getAmount());
        requestDTO.setAppId(payNotifyDto.getAppId());
        requestDTO.setOrderId(payNotifyDto.getOrderId());
        requestDTO.setType(payNotifyDto.getType());
        requestDTO.setTenantId(payNotifyDto.getTenanId());
        saleOrderPayNotifyClient.insert(requestDTO);
    }

    /**
     * 保存按单收款记录
     *
     * @param payNotifyDto
     */
    private void addCollection(SaleOrderCollectionNotifyDto payNotifyDto) {
        SaleOrderCollectionNotifyRequestDTO requestDTO = new SaleOrderCollectionNotifyRequestDTO();
        requestDTO.setPayNo(payNotifyDto.getPayNo());
        requestDTO.setAmount(payNotifyDto.getAmount());
        requestDTO.setAppId(payNotifyDto.getAppId());
        requestDTO.setOrderId(payNotifyDto.getOrderId());
        requestDTO.setTenantId(payNotifyDto.getTenanId());
        saleOrderCollectionNotifyClient.insert(requestDTO);
    }

    @Override
    public void saleOrdercollection(SaleOrderCollectionNotifyDto collectionNotifyDto) {
        List<SaleOrderCollectionNotifyResponseDTO> records = getSaleOrderCollectionNotify(collectionNotifyDto);
        if (records != null && records.size() > 0) {
            logger.info("支付记录已经更新:{}", collectionNotifyDto.getPayNo());
            return;
        }
        Payload<SaleOrderResponseDTO> payload = saleOrderInfoClient.selectSaleOrder(collectionNotifyDto.getOrderId());
        if (SUCCESS.equals(payload.getCode())) {
            try {
                SaleOrderResponseDTO order = GeneralConvertUtils.conv(payload.getPayload(), SaleOrderResponseDTO.class);
                //已付款金额
                BigDecimal payAmount = order.getPayAmount().add(collectionNotifyDto.getAmount());

                SaleOrderInfoAmountEditDTO editDTO = new SaleOrderInfoAmountEditDTO();
                editDTO.setId(order.getId());
                editDTO.setAmount(payAmount);
                editDTO.setVersion(order.getVersion());
                //设置支付状态为 原单状态
                editDTO.setStatus(order.getPaymentStatus());
                //更新订单已付金额和订单状态
                saleOrderInfoClient.updateOrderAmount(editDTO);
                //保存已更新的按单收款记录
                addCollection(collectionNotifyDto);

                //销售单更新支付记录所关联订单编号
                saleOrderInfoUtils.updatePayOrderCode(order.getId(), order.getCode());
                //链路扭转
                toolActionExcutor.excute(LinkBusinessCodeEnum.BuyerPayment.name(), ListTypeEnum.SalesOrder.name(),
                        order.getId(), order.getTenantId(), order.getAppId());
                //添加操作日志
                saleOrderInfoUtils.addPaySuccess(order.getId(), 1, "按单收款成功");
            } catch (Exception e) {
                logger.info("更新订单已付金额和订单状态失败:{}", JsonUtil.bean2JsonString(collectionNotifyDto));
                logger.error("更新订单已付金额和订单状态失败:", e);
                throw new ApplicationException(ResultEnum.COLLECTION_RECORD_FAIL);
            }
        }
    }

    /**
     * @param query
     * @Description: 查询线下付款的收款/付款信息.
     * @Param: [query]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    @Override
    public OfflinePayInfoResponseDTO offlinePayInfo(OfflinePayInfoRequestQuery query) throws Exception {
        if (Objects.isNull(query)) {
            throw new ApplicationException(ResultEnum.REQUEST_ID_NOT_FOUND);
        }
        OfflinePayInfoResponseDTO responseDTO = new OfflinePayInfoResponseDTO();
        Long buyerOrganizeId = null;//当前客户的组织id
        if (Integer.valueOf(0).equals(query.getOrderType())) {
            //查询子订单信息
            SaleOrderResponseDTO saleOrderResponseDTO = selectSaleOrder(query.getSaleOrderId(), query.getOrderCode());
            //如果子订单为空则查询父订单信息
            if (saleOrderResponseDTO == null) {
                SaleOrderSplitRecordResponseDTO recode = saleOrderSplitRecordClient.selectById(query.getSaleOrderId());
                if (recode == null) {
                    throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
                } else {
                    buyerOrganizeId = recode.getBuyerId();
                }
            } else {
                buyerOrganizeId = saleOrderResponseDTO.getBuyerId();
            }
        } else if(Integer.valueOf(1).equals(query.getOrderType())){
            SalePickGoodsInfoResponseDTO goodsInfoResponseDTO =
                    salePickGoodsInfoClient.selectById(query.getSaleOrderId());
            if (goodsInfoResponseDTO == null) {
                throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
            } else {
                buyerOrganizeId = goodsInfoResponseDTO.getBuyerId();
            }
        }else {
            buyerOrganizeId=appRuntimeEnv.getTopOrganization().getId();
        }

        FinanceBankAccountRequestQuery financeBankAccountRequestQuery = new FinanceBankAccountRequestQuery();
        financeBankAccountRequestQuery.setAppId(query.getAppId());
        financeBankAccountRequestQuery.setTenantId(query.getTenantId());
        financeBankAccountRequestQuery.setIsolationId(String.valueOf(buyerOrganizeId));
        financeBankAccountRequestQuery.setStatus(0);
        //客户组织
        Payload<List<FinanceBankAccountResponseDTO>> payload =
                financeBankAccountInfoClient.listFinanceBankAccounts(financeBankAccountRequestQuery);
        if (CollectionUtils.isEmpty(payload.getPayload())) {
            logger.info("未找到对应的付款信息:{}", buyerOrganizeId);
            return responseDTO;
        }
        List<OfflinePayInfoDTO> offlinePayInfoRequestDTOList = GeneralConvertUtils.convert2List(payload.getPayload(),
                OfflinePayInfoDTO.class);//客户组织银行信息
        responseDTO.setPayInfoList(offlinePayInfoRequestDTOList);//付款信息
        return responseDTO;
    }

    /**
     * @param query
     * @Description: 查询线下付款的收款信息.
     * @Param: [query]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflineCollectionResponseDTO
     * @Author: SongTao
     * @Date: 2020/8/5
     */
    @Override
    public OfflineCollectionResponseDTO offlineCollectionInfo(OfflinePayInfoRequestQuery query) throws Exception {
        if (Objects.isNull(query)) {
            throw new ApplicationException(ResultEnum.REQUEST_ID_NOT_FOUND);
        }
        Long sellerId = null;
        OfflineCollectionResponseDTO responseDTO = new OfflineCollectionResponseDTO();
        if (Integer.valueOf(0).equals(query.getOrderType())) {
            //查询子订单信息
            SaleOrderResponseDTO saleOrderResponseDTO = selectSaleOrder(query.getSaleOrderId(), query.getOrderCode());
            //如果子订单为空则查询父订单信息
            if (saleOrderResponseDTO == null) {
                SaleOrderSplitRecordResponseDTO recode = saleOrderSplitRecordClient.selectById(query.getSaleOrderId());
                if (recode == null) {
                    throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
                } else {
                    sellerId = recode.getSellerId();
                }
            } else {
                sellerId = saleOrderResponseDTO.getSellerId();
            }
        } else if(Integer.valueOf(1).equals(query.getOrderType())){
            SalePickGoodsInfoResponseDTO goodsInfoResponseDTO =
                    salePickGoodsInfoClient.selectById(query.getSaleOrderId());
            if (goodsInfoResponseDTO == null) {
                throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
            } else {
                sellerId = goodsInfoResponseDTO.getSellerId();
            }
        }
        else if(Integer.valueOf(2).equals(query.getOrderType())){
            Payload<FinanceCreditRepaymentBillResponseDTO> billPayload  =
                    financeCreditRepaymentBillClient.selectById(query.getSaleOrderId());
            if (!SUCCESS.equals(billPayload.getCode()) || null==billPayload.getPayload()) {
                throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
            } else {
                FinanceCreditRepaymentBillResponseDTO bill=GeneralConvertUtils.conv(billPayload.getPayload(),FinanceCreditRepaymentBillResponseDTO.class);
                sellerId =Long.valueOf(bill.getIsolationId()) ;
            }
        }

        FinanceBankAccountRequestQuery financeBankAccountRequestQuery = new FinanceBankAccountRequestQuery();
        financeBankAccountRequestQuery.setAppId(query.getAppId());
        financeBankAccountRequestQuery.setTenantId(query.getTenantId());
        financeBankAccountRequestQuery.setStatus(0);
        financeBankAccountRequestQuery.setIsolationId(String.valueOf(sellerId));
        financeBankAccountRequestQuery.setIsOffline(0);//设置默认只显示用于收款的账号
        //供应商组织
        Payload<List<FinanceBankAccountResponseDTO>> payloadList =
                financeBankAccountInfoClient.listFinanceBankAccounts(financeBankAccountRequestQuery);//供应商的收款信息
        if (CollectionUtils.isEmpty(payloadList.getPayload())) {
            logger.info("收款信息未找到对应的:{}", sellerId);
            return responseDTO;
        }
        List<OfflineCollectionInfoDTO> payInfoRequestDTOList =
                GeneralConvertUtils.convert2List(payloadList.getPayload(), OfflineCollectionInfoDTO.class);//供应商银行信息
        responseDTO.setCollectMoneyInfoList(payInfoRequestDTOList);//收款信息
        return responseDTO;
    }

    /**
     * @Description: 普通订单保存线下付款的收款信息.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveOfflinePayInfo(OfflinePayInfoRequestDTO requestDTO) throws Exception {
        if (Objects.isNull(requestDTO)) {
            throw new ApplicationException("请求参数为空.");
        }
        if (Objects.isNull(requestDTO.getCollectMoneyInfo()) || Objects.isNull(requestDTO.getPayInfo())) {
            throw new ApplicationException("收款信息/付款信息为空.");
        }
        SaleOrderResponseDTO saleOrderResponseDTO = new SaleOrderResponseDTO();
        saleOrderResponseDTO = selectSaleOrder(requestDTO.getSaleOrderId(), requestDTO.getOrderCode());//获取订单信息
        if (!Objects.isNull(saleOrderResponseDTO)) {
            if(getBigDecimal(saleOrderResponseDTO.getAccrueAmount()).subtract(getBigDecimal(saleOrderResponseDTO.getPayAmount())).compareTo(getBigDecimal(requestDTO.getPayAmount()))!=0 ){
                throw new ApplicationException("支付金额与待付款金额不一致");
            }
            FinanceCollectionPaymentRecordsDTO financeDto = new FinanceCollectionPaymentRecordsDTO();
            financeDto.setFinanceCollectionAdminRequestDTO(setFinanceCollection(saleOrderResponseDTO, requestDTO));
            financeDto.setFinancePaymentRecordsRequestDTO(setFinancePaymentRecords(saleOrderResponseDTO, requestDTO));
            financeDto.getFinancePaymentRecordsRequestDTO().setOrderType(0);//非提货单
            //支付类型：0-线上支付；1-线下支付
            Payload<Boolean> payload = financeCollectionClient.addPaymentRecords(financeDto, 1);
            if (payload.getPayload()) {
                //更新订单已付金额和订单状态
                SaleOrderInfoAmountEditDTO editDTO = new SaleOrderInfoAmountEditDTO();
                editDTO.setId(saleOrderResponseDTO.getId());
                editDTO.setAmount(saleOrderResponseDTO.getPayAmount().add(requestDTO.getPayAmount()));
                editDTO.setVersion(saleOrderResponseDTO.getVersion());
                editDTO.setStatus(saleOrderResponseDTO.getPaymentStatus());
                saleOrderInfoClient.updateOrderAmount(editDTO);
                //链路扭转
                toolActionExcutor.excute(LinkBusinessCodeEnum.BuyerPayment.name(), ListTypeEnum.SalesOrder.name(),
                        saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTenantId(),
                        saleOrderResponseDTO.getAppId());
                //更新支付记录所关联订单编号
                saleOrderInfoUtils.updatePayOrderCode(saleOrderResponseDTO.getId(), requestDTO.getOrderCode());
                saleOrderInfoUtils.addPaySuccess(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTicketType(),
                        "线下支付成功");
                saleOrderInfoUtils.updatePaymentType(requestDTO.getSaleOrderId(), requestDTO.getOrderCode(), 0, 1);
                OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
                orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
                orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
                orderOperationRecordRequestDTO.setCreatedTime(new Date());
                orderOperationRecordRequestDTO.setOrderId(requestDTO.getSaleOrderId());
                orderOperationRecordRequestDTO.setOperation("支付成功");
                orderOperationRecordRequestDTO.setOperationType(1);
                orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getTenantId());
                saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
                return Boolean.TRUE;
            } else {
                saleOrderInfoUtils.addPaySuccess(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTicketType(),
                        "线下支付失败");
            }
        } else {
            //继续查询父单
            SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO =
                    saleOrderSplitRecordClient.selectById(requestDTO.getSaleOrderId());
            Optional.ofNullable(saleOrderSplitRecordResponseDTO).orElseThrow(() -> new ApplicationException("订单不存在"));
            saleOrderResponseDTO = saleOrderSplitRecordResponseDTO.clone(SaleOrderResponseDTO.class);
            //根据父单查询订单列表
            Payload<List<SaleOrderResponseDTO>> payloadOrders =
                    saleOrderInfoClient.selectSaleOrderByParentCode(saleOrderSplitRecordResponseDTO.getCode());
            List<SaleOrderResponseDTO> saleOrderResponseDTOS =null;
            if (SUCCESS.equals(payloadOrders.getCode())) {
                saleOrderResponseDTOS =
                        GeneralConvertUtils.convert2List(payloadOrders.getPayload(),
                                SaleOrderResponseDTO.class);
                BigDecimal amount=BigDecimal.valueOf(0);
                for(SaleOrderResponseDTO saleOrder:saleOrderResponseDTOS){
                    amount=amount.add(getBigDecimal(saleOrder.getAccrueAmount()).subtract(saleOrder.getPayAmount()));
                }
                if(requestDTO.getPayAmount().compareTo(amount)!=0){
                    throw new ApplicationException("支付金额与待付款金额不一致");
                }
            }
            FinanceCollectionPaymentRecordsDTO financeDto = new FinanceCollectionPaymentRecordsDTO();
            financeDto.setFinanceCollectionAdminRequestDTO(setFinanceCollection(saleOrderResponseDTO, requestDTO));
            financeDto.setFinancePaymentRecordsRequestDTO(setFinancePaymentRecords(saleOrderResponseDTO, requestDTO));
            financeDto.getFinancePaymentRecordsRequestDTO().setOrderType(0);//非提货单
            //支付类型：0-线上支付；1-线下支付
            Payload<Boolean> payload = financeCollectionClient.addPaymentRecords(financeDto, 1);
            if (payload.getPayload()) {
                //更新父单付款状态和支付金额
                BigDecimal payAmount = saleOrderSplitRecordResponseDTO.getPayAmount().add(requestDTO.getPayAmount());
                PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
                payStatusQuery.setAppId(saleOrderSplitRecordResponseDTO.getAppId());
                payStatusQuery.setTenantId(saleOrderSplitRecordResponseDTO.getTenantId());
                payStatusQuery.setId(saleOrderSplitRecordResponseDTO.getId());
                payStatusQuery.setTotalCollectionAmount(saleOrderSplitRecordResponseDTO.getAccrueAmount());//应收金额
                payStatusQuery.setCollectionAmount(payAmount);//已付款金额
                //Integer status = getPayStatus(payStatusQuery);//支付状态 不从链路获取状态更新
                saleOrderSplitRecordResponseDTO.setPayAmount(payAmount);
                //saleOrderSplitRecordResponseDTO.setPaymentStatus(status);不从链路获取状态更新
                saleOrderInfoUtils.addPaySuccess(saleOrderSplitRecordResponseDTO.getId(),
                        saleOrderSplitRecordResponseDTO.getTicketType(), "线下支付成功");
                saleOrderInfoUtils.updateSaleOrderInfoCode(2, saleOrderSplitRecordResponseDTO.getCode(),
                        requestDTO.getOrderCode());
                saleOrderInfoUtils.updatePaymentType(requestDTO.getSaleOrderId(), requestDTO.getOrderCode(), 0, 1);
                saleOrderSplitRecordClient.updateById(saleOrderSplitRecordResponseDTO.getId(),
                        saleOrderSplitRecordResponseDTO.clone(SaleOrderSplitRecordRequestDTO.class));
                //如果付款金额大于等于0，即代付金额少于0
                if (payAmount.compareTo(saleOrderSplitRecordResponseDTO.getAccrueAmount()) >= 0) {
                    //根据父单查询订单列表
                    payloadOrders =
                            saleOrderInfoClient.selectSaleOrderByParentCode(saleOrderSplitRecordResponseDTO.getCode());
                    if (SUCCESS.equals(payloadOrders.getCode())) {
                        saleOrderResponseDTOS =
                                GeneralConvertUtils.convert2List(payloadOrders.getPayload(),
                                        SaleOrderResponseDTO.class);
                        if (CollectionUtil.isNotEmpty(saleOrderResponseDTOS)) {
                            for (SaleOrderResponseDTO saleOrderdto : saleOrderResponseDTOS) {
                                //如果父订单已支付完成，则改变子订单为支付金额=应收金额
                                saleOrderdto.setPayAmount(saleOrderdto.getAccrueAmount());
                                updateOfflineOrderPayInfo(saleOrderdto);
                            }
                        }
                    }
                }
            }
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(requestDTO.getSaleOrderId());
            orderOperationRecordRequestDTO.setOperation("支付成功");
            orderOperationRecordRequestDTO.setOperationType(1);
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
            return Boolean.TRUE;
        }
        saleOrderInfoUtils.addPaySuccess(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTicketType(), "线下支付失败");
        return Boolean.FALSE;
    }

    /**
     * @param query
     * @Description: 确认订单的订单信息.
     * @Param: [saleOrderAscertainRequestDTO]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.saleOrderAscertainInfo.SaleOrderAscertainResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/27
     */
    @Override
    public SaleOrderAscertainResponseDTO ascertainOrderInfo(SaleOrderAscertainRequestQuery query) throws Exception {
        SaleOrderAscertainResponseDTO responseDTO = new SaleOrderAscertainResponseDTO();
        responseDTO.setTicketType(query.getTicketType());//单据类型
        if (ObjectUtils.equals(query.getTicketType(), 0)) {//直供则直接获取当前用户的顶级组织
            GroupResultVO vo = appRuntimeEnv.getTopOrganization();
            if (Objects.nonNull(vo)) {
                responseDTO.setOrgName(vo.getName());
            }
        }
        if (ObjectUtils.equals(query.getTicketType(), 1)) {//如果非直供
            if (Objects.isNull(query.getOrgId())) {//组织id为空
                return new SaleOrderAscertainResponseDTO();
            }
            GroupResultVO group = iamUserService.getGroup(query.getOrgId(), appRuntimeEnv.getTenantId(),
                    appRuntimeEnv.getUserId(), appRuntimeEnv.getUsername());
            if (Objects.nonNull(group)) {
                responseDTO.setOrgName(group.getName());
            }
            return responseDTO;
        }
        return responseDTO;
    }

    /**
     * @Description: 支付完成后的订单信息.
     * @Param: [saleOrderId, code]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.PaymentCompletedResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/28
     */
    @Override
    public PaymentCompletedResponseDTO paymentCompletedInfo(Long saleOrderId, String code) throws Exception {
        if (Objects.isNull(saleOrderId)) {
            throw new ApplicationException("订单ID为空");
        }
        //先根据订单ID+编号去查
        SaleOrderInfoRequestQuery query = new SaleOrderInfoRequestQuery();
        query.setId(saleOrderId);
        query.setCode(code);
        Payload<SaleOrderInfoResponseDTO> dtoPayload = saleOrderInfoClient.searchSaleOrderInfoByQuery(query);
        SaleOrderInfoResponseDTO dto = GeneralConvertUtils.conv(dtoPayload.getPayload(),
                SaleOrderInfoResponseDTO.class);
        log.info("返回的支付成功的订单信息:{}", dtoPayload);
        PaymentCompletedResponseDTO paymentCompletedResponseDTO = new PaymentCompletedResponseDTO();
        List<SaleOrderItemMiddleResponseDTO> itemList = dto.getItems();//商品明细
        paymentCompletedResponseDTO.setSaleOrderCode(dto.getCode());//订单编号
        paymentCompletedResponseDTO.setArriveDate(dto.getArriveDate());//送达时间
        paymentCompletedResponseDTO.setConsignee(dto.getOrderConsigneeInfo().getConsignee());//收货人
        paymentCompletedResponseDTO.setMobile(dto.getOrderConsigneeInfo().getMobile());//手机号码
        paymentCompletedResponseDTO.setItemType(itemList.stream().filter(f -> Objects.nonNull(f.getSkuId())).map(SaleOrderItemMiddleResponseDTO::getSkuId).distinct().count());//商品种类
        paymentCompletedResponseDTO.setItemToTalQuantity(itemList.stream().mapToLong(SaleOrderItemMiddleResponseDTO::getSkuQuantity).sum());//商品总数量
        paymentCompletedResponseDTO.setItemTotalAmount(dto.getAccrueAmount());//总金额
        List<String> list = Arrays.asList(dto.getOrderConsigneeInfo().getProvinceName(),
                dto.getOrderConsigneeInfo().getCityName(),
                dto.getOrderConsigneeInfo().getAreaName(), dto.getOrderConsigneeInfo().getStreetName(),
                dto.getOrderConsigneeInfo().getDetailedAddress());//拼接地址
        paymentCompletedResponseDTO.setAddress(String.join("", list));//地址
        return paymentCompletedResponseDTO;
    }

    /**
     * @Description: 收款单对象转化.
     * @Param: [dto, offlinePayInfoRequestDTO]
     * @return: com.deepexi.dd.middle.finance.domain.dto.FinanceCollectionAdminRequestDTO
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    private FinanceCollectionAdminRequestDTO setFinanceCollection(SaleOrderResponseDTO dto,
                                                                  OfflinePayInfoRequestDTO offlinePayInfoRequestDTO) {
        OfflineCollectionInfoDTO collectionInfoDTO = offlinePayInfoRequestDTO.getCollectMoneyInfo();//收款信息
        OfflinePayInfoDTO payInfoDTO = offlinePayInfoRequestDTO.getPayInfo();//付款信息
        FinanceCollectionAdminRequestDTO requestDTO = new FinanceCollectionAdminRequestDTO();
        requestDTO.setAppId(offlinePayInfoRequestDTO.getAppId());//企业id
        requestDTO.setTenantId(offlinePayInfoRequestDTO.getTenantId());//租户id
        requestDTO.setCustomerId(dto.getPartnerId());//客户id
        requestDTO.setCustomerName(dto.getPartnerName());//客户名称
        requestDTO.setHandlersId(appRuntimeEnv.getUserId());//经手人id
        requestDTO.setHandlersName(appRuntimeEnv.getUsername());//经手人名称
        requestDTO.setAccountName(collectionInfoDTO.getName());//收款账户名称
        requestDTO.setAccountNumber(collectionInfoDTO.getBankAccount());//收款银行账户
        requestDTO.setPaymentAccountName(payInfoDTO.getName());//付款账户名称
        requestDTO.setPaymentAccountNumber(payInfoDTO.getBankAccount());//付款银行账户
        requestDTO.setWay(1);//'支付类型：0信用支付，1-线下付款， 2-线上付款， 3-余额支付'
        requestDTO.setReceiptsTime(dto.getTicketDate());//单据日期
        requestDTO.setCredentialsUrl(offlinePayInfoRequestDTO.getCredentialsUrl());//凭证urL
        requestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getType(),
                IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getPrefix()
                , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getLen()));
        //收款单code
        requestDTO.setIsolationId(String.valueOf(dto.getSellerId()));//组织id
        requestDTO.setAccountId(collectionInfoDTO.getId());//收款账户id
        requestDTO.setSettlementId(dto.getPartnerId());//结算单位id
        requestDTO.setSettlementName(dto.getPartnerName());//结算单位名称
        requestDTO.setAmount(offlinePayInfoRequestDTO.getPayAmount());//支付金额
        //订单信息
        FinanceCollectionOrderAdminRequesDTO financeCollectionOrderAdminRequesDTO =
                new FinanceCollectionOrderAdminRequesDTO();
        financeCollectionOrderAdminRequesDTO.setOrderId(dto.getId());//订单id
        financeCollectionOrderAdminRequesDTO.setOrderCode(dto.getCode());//订单编号
        financeCollectionOrderAdminRequesDTO.setSettlementAmount(offlinePayInfoRequestDTO.getPayAmount());//支付金额
        financeCollectionOrderAdminRequesDTO.setType(0);//单据类型：0-销售订单、1-销售退货单
        List<FinanceCollectionOrderAdminRequesDTO> list = Lists.newArrayList();
        list.add(financeCollectionOrderAdminRequesDTO);
        requestDTO.setDatas(list);
        return requestDTO;
    }

    /**
     * @Description: 支付流水对象转化.
     * @Param: [dto, offlinePayInfoRequestDTO]
     * @return: com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsRequestDTO
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    private FinancePaymentRecordsRequestDTO setFinancePaymentRecords(SaleOrderResponseDTO dto,
                                                                     OfflinePayInfoRequestDTO offlinePayInfoRequestDTO) {
        OfflineCollectionInfoDTO collectionInfoDTO = offlinePayInfoRequestDTO.getCollectMoneyInfo();//收款信息
        OfflinePayInfoDTO payInfoDTO = offlinePayInfoRequestDTO.getPayInfo();//付款信息
        FinancePaymentRecordsRequestDTO requestDTO = new FinancePaymentRecordsRequestDTO();
        requestDTO.setAppId(offlinePayInfoRequestDTO.getAppId());//企业id
        requestDTO.setTenantId(offlinePayInfoRequestDTO.getTenantId());//租户id
        requestDTO.setCustomerName(dto.getPartnerName());//客户名称
        requestDTO.setOrderId(offlinePayInfoRequestDTO.getSaleOrderId());//订单id
        requestDTO.setOrderCode(dto.getCode());//订单编号
        requestDTO.setPayType(1);//'支付类型：0信用支付，1-线下付款， 2-线上付款， 3-余额支付'
        Date now = new Date();
        requestDTO.setReceiptsTime(now);//支付时间
        requestDTO.setAmount(offlinePayInfoRequestDTO.getPayAmount());//支付金额
        requestDTO.setProceedsAccount(collectionInfoDTO.getName());//收款账户名称
        requestDTO.setPaymentAccount(payInfoDTO.getName());//付款账户名称
        requestDTO.setProceedsBankAccount(collectionInfoDTO.getBankAccount());//收款银行账户
        requestDTO.setPaymentBankAccount(payInfoDTO.getBankAccount());//付款银行账户
        requestDTO.setIsolationId(dto.getSellerId() + "");
        requestDTO.setOrgId(dto.getAscriptionOrgId());
        requestDTO.setPaymentVoucher(offlinePayInfoRequestDTO.getCredentialsUrl());//凭证
        String code = identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getType(),
                IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getPrefix()
                , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getLen());
        requestDTO.setCode(code);//支付流水
        return requestDTO;
    }

    @Override
    public List<SaleOrderBtnResponseDTO> exportSaleOrderPage(SaleOrderInfoQuery query) throws Exception {
//        PageMethod.startPage(query.getPage(), query.getSize(), "payment_status asc, ticket_date desc");
//        //userId找到组织    组织=隔离
//        SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = query.clone(SaleOrderInfoRequestQuery.class);
//        GetUserIdGroupResultVO groupResultVO = iamUserService.getUserGroupInfo();
//        if (null != groupResultVO && CollectionUtil.isNotEmpty(groupResultVO.getGroupInfo())) {
//            String idPath = groupResultVO.getGroupInfo().get(0).getIdPath();
//            String[] idArray = idPath.split("\\/");
//            if (idArray.length > 2) {
//                String orgId = idArray[2];
//               // saleOrderInfoRequestQuery.setIsolationId(orgId);
//                saleOrderInfoRequestQuery.setSellerId(Long.valueOf(orgId));
//            }
//        }
//        Payload<List<SaleOrderInfoResponseDTO>> payload =
//                saleOrderInfoClient.listSaleOrderInfos(saleOrderInfoRequestQuery);
//        List<SaleOrderBtnResponseDTO> saleOrders = GeneralConvertUtils.convert2List(payload.getPayload(),
//                                                                                    SaleOrderBtnResponseDTO.class);
//        List<Long> orderids = null;
//        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(saleOrders)) {
//            orderids = saleOrders.stream().map(SaleOrderBtnResponseDTO::getId).collect(Collectors.toList());
//        }
//        List<SaleOutTaskMiddleResponseDTO> saleOutList = null;
//        if (!CollectionUtil.isEmpty(orderids)) {
//            SaleOutTaskMiddleRequestQuery outTask = new SaleOutTaskMiddleRequestQuery();
//            outTask.setSaleOrderIdList(orderids);
//            saleOutList = saleOutTaskClient.findSaleOutTaskByIds(outTask);
//        }
//        //拼接数据销售订单信息
//        return splicingSaleOrderInfo(saleOrders, saleOutList);


        PageMethod.startPage(query.getPage(), query.getSize(), "payment_status asc, ticket_date desc");
        //userId找到组织    组织=隔离

        SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = query.clone(SaleOrderInfoRequestQuery.class);
        saleOrderInfoRequestQuery.setSize(5000);
        saleOrderInfoRequestQuery.setPage(1);
//        GetUserIdGroupResultVO groupResultVO = iamUserService.getUserGroupInfo();
//        if (CollectionUtil.isNotEmpty(groupResultVO.getGroupInfo())) {
//            String idPath = groupResultVO.getGroupInfo().get(0).getIdPath();
//            String[] idArray = idPath.split("\\/");
//            if (idArray.length > 2) {
//                String orgId = idArray[2];
////                saleOrderInfoRequestQuery.setSellerId(Long.valueOf(orgId));
//            }
//        } else {
//            throw new ApplicationException("查不到用户");
//        }

        if (appRuntimeEnv.getTopOrganization().getId().equals(appRuntimeEnv.getUserOrganization().getId())) {
            saleOrderInfoRequestQuery.setSellerId(appRuntimeEnv.getUserOrganization().getId());
        } else {
            saleOrderInfoRequestQuery.setAscriptionOrgId(appRuntimeEnv.getUserOrganization().getId());
        }
        //如果seller_id没传取顶级组织id
        if (null == saleOrderInfoRequestQuery.getSellerId()) {
            saleOrderInfoRequestQuery.setSellerId(appRuntimeEnv.getTopOrganization().getId());
        }
//        Payload<List<SaleOrderInfoResponseDTO>> payload = saleOrderInfoClient.listSaleOrderInfos
//        (saleOrderInfoRequestQuery);
//        List<SaleOrderBtnResponseDTO> saleOrderBtnResponseDTOS = GeneralConvertUtils.convert2List(payload
//        .getPayload(),SaleOrderBtnResponseDTO.class);
        Payload<PageBean<SaleOrderInfoResponseDTO>> payload =
                saleOrderInfoClient.listSaleOrderInfosPage(saleOrderInfoRequestQuery);
        PageBean<SaleOrderBtnResponseDTO> result = GeneralConvertUtils.convert2PageBean(payload.getPayload(),
                SaleOrderBtnResponseDTO.class);
        List<SaleOrderBtnResponseDTO> saleOrderBtnResponseDTOS = result.getContent();
        try {
            setPayAmount(saleOrderBtnResponseDTOS);
        } catch (Exception e) {
            throw new ApplicationException("查询支付金额出错");
        }
        return saleOrderBtnResponseDTOS;
    }

    @Override
    public Payload<Boolean> deletePlanById(Long id) throws Exception {
        Payload<SaleOrderInfoResponseDTO> payload = saleOrderInfoClient.selectById(id);
        if (payload == null || payload.getPayload() == null) {
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = GeneralConvertUtils.conv(payload.getPayload(),
                SaleOrderInfoResponseDTO.class);
        if (!StatusCodeEnum.ToTakeOrder.getCode().equals(saleOrderInfoResponseDTO.getStatus())) {
            throw new ApplicationException(ResultEnum.ORDER_STATUS_IS_NOT_DRAFT);
        }
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        return saleOrderInfoClient.deleteByIdIn(ids);
    }

    //拼接数据销售订单信息
    private List<SaleOrderBtnResponseDTO> splicingSaleOrderInfo(List<SaleOrderBtnResponseDTO> result,
                                                                List<SaleOutTaskMiddleResponseDTO> saleOutList) {
      /*  PageBean pageBean = new PageBean();
        pageBean.setTotalPages(result.getTotalPages());
        pageBean.setSize(result.getSize());
        pageBean.setTotalElements(result.getTotalElements());
        pageBean.setNumberOfElements(result.getNumberOfElements());
        pageBean.setNumber(result.getNumber());*/
        List<SaleOrderBtnResponseDTO> resultList = new ArrayList<>();
        result.forEach(e -> {
            if (!CollectionUtil.isEmpty(saleOutList)) {
                SaleOutTaskMiddleResponseDTO saleOutTaskMiddleResponseDTO =
                        saleOutList.stream().filter(a -> a.getSaleOrderId().equals(e.getId())).findFirst().orElseGet(SaleOutTaskMiddleResponseDTO::new);
                OrderConsigneeInfoResponseDTO orderCon = e.getOrderConsigneeInfo();
                if (null != orderCon) {
                    orderCon.setSignTime(saleOutTaskMiddleResponseDTO.getSignTime());
                    e.setOrderConsigneeInfo(orderCon);
                }
            }
            resultList.add(e);
        });
        try {
            setPayAmount(resultList);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ApplicationException("查询支付金额出错");
        }
        //pageBean.setContent(resultList);
        return resultList;
    }

    private void setPayAmount(List<SaleOrderBtnResponseDTO> content) throws Exception {
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

                //提货计划id对应提货计划
//                Map<Long,SalePickGoodsOrderSkuResponseDTO> pickMap = salePickGoodsOrderSkuResponseDTOS.stream().collect
//                        (Collectors.toMap(SalePickGoodsOrderSkuResponseDTO::getPickGoodsInfoId,e->e));

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
//                                            for(FinancePaymentRecordsResponseDTO financePaymentRecordsResponseDTO: recordMap.get(salePickGoodsOrderSkuResponseDTO.getPickGoodsInfoId())){
//                                                amount = amount.add(financePaymentRecordsResponseDTO.getAmount());
//                                            }
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
    }

    public Boolean cancelPlanOrder(SaleOrderCancelRequestDTO saleOrderCancelRequestDTO,
                                   SaleOrderInfoResponseDTO saleOrderInfoResponseDTO) throws Exception {
        ToolStatusRequestQuery query = new ToolStatusRequestQuery();
        query.setActionCode(saleOrderCancelRequestDTO.getActionCode());
        query.setAppId(saleOrderCancelRequestDTO.getAppId());
        query.setTenantId(saleOrderCancelRequestDTO.getTenantId());
        //统一使用SalesOrder类型
        query.setListType(ListTypeEnum.SalesOrder.name());
//        query.setListType("OrderPlan");
        Payload<Integer> codePayload = toolStatusClient.getStatusCode(query);

        if ("0".equals(codePayload.getCode())) {
            Integer code = codePayload.getPayload();
            com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO = new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO();
            saleOrderInfoStatusEditRequestDTO.setStatus(code);
            saleOrderInfoStatusEditRequestDTO.setId(saleOrderCancelRequestDTO.getId());
            Boolean result = saleOrderInfoClient.updateOrderStatus(saleOrderInfoStatusEditRequestDTO).getPayload();
            //记录操作记录
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(saleOrderCancelRequestDTO.getId());
            orderOperationRecordRequestDTO.setOperation("取消订单成功");
            orderOperationRecordRequestDTO.setOperationType(1);
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            orderOperationRecordRequestDTO.setRemark(saleOrderCancelRequestDTO.getRemark());
            orderOperationRecordRequestDTO.setActionCode(saleOrderCancelRequestDTO.getActionCode());
            orderOperationRecordRequestDTO.setRadioName(saleOrderCancelRequestDTO.getRadioName());
            saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
            return result;
        } else {
            throw new ApplicationException("取消订单失败");
        }
    }

    @Override
    public SaleOrderPayResponseDTO salePickPaySaleOrder(SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        logger.info("提货计划进入预下单:" + JsonUtil.bean2JsonString(saleOrderPayRequestDTO));
        SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                salePickGoodsInfoClient.selectById(saleOrderPayRequestDTO.getId());
        if (salePickGoodsInfoResponseDTO == null) {
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        //提货单--->普通订单
        SaleOrderInfoResponseDTO salePickOrderInfoResponseDTO =
                SaleOrderInfoDtoHelper.convertToSaleOrderInfoResponseDTO(salePickGoodsInfoResponseDTO);
        //获取伙伴信息
        BusinessPartnerResponseDTO businessPartner =
                saleOrderInfoUtils.getBusinessPartner(saleOrderPayRequestDTO.getTenantId(),
                        saleOrderPayRequestDTO.getAppId(), salePickGoodsInfoResponseDTO.getBuyerId());
        if (null == businessPartner) {
            throw new ApplicationException("获取伙伴信息失败");
        }
        Long partnerId = businessPartner.getId();
        Payload<FinancePayInfoResponseDTO> financePayInfoResponseDTOPayload =
                financePayInfoClient.getByPartneId(partnerId);
        if (!SUCCESS.equals(financePayInfoResponseDTOPayload.getCode()) || (SUCCESS.equals(financePayInfoResponseDTOPayload.getCode()) && financePayInfoResponseDTOPayload.getPayload() == null)) {
            throw new ApplicationException("未找到收款账户");
        }
        FinancePayInfoResponseDTO financePayInfoResponseDTO =
                GeneralConvertUtils.conv(financePayInfoResponseDTOPayload.getPayload(),
                        FinancePayInfoResponseDTO.class);
        MerchantConfig merchantConfig = MerchantConfig
                .builder()
                .merchantCode(financePayInfoResponseDTO.getMerchantCode()) // 支付中心提供的商户code
                .middleHost(middleHost) // 支付中心请求地址
                .middlePayPublicKey(financePayInfoResponseDTO.getPublicKey())
                .merchantPrivateKey(financePayInfoResponseDTO.getMerchantPrivateKey())
                .tenantId(partnerId + "") // 租户Id，多租户环境下的租户唯一标识
                .build();
        logger.info("组装请求参数:{}", JsonUtil.bean2JsonString(merchantConfig));
        PayRequestManager payRequestManager = PayRequestManagerFactory.create(merchantConfig);

        GetPayStatusRequest getPayStatusRequest = new GetPayStatusRequest();
        getPayStatusRequest.setOutOrderNo(salePickOrderInfoResponseDTO.getCode());
        getPayStatusRequest.setClientIp(clientIp);
        getPayStatusRequest.setTenantId(partnerId + "");
        getPayStatusRequest.setMerchantCode(financePayInfoResponseDTO.getMerchantCode());
        //组装提货单预下单的参数
        PrePlaceOrderRequest request = new PrePlaceOrderRequest();
        request.setOutOrderNo(salePickOrderInfoResponseDTO.getCode());
        request.setAmount(salePickOrderInfoResponseDTO.getAccrueAmount());
        request.setBody("商品描述");
        request.setClientIp(clientIp);
        request.setDescription("订单详细信息");
        request.setSubject("商品共" + salePickOrderInfoResponseDTO.getQuantity() + "件");
        request.setTenantId(partnerId + "");  //伙伴Id
        request.setMerchantCode(financePayInfoResponseDTO.getMerchantCode()); //商户code
        request.setPayExpireTime(DateUtils.addMinutes(new Date(), addMinute));
        request.setCurrencyType("CNY");
        request.setReturnUrl(appendUrl(financePayInfoResponseDTO.getMerchantCode(),
                salePickOrderInfoResponseDTO.getCode(), partnerId));
        SaleOrderPayResponseDTO result = new SaleOrderPayResponseDTO();
        // 设置收银台的地址.
        result.setReturnUrl(request.getReturnUrl());
        SaleOrderPayResponseDTO saleOrderPayResponseDTO = this.torsionStatus(request, result, getPayStatusRequest,
                financePayInfoResponseDTO, salePickOrderInfoResponseDTO, payRequestManager);
        SalePickGoodsInfoRequestDTO spg = new SalePickGoodsInfoRequestDTO();
        spg.setPaymentType(2);
        salePickGoodsInfoClient.updateById(salePickGoodsInfoResponseDTO.getId(), spg);
        //TODO 获取订单状态
        return saleOrderPayResponseDTO;
    }

    /**
     * 线上支付-还款单
     *
     * @param saleOrderPayRequestDTO
     * @return
     * @throws Exception
     */
    @Override
    public SaleOrderPayResponseDTO financeCreditPaySaleOrder(SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        logger.info("还款单进入预下单:" + JsonUtil.bean2JsonString(saleOrderPayRequestDTO));
        Payload<FinanceCreditRepaymentBillResponseDTO> payload =
                financeCreditRepaymentBillClient.selectById(saleOrderPayRequestDTO.getId());
        if (!SUCCESS.equals(payload.getCode()) || payload.getPayload() == null) {
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        FinanceCreditRepaymentBillResponseDTO bill = GeneralConvertUtils.conv(payload.getPayload(),
                FinanceCreditRepaymentBillResponseDTO.class);
        //提货单--->普通订单

        //获取伙伴信息
        BusinessPartnerResponseDTO businessPartner =
                saleOrderInfoUtils.getBusinessPartner(bill.getTenantId(),
                        bill.getAppId(), Long.valueOf(bill.getIsolationId()));
        if (null == businessPartner) {
            throw new ApplicationException("获取伙伴信息失败");
        }
        Long partnerId = businessPartner.getId();
        Payload<FinancePayInfoResponseDTO> financePayInfoResponseDTOPayload =
                financePayInfoClient.getByPartneId(partnerId);
        if (!SUCCESS.equals(financePayInfoResponseDTOPayload.getCode()) || (SUCCESS.equals(financePayInfoResponseDTOPayload.getCode()) && financePayInfoResponseDTOPayload.getPayload() == null)) {
            throw new ApplicationException("未找到收款账户");
        }
        FinancePayInfoResponseDTO financePayInfoResponseDTO =
                GeneralConvertUtils.conv(financePayInfoResponseDTOPayload.getPayload(),
                        FinancePayInfoResponseDTO.class);
        MerchantConfig merchantConfig = MerchantConfig
                .builder()
                .merchantCode(financePayInfoResponseDTO.getMerchantCode()) // 支付中心提供的商户code
                .middleHost(middleHost) // 支付中心请求地址
                .middlePayPublicKey(financePayInfoResponseDTO.getPublicKey())
                .merchantPrivateKey(financePayInfoResponseDTO.getMerchantPrivateKey())
                .tenantId(partnerId + "") // 租户Id，多租户环境下的租户唯一标识
                .build();
        logger.info("组装请求参数:{}", JsonUtil.bean2JsonString(merchantConfig));
        PayRequestManager payRequestManager = PayRequestManagerFactory.create(merchantConfig);

        GetPayStatusRequest getPayStatusRequest = new GetPayStatusRequest();
        getPayStatusRequest.setOutOrderNo(bill.getCode());
        getPayStatusRequest.setClientIp(clientIp);
        getPayStatusRequest.setTenantId(partnerId + "");
        getPayStatusRequest.setMerchantCode(financePayInfoResponseDTO.getMerchantCode());
        //组装提货单预下单的参数
        PrePlaceOrderRequest request = new PrePlaceOrderRequest();
        request.setOutOrderNo(bill.getCode());
        request.setAmount(bill.getAmount());
        request.setBody("信用还款");
        request.setClientIp(clientIp);
        request.setDescription("还款详细信息");
        request.setSubject("还款金额:" + bill.getAmount());
        request.setTenantId(partnerId + "");  //伙伴Id
        request.setMerchantCode(financePayInfoResponseDTO.getMerchantCode()); //商户code
        request.setPayExpireTime(DateUtils.addMinutes(new Date(), addMinute));
        request.setCurrencyType("CNY");
        request.setReturnUrl(appendUrl(financePayInfoResponseDTO.getMerchantCode(),
                bill.getCode(), partnerId));
        SaleOrderPayResponseDTO result = new SaleOrderPayResponseDTO();
        // 设置收银台的地址.
        result.setReturnUrl(request.getReturnUrl());
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = new SaleOrderInfoResponseDTO();
        saleOrderInfoResponseDTO.setCode(bill.getCode());
        SaleOrderPayResponseDTO saleOrderPayResponseDTO = this.torsionStatus(request, result, getPayStatusRequest,
                financePayInfoResponseDTO, saleOrderInfoResponseDTO, payRequestManager);
        SalePickGoodsInfoRequestDTO spg = new SalePickGoodsInfoRequestDTO();
        spg.setPaymentType(2);
        //TODO 获取订单状态
        return saleOrderPayResponseDTO;
    }

    /**
     * 提货订单保存线下付款的收款信息
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Boolean saveSalePickOfflinePayInfo(@Valid OfflinePayInfoRequestDTO requestDTO) throws Exception {
        if (Objects.isNull(requestDTO)) {
            throw new ApplicationException("请求参数为空.");
        }
        logger.info("提货计划进入线下付款:" + JsonUtil.bean2JsonString(requestDTO));
        if (Objects.isNull(requestDTO.getCollectMoneyInfo()) || Objects.isNull(requestDTO.getPayInfo())) {
            throw new ApplicationException("提货单收款信息/提货单付款信息为空.");
        }
        SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                salePickGoodsInfoClient.selectById(requestDTO.getSaleOrderId());//拿到提货单信息
        if (Objects.isNull(salePickGoodsInfoResponseDTO)) {
            throw new ApplicationException(ResultEnum.REQUEST_ID_NOT_FOUND);
        }
        if (salePickGoodsInfoResponseDTO.getStatus().equals(StatusCodeEnum.ReceivedPayments.getCode())) {
            throw new ApplicationException(StatusCodeEnum.ReceivedPayments.getMsg() + ",请勿重复操作");
        }
        SaleOrderResponseDTO saleOrderResponseDTO =
                SaleOrderInfoDtoHelper.convertToSaleOrderResponseDTO(salePickGoodsInfoResponseDTO);
        //获取伙伴信息--卖家
        BusinessPartnerResponseDTO businessPartner = saleOrderInfoUtils.getBusinessPartner(requestDTO.getTenantId(),
                requestDTO.getAppId(), salePickGoodsInfoResponseDTO.getBuyerId());
        if (null == businessPartner) {
            throw new ApplicationException("获取伙伴信息失败");
        }
        saleOrderResponseDTO.setPartnerId(businessPartner.getId());
        saleOrderResponseDTO.setPartnerName(businessPartner.getName());
        FinanceCollectionPaymentRecordsDTO financeDto = new FinanceCollectionPaymentRecordsDTO();
        financeDto.setFinanceCollectionAdminRequestDTO(setFinanceCollection(saleOrderResponseDTO, requestDTO));
        financeDto.setFinancePaymentRecordsRequestDTO(setFinancePaymentRecords(saleOrderResponseDTO, requestDTO));
        financeDto.getFinancePaymentRecordsRequestDTO().setOrderType(1);
        financeDto.getFinancePaymentRecordsRequestDTO().setOrgId(businessPartner.getOrgId());//买家
        financeDto.getFinancePaymentRecordsRequestDTO().setPartnerId(businessPartner.getId());//买家
        financeDto.getFinancePaymentRecordsRequestDTO().setCustomerName(salePickGoodsInfoResponseDTO.getCustomerName());//买家
        financeDto.getFinancePaymentRecordsRequestDTO().setOrderType(1);//提货计划

        //支付类型：0-线上支付；1-线下支付
        Payload<Boolean> payload = financeCollectionClient.addPaymentRecords(financeDto, 1);
        if (payload.getPayload()) {
        /*    //获取业务链路状态
            PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
            payStatusQuery.setAppId(requestDTO.getAppId());
            payStatusQuery.setTenantId(requestDTO.getTenantId());
            payStatusQuery.setId(saleOrderResponseDTO.getId());
            payStatusQuery.setTotalCollectionAmount(saleOrderResponseDTO.getAccrueAmount());//营收金额
            payStatusQuery.setCollectionAmount(requestDTO.getPayAmount());//已付款金额
            //Integer status = getPayStatus(payStatusQuery);//支付状态 不从链路获取*/
            //更新订单已付金额和订单状态
            SalePickGoodsInfoRequestDTO salePickGoodsInfoRequestDTO = new SalePickGoodsInfoRequestDTO();
            salePickGoodsInfoRequestDTO.setId(saleOrderResponseDTO.getId());
            salePickGoodsInfoRequestDTO.setPayAmount(salePickGoodsInfoResponseDTO.getPayAmount().add(requestDTO.getPayAmount()));
            salePickGoodsInfoRequestDTO.setVersion(saleOrderResponseDTO.getVersion() + 1);
            salePickGoodsInfoRequestDTO.setStatus(salePickGoodsInfoResponseDTO.getStatus());
            salePickGoodsInfoRequestDTO.setPaymentType(1);
            salePickGoodsInfoRequestDTO.setPaymentStatus(salePickGoodsInfoResponseDTO.getPaymentStatus());
            salePickGoodsInfoClient.updateById(saleOrderResponseDTO.getId(), salePickGoodsInfoRequestDTO);
            //销售单更新支付记录所关联订单编号
            saleOrderInfoUtils.addPaySuccess(saleOrderResponseDTO.getId(), 2, "线下支付成功");
            saleOrderInfoUtils.updatePayOrderCode(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getCode());
            saleOrderInfoUtils.updatePaymentType(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getCode(), 1, 1);

            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * 还款保存线下付款的收款信息
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Boolean saveRepaymentOfflinePayInfo(@Valid OfflinePayInfoRequestDTO requestDTO) throws Exception {
        if (Objects.isNull(requestDTO)) {
            throw new ApplicationException("请求参数为空.");
        }
        logger.info("还款进入线下付款:" + JsonUtil.bean2JsonString(requestDTO));
        if (Objects.isNull(requestDTO.getCollectMoneyInfo()) || Objects.isNull(requestDTO.getPayInfo())) {
            throw new ApplicationException("还款单收款信息/还款单付款信息为空.");
        }
        //查询还款单
        Payload<FinanceCreditRepaymentBillResponseDTO> billPayload =
                financeCreditRepaymentBillClient.selectById(requestDTO.getSaleOrderId());

        if (!SUCCESS.equals(billPayload.getCode()) || billPayload.getPayload() == null) {
            logger.error("未找到信用账单:{},结果:{}", requestDTO.getSaleOrderId(), JsonUtil.bean2JsonString(billPayload));
            throw new ApplicationException(ResultEnum.REQUEST_ID_NOT_FOUND);
        }
        FinanceCreditRepaymentBillResponseDTO billResponseDTO = GeneralConvertUtils.conv(billPayload.getPayload(),
                FinanceCreditRepaymentBillResponseDTO.class);
        if(billResponseDTO.getStatus().equals(34)||billResponseDTO.getStatus().equals(24)){
            throw new ApplicationException("还款失败,该账单已还款");
        }
        requestDTO.setPayAmount(billResponseDTO.getAmount());
        //获取伙伴信息--买家
        BusinessPartnerRequestQuery partnerRequestQuery=new BusinessPartnerRequestQuery();
        partnerRequestQuery.setTenantId(billResponseDTO.getTenantId());
        partnerRequestQuery.setAppId(billResponseDTO.getAppId());
        partnerRequestQuery.setId(billResponseDTO.getPartnerId());
        Payload<BusinessPartnerResponseDTO> partnerPayload=  businessPartnerClient.getPartner(partnerRequestQuery);
        if(!SUCCESS.equals(partnerPayload.getCode()) || null==partnerPayload.getPayload()){
            throw new ApplicationException("未查询到买家业务伙伴");
        }
        BusinessPartnerResponseDTO businessPartner= GeneralConvertUtils.conv(partnerPayload.getPayload(),BusinessPartnerResponseDTO.class);

        SaleOrderResponseDTO saleOrderResponseDTO = new SaleOrderResponseDTO();
        saleOrderResponseDTO.setPartnerId(businessPartner.getId());
        saleOrderResponseDTO.setPartnerName(businessPartner.getName());
        saleOrderResponseDTO.setId(billResponseDTO.getId());
        saleOrderResponseDTO.setCode(billResponseDTO.getCode());
        saleOrderResponseDTO.setSellerId(Long.valueOf(billResponseDTO.getIsolationId()));
        saleOrderResponseDTO.setTicketDate(billResponseDTO.getCreatedTime());
        saleOrderResponseDTO.setCustomerName(businessPartner.getName());
        saleOrderResponseDTO.setCustomerId(businessPartner.getId());
        FinanceCollectionPaymentRecordsDTO financeDto = new FinanceCollectionPaymentRecordsDTO();
        financeDto.setFinanceCollectionAdminRequestDTO(setFinanceCollection(saleOrderResponseDTO, requestDTO));
        financeDto.setFinancePaymentRecordsRequestDTO(setFinancePaymentRecords(saleOrderResponseDTO, requestDTO));
        financeDto.getFinancePaymentRecordsRequestDTO().setOrderType(2);
        financeDto.getFinancePaymentRecordsRequestDTO().setOrgId(businessPartner.getOrgId());//买家
        financeDto.getFinancePaymentRecordsRequestDTO().setPartnerId(businessPartner.getId());//卖家
        financeDto.getFinancePaymentRecordsRequestDTO().setOrderType(2);//提货计划

        //支付类型：0-线上支付；1-线下支付
        Payload<Boolean> payload = financeCollectionClient.addPaymentRecords(financeDto, 1);
        if (payload.getPayload()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * @param salePickId
     * @return 提货单支付成功页面
     */
    @Override
    public PaymentCompletedResponseDTO paymentSalePickInfo(Long salePickId) {
        if (Objects.isNull(salePickId)) {
            throw new ApplicationException("提货订单ID为空");
        }
        SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = salePickGoodsInfoClient.selectById(salePickId);
        //拿到订单id关联的所有信息
        Optional.ofNullable(salePickGoodsInfoResponseDTO).orElseThrow(() -> new ApplicationException("提货单id错误"));
        PaymentCompletedResponseDTO paymentCompletedResponseDTO = new PaymentCompletedResponseDTO();
        paymentCompletedResponseDTO.setSaleOrderCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());//订单编号
        paymentCompletedResponseDTO.setArriveDate(salePickGoodsInfoResponseDTO.getRequiredTime());//送达时间
        paymentCompletedResponseDTO.setConsignee(salePickGoodsInfoResponseDTO.getReceiveGoodsMan());//收货人
        paymentCompletedResponseDTO.setMobile(salePickGoodsInfoResponseDTO.getContactWay());//手机号码
        paymentCompletedResponseDTO.setIdCardNumber(salePickGoodsInfoResponseDTO.getIdCardNumber());//身份证号
        paymentCompletedResponseDTO.setCarNumberZt(salePickGoodsInfoResponseDTO.getCarNumberZt());//车牌号
        paymentCompletedResponseDTO.setPickGoodsTimeZt(salePickGoodsInfoResponseDTO.getPickGoodsTimeZt());//提货时间
        paymentCompletedResponseDTO.setReceiveHouseIdWc(salePickGoodsInfoResponseDTO.getReceiveHouseIdWc());//收货仓库Id 外仓
        paymentCompletedResponseDTO.setReceiveHouseNameWc(salePickGoodsInfoResponseDTO.getReceiveHouseNameWc());
        paymentCompletedResponseDTO.setAddress(salePickGoodsInfoResponseDTO.getSalePickGoodsConsigneeResponseDTOS().get(0).getProvinceName()
                + salePickGoodsInfoResponseDTO.getSalePickGoodsConsigneeResponseDTOS().get(0).getCityName()
                + salePickGoodsInfoResponseDTO.getSalePickGoodsConsigneeResponseDTOS().get(0).getAreaName()
                + salePickGoodsInfoResponseDTO.getSalePickGoodsConsigneeResponseDTOS().get(0).getDetailedAddress());
        //收货仓库name 外仓
        paymentCompletedResponseDTO.setProjectNameGd(salePickGoodsInfoResponseDTO.getProjectNameGd());//工程名称
        paymentCompletedResponseDTO.setDetailedAddress(salePickGoodsInfoResponseDTO.getDetailedAddress());//详细地址
        paymentCompletedResponseDTO.setPickGoodsWayZt(salePickGoodsInfoResponseDTO.getPickGoodsWayZt());//区分标识

        SalePickGoodsOrderSkuRequestQuery query = new SalePickGoodsOrderSkuRequestQuery();//查询提货单下item信息
        query.setPickGoodsInfoId(salePickId);
        List<SalePickGoodsOrderSkuResponseDTO> salePickGoodsOrderSkuResponseDTOS =
                salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkus(query);
        paymentCompletedResponseDTO.setItemType(salePickGoodsOrderSkuResponseDTOS.stream().filter(f -> Objects.nonNull(f.getSkuId())).map(SalePickGoodsOrderSkuResponseDTO::getSkuId).distinct().count());//商品种类
        paymentCompletedResponseDTO.setItemToTalQuantity(salePickGoodsOrderSkuResponseDTOS.stream().mapToLong(SalePickGoodsOrderSkuResponseDTO::getPickNum).sum());//商品总发货数量
        paymentCompletedResponseDTO.setItemTotalAmount(salePickGoodsInfoResponseDTO.getTotalGoodsMoney());//总金额
//        paymentCompletedResponseDTO.setAddress(salePickGoodsInfoResponseDTO.getReceiveAddress());//地址
        paymentCompletedResponseDTO.setStatus(salePickGoodsInfoResponseDTO.getStatus());//订单状态
        return paymentCompletedResponseDTO;
    }


    /**
     * 获取实时库存
     *
     * @return
     */
    @Override
    public List<StockResponseDTO> getRealTimeStock(StockFindAllPostQuery stockFindAllPostQuery) throws Exception {
        if(null == stockFindAllPostQuery.getSkuCode()){
            throw new ApplicationException("传入的skuCode为空");
        }
        //stockSwitch 为true 查询中台  false查询云仓
        if (stockSwitch) {
            //获取当前账号可操作仓库（目前用于提货订单的审核）
            List<DepotFindPagePostResponseDTODepot> content = getOperateDepot(stockFindAllPostQuery);
            //获取仓库的库存数据
            if (CollectionUtil.isNotEmpty(content)) {
                List<StockResponseDTO> stockResponseDTOS = GeneralConvertUtils.convert2List(content, StockResponseDTO.class);
                //过滤出禁用的仓库
                List<StockResponseDTO> collect = stockResponseDTOS.stream().filter(item -> item.getIsEnabled() == 1).collect(Collectors.toList());
                //获取可用仓库的ID
                List<Long> depotIdList = collect.stream().map(StockResponseDTO::getId).collect(Collectors.toList());
                //获取可用仓库改SKU
                StockFindAllPostRequestDTO sf = new StockFindAllPostRequestDTO();
                sf.setDepotIdList(depotIdList);
                sf.setSkuId(stockFindAllPostQuery.getSkuId());
                sf.setPage(-1);
                PayloadStockFindAllPostResponseDTO stockAll = stockSdkApi.findStockAll(sf);
                List<StockFindAllPostResponseDTOStock> stockList = stockAll.getPayload().getStockList();
                collect.forEach(stock -> {
                    if (!CollectionUtil.isEmpty(stockList)) {
                        for (int i = 0; i < stockList.size(); i++) {
                            if (stock.getId().equals(stockList.get(i).getDepotId())) {
                                stock.setAvailableStockQty(stockList.get(i).getAvailableStockQty());
                                stock.setSkuId(stockList.get(i).getSkuId());
                                stock.setSkuNo(stockList.get(i).getSkuNo());
                                stock.setRemark(stockList.get(i).getRemark());
                                stock.setUnionId(stockList.get(i).getUnionId());
                                stock.setStockType(stockList.get(i).getStockType());
                                stock.setLockStockQty(stockList.get(i).getLockStockQty());
                                stock.setOutboundStockQty(stockList.get(i).getOutboundStockQty());
                                stock.setCommodityBatchNo(stockList.get(i).getCommodityBatchNo());
                            }
                        }
                    } else {
                        stock.setSkuId(sf.getSkuId());
                        stock.setAvailableStockQty(0);
                    }
                    //判断该仓库是否对接云仓 如果对接云仓更新库存为云仓的可用库存
                    if (stock.getIsJoin() == 1) {
                        Future<List<YuncangRealTimeStock>> submit = executor.submit(() -> {
                            //查询云仓仓库实时库存
                            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                            headers.set("appKey", appKey);
                            headers.set("appSecret", appSecret);
                            headers.set("contentType", "application/json;charset=UTF-8");
                            headers.set("Accept", "*/*");
                            WarehouseQuery warehouseQuery = new WarehouseQuery();
                            warehouseQuery.setItemCode(stockFindAllPostQuery.getSkuCode());
                            warehouseQuery.setWareCode(stock.getDepotNo());
                            HttpEntity requestEntity = new HttpEntity<>(JsonUtil.bean2JsonString(warehouseQuery),
                                    headers);
                            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                                    requestEntity, Map.class);
                            Map map = responseEntity.getBody();
                            List<YuncangRealTimeStock> rows = null;
                            if (null != map) {
                                YuncangResponseRealTimeStock yuncangResponseRealTimeStock = JsonUtil.map2Bean(map
                                        , YuncangResponseRealTimeStock.class);
                                rows = yuncangResponseRealTimeStock.getRows();
                            }
                            return rows;
                        });
                        try {
                            List<YuncangRealTimeStock> yuncangRealTimeStocks = submit.get();
                            if (CollectionUtil.isNotEmpty(yuncangRealTimeStocks)) {
                                stock.setSkuNo(yuncangRealTimeStocks.get(0).getItemCode());
                                //设置可用库存
                                stock.setAvailableStockQty(yuncangRealTimeStocks.get(0).getValidQuantity());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error("对接外部系统的库存查询失败", e);
                        }
                    }
                });
                return collect;
            } else {
                throw new ApplicationException("获取到的仓库为空");
            }
        }
        return null;
    }


    //查询当前登陆人可操作的仓库
    @Override
    public List<DepotFindPagePostResponseDTODepot> getOperateDepot(StockFindAllPostQuery stockFindAllPostQuery) throws Exception {
        List<DepotFindPagePostResponseDTODepot> content = null;
        DepotFindPagePostByTypeRequestDTO dto = new DepotFindPagePostByTypeRequestDTO();
        dto.setIsolationId(appRuntimeEnv.getTopOrganization().getId() + "");
        dto.setAppId(stockFindAllPostQuery.getAppId());
        dto.setTenantId(appRuntimeEnv.getTenantId());
        dto.setPage(-1);//不分页
        PayloadDepotFindPagePostResponseDTO depotPage = depotSdkApi.findDepotPageByType(dto);
        if (SUCCESS.equals(depotPage.getCode())) {
            DepotFindPagePostResponseDTO payload = depotPage.getPayload();
            content = payload.getDepotList().getContent();
            //增加显示授权给客户的仓库
            content = addDepots(content, stockFindAllPostQuery);
            logger.info("获取到的当前组织的可操作仓库为:{}", content);
        } else {
            throw new ApplicationException("获取当前组织的可操作仓库失败：中台接口返回失败");
        }
        return content;
    }


    /**
     * 对仓库进行过滤处理
     * 1.目前提货审核页面需要根据授权情况过滤仓库
     *
     * @param depots
     * @param query
     */
    private List<DepotFindPagePostResponseDTODepot> filterDepots(List<DepotFindPagePostResponseDTODepot> depots, StockFindAllPostQuery query) throws Exception {
        if (depots == null || depots.size() == 0 || depots.get(0) == null) {
            return depots;
        }
        PickGoodStoreHouseReq pickGoodStoreHouseReq = query.getPickGoodStoreHouseReq();
        if (pickGoodStoreHouseReq == null || !"sendGoods".equals(pickGoodStoreHouseReq.getConditionType())) {
            return depots;
        }
        PickGoodStoreHouseResponseDTO storehouseList = salePickGoodsInfoService.getStorehouseList(pickGoodStoreHouseReq);
        if (storehouseList == null || storehouseList.getDepotList() == null) {
            return null;
        }
        List<DepotPostResponseDTODepot> authDepots = storehouseList.getDepotList();
        Map<Long, List<DepotPostResponseDTODepot>> flatMap = authDepots.stream().collect(Collectors.groupingBy(DepotPostResponseDTODepot::getId));
        for (int i = depots.size() - 1; i >= 0; i--) {
            DepotFindPagePostResponseDTODepot depot = depots.get(i);
            if (!flatMap.containsKey(depot.getId())) {
                depots.remove(i);
            }
        }
        return depots;
    }




    /**
     * 提货申请审核的时候，需要增加显示授权仓库供选择库存
     */
    private List<DepotFindPagePostResponseDTODepot> addDepots(List<DepotFindPagePostResponseDTODepot> depots, StockFindAllPostQuery query) throws Exception {
        if(query == null){
            return depots;
        }
        PickGoodStoreHouseReq pickGoodStoreHouseReq = query.getPickGoodStoreHouseReq();
        if (pickGoodStoreHouseReq == null || !"sendGoods".equals(pickGoodStoreHouseReq.getConditionType())) {
            return depots;
        }
        if(pickGoodStoreHouseReq.getPickGoodsCode() == null){
            return depots;
        }
        PickGoodStoreHouseResponseDTO storehouseList = salePickGoodsInfoService.getStorehouseList(pickGoodStoreHouseReq);
        if(storehouseList == null || storehouseList.getDepotList() == null){
            return depots;
        }
        List<DepotFindPagePostResponseDTODepot> addDepot = ObjectCloneUtils.convertList(storehouseList.getDepotList(), DepotFindPagePostResponseDTODepot.class);
        if(depots == null){
            depots = new ArrayList<DepotFindPagePostResponseDTODepot>();
        }
        if(addDepot != null){
            depots.addAll(addDepot);
        }
        //根据id去重仓库
        if(depots != null){
            HashSet<Long> hashSet = new HashSet<>();
            log.info("去重前的depots.size:"+depots.size());
            for(int i = depots.size()-1 ; i >= 0 ; i--){
                if(!hashSet.add(depots.get(i).getId())){
                    depots.remove(i);
                }
            }
            log.info("去重后的depots.size:"+depots.size());
        }
        return depots;
    }



    @ApiOperation("根据Id和Code查找销售订单或提货计划订单")
    @Override
    public SaleOrPinkOrderResponseDTO findOrderByIdAndCode(SaleOrPinkOrderRequestDTO req) throws Exception {
        if (req == null) {
            return null;
        }
        if (req.getCode() == null && req.getId() == null) {
            return null;
        }
        try {
            SaleOrPinkOrderResponseDTO saleOrPinkDTO = new SaleOrPinkOrderResponseDTO();
            List<SaleOrPinkOrderItemResponseDTO> items = null;
            //根据id和code查询销售订单
            SaleOrderInfoRequestQuery query = new SaleOrderInfoRequestQuery();
            query.setId(req.getId());
            query.setCode(req.getCode());
            items = getSaleOrPinkItem(query);
            if (items != null && items.size() != 0) {
                saleOrPinkDTO.setSaleOrPinkList(items);
                return saleOrPinkDTO;
            }
            //上述方法如果没有数据,则使用id和code查询字段“父订单ID”和“父订单编号”
            SaleOrderInfoRequestQuery query2 = new SaleOrderInfoRequestQuery();
            query2.setParentSaleOrderId(req.getId());
            query2.setParentSaleOrderCode(req.getCode());
            items = getSaleOrPinkItem(query2);
            if (items != null && items.size() != 0) {
                saleOrPinkDTO.setSaleOrPinkList(items);
                return saleOrPinkDTO;
            }
            //上述方法如果没有数据,则查询提货计划订单
            SalePickGoodsInfoRequestQuery query3 = new SalePickGoodsInfoRequestQuery();
            query3.setId(req.getId());
            query3.setPickGoodsCode(req.getCode());
            List<SalePickGoodsInfoResponseDTO> pickList = salePickGoodsInfoClient.listSalePickGoodsInfos(query3);
            if (pickList != null && pickList.size() != 0) {
                items = pickList.stream().map(pick -> {
                    SaleOrPinkOrderItemResponseDTO item = new SaleOrPinkOrderItemResponseDTO();
                    item.setId(pick.getId());
                    item.setCode(pick.getPickGoodsCode());
                    item.setMoney(pick.getPayAmount());
                    return item;
                }).collect(Collectors.toList());
            }
            if (items != null && items.size() != 0) {
                saleOrPinkDTO.setSaleOrPinkList(items);
                return saleOrPinkDTO;
            }
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
        return null;
    }

    //根据某些条件查询销售订单或提货订单
    private List<SaleOrPinkOrderItemResponseDTO> getSaleOrPinkItem(SaleOrderInfoRequestQuery query) throws Exception {
        List<SaleOrPinkOrderItemResponseDTO> items = null;
        Payload<List<SaleOrderInfoResponseDTO>> listPayload = saleOrderInfoClient.listSaleOrderInfos(query);
        if (listPayload != null) {
            List<SaleOrderInfoResponseDTO> saleOrderArr = GeneralConvertUtils.convert2List(listPayload.getPayload(),
                    SaleOrderInfoResponseDTO.class);
            if (saleOrderArr != null && saleOrderArr.size() != 0) {
                items = saleOrderArr.stream().map(order -> {
                    SaleOrPinkOrderItemResponseDTO item = new SaleOrPinkOrderItemResponseDTO();
                    item.setId(order.getId());
                    item.setCode(order.getCode());
                    item.setMoney(order.getPayAmount());
                    return item;
                }).collect(Collectors.toList());
            }
        }
        return items;
    }

    /**
     * TODO 获取预下单状态
     * public
     *
     * @param request
     * @param result
     * @param getPayStatusRequest
     * @param financePayInfoResponseDTO
     * @param saleOrderInfoResponseDTO
     * @param payRequestManager
     * @return
     */
    private SaleOrderPayResponseDTO torsionStatus(PrePlaceOrderRequest request,
                                                  SaleOrderPayResponseDTO result,
                                                  GetPayStatusRequest getPayStatusRequest,
                                                  FinancePayInfoResponseDTO financePayInfoResponseDTO,
                                                  SaleOrderInfoResponseDTO saleOrderInfoResponseDTO,
                                                  PayRequestManager payRequestManager) {
        try {
            logger.info("获取支付状态:{}", JsonUtil.bean2JsonString(request));
            GetPayStatusResponse getPayStatusResponse = payRequestManager.getPayStatus(getPayStatusRequest);
            logger.info("支付状态结果:{}", JsonUtil.bean2JsonString(getPayStatusResponse));
            try {
                if (getPayStatusResponse.getStatus() == 1) {    //已支付 直接返回
                    result.setMerchantCode(getPayStatusResponse.getMerchantCode());
                    result.setOutPayOrderNo(getPayStatusResponse.getOutOrderNo());
                    result.setOpenCashier(false);
                    return result;
                }
                if (getPayStatusResponse.getStatus() == 2) {  //已关闭 预下单
                    PrePlaceOrderResponse prePlaceOrderResponse = payRequestManager.prePlaceOrder(request);
                    result.setMerchantCode(prePlaceOrderResponse.getMerchantCode());
                    result.setOutPayOrderNo(prePlaceOrderResponse.getOutOrderNo());
                    result.setOpenCashier(true);
                    return result;
                }
                if (getPayStatusResponse.getStatus() == 0) {  //未付款
                    result.setMerchantCode(getPayStatusResponse.getMerchantCode());
                    result.setOutPayOrderNo(getPayStatusResponse.getOutOrderNo());
                    result.setOpenCashier(true);
                    return result;
                }
            } catch (Exception e) {
                throw new ApplicationException(e.getMessage());
            }

        }
        //HttpClientException
        catch (HttpClientException e) {
            try {
                Gson gson = new Gson();
                HashMap<String, Object> map = gson.fromJson(e.getMessage(), HashMap.class);
                logger.info("中台返回订单状态:{}", JsonUtil.bean2JsonString(map));
                if (map.get("code").equals("20001")) {   //支付订单不存在
                    logger.info("开始下单.......");
                    //调中台的支付中心 预下单
                    PrePlaceOrderResponse prePlaceOrderResponse = payRequestManager.prePlaceOrder(request);
                    result.setMerchantCode(prePlaceOrderResponse.getMerchantCode());
                    result.setOutPayOrderNo(prePlaceOrderResponse.getOutOrderNo());
                    result.setOpenCashier(true);
                    logger.info("下单结果:{}", JsonUtil.bean2JsonString(result));
                    return result;
                } else if (map.get("code").equals("20000")) {    //支付订单已存在
                    result.setMerchantCode(financePayInfoResponseDTO.getMerchantCode());
                    result.setOutPayOrderNo(saleOrderInfoResponseDTO.getCode());
                    result.setOpenCashier(true);
                    return result;
                } else {

                    throw new ApplicationException(map.get("msg").toString());
                }
            } catch (Exception ex) {
                logger.error("转换订单状态异常:", ex);
                throw new ApplicationException(ex.getMessage());
            }

        }
        return null;
    }

    /**
     * @param orders
     * @param actionType 0=增加；1=减少
     */
    public void handerMq(List<SaleOrderInfoDTO> orders, Integer actionType, Integer orderType) {
        List<OrderMqMsg> list = new ArrayList<>();
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(orders)) {
            for (SaleOrderInfoDTO saleOrderInfoDTO : orders) {
                List<SaleOrderItemDTO> items = saleOrderInfoDTO.getItems();
                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(items)) {
                    items.forEach(item -> {
                        if (item.getDirectId() != null || item.getActivitiesId() != null) {
                            OrderMqMsg orderMqMsg = new OrderMqMsg();
                            orderMqMsg.setActionType(actionType);
                            orderMqMsg.setNum(item.getSkuQuantity());
                            orderMqMsg.setOrderCode(saleOrderInfoDTO.getCode());
                            if (item.getDirectId() != null) {
                                orderMqMsg.setOrderType(1);
                            } else if (item.getActivitiesId() != null) {
                                orderMqMsg.setOrderType(0);
                            }
                            orderMqMsg.setSkuId(item.getSkuId());
                            orderMqMsg.setActivitiesId(item.getActivitiesId() == null ? 0 : item.getActivitiesId());
                            orderMqMsg.setDirectId(item.getDirectId() == null ? 0 : item.getDirectId());
                            list.add(orderMqMsg);
                        }
                    });
                }
            }
        }
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(list)) {
            mqProducter.confirmOrCancel(JSONObject.toJSONString(list, SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteMapNullValue));
        }
    }

    /**
     * 支付渠道转换
     * ALIPAY: 支付宝 -2
     * WX：微信 -1
     * UNION：云闪付 -3
     *
     * @param code
     * @return
     */
    private Integer codeTranNumber(String code) {
        if (null != code) {
            if ("WX".equals(code)) {
                return 1;
            } else if ("ALIPAY".equals(code)) {
                return 2;
            } else if ("UNION".equals(code)) {
                return 3;
            }
        }
        return 0;
    }

    @Override
    public PageBean<StockDetailDTO> getStockDetailPage(StockDetailQuery query) throws Exception {

        StockChangeFindDetailPageRequestDTO stockQuery = query.clone(StockChangeFindDetailPageRequestDTO.class);
        PayloadStockChangeFindDetailPageResponseDTO payloadStockChangeFindDetailPageResponseDTO = stockChangeSdkApi.findStockChangeDetailPage1(stockQuery);
        if(SUCCESS.equals(payloadStockChangeFindDetailPageResponseDTO.getCode())){
            return GeneralConvertUtils.convert2PageBean(payloadStockChangeFindDetailPageResponseDTO.getPayload().getStockChangePageBean(), StockDetailDTO.class);
        }
        return new PageBean<>(new ArrayList<>());
    }

    @Override
    public Boolean closePreMonthPlanOrder() throws Exception {
        return saleOrderInfoClient.closePreMonthPlanOrder().getPayload();
    }

    public void setOrder(Map<Long, List<Long>> skuOrgMap, SaleOrderInfoDTO saleOrder, Map<String,
            MerchantResponseDTO> merchatMap) {
        Set<Long> skuSets = new HashSet<>();
        if (skuOrgMap.size() > 0) {
            for (Map.Entry<Long, List<Long>> entry : skuOrgMap.entrySet()) {
                saleOrder.setAscriptionOrgId(entry.getKey().equals(-9999L) ? saleOrder.getSellerId() :
                        entry.getKey());
                //如果组织id不为空，则获取客户信息
                if (!entry.getKey().equals(-9999L)) {
                    MerchantResponseDTO merchant = merchatMap.get(entry.getKey() + "");
                    if (merchant == null) {
                        merchant = merchatMap.get(saleOrder.getSellerId() + "");
                    }
                    if (merchant != null) {
                        saleOrder.setCustomerId(merchant.getId());
                        saleOrder.setCustomerName(merchant.getName());
                        saleOrder.setCustomerType(merchant.getTypeId());
                    } else {//如果还等于空，则取第一个值
                        log.info("找不到客户=={}",merchatMap);
                        for (Map.Entry<String, MerchantResponseDTO> merchantResponseDTOEntry : merchatMap.entrySet()) {
                            merchant = merchantResponseDTOEntry.getValue();
                        }
                        saleOrder.setCustomerId(merchant.getId());
                        saleOrder.setCustomerName(merchant.getName());
                        saleOrder.setCustomerType(merchant.getTypeId());
                    }
                } else {
                    MerchantResponseDTO merchant = merchatMap.get(saleOrder.getSellerId() + "");
                    if (merchant != null) {
                        saleOrder.setCustomerId(merchant.getId());
                        saleOrder.setCustomerName(merchant.getName());
                        saleOrder.setCustomerType(merchant.getTypeId());
                    } else {
                        //如果还等于空，则取第一个值
                        log.info("找不到客户=={}",merchatMap);
                        for (Map.Entry<String, MerchantResponseDTO> merchantResponseDTOEntry : merchatMap.entrySet()) {
                            merchant = merchantResponseDTOEntry.getValue();
                        }
                        saleOrder.setCustomerId(merchant.getId());
                        saleOrder.setCustomerName(merchant.getName());
                        saleOrder.setCustomerType(merchant.getTypeId());
                    }
                }
            }
            //设置订单商品信息
            // setCustPrice(saleOrderInfoDTO);
            //setOrderBaseInfo(saleOrder);
            // resultList.add(saleOrderInfoDTO);
        } else {
            MerchantResponseDTO merchant = merchatMap.get(saleOrder.getSellerId() + "");
            if (merchant != null) {
                saleOrder.setCustomerId(merchant.getId());
                saleOrder.setCustomerName(merchant.getName());
                saleOrder.setCustomerType(merchant.getTypeId());
            }
            //设置订单商品信息
            // saleOrder.setItems(getSaleOrderItems(saleOrder, items));
            // setCustPrice(saleOrder);
            //setOrderBaseInfo(saleOrder);
            // resultList.add(saleOrder);
        }
    }
}