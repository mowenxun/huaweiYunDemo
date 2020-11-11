package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderConsigneeInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderConsigneeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderConsigneeInfoDAOImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Repository
public class OrderConsigneeInfoDAOImpl extends ServiceImpl<OrderConsigneeInfoMapper, OrderConsigneeInfoDO> implements OrderConsigneeInfoDAO {
  /*  @Autowired
    private OrderConsigneeInfoMapper orderConsigneeInfoMapper;*/

    @Override
    public List<OrderConsigneeInfoDO> listOrderConsigneeInfos(OrderConsigneeInfoQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderConsigneeInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderConsigneeInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }
/*
    @Override
    public int updateById(OrderConsigneeInfoDO record) {
        return orderConsigneeInfoMapper.updateById(record);
    }*/

}