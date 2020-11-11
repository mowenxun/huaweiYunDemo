package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderReturnInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderReturnInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderReturnInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderReturnInfoDAOImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Repository
public class OrderReturnInfoDAOImpl extends ServiceImpl<OrderReturnInfoMapper, OrderReturnInfoDO> implements OrderReturnInfoDAO {
  /*  @Autowired
    private OrderReturnInfoMapper orderReturnInfoMapper;*/

    @Override
    public List<OrderReturnInfoDO> listOrderReturnInfos(OrderReturnInfoQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderReturnInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderReturnInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

  /*  @Override
    public int updateById(OrderReturnInfoDO record) {
        return baseMapper.updateById(record);
    }*/

}