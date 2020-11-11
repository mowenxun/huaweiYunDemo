package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoAddRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderReturnInfoService
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
public interface OrderReturnInfoService {


    /**
     * 查询销售订单退货单信息表列表
     *
     * @return
     */
    List<OrderReturnInfoDTO> listOrderReturnInfos(OrderReturnInfoQuery query);

    /**
     * 分页查询销售订单退货单信息表列表
     *
     * @return
     */
    PageBean<OrderReturnInfoDTO> listOrderReturnInfosPage(OrderReturnInfoQuery query);

    /**
     * 根据ID删除销售订单退货单信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单退货单信息表
     *
     * @param record
     * @return
     */
    OrderReturnInfoDTO insert(OrderReturnInfoAddRequestDTO record);

    /**
     * 查询销售订单退货单信息表详情
     *
     * @param id
     * @return
     */
    OrderReturnInfoDTO selectById(Long id);


    /**
     * 根据ID修改销售订单退货单信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderReturnInfoDTO record);

}