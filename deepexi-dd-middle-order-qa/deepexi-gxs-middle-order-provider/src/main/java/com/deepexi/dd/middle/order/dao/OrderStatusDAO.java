package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.OrderStatusDO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusQuery;

import java.util.List;


/**
 * OrderStatusDAO
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
public interface OrderStatusDAO {

    /**
     * 查询状态表列表}
     *
     * @return
     */
    List<OrderStatusDO> listOrderStatuss(OrderStatusQuery query);

    /**
     * 根据ID删除状态表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增状态表
     *
     * @param record
     * @return
     */
    int insert(OrderStatusDO record);

    /**
     * 查询状态表详情
     *
     * @param id
     * @return
     */
    OrderStatusDO selectById(Long id);

    /**
     * 根据ID修改状态表
     *
     * @param record
     * @return
     */
    int updateById(OrderStatusDO record);

}