package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.PayOrderItemDao;
import com.deepexi.dd.middle.order.domain.PayOrderItemDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemPageDTO;
import com.deepexi.dd.middle.order.mapper.PayOrderItemMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Repository
public class PayOrderItemDaoImpl extends ServiceImpl<PayOrderItemMapper, PayOrderItemDO> implements PayOrderItemDao {


    @Override
    public List<PayOrderItemDO> findByQuery(PayOrderItemPageDTO query) {
        return baseMapper.findByQuery(query);
    }
}