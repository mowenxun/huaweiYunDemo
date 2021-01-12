package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.domain.finance.domain.dto.FinanceRefundRecordsAdminResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinanceAuditAdminRequestQuery;
import com.deepexi.dd.domain.finance.domain.query.FinanceRefundRecordsAdminRequestQuery;
import com.deepexi.dd.system.mall.remote.order.FinanceRefundRecordsRemote;
import com.deepexi.dd.system.mall.service.FinanceRefundRecordsService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: FinanceRefundRecordsServiceImpl
 * @Description: TODO
 * @Author: hezhijian
 * @Datetime: 2020/9/17 17:29
 * @version: v1.0
 */
@Service
@Slf4j
public class FinanceRefundRecordsServiceImpl implements FinanceRefundRecordsService {

    @Autowired
    private FinanceRefundRecordsRemote financeRefundRecordsRemote;

    @Override
    public List<FinanceRefundRecordsAdminResponseDTO> listFinanceRefundRecords(FinanceRefundRecordsAdminRequestQuery query) throws Exception{
        return GeneralConvertUtils.convert2List(financeRefundRecordsRemote.listFinanceRefundRecords(query).getPayload(),FinanceRefundRecordsAdminResponseDTO.class);
    }

    @Override
    public PageBean<FinanceRefundRecordsAdminResponseDTO> listFinanceRefundRecordsPage(FinanceRefundRecordsAdminRequestQuery query) throws Exception {
        return GeneralConvertUtils.convert2PageBean(financeRefundRecordsRemote.listFinanceRefundRecordsPage(query).getPayload(),FinanceRefundRecordsAdminResponseDTO.class);
    }

    @Override
    public Boolean financeAuditRefundRecords(FinanceAuditAdminRequestQuery query) throws Exception {
        return financeRefundRecordsRemote.financeAuditRefundRecords(query).getPayload();
    }
}
