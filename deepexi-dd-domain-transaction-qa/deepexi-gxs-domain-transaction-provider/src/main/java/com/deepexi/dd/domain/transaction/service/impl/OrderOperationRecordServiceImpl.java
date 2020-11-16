package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.transaction.remote.order.OrderOperationRecordClient;
import com.deepexi.dd.domain.transaction.service.OrderOperationRecordService;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordRequestQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderOperationRecordServiceImpl implements OrderOperationRecordService {


    @Autowired
    private OrderOperationRecordClient orderOperationRecordClient;

    @Override
    public List<OrderOperationRecordResponseDTO> listOrderOperationRecords(OrderOperationRecordRequestQuery query) throws Exception {
        return orderOperationRecordClient.listOrderOperationRecords(query);
    }
}
