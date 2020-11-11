package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SaleOrderItemDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.dd.middle.order.mapper.SaleOrderItemMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleOrderItemDAOImpl
 *
 * @author admin
 * @date Tue Jun 23 19:44:58 CST 2020
 * @version 1.0
 */
@Repository
public class SaleOrderItemDAOImpl extends ServiceImpl<SaleOrderItemMapper, SaleOrderItemDO> implements SaleOrderItemDAO {
    /*@Autowired
    private SaleOrderItemMapper saleOrderItemMapper;*/

    @Override
    public List<SaleOrderItemDO> listSaleOrderItems(SaleOrderItemMiddleRequestQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOrderItemDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public SaleOrderItemDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

   /* @Override
    public int updateById(SaleOrderItemDO record) {
        return baseMapper.updateById(record);
    }*/

    @Override
    public int batchInsert(List<SaleOrderItemDO> list) {
        return baseMapper.batchInsert(list);
    }
}