package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderConsigneeAddressDAO;
import com.deepexi.dd.middle.order.domain.ActivitySkuOrderQuantityDO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressDO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressQuery;
import com.deepexi.dd.middle.order.mapper.ActivitySkuOrderQuantityMapper;
import com.deepexi.dd.middle.order.mapper.OrderConsigneeAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderConsigneeAddressDAOImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Repository
public class OrderConsigneeAddressDAOImpl extends ServiceImpl<OrderConsigneeAddressMapper, OrderConsigneeAddressDO> implements OrderConsigneeAddressDAO {
/*    @Autowired
    private OrderConsigneeAddressMapper orderConsigneeAddressMapper;*/

    @Override
    public List<OrderConsigneeAddressDO> listOrderConsigneeAddresss(OrderConsigneeAddressQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderConsigneeAddressDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderConsigneeAddressDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(OrderConsigneeAddressDO record) {
        if(baseMapper.updateById(record)>0){
            return true;
        }
        return false;
    }

}