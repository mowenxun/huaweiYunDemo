package com.deepexi.dd.system.mall.service.customer;

import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAccountBankCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAccountBankUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAccountBankResponseVO;
import com.deepexi.util.pageHelper.PageBean;
import domain.query.MerchantAccountBankRequestQuery;

import java.util.List;

/**
 * @ClassName MerchantAccountBankService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-15
 * @Version 1.0
 **/
public interface MerchantAccountBankService {

    List<MerchantAccountBankResponseVO> findAll(MerchantAccountBankRequestQuery query) throws Exception;

    MerchantAccountBankResponseVO detail(Long id) throws Exception;

    Boolean update(MerchantAccountBankUpdateAdminRequestDTO dto) throws Exception;

    MerchantAccountBankResponseVO create(MerchantAccountBankCreateAdminRequestDTO dto) throws Exception;

    Boolean delete(Long id) throws Exception;

    PageBean<MerchantAccountBankResponseVO> findPage(FinanceBankAccountRequestQuery pageQuery) throws Exception;
}
