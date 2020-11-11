package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderStatusOperationDTO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderStatusOperationService
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
public interface OrderStatusOperationService {


    /**
     * 查询订单 状态-操作表列表
     *
     * @return
     */
    List<OrderStatusOperationDTO> listOrderStatusOperations(OrderStatusOperationQuery query);

    /**
     * 分页查询订单 状态-操作表列表
     *
     * @return
     */
    PageBean<OrderStatusOperationDTO> listOrderStatusOperationsPage(OrderStatusOperationQuery query);

    /**
     * 根据ID删除订单 状态-操作表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增订单 状态-操作表
     *
     * @param record
     * @return
     */
    OrderStatusOperationDTO insert(OrderStatusOperationDTO record);

    /**
     * 查询订单 状态-操作表详情
     *
     * @param id
     * @return
     */
    OrderStatusOperationDTO selectById(Long id);


}