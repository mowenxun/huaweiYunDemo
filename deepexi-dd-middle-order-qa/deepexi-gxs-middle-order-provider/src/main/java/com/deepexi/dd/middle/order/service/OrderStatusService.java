package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderStatusDTO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderStatusService
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
public interface OrderStatusService {


    /**
     * 查询状态表列表
     *
     * @return
     */
    List<OrderStatusDTO> listOrderStatuss(OrderStatusQuery query);

    /**
     * 分页查询状态表列表
     *
     * @return
     */
    PageBean<OrderStatusDTO> listOrderStatussPage(OrderStatusQuery query);

    /**
     * 根据ID删除状态表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增状态表
     *
     * @param record
     * @return
     */
    OrderStatusDTO insert(OrderStatusDTO record);

    /**
     * 查询状态表详情
     *
     * @param id
     * @return
     */
    OrderStatusDTO selectById(Long id);


    /**
     * 根据ID修改状态表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderStatusDTO record);

}