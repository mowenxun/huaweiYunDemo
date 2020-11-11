package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SaleOrderCollectionNotifyDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyDO;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyQuery;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDO;
import com.deepexi.dd.middle.order.mapper.SaleOrderCollectionNotifyMapper;
import com.deepexi.dd.middle.order.mapper.SaleOrderPayNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleOrderCollectionNotifyDAOImpl
 *
 * @author admin
 * @date Fri Jul 24 14:50:09 CST 2020
 * @version 1.0
 */
@Repository
public class SaleOrderCollectionNotifyDAOImpl extends ServiceImpl<SaleOrderCollectionNotifyMapper, SaleOrderCollectionNotifyDO> implements SaleOrderCollectionNotifyDAO {
    @Autowired
    private SaleOrderCollectionNotifyMapper saleOrderCollectionNotifyMapper;

    @Override
    public List<SaleOrderCollectionNotifyDO> listSaleOrderCollectionNotifys(SaleOrderCollectionNotifyQuery query) {
        return saleOrderCollectionNotifyMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleOrderCollectionNotifyMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOrderCollectionNotifyDO record) {
        return saleOrderCollectionNotifyMapper.insert(record);
    }

    @Override
    public SaleOrderCollectionNotifyDO selectById(Long id) {
        return saleOrderCollectionNotifyMapper.selectById(id);
    }

    @Override
    public boolean updateById(SaleOrderCollectionNotifyDO record) {
        return saleOrderCollectionNotifyMapper.updateById(record)>0;
    }

}