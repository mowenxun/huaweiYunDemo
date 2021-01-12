package com.deepexi.dd.system.mall.service.finance;

import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillDetailResponseDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillRelationResponseDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditRepaymentBillRequestDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRepaymentBillRelationRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRepaymentBillRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

/**
 * @author easy
 * @date 2020/9/10 10:10
 **/
public interface FinanceCreditBillService {

    /**
     * 分页查询信用账单列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Payload<PageBean<FinanceCreditRepaymentBillResponseDTO>> listFinanceCreditRepaymentBillsPage(FinanceCreditRepaymentBillRequestQuery query) throws Exception;

    /**
     * 新增信用账单、生成信用账单与支付记录关联关系、更新支付记录还款状态为待确认
     *
     * @param record
     * @return
     * @throws Exception
     */
    Payload<FinanceCreditRepaymentBillResponseDTO> insert(FinanceCreditRepaymentBillRequestDTO record) throws Exception;

    /**
     * 查看信用账单详情
     *
     * @param query
     * @return
     * @throws Exception
     */
    Payload<FinanceCreditRepaymentBillDetailResponseDTO> lookCreditBill(FinanceCreditRepaymentBillRequestQuery query) throws Exception;

    /**
     * 分页查询信用账单与支付记录关联关系列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Payload<PageBean<FinanceCreditRepaymentBillRelationResponseDTO>> listFinanceCreditRepaymentBillRelationsPage(FinanceCreditRepaymentBillRelationRequestQuery query) throws Exception;

    /**
     * 还款按钮查询信用账单还款状态
     *
     * @param query
     * @return
     * @throws Exception
     */
    Payload<String> repaymentButton(FinanceCreditRequestQuery query) throws Exception;

}
