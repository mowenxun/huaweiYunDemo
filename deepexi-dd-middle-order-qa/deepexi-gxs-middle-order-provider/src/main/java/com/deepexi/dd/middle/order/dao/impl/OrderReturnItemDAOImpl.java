package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderReturnItemDAO;
import com.deepexi.dd.middle.order.domain.OrderReturnItemDO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnItemQuery;
import com.deepexi.dd.middle.order.mapper.OrderReturnItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderReturnItemDAOImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Repository
public class OrderReturnItemDAOImpl extends ServiceImpl<OrderReturnItemMapper, OrderReturnItemDO> implements OrderReturnItemDAO {
    /*@Autowired
    private OrderReturnItemMapper orderReturnItemMapper;*/

    @Override
    public List<OrderReturnItemDO> listOrderReturnItems(OrderReturnItemQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderReturnItemDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderReturnItemDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    /*@Override
    public int updateById(OrderReturnItemDO record) {
        return orderReturnItemMapper.updateById(record);
    }*/

}