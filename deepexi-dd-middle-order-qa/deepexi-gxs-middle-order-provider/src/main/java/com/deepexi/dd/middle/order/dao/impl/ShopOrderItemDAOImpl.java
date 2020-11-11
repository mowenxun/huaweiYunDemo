package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.ShopOrderItemDAO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoDO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemQuery;
import com.deepexi.dd.middle.order.mapper.SendGoodsInfoMapper;
import com.deepexi.dd.middle.order.mapper.ShopOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShopOrderItemDAOImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Repository
public class ShopOrderItemDAOImpl extends ServiceImpl<ShopOrderItemMapper, ShopOrderItemDO> implements ShopOrderItemDAO {
    /*@Autowired
    private ShopOrderItemMapper shopOrderItemMapper;*/

    @Override
    public List<ShopOrderItemDO> listShopOrderItems(ShopOrderItemQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(ShopOrderItemDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public ShopOrderItemDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(ShopOrderItemDO record) {
        if(baseMapper.updateById(record)>0){
            return true;
        }
        return false;
    }

}