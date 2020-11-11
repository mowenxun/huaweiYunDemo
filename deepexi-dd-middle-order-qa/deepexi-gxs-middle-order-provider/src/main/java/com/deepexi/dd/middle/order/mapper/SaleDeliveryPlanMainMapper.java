package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanMainDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanMainQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SaleDeliveryPlanMainMapper
 *
 * @author admin
 * @version 1.0
 * @date Thu Aug 13 15:26:43 CST 2020
 */
@Mapper
public interface SaleDeliveryPlanMainMapper extends BaseMapper<SaleDeliveryPlanMainDO> {

    /**
     * 查询发货编排主表
     *
     * @param query
     * @return
     */
    List<SaleDeliveryPlanMainDO> findAll(SaleDeliveryPlanMainQuery query);

    /**
     * 删除发货编排主表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新发货编排主表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleDeliveryPlanMainDO> list);

    /**
     * 批量新增发货编排主表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleDeliveryPlanMainDO> list);
}