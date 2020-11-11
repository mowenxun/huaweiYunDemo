package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.OrderPayBackDAO;
import com.deepexi.dd.middle.order.domain.OrderPayBackDO;
import com.deepexi.dd.middle.order.domain.OrderPayBackQuery;
import com.deepexi.dd.middle.order.mapper.OrderPayBackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderPayBackDAOImpl
 *
 * @author admin
 * @date Wed Jul 22 16:27:51 CST 2020
 * @version 1.0
 */
@Repository
public class OrderPayBackDAOImpl implements OrderPayBackDAO {
    @Autowired
    private OrderPayBackMapper orderPayBackMapper;

    @Override
    public List<OrderPayBackDO> listOrderPayBacks(OrderPayBackQuery query) {
        return orderPayBackMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return orderPayBackMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderPayBackDO record) {
        return orderPayBackMapper.insert(record);
    }

    @Override
    public OrderPayBackDO selectById(Long id) {
        return orderPayBackMapper.selectById(id);
    }

    @Override
    public int updateById(OrderPayBackDO record) {
        return orderPayBackMapper.updateById(record);
    }

}