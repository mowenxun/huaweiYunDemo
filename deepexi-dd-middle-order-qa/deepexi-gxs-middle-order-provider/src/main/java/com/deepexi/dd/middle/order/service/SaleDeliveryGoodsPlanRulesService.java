package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesDTO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleDeliveryGoodsPlanRulesService
 *
 * @author admin
 * @date Wed Aug 12 14:26:43 CST 2020
 * @version 1.0
 */
public interface SaleDeliveryGoodsPlanRulesService {


    /**
     * 查询发货计划编排规则列表
     *
     * @return
     */
    List<SaleDeliveryGoodsPlanRulesDTO> listSaleDeliveryGoodsPlanRules(SaleDeliveryGoodsPlanRulesQuery query) throws Exception;

    /**
     * 分页查询发货计划编排规则列表
     *
     * @return
     */
    PageBean<SaleDeliveryGoodsPlanRulesDTO> listSaleDeliveryGoodsPlanRulesPage(SaleDeliveryGoodsPlanRulesQuery query) throws Exception;

    /**
     * 根据ID删除发货计划编排规则
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id) throws Exception;

    /**
     * 新增发货计划编排规则
     *
     * @param record
     * @return
     */
    SaleDeliveryGoodsPlanRulesDTO insert(SaleDeliveryGoodsPlanRulesDTO record) throws Exception;

    /**
     * 查询发货计划编排规则详情
     *
     * @param id
     * @return
     */
    SaleDeliveryGoodsPlanRulesDTO selectById(Long id) throws Exception;


    /**
     * 根据ID修改发货计划编排规则
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleDeliveryGoodsPlanRulesDTO record) throws Exception;

}