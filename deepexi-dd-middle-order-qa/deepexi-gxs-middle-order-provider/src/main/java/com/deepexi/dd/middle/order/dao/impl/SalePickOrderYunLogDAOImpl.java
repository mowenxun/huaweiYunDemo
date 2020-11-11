package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.SalePickOrderYunLogDAO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogDO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogQuery;
import com.deepexi.dd.middle.order.mapper.SalePickOrderYunLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SalePickOrderYunLogDAOImpl
 *
 * @author admin
 * @date Thu Aug 27 21:37:43 CST 2020
 * @version 1.0
 */
@Repository
public class SalePickOrderYunLogDAOImpl implements SalePickOrderYunLogDAO {
    @Autowired
    private SalePickOrderYunLogMapper salePickOrderYunLogMapper;

    @Override
    public List<SalePickOrderYunLogDO> listSalePickOrderYunLogs(SalePickOrderYunLogQuery query) {
        return salePickOrderYunLogMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return salePickOrderYunLogMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SalePickOrderYunLogDO record) {
        return salePickOrderYunLogMapper.insert(record);
    }

    @Override
    public SalePickOrderYunLogDO selectById(Long id) {
        return salePickOrderYunLogMapper.selectById(id);
    }

    @Override
    public int updateById(SalePickOrderYunLogDO record) {
        return salePickOrderYunLogMapper.updateById(record);
    }

}