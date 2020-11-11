package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.SaleDeliveryPlanMainDAO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanMainDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanMainQuery;
import com.deepexi.dd.middle.order.mapper.SaleDeliveryPlanMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleDeliveryPlanMainDAOImpl
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@Repository
public class SaleDeliveryPlanMainDAOImpl implements SaleDeliveryPlanMainDAO {
    @Autowired
    private SaleDeliveryPlanMainMapper saleDeliveryPlanMainMapper;

    @Override
    public List<SaleDeliveryPlanMainDO> listSaleDeliveryPlanMains(SaleDeliveryPlanMainQuery query) {
        return saleDeliveryPlanMainMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleDeliveryPlanMainMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleDeliveryPlanMainDO record) {
        return saleDeliveryPlanMainMapper.insert(record);
    }

    @Override
    public SaleDeliveryPlanMainDO selectById(Long id) {
        return saleDeliveryPlanMainMapper.selectById(id);
    }

    @Override
    public int updateById(SaleDeliveryPlanMainDO record) {
        return saleDeliveryPlanMainMapper.updateById(record);
    }

}