package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SalePickGoodsOrderDAO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderQuery;
import com.deepexi.dd.middle.order.mapper.SalePickGoodsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * SalePickGoodsOrderDAOImpl
 *
 * @author admin
 * @date Wed Aug 12 22:17:35 CST 2020
 * @version 1.0
 */
@Repository
public class SalePickGoodsOrderDAOImpl  extends ServiceImpl<SalePickGoodsOrderMapper, SalePickGoodsOrderDO> implements SalePickGoodsOrderDAO {
    @Autowired
    private SalePickGoodsOrderMapper salePickGoodsOrderMapper;

    @Override
    public List<SalePickGoodsOrderDO> listSalePickGoodsOrders(SalePickGoodsOrderQuery query) {
        return salePickGoodsOrderMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return salePickGoodsOrderMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SalePickGoodsOrderDO record) {
        return salePickGoodsOrderMapper.insert(record);
    }

    @Override
    public SalePickGoodsOrderDO selectById(Long id) {
        return salePickGoodsOrderMapper.selectById(id);
    }

//    @Override
//    public int updateById(SalePickGoodsOrderDO record) {
//        return salePickGoodsOrderMapper.updateById(record);
//    }

    @Override
    public Integer insertBatch(ArrayList<SalePickGoodsOrderDO> orderDOs) {
        return salePickGoodsOrderMapper.batchInsert(orderDOs);
    }

}