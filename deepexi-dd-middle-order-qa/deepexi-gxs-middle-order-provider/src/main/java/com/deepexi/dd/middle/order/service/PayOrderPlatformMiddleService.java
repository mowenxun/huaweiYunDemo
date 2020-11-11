package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformPageDTO;

import java.util.List;

/**
 *
 * @author huanghuai
 * @since 2020-10-13
 */
public interface PayOrderPlatformMiddleService {

    /**
     * 创建
     */
    PayOrderPlatformDTO insert(PayOrderPlatformDTO dto);

    /**
     * 批量创建
     */
    boolean createBatch(List<PayOrderPlatformRequestDTO> list);

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
    List<PayOrderPlatformRequestDTO> listPage(PayOrderPlatformPageDTO query);
    /**
     * 分页条件查询
     */
    List<PayOrderPlatformRequestDTO> list(PayOrderPlatformRequestDTO query);

    /**
     * 获取详情
     * @return
     */
    PayOrderPlatformDTO selectById(Long id);

    /**
     * 更新
     */
    boolean updateById(PayOrderPlatformDTO dto);
}
