package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.ShopOrderItemDTO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationDTO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * ShopSupplerRelationService
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
public interface ShopSupplerRelationService {


    /**
     * 查询已分发订单和店铺订单关系表列表
     *
     * @return
     */
    List<ShopSupplerRelationDTO> listShopSupplerRelations(ShopSupplerRelationQuery query);

    /**
     * 分页查询已分发订单和店铺订单关系表列表
     *
     * @return
     */
    PageBean<ShopSupplerRelationDTO> listShopSupplerRelationsPage(ShopSupplerRelationQuery query);

    /**
     * 根据ID删除已分发订单和店铺订单关系表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增已分发订单和店铺订单关系表
     *
     * @param record
     * @return
     */
    ShopSupplerRelationDTO insert(ShopSupplerRelationDTO record);

    /**
     * 查询已分发订单和店铺订单关系表详情
     *
     * @param id
     * @return
     */
    ShopSupplerRelationDTO selectById(Long id);


    /**
     * 根据ID修改已分发订单和店铺订单关系表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, ShopSupplerRelationDTO record);


    boolean saveBatch(List<ShopSupplerRelationDTO> list);

}