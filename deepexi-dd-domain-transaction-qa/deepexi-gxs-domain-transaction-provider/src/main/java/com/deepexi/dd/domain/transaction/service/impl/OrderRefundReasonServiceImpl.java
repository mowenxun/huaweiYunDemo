package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonFindResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceCreditPaymentRequestDTO;
import com.deepexi.dd.domain.transaction.enums.OrderOperationRecordEnum;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.remote.order.FinancePaymentRecordClient;
import com.deepexi.dd.domain.transaction.remote.order.MerchantClient;
import com.deepexi.dd.domain.transaction.remote.order.OrderOperationRecordClient;
import com.deepexi.dd.domain.transaction.remote.order.OrderRefundReasonClient;
import com.deepexi.dd.domain.transaction.remote.order.OrderRefundSkuClient;
import com.deepexi.dd.domain.transaction.service.OrderRefundReasonService;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.domain.transaction.service.SaleOrderOperationRecordService;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsInfoService;
import com.deepexi.dd.domain.transaction.util.SaleOrderInfoUtils;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditStatusRequestDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordRequestQuery;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonRequestQuery;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuRequestQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.DateUtils;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.MerchantResponseDTO;
import domain.query.MerchantRequestQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderRefundReasonServiceImpl implements OrderRefundReasonService {

    private static final String YYYYMMDD = "yyyyMMdd";

    @Autowired
    private OrderRefundReasonClient orderRefundReasonClient;

    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Autowired
    private OrderRefundSkuClient orderRefundSkuClient;

    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;

    @Autowired
    private FinancePaymentRecordClient financePaymentRecordClient;

    @Autowired
    SaleOrderInfoUtils saleOrderInfoUtils;

    @Autowired
    SaleOrderOperationRecordService saleOrderOperationRecordService;

    @Autowired
    private OrderOperationRecordClient orderOperationRecordClient;

    @Override
    public List<OrderRefundReasonResponseDTO> listOrderRefundReasons(OrderRefundReasonRequestQuery query) throws Exception {
        return GeneralConvertUtils.convert2List(orderRefundReasonClient.listOrderRefundReasons(query).getPayload(), OrderRefundReasonResponseDTO.class);
    }

    /*@Override
    public PageBean<OrderRefundReasonResponseDTO> listOrderRefundReasonsPage(OrderRefundReasonRequestQuery query) throws Exception {
        if (Objects.nonNull(query.getCustomerName())) {
            MerchantRequestQuery merchantQuery = new MerchantRequestQuery();
            merchantQuery.setName(query.getCustomerName());
            List<MerchantResponseDTO> merchantDTOS = merchantClient.findAll(merchantQuery).getPayload();
            if (CollectionUtil.isEmpty(merchantDTOS)) {
                log.info("模糊查询客户为空");
                return new PageBean<>();
            }
            query.setCustomerIdList(merchantDTOS.stream().map(MerchantResponseDTO::getId).collect(Collectors.toList()));
        }
        PageBean<OrderRefundReasonResponseDTO> orderRefundReasonResponseDTOPageBean = GeneralConvertUtils.convert2PageBean(orderRefundReasonClient.listOrderRefundReasonsPage(query).getPayload(), OrderRefundReasonResponseDTO.class);
        if (!Objects.isNull(orderRefundReasonResponseDTOPageBean)) {
            for (OrderRefundReasonResponseDTO orderRefundReasonResponseDTO : orderRefundReasonResponseDTOPageBean.getContent()) {
                OrderRefundSkuRequestQuery refundQuery = new OrderRefundSkuRequestQuery();
                refundQuery.setTenantId(orderRefundReasonResponseDTO.getTenantId());
                refundQuery.setAppId(orderRefundReasonResponseDTO.getAppId());
                refundQuery.setRefundOrderId(orderRefundReasonResponseDTO.getId());
                Payload<List<OrderRefundSkuResponseDTO>> payload = orderRefundSkuClient.listOrderRefundSkus(refundQuery);
                if ("0".equals(payload.getCode())) {
                    orderRefundReasonResponseDTO.setRefundSku(GeneralConvertUtils.convert2List(payload.getPayload(), OrderRefundSkuResponseDTO.class));
                }
            }

        }
        return orderRefundReasonResponseDTOPageBean;
    }*/
    @Override
    public PageBean<OrderRefundReasonResponseDTO> listOrderRefundReasonsPage(OrderRefundReasonRequestQuery query) throws Exception {
//        if (Objects.nonNull(query.getCustomerName())) {
//            MerchantRequestQuery merchantQuery = new MerchantRequestQuery();
//            merchantQuery.setName(query.getCustomerName());
//            List<MerchantResponseDTO> merchantDTOS = merchantClient.findAll(merchantQuery).getPayload();
//            if (CollectionUtil.isEmpty(merchantDTOS)) {
//                log.info("模糊查询客户为空");
//                return new PageBean<>();
//            }
//            query.setCustomerIdList(merchantDTOS.stream().map(MerchantResponseDTO::getId).collect(Collectors.toList()));
//        }
        //return GeneralConvertUtils.convert2PageBean(orderRefundReasonClient.listOrderRefundReasonsPage(query).getPayload(), OrderRefundReasonResponseDTO.class);
        // 在线订购用户查询自己的退款单
        if(query.getBuyerId()!=null){
            query.setIsolationId(null);
        } else { // 运营中心查询退款单
            // 一级组织
            if (!appRuntimeEnv.getTopOrganization().getId().equals(appRuntimeEnv.getUserOrganization().getId())) {
                query.setIsolationId(null);
                query.setOrgId(appRuntimeEnv.getUserOrganization().getId());
            }
        }

        PageBean<OrderRefundReasonResponseDTO> orderRefundReasonResponseDTOPageBean = GeneralConvertUtils.convert2PageBean(orderRefundReasonClient.listOrderRefundReasonsPage(query).getPayload(), OrderRefundReasonResponseDTO.class);
        if (!Objects.isNull(orderRefundReasonResponseDTOPageBean)) {
            for (OrderRefundReasonResponseDTO orderRefundReasonResponseDTO : orderRefundReasonResponseDTOPageBean.getContent()) {
                OrderRefundSkuRequestQuery refundQuery = new OrderRefundSkuRequestQuery();
                refundQuery.setTenantId(orderRefundReasonResponseDTO.getTenantId());
                refundQuery.setAppId(orderRefundReasonResponseDTO.getAppId());
                refundQuery.setRefundOrderId(orderRefundReasonResponseDTO.getId());
                Payload<List<OrderRefundSkuResponseDTO>> payload = orderRefundSkuClient.listOrderRefundSkus(refundQuery);
                if ("0".equals(payload.getCode())) {
                    orderRefundReasonResponseDTO.setRefundSku(GeneralConvertUtils.convert2List(payload.getPayload(), OrderRefundSkuResponseDTO.class));
                }
            }

        }


        return orderRefundReasonResponseDTOPageBean;
    }

    @Override
    public OrderRefundReasonResponseDTO selectById(Long id) throws Exception {
        OrderRefundReasonResponseDTO result = GeneralConvertUtils.conv(orderRefundReasonClient.selectById(id).getPayload(), OrderRefundReasonResponseDTO.class);
        OrderRefundSkuRequestQuery refundQuery = new OrderRefundSkuRequestQuery();
        refundQuery.setTenantId(result.getTenantId());
        refundQuery.setAppId(result.getAppId());
        refundQuery.setRefundOrderId(result.getId());
        Payload<List<OrderRefundSkuResponseDTO>> payload = orderRefundSkuClient.listOrderRefundSkus(refundQuery);
        if ("0".equals(payload.getCode())) {
            result.setRefundSku(GeneralConvertUtils.convert2List(payload.getPayload(), OrderRefundSkuResponseDTO.class));
        }

        //添加退款操作记录
        OrderOperationRecordRequestQuery recordQuery = new OrderOperationRecordRequestQuery();
        recordQuery.setOperationType(OrderOperationRecordEnum.REFUND_ORDER.getCode());
        recordQuery.setOrderId(result.getId());
        recordQuery.setTenantId(result.getTenantId());
        recordQuery.setAppId(result.getAppId());
        List<OrderOperationRecordResponseDTO> recordList = orderOperationRecordClient.listOrderOperationRecords(recordQuery);
        result.setOrderOperationRecordResponseList(recordList);
        //添加退款操作记录


        return result;
    }

    @Override
    public List<OrderRefundSkuResponseDTO> queryOrderRefundSku(OrderRefundSkuRequestQuery refundQuery) throws ApplicationException {
        refundQuery.setTenantId(refundQuery.getTenantId());
        refundQuery.setAppId(refundQuery.getAppId());
        refundQuery.setRefundOrderId(refundQuery.getRefundOrderId());
        log.info("查询退款明细........:{}", orderRefundSkuClient);
        Payload<List<OrderRefundSkuResponseDTO>> payload = orderRefundSkuClient.listOrderRefundSkus(refundQuery);
        try {

            List<OrderRefundSkuResponseDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(), OrderRefundSkuResponseDTO.class);
            log.info("返回退款明细:{}", JsonUtil.bean2JsonString(list));
            return list;
        } catch (Exception e) {
            log.error("转换退货明细错误:", e);
            throw new ApplicationException("转换退货明细错误", e);
        }
    }

    @Override
    public OrderRefundReasonResponseDTO insert(OrderRefundReasonRequestDTO record) throws Exception {
        // 生成合同编号
        String orderNo = identifierGenerator.getIdentifier(IdentifierTypeEnum.REFUND_ORDER_CODE.getType(),
                IdentifierTypeEnum.REFUND_ORDER_CODE.getPrefix() + DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.REFUND_ORDER_CODE.getLen());
        record.setRefundCode(orderNo);

        OrderRefundReasonResponseDTO orderRefundReasonResponseDTO=null;

        // 判断查什么订单: 0网批订单, 1提货计划单
        if (record.getOrderType() == 0) {
            // 查询网批订单
            orderRefundReasonResponseDTO = getSaleOrderInfo(record);
        } else if (record.getOrderType() == 1) {
            // 查询提货计划单
            orderRefundReasonResponseDTO = getTakeDeliveryRecord(record);
        }

        if(orderRefundReasonResponseDTO==null){
            throw new ApplicationException("订单类型错误");
        }

        insertOperationRecord(orderRefundReasonResponseDTO,"创建退款");

        return orderRefundReasonResponseDTO;

    }

    /**
     * 查询网批订单
     *
     * @param record
     * @return
     * @throws Exception
     */
    private OrderRefundReasonResponseDTO getSaleOrderInfo(OrderRefundReasonRequestDTO record) throws Exception {
        SaleOrderInfoResponseDTO sale = saleOrderInfoService.selectById(record.getOrderId());
        if (Objects.isNull(sale)) {
            log.error("查询退款单为空");
            throw new ApplicationException(ResultEnum.INSERT_ERROR);
        }

        if(hasRefundReason(sale.getCode())){
            log.error("此单已进行了退款");
            throw new ApplicationException("此单已进行了退款");
        }

        record.setTenantId(appRuntimeEnv.getTenantId());
        //record.setAppId(appRuntimeEnv.getAppId());
        record.setCreatedBy(appRuntimeEnv.getUsername());
        record.setUpdatedBy(appRuntimeEnv.getUsername());
        record.setOrderCode(sale.getCode());
        record.setApplyDate(new Date());
        record.setTicketDate(sale.getTicketDate());
        record.setCustomerId(sale.getCustomerId());
        record.setCustomerName(sale.getCustomerName());
        record.setPayAmount(sale.getPayAmount());
        record.setOrgId(sale.getAscriptionOrgId());
        record.setPaymentType(sale.getPaymentType());
        record.setPayCode(sale.getPayOrderCode());
        record.setBuyerId(sale.getBuyerId());
        record.setPartnerId(sale.getPartnerId());
        record.setRefundStatus(24);//默认待确认
        record.setRefundType(getRefundType(sale.getCode(),sale.getPaymentType()));//设置退款类型
        record.setIsolationId(String.valueOf(sale.getSellerId()));
        //record.setIsolationId(topOrganizationId);
        OrderRefundReasonResponseDTO result = GeneralConvertUtils.conv(
                orderRefundReasonClient.insert(record).getPayload(), OrderRefundReasonResponseDTO.class);
        if (Objects.isNull(result)) {
            log.error("新增退款原因失败");
            throw new ApplicationException(ResultEnum.INSERT_ERROR);
        }
        List<OrderRefundSkuRequestDTO> skuDTOS = GeneralConvertUtils.convert2List(sale.getItems(), OrderRefundSkuRequestDTO.class);
        skuDTOS.forEach(s -> {
            s.setTenantId(record.getTenantId());
            s.setAppId(record.getAppId());
            s.setRefundOrderId(result.getId());
            s.setCreatedBy(appRuntimeEnv.getUsername());
            s.setUpdatedBy(appRuntimeEnv.getUsername());
            s.setIsolationId(String.valueOf(sale.getSellerId()));
            //s.setIsolationId(topOrganizationId);
        });
        if (orderRefundSkuClient.batchInsert(skuDTOS).getPayload().size() != sale.getItems().size()) {
            log.error("新增退款原因明细失败");
            throw new ApplicationException(ResultEnum.INSERT_ERROR);
        }

        FinanceCreditStatusRequestDTO paymentRequestDTO=new FinanceCreditStatusRequestDTO();
        paymentRequestDTO.setOrderCode(sale.getCode());
        paymentRequestDTO.setStatus(2);
        financePaymentRecordClient.updateStatusByOrderCode(paymentRequestDTO);

        return result;
    }

    /**
     * 获取提货记录单
     *
     * @param record
     * @return
     * @throws Exception
     */
    private OrderRefundReasonResponseDTO getTakeDeliveryRecord(OrderRefundReasonRequestDTO record) throws Exception {
        // 获取提货记录单
        SalePickGoodsInfoDetailResponseDTO sale = salePickGoodsInfoService.getDetailById(record.getOrderId());
        if (Objects.isNull(sale)) {
            log.error("查询提货记录单为空");
            throw new ApplicationException(ResultEnum.INSERT_ERROR);
        }


        if(hasRefundReason(sale.getPickGoodsCode())){
            log.error("此单已进行了退款");
            throw new ApplicationException("此单已进行了退款");
        }

        record.setTenantId(appRuntimeEnv.getTenantId());
        //record.setAppId(appRuntimeEnv.getAppId());
        record.setCreatedBy(appRuntimeEnv.getUsername());
        record.setUpdatedBy(appRuntimeEnv.getUsername());
        record.setOrderCode(sale.getPickGoodsCode());
        record.setApplyDate(new Date());
        record.setTicketDate(sale.getCreatedTime());
        record.setCustomerId(sale.getCustomerId());
        record.setCustomerName(sale.getCustomerName());
        record.setPayAmount(sale.getPayAmount());
        record.setOrgId(sale.getAscriptionOrgId());
        record.setPaymentType(sale.getPaymentType());
        record.setBuyerId(sale.getBuyerId());
        record.setPartnerId(getPartnerId(sale.getTenantId(),sale.getAppId(),sale.getBuyerId()));
        record.setPayCode(sale.getPickGoodsCode());
        record.setRefundStatus(24);//默认待确认
        record.setRefundType(getRefundType(sale.getPickGoodsCode(),sale.getPaymentType()));//设置退款类型
        record.setIsolationId(String.valueOf(sale.getSellerId()));
        //record.setIsolationId(String.valueOf(sale.getAscriptionOrgId());
        OrderRefundReasonResponseDTO result = GeneralConvertUtils.conv(
                orderRefundReasonClient.insert(record).getPayload(), OrderRefundReasonResponseDTO.class);
        if (Objects.isNull(result)) {
            log.error("新增提货记录单失败");
            throw new ApplicationException(ResultEnum.INSERT_ERROR);
        }
        List<SalePickGoodsOrderSkuResponseDTO> skuDTOS =
                GeneralConvertUtils.convert2List(salePickGoodsInfoService.getSkuByInfoId(sale.getId()), SalePickGoodsOrderSkuResponseDTO.class);

        List<OrderRefundSkuRequestDTO> skuRequestDTOS=new ArrayList<>();
        skuDTOS.forEach(sku->{
            OrderRefundSkuRequestDTO orderRefundSku=new OrderRefundSkuRequestDTO();
            orderRefundSku.setAppId(record.getAppId());
            orderRefundSku.setTenantId(record.getTenantId());
            orderRefundSku.setAppId(record.getAppId());
            orderRefundSku.setRefundOrderId(result.getId());
            orderRefundSku.setCreatedBy(appRuntimeEnv.getUsername());
            orderRefundSku.setUpdatedBy(appRuntimeEnv.getUsername());
            orderRefundSku.setIsolationId(String.valueOf(sale.getSellerId()));
            orderRefundSku.setCreatedTime(new Date());
            orderRefundSku.setUpdatedTime(new Date());
            orderRefundSku.setVersion(0);
            orderRefundSku.setDeleted(false);
            orderRefundSku.setMajorPicture(sku.getMajorPicture());
            orderRefundSku.setSkuCode(sku.getSkuCode());
            orderRefundSku.setSkuFormat(sku.getSkuFormat());
            orderRefundSku.setSkuName(sku.getSkuName());
            orderRefundSku.setPrice(sku.getPrice());
            //orderRefundSku.setSkuQuantity(sku.getPickNum());
            orderRefundSku.setSkuQuantity(getPickSkuQty(sku.getDeliveryQuantity(),sku.getPickNum()));
            orderRefundSku.setSkuTotalQuantity(Optional.ofNullable(sku.getDeliveryQuantity()).orElse(0L)- Optional.ofNullable(sku.getWaitSendNum()).orElse(0L));

            skuRequestDTOS.add(orderRefundSku);

        });

        orderRefundSkuClient.batchInsert(skuRequestDTOS);

//        if (orderRefundSkuClient.batchInsert(skuRequestDTOS).getPayload().size() != sale.getItems().size()) {
//            log.error("新增提货记录单明细失败");
//            throw new ApplicationException(ResultEnum.INSERT_ERROR);
//        }

        FinanceCreditStatusRequestDTO paymentRequestDTO=new FinanceCreditStatusRequestDTO();
        paymentRequestDTO.setOrderCode(sale.getPickGoodsCode());
        paymentRequestDTO.setStatus(2);
        financePaymentRecordClient.updateStatusByOrderCode(paymentRequestDTO);

        return result;
    }

    private Long getPickSkuQty(Long deliveryQty,Long pickQty){
        if(deliveryQty==null || deliveryQty==0L){
            return pickQty;
        }

        return deliveryQty;
    }

    private boolean hasRefundReason(String code) throws Exception {
        List<String> codes=new ArrayList<>();
        codes.add(code);

        List<OrderRefundReasonFindResponseDTO> refundReasons = findOrderRefundReason(codes);

        if(CollectionUtil.isEmpty(refundReasons)){
            return false;
        }

        return true;
    }

    private Long getPartnerId(String tenantId,Long appId,Long buyerId) throws Exception {
        BusinessPartnerResponseDTO businessPartner = saleOrderInfoUtils.getBusinessPartner(tenantId,appId,buyerId);

        return businessPartner.getId();
    }

    @Override
    public Boolean updateById(Long id, OrderRefundReasonRequestDTO record) throws Exception {
        // 校验编码
        //CheckCode(id, record);
        record.setUpdatedBy(appRuntimeEnv.getUsername());
        record.setUpdatedTime(new Date());
        record.setRefundStatus(24);//默认待确认
        Boolean result = orderRefundReasonClient.updateById(id, record).getPayload();

        OrderRefundReasonResponseDTO orderRefundReason = selectById(id);

        insertOperationRecord(orderRefundReason,"修改申请");

        return result;
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) throws Exception {
        return orderRefundReasonClient.deleteByIdIn(id).getPayload();
    }

    @Override
    public Boolean updateStatus(OrderRefundReasonRequestDTO record) throws Exception {
        Boolean result = orderRefundReasonClient.updateStatus(record).getPayload();

        OrderRefundReasonResponseDTO orderRefundReason = selectById(record.getId());

        insertOperationRecord(orderRefundReason,record.getType() ? "退款成功" : "已驳回");

        return result;
    }

    @Override
    public Boolean cancel(Long id) throws Exception {

        OrderRefundReasonResponseDTO orderRefundReason = selectById(id);

        FinanceCreditStatusRequestDTO paymentRequestDTO=new FinanceCreditStatusRequestDTO();
        paymentRequestDTO.setOrderCode(orderRefundReason.getOrderCode());
        paymentRequestDTO.setStatus(1);
        financePaymentRecordClient.updateStatusByOrderCode(paymentRequestDTO);

        Boolean result= orderRefundReasonClient.cancel(id).getPayload();

        insertOperationRecord(orderRefundReason,"取消退款");

        return result;
    }

    @Override
    public List<OrderRefundReasonFindResponseDTO> findOrderRefundReason(List<String> orderCodes) throws Exception {
        return GeneralConvertUtils.convert2List(orderRefundReasonClient.findOrderRefundReason(orderCodes).getPayload(),OrderRefundReasonFindResponseDTO.class);
    }

    @Override
    public FinancePaymentRecordsResponseDTO findOriginOrderRefundPaymentRecord(FinancePaymentRecordsRequestQuery query) throws Exception {
        if (StringUtil.isBlank(query.getOrderCode())) {
            throw new ApplicationException("orderType为空！");
        }
        query.setIsolationId(null);
        List<OrderRefundReasonResponseDTO> orderRefundReasonPayload = orderRefundReasonClient.listOrderRefundReasons(query.clone(OrderRefundReasonRequestQuery.class)).getPayload();
        if (CollectionUtil.isEmpty(orderRefundReasonPayload)) {
            return null;
        }
        OrderRefundReasonResponseDTO orderRefundReasonResponseDTO = GeneralConvertUtils.convert2List(orderRefundReasonPayload, OrderRefundReasonResponseDTO.class).get(0);
        FinancePaymentRecordsRequestQuery paymentRecordsRequestQuery = new FinancePaymentRecordsRequestQuery();
        paymentRecordsRequestQuery.setOrderCode(orderRefundReasonResponseDTO.getRefundCode());
        FinancePaymentRecordsResponseDTO paymentRecordResponseDTO = GeneralConvertUtils.conv(financePaymentRecordClient.selectOnePaymentRecords(paymentRecordsRequestQuery).getPayload(), FinancePaymentRecordsResponseDTO.class);
        if (paymentRecordResponseDTO == null) {
            return null;
        }
        paymentRecordResponseDTO.setProceedsAccount(paymentRecordResponseDTO.getCustomerName() + (paymentRecordResponseDTO.getPayType() == 0 ? "-信用账户" : "-余额账户"));
        return paymentRecordResponseDTO;
    }

    /**
     * 校验编码
     *
     * @param id
     * @param record
     * @throws Exception
     */
    private void CheckCode(Long id, OrderRefundReasonRequestDTO record) throws Exception {
        if (Objects.isNull(id)) {
            OrderRefundReasonRequestQuery query = new OrderRefundReasonRequestQuery();
            query.setTenantId(appRuntimeEnv.getTenantId());
            query.setAppId(record.getAppId());
            query.setRefundCode(record.getRefundCode());
            if (CollectionUtil.isNotEmpty(orderRefundReasonClient.CheckCode(query).getPayload())) {
                log.error("编码重复");
                throw new ApplicationException(ResultEnum.CODE_ERROR);
            }
        } else {
            OrderRefundReasonRequestQuery query = new OrderRefundReasonRequestQuery();
            query.setTenantId(appRuntimeEnv.getTenantId());
            query.setAppId(appRuntimeEnv.getAppId());
            query.setRefundCode(record.getRefundCode());
            List<OrderRefundReasonRequestDTO> result = GeneralConvertUtils.convert2List(
                    orderRefundReasonClient.CheckCode(query).getPayload(), OrderRefundReasonRequestDTO.class);
            if (CollectionUtil.isNotEmpty(result)) {
                if (!Objects.equals(result.get(0).getId(), id)) {
                    log.error("编码重复");
                    throw new ApplicationException(ResultEnum.CODE_ERROR);
                }
            }
        }
    }

    private Integer getRefundType(String orderCode,Integer paymentType) throws Exception {

        Integer refundType = 0;

        switch (paymentType) {
            case 1://如果支付方式=余额支付/线上支付/线下支付， 则退款方式=退回余额
            case 2:
            case 4:
                refundType = 0;
                break;
            case 3: {
                FinancePaymentRecordsRequestQuery query = new FinancePaymentRecordsRequestQuery();
                query.setOrderCode(orderCode);
                query.setPayType(0);//信用支付
                Payload<List<FinancePaymentRecordsResponseDTO>> financePaymentRecordsPayload = financePaymentRecordClient.listFinancePaymentRecords(query);
                List<FinancePaymentRecordsResponseDTO> financePaymentRecords = GeneralConvertUtils.convert2List(financePaymentRecordsPayload.getPayload(), FinancePaymentRecordsResponseDTO.class);

                FinancePaymentRecordsResponseDTO financePaymentRecord = financePaymentRecords.get(0);

                if (financePaymentRecord.getRepaymentStatus() == 1) {
                    refundType = 1;//如果支付方式=信用支付，且还没有还款， 则退款方式=退回信用
                } else if (financePaymentRecord.getRepaymentStatus() == 3) {
                    refundType = 0;//如果支付方式=信用支付，且还款， 则退款方式=退回余额
                }

            }
            break;
        }

        return refundType;

    }

    private void insertOperationRecord(OrderRefundReasonResponseDTO reason,String operation){

        OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
        orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
        orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
        orderOperationRecordRequestDTO.setCreatedTime(new Date());
        orderOperationRecordRequestDTO.setOrderId(reason.getOrderId());
        orderOperationRecordRequestDTO.setOrderCode(reason.getRefundCode());
        orderOperationRecordRequestDTO.setOperation(operation);
        orderOperationRecordRequestDTO.setOperationType(3);
        orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
        orderOperationRecordRequestDTO.setRemark(reason.getRemark());

        saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
    }
}
