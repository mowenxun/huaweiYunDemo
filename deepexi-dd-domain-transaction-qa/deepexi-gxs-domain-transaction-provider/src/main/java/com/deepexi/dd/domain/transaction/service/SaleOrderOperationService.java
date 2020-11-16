package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.OrderStatusOperationDTO;

import java.util.List;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-13 18:43
 */
public interface SaleOrderOperationService {
    /**
     * 查询订单状态操作关联表
     *
     * @return
     */
    List<OrderStatusOperationDTO> listOrderStatusOperations(List<Integer> statuses,String portal);
}
