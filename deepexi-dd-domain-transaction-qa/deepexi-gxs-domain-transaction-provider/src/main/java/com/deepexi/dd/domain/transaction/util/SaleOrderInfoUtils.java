package com.deepexi.dd.domain.transaction.util;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.remote.order.BusinessPartnerClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderSplitRecordClient;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsInfoClient;
import com.deepexi.dd.domain.transaction.service.impl.FinanceCreditServiceImpl;
import com.deepexi.dd.domain.transaction.service.impl.SaleOrderOperationRecordServiceImpl;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoRequestQuery;
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

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-29 14:34
 * @Description : 支付公共业务抽取类
 */
@Service
@Slf4j
public class SaleOrderInfoUtils {
    Logger logger = LoggerFactory.getLogger(SaleOrderInfoUtils.class);
    @Autowired
    BusinessPartnerClient businessPartnerClient;
    @Autowired
    SaleOrderInfoClient saleOrderInfoClient;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;
    @Autowired
    private SaleOrderOperationRecordServiceImpl saleOrderOperationRecordService;
    @Autowired
    private SaleOrderSplitRecordClient saleOrderSplitRecordClient;
    @Autowired
    SalePickGoodsInfoClient salePickGoodsInfoClient;
    private static final String SUCCESS = "0";

    /**
     * 获取伙伴信息
     *
     * @param tenantId
     * @param appId
     * @param orgId
     * @return
     * @throws Exception
     */
    public BusinessPartnerResponseDTO getBusinessPartner(String tenantId, Long appId, Long orgId) throws Exception {
        if (Objects.isNull(tenantId) || Objects.isNull(appId) || Objects.isNull(orgId)) {
            return null;
        }
        Payload<BusinessPartnerResponseDTO> businessPartnerResponseDTOPayload = null;
        try {
            BusinessPartnerRequestQuery businessPartnerRequestQuery = new BusinessPartnerRequestQuery();
            businessPartnerRequestQuery.setTenantId(tenantId);
            businessPartnerRequestQuery.setAppId(appId);
            businessPartnerRequestQuery.setOrgId(Long.valueOf(orgId));

            businessPartnerResponseDTOPayload = businessPartnerClient.getPartner(businessPartnerRequestQuery);
            logger.info("获取伙伴id:{}", JsonUtil.bean2JsonString(businessPartnerResponseDTOPayload));
            if (!businessPartnerResponseDTOPayload.getCode().equals("0")) {
                throw new ApplicationException("获取伙伴ID失败");
            }
        } catch (Exception e) {
            logger.error("获取伙伴ID失败:", e);
            throw new ApplicationException("获取伙伴ID失败");
        }
        BusinessPartnerResponseDTO businessPartner = GeneralConvertUtils.conv(businessPartnerResponseDTOPayload.getPayload(), BusinessPartnerResponseDTO.class);
        return businessPartner;
    }

    /**
     * 更新订单支付交易记录所用的订单编码
     *
     * @param saleOrderId
     * @param payOrderCode
     */
    public void updatePayOrderCode(Long saleOrderId, String payOrderCode) {
        logger.info("更新订单支付记录编码:{}", JsonUtil.bean2JsonString(saleOrderId + "{}\t" + payOrderCode));
        if (!Objects.isNull(saleOrderId) && !Objects.isNull(payOrderCode)) {
            SaleOrderInfoPayOrderCodeEditDTO payCodeEditDTO = new SaleOrderInfoPayOrderCodeEditDTO();
            payCodeEditDTO.setId(saleOrderId);
            payCodeEditDTO.setPayOrderCode(payOrderCode);
            saleOrderInfoClient.updatePayOrderCode(payCodeEditDTO);
        }
    }

    /**
     * 新增支付操作记录-成功
     */
    public void addPaySuccess(Long OrderId, Integer orderType, String remake) {
        if (!Objects.isNull(OrderId) && !Objects.isNull(orderType) && !Objects.isNull(remake)) {
            logger.info("更新订单支付记录类型:{}", JsonUtil.bean2JsonString("订单ID:" + OrderId + "{}\t" + "订单类型:" + orderType));
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setVersion(1);
            orderOperationRecordRequestDTO.setRemark("支付成功");
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(OrderId);
            orderOperationRecordRequestDTO.setOperationType(orderType);
            orderOperationRecordRequestDTO.setOperation("立即付款");
            saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
        }
    }

    /**
     * 新增支付操作记录-失败
     */
    public void addPayFailed(Long OrderId, Integer orderType, String remake) {
        if (!Objects.isNull(OrderId) && !Objects.isNull(orderType) && !Objects.isNull(remake)) {
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setVersion(1);
            orderOperationRecordRequestDTO.setRemark(remake);
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(OrderId);
            orderOperationRecordRequestDTO.setOperationType(orderType);
            orderOperationRecordRequestDTO.setOperation("立即付款");
            saleOrderOperationRecordService.add(orderOperationRecordRequestDTO);
        }
    }

    /**
     * 更新子订单支付编码
     *
     * @param type         支付类型 0、信用 1、余额 2、线下 3、线上
     * @param code         父订单编码
     * @param payOrderCode 子订单支付订单编码
     * @throws Exception
     */
    public void updateSaleOrderInfoCode(Integer type, String code, String payOrderCode) throws Exception {
        Payload<List<SaleOrderResponseDTO>> listPayload = saleOrderInfoClient.selectSaleOrderByParentCode(code);
        if (ObjectUtils.isEmpty(listPayload) || ObjectUtils.isEmpty(listPayload.getPayload())) {
            logger.info("更新子订单支付编码失败", code);
            return;
        }
        List<SaleOrderResponseDTO> list = GeneralConvertUtils.convert2List(listPayload.getPayload(), SaleOrderResponseDTO.class);
        //销售单更新支付记录所关联订单编号
        try {
            for (SaleOrderResponseDTO saleOrderResponseDTO : list) {
                if (type == 0) {//信用支付
                    this.updatePayOrderCode(saleOrderResponseDTO.getId(), payOrderCode);
                    this.addPaySuccess(saleOrderResponseDTO.getId(), 1, "信用支付成功");
                    logger.info("信用支付更新子订单支付编码:{}" + payOrderCode, JsonUtil.bean2JsonString(saleOrderResponseDTO));
                } else if (type == 1) {//余额支付
                    this.updatePayOrderCode(saleOrderResponseDTO.getId(), payOrderCode);
                    this.addPaySuccess(saleOrderResponseDTO.getId(), 1, "余额支付成功");
                    logger.info("余额支付更新子订单支付编码:{}" + payOrderCode, JsonUtil.bean2JsonString(saleOrderResponseDTO));
                } else if (type == 2) {//线下支付
                    this.updatePayOrderCode(saleOrderResponseDTO.getId(), payOrderCode);
                    this.addPaySuccess(saleOrderResponseDTO.getId(), 1, "线下支付成功");
                    logger.info("线下支付更新子订单支付编码:{}" + payOrderCode, JsonUtil.bean2JsonString(saleOrderResponseDTO));
                } else if (type == 3) {//线上支付
                    this.updatePayOrderCode(saleOrderResponseDTO.getId(), payOrderCode);
                    this.addPaySuccess(saleOrderResponseDTO.getId(), 1, "线上支付成功");
                    logger.info("线上支付更新子订单支付编码:{}" + payOrderCode, JsonUtil.bean2JsonString(saleOrderResponseDTO));
                }

            }
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * 更新订单支付类型
     *
     * @param OrderId     订单id
     * @param orderCode   订单编号
     * @param orderType   订单类型（普通/网批）
     * @param paymentType 支付类型
     */
    public void updatePaymentType(Long OrderId, String orderCode, Integer orderType, Integer paymentType) throws Exception {
        if (null != OrderId && null != orderCode && null != orderType && paymentType != null) {
            logger.info("更新订单支付记录类型:{}", JsonUtil.bean2JsonString(OrderId + "{}\t" + orderCode));
            if (orderType == 0) {//普通单
                SaleOrderInfoRequestQuery saleOrderInfoRequestQuery = new SaleOrderInfoRequestQuery();
                saleOrderInfoRequestQuery.setCode(orderCode);
                saleOrderInfoRequestQuery.setId(OrderId);
                Payload<SaleOrderResponseDTO> saleOrderInfos = saleOrderInfoClient.selectSaleOrder(saleOrderInfoRequestQuery);
                if (SUCCESS.equals(saleOrderInfos.getCode()) && saleOrderInfos.getPayload() != null) {//子单
                    SaleOrderInfoRequestDTO updateDto = new SaleOrderInfoRequestDTO();
                    updateDto.setId(OrderId);
                    updateDto.setPaymentType(paymentType);
                    updateDto.setUpdatedTime(new Date());
                    updateDto.setUpdatedBy(appRuntimeEnv.getUsername());
                    saleOrderInfoClient.updateMainOrderById(OrderId, updateDto);
                } else {//父单
                    SaleOrderSplitRecordRequestDTO updateDto = new SaleOrderSplitRecordRequestDTO();
                    updateDto.setId(OrderId);
                    updateDto.setPaymentType(paymentType);
                    updateDto.setUpdatedTime(new Date());
                    updateDto.setUpdatedBy(appRuntimeEnv.getUsername());
                    saleOrderSplitRecordClient.updateById(OrderId, updateDto);
                }
            } else if (orderType == 1) {//提货单
                SalePickGoodsInfoRequestDTO updateDto = new SalePickGoodsInfoRequestDTO();
                updateDto.setId(OrderId);
                updateDto.setPaymentType(paymentType);
                updateDto.setUpdatedTime(new Date());
                updateDto.setUpdatedBy(appRuntimeEnv.getUsername());
                salePickGoodsInfoClient.updateById(OrderId, updateDto);
            }
        }
    }

}
