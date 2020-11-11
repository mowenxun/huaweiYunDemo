package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.OrderPayBackDO;
import com.deepexi.dd.middle.order.domain.OrderPayBackQuery;

import java.util.List;


/**
 * OrderPayBackDAO
 *
 * @author admin
 * @date Wed Jul 22 16:27:51 CST 2020
 * @version 1.0
 */
public interface OrderPayBackDAO {

    /**
     * 查询支付回调参数表列表}
     *
     * @return
     */
    List<OrderPayBackDO> listOrderPayBacks(OrderPayBackQuery query);

    /**
     * 根据ID删除支付回调参数表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增支付回调参数表
     *
     * @param record
     * @return
     */
    int insert(OrderPayBackDO record);

    /**
     * 查询支付回调参数表详情
     *
     * @param id
     * @return
     */
    OrderPayBackDO selectById(Long id);

    /**
     * 根据ID修改支付回调参数表
     *
     * @param record
     * @return
     */
    int updateById(OrderPayBackDO record);

}