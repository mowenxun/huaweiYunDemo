package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderOperationDAO;
import com.deepexi.dd.middle.order.domain.OrderOperationDO;
import com.deepexi.dd.middle.order.domain.OrderStatusOperationDO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationQuery;
import com.deepexi.dd.middle.order.mapper.OrderOperationMapper;
import com.deepexi.dd.middle.order.mapper.OrderStatusOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderOperationDAOImpl
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Repository
public class OrderOperationDAOImpl extends ServiceImpl<OrderOperationMapper, OrderOperationDO> implements OrderOperationDAO {
    @Autowired
    private OrderOperationMapper orderOperationMapper;

    @Override
    public List<OrderOperationDO> listOrderOperations(OrderOperationQuery query) {
        return orderOperationMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return orderOperationMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderOperationDO record) {
        return orderOperationMapper.insert(record);
    }

    @Override
    public OrderOperationDO selectById(Long id) {
        return orderOperationMapper.selectById(id);
    }

}