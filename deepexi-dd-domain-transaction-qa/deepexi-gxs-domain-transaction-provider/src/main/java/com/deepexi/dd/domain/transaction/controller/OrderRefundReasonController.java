package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.transaction.service.OrderRefundReasonService;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author easy
 * @date 2020/9/15 14:53
 **/
@RestController
@Api(tags = "退款单管理")
@RequestMapping("/admin-api/v1/domain/transaction/orderRefundReason")
public class OrderRefundReasonController {

    @Autowired
    private OrderRefundReasonService orderRefundReasonService;

    /**
     * 根据来源单的code查询退款单的退款支付记录
     *
     * @param query
     * @return
     * @throws Exception
     */
    @GetMapping("/findOriginOrderRefundPaymentRecord")
    @ApiOperation("根据来源单的code查询退款单的退款支付记录")
    Payload<FinancePaymentRecordsResponseDTO> findOriginOrderRefundPaymentRecord(FinancePaymentRecordsRequestQuery query) throws Exception {
        return new Payload<>(orderRefundReasonService.findOriginOrderRefundPaymentRecord(query));
    }

}
