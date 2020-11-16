package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/09/11/17:47
 * @Description:
 */
@Api(value = "销售单操作记录表")
@RequestMapping("/open-api/v1/domain/saleorder/operationrecord")
public interface SaleOrderOperationRecordDomainApi {

    @GetMapping("/getOrderRejectOperationRecord")
    Payload<OrderOperationRecordResponseDTO> getOrderRejectOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO);
}
