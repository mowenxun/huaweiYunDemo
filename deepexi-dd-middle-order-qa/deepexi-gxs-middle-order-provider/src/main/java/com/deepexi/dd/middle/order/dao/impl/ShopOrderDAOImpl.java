package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.ShopOrderDAO;
import com.deepexi.dd.middle.order.domain.ShopOrderDO;
import com.deepexi.dd.middle.order.domain.ShopOrderQuery;
import com.deepexi.dd.middle.order.mapper.ShopOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShopOrderDAOImpl
 *
 * @author admin
 * @date Tue Oct 13 14:53:15 CST 2020
 * @version 1.0
 */
@Repository
public class ShopOrderDAOImpl extends ServiceImpl<ShopOrderMapper,ShopOrderDO> implements ShopOrderDAO {
   /* @Autowired
    private ShopOrderMapper shopOrderMapper;*/

    @Override
    public List<ShopOrderDO> listShopOrders(ShopOrderQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(ShopOrderDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public ShopOrderDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(ShopOrderDO record) {
        if(baseMapper.updateById(record)>0){
        return true;
        }else{
            return  false;
        }
    }

    @Override
    public Boolean updateByCode(List<ShopOrderDO> shopOrderDos) {
        return baseMapper.updateBatchByCode(shopOrderDos) > 0;
    }

}