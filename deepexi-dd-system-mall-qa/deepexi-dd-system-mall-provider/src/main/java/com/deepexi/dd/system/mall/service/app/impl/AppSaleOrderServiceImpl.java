package com.deepexi.dd.system.mall.service.app.impl;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.tool.domain.dto.ToolStatusAllResponseDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusAllRequestQuery;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoReceiptRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.AppSaleOrderInfoReceiptResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.AppSaleOrderResponseDTO;
import com.deepexi.dd.system.mall.remote.order.AppSaleOrderInfoRemote;
import com.deepexi.dd.system.mall.remote.order.SaleOutTaskRemote;
import com.deepexi.dd.system.mall.remote.tool.ToolStatusClient;
import com.deepexi.dd.system.mall.service.app.AppSaleOrderService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: mumu
 * @Datetime: 2020/9/1   19:38
 * @version: v1.0
 */
@Service
@Slf4j
public class AppSaleOrderServiceImpl implements AppSaleOrderService {
    private static final String SUCCESS = "0";

    @Autowired
    private AppSaleOrderInfoRemote appSaleOrderInfoRemote;

    @Autowired
    SaleOutTaskRemote saleOutTaskRemote;

    @Autowired
    private ToolStatusClient toolStatusClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean<AppSaleOrderResponseDTO> listSaleOrderInfosPage(SaleOrderInfoRequestQuery query) throws Exception {
        PageBean<AppSaleOrderResponseDTO> result = GeneralConvertUtils.convert2PageBean(
                appSaleOrderInfoRemote.listSaleOrderInfosPage(query).getPayload(), AppSaleOrderResponseDTO.class);
        if (CollectionUtils.isNotEmpty(result.getContent())) {//查询出订单关联的多个出库单id 供app调用 SongTao 2020/08/10
            List<Long> list =
                    result.getContent().stream().map(AppSaleOrderResponseDTO::getId).collect(Collectors.toList());
            //订单id集合
            SaleOutTaskRequestQuery saleOutTaskRequestQuery = new SaleOutTaskRequestQuery();
            saleOutTaskRequestQuery.setSaleOrderIdList(list);
            List<SaleOutTaskDetailResponseDTO> saleOutTaskDetailResponseDTOS =
                    saleOutTaskRemote.listSaleOutTasksForApp(saleOutTaskRequestQuery);
            List<SaleOutTaskDetailResponseDTO> saleOutTaskList = GeneralConvertUtils.convert2List(saleOutTaskDetailResponseDTOS, SaleOutTaskDetailResponseDTO.class);
            Map<Long, List<SaleOutTaskDetailResponseDTO>> map
                    =
                    saleOutTaskList.stream().collect(Collectors.groupingBy(SaleOutTaskDetailResponseDTO::getSaleOrderId));
            result.getContent().forEach(f -> {
                f.setSaleOutTaskList(map.get(f.getId()));
            });
        }
        return result;
    }

    @Override
    public Boolean closeSaleOrder(SaleOrderCloseRequestDTO saleOrderCloseRequestDTO) throws Exception {
        Payload<Boolean> payload = appSaleOrderInfoRemote.closeSaleOrder(saleOrderCloseRequestDTO);
        if (SUCCESS.equals(payload.getCode())) {
            return payload.getPayload();
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public Boolean cancelSaleOrder(SaleOrderCancelRequestDTO saleOrderCancelRequestDTO) throws Exception {
        Payload<Boolean> payload = appSaleOrderInfoRemote.cancelSaleOrder(saleOrderCancelRequestDTO);
        if (SUCCESS.equals(payload.getCode())) {
            return payload.getPayload();
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public Boolean orderReceiving(SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception {
        Payload<Boolean> payload = appSaleOrderInfoRemote.orderReceiving(saleOrderReceivingRequestDTO);
        if (SUCCESS.equals(payload.getCode())) {
            return payload.getPayload();
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public Boolean deliveryGoodsOrder(SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception {
        Payload<Boolean> payload = appSaleOrderInfoRemote.deliveryGoodsOrder(saleOrderInfoDeliverGoodsRequestDTO);
        if (SUCCESS.equals(payload.getCode())) {
            return payload.getPayload();
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public PageBean<AppSaleOrderInfoReceiptResponseDTO> listSaleOrderInfosPageForReceipt(SaleOrderInfoReceiptRequestQuery query) throws Exception {
        Payload<PageBean<SaleOrderInfoReceiptResponseDTO>> payload = appSaleOrderInfoRemote.listSaleOrderInfosPageForReceipt(query);
        PageBean<AppSaleOrderInfoReceiptResponseDTO> result = new PageBean<>(Collections.emptyList());
        if (SUCCESS.equals(payload.getCode())) {
            result = GeneralConvertUtils.convert2PageBean(payload.getPayload(), AppSaleOrderInfoReceiptResponseDTO.class);
        } else {
            throw new ApplicationException(payload.getMsg());
        }
        //查询字典表
        try {
            ToolStatusAllRequestQuery statusQuery = new ToolStatusAllRequestQuery();
            statusQuery.setTenantId(appRuntimeEnv.getTenantId());
            statusQuery.setAppId(appRuntimeEnv.getAppId());
            Payload<List<ToolStatusAllResponseDTO>> listPayload = toolStatusClient.listAll(statusQuery);
            List<ToolStatusAllResponseDTO> toolStatusAllResponseList =
                    GeneralConvertUtils.convert2List(listPayload.getPayload(), ToolStatusAllResponseDTO.class);
            //k->status code v->statusName
            Map<Integer, String> toolStatusMap = toolStatusAllResponseList.stream().collect(Collectors.toMap(ToolStatusAllResponseDTO::getStatusCode,
                    ToolStatusAllResponseDTO::getStatusName,(v1,v2)->v1));
            for (AppSaleOrderInfoReceiptResponseDTO dto : result.getContent()) {
                if (toolStatusMap.containsKey(dto.getPaymentStatus())){
                    dto.setPaymentStatusName(toolStatusMap.get(dto.getPaymentStatus()));
                }
            }
        } catch (Exception e) {
            log.error("查询工具域状态报错了！", e);
        }
        return result;
    }

    @Override
    public SaleOrderInfoResponseDTO selectById(Long id) throws Exception {
        Payload<SaleOrderInfoResponseDTO> payload = appSaleOrderInfoRemote.selectById(id);
        if (SUCCESS.equals(payload.getCode())) {
            return GeneralConvertUtils.conv(payload.getPayload(), SaleOrderInfoResponseDTO.class);
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }

    @Override
    public PageBean<OrderOperationRecordResponseDTO> getOrderOperationRecord(OrderOperationRecordRequestDTO dto) throws Exception {
        Payload<PageBean<OrderOperationRecordResponseDTO>> orderOperationRecord = appSaleOrderInfoRemote.getOrderOperationRecord(dto);
        if (SUCCESS.equals(orderOperationRecord.getCode())) {
            if (orderOperationRecord.getPayload() == null) {
                return new PageBean<>(Collections.emptyList());
            } else {
                return GeneralConvertUtils.convert2PageBean(orderOperationRecord.getPayload(), OrderOperationRecordResponseDTO.class);
            }
        } else {
            throw new ApplicationException(orderOperationRecord.getMsg());
        }
    }
}
