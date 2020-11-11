package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.PayOrderPlatformDao;
import com.deepexi.dd.middle.order.domain.PayOrderPlatformDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformPageDTO;
import com.deepexi.dd.middle.order.mapper.PayOrderPlatformMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Repository
public class PayOrderPlatformDaoImpl extends ServiceImpl<PayOrderPlatformMapper, PayOrderPlatformDO> implements PayOrderPlatformDao {


    @Override
    public List<PayOrderPlatformDO> findByQuery(PayOrderPlatformPageDTO query) {
        return baseMapper.findByQuery(query);
    }
}