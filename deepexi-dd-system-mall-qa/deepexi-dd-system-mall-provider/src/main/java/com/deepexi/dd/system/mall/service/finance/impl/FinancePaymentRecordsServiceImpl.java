package com.deepexi.dd.system.mall.service.finance.impl;

import com.deepexi.dd.domain.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.system.mall.domain.query.FinancePaymentRecordQuery;
import com.deepexi.dd.system.mall.remote.finance.FinancePaymentRecordsClient;
import com.deepexi.dd.system.mall.service.finance.FinancePaymentRecordsService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.dd.system.mall.util.PayloadUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FinancePaymentRecordsServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-08
 * @Version 1.0
 **/
@Service
public class FinancePaymentRecordsServiceImpl implements FinancePaymentRecordsService {

    @Autowired
    private FinancePaymentRecordsClient recordsClient;

    /**
     * @Description: 分页查询支付记录
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/8
     */
    public Payload<PageBean<FinancePaymentRecordsResponseDTO>> listFinancePaymentRecordsPage(FinancePaymentRecordsRequestQuery requestQuery) throws Exception {
        PageBean<FinancePaymentRecordsResponseDTO> result = GeneralConvertUtils.convert2PageBean(
                recordsClient.listFinancePaymentRecordsPage(requestQuery).getPayload(), FinancePaymentRecordsResponseDTO.class);
        result.getContent().forEach(r -> {
            if (r.getPayChannel() != null && r.getPayChannel() == 1) {
                r.setPayChannelName("微信");
            } else if (r.getPayChannel() != null && r.getPayChannel() == 2) {
                r.setPayChannelName("支付宝");
            } else if (r.getPayChannel() != null && r.getPayChannel() == 3) {
                r.setPayChannelName("云闪付");
            }
        });
        return new Payload<>(result);
    }

    /**
     * @Description: 订单ID查询支付流水记录
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/8
     */
    public Payload<List<FinancePaymentRecordsResponseDTO>> listFinancePaymentRecordsByOrderIds(Integer type, String ids) throws Exception {
        return recordsClient.listFinancePaymentRecordsByOrderIds(ids);
    }

    @Override
    public PageBean<FinancePaymentRecordsResponseDTO> byOrderCode(FinancePaymentRecordQuery query) throws Exception {
        Payload<PageBean<FinancePaymentRecordsResponseDTO>> pageBeanPayload = recordsClient.byOrderCode(query.clone(FinancePaymentRecordsRequestQuery.class));
        PayloadUtils.assertSuccess(pageBeanPayload);
        return GeneralConvertUtils.convert2PageBean(pageBeanPayload.getPayload(), FinancePaymentRecordsResponseDTO.class);
    }
}
