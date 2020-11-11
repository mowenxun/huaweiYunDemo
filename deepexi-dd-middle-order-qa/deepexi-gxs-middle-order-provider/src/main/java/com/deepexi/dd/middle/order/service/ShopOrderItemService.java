package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.ShopOrderItemDO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDTO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * ShopOrderItemService
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
public interface ShopOrderItemService {


    /**
     * 查询店铺订单明细表列表
     *
     * @return
     */
    List<ShopOrderItemDTO> listShopOrderItems(ShopOrderItemQuery query);

    /**
     * 分页查询店铺订单明细表列表
     *
     * @return
     */
    PageBean<ShopOrderItemDTO> listShopOrderItemsPage(ShopOrderItemQuery query);

    /**
     * 根据ID删除店铺订单明细表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增店铺订单明细表
     *
     * @param record
     * @return
     */
    ShopOrderItemDTO insert(ShopOrderItemDTO record);

    boolean saveBatch(List<ShopOrderItemDTO> list);

    /**
     * 查询店铺订单明细表详情
     *
     * @param id
     * @return
     */
    ShopOrderItemDTO selectById(Long id);


    /**
     * 根据ID修改店铺订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, ShopOrderItemDTO record);

}