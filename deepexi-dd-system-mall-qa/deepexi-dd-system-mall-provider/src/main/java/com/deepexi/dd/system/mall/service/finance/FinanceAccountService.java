package com.deepexi.dd.system.mall.service.finance;

import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountDetailResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditDetailResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceAmountDetailRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceAmountRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditDetailRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author easy
 * @date 2020/8/28 16:27
 **/
public interface FinanceAccountService {

    /**
     * 分页查询余额明细
     *
     * @param clone
     * @return
     * @throws Exception
     */
    Payload<PageBean<FinanceAmountDetailResponseDTO>> listFinanceAmountDetailPage(FinanceAmountDetailRequestQuery clone) throws Exception;

    /**
     * 分页查询信用明细
     *
     * @param clone
     * @return
     * @throws Exception
     */
    Payload<PageBean<FinanceCreditDetailResponseDTO>> listFinanceCreditDetailPage(FinanceCreditDetailRequestQuery clone) throws Exception;

    /**
     * 获取当前登录用户的所有上级供应商名字和顶级组织ID
     *
     * @param partnerId
     * @return
     * @throws Exception
     */
    Payload<List<Map<String, String>>> listBusinessPartner(Long partnerId) throws Exception;

    /**
     * 查询我的资金账户
     *
     * @param query
     * @return
     * @throws Exception
     */
    Payload<FinanceAmountResponseDTO> selectMyFinanceAccount(FinanceAmountRequestQuery query) throws Exception;
}
