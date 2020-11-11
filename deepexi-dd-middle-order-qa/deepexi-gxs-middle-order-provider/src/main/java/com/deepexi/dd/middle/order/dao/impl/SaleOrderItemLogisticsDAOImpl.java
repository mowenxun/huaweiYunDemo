package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.SaleOrderItemLogisticsDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderItemLogisticsDO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemLogisticsQuery;
import com.deepexi.dd.middle.order.mapper.SaleOrderItemLogisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleOrderItemLogisticsDAOImpl
 *
 * @author admin
 * @date Sat Aug 22 16:34:04 CST 2020
 * @version 1.0
 */
@Repository
public class SaleOrderItemLogisticsDAOImpl implements SaleOrderItemLogisticsDAO {
    @Autowired
    private SaleOrderItemLogisticsMapper saleOrderItemLogisticsMapper;

    @Override
    public List<SaleOrderItemLogisticsDO> listSaleOrderItemLogisticss(SaleOrderItemLogisticsQuery query) {
        return saleOrderItemLogisticsMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleOrderItemLogisticsMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOrderItemLogisticsDO record) {
        return saleOrderItemLogisticsMapper.insert(record);
    }

    @Override
    public SaleOrderItemLogisticsDO selectById(Long id) {
        return saleOrderItemLogisticsMapper.selectById(id);
    }

    @Override
    public int updateById(SaleOrderItemLogisticsDO record) {
        return saleOrderItemLogisticsMapper.updateById(record);
    }

}