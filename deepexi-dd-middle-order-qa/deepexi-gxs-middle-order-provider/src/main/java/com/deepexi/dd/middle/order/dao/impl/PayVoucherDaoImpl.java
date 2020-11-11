package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.PayVoucherDao;
import com.deepexi.dd.middle.order.domain.PayVoucherDO;
import com.deepexi.dd.middle.order.domain.query.PayVoucherPageDTO;
import com.deepexi.dd.middle.order.mapper.PayVoucherMapper;
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
public class PayVoucherDaoImpl extends ServiceImpl<PayVoucherMapper, PayVoucherDO> implements PayVoucherDao {


    @Override
    public List<PayVoucherDO> findByQuery(PayVoucherPageDTO query) {
        return baseMapper.findByQuery(query);
    }
}