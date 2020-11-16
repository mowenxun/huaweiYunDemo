package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonFindResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.RefundReasonRequestQuery;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderRefundReasonApi
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
@Api(value = "退货原因管理")
@RequestMapping("/open-api/v1/domain/middle/orderRefundReasons")
public interface OrderRefundReasonApi {

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    Payload<List<RefundReasonResponseDTO>> listOrderRefundReasons(RefundReasonRequestQuery query) throws Exception;

    /**
     * 分页查询列表
     *
     * @return
     */
    @GetMapping("/page")
    Payload<PageBean<RefundReasonResponseDTO>> listOrderRefundReasonsPage(RefundReasonRequestQuery query) throws Exception;


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @DeleteMapping
    Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) throws Exception;

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @PostMapping
    Payload<RefundReasonResponseDTO> insert(@RequestBody RefundReasonRequestDTO record) throws Exception;

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Payload<RefundReasonResponseDTO> selectById(@PathVariable Long id) throws Exception;


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Payload<Boolean> updateById(@PathVariable Long id, @RequestBody RefundReasonRequestDTO record) throws Exception;

    /**
     * 批量修改状态
     *
     * @param record
     * @return
     * @throws Exception
     */
    @PostMapping("/updateStatus")
    Payload<Boolean> updateStatus(@RequestBody RefundReasonRequestDTO record) throws Exception;

    @PutMapping("/cancel/{id}")
    Payload<Boolean> cancel(@PathVariable Long id) throws Exception;

    @PostMapping("/findOrderRefundReason")
    Payload<List<RefundReasonFindResponseDTO>> findOrderRefundReason(@RequestBody List<String> orderCodes) throws Exception;

    /**
     * 根据来源单的code查询退款单的退款支付记录
     *
     * @param query
     * @return
     * @throws Exception
     */
    @GetMapping("/findOriginOrderRefundPaymentRecord")
    Payload<FinancePaymentRecordsResponseDTO> findOriginOrderRefundPaymentRecord(FinancePaymentRecordsRequestQuery query) throws Exception;

}