package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceAmountPaymentReqDTO;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceAmountResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceCreditPaymentRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceCreditResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.SalePickOrderPayRequestDTO;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-21 20:03
 */
@Api(value = "资金API")
@RequestMapping("/financeCredit")
public interface FinanceCreditApi {
    @ApiOperation("查询账户信用额度")
    @PostMapping("/findFinanceCredit")
    Payload<FinanceCreditResponseDTO> queryCreditBySalePickOrderId(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;

    @ApiOperation("扣减账户信用额度，生成信用明细")
    @PostMapping("/deductionFinanceCredit")
    Payload<Boolean> creditPayment(@RequestBody FinanceCreditPaymentRequestDTO dto) throws Exception;

    @ApiOperation("扣减账户余额，生成余额明细")
    @PostMapping("/deductionFinanceAmount")
    Payload<Boolean> financeAmount(@RequestBody FinanceAmountPaymentReqDTO dto) throws Exception;

    @ApiOperation("查询账户余额")
    @PostMapping("/findFinanceAmount")
    Payload<FinanceAmountResponseDTO> queryAmountBySalePickOrderId(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;


}
