package com.deepexi.dd.system.mall.service.finance;

import com.deepexi.dd.domain.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.system.mall.domain.query.FinancePaymentRecordQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @ClassName FinancePaymentRecords
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-08
 * @Version 1.0
 **/
public interface FinancePaymentRecordsService {

    /**
    * @Description: 分页查询支付记录
    * @Param: 
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/8/8
    */
    Payload<PageBean<FinancePaymentRecordsResponseDTO>> listFinancePaymentRecordsPage(FinancePaymentRecordsRequestQuery requestQuery) throws Exception;

    /**
    * @Description: 订单ID查询支付流水记录
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/8/8
    */
    Payload<List<FinancePaymentRecordsResponseDTO>> listFinancePaymentRecordsByOrderIds(Integer type,String ids) throws Exception;

    PageBean<FinancePaymentRecordsResponseDTO> byOrderCode(FinancePaymentRecordQuery query) throws Exception;
}
