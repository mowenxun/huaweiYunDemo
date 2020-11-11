package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesDO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesQuery;

import java.util.List;


/**
 * SaleDeliveryGoodsPlanRulesDAO
 *
 * @author admin
 * @date Wed Aug 12 14:26:43 CST 2020
 * @version 1.0
 */
public interface SaleDeliveryGoodsPlanRulesDAO {

    /**
     * 查询发货计划编排规则列表}
     *
     * @return
     */
    List<SaleDeliveryGoodsPlanRulesDO> listSaleDeliveryGoodsPlanRules(SaleDeliveryGoodsPlanRulesQuery query);

    /**
     * 根据ID删除发货计划编排规则
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增发货计划编排规则
     *
     * @param record
     * @return
     */
    int insert(SaleDeliveryGoodsPlanRulesDO record);

    /**
     * 查询发货计划编排规则详情
     *
     * @param id
     * @return
     */
    SaleDeliveryGoodsPlanRulesDO selectById(Long id);

    /**
     * 根据ID修改发货计划编排规则
     *
     * @param record
     * @return
     */
    int updateById(SaleDeliveryGoodsPlanRulesDO record);

}