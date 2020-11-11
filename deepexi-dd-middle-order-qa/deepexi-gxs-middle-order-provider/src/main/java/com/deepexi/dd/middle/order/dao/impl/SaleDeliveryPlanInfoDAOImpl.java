package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.SaleDeliveryPlanInfoDAO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanInfoDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoQuery;
import com.deepexi.dd.middle.order.mapper.SaleDeliveryPlanInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleDeliveryPlanInfoDAOImpl
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@Repository
public class SaleDeliveryPlanInfoDAOImpl implements SaleDeliveryPlanInfoDAO {
    @Autowired
    private SaleDeliveryPlanInfoMapper saleDeliveryPlanInfoMapper;

    @Override
    public List<SaleDeliveryPlanInfoDO> listSaleDeliveryPlanInfos(SaleDeliveryPlanInfoQuery query) {
        return saleDeliveryPlanInfoMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleDeliveryPlanInfoMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleDeliveryPlanInfoDO record) {
        return saleDeliveryPlanInfoMapper.insert(record);
    }

    @Override
    public SaleDeliveryPlanInfoDO selectById(Long id) {
        return saleDeliveryPlanInfoMapper.selectById(id);
    }

    @Override
    public int updateById(SaleDeliveryPlanInfoDO record) {
        return saleDeliveryPlanInfoMapper.updateById(record);
    }

}