package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.RefundReasonRequestQuery;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.RefundDetailsInfoVO;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * OrderRefundReasonApi
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 20 11:29:27 CST 2020
 */
public interface OrderRefundReasonService {
    /**
     * 查询列表
     *
     * @return
     */
    List<RefundReasonResponseDTO> listOrderRefundReasons(RefundReasonRequestQuery query) throws Exception;

    /**
     * 分页查询列表
     *
     * @return
     */
    PageBean<RefundReasonResponseDTO> listOrderRefundReasonsPage(RefundReasonRequestQuery query) throws Exception;

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(@RequestBody List<Long> id) throws Exception;

    /**
     * 新增
     *
     * @param record
     * @return
     */
    RefundReasonResponseDTO insert(@RequestBody RefundReasonRequestDTO record) throws Exception;

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    RefundReasonResponseDTO selectById(@PathVariable Long id) throws Exception;

    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(@PathVariable Long id, @RequestBody RefundReasonRequestDTO record) throws Exception;

    /**
     * 根据订单号查询退款金额
     *
     * @param query
     * @return
     * @throws Exception
     */
    RefundReasonResponseDTO listOrderRefundMoney(RefundReasonRequestQuery query) throws Exception;

    /**
     * 批量修改状态
     *
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateStatus(@RequestBody RefundReasonRequestDTO record) throws Exception;

    RefundDetailsInfoVO findRefundInfoById(Long id) throws Exception;

    /**
     * 根据提货单号查询退款金额
     *
     * @param query
     * @return
     */
    RefundReasonResponseDTO listSkuByInfoId(RefundReasonRequestQuery query) throws Exception;

    /**
     * 取消退款
     * @param id
     * @return
     * @throws Exception
     */
    Boolean cancel(Long id) throws Exception;

    /**
     * 根据来源单的code查询退款单的退款支付记录
     *
     * @param query
     * @return
     * @throws Exception
     */
    FinancePaymentRecordsResponseDTO findOriginOrderRefundPaymentRecord(FinancePaymentRecordsRequestQuery query) throws Exception;

    String getRefundPayCode(String refundCode) throws Exception;

    /**
     * 获取退款记录
     *
     * @param query
     * @return
     */
    List<OrderOperationRecordResponseDTO> getRefundRecord(OrderOperationRecordRequestDTO query) throws Exception;

}
