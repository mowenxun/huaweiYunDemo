package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonFindResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonRequestQuery;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuRequestQuery;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * OrderRefundReasonApi
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
public interface OrderRefundReasonService {
    /**
     * 查询列表
     *
     * @return
     */
    List<OrderRefundReasonResponseDTO> listOrderRefundReasons(OrderRefundReasonRequestQuery query) throws Exception;

    /**
     * 分页查询列表
     *
     * @return
     */
    PageBean<OrderRefundReasonResponseDTO> listOrderRefundReasonsPage(OrderRefundReasonRequestQuery query) throws Exception;


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
    OrderRefundReasonResponseDTO insert(@RequestBody OrderRefundReasonRequestDTO record) throws Exception;

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    OrderRefundReasonResponseDTO selectById(@PathVariable Long id) throws Exception;

    /**
     * 查询退货明细
     * @param refundQuery
     * @return
     * @throws ApplicationException
     */
     List<OrderRefundSkuResponseDTO> queryOrderRefundSku(OrderRefundSkuRequestQuery refundQuery) throws ApplicationException;

    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(@PathVariable Long id, @RequestBody OrderRefundReasonRequestDTO record) throws Exception;

    /**
     * 批量修改状态
     *
     * @param record
     * @return
     */
    Boolean updateStatus(@RequestBody OrderRefundReasonRequestDTO record) throws Exception;

    /**
     * 取消退款
     * @param id
     * @return
     * @throws Exception
     */
    Boolean cancel(Long id) throws Exception;

    List<OrderRefundReasonFindResponseDTO> findOrderRefundReason(List<String> orderCodes) throws Exception;

    /**
     * 根据来源单的code查询退款单的退款支付记录
     *
     * @param query
     * @return
     * @throws Exception
     */
    FinancePaymentRecordsResponseDTO findOriginOrderRefundPaymentRecord(FinancePaymentRecordsRequestQuery query) throws Exception;

}