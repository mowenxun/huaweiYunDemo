package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SaleOmsLogisticsTrajectoryDAO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryDO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryQuery;
import com.deepexi.dd.middle.order.mapper.SaleOmsLogisticsTrajectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleOmsLogisticsTrajectoryDAOImpl
 *
 * @author admin
 * @date Tue Aug 25 16:23:34 CST 2020
 * @version 1.0
 */
@Repository
public class SaleOmsLogisticsTrajectoryDAOImpl extends ServiceImpl<SaleOmsLogisticsTrajectoryMapper, SaleOmsLogisticsTrajectoryDO> implements SaleOmsLogisticsTrajectoryDAO {
    @Autowired
    private SaleOmsLogisticsTrajectoryMapper saleOmsLogisticsTrajectoryMapper;

    @Override
    public List<SaleOmsLogisticsTrajectoryDO> listSaleOmsLogisticsTrajectorys(SaleOmsLogisticsTrajectoryQuery query) {
        return saleOmsLogisticsTrajectoryMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleOmsLogisticsTrajectoryMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOmsLogisticsTrajectoryDO record) {
        return saleOmsLogisticsTrajectoryMapper.insert(record);
    }

    @Override
    public SaleOmsLogisticsTrajectoryDO selectById(Long id) {
        return saleOmsLogisticsTrajectoryMapper.selectById(id);
    }

    @Override
    public Boolean updateBatch(List<SaleOmsLogisticsTrajectoryDO> record) {
        return saleOmsLogisticsTrajectoryMapper.updateBatch(record)>0;
    }

}