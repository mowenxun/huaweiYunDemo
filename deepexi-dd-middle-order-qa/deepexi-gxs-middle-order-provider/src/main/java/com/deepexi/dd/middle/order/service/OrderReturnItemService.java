package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemDTO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnItemQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderReturnItemService
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
public interface OrderReturnItemService {


    /**
     * 查询销售订单退货明细表列表
     *
     * @return
     */
    List<OrderReturnItemDTO> listOrderReturnItems(OrderReturnItemQuery query);

    /**
     * 分页查询销售订单退货明细表列表
     *
     * @return
     */
    PageBean<OrderReturnItemDTO> listOrderReturnItemsPage(OrderReturnItemQuery query);

    /**
     * 根据ID删除销售订单退货明细表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单退货明细表
     *
     * @param record
     * @return
     */
    OrderReturnItemDTO insert(OrderReturnItemDTO record);

    /**
     * 查询销售订单退货明细表详情
     *
     * @param id
     * @return
     */
    OrderReturnItemDTO selectById(Long id);


    /**
     * 根据ID修改销售订单退货明细表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderReturnItemDTO record);

}