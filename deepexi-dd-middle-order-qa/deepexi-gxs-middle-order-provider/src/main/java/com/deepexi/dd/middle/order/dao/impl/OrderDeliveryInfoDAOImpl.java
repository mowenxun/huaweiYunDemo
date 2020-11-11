package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderDeliveryInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderDeliveryInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliveryInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderDeliveryInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderDeliveryInfoDAOImpl
 *
 * @author admin
 * @date Wed Jul 01 19:40:51 CST 2020
 * @version 1.0
 */
@Repository
public class OrderDeliveryInfoDAOImpl extends ServiceImpl<OrderDeliveryInfoMapper, OrderDeliveryInfoDO> implements OrderDeliveryInfoDAO {
   /* @Autowired
    private OrderDeliveryInfoMapper orderDeliveryInfoMapper;*/

    @Override
    public List<OrderDeliveryInfoDO> listOrderDeliveryInfos(OrderDeliveryInfoQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderDeliveryInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderDeliveryInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

  /*  @Override
    public int updateById(OrderDeliveryInfoDO record) {
        return baseMapper.updateById(record);
    }*/

}