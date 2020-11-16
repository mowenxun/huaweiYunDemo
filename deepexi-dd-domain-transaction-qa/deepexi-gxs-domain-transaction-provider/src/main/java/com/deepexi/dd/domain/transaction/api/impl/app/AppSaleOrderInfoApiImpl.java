package com.deepexi.dd.domain.transaction.api.impl.app;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.AppSaleOrderInfoApi;
import com.deepexi.dd.domain.transaction.api.impl.SaleOrderInfoApiImpl;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoReceiptRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.domain.transaction.export.SaleOrderExportService;
import com.deepexi.dd.domain.transaction.remote.order.BusinessPartnerClient;
import com.deepexi.dd.domain.transaction.service.*;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: mumu
 * @Datetime: 2020/9/1   16:11
 * @version: v1.0
 */
@RestController
public class AppSaleOrderInfoApiImpl implements AppSaleOrderInfoApi
{
    Logger logger = LoggerFactory.getLogger(SaleOrderInfoApiImpl.class);

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    @Autowired
    SaleOrderOperationService saleOrderOperationService;
    @Autowired
    private IamUserService iamUserService;
    @Autowired
    private SaleOrderExportService saleOrderExportService;
    //    @Autowired
//    private HttpServletResponse response;
    @Autowired
    private SaleOrderOperationRecordService saleOrderOperationRecordService;
    @Autowired
    private FinanceCreditService financeCreditService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Value("${appId}")
    private Long appId;

    @Autowired
    BusinessPartnerClient businessPartnerClient;

    @Override
    public Payload<PageBean<SaleOrderBtnResponseDTO>> listSaleOrderInfosPage(@RequestBody @Valid SaleOrderInfoRequestQuery query) throws Exception {
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        model.setListType("SalesOrder");
        PageBean<SaleOrderBtnResponseDTO> pageBean = GeneralConvertUtils.convert2PageBean(saleOrderInfoService.listSaleOrderInfosPage(model), SaleOrderBtnResponseDTO.class);
        //packOperationButtons(pageBean);
        return new Payload<>(pageBean);
    }

    @Override
    public Payload<Boolean> closeSaleOrder(@RequestBody @Valid SaleOrderCloseRequestDTO saleOrderCloseRequestDTO) {
        return new Payload<>(saleOrderInfoService.closeSaleOrder(saleOrderCloseRequestDTO));
    }

    @Override
    public Payload<Boolean> cancelSaleOrder(@RequestBody @Valid SaleOrderCancelRequestDTO saleOrderCancelRequestDTO) {
        return new Payload<>(saleOrderInfoService.cancelSaleOrder(saleOrderCancelRequestDTO));
    }

    @Override
    public Payload<Boolean> orderReceiving(@RequestBody @Valid SaleOrderReceivingRequestDTO saleOrderReceivingRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.orderReceiving(saleOrderReceivingRequestDTO));
    }

    @Override
    public Payload<Boolean> deliveryGoodsOrder(@RequestBody @Valid SaleOrderInfoDeliverGoodsRequestDTO saleOrderInfoDeliverGoodsRequestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.deliveryGoodsOrder(saleOrderInfoDeliverGoodsRequestDTO));
    }

    @Override
    public Payload<PageBean<SaleOrderInfoReceiptResponseDTO>> listSaleOrderInfosPageForReceipt(@Valid SaleOrderInfoReceiptRequestQuery query) throws Exception {
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        model.setListType("ListCollection");
        PageBean<SaleOrderBtnResponseDTO> saleOrderInfoResponseDTOPageBean = saleOrderInfoService.listSaleOrderInfosPage(model);
        PageBean<SaleOrderInfoReceiptResponseDTO> pageBean = GeneralConvertUtils.convert2PageBean(saleOrderInfoResponseDTOPageBean, SaleOrderInfoReceiptResponseDTO.class);

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
    public Payload<SaleOrderInfoResponseDTO> selectById(Long id) {
        return new Payload<>(GeneralConvertUtils.conv(saleOrderInfoService.selectById(id), SaleOrderInfoResponseDTO.class));
    }

    @Override
    public Payload<PageBean<OrderOperationRecordResponseDTO>> getOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        return new Payload<>(saleOrderOperationRecordService.getOrderOperationRecord(orderOperationRecordRequestDTO));
    }
}
