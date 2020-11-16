package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceRepaymentNotifyDTO;
import com.deepexi.dd.domain.transaction.manager.ToolActionExcutor;
import com.deepexi.dd.domain.transaction.mq.producter.MqMessageProducter;
import com.deepexi.dd.domain.transaction.remote.finance.FinanceAmountClient;
import com.deepexi.dd.domain.transaction.remote.finance.FinanceCreditClient;
import com.deepexi.dd.domain.transaction.remote.order.*;
import com.deepexi.dd.domain.transaction.service.FinanceCreditService;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.domain.transaction.service.common.ToolLinkService;
import com.deepexi.dd.domain.transaction.util.SaleOrderInfoUtils;
import com.deepexi.dd.middle.finance.api.FinanceBankAccountApi;
import com.deepexi.dd.middle.finance.domain.dto.*;
import com.deepexi.dd.middle.finance.domain.query.FinanceAmountRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.DateUtils;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.BusinessPartnerResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-20 11:30
 * TODO 信用账户、余额账户 (Management)
 */
@Service
@Slf4j
public class FinanceCreditServiceImpl implements FinanceCreditService {
    @Autowired
    FinanceCreditClient financeCreditClient;
    @Autowired
    SalePickGoodsInfoClient salePickGoodsInfoClient;
    @Autowired
    FinanceAmountClient financeAmountClient;
    @Autowired
    SaleOrderInfoService saleOrderInfoService;
    @Autowired
    SaleOrderInfoClient saleOrderInfoClient;
    @Autowired
    ToolLinkService toolLinkService;
    @Autowired
    private SaleOrderSplitRecordClient saleOrderSplitRecordClient;
    @Autowired
    IdentifierGenerator identifierGenerator;
    @Autowired
    BusinessPartnerClient businessPartnerClient;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;
    @Autowired
    FinanceBankAccountApi financeBankAccountApi;
    @Autowired
    SaleOrderInfoUtils saleOrderInfoUtils;
    @Autowired
    FinanceCreditRepaymentBillClient financeCreditRepaymentBillClient;
    @Autowired
    MqMessageProducter mqMessageProducter;
    @Autowired
    private ToolActionExcutor<SaleOrderResponseDTO> toolActionExcutor;

    private static final String TOPOIC = "DEEPEXI_DD";

    private static final String FINANCE_CREDIT_REPAYMENT = "FINANCE_CREDIT_REPAYMENT";

    private static final String SUCCESS = "0";
    private static final String YYYYMMDD = "yyyyMMdd";

    Logger logger = LoggerFactory.getLogger(FinanceCreditServiceImpl.class);

    /**
     * TODO 调用财务中心查询伙伴信用额度
     *
     * @param saleOrderPayRequestDTO
     * @return
     */
    @Override
    public FinanceCreditResponseDTO queryCreditBySalePickOrderId(@Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        try {
            Optional.ofNullable(saleOrderPayRequestDTO).orElseThrow(() -> new ApplicationException("参数为空"));
            Optional.ofNullable(saleOrderPayRequestDTO.getOrderType()).orElseThrow(() -> new ApplicationException("订单类型为空"));
            FinanceCreditRequestQuery financeCreditRequestQuery = new FinanceCreditRequestQuery();
            if (saleOrderPayRequestDTO.getOrderType() == 2) {
                Payload<FinanceCreditRepaymentBillResponseDTO> payload = financeCreditRepaymentBillClient.selectById(saleOrderPayRequestDTO.getId());
                if (!SUCCESS.equals(payload.getCode())) {
                    throw new ApplicationException("还款单不存在");
                }
                FinanceCreditRepaymentBillResponseDTO billResponseDTO = GeneralConvertUtils.conv(payload.getPayload(), FinanceCreditRepaymentBillResponseDTO.class);
                Optional.ofNullable(billResponseDTO).orElseThrow(() -> new ApplicationException("还款单不存在"));
                financeCreditRequestQuery.setIsolationId(billResponseDTO.getIsolationId());
                financeCreditRequestQuery.setCustomerId(billResponseDTO.getPartnerId());
                financeCreditRequestQuery.setTenantId(billResponseDTO.getTenantId());
                financeCreditRequestQuery.setAppId(billResponseDTO.getAppId());
            } else if (saleOrderPayRequestDTO.getOrderType() == 1) {
                SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = salePickGoodsInfoClient.selectById(saleOrderPayRequestDTO.getId());
                Optional.ofNullable(salePickGoodsInfoResponseDTO).orElseThrow(() -> new ApplicationException("提货单不存在"));
                BusinessPartnerResponseDTO businessPartner = saleOrderInfoUtils.getBusinessPartner(salePickGoodsInfoResponseDTO.getTenantId(), salePickGoodsInfoResponseDTO.getAppId(), salePickGoodsInfoResponseDTO.getBuyerId());
                financeCreditRequestQuery.setIsolationId(salePickGoodsInfoResponseDTO.getSellerId() + "");
                financeCreditRequestQuery.setCustomerId(businessPartner.getId());
                financeCreditRequestQuery.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
                financeCreditRequestQuery.setAppId(salePickGoodsInfoResponseDTO.getAppId());
            } else if (saleOrderPayRequestDTO.getOrderType() == 0) {
                SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = new SaleOrderInfoRequestQuery();
                saleOrderInfoRequestQuery.setCode(saleOrderPayRequestDTO.getOrderCode());
                saleOrderInfoRequestQuery.setId(saleOrderPayRequestDTO.getId());
                Payload<SaleOrderResponseDTO> saleOrderInfoResponseDTOPayload = saleOrderInfoClient.selectSaleOrder(saleOrderInfoRequestQuery);
                if (SUCCESS.equals(saleOrderInfoResponseDTOPayload.getCode()) && saleOrderInfoResponseDTOPayload.getPayload() != null) {
                    SaleOrderInfoResponseDTO saleOrder = GeneralConvertUtils.conv(saleOrderInfoResponseDTOPayload.getPayload(), SaleOrderInfoResponseDTO.class);
                    financeCreditRequestQuery.setIsolationId(saleOrder.getSellerId() + "");
                    financeCreditRequestQuery.setCustomerId(saleOrder.getPartnerId());
                    financeCreditRequestQuery.setTenantId(saleOrder.getTenantId());
                    financeCreditRequestQuery.setAppId(saleOrder.getAppId());
                } else {
                    //搜索父订单
                    SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO = saleOrderSplitRecordClient.selectById(saleOrderPayRequestDTO.getId());
                    Optional.ofNullable(saleOrderSplitRecordResponseDTO).orElseThrow(() -> new ApplicationException("订单不存在"));
                    financeCreditRequestQuery.setIsolationId(saleOrderSplitRecordResponseDTO.getSellerId() + "");
                    financeCreditRequestQuery.setCustomerId(saleOrderSplitRecordResponseDTO.getPartnerId());
                    financeCreditRequestQuery.setTenantId(saleOrderSplitRecordResponseDTO.getTenantId());
                    financeCreditRequestQuery.setAppId(saleOrderSplitRecordResponseDTO.getAppId());
                }
            }
            Optional.ofNullable(financeCreditRequestQuery).orElseThrow(() -> new ApplicationException("查询条件错误"));
            logger.info("查询信用条件", JsonUtil.bean2JsonString(financeCreditRequestQuery));
            Payload<List<FinanceCreditResponseDTO>> payload = financeCreditClient.listFinanceCredits(financeCreditRequestQuery);
            if (!Objects.isNull(payload) && !Objects.isNull(payload.getPayload())) {
                List<FinanceCreditResponseDTO> dtoList = GeneralConvertUtils.convert2List(payload.getPayload(), FinanceCreditResponseDTO.class);
                if (CollectionUtil.isNotEmpty(dtoList)) {
                    return dtoList.get(0);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            logger.error("信用查询异常:{}", e);
            saleOrderInfoUtils.addPayFailed(saleOrderPayRequestDTO.getId(), saleOrderPayRequestDTO.getOrderType(), "账户信用查询异常");
            throw new ApplicationException(e.getMessage());
        }
        return null;
    }

    /**
     * TODO 调用财务中心账户扣减信用额度
     * 生成信用明细、改变状态/支付金额、触发链路扭转
     *
     * @param dto
     * @return
     */
    @Override
    public Boolean creditPayment(FinanceCreditPaymentRequestDTO dto) throws Exception {

        Optional.ofNullable(dto).orElseThrow(() -> new ApplicationException("参数为空"));
        Optional.ofNullable(dto.getOrderType()).orElseThrow(() -> new ApplicationException("订单类型为空"));
        //全局生成流水号
        dto.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.CREDIT_CODE.getType(), IdentifierTypeEnum.CREDIT_CODE.getPrefix()
                , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.CREDIT_CODE.getLen()));//信用明细流水号
        dto.setPayCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getType(), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getPrefix()
                , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getLen()));//支付流水号

        Payload<Boolean> booleanPayload = new Payload<>();//扣减结果
        //保存伙伴信息
        BusinessPartnerResponseDTO businessPartner = new BusinessPartnerResponseDTO();
        if (dto.getOrderType() == 0) {
            //网批单
            SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = new SaleOrderInfoRequestQuery();
            saleOrderInfoRequestQuery.setCode(dto.getOrderCode());
            saleOrderInfoRequestQuery.setId(dto.getOrderId());
            Payload<SaleOrderResponseDTO> saleOrderInfos = saleOrderInfoClient.selectSaleOrder(saleOrderInfoRequestQuery);
            if (SUCCESS.equals(saleOrderInfos.getCode()) && saleOrderInfos.getPayload() != null) {
                SaleOrderResponseDTO dtoPayloadPayload = GeneralConvertUtils.conv(saleOrderInfos.getPayload(), SaleOrderResponseDTO.class);
                businessPartner = saleOrderInfoUtils.getBusinessPartner(dtoPayloadPayload.getTenantId(),
                                                                        dtoPayloadPayload.getAppId(),
                                                                        dtoPayloadPayload.getBuyerId());
                //财务中心依赖
                dto.setOrgId(dtoPayloadPayload.getAscriptionOrgId());//一级组织
                dto.setIsolationId(dtoPayloadPayload.getSellerId() + "");
                dto.setCustomerId(businessPartner.getId());
                dto.setCustomerName(businessPartner.getName());

                dto.setOrderType(0);//网批单同步财务中心状态0
                dto.setOrderCode(dtoPayloadPayload.getCode());
                dto.setOrderId(dtoPayloadPayload.getId());
                dto.setUserId(appRuntimeEnv.getUserId());
                dto.setUserName(appRuntimeEnv.getUsername());
                dto.setType(3);
                if (dto.getHappenLimit().compareTo(dtoPayloadPayload.getAccrueAmount().subtract(dtoPayloadPayload.getPayAmount())) != 0) {
                    throw new ApplicationException("支付失败!发生金额与订单应付金额不符");
                }
                logger.info("普通单开始扣减信用:{}", JsonUtil.bean2JsonString(dto));
                booleanPayload = financeCreditClient.creditPayment(dto);//扣减信用，生成交易明细
                logger.info("普通单扣减信用结束:{}", JsonUtil.bean2JsonString(booleanPayload));
                if (booleanPayload.getPayload()) {
                    // 扣减成功,直接改订单已付金额，业务扭转时会更改子单状态
                    SaleOrderResponseDTO saleOrderResponseDTO = GeneralConvertUtils.conv(dtoPayloadPayload, SaleOrderResponseDTO.class);
                    BigDecimal payAmount = saleOrderResponseDTO.getPayAmount().add(dto.getHappenLimit());
                    payAmount = payAmount == null ? BigDecimal.valueOf(0) : payAmount;
                    saleOrderResponseDTO.setPayAmount(payAmount);
                    //更新金额
                    SaleOrderInfoAmountEditDTO editDTO = new SaleOrderInfoAmountEditDTO();
                    editDTO.setId(saleOrderResponseDTO.getId());
                    editDTO.setAmount(payAmount);
                    editDTO.setVersion(saleOrderResponseDTO.getVersion());
                    editDTO.setStatus(saleOrderResponseDTO.getStatus());
                    saleOrderInfoClient.updateOrderAmount(editDTO);
                    logger.info("更新普通单已付金额:{}", payAmount);
                    //销售单更新支付记录所关联订单编号、支付类型、操作记录
                    saleOrderInfoUtils.updatePayOrderCode(dtoPayloadPayload.getId(), dto.getOrderCode());
                    saleOrderInfoUtils.updatePaymentType(dto.getOrderId(), dto.getOrderCode(), 0, 3);
                    saleOrderInfoUtils.addPaySuccess(dtoPayloadPayload.getId(), 1, "信用支付成功");
                }
            } else {
                //如果搜索不到订单 则根据ID搜父订单
                SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO = saleOrderSplitRecordClient.selectById(dto.getOrderId());
                Optional.ofNullable(saleOrderSplitRecordResponseDTO).orElseThrow(() -> new ApplicationException("订单不存在"));
                if (dto.getHappenLimit().compareTo(saleOrderSplitRecordResponseDTO.getAccrueAmount().subtract(saleOrderSplitRecordResponseDTO.getPayAmount())) != 0) {
                    throw new ApplicationException("支付失败!发生金额与订单应付金额不符");
                }
                businessPartner = saleOrderInfoUtils.getBusinessPartner(saleOrderSplitRecordResponseDTO.getTenantId(),
                                                                        saleOrderSplitRecordResponseDTO.getAppId(),
                                                                        saleOrderSplitRecordResponseDTO.getBuyerId());
                //TODO 再次搜索父单下所有子单产生资金明细 资金那边只会根据子单生成交易记录及扣减信用
                Payload<List<SaleOrderResponseDTO>> payloadOrders = saleOrderInfoClient.selectSaleOrderByParentCode(saleOrderSplitRecordResponseDTO.getCode());
                if (SUCCESS.equals(payloadOrders.getCode()) && null != payloadOrders.getPayload()) {
                    List<SaleOrderResponseDTO> list = GeneralConvertUtils.convert2List(payloadOrders.getPayload(), SaleOrderResponseDTO.class);
                    if (CollectionUtil.isNotEmpty(list)) {
                        logger.info("网批单组合扣减数据开始:{}", JsonUtil.bean2JsonString(list));
                        List<FinanceCreditPaymentRequestDTO> updateDate = new ArrayList<>();//批量扣减数据源
                        for (SaleOrderResponseDTO saleOrderResponseDTO : list) {
                            FinanceCreditPaymentRequestDTO updateCredit = new FinanceCreditPaymentRequestDTO();//组装扣减数据
                            updateCredit.setOrgId(businessPartner.getOrgId());
                            updateCredit.setIsolationId(saleOrderResponseDTO.getSellerId() + "");
                            updateCredit.setCustomerId(businessPartner.getId());
                            updateCredit.setCustomerName(businessPartner.getName());
                            updateCredit.setOrderType(0);//普通网批单同步财务中心状态0
                            updateCredit.setOrderCode(saleOrderResponseDTO.getCode());
                            updateCredit.setOrderId(saleOrderResponseDTO.getId());
                            updateCredit.setUserId(appRuntimeEnv.getUserId());
                            updateCredit.setUserName(appRuntimeEnv.getUsername());
                            updateCredit.setHappenLimit(saleOrderResponseDTO.getAccrueAmount().subtract(saleOrderResponseDTO.getPayAmount()));//子单支付金额，上面已校验过
                            updateCredit.setType(3);//操作类型：1-信用变更，2-退款返还，3-订单扣款，4-还款返还
                            //局部生成明细、支付流水等信息
                            updateCredit.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.CREDIT_CODE.getType(), IdentifierTypeEnum.CREDIT_CODE.getPrefix()
                                    , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.CREDIT_CODE.getLen()));//信用明细流水号
                            updateCredit.setPayCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getType(), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getPrefix()
                                    , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getLen()));//支付流水号
                            updateCredit.setCreditId(dto.getCreditId());
                            updateCredit.setAppId(dto.getAppId());
                            updateCredit.setTenantId(dto.getTenantId());
                            updateDate.add(updateCredit);
                        }
                        Optional.ofNullable(updateDate).orElseThrow(() -> new ApplicationException("拆单:{}扣减数据为空,请检查订单是否正常"));
                        logger.info("网批单组合扣减数据结束:{}", JsonUtil.bean2JsonString(updateDate));
                        //开始扣减
                        FinanceCreditPaymentRequestDTO update = new FinanceCreditPaymentRequestDTO();
                        update.setDtoList(updateDate);
                        booleanPayload = financeCreditClient.creditPayment(update);//扣减信用，生成交易明细
                        logger.info("组合扣减信用结果:{}", JsonUtil.bean2JsonString(booleanPayload));
                        //扣减成功更新本地数据库
                        if (booleanPayload.getPayload()) {
                            //父单更新支付记录关联订单编号、支付类型、操作记录、已付金额
                            BigDecimal payAmount = saleOrderSplitRecordResponseDTO.getPayAmount().add(dto.getHappenLimit());
                            payAmount = payAmount == null ? BigDecimal.valueOf(0) : payAmount;
                            saleOrderSplitRecordResponseDTO.setPayAmount(payAmount);
                            saleOrderSplitRecordClient.updateById(saleOrderSplitRecordResponseDTO.getId(), saleOrderSplitRecordResponseDTO.clone(SaleOrderSplitRecordRequestDTO.class));
                            saleOrderInfoUtils.addPaySuccess(saleOrderSplitRecordResponseDTO.getId(), 1, "信用支付父单成功");
                            //saleOrderInfoUtils.updateSaleOrderInfoCode(0, saleOrderSplitRecordResponseDTO.getCode(), dto.getOrderCode());
                            //saleOrderInfoUtils.updatePaymentType(dto.getOrderId(), dto.getOrderCode(), 0, 3);
                            //子单更新支付记录关联订单编号、支付类型、操作记录、已付金额
                            for (SaleOrderResponseDTO saleOrderResponseDTO : list) {
                                SaleOrderInfoAmountEditDTO editDTO = new SaleOrderInfoAmountEditDTO();
                                editDTO.setId(saleOrderResponseDTO.getId());
                                editDTO.setAmount(saleOrderResponseDTO.getAccrueAmount());
                                editDTO.setVersion(saleOrderResponseDTO.getVersion());
                                editDTO.setStatus(saleOrderResponseDTO.getStatus());
                                saleOrderInfoClient.updateOrderAmount(editDTO);
                                saleOrderInfoUtils.updatePayOrderCode(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getCode());
                                saleOrderInfoUtils.updatePaymentType(saleOrderResponseDTO.getId(), saleOrderResponseDTO.getCode(), 0, 3);
                                saleOrderInfoUtils.addPaySuccess(saleOrderResponseDTO.getId(), 1, "信用支付子单成功");
                                //单独扭转订单状态、支付状态
                                BigDecimal payOrderAmount = editDTO.getAmount();
                                //获取业务链路状态
                                PayStatusQueryDTO payStatusQuery = new PayStatusQueryDTO();
                                payStatusQuery.setAppId(saleOrderResponseDTO.getAppId());
                                payStatusQuery.setTenantId(saleOrderResponseDTO.getTenantId());
                                payStatusQuery.setId(saleOrderResponseDTO.getId());
                                payStatusQuery.setTotalCollectionAmount(saleOrderResponseDTO.getAccrueAmount());//营收金额
                                payStatusQuery.setCollectionAmount(payOrderAmount);//已付款金额
                                Integer status = getPayStatus(payStatusQuery);//支付状态
                                com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO requestDTO=new com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO();
                                requestDTO.setPaymentStatus(status);
                                requestDTO.setId(saleOrderResponseDTO.getId());
                                saleOrderInfoClient.updateMainOrderById(saleOrderResponseDTO.getId(),requestDTO);

                                toolActionExcutor.excute(LinkBusinessCodeEnum.BuyerPayment.name(), ListTypeEnum.SalesOrder.name(),
                                                         saleOrderResponseDTO.getId(), saleOrderResponseDTO.getTenantId(), saleOrderResponseDTO.getAppId());
                            }
                        }
                    }
                }
            }
        } else if (dto.getOrderType() == 1) {
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = salePickGoodsInfoClient.selectById(dto.getOrderId());
            Optional.ofNullable(salePickGoodsInfoResponseDTO).orElseThrow(() -> new ApplicationException("提货单不存在"));
            businessPartner = saleOrderInfoUtils.getBusinessPartner(salePickGoodsInfoResponseDTO.getTenantId(),
                                                                    salePickGoodsInfoResponseDTO.getAppId(),
                                                                    salePickGoodsInfoResponseDTO.getBuyerId());
            //财务中心依赖
            dto.setOrgId(salePickGoodsInfoResponseDTO.getAscriptionOrgId());//所属一级组织ID
            dto.setIsolationId(salePickGoodsInfoResponseDTO.getSellerId() + "");
            dto.setCustomerId(businessPartner.getId());
            dto.setCustomerName(businessPartner.getName());
            dto.setOrderType(1);//提货单同步财务中心状态1
            dto.setOrderCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());
            dto.setOrderId(salePickGoodsInfoResponseDTO.getId());
            dto.setUserId(appRuntimeEnv.getUserId());
            dto.setUserName(appRuntimeEnv.getUsername());
            if (dto.getHappenLimit().compareTo(salePickGoodsInfoResponseDTO.getTotalGoodsMoney().subtract(salePickGoodsInfoResponseDTO.getPayAmount())) != 0) {
                throw new ApplicationException("支付失败!发生金额与订单应付金额不符");
            }
            dto.setHappenLimit(dto.getHappenLimit());
            dto.setType(3);
            logger.info("提货单开始扣减信用:{}", JsonUtil.bean2JsonString(dto));
            booleanPayload = financeCreditClient.creditPayment(dto);//扣减信用，生成交易明细
            logger.info("提货单扣减信用结束:{}", JsonUtil.bean2JsonString(booleanPayload));
            if (booleanPayload.getPayload()) {
                //直接改提货单已付金额，业务扭转时会更改状态
                SalePickGoodsInfoRequestDTO salePickGoodsInfoRequestDTO = new SalePickGoodsInfoRequestDTO();
                salePickGoodsInfoRequestDTO.setId(salePickGoodsInfoResponseDTO.getId());
                BigDecimal amount = salePickGoodsInfoResponseDTO.getPayAmount().add(dto.getHappenLimit());//已付金额
                salePickGoodsInfoRequestDTO.setPayAmount(amount);
                salePickGoodsInfoRequestDTO.setVersion(salePickGoodsInfoResponseDTO.getVersion() + 1);
                salePickGoodsInfoRequestDTO.setPaymentType(3);
                salePickGoodsInfoRequestDTO.setPaymentStatus(StatusCodeEnum.ReceivedPayments.getCode());
                salePickGoodsInfoRequestDTO.setStatus(StatusCodeEnum.ToConfirm.getCode());
                salePickGoodsInfoClient.updateById(salePickGoodsInfoResponseDTO.getId(), salePickGoodsInfoRequestDTO);
                saleOrderInfoUtils.addPaySuccess(salePickGoodsInfoRequestDTO.getId(), 2, "信用支付成功");
            }
        }
        logger.info("支付结果:{}", booleanPayload.getPayload());
        //扭转订单业务链路
        if (booleanPayload.getPayload()) {
            logger.info("信用扣减成功，开始扭转订单链路:{}", JsonUtil.bean2JsonString(dto));
            SaleOrderPayNotifyDto saleOrderPayNotifyDto = dto.clone(SaleOrderPayNotifyDto.class);
            saleOrderPayNotifyDto.setType(1);
            saleOrderPayNotifyDto.setTenanId(dto.getTenantId());
            saleOrderPayNotifyDto.setAmount(dto.getHappenLimit());
            saleOrderPayNotifyDto.setPayNo(dto.getPayCode());
            saleOrderInfoService.payOrderNextStep(saleOrderPayNotifyDto);
        }
        return booleanPayload.getPayload();
    }

    /**
     * TODO 调用财务中心查询伙伴账户余额
     *
     * @param saleOrderPayRequestDTO
     * @return
     * @throws Exception
     */
    @Override
    public FinanceAmountResponseDTO queryAmountBySalePickOrderId(@Valid SalePickOrderPayRequestDTO
                                                                         saleOrderPayRequestDTO) throws Exception {

        Optional.ofNullable(saleOrderPayRequestDTO).orElseThrow(() -> new ApplicationException("参数为空"));
        Optional.ofNullable(saleOrderPayRequestDTO.getOrderType()).orElseThrow(() -> new ApplicationException("订单类型为空"));
        FinanceAmountRequestQuery amountRequestQuery = new FinanceAmountRequestQuery();
        if (saleOrderPayRequestDTO.getOrderType() == 2) {
            Payload<FinanceCreditRepaymentBillResponseDTO> payload = financeCreditRepaymentBillClient.selectById(saleOrderPayRequestDTO.getId());
            if (!SUCCESS.equals(payload.getCode())) {
                throw new ApplicationException("还款单不存在");
            }
            FinanceCreditRepaymentBillResponseDTO billResponseDTO = GeneralConvertUtils.conv(payload.getPayload(), FinanceCreditRepaymentBillResponseDTO.class);
            Optional.ofNullable(billResponseDTO).orElseThrow(() -> new ApplicationException("还款单不存在"));
            amountRequestQuery.setIsolationId(billResponseDTO.getIsolationId());
            amountRequestQuery.setRelationId(billResponseDTO.getPartnerId());
            amountRequestQuery.setTenantId(billResponseDTO.getTenantId());
            amountRequestQuery.setAppId(billResponseDTO.getAppId());
        } else if (saleOrderPayRequestDTO.getOrderType() == 1) {
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = salePickGoodsInfoClient.selectById(saleOrderPayRequestDTO.getId());
            Optional.ofNullable(salePickGoodsInfoResponseDTO).orElseThrow(() -> new ApplicationException("提货单不存在"));
            amountRequestQuery.setIsolationId(salePickGoodsInfoResponseDTO.getSellerId() + "");
            BusinessPartnerResponseDTO businessPartner = saleOrderInfoUtils.getBusinessPartner(salePickGoodsInfoResponseDTO.getTenantId(), salePickGoodsInfoResponseDTO.getAppId(), salePickGoodsInfoResponseDTO.getBuyerId());
            amountRequestQuery.setRelationId(businessPartner.getId());
            amountRequestQuery.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
            amountRequestQuery.setAppId(salePickGoodsInfoResponseDTO.getAppId());
        } else if (saleOrderPayRequestDTO.getOrderType() == 0) {
            SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = new SaleOrderInfoRequestQuery();
            saleOrderInfoRequestQuery.setCode(saleOrderPayRequestDTO.getOrderCode());
            saleOrderInfoRequestQuery.setId(saleOrderPayRequestDTO.getId());
            Payload<SaleOrderResponseDTO> saleOrderInfoResponseDTOPayload = saleOrderInfoClient.selectSaleOrder(saleOrderInfoRequestQuery);
            if (SUCCESS.equals(saleOrderInfoResponseDTOPayload.getCode()) && saleOrderInfoResponseDTOPayload.getPayload() != null) {
                SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = GeneralConvertUtils.conv(saleOrderInfoResponseDTOPayload.getPayload(), SaleOrderInfoResponseDTO.class);
                amountRequestQuery.setIsolationId(saleOrderInfoResponseDTO.getSellerId() + "");
                amountRequestQuery.setRelationId(saleOrderInfoResponseDTO.getPartnerId());
                amountRequestQuery.setTenantId(saleOrderInfoResponseDTO.getTenantId());
                amountRequestQuery.setAppId(saleOrderInfoResponseDTO.getAppId());
            } else {
                //搜索父订单
                SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO = saleOrderSplitRecordClient.selectById(saleOrderPayRequestDTO.getId());
                Optional.ofNullable(saleOrderSplitRecordResponseDTO).orElseThrow(() -> new ApplicationException("订单不存在"));
                amountRequestQuery.setIsolationId(saleOrderSplitRecordResponseDTO.getSellerId() + "");
                amountRequestQuery.setRelationId(saleOrderSplitRecordResponseDTO.getPartnerId());
                amountRequestQuery.setTenantId(saleOrderSplitRecordResponseDTO.getTenantId());
                amountRequestQuery.setAppId(saleOrderSplitRecordResponseDTO.getAppId());
            }
        }
        Optional.ofNullable(amountRequestQuery).orElseThrow(() -> new ApplicationException("查询条件错误"));
        logger.info("查询余额条件", JsonUtil.bean2JsonString(amountRequestQuery));
        Payload<List<FinanceAmountResponseDTO>> payload = financeAmountClient.listFinanceAmounts(amountRequestQuery);
        if (!Objects.isNull(payload) && !Objects.isNull(payload.getPayload())) {
            List<FinanceAmountResponseDTO> dtoList = GeneralConvertUtils.convert2List(payload.getPayload(), FinanceAmountResponseDTO.class);
            if (CollectionUtil.isNotEmpty(dtoList)) {
                return dtoList.get(0);
            } else {
                return null;
            }
        }

        return null;

    }

    /**
     * 调用财务中心扣减账户余额、扭转链路、更新订单
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Boolean FinanceAmount(FinanceAmountPaymentRequestDTO dto) throws Exception {
        if (ObjectUtils.isEmpty(dto)) {
            throw new ApplicationException("参数为空");
        }
        //组装余额扣减明细、收款单、支付流水
        dto = this.assembleBalanceDetails(dto);
        Payload<Boolean> booleanPayload = new Payload<>();
        try {
            logger.info("余额开始扣减:{}", JsonUtil.bean2JsonString(dto));
            booleanPayload = financeAmountClient.amountPayment(dto);
            logger.info("余额扣减结束:{}", JsonUtil.bean2JsonString(booleanPayload));
        } catch (Exception e) {
            logger.error("余额扣减异常:{}", e);
            throw new ApplicationException(e.getMessage());
        }
        //改变订单已付金额
        if (booleanPayload.getPayload()) {
            if (dto.getFinanceAmountDetailRequestDTO().getOrderType() == 0) {
                SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = new SaleOrderInfoRequestQuery();
                saleOrderInfoRequestQuery.setCode(dto.getFinanceAmountDetailRequestDTO().getOrderCode());
                saleOrderInfoRequestQuery.setId(dto.getFinanceAmountDetailRequestDTO().getOrderId());
                Payload<SaleOrderResponseDTO> responseDTOPayload = saleOrderInfoClient.selectSaleOrder(saleOrderInfoRequestQuery);
                //Payload<SaleOrderResponseDTO> responseDTOPayload = saleOrderInfoClient.selectSaleOrder(dto.getFinanceAmountDetailRequestDTO().getOrderCode());
                SaleOrderResponseDTO responseDTOPayloadPayload = GeneralConvertUtils.conv(responseDTOPayload.getPayload(), SaleOrderResponseDTO.class);
                if (SUCCESS.equals(responseDTOPayload.getCode()) && responseDTOPayloadPayload != null) {
                    // 扣减成功,直接改订单已付金额，业务扭转时会更改子单状态
                    SaleOrderResponseDTO saleOrderResponseDTO = GeneralConvertUtils.conv(responseDTOPayloadPayload, SaleOrderResponseDTO.class);
                    BigDecimal payAmount = saleOrderResponseDTO.getPayAmount().add(dto.getFinanceAmountDetailRequestDTO().getHappenAmount());
                    payAmount = payAmount == null ? BigDecimal.valueOf(0) : payAmount;
                    saleOrderResponseDTO.setPayAmount(payAmount);
                    SaleOrderInfoAmountEditDTO editDTO = new SaleOrderInfoAmountEditDTO();
                    editDTO.setId(saleOrderResponseDTO.getId());
                    editDTO.setAmount(payAmount);
                    editDTO.setVersion(saleOrderResponseDTO.getVersion());
                    editDTO.setStatus(saleOrderResponseDTO.getStatus());
                    saleOrderInfoClient.updateOrderAmount(editDTO);
                    logger.info("更新子单已付金额:{}", payAmount);
                    //销售父单更新支付记录所关联订单编号、支付类型、操作记录
                    saleOrderInfoUtils.updatePayOrderCode(responseDTOPayloadPayload.getId(), dto.getFinanceAmountDetailRequestDTO().getOrderCode());
                    saleOrderInfoUtils.addPaySuccess(responseDTOPayloadPayload.getId(), 1, "余额支付成功");
                    saleOrderInfoUtils.updatePaymentType(dto.getFinanceAmountDetailRequestDTO().getId(), dto.getFinanceAmountDetailRequestDTO().getOrderCode(), 0, 4);
                } else {
                    //如果搜索不到订单 则根据ID搜父订单
                    SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO = saleOrderSplitRecordClient.selectById(dto.getFinanceAmountDetailRequestDTO().getOrderId());
                    // 更新父单金额
                    BigDecimal payAmount = saleOrderSplitRecordResponseDTO.getPayAmount().add(dto.getFinanceAmountDetailRequestDTO().getHappenAmount());
                    payAmount = payAmount == null ? BigDecimal.valueOf(0) : payAmount;
                    saleOrderSplitRecordResponseDTO.setPayAmount(payAmount);
                    saleOrderSplitRecordClient.updateById(saleOrderSplitRecordResponseDTO.getId(), saleOrderSplitRecordResponseDTO.clone(SaleOrderSplitRecordRequestDTO.class));
                    //销售父单更新支付记录所关联订单编号、支付类型、操作记录
                    saleOrderInfoUtils.addPaySuccess(saleOrderSplitRecordResponseDTO.getId(), 1, "余额支付成功");
                    saleOrderInfoUtils.updateSaleOrderInfoCode(1, saleOrderSplitRecordResponseDTO.getCode(), dto.getFinanceAmountDetailRequestDTO().getOrderCode());
                    saleOrderInfoUtils.updatePaymentType(dto.getFinanceAmountDetailRequestDTO().getId(), dto.getFinanceAmountDetailRequestDTO().getOrderCode(), 0, 4);
                    logger.info("原来父单已付金额:{}" + saleOrderSplitRecordResponseDTO.getPayAmount() + "\t更新父单已付金额:{}", payAmount);
                    if (payAmount.compareTo(saleOrderSplitRecordResponseDTO.getAccrueAmount()) >= 0) {
                        Payload<List<SaleOrderResponseDTO>> payloadOrders = saleOrderInfoClient.selectSaleOrderByParentCode(saleOrderSplitRecordResponseDTO.getCode());
                        if (SUCCESS.equals(payloadOrders.getCode()) && null != payloadOrders.getPayload()) {
                            List<SaleOrderResponseDTO> list = GeneralConvertUtils.convert2List(payloadOrders.getPayload(), SaleOrderResponseDTO.class);
                            if (CollectionUtil.isNotEmpty(list)) {
                                for (SaleOrderResponseDTO saleOrderResponseDTO : list) {
                                    //如果父订单已支付完成，则改变子订单为支付金额=应收金额
                                    SaleOrderInfoAmountEditDTO editDTO = new SaleOrderInfoAmountEditDTO();
                                    editDTO.setId(saleOrderResponseDTO.getId());
                                    editDTO.setAmount(saleOrderResponseDTO.getAccrueAmount());
                                    editDTO.setVersion(saleOrderResponseDTO.getVersion());
                                    editDTO.setStatus(saleOrderResponseDTO.getStatus());
                                    saleOrderInfoClient.updateOrderAmount(editDTO);
                                    logger.info("orderInfoId:{}" + saleOrderResponseDTO.getId() + "\t更新子单已付金额:{}", saleOrderResponseDTO.getAccrueAmount());
                                }
                            }
                        }
                    }
                }
            } else if (dto.getFinanceAmountDetailRequestDTO().getOrderType() == 1) {
                //直接改提货单已付金额，业务扭转时会更改状态
                SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = salePickGoodsInfoClient.selectById(dto.getFinanceAmountDetailRequestDTO().getOrderId());
                SalePickGoodsInfoRequestDTO salePickGoodsInfoRequestDTO = new SalePickGoodsInfoRequestDTO();
                salePickGoodsInfoRequestDTO.setId(salePickGoodsInfoResponseDTO.getId());
                BigDecimal amount = salePickGoodsInfoResponseDTO.getPayAmount().add(dto.getFinanceAmountDetailRequestDTO().getHappenAmount());//已付金额
                salePickGoodsInfoRequestDTO.setPayAmount(amount);
                salePickGoodsInfoRequestDTO.setVersion(salePickGoodsInfoResponseDTO.getVersion() + 1);
                salePickGoodsInfoRequestDTO.setPaymentType(4);
                salePickGoodsInfoRequestDTO.setPaymentStatus(StatusCodeEnum.ReceivedPayments.getCode());
                salePickGoodsInfoRequestDTO.setStatus(StatusCodeEnum.ToConfirm.getCode());
                salePickGoodsInfoClient.updateById(salePickGoodsInfoResponseDTO.getId(), salePickGoodsInfoRequestDTO);
                saleOrderInfoUtils.addPaySuccess(salePickGoodsInfoResponseDTO.getId(), 2, "余额支付成功");
            } else if (dto.getFinanceAmountDetailRequestDTO().getOrderType() == 2)//还款单付款
            {
                FinanceRepaymentNotifyDTO notifyDTO = new FinanceRepaymentNotifyDTO();
                notifyDTO.setAmount(dto.getFinanceAmountDetailRequestDTO().getHappenAmount());
                notifyDTO.setCreditRepaymentId(dto.getFinanceAmountDetailRequestDTO().getOrderId());
                notifyDTO.setPayNo(dto.getFinancePaymentRecordsRequestDTO().getCode());
                //发送支付成功MQ
                mqMessageProducter.sendMsg(TOPOIC, FINANCE_CREDIT_REPAYMENT, JsonUtil.bean2JsonString(notifyDTO));
            }
        }
        //扭转业务链路
        if (booleanPayload.getPayload()) {
            SaleOrderPayNotifyDto saleOrderPayNotifyDto = dto.getFinanceAmountDetailRequestDTO().clone(SaleOrderPayNotifyDto.class);
            saleOrderPayNotifyDto.setType(1);
            saleOrderPayNotifyDto.setTenanId(dto.getFinanceAmountDetailRequestDTO().getTenantId());
            saleOrderPayNotifyDto.setAmount(dto.getFinanceAmountDetailRequestDTO().getHappenAmount());
            saleOrderPayNotifyDto.setPayNo(dto.getFinanceAmountDetailRequestDTO().getCode());
            saleOrderInfoService.payOrderNextStep(saleOrderPayNotifyDto);
        }
        return booleanPayload.getPayload();
    }

    /**
     * 组装扣减余额明细
     *
     * @param dto
     * @return
     */
    private FinanceAmountPaymentRequestDTO assembleBalanceDetails(FinanceAmountPaymentRequestDTO dto) throws
            Exception {
        Optional.ofNullable(dto).orElseThrow(() -> new ApplicationException("参数为空"));
        //当前方法内全局保存卖家伙伴信息
        BusinessPartnerResponseDTO businessPartner = new BusinessPartnerResponseDTO();
        logger.info("余额开始扣减:{}", JsonUtil.bean2JsonString(dto));
        //余额明细对象
        FinanceAmountDetailRequestDTO financeAmountDetailRequestDTO = dto.getFinanceAmountDetailRequestDTO();
        //收款单对象
        FinanceCollectionAdminRequestDTO financeCollectionAdminRequestDTO = new FinanceCollectionAdminRequestDTO();
        //支付流水对象
        FinancePaymentRecordsRequestDTO financePaymentRecordsRequestDTO = new FinancePaymentRecordsRequestDTO();
        SaleOrderResponseDTO saleOrderResponseDTO = new SaleOrderResponseDTO();
        String isolationId = "";
        if (financeAmountDetailRequestDTO.getOrderType() == 0) {
            logger.info("进入网批订单余额扣减:{}", JsonUtil.bean2JsonString(dto));
            //获取网批订单信息
            SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = new SaleOrderInfoRequestQuery();
            saleOrderInfoRequestQuery.setCode(financeAmountDetailRequestDTO.getOrderCode());
            saleOrderInfoRequestQuery.setId(financeAmountDetailRequestDTO.getOrderId());
            Payload<SaleOrderResponseDTO> responseDTOPayload = saleOrderInfoClient.selectSaleOrder(saleOrderInfoRequestQuery);
            saleOrderResponseDTO = GeneralConvertUtils.conv(responseDTOPayload.getPayload(), SaleOrderResponseDTO.class);
            if (SUCCESS.equals(responseDTOPayload.getCode()) && responseDTOPayload.getPayload() != null) {
                businessPartner = saleOrderInfoUtils.getBusinessPartner(saleOrderResponseDTO.getTenantId(), saleOrderResponseDTO.getAppId(), saleOrderResponseDTO.getBuyerId());
                Optional.ofNullable(businessPartner).orElseThrow(() -> new ApplicationException("订单伙伴信息不存在"));
                saleOrderResponseDTO.setTicketType(0);//网批单写死0
                isolationId = saleOrderResponseDTO.getSellerId() + "";
            } else {
                //如果查询子单为空，则继续查询父单
                SaleOrderSplitRecordResponseDTO saleOrderSplitRecordResponseDTO = saleOrderSplitRecordClient.selectById(dto.getFinanceAmountDetailRequestDTO().getOrderId());
                Optional.ofNullable(saleOrderSplitRecordResponseDTO).orElseThrow(() -> new ApplicationException("订单不存在"));
                businessPartner = saleOrderInfoUtils.getBusinessPartner(saleOrderSplitRecordResponseDTO.getTenantId(), saleOrderSplitRecordResponseDTO.getAppId(), saleOrderSplitRecordResponseDTO.getBuyerId());
                Optional.ofNullable(businessPartner).orElseThrow(() -> new ApplicationException("订单伙伴信息不存在"));
                saleOrderResponseDTO = saleOrderSplitRecordResponseDTO.clone(SaleOrderResponseDTO.class);
                saleOrderResponseDTO.setTicketType(0);//网批单写死0
                isolationId = saleOrderSplitRecordResponseDTO.getSellerId() + "";
            }
        } else if (financeAmountDetailRequestDTO.getOrderType() == 1) {
            logger.info("进入提货订单余额扣减:{}", JsonUtil.bean2JsonString(dto));
            //获取提货单信息
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = salePickGoodsInfoClient.selectById(financeAmountDetailRequestDTO.getOrderId());
            Optional.ofNullable(salePickGoodsInfoResponseDTO).orElseThrow(() -> new ApplicationException("提货单不存在"));
            businessPartner = saleOrderInfoUtils.getBusinessPartner(salePickGoodsInfoResponseDTO.getTenantId(), salePickGoodsInfoResponseDTO.getAppId(), salePickGoodsInfoResponseDTO.getBuyerId());
            Optional.ofNullable(businessPartner).orElseThrow(() -> new ApplicationException("订单伙伴信息不存在"));
            saleOrderResponseDTO = salePickGoodsInfoResponseDTO.clone(SaleOrderResponseDTO.class);
            saleOrderResponseDTO.setAccrueAmount(salePickGoodsInfoResponseDTO.getTotalGoodsMoney());
            saleOrderResponseDTO.setTicketType(1);//提货计划写死1 TODO 代表OrderType
            saleOrderResponseDTO.setCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());
            isolationId = salePickGoodsInfoResponseDTO.getSellerId() + "";
        } else if (financeAmountDetailRequestDTO.getOrderType() == 2)//还款单
        {
            logger.info("进入还款单余额扣减:{}", JsonUtil.bean2JsonString(dto));
            //查询还款单
            Payload<FinanceCreditRepaymentBillResponseDTO> billPayload = financeCreditRepaymentBillClient.selectById(financeAmountDetailRequestDTO.getOrderId());
            if (!SUCCESS.equals(billPayload.getCode())) {
                throw new ApplicationException("未找到还款单:{}", financeAmountDetailRequestDTO.getOrderId());
            }
            FinanceCreditRepaymentBillResponseDTO billResponseDTO = GeneralConvertUtils.conv(billPayload.getPayload(), FinanceCreditRepaymentBillResponseDTO.class);
            if(billResponseDTO.getStatus().equals(34) || billResponseDTO.getStatus().equals(24)){
                throw new ApplicationException("还款失败,该账单已还款");
            }
            isolationId = billResponseDTO.getIsolationId();
            //获取伙伴信息--买家
            BusinessPartnerRequestQuery query = new BusinessPartnerRequestQuery();
            query.setId(billResponseDTO.getPartnerId());
            query.setTenantId(billResponseDTO.getTenantId());
            query.setAppId(billResponseDTO.getAppId());
            Payload<BusinessPartnerResponseDTO> buyerPartner = businessPartnerClient.getPartner(query);
            if (!SUCCESS.equals(buyerPartner.getCode())) {
                throw new ApplicationException("获取买家伙伴信息失败");
            }
            businessPartner = GeneralConvertUtils.conv(buyerPartner.getPayload(), BusinessPartnerResponseDTO.class);
            saleOrderResponseDTO.setPartnerId(businessPartner.getId());
            saleOrderResponseDTO.setPartnerName(businessPartner.getName());
            saleOrderResponseDTO.setAccrueAmount(billResponseDTO.getAmount());
            saleOrderResponseDTO.setAppId(billResponseDTO.getAppId());
            saleOrderResponseDTO.setTenantId(billResponseDTO.getTenantId());
            saleOrderResponseDTO.setTicketDate(billResponseDTO.getCreatedTime());
            saleOrderResponseDTO.setId(billResponseDTO.getId());
            saleOrderResponseDTO.setCode(billResponseDTO.getCode());
            saleOrderResponseDTO.setPayAmount(BigDecimal.valueOf(0));
            saleOrderResponseDTO.setSellerId(Long.valueOf(billResponseDTO.getIsolationId()));
            saleOrderResponseDTO.setHandler(appRuntimeEnv.getUserId());
            saleOrderResponseDTO.setHandlerName(appRuntimeEnv.getUsername());
            saleOrderResponseDTO.setTicketType(2);
            saleOrderResponseDTO.setBuyerName(businessPartner.getName());
        }
        logger.info("组装余额扣减信息开始{}...", dto);
        if (dto.getFinanceAmountDetailRequestDTO().getHappenAmount().compareTo(saleOrderResponseDTO.getAccrueAmount().subtract(saleOrderResponseDTO.getPayAmount())) != 0) {
            throw new ApplicationException("支付失败!发生金额与订单应付金额不符");
        }
        //组装余额明细对象
        financeAmountDetailRequestDTO.setHappenAmount(saleOrderResponseDTO.getAccrueAmount().subtract(saleOrderResponseDTO.getPayAmount()));
        financeAmountDetailRequestDTO.setOrderId(saleOrderResponseDTO.getId());
        if (financeAmountDetailRequestDTO.getOrderType() == 2) {
            financeAmountDetailRequestDTO.setType(4);
        } else {
            financeAmountDetailRequestDTO.setType(2);
        }
        //TODO 此处订单类型暂时同步财务中心,提货计划写死1.网批单写死0
        financeAmountDetailRequestDTO.setOrderType(saleOrderResponseDTO.getTicketType());
        financeAmountDetailRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.AMOUNT_CODE.getType(), IdentifierTypeEnum.AMOUNT_CODE.getPrefix()
                , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.AMOUNT_CODE.getLen()));//余额明细流水号
        financeAmountDetailRequestDTO.setTenantId(businessPartner.getTenantId());
        financeAmountDetailRequestDTO.setAppId(businessPartner.getAppId());
        financeAmountDetailRequestDTO.setIsolationId(isolationId);
//        financeAmountDetailRequestDTO.setType(2);
        financeAmountDetailRequestDTO.setUserId(appRuntimeEnv.getUserId());
        financeAmountDetailRequestDTO.setUserName(appRuntimeEnv.getUsername());
        dto.setFinanceAmountDetailRequestDTO(financeAmountDetailRequestDTO);

        //根据伙伴信息查收款银行信息
        FinanceBankAccountRequestQuery financeBankAccountRequestQuery = new FinanceBankAccountRequestQuery();
        financeBankAccountRequestQuery.setAppId(saleOrderResponseDTO.getAppId());
        financeBankAccountRequestQuery.setTenantId(saleOrderResponseDTO.getTenantId());
        financeBankAccountRequestQuery.setIsProceedsAccount(0);
        financeBankAccountRequestQuery.setIsolationId(businessPartner.getIsolationId());

        //组装收款单对象
        financeCollectionAdminRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
        financeCollectionAdminRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
        financeCollectionAdminRequestDTO.setIsPaymentRecords(0);
        financeCollectionAdminRequestDTO.setCustomerId(businessPartner.getId());
        financeCollectionAdminRequestDTO.setCustomerName(businessPartner.getName());
        financeCollectionAdminRequestDTO.setHandlersId(saleOrderResponseDTO.getHandler());
        financeCollectionAdminRequestDTO.setHandlersName(saleOrderResponseDTO.getHandlerName());
        if (dto.getFinanceAmountDetailRequestDTO() != null) {
            financeCollectionAdminRequestDTO.setAccountId(dto.getFinanceAmountDetailRequestDTO().getAmountId());
        }
//        financeCollectionAdminRequestDTO.setAccountName(financeBankAccountResponseDTO.getName());
//        financeCollectionAdminRequestDTO.setAccountNumber(financeBankAccountResponseDTO.getBankAccount());
        financeCollectionAdminRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getType(), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getPrefix(), DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_COLLECTION_CODE.getLen()));
        financeCollectionAdminRequestDTO.setType(0);
        financeCollectionAdminRequestDTO.setWay(0);
        financeCollectionAdminRequestDTO.setReceiptsTime(new Date());
        financeCollectionAdminRequestDTO.setAmount(saleOrderResponseDTO.getAccrueAmount());//此处收款金额已经处理过(AccrueAmount=AccrueAmount-PayAmount)
        financeCollectionAdminRequestDTO.setSettlementId(businessPartner.getId());
        financeCollectionAdminRequestDTO.setSettlementName(businessPartner.getName());
        financeCollectionAdminRequestDTO.setIsolationId(isolationId);
        List<FinanceCollectionOrderAdminRequesDTO> financeCollectionOrderAdminRequesDTOList = new ArrayList<>();
        FinanceCollectionOrderAdminRequesDTO financeCollectionOrderAdminRequesDTO = new FinanceCollectionOrderAdminRequesDTO();
        financeCollectionOrderAdminRequesDTO.setOrderCode(saleOrderResponseDTO.getCode());
        financeCollectionOrderAdminRequesDTO.setOrderId(saleOrderResponseDTO.getId());
        financeCollectionOrderAdminRequesDTO.setSettlementAmount(dto.getFinanceAmountDetailRequestDTO().getHappenAmount());
        financeCollectionOrderAdminRequesDTO.setType(0);
        financeCollectionOrderAdminRequesDTOList.add(financeCollectionOrderAdminRequesDTO);
        financeCollectionAdminRequestDTO.setDatas(financeCollectionOrderAdminRequesDTOList);
        dto.setFinanceCollectionAdminRequestDTO(financeCollectionAdminRequestDTO);
        //组装支付流水对象
        financePaymentRecordsRequestDTO.setPartnerId(saleOrderResponseDTO.getPartnerId());
        financePaymentRecordsRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
        financePaymentRecordsRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
        financePaymentRecordsRequestDTO.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getType(), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getPrefix()
                , DateUtils.format(new Date(), YYYYMMDD), IdentifierTypeEnum.FINANCE_PAYMENT_RECORD_CODE.getLen()));//支付流水
        financePaymentRecordsRequestDTO.setOrderId(saleOrderResponseDTO.getId());
        financePaymentRecordsRequestDTO.setOrderCode(saleOrderResponseDTO.getCode());
        financePaymentRecordsRequestDTO.setOrderType(saleOrderResponseDTO.getTicketType());
        financePaymentRecordsRequestDTO.setCustomerName(saleOrderResponseDTO.getBuyerName());
        financePaymentRecordsRequestDTO.setPayType(0);
        financePaymentRecordsRequestDTO.setReceiptsTime(new Date());
        financePaymentRecordsRequestDTO.setAmount(dto.getFinanceAmountDetailRequestDTO().getHappenAmount());
        financePaymentRecordsRequestDTO.setIsolationId(isolationId);
        financePaymentRecordsRequestDTO.setOrgId(saleOrderResponseDTO.getAscriptionOrgId());
//        financePaymentRecordsRequestDTO.setProceedsAccount(financeBankAccountResponseDTO.getName());
//        financePaymentRecordsRequestDTO.setProceedsBankAccount(financeBankAccountResponseDTO.getBankAccount());
        dto.setFinancePaymentRecordsRequestDTO(financePaymentRecordsRequestDTO);
        return dto;
    }
    /**
     * 获取链路订单状态
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

}