package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SupplerOrderDTO;
import com.deepexi.dd.middle.order.domain.SupplerOrderQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SupplerOrderService
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
public interface SupplerOrderService {


    /**
     * 查询已分发订单列表
     *
     * @return
     */
    List<SupplerOrderDTO> listSupplerOrders(SupplerOrderQuery query);

    /**
     * 分页查询已分发订单列表
     *
     * @return
     */
    PageBean<SupplerOrderDTO> listSupplerOrdersPage(SupplerOrderQuery query);

    /**
     * 根据ID删除已分发订单
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增已分发订单
     *
     * @param record
     * @return
     */
    SupplerOrderDTO insert(SupplerOrderDTO record);

    /**
     * 查询已分发订单详情
     *
     * @param id
     * @return
     */
    SupplerOrderDTO selectById(Long id);


    /**
     * 根据ID修改已分发订单
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SupplerOrderDTO record);

}