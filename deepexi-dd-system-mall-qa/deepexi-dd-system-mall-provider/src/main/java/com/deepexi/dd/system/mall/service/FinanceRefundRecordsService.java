package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.finance.domain.dto.FinanceRefundRecordsAdminResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinanceAuditAdminRequestQuery;
import com.deepexi.dd.domain.finance.domain.query.FinanceRefundRecordsAdminRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @Title: FinanceRefundRecordsApiImpl
 * @Description: TODO
 * @Author: hezhijian
 * @Datetime: 2020/9/17 17:17
 * @version: v1.0
 */
public interface FinanceRefundRecordsService {

    /**
     * 查询退款记录表
     *
     * @param query
     * @return
     */
    List<FinanceRefundRecordsAdminResponseDTO> listFinanceRefundRecords(FinanceRefundRecordsAdminRequestQuery query) throws Exception;

    /**
     * 分页查询退款记录表
     *
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<FinanceRefundRecordsAdminResponseDTO> listFinanceRefundRecordsPage(FinanceRefundRecordsAdminRequestQuery query) throws Exception;

    /**
     * 审核退款单
     *
     * @param query
     * @return
     * @throws Exception
     */
    Boolean financeAuditRefundRecords(FinanceAuditAdminRequestQuery query) throws Exception;
}
