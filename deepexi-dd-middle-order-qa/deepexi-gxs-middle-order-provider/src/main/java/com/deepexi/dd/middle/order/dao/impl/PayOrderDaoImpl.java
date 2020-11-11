package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.PayOrderDao;
import com.deepexi.dd.middle.order.domain.PayOrderDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPageDTO;
import com.deepexi.dd.middle.order.mapper.PayOrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PayVoucherDaoImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Repository
public class PayOrderDaoImpl extends ServiceImpl<PayOrderMapper, PayOrderDO> implements PayOrderDao {


    @Override
    public List<PayOrderDO> findByQuery(PayOrderPageDTO query) {
        return baseMapper.findByQuery(query);
    }
}