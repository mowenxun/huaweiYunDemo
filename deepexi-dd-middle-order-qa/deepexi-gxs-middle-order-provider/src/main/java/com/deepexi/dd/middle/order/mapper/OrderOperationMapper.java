package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderOperationDO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderOperationMapper
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderOperationMapper extends BaseMapper<OrderOperationDO> {

    /**
     * 查询按钮表
     *
     * @param query
     * @return
     */
    List<OrderOperationDO> findAll(OrderOperationQuery query);

    /**
     * 删除按钮表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新按钮表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderOperationDO> list);

    /**
     * 批量新增按钮表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderOperationDO> list);
}