package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemPageDTO;

import java.util.List;

/**
 *
 * @author huanghuai
 * @since 2020-10-13
 */
public interface PayOrderPlatformItemMiddleService {

    /**
     * 创建
     */
    PayOrderPlatformItemDTO insert(PayOrderPlatformItemDTO dto);

    /**
     * 批量创建
     */
    boolean createBatch(List<PayOrderPlatformItemDTO> list);

    /**
     * 批量删除
     */
    boolean deleteByIds(List<Long> ids);
    /**
     * 删除
     */
    boolean deleteById(Long id);

    /**
     * 分页条件查询
     */
    List<PayOrderPlatformItemDTO> listPage(PayOrderPlatformItemPageDTO query);
    /**
     * 分页条件查询
     */
    List<PayOrderPlatformItemDTO> list(PayOrderPlatformItemDTO query);

    /**
     * 获取详情
     */
    PayOrderPlatformItemDTO selectById(Long id);

    /**
     * 更新
     */
    boolean updateById(PayOrderPlatformItemDTO dto);
}
