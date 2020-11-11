package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderStatusOperationDO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationQuery;

import java.util.List;


/**
 * OrderStatusOperationDAO
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
public interface OrderStatusOperationDAO extends IService<OrderStatusOperationDO> {

    /**
     * 查询订单 状态-操作表列表}
     *
     * @return
     */
    List<OrderStatusOperationDO> listOrderStatusOperations(OrderStatusOperationQuery query);

    /**
     * 根据ID删除订单 状态-操作表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增订单 状态-操作表
     *
     * @param record
     * @return
     */
    int insert(OrderStatusOperationDO record);

    /**
     * 查询订单 状态-操作表详情
     *
     * @param id
     * @return
     */
    OrderStatusOperationDO selectById(Long id);



}