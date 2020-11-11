package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SupplerOrderDAO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderQuery;
import com.deepexi.dd.middle.order.mapper.ShopSupplerRelationMapper;
import com.deepexi.dd.middle.order.mapper.SupplerOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SupplerOrderDAOImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Repository
public class SupplerOrderDAOImpl extends ServiceImpl<SupplerOrderMapper, SupplerOrderDO> implements SupplerOrderDAO {
    /*@Autowired
    private SupplerOrderMapper supplerOrderMapper;*/

    @Override
    public List<SupplerOrderDO> listSupplerOrders(SupplerOrderQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SupplerOrderDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public SupplerOrderDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(SupplerOrderDO record) {
        if(baseMapper.updateById(record)>0){
            return true;
        }
        return false;
    }

}