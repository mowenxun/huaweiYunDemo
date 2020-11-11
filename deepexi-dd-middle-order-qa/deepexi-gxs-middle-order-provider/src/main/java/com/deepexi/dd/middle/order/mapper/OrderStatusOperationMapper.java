package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderStatusOperationDO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderStatusOperationMapper
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderStatusOperationMapper extends BaseMapper<OrderStatusOperationDO> {

    /**
     * 查询订单 状态-操作表
     *
     * @param query
     * @return
     */
    List<OrderStatusOperationDO> findAll(OrderStatusOperationQuery query);

    /**
     * 删除订单 状态-操作表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新订单 状态-操作表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderStatusOperationDO> list);

    /**
     * 批量新增订单 状态-操作表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderStatusOperationDO> list);
}