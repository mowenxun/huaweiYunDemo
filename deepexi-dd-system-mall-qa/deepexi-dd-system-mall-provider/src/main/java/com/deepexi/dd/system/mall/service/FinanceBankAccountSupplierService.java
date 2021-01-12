package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.finance.domain.dto.FinanceBankAccountSupplierDomainRequestDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceBankAccountSupplierDomainResponseDTO;
import com.deepexi.dd.system.mall.domain.query.FinanceBankAccountSupplierRequestQuery;

/**
 * @author easy
 * @date 2020/8/28 16:27
 **/
public interface FinanceBankAccountSupplierService {

    /**
     * 查询当前登录供应商的资金账户
     *
     * @param query
     * @return
     * @throws Exception
     */
    FinanceBankAccountSupplierDomainResponseDTO selectMyFinanceAccount(FinanceBankAccountSupplierRequestQuery query) throws Exception;

    /**
     * 新增供应商-银行账户表
     *
     * @param record
     * @return
     */
    FinanceBankAccountSupplierDomainResponseDTO insert(FinanceBankAccountSupplierDomainRequestDTO record) throws Exception;

    /**
     * 根据ID修改供应商-银行账户表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, FinanceBankAccountSupplierDomainRequestDTO record) throws Exception;
}
