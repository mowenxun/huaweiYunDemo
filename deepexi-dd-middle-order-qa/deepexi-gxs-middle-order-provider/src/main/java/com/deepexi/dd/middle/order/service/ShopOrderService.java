package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.ShopOrderDTO;
import com.deepexi.dd.middle.order.domain.ShopOrderQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * ShopOrderService
 *
 * @author admin
 * @date Tue Oct 13 14:53:15 CST 2020
 * @version 1.0
 */
public interface ShopOrderService {


    /**
     * 查询店铺订单列表
     *
     * @return
     */
    List<ShopOrderDTO> listShopOrders(ShopOrderQuery query);

    /**
     * 分页查询店铺订单列表
     *
     * @return
     */
    PageBean<ShopOrderDTO> listShopOrdersPage(ShopOrderQuery query);

    /**
     * 根据ID删除店铺订单
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增店铺订单
     *
     * @param record
     * @return
     */
    ShopOrderDTO insert(ShopOrderDTO record);

    /**
     * 查询店铺订单详情
     *
     * @param id
     * @return
     */
    ShopOrderDTO selectById(Long id);


    /**
     * 根据ID修改店铺订单
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, ShopOrderDTO record);

    /**
     * 根据code修改店铺订单
     * @param records
     * @return
     */
    Boolean updateByCode(List<ShopOrderDTO> records);
}