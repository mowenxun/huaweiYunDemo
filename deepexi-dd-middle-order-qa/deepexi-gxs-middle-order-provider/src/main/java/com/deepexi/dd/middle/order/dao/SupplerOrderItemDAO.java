package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemQuery;

import java.util.List;


/**
 * SupplerOrderItemDAO
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
public interface SupplerOrderItemDAO extends IService<SupplerOrderItemDO> {

    /**
     * 查询已分发订单明细表列表}
     *
     * @return
     */
    List<SupplerOrderItemDO> listSupplerOrderItems(SupplerOrderItemQuery query);

    /**
     * 根据ID删除已分发订单明细表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增已分发订单明细表
     *
     * @param record
     * @return
     */
    int insert(SupplerOrderItemDO record);

    /**
     * 查询已分发订单明细表详情
     *
     * @param id
     * @return
     */
    SupplerOrderItemDO selectById(Long id);

    /**
     * 根据ID修改已分发订单明细表
     *
     * @param record
     * @return
     */
    boolean updateById(SupplerOrderItemDO record);

}