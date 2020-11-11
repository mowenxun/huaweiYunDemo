package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonFindDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * OrderRefundReasonService
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
public interface OrderRefundReasonService {

    /**
     * 校验编码
     *
     * @param query
     * @return
     */
    List<OrderRefundReasonDTO> CheckCode(OrderRefundReasonQuery query);

    /**
     * 查询列表
     *
     * @return
     */
    List<OrderRefundReasonDTO> listOrderRefundReasons(OrderRefundReasonQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    PageBean<OrderRefundReasonDTO> listOrderRefundReasonsPage(OrderRefundReasonQuery query);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    OrderRefundReasonDTO insert(OrderRefundReasonDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    OrderRefundReasonDTO selectById(Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderRefundReasonDTO record);


    /**
     * 批量修改状态
     *
     * @param record
     * @return
     */
    boolean updateStatus(OrderRefundReasonRequestDTO record);

    /**
     * 取消退款
     * @param id
     * @return
     */
    Boolean cancel(Long id);

    List<OrderRefundReasonFindDTO> findOrderRefundReason(List<String> orderCodeList);
}