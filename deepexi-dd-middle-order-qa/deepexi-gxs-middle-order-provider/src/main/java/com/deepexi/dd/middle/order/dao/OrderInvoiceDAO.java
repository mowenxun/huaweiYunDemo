package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderInvoiceDO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceQuery;

import java.util.List;


/**
 * OrderInvoiceDAO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
public interface OrderInvoiceDAO extends IService<OrderInvoiceDO> {

    /**
     * 查询订单的开票信息表列表}
     *
     * @return
     */
    List<OrderInvoiceDO> listOrderInvoices(OrderInvoiceQuery query);

    /**
     * 根据ID删除订单的开票信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增订单的开票信息表
     *
     * @param record
     * @return
     */
    int insert(OrderInvoiceDO record);

    /**
     * 查询订单的开票信息表详情
     *
     * @param id
     * @return
     */
    OrderInvoiceDO selectById(Long id);

    /**
     * 根据ID修改订单的开票信息表
     *
     * @param record
     * @return
     */
    boolean updateById(OrderInvoiceDO record);

}