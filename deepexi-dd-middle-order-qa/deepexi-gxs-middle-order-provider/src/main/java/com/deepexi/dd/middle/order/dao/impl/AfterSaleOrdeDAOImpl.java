package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.AfterSaleOrdeDAO;
import com.deepexi.dd.middle.order.domain.AfterSaleOrdeDO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeQuery;
import com.deepexi.dd.middle.order.mapper.AfterSaleOrdeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AfterSaleOrdeDAOImpl
 *
 * @author admin
 * @date Fri Oct 16 14:17:12 CST 2020
 * @version 1.0
 */
@Repository
public class AfterSaleOrdeDAOImpl implements AfterSaleOrdeDAO {
    @Autowired
    private AfterSaleOrdeMapper afterSaleOrdeMapper;

    @Override
    public List<AfterSaleOrdeDO> listAfterSaleOrdes(AfterSaleOrdeQuery query) {
        return afterSaleOrdeMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return afterSaleOrdeMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(AfterSaleOrdeDO record) {
        return afterSaleOrdeMapper.insert(record);
    }

    @Override
    public AfterSaleOrdeDO selectById(Long id) {
        return afterSaleOrdeMapper.selectById(id);
    }

    @Override
    public int updateById(AfterSaleOrdeDO record) {
        return afterSaleOrdeMapper.updateById(record);
    }

}