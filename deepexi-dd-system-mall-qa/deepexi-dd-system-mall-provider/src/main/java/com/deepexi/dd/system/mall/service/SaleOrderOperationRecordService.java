package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.config.Payload;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/09/11/18:01
 * @Description:
 */
public interface SaleOrderOperationRecordService {
    Payload<OrderOperationRecordResponseDTO> getOrderRejectOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO);
}
