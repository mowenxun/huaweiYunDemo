package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderRefundSkuDAO;
import com.deepexi.dd.middle.order.domain.OrderRefundSkuDO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuQuery;
import com.deepexi.dd.middle.order.mapper.OrderRefundSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderRefundSkuDAOImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:31:13 CST 2020
 */
@Repository
public class OrderRefundSkuDAOImpl extends ServiceImpl<OrderRefundSkuMapper, OrderRefundSkuDO> implements OrderRefundSkuDAO {
    @Autowired
    private OrderRefundSkuMapper orderRefundSkuMapper;

    @Override
    public List<OrderRefundSkuDO> listOrderRefundSkus(OrderRefundSkuQuery query) {
        return orderRefundSkuMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return orderRefundSkuMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderRefundSkuDO record) {
        return orderRefundSkuMapper.insert(record);
    }

    @Override
    public OrderRefundSkuDO selectById(Long id) {
        return orderRefundSkuMapper.selectById(id);
    }

    @Override
    public boolean updateById(OrderRefundSkuDO record) {
        return orderRefundSkuMapper.updateById(record) > 0;
    }

}