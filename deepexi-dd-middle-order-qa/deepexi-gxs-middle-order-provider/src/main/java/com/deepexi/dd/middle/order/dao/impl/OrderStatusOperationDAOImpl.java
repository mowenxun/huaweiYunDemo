package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderStatusOperationDAO;
import com.deepexi.dd.middle.order.domain.OrderStatusOperationDO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationQuery;
import com.deepexi.dd.middle.order.mapper.OrderStatusOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderStatusOperationDAOImpl
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Repository
public class OrderStatusOperationDAOImpl extends ServiceImpl<OrderStatusOperationMapper, OrderStatusOperationDO> implements OrderStatusOperationDAO {
    @Autowired
    private OrderStatusOperationMapper orderStatusOperationMapper;

    @Override
    public List<OrderStatusOperationDO> listOrderStatusOperations(OrderStatusOperationQuery query) {
        return orderStatusOperationMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return orderStatusOperationMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderStatusOperationDO record) {
        return orderStatusOperationMapper.insert(record);
    }

    @Override
    public OrderStatusOperationDO selectById(Long id) {
        return orderStatusOperationMapper.selectById(id);
    }

}