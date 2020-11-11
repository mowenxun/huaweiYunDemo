package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.ShopSupplerRelationDAO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationDO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationQuery;
import com.deepexi.dd.middle.order.mapper.ShopOrderItemMapper;
import com.deepexi.dd.middle.order.mapper.ShopSupplerRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShopSupplerRelationDAOImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Repository
public class ShopSupplerRelationDAOImpl extends ServiceImpl<ShopSupplerRelationMapper, ShopSupplerRelationDO> implements ShopSupplerRelationDAO {
   /* @Autowired
    private ShopSupplerRelationMapper shopSupplerRelationMapper;*/

    @Override
    public List<ShopSupplerRelationDO> listShopSupplerRelations(ShopSupplerRelationQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(ShopSupplerRelationDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public ShopSupplerRelationDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(ShopSupplerRelationDO record) {
        if(baseMapper.updateById(record)>0){
            return true;
        }
        return false;
    }

}