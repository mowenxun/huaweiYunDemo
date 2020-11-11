package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderOperationDO;
import com.deepexi.dd.middle.order.domain.OrderStatusOperationDO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationQuery;

import java.util.List;


/**
 * OrderOperationDAO
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
public interface OrderOperationDAO extends IService<OrderOperationDO> {

    /**
     * 查询按钮表列表}
     *
     * @return
     */
    List<OrderOperationDO> listOrderOperations(OrderOperationQuery query);

    /**
     * 根据ID删除按钮表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增按钮表
     *
     * @param record
     * @return
     */
    int insert(OrderOperationDO record);

    /**
     * 查询按钮表详情
     *
     * @param id
     * @return
     */
    OrderOperationDO selectById(Long id);

}