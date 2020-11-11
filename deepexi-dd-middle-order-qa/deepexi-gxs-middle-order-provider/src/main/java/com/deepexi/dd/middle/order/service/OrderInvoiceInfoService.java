package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderInvoiceInfoService
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
public interface OrderInvoiceInfoService {


    /**
     * 查询订单的开票信息表列表
     *
     * @return
     */
    List<OrderInvoiceInfoDTO> listOrderInvoiceInfos(OrderInvoiceInfoQuery query);

    /**
     * 分页查询订单的开票信息表列表
     *
     * @return
     */
    PageBean<OrderInvoiceInfoDTO> listOrderInvoiceInfosPage(OrderInvoiceInfoQuery query);

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
    OrderInvoiceInfoDTO insert(OrderInvoiceInfoDTO record);

    /**
     * 查询订单的开票信息表详情
     *
     * @param id
     * @return
     */
    OrderInvoiceInfoDTO selectById(Long id);


    /**
     * 根据ID修改订单的开票信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderInvoiceInfoDTO record);

}