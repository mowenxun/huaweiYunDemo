package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.FinanceCreditApi;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceAmountResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceAmountPaymentReqDTO;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceCreditPaymentRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.finance.FinanceCreditResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.SalePickOrderPayRequestDTO;
import com.deepexi.dd.domain.transaction.service.FinanceCreditService;
import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountPaymentRequestDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-21 20:20
 */
@RestController
public class FinanceCreditApiImpl implements FinanceCreditApi {
    @Autowired
    private FinanceCreditService financeCreditService;

    @Override
    public Payload<FinanceCreditResponseDTO> queryCreditBySalePickOrderId(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        com.deepexi.dd.middle.finance.domain.dto.FinanceCreditResponseDTO response = financeCreditService.queryCreditBySalePickOrderId(
                saleOrderPayRequestDTO.clone(com.deepexi.dd.domain.transaction.domain.dto.SalePickOrderPayRequestDTO.class));
        if (response == null) {
            return new Payload<>();
        }
        return new Payload<>(response.clone(FinanceCreditResponseDTO.class));
    }

    @Override
    public Payload<Boolean> creditPayment(@RequestBody FinanceCreditPaymentRequestDTO dto) throws Exception {
        return new Payload<>(financeCreditService.creditPayment(dto.clone(com.deepexi.dd.middle.finance.domain.dto.FinanceCreditPaymentRequestDTO.class)));
    }

    @Override
    public Payload<Boolean> financeAmount(@RequestBody FinanceAmountPaymentReqDTO dto) throws Exception {
        return new Payload<>(financeCreditService.FinanceAmount(GeneralConvertUtils.conv(dto, FinanceAmountPaymentRequestDTO.class)));
    }

    @Override
    public Payload<FinanceAmountResponseDTO> queryAmountBySalePickOrderId(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        com.deepexi.dd.middle.finance.domain.dto.FinanceAmountResponseDTO responseDTO = financeCreditService.queryAmountBySalePickOrderId(
                saleOrderPayRequestDTO.clone(com.deepexi.dd.domain.transaction.domain.dto.SalePickOrderPayRequestDTO.class));
        if (responseDTO == null) {
            return new Payload<>();
        }

        return new Payload<>(responseDTO.
                                                clone(FinanceAmountResponseDTO.class));
    }


}
