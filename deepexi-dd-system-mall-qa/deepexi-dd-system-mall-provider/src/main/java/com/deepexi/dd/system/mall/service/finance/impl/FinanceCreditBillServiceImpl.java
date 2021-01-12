package com.deepexi.dd.system.mall.service.finance.impl;

import com.deepexi.dd.domain.finance.api.FinanceCreditBillApi;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillDetailResponseDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillRelationResponseDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditRepaymentBillRequestDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRepaymentBillRelationRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRepaymentBillRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRequestQuery;
import com.deepexi.dd.system.mall.remote.finance.FinanceCreditBillClient;
import com.deepexi.dd.system.mall.service.finance.FinanceCreditBillService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author easy
 * @date 2020/9/10 10:12
 **/
@Service
public class FinanceCreditBillServiceImpl implements FinanceCreditBillService {

    @Autowired
    private FinanceCreditBillClient financeCreditBillClient;

    @Override
    public Payload<PageBean<FinanceCreditRepaymentBillResponseDTO>> listFinanceCreditRepaymentBillsPage(FinanceCreditRepaymentBillRequestQuery query) throws Exception {
        return financeCreditBillClient.listFinanceCreditRepaymentBillsPage(query);
    }

    @Override
    public Payload<FinanceCreditRepaymentBillResponseDTO> insert(FinanceCreditRepaymentBillRequestDTO record) throws Exception {
        return financeCreditBillClient.insert(record);
    }

    @Override
    public Payload<FinanceCreditRepaymentBillDetailResponseDTO> lookCreditBill(FinanceCreditRepaymentBillRequestQuery query) throws Exception {
        return financeCreditBillClient.lookCreditBill(query);
    }

    @Override
    public Payload<PageBean<FinanceCreditRepaymentBillRelationResponseDTO>> listFinanceCreditRepaymentBillRelationsPage(FinanceCreditRepaymentBillRelationRequestQuery query) throws Exception {
        return financeCreditBillClient.listFinanceCreditRepaymentBillRelationsPage(query);
    }

    @Override
    public Payload<String> repaymentButton(FinanceCreditRequestQuery query) throws Exception {
        return financeCreditBillClient.repaymentButton(query);
    }
}
