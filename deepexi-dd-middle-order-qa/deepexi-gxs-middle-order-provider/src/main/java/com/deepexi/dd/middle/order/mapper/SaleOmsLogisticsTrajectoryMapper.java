package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryDO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleOmsLogisticsTrajectoryMapper
 *
 * @author admin
 * @date Tue Aug 25 16:23:34 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleOmsLogisticsTrajectoryMapper extends BaseMapper<SaleOmsLogisticsTrajectoryDO> {

    /**
     * 查询OMS物流轨迹
     *
     * @param query
     * @return
     */
    List<SaleOmsLogisticsTrajectoryDO> findAll(SaleOmsLogisticsTrajectoryQuery query);

    /**
     * 删除OMS物流轨迹
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新OMS物流轨迹
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleOmsLogisticsTrajectoryDO> list);

    /**
     * 批量新增OMS物流轨迹
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOmsLogisticsTrajectoryDO> list);
}