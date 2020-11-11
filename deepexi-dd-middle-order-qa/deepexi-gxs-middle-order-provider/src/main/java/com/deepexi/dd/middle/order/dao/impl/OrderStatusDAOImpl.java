package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.OrderStatusDAO;
import com.deepexi.dd.middle.order.domain.OrderStatusDO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusQuery;
import com.deepexi.dd.middle.order.mapper.OrderStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderStatusDAOImpl
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Repository
public class OrderStatusDAOImpl implements OrderStatusDAO {
    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public List<OrderStatusDO> listOrderStatuss(OrderStatusQuery query) {
        return orderStatusMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return orderStatusMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderStatusDO record) {
        return orderStatusMapper.insert(record);
    }

    @Override
    public OrderStatusDO selectById(Long id) {
        return orderStatusMapper.selectById(id);
    }

    @Override
    public int updateById(OrderStatusDO record) {
        return orderStatusMapper.updateById(record);
    }

}