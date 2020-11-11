package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderInvoiceInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceInfoQuery;

import java.util.List;


/**
 * OrderInvoiceInfoDAO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
public interface OrderInvoiceInfoDAO extends IService<OrderInvoiceInfoDO> {

    /**
     * 查询订单的开票信息表列表}
     *
     * @return
     */
    List<OrderInvoiceInfoDO> listOrderInvoiceInfos(OrderInvoiceInfoQuery query);

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
    int insert(OrderInvoiceInfoDO record);

    /**
     * 查询订单的开票信息表详情
     *
     * @param id
     * @return
     */
    OrderInvoiceInfoDO selectById(Long id);

    /**
     * 根据ID修改订单的开票信息表
     *
     * @param record
     * @return
     */
//    int updateById(OrderInvoiceInfoDO record);

}