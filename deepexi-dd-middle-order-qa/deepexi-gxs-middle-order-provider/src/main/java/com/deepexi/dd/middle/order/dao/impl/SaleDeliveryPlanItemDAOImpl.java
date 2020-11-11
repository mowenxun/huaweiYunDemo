package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.SaleDeliveryPlanItemDAO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanItemDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemQuery;
import com.deepexi.dd.middle.order.mapper.SaleDeliveryPlanItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleDeliveryPlanItemDAOImpl
 *
 * @author admin
 * @date Thu Aug 13 16:42:15 CST 2020
 * @version 1.0
 */
@Repository
public class SaleDeliveryPlanItemDAOImpl implements SaleDeliveryPlanItemDAO {
    @Autowired
    private SaleDeliveryPlanItemMapper saleDeliveryPlanItemMapper;

    @Override
    public List<SaleDeliveryPlanItemDO> listSaleDeliveryPlanItems(SaleDeliveryPlanItemQuery query) {
        return saleDeliveryPlanItemMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleDeliveryPlanItemMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleDeliveryPlanItemDO record) {
        return saleDeliveryPlanItemMapper.insert(record);
    }

    @Override
    public SaleDeliveryPlanItemDO selectById(Long id) {
        return saleDeliveryPlanItemMapper.selectById(id);
    }

    @Override
    public int updateById(SaleDeliveryPlanItemDO record) {
        return saleDeliveryPlanItemMapper.updateById(record);
    }

}