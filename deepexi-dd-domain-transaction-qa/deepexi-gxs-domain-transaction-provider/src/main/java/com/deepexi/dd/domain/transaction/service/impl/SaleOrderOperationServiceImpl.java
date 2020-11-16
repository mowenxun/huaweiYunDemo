package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.OrderStatusOperationDTO;
import com.deepexi.dd.domain.transaction.remote.order.OrderStatusOperationClient;
import com.deepexi.dd.domain.transaction.service.SaleOrderOperationService;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationRequestQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-13 18:49
 */
@Service
@Slf4j
public class SaleOrderOperationServiceImpl implements SaleOrderOperationService {

    @Autowired
    OrderStatusOperationClient orderStatusOperationClient;

    @Override
    public List<OrderStatusOperationDTO> listOrderStatusOperations(List<Integer> statuses,String portal){

        OrderStatusOperationRequestQuery query = new OrderStatusOperationRequestQuery();
        query.setStatuses(statuses);
        query.setPortal(portal);
        return GeneralConvertUtils.convert2List(orderStatusOperationClient.listOrderStatusOperations(query),OrderStatusOperationDTO.class);
    }
}
