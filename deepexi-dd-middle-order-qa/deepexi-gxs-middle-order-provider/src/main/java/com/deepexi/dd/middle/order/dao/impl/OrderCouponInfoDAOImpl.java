package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderCouponInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderCouponInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderCouponInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderCouponInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderCouponInfoDAOImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Repository
public class OrderCouponInfoDAOImpl extends ServiceImpl<OrderCouponInfoMapper, OrderCouponInfoDO> implements OrderCouponInfoDAO {
 /*   @Autowired
    private OrderCouponInfoMapper orderCouponInfoMapper;*/

    @Override
    public List<OrderCouponInfoDO> listOrderCouponInfos(OrderCouponInfoQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderCouponInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderCouponInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

  /*  @Override
    public int updateById(OrderCouponInfoDO record) {
        return orderCouponInfoMapper.updateById(record);
    }*/

    @Override
    public int batchInsert(List<OrderCouponInfoDO> list) {
        return baseMapper.batchInsert(list);
    }
}