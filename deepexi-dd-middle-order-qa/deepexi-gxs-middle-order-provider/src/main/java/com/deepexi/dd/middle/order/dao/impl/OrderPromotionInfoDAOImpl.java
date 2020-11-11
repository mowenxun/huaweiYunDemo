package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderPromotionInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderPromotionInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderPromotionInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderPromotionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderPromotionInfoDAOImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Repository
public class OrderPromotionInfoDAOImpl extends ServiceImpl<OrderPromotionInfoMapper, OrderPromotionInfoDO> implements OrderPromotionInfoDAO {
   /* @Autowired
    private OrderPromotionInfoMapper orderPromotionInfoMapper;*/

    @Override
    public List<OrderPromotionInfoDO> listOrderPromotionInfos(OrderPromotionInfoQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderPromotionInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderPromotionInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

/*    @Override
    public int updateById(OrderPromotionInfoDO record) {
        return orderPromotionInfoMapper.updateById(record);
    }*/

    @Override
    public int batchInsert(List<OrderPromotionInfoDO> list) {
        return baseMapper.batchInsert(list);
    }
}