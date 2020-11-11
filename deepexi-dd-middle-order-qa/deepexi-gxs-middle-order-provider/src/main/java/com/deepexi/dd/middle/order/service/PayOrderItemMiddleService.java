package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.PayOrderItemDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemPageDTO;

import java.util.List;

/**
 *
 * @author huanghuai
 * @since 2020-10-13
 */
public interface PayOrderItemMiddleService {

    /**
     * 创建
     */
    PayOrderItemDTO insert(PayOrderItemDTO dto);

    /**
     * 批量创建
     */
    boolean createBatch(List<PayOrderItemDTO> list);

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
    List<PayOrderItemDTO> listPage(PayOrderItemPageDTO query);
    /**
     * 分页条件查询
     */
    List<PayOrderItemDTO> list(PayOrderItemDTO query);

    /**
     * 获取详情
     */
    PayOrderItemDTO selectById(Long id);

    /**
     * 更新
     */
    boolean updateById(PayOrderItemDTO dto);
}
