package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.PayOrderDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPageDTO;

import java.util.List;

/**
 *
 * @author makejava
 * @since 2020-10-14 10:27:04
 */
public interface PayOrderDao extends IService<PayOrderDO> {

    List<PayOrderDO> findByQuery(PayOrderPageDTO query);
}