package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.OrderPayBackDTO;
import com.deepexi.dd.middle.order.domain.OrderPayBackQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderPayBackService
 *
 * @author admin
 * @date Wed Jul 22 16:27:51 CST 2020
 * @version 1.0
 */
public interface OrderPayBackService {


    /**
     * 查询支付回调参数表列表
     *
     * @return
     */
    List<OrderPayBackDTO> listOrderPayBacks(OrderPayBackQuery query);

    /**
     * 分页查询支付回调参数表列表
     *
     * @return
     */
    PageBean<OrderPayBackDTO> listOrderPayBacksPage(OrderPayBackQuery query);

    /**
     * 根据ID删除支付回调参数表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增支付回调参数表
     *
     * @param record
     * @return
     */
    OrderPayBackDTO insert(OrderPayBackDTO record);

    /**
     * 查询支付回调参数表详情
     *
     * @param id
     * @return
     */
    OrderPayBackDTO selectById(Long id);


    /**
     * 根据ID修改支付回调参数表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderPayBackDTO record);

}