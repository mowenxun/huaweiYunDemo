package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.PayOrderPlatformItemDao;
import com.deepexi.dd.middle.order.domain.PayOrderPlatformItemDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemPageDTO;
import com.deepexi.dd.middle.order.mapper.PayOrderPlatformItemMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Repository
public class PayOrderPlatformItemDaoImpl extends ServiceImpl<PayOrderPlatformItemMapper, PayOrderPlatformItemDO> implements PayOrderPlatformItemDao {


    @Override
    public List<PayOrderPlatformItemDO> findByQuery(PayOrderPlatformItemPageDTO query) {
        return baseMapper.findByQuery(query);
    }
}