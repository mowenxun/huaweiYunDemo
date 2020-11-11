package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderOperationDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderOperationService
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
public interface OrderOperationService {


    /**
     * 查询按钮表列表
     *
     * @return
     */
    List<OrderOperationDTO> listOrderOperations(OrderOperationQuery query);

    /**
     * 分页查询按钮表列表
     *
     * @return
     */
    PageBean<OrderOperationDTO> listOrderOperationsPage(OrderOperationQuery query);

    /**
     * 根据ID删除按钮表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增按钮表
     *
     * @param record
     * @return
     */
    OrderOperationDTO insert(OrderOperationDTO record);

    /**
     * 查询按钮表详情
     *
     * @param id
     * @return
     */
    OrderOperationDTO selectById(Long id);

}