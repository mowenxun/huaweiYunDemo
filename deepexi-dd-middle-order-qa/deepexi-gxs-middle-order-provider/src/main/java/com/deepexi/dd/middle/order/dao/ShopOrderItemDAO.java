package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemQuery;

import java.util.List;


/**
 * ShopOrderItemDAO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
public interface ShopOrderItemDAO extends IService<ShopOrderItemDO> {

    /**
     * 查询店铺订单明细表列表}
     *
     * @return
     */
    List<ShopOrderItemDO> listShopOrderItems(ShopOrderItemQuery query);

    /**
     * 根据ID删除店铺订单明细表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增店铺订单明细表
     *
     * @param record
     * @return
     */
    int insert(ShopOrderItemDO record);



    /**
     * 查询店铺订单明细表详情
     *
     * @param id
     * @return
     */
    ShopOrderItemDO selectById(Long id);

    /**
     * 根据ID修改店铺订单明细表
     *
     * @param record
     * @return
     */
    boolean updateById(ShopOrderItemDO record);

}