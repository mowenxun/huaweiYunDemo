package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.AfterSaleOrderItemDAO;
import com.deepexi.dd.middle.order.domain.AfterSaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemQuery;
import com.deepexi.dd.middle.order.mapper.AfterSaleOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AfterSaleOrderItemDAOImpl
 *
 * @author admin
 * @date Fri Oct 16 14:17:13 CST 2020
 * @version 1.0
 */
@Repository
public class AfterSaleOrderItemDAOImpl implements AfterSaleOrderItemDAO {
    @Autowired
    private AfterSaleOrderItemMapper afterSaleOrderItemMapper;

    @Override
    public List<AfterSaleOrderItemDO> listAfterSaleOrderItems(AfterSaleOrderItemQuery query) {
        return afterSaleOrderItemMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return afterSaleOrderItemMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(AfterSaleOrderItemDO record) {
        return afterSaleOrderItemMapper.insert(record);
    }

    @Override
    public AfterSaleOrderItemDO selectById(Long id) {
        return afterSaleOrderItemMapper.selectById(id);
    }

    @Override
    public int updateById(AfterSaleOrderItemDO record) {
        return afterSaleOrderItemMapper.updateById(record);
    }

}