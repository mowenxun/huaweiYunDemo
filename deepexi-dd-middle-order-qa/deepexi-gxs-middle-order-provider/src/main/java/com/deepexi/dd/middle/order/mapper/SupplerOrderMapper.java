package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SupplerOrderDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SupplerOrderMapper
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Mapper
public interface SupplerOrderMapper extends BaseMapper<SupplerOrderDO> {

    /**
     * 查询已分发订单
     *
     * @param query
     * @return
     */
    List<SupplerOrderDO> findAll(SupplerOrderQuery query);

    /**
     * 删除已分发订单
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新已分发订单
     *
     * @param list
     * @return
     */
    int updateBatch(List<SupplerOrderDO> list);

    /**
     * 批量新增已分发订单
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SupplerOrderDO> list);
}