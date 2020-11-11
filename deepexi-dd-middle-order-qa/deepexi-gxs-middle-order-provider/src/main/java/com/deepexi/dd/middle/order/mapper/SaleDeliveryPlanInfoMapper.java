package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanInfoDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleDeliveryPlanInfoMapper
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleDeliveryPlanInfoMapper extends BaseMapper<SaleDeliveryPlanInfoDO> {

    /**
     * 查询发货计划表
     *
     * @param query
     * @return
     */
    List<SaleDeliveryPlanInfoDO> findAll(SaleDeliveryPlanInfoQuery query);

    /**
     * 删除发货计划表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新发货计划表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleDeliveryPlanInfoDO> list);

    /**
     * 批量新增发货计划表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleDeliveryPlanInfoDO> list);
}