package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * AfterSaleOrderItemService
 *
 * @author admin
 * @date Fri Oct 16 14:17:13 CST 2020
 * @version 1.0
 */
public interface AfterSaleOrderItemService {


    /**
     * 查询售后订单明细表列表
     *
     * @return
     */
    List<AfterSaleOrderItemDTO> listAfterSaleOrderItems(AfterSaleOrderItemQuery query);

    /**
     * 分页查询售后订单明细表列表
     *
     * @return
     */
    PageBean<AfterSaleOrderItemDTO> listAfterSaleOrderItemsPage(AfterSaleOrderItemQuery query);

    /**
     * 根据ID删除售后订单明细表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增售后订单明细表
     *
     * @param record
     * @return
     */
    AfterSaleOrderItemDTO insert(AfterSaleOrderItemDTO record);

    /**
     * 查询售后订单明细表详情
     *
     * @param id
     * @return
     */
    AfterSaleOrderItemDTO selectById(Long id);


    /**
     * 根据ID修改售后订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, AfterSaleOrderItemDTO record);

}