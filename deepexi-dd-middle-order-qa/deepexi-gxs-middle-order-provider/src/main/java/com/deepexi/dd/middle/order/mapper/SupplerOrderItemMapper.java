package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SupplerOrderItemMapper
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Mapper
public interface SupplerOrderItemMapper extends BaseMapper<SupplerOrderItemDO> {

    /**
     * 查询已分发订单明细表
     *
     * @param query
     * @return
     */
    List<SupplerOrderItemDO> findAll(SupplerOrderItemQuery query);

    /**
     * 删除已分发订单明细表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新已分发订单明细表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SupplerOrderItemDO> list);

    /**
     * 批量新增已分发订单明细表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SupplerOrderItemDO> list);
}