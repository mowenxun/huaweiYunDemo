package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SaleOrderPayNotifyDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderInfoDO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyQuery;
import com.deepexi.dd.middle.order.mapper.SaleOrderInfoMapper;
import com.deepexi.dd.middle.order.mapper.SaleOrderPayNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleOrderPayNotifyDAOImpl
 *
 * @author admin
 * @date Thu Jul 23 18:17:38 CST 2020
 * @version 1.0
 */
@Repository
public class SaleOrderPayNotifyDAOImpl extends ServiceImpl<SaleOrderPayNotifyMapper, SaleOrderPayNotifyDO> implements SaleOrderPayNotifyDAO {
    @Autowired
    private SaleOrderPayNotifyMapper saleOrderPayNotifyMapper;

    @Override
    public List<SaleOrderPayNotifyDO> listSaleOrderPayNotifys(SaleOrderPayNotifyQuery query) {
        return saleOrderPayNotifyMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleOrderPayNotifyMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOrderPayNotifyDO record) {
        return saleOrderPayNotifyMapper.insert(record);
    }

    @Override
    public SaleOrderPayNotifyDO selectById(Long id) {
        return saleOrderPayNotifyMapper.selectById(id);
    }

    @Override
    public boolean updateById(SaleOrderPayNotifyDO record) {
        return saleOrderPayNotifyMapper.updateById(record)>0;
    }

}