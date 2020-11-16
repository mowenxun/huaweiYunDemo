package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan.SaleDeliveryPlanRulesRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan.SaleDeliveryPlanRulesResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleDeliveryPlanRulesRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @ClassName SaleDeliveryPlanService
 * @Description 发货计划
 * @Author SongTao
 * @Date 2020-08-12
 * @Version 1.0
 **/
public interface SaleDeliveryPlanService {
    /**
     * @Description:  分页查询发货计划编排规则.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan.SaleDeliveryPlanRulesResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/12
     */
    PageBean<SaleDeliveryPlanRulesResponseDTO> saleDeliveryGoodsPlanRules(SaleDeliveryPlanRulesRequestQuery query) throws Exception;
    /**
     * @Description:  新增发货计划编排规则.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/12
     */
    Boolean insertDeliveryGoodsPlanRules(SaleDeliveryPlanRulesRequestDTO requestDTO) throws Exception;
    /**
     * @Description:  修改发货计划编排规则.
     * @Param: [id, requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/12
     */
    Boolean updateDeliveryGoodsPlanRules(Long id, SaleDeliveryPlanRulesRequestDTO requestDTO) throws Exception;
    /**
     * @Description:  删除发货计划编排规则.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/12
     */
    Boolean deleteDeliveryGoodsPlanRules(List<Long> id) throws Exception;

}
