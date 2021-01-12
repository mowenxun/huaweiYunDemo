package com.deepexi.dd.system.mall.service.finance.impl;

import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountDetailResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditDetailResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceAmountDetailRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceAmountRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditDetailRequestQuery;
import com.deepexi.dd.system.mall.remote.finance.FinanceAccountClient;
import com.deepexi.dd.system.mall.service.finance.FinanceAccountService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author easy
 * @date 2020/8/28 16:30
 **/
@Service
public class FinanceAccountServiceImpl implements FinanceAccountService {

    @Autowired
    private FinanceAccountClient financeAccountClient;


    @Override
    public Payload<PageBean<FinanceAmountDetailResponseDTO>> listFinanceAmountDetailPage(FinanceAmountDetailRequestQuery clone) throws Exception {
        return financeAccountClient.listFinanceAmountDetailPage(clone);
    }

    @Override
    public Payload<PageBean<FinanceCreditDetailResponseDTO>> listFinanceCreditDetailPage(FinanceCreditDetailRequestQuery clone) throws Exception {
        return financeAccountClient.listFinanceCreditDetailPage(clone);
    }

    @Override
    public Payload<List<Map<String, String>>> listBusinessPartner(Long partnerId) throws Exception {
        return financeAccountClient.listBusinessPartner(partnerId);
    }

    @Override
    public Payload<FinanceAmountResponseDTO> selectMyFinanceAccount(FinanceAmountRequestQuery query) throws Exception {
        return financeAccountClient.selectMyFinanceAccount(query);
    }
}
