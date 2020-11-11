package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderInvoiceInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderInvoiceInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderInvoiceInfoDAOImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Repository
public class OrderInvoiceInfoDAOImpl extends ServiceImpl<OrderInvoiceInfoMapper, OrderInvoiceInfoDO> implements OrderInvoiceInfoDAO {
/*    @Autowired
    private OrderInvoiceInfoMapper orderInvoiceInfoMapper;*/

    @Override
    public List<OrderInvoiceInfoDO> listOrderInvoiceInfos(OrderInvoiceInfoQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderInvoiceInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderInvoiceInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

  /*  @Override
    public int updateById(OrderInvoiceInfoDO record) {
        return baseMapper.updateById(record);
    }*/

}