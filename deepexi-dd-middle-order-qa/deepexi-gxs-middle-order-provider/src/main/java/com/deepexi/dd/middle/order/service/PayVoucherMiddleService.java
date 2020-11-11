package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.PayVoucherDTO;
import com.deepexi.dd.middle.order.domain.query.PayVoucherPageDTO;

import java.util.List;

/**
 *
 * @author huanghuai
 * @since 2020-10-13
 */
public interface PayVoucherMiddleService {

    /**
     * 创建
     */
    PayVoucherDTO insert(PayVoucherDTO dto);

    /**
     * 批量创建
     */
    boolean createBatch(List<PayVoucherDTO> list);

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
    List<PayVoucherDTO> listPage(PayVoucherPageDTO query);
    /**
     * 分页条件查询
     */
    List<PayVoucherDTO> list(PayVoucherDTO query);

    /**
     * 获取详情
     */
    PayVoucherDTO selectById(Long id);

    /**
     * 更新
     */
    boolean updateById(PayVoucherDTO dto);
}
