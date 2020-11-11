package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressDO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressQuery;

import java.util.List;


/**
 * OrderConsigneeAddressDAO
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
public interface OrderConsigneeAddressDAO extends IService<OrderConsigneeAddressDO> {

    /**
     * 查询订单收货地址信息表列表}
     *
     * @return
     */
    List<OrderConsigneeAddressDO> listOrderConsigneeAddresss(OrderConsigneeAddressQuery query);

    /**
     * 根据ID删除订单收货地址信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增订单收货地址信息表
     *
     * @param record
     * @return
     */
    int insert(OrderConsigneeAddressDO record);

    /**
     * 查询订单收货地址信息表详情
     *
     * @param id
     * @return
     */
    OrderConsigneeAddressDO selectById(Long id);

    /**
     * 根据ID修改订单收货地址信息表
     *
     * @param record
     * @return
     */
    boolean updateById(OrderConsigneeAddressDO record);

}