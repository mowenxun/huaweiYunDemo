package com.deepexi.dd.system.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.finance.domain.dto.FinanceBankAccountSupplierDomainRequestDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceBankAccountSupplierDomainResponseDTO;
import com.deepexi.dd.system.mall.domain.query.FinanceBankAccountSupplierRequestQuery;
import com.deepexi.dd.system.mall.remote.customer.CompanyInfoClient;
import com.deepexi.dd.system.mall.remote.finance.FinanceBankAccountSupplierRemote;
import com.deepexi.dd.system.mall.service.FinanceBankAccountSupplierService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.CompanyInfoResponseApiDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author easy
 * @date 2020/8/28 16:30
 **/
@Service
@Slf4j
public class FinanceBankAccountSupplierServiceImpl implements FinanceBankAccountSupplierService {


    private static final String SUCCESS = "0";

    @Autowired
    private FinanceBankAccountSupplierRemote financeBankAccountSupplierRemote;
    @Autowired
    private CompanyInfoClient companyInfoClient;

    /**
     * 查询当前登录供应商的资金账户
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public FinanceBankAccountSupplierDomainResponseDTO selectMyFinanceAccount(FinanceBankAccountSupplierRequestQuery query) throws Exception {
        // 如果数据隔离ID（供应商组织id）为空，则用供应商id去查询供应商组织id
        if (StringUtil.isEmpty(query.getIsolationId())) {
            Payload<CompanyInfoResponseApiDTO> payload = companyInfoClient.selectById(query.getSupplierId());
            if (null != payload) {
                if (null != payload.getPayload()) {
                    CompanyInfoResponseApiDTO companyInfoResponseApiDTO = GeneralConvertUtils.conv(payload.getPayload(),CompanyInfoResponseApiDTO.class);
                    if (null != companyInfoResponseApiDTO.getCompanyId()) {
                        query.setIsolationId(companyInfoResponseApiDTO.getCompanyId().toString());
                    }
                }
            }
        }
        return GeneralConvertUtils.conv(financeBankAccountSupplierRemote.selectByIsolationId(query.getIsolationId()).getPayload(), FinanceBankAccountSupplierDomainResponseDTO.class);
    }

    /**
     * 新增供应商-银行账户表
     *
     * @param record
     * @return
     */
    @Override
    public FinanceBankAccountSupplierDomainResponseDTO insert(FinanceBankAccountSupplierDomainRequestDTO record) throws Exception {
        log.info("FinanceBankAccountSupplierDomainRequestDTO:{}", JSON.toJSONString(record));
        FinanceBankAccountSupplierDomainResponseDTO domainResponseDTO = GeneralConvertUtils.conv(financeBankAccountSupplierRemote.selectByIsolationId(record.getIsolationId()).getPayload(), FinanceBankAccountSupplierDomainResponseDTO.class);
        if (null != domainResponseDTO) {
            log.error("已有银行账户，请勿重复添加！");
            throw new ApplicationException("已有银行账户，请勿重复添加！");
        }
        Payload<FinanceBankAccountSupplierDomainResponseDTO> payload = financeBankAccountSupplierRemote.insert(record);
        if (SUCCESS.equals(payload.getCode())) {
            try {
                return GeneralConvertUtils.conv(payload.getPayload(), FinanceBankAccountSupplierDomainResponseDTO.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
        return null;
    }

    /**
     * 根据ID修改供应商-银行账户表
     *
     * @param id
     * @param record
     * @return
     */
    @Override
    public Boolean updateById(Long id, FinanceBankAccountSupplierDomainRequestDTO record) throws Exception {
        log.info("FinanceBankAccountSupplierDomainRequestDTO:{}", JSON.toJSONString(record));
        Payload<Boolean> result = financeBankAccountSupplierRemote.updateById(id, record);
        if (SUCCESS.equalsIgnoreCase(result.getCode())) {
            try {
                return result.getPayload();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new ApplicationException(result.getMsg());
        }
        return false;
    }
}
