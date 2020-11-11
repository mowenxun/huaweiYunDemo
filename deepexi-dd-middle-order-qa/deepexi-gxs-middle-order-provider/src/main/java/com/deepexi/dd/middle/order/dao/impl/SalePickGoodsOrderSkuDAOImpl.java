package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SalePickGoodsOrderSkuDAO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.mapper.SalePickGoodsOrderSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * SalePickGoodsOrderSkuDAOImpl
 *
 * @author admin
 * @date Thu Aug 13 07:37:26 CST 2020
 * @version 1.0
 */
@Repository
public class SalePickGoodsOrderSkuDAOImpl  extends ServiceImpl<SalePickGoodsOrderSkuMapper, SalePickGoodsOrderSkuDO> implements SalePickGoodsOrderSkuDAO {
    @Autowired
    private SalePickGoodsOrderSkuMapper salePickGoodsOrderSkuMapper;

    @Override
    public List<SalePickGoodsOrderSkuDO> listSalePickGoodsOrderSkus(SalePickGoodsOrderSkuQuery query) {
        return salePickGoodsOrderSkuMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return salePickGoodsOrderSkuMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SalePickGoodsOrderSkuDO record) {
        return salePickGoodsOrderSkuMapper.insert(record);
    }

    @Override
    public SalePickGoodsOrderSkuDO selectById(Long id) {
        return salePickGoodsOrderSkuMapper.selectById(id);
    }

//    @Override
//    public Boolean updateById(SalePickGoodsOrderSkuDO record) {
//        return salePickGoodsOrderSkuMapper.updateById(record)>0;
//    }

    @Override
    public Integer insertBatch(ArrayList<SalePickGoodsOrderSkuDO> skuDOS) {
        return salePickGoodsOrderSkuMapper.batchInsert(skuDOS);
    }

    @Override
    public SalePickGoodsOrderSkuResponseDTO selectByRowCode(String rowCode) {
        return salePickGoodsOrderSkuMapper.selectByRowCode(rowCode).clone(SalePickGoodsOrderSkuResponseDTO.class);
    }

}