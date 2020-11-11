package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.OrderInvoiceDTO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderInvoiceService
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
public interface OrderInvoiceService {


    /**
     * 查询订单的开票信息表列表
     *
     * @return
     */
    List<OrderInvoiceDTO> listOrderInvoices(OrderInvoiceQuery query);

    /**
     * 分页查询订单的开票信息表列表
     *
     * @return
     */
    PageBean<OrderInvoiceDTO> listOrderInvoicesPage(OrderInvoiceQuery query);

    /**
     * 根据ID删除订单的开票信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增订单的开票信息表
     *
     * @param record
     * @return
     */
    OrderInvoiceDTO insert(OrderInvoiceDTO record);

    /**
     * 查询订单的开票信息表详情
     *
     * @param id
     * @return
     */
    OrderInvoiceDTO selectById(Long id);


    /**
     * 根据ID修改订单的开票信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderInvoiceDTO record);

}