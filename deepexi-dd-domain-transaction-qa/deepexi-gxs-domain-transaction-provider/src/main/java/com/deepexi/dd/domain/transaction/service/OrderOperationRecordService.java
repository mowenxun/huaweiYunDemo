package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordRequestQuery;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonRequestQuery;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuRequestQuery;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * OrderOperationRecordService
 *
 * @author zhaochongsen
 * @version 1.0
 * @date 2020-08-30 17:27
 */
public interface OrderOperationRecordService {

    /**
     * 查询列表
     *
     * @return
     */
    List<OrderOperationRecordResponseDTO> listOrderOperationRecords(OrderOperationRecordRequestQuery query) throws Exception;

}