package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SupplerOrderItemDAO;
import com.deepexi.dd.middle.order.domain.SupplerOrderDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemQuery;
import com.deepexi.dd.middle.order.mapper.SupplerOrderItemMapper;
import com.deepexi.dd.middle.order.mapper.SupplerOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SupplerOrderItemDAOImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Repository
public class SupplerOrderItemDAOImpl extends ServiceImpl<SupplerOrderItemMapper, SupplerOrderItemDO> implements SupplerOrderItemDAO {
/*    @Autowired
    private SupplerOrderItemMapper supplerOrderItemMapper;*/

    @Override
    public List<SupplerOrderItemDO> listSupplerOrderItems(SupplerOrderItemQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SupplerOrderItemDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public SupplerOrderItemDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(SupplerOrderItemDO record) {
        if(baseMapper.updateById(record)>0){
            return true;
        }
        return false;
    }

}