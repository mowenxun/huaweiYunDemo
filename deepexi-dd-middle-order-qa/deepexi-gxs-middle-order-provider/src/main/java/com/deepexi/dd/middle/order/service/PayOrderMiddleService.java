package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.PayOrderDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPageDTO;

import java.util.List;

/**
 *
 * @author huanghuai
 * @since 2020-10-13
 */
public interface PayOrderMiddleService {

    /**
     * 创建
     */
    PayOrderDTO insert(PayOrderDTO dto);

    /**
     * 批量创建
     */
    boolean createBatch(List<PayOrderRequestDTO> list);

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
    List<PayOrderRequestDTO> listPage(PayOrderPageDTO query);
    /**
     * 分页条件查询
     */
    List<PayOrderRequestDTO> list(PayOrderRequestDTO query);

    /**
     * 获取详情
     * @return
     */
    PayOrderDTO selectById(Long id);

    /**
     * 更新
     */
    boolean updateById(PayOrderDTO dto);
}
