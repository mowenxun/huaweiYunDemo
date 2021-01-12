package com.deepexi.dd.system.mall.controller.finance;

import com.deepexi.dd.domain.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.system.mall.domain.query.FinancePaymentRecordQuery;
import com.deepexi.dd.system.mall.service.finance.FinancePaymentRecordsService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName FinancePaymentRecordsController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-08
 * @Version 1.0
 **/
@Api(value = "支付记录", tags = "支付记录")
@RequestMapping("/admin-api/v1/domain/customer/financePaymentRecords")
@RestController
public class FinancePaymentRecordsController {

    @Autowired
    private FinancePaymentRecordsService paymentRecordsService;

    @ApiOperation("分页查询支付记录")
    @GetMapping("/page")
    public Payload<PageBean<FinancePaymentRecordsResponseDTO>> listFinancePaymentRecordsPage(FinancePaymentRecordsRequestQuery requestQuery) throws Exception {
        return paymentRecordsService.listFinancePaymentRecordsPage(requestQuery);
    }

    @ApiOperation("订单ID查询支付流水记录")
    @GetMapping("/list/recodes")
    public Payload<List<FinancePaymentRecordsResponseDTO>> listFinancePaymentRecordsByOrderIds(FinancePaymentRecordQuery query)  throws Exception {
        return paymentRecordsService.listFinancePaymentRecordsByOrderIds(query.getType(),query.getIds());
    }

    @ApiOperation("订单编码查询支付流水分页记录")
    @GetMapping("/byOrderCode")
    public Payload<PageBean<FinancePaymentRecordsResponseDTO>> byOrderCode(FinancePaymentRecordQuery query)  throws Exception {
        return new Payload<>(paymentRecordsService.byOrderCode(query));
    }
}
