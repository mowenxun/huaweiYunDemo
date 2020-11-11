package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderStatusDO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderStatusMapper
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderStatusMapper extends BaseMapper<OrderStatusDO> {

    /**
     * 查询状态表
     *
     * @param query
     * @return
     */
    List<OrderStatusDO> findAll(OrderStatusQuery query);

    /**
     * 删除状态表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新状态表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderStatusDO> list);

    /**
     * 批量新增状态表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderStatusDO> list);
}