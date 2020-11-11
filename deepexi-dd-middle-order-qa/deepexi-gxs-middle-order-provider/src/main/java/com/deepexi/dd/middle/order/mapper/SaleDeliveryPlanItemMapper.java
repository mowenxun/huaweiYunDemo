package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanItemDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleDeliveryPlanItemMapper
 *
 * @author admin
 * @date Thu Aug 13 16:42:15 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleDeliveryPlanItemMapper extends BaseMapper<SaleDeliveryPlanItemDO> {

    /**
     * 查询发货计划明细表
     *
     * @param query
     * @return
     */
    List<SaleDeliveryPlanItemDO> findAll(SaleDeliveryPlanItemQuery query);

    /**
     * 删除发货计划明细表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新发货计划明细表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleDeliveryPlanItemDO> list);

    /**
     * 批量新增发货计划明细表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleDeliveryPlanItemDO> list);
}