package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderReturnItemDO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnItemQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderReturnItemMapper
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderReturnItemMapper extends BaseMapper<OrderReturnItemDO> {

    /**
     * 查询销售订单退货明细表
     *
     * @param query
     * @return
     */
    List<OrderReturnItemDO> findAll(OrderReturnItemQuery query);

    /**
     * 删除销售订单退货明细表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售订单退货明细表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderReturnItemDO> list);

    /**
     * 批量新增销售订单退货明细表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderReturnItemDO> list);
}