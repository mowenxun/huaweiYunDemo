package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.OrderRefundReasonApi;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonFindResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.RefundReasonRequestQuery;
import com.deepexi.dd.domain.transaction.service.OrderRefundReasonService;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderRefundReasonApiImpl implements OrderRefundReasonApi {

    @Autowired
    private OrderRefundReasonService orderRefundReasonService;

    @Override
    public Payload<List<RefundReasonResponseDTO>> listOrderRefundReasons(RefundReasonRequestQuery query) throws Exception {
        List<OrderRefundReasonResponseDTO> result = orderRefundReasonService.listOrderRefundReasons(query.clone(OrderRefundReasonRequestQuery.class));
        return new Payload<>(GeneralConvertUtils.convert2List(result, RefundReasonResponseDTO.class));
    }

    @Override
    public Payload<PageBean<RefundReasonResponseDTO>> listOrderRefundReasonsPage(RefundReasonRequestQuery query) throws Exception {
        PageBean<OrderRefundReasonResponseDTO> result = orderRefundReasonService.listOrderRefundReasonsPage(query.clone(OrderRefundReasonRequestQuery.class));
        return new Payload<>(GeneralConvertUtils.convert2PageBean(result, RefundReasonResponseDTO.class));
    }

    @Override
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) throws Exception {
        return new Payload<>(orderRefundReasonService.deleteByIdIn(id));
    }

    @Override
    public Payload<RefundReasonResponseDTO> insert(@RequestBody RefundReasonRequestDTO record) throws Exception {
        OrderRefundReasonResponseDTO result = orderRefundReasonService.insert(record.clone(OrderRefundReasonRequestDTO.class));
        return new Payload<>(GeneralConvertUtils.conv(result, RefundReasonResponseDTO.class));
    }

    @Override
    public Payload<RefundReasonResponseDTO> selectById(@PathVariable Long id) throws Exception {
        OrderRefundReasonResponseDTO result = orderRefundReasonService.selectById(id);
        return new Payload<>(GeneralConvertUtils.conv(result, RefundReasonResponseDTO.class));
    }

    @Override
    public Payload<Boolean> updateById(@PathVariable Long id, @RequestBody RefundReasonRequestDTO record) throws Exception {
        return new Payload<>(orderRefundReasonService.updateById(id, record.clone(OrderRefundReasonRequestDTO.class)));
    }

    @Override
    public Payload<Boolean> updateStatus(@RequestBody RefundReasonRequestDTO record) throws Exception {
        return new Payload<>(orderRefundReasonService.updateStatus(record.clone(OrderRefundReasonRequestDTO.class)));
    }

    @Override
    public Payload<Boolean> cancel(@PathVariable Long id) throws Exception {
        return new Payload<>(orderRefundReasonService.cancel(id));
    }

    @Override
    public Payload<List<RefundReasonFindResponseDTO>> findOrderRefundReason(@RequestBody List<String> orderCodes) throws Exception {
        return new Payload<>(GeneralConvertUtils.convert2List(orderRefundReasonService.findOrderRefundReason(orderCodes),RefundReasonFindResponseDTO.class));
    }

    @Override
    public Payload<FinancePaymentRecordsResponseDTO> findOriginOrderRefundPaymentRecord(FinancePaymentRecordsRequestQuery query) throws Exception {
        return new Payload<>(GeneralConvertUtils.conv(orderRefundReasonService.findOriginOrderRefundPaymentRecord(query), FinancePaymentRecordsResponseDTO.class));
    }
}
