package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesDO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleDeliveryGoodsPlanRulesMapper
 *
 * @author admin
 * @date Wed Aug 12 14:26:43 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleDeliveryGoodsPlanRulesMapper extends BaseMapper<SaleDeliveryGoodsPlanRulesDO> {

    /**
     * 查询发货计划编排规则
     *
     * @param query
     * @return
     */
    List<SaleDeliveryGoodsPlanRulesDO> findAll(SaleDeliveryGoodsPlanRulesQuery query);

    /**
     * 删除发货计划编排规则
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新发货计划编排规则
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleDeliveryGoodsPlanRulesDO> list);

    /**
     * 批量新增发货计划编排规则
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleDeliveryGoodsPlanRulesDO> list);
}