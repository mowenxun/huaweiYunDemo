package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.OrderInvoiceDAO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceDO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceQuery;
import com.deepexi.dd.middle.order.mapper.OrderInvoiceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderInvoiceDAOImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Repository
public class OrderInvoiceDAOImpl extends ServiceImpl<OrderInvoiceMapper, OrderInvoiceDO> implements OrderInvoiceDAO {
   /* @Autowired
    private OrderInvoiceMapper orderInvoiceMapper;*/

    @Override
    public List<OrderInvoiceDO> listOrderInvoices(OrderInvoiceQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(OrderInvoiceDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public OrderInvoiceDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(OrderInvoiceDO record) {
        if(baseMapper.updateById(record)>0){
            return true;
        }
        return false;

    }

}