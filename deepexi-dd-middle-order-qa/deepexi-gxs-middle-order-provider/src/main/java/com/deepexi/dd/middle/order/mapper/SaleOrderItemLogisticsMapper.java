package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOrderItemLogisticsDO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemLogisticsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SaleOrderItemLogisticsMapper
 *
 * @author admin
 * @date Sat Aug 22 16:34:04 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleOrderItemLogisticsMapper extends BaseMapper<SaleOrderItemLogisticsDO> {

    /**
     * 查询OMS物流信息
     *
     * @param query
     * @return
     */
    List<SaleOrderItemLogisticsDO> findAll(SaleOrderItemLogisticsQuery query);

    /**
     * 删除OMS物流信息
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新OMS物流信息
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleOrderItemLogisticsDO> list);

    /**
     * 批量新增OMS物流信息
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOrderItemLogisticsDO> list);
}