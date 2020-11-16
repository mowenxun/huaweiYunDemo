package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.OrderRejectOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @author aofeng
 * @version 1.0
 * @date 2020-08-18 19:52
 */
public interface SaleOrderOperationRecordService {
    /**
     * 根据订单Id查询操作记录
     * @param orderOperationRecordRequestDTO
     * @return
     */
    PageBean<OrderOperationRecordResponseDTO> getOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO);

    OrderOperationRecordResponseDTO getOrderRejectOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO);
    /**
     * 查询操作记录集合
     *
     * @param orderOperationRecordRequestDTO
     * @return
     */
    List<OrderOperationRecordResponseDTO> listOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO);

    /**
     * 新增操作记录
     * @param orderOperationRecordRequestDTO
     * @return
     */
    Payload add(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO);

}
