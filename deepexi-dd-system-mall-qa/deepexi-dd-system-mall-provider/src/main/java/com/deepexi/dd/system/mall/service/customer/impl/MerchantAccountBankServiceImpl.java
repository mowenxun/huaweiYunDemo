package com.deepexi.dd.system.mall.service.customer.impl;

import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountRequestDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAccountBankCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAccountBankUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAccountBankResponseVO;
import com.deepexi.dd.system.mall.remote.finance.FinanceBankAccountRemote;
import com.deepexi.dd.system.mall.service.customer.BaseService;
import com.deepexi.dd.system.mall.service.customer.MerchantAccountBankService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import domain.query.MerchantAccountBankRequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MerchantAccountBankServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-15
 * @Version 1.0
 **/
@Service
public class MerchantAccountBankServiceImpl extends BaseService implements MerchantAccountBankService {

    @Autowired
    private FinanceBankAccountRemote accountRemote;

    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Override
    public List<MerchantAccountBankResponseVO> findAll(MerchantAccountBankRequestQuery query) throws Exception {
        initQuery(query);
        Payload<List<FinanceBankAccountResponseDTO>> payload = accountRemote.listFinanceBankAccounts(query.clone(FinanceBankAccountRequestQuery.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.convert2List(payload.getPayload(), MerchantAccountBankResponseVO.class);
    }

    private void initQuery(MerchantAccountBankRequestQuery query) throws Exception {
        query.setIsolationId(getIsolationId());
    }

    @Override
    public MerchantAccountBankResponseVO detail(Long id) throws Exception {
        Payload<FinanceBankAccountResponseDTO> payload = accountRemote.selectById(id);
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.conv(payload.getPayload(), MerchantAccountBankResponseVO.class);
    }

    @Override
    public Boolean update(MerchantAccountBankUpdateAdminRequestDTO dto) throws Exception {
        initModify(dto);
        Payload<Boolean> payload = accountRemote.updateById(dto.getId(), dto.clone(FinanceBankAccountRequestDTO.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return payload.getPayload();
    }

    @Override
    public MerchantAccountBankResponseVO create(MerchantAccountBankCreateAdminRequestDTO dto) throws Exception {
        initCreate(dto);
        dto.setCode(identifierGenerator.getIdentifier(IdentifierTypeEnum.FINANCE_BANK.getType(), IdentifierTypeEnum.FINANCE_BANK.getPrefix(), IdentifierTypeEnum.FINANCE_BANK.getLen()));
        Payload<FinanceBankAccountResponseDTO> payload = accountRemote.insert(dto.clone(FinanceBankAccountRequestDTO.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.conv(payload.getPayload(), MerchantAccountBankResponseVO.class);
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        Payload<Boolean> payload = accountRemote.deleteByIdIn(Arrays.asList(id));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return payload.getPayload();
    }

    @Override
    public PageBean<MerchantAccountBankResponseVO> findPage(FinanceBankAccountRequestQuery pageQuery) throws Exception {
        pageQuery.setIsolationId(getIsolationId());
        Payload<PageBean<FinanceBankAccountResponseDTO>> payload = accountRemote.listFinanceBankAccountsPage(pageQuery);
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.convert2PageBean(payload.getPayload(), MerchantAccountBankResponseVO.class);
    }
}
