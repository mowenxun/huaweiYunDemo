package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SupplerOrderDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderQuery;

import java.util.List;


/**
 * SupplerOrderDAO
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
public interface SupplerOrderDAO extends IService<SupplerOrderDO> {

    /**
     * 查询已分发订单列表}
     *
     * @return
     */
    List<SupplerOrderDO> listSupplerOrders(SupplerOrderQuery query);

    /**
     * 根据ID删除已分发订单
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增已分发订单
     *
     * @param record
     * @return
     */
    int insert(SupplerOrderDO record);

    /**
     * 查询已分发订单详情
     *
     * @param id
     * @return
     */
    SupplerOrderDO selectById(Long id);

    /**
     * 根据ID修改已分发订单
     *
     * @param record
     * @return
     */
    boolean updateById(SupplerOrderDO record);

}