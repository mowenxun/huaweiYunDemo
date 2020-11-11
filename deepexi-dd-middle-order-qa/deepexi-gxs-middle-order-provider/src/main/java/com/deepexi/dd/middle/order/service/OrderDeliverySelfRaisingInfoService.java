package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoDTO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderDeliverySelfRaisingInfoService
 *
 * @author admin
 * @date Wed Aug 26 16:41:35 CST 2020
 * @version 1.0
 */
public interface OrderDeliverySelfRaisingInfoService {


    /**
     * 查询出库单自提地址信息表列表
     *
     * @return
     */
    List<OrderDeliverySelfRaisingInfoDTO> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoQuery query);

    /**
     * 分页查询出库单自提地址信息表列表
     *
     * @return
     */
    PageBean<OrderDeliverySelfRaisingInfoDTO> listOrderDeliverySelfRaisingInfosPage(OrderDeliverySelfRaisingInfoQuery query);

    /**
     * 根据ID删除出库单自提地址信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增出库单自提地址信息表
     *
     * @param record
     * @return
     */
    OrderDeliverySelfRaisingInfoDTO insert(OrderDeliverySelfRaisingInfoDTO record);

    /**
     * 查询出库单自提地址信息表详情
     *
     * @param id
     * @return
     */
    OrderDeliverySelfRaisingInfoDTO selectById(Long id);


    /**
     * 根据ID修改出库单自提地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderDeliverySelfRaisingInfoDTO record);

}