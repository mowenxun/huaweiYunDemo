package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderDeliverySelfRaisingInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoDO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderDeliverySelfRaisingInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderDeliverySelfRaisingInfoDAOImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 26 16:41:35 CST 2020
 */
@Repository
public class OrderDeliverySelfRaisingInfoDAOImpl extends ServiceImpl<OrderDeliverySelfRaisingInfoMapper, OrderDeliverySelfRaisingInfoDO> implements OrderDeliverySelfRaisingInfoDAO {
    @Autowired
    private OrderDeliverySelfRaisingInfoMapper orderDeliverySelfRaisingInfoMapper;

    @Override
    public List<OrderDeliverySelfRaisingInfoDO> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoQuery query) {
        return orderDeliverySelfRaisingInfoMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return orderDeliverySelfRaisingInfoMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderDeliverySelfRaisingInfoDO record) {
        return orderDeliverySelfRaisingInfoMapper.insert(record);
    }

    @Override
    public OrderDeliverySelfRaisingInfoDO selectById(Long id) {
        return orderDeliverySelfRaisingInfoMapper.selectById(id);
    }

//    @Override
//    public int updateById(OrderDeliverySelfRaisingInfoDO record) {
//        return orderDeliverySelfRaisingInfoMapper.updateById(record);
//    }

    @Override
    public OrderDeliverySelfRaisingInfoDO selectBySaleOrderId(Long id) {
        QueryWrapper<OrderDeliverySelfRaisingInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("sale_order_id",id);
        return orderDeliverySelfRaisingInfoMapper.selectOne(queryWrapper);
    }

}