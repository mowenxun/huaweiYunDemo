package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.ShopOrderItemDTO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemDTO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SupplerOrderItemService
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
public interface SupplerOrderItemService {


    /**
     * 查询已分发订单明细表列表
     *
     * @return
     */
    List<SupplerOrderItemDTO> listSupplerOrderItems(SupplerOrderItemQuery query);

    /**
     * 分页查询已分发订单明细表列表
     *
     * @return
     */
    PageBean<SupplerOrderItemDTO> listSupplerOrderItemsPage(SupplerOrderItemQuery query);

    /**
     * 根据ID删除已分发订单明细表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增已分发订单明细表
     *
     * @param record
     * @return
     */
    SupplerOrderItemDTO insert(SupplerOrderItemDTO record);

    /**
     * 查询已分发订单明细表详情
     *
     * @param id
     * @return
     */
    SupplerOrderItemDTO selectById(Long id);


    /**
     * 根据ID修改已分发订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SupplerOrderItemDTO record);


    /**
     *
     * @param list
     * @return
     */
    boolean saveBatch(List<SupplerOrderItemDTO> list);

    boolean updateBatchById(List<SupplerOrderItemDTO> list);



}