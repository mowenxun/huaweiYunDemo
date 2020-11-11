package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationDO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationQuery;

import java.util.List;


/**
 * ShopSupplerRelationDAO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
public interface ShopSupplerRelationDAO extends IService<ShopSupplerRelationDO> {

    /**
     * 查询已分发订单和店铺订单关系表列表}
     *
     * @return
     */
    List<ShopSupplerRelationDO> listShopSupplerRelations(ShopSupplerRelationQuery query);

    /**
     * 根据ID删除已分发订单和店铺订单关系表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增已分发订单和店铺订单关系表
     *
     * @param record
     * @return
     */
    int insert(ShopSupplerRelationDO record);

    /**
     * 查询已分发订单和店铺订单关系表详情
     *
     * @param id
     * @return
     */
    ShopSupplerRelationDO selectById(Long id);

    /**
     * 根据ID修改已分发订单和店铺订单关系表
     *
     * @param record
     * @return
     */
    boolean updateById(ShopSupplerRelationDO record);

}