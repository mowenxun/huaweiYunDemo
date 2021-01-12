package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountRequestDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 *
 * @author admin
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
public interface FinanceBankAccountService {


    /**
     * 查询银行账户表列表
     *
     * @return
     */
    List<FinanceBankAccountResponseDTO> listFinanceBankAccounts(FinanceBankAccountRequestQuery query) throws Exception;

    /**
     * 分页查询银行账户表列表
     *
     * @return
     */
    PageBean<FinanceBankAccountResponseDTO> listFinanceBankAccountsPage(FinanceBankAccountRequestQuery query) throws Exception;

    /**
     * 根据ID删除银行账户表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id) throws Exception;

    /**
     * 新增银行账户表
     *
     * @param record
     * @return
     */
    FinanceBankAccountResponseDTO insert(FinanceBankAccountRequestDTO record) throws Exception;

    /**
     * 查询银行账户表详情
     *
     * @param id
     * @return
     */
    FinanceBankAccountResponseDTO selectById(Long id) throws Exception;


    /**
     * 根据ID修改银行账户表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, FinanceBankAccountRequestDTO record) throws Exception;

}