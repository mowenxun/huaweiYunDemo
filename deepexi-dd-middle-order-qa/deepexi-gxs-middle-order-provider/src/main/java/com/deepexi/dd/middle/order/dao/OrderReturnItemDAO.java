package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderReturnItemDO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnItemQuery;

import java.util.List;


/**
 * OrderReturnItemDAO
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
public interface OrderReturnItemDAO extends IService<OrderReturnItemDO> {

    /**
     * 查询销售订单退货明细表列表}
     *
     * @return
     */
    List<OrderReturnItemDO> listOrderReturnItems(OrderReturnItemQuery query);

    /**
     * 根据ID删除销售订单退货明细表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单退货明细表
     *
     * @param record
     * @return
     */
    int insert(OrderReturnItemDO record);

    /**
     * 查询销售订单退货明细表详情
     *
     * @param id
     * @return
     */
    OrderReturnItemDO selectById(Long id);

    /**
     * 根据ID修改销售订单退货明细表
     *
     * @param record
     * @return
     */
//    int updateById(OrderReturnItemDO record);

}