package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.OrderOperationRecordApi;
import com.deepexi.dd.domain.transaction.api.OrderRefundReasonApi;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.RefundReasonRequestQuery;
import com.deepexi.dd.domain.transaction.service.OrderOperationRecordService;
import com.deepexi.dd.domain.transaction.service.OrderRefundReasonService;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordRequestQuery;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderOperationRecordApiImpl implements OrderOperationRecordApi {

    @Autowired
    private OrderOperationRecordService orderOperationRecordService;

    @Override
    public List<OrderOperationRecordResponseDTO> listOrderOperationRecords(OrderOperationRecordRequestQuery query) throws Exception {
        return orderOperationRecordService.listOrderOperationRecords(query);
    }
}
