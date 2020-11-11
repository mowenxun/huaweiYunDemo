package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.PayOrderItemDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemPageDTO;

import java.util.List;

/**
 *
 * @author makejava
 * @since 2020-10-14 10:27:04
 */
public interface PayOrderItemDao extends IService<PayOrderItemDO> {

    List<PayOrderItemDO> findByQuery(PayOrderItemPageDTO query);
}