package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordRequestQuery;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * OrderOperationRecordApi
 *
 * @author zhaochongsen
 * @version 1.0
 * @date 2020-08-30 17:12
 */
@Api(value = "退货原因管理")
@RequestMapping("/open-api/v1/domain/middle/orderOperationRecord")
public interface OrderOperationRecordApi {

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderOperationRecordResponseDTO> listOrderOperationRecords(OrderOperationRecordRequestQuery query) throws Exception;

}