package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.PayOrderPlatformItemDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemPageDTO;

import java.util.List;

/**
 *
 * @author makejava
 * @since 2020-10-14 10:27:04
 */
public interface PayOrderPlatformItemDao extends IService<PayOrderPlatformItemDO> {

    List<PayOrderPlatformItemDO> findByQuery(PayOrderPlatformItemPageDTO query);
}