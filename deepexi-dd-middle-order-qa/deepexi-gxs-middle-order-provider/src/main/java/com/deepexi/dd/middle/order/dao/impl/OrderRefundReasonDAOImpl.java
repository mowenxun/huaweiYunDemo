package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.OrderRefundReasonDAO;
import com.deepexi.dd.middle.order.domain.OrderRefundReasonDO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonFindDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonQuery;
import com.deepexi.dd.middle.order.mapper.OrderRefundReasonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderRefundReasonDAOImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
@Repository
public class OrderRefundReasonDAOImpl implements OrderRefundReasonDAO {
    @Autowired
    private OrderRefundReasonMapper orderRefundReasonMapper;

    @Override
    public List<OrderRefundReasonDO> CheckCode(OrderRefundReasonQuery query) {
        return orderRefundReasonMapper.CheckCode(query);
    }

    @Override
    public List<OrderRefundReasonDO> listOrderRefundReasons(OrderRefundReasonQuery query) {
        return orderRefundReasonMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return orderRefundReasonMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderRefundReasonDO record) {
        return orderRefundReasonMapper.insert(record);
    }

    @Override
    public OrderRefundReasonDO selectById(Long id) {
        return orderRefundReasonMapper.selectById(id);
    }

    @Override
    public int updateById(OrderRefundReasonDO record) {
        return orderRefundReasonMapper.updateById(record);
    }

    @Override
    public int updateStatus(OrderRefundReasonRequestDTO record) {
        return orderRefundReasonMapper.updateStatus(record);
    }

    @Override
    public List<OrderRefundReasonFindDTO> findOrderRefundReason(List<String> orderCodeList) {
        return orderRefundReasonMapper.findOrderRefundReason(orderCodeList);
    }
}