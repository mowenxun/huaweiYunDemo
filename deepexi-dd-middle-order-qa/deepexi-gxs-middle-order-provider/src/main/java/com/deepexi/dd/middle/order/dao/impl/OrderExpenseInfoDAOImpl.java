package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderExpenseInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderExpenseInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderExpenseInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderExpenseInfoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderExpenseInfoDAOImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:04 CST 2020
 * @version 1.0
 */
@Repository
public class OrderExpenseInfoDAOImpl extends ServiceImpl<OrderExpenseInfoMapper, OrderExpenseInfoDO> implements OrderExpenseInfoDAO {
  /*  @Autowired
    private OrderExpenseInfoMapper orderExpenseInfoMapper;*/

    @Override
    public List<OrderExpenseInfoDO> listOrderExpenseInfos(OrderExpenseInfoQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderExpenseInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderExpenseInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

  /*  @Override
    public int updateById(OrderExpenseInfoDO record) {
        return orderExpenseInfoMapper.updateById(record);
    }*/

    @Override
    public int batchInsert(List<OrderExpenseInfoDO> list) {
        return baseMapper.batchInsert(list);
    }
}