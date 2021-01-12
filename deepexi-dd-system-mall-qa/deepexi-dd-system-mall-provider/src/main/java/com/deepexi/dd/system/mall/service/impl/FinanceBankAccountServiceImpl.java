package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.system.mall.remote.finance.FinanceBankAccountRemote;
import com.deepexi.dd.system.mall.service.FinanceBankAccountService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountRequestDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @author admin
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class FinanceBankAccountServiceImpl implements FinanceBankAccountService {

    @Autowired
    private FinanceBankAccountRemote financeBankAccountRemote;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;

    @Override
    public List<FinanceBankAccountResponseDTO> listFinanceBankAccounts(FinanceBankAccountRequestQuery query) throws Exception {
        query.setIsolationId(String.valueOf(appRuntimeEnv.getTopOrganization().getId()));
        return GeneralConvertUtils.convert2List(financeBankAccountRemote.listFinanceBankAccounts(query).getPayload(), FinanceBankAccountResponseDTO.class);
    }

    @Override
    public PageBean<FinanceBankAccountResponseDTO> listFinanceBankAccountsPage(FinanceBankAccountRequestQuery query) throws Exception{
        return GeneralConvertUtils.convert2PageBean(financeBankAccountRemote.listFinanceBankAccountsPage(query).getPayload(), FinanceBankAccountResponseDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) throws Exception {
        return financeBankAccountRemote.deleteByIdIn(id).getPayload();
    }

    @Override
    public FinanceBankAccountResponseDTO insert(FinanceBankAccountRequestDTO record) throws Exception {
        return GeneralConvertUtils.conv(financeBankAccountRemote.insert(record).getPayload(),FinanceBankAccountResponseDTO.class);
    }

    @Override
    public FinanceBankAccountResponseDTO selectById(Long id) throws Exception {
        return GeneralConvertUtils.conv(financeBankAccountRemote.selectById(id).getPayload(), FinanceBankAccountResponseDTO.class);
    }

    @Override
    public Boolean updateById(Long id, FinanceBankAccountRequestDTO record) throws Exception {
        return financeBankAccountRemote.updateById(id, record).getPayload();
    }


}