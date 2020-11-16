package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.SalePickOrderPayRequestDTO;
import com.deepexi.dd.middle.finance.domain.dto.*;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-20 11:29
 * TODO 信用支付、余额支付管理
 */
@ApiOperation("根据提货单查询伙伴信用额度")
public interface FinanceCreditService {
    /**
     * @param saleOrderPayRequestDTO
     * @return 根据提货单查询伙伴信用额度
     */
    FinanceCreditResponseDTO queryCreditBySalePickOrderId(@Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;

    /**
     * @param dto
     * @return 账户扣减信用额度，生成信用明细
     */
    Boolean creditPayment(FinanceCreditPaymentRequestDTO dto) throws Exception;

    /**
     * @param saleOrderPayRequestDTO
     * @return 查询伙伴账户余额
     */
    FinanceAmountResponseDTO queryAmountBySalePickOrderId(@Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;

    /**
     * @param dto
     * @return
     * @throws Exception 扣减账户余额，生成余额明细
     */
    Boolean FinanceAmount(@RequestBody FinanceAmountPaymentRequestDTO dto) throws Exception;
}
