package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SaleOrderSplitRecordDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordDO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordQuery;
import com.deepexi.dd.middle.order.mapper.SaleOrderSplitRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleOrderSplitRecordDAOImpl
 *
 * @author admin
 * @date Wed Aug 12 20:24:31 CST 2020
 * @version 1.0
 */
@Repository
public class SaleOrderSplitRecordDAOImpl extends ServiceImpl<SaleOrderSplitRecordMapper,SaleOrderSplitRecordDO> implements SaleOrderSplitRecordDAO {
    @Autowired
    private SaleOrderSplitRecordMapper saleOrderSplitRecordMapper;

    @Override
    public List<SaleOrderSplitRecordDO> listSaleOrderSplitRecords(SaleOrderSplitRecordQuery query) {
        return saleOrderSplitRecordMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleOrderSplitRecordMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOrderSplitRecordDO record) {
        return saleOrderSplitRecordMapper.insert(record);
    }

    @Override
    public SaleOrderSplitRecordDO selectById(Long id) {
        return saleOrderSplitRecordMapper.selectById(id);
    }

    @Override
    public boolean updateById(SaleOrderSplitRecordDO record) {
        return saleOrderSplitRecordMapper.updateById(record)>0;
    }


}