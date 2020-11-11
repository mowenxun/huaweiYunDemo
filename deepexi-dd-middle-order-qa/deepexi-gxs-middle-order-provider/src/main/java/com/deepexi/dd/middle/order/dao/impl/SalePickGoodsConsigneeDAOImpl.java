package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.deepexi.dd.middle.order.dao.SalePickGoodsConsigneeDAO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsConsigneeResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsConsigneeRequestQuery;
import com.deepexi.dd.middle.order.mapper.SalePickGoodsConsigneeMapper;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SalePickGoodsConsigneeDAOImpl
 *
 * @author admin
 * @version 1.0
 * @date Mon Aug 24 16:35:15 CST 2020
 */
@Repository
public class SalePickGoodsConsigneeDAOImpl implements SalePickGoodsConsigneeDAO {
    @Autowired
    private SalePickGoodsConsigneeMapper salePickGoodsConsigneeMapper;

    @Override
    public List<SalePickGoodsConsigneeDO> listSalePickGoodsConsignees(SalePickGoodsConsigneeQuery query) {
        return salePickGoodsConsigneeMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return salePickGoodsConsigneeMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SalePickGoodsConsigneeDO record) {
        return salePickGoodsConsigneeMapper.insert(record);
    }

    @Override
    public SalePickGoodsConsigneeDO selectById(Long id) {
        return salePickGoodsConsigneeMapper.selectById(id);
    }

    @Override
    public int updateById(SalePickGoodsConsigneeDO record) {
        return salePickGoodsConsigneeMapper.updateById(record);
    }

    @Override
    public int updateByWrapper(SalePickGoodsConsigneeDO record, Wrapper<SalePickGoodsConsigneeDO> updateWrapper) {
        return salePickGoodsConsigneeMapper.update(record, updateWrapper);
    }

    @Override
    public SalePickGoodsConsigneeResponseDTO selectByPickCode(SalePickGoodsConsigneeRequestQuery salePickGoodsConsigneeRequestQuery) {
        return salePickGoodsConsigneeMapper.selectByPickCode(salePickGoodsConsigneeRequestQuery).clone(SalePickGoodsConsigneeResponseDTO.class);
    }

}