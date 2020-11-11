package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.ShopOrderDO;
import com.deepexi.dd.middle.order.domain.ShopOrderQuery;

import java.util.List;


/**
 * ShopOrderDAO
 *
 * @author admin
 * @date Tue Oct 13 14:53:15 CST 2020
 * @version 1.0
 */
public interface ShopOrderDAO extends IService<ShopOrderDO> {

    /**
     * 查询店铺订单列表}
     *
     * @return
     */
    List<ShopOrderDO> listShopOrders(ShopOrderQuery query);

    /**
     * 根据ID删除店铺订单
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增店铺订单
     *
     * @param record
     * @return
     */
    int insert(ShopOrderDO record);

    /**
     * 查询店铺订单详情
     *
     * @param id
     * @return
     */
    ShopOrderDO selectById(Long id);

    /**
     * 根据ID修改店铺订单
     *
     * @param record
     * @return
     */
    boolean updateById(ShopOrderDO record);

    /**
     * 根据code修改
     * @param shopOrderDos
     * @return
     */
    Boolean updateByCode(List<ShopOrderDO> shopOrderDos);
}