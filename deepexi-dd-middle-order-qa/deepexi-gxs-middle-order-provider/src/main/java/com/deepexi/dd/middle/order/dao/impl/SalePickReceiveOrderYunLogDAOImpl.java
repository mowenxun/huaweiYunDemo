package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.SalePickReceiveOrderYunLogDAO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogDO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogQuery;
import com.deepexi.dd.middle.order.mapper.SalePickReceiveOrderYunLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SalePickReceiveOrderYunLogDAOImpl
 *
 * @author admin
 * @date Wed Sep 23 13:47:55 CST 2020
 * @version 1.0
 */
@Repository
public class SalePickReceiveOrderYunLogDAOImpl implements SalePickReceiveOrderYunLogDAO {
    @Autowired
    private SalePickReceiveOrderYunLogMapper salePickReceiveOrderYunLogMapper;

    @Override
    public List<SalePickReceiveOrderYunLogDO> listSalePickReceiveOrderYunLogs(SalePickReceiveOrderYunLogQuery query) {
        return salePickReceiveOrderYunLogMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return salePickReceiveOrderYunLogMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SalePickReceiveOrderYunLogDO record) {
        return salePickReceiveOrderYunLogMapper.insert(record);
    }

    @Override
    public SalePickReceiveOrderYunLogDO selectById(Long id) {
        return salePickReceiveOrderYunLogMapper.selectById(id);
    }

    @Override
    public int updateById(SalePickReceiveOrderYunLogDO record) {
        return salePickReceiveOrderYunLogMapper.updateById(record);
    }

}