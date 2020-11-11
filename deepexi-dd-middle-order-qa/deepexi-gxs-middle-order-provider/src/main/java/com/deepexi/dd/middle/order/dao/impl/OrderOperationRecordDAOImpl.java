package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderOperationRecordDAO;
import com.deepexi.dd.middle.order.domain.OrderOperationRecordDO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryDO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordQuery;
import com.deepexi.dd.middle.order.mapper.OrderOperationRecordMapper;
import com.deepexi.dd.middle.order.mapper.SaleOmsLogisticsTrajectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderOperationRecordDAOImpl
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
@Repository
public class OrderOperationRecordDAOImpl extends ServiceImpl<OrderOperationRecordMapper, OrderOperationRecordDO> implements OrderOperationRecordDAO {
    @Autowired
    private OrderOperationRecordMapper orderOperationRecordMapper;

    @Override
    public List<OrderOperationRecordDO> listOrderOperationRecords(OrderOperationRecordQuery query) {
        return orderOperationRecordMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return orderOperationRecordMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderOperationRecordDO record) {
        return orderOperationRecordMapper.insert(record);
    }

    @Override
    public OrderOperationRecordDO selectById(Long id) {
        return orderOperationRecordMapper.selectById(id);
    }

//    @Override
//    public int updateById(OrderOperationRecordDO record) {
//        return orderOperationRecordMapper.updateById(record);
//    }

}