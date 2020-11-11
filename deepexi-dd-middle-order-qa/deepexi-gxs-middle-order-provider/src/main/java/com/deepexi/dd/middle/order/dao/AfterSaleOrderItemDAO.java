package com.deepexi.dd.middle.order.dao;


import com.deepexi.dd.middle.order.domain.AfterSaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemQuery;

import java.util.List;


/**
 * AfterSaleOrderItemDAO
 *
 * @author admin
 * @date Fri Oct 16 14:17:13 CST 2020
 * @version 1.0
 */
public interface AfterSaleOrderItemDAO {

    /**
     * 查询售后订单明细表列表}
     *
     * @return
     */
    List<AfterSaleOrderItemDO> listAfterSaleOrderItems(AfterSaleOrderItemQuery query);

    /**
     * 根据ID删除售后订单明细表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增售后订单明细表
     *
     * @param record
     * @return
     */
    int insert(AfterSaleOrderItemDO record);

    /**
     * 查询售后订单明细表详情
     *
     * @param id
     * @return
     */
    AfterSaleOrderItemDO selectById(Long id);

    /**
     * 根据ID修改售后订单明细表
     *
     * @param record
     * @return
     */
    int updateById(AfterSaleOrderItemDO record);

}