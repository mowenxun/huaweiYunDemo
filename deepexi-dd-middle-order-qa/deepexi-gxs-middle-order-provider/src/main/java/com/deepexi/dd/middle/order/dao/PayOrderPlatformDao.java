package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.PayOrderPlatformDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformPageDTO;

import java.util.List;

/**
 * 付款凭证表(PayVoucher)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-14 10:27:04
 */
public interface PayOrderPlatformDao extends IService<PayOrderPlatformDO> {

    List<PayOrderPlatformDO> findByQuery(PayOrderPlatformPageDTO query);
}