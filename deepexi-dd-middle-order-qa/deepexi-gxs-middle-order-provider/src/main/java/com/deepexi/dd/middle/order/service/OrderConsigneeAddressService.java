package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressDTO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderConsigneeAddressService
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
public interface OrderConsigneeAddressService {


    /**
     * 查询订单收货地址信息表列表
     *
     * @return
     */
    List<OrderConsigneeAddressDTO> listOrderConsigneeAddresss(OrderConsigneeAddressQuery query);

    /**
     * 分页查询订单收货地址信息表列表
     *
     * @return
     */
    PageBean<OrderConsigneeAddressDTO> listOrderConsigneeAddresssPage(OrderConsigneeAddressQuery query);

    /**
     * 根据ID删除订单收货地址信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增订单收货地址信息表
     *
     * @param record
     * @return
     */
    OrderConsigneeAddressDTO insert(OrderConsigneeAddressDTO record);

    /**
     * 查询订单收货地址信息表详情
     *
     * @param id
     * @return
     */
    OrderConsigneeAddressDTO selectById(Long id);


    /**
     * 根据ID修改订单收货地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderConsigneeAddressDTO record);

}