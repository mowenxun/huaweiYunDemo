package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderExpenseInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderExpenseInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderExpenseInfoService
 *
 * @author admin
 * @date Wed Jun 24 09:42:04 CST 2020
 * @version 1.0
 */
public interface OrderExpenseInfoService {


    /**
     * 查询销售订单的费用信息表列表
     *
     * @return
     */
    List<OrderExpenseInfoDTO> listOrderExpenseInfos(OrderExpenseInfoQuery query);

    /**
     * 分页查询销售订单的费用信息表列表
     *
     * @return
     */
    PageBean<OrderExpenseInfoDTO> listOrderExpenseInfosPage(OrderExpenseInfoQuery query);

    /**
     * 根据ID删除销售订单的费用信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单的费用信息表
     *
     * @param record
     * @return
     */
    OrderExpenseInfoDTO insert(OrderExpenseInfoDTO record);

    /**
     * 查询销售订单的费用信息表详情
     *
     * @param id
     * @return
     */
    OrderExpenseInfoDTO selectById(Long id);


    /**
     * 根据ID修改销售订单的费用信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderExpenseInfoDTO record);

}