package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * OrderRefundSkuService
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:31:13 CST 2020
 */
public interface OrderRefundSkuService {


    /**
     * 查询列表
     *
     * @return
     */
    List<OrderRefundSkuDTO> listOrderRefundSkus(OrderRefundSkuQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    PageBean<OrderRefundSkuDTO> listOrderRefundSkusPage(OrderRefundSkuQuery query);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    OrderRefundSkuDTO insert(OrderRefundSkuDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    OrderRefundSkuDTO selectById(Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderRefundSkuDTO record);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    List<OrderRefundSkuResponseDTO> batchInsert(List<OrderRefundSkuRequestDTO> list);
}