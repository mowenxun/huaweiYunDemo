package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan.SaleDeliveryPlanRulesRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan.SaleDeliveryPlanRulesResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleDeliveryPlanRulesRequestQuery;
import com.deepexi.dd.domain.transaction.remote.order.SaleDeliveryGoodsPlanRulesClient;
import com.deepexi.dd.domain.transaction.service.SaleDeliveryPlanService;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryGoodsPlanRulesRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName SaleDeliveryPlanServiceImpl
 * @Description 发货计划
 * @Author SongTao
 * @Date 2020-08-12
 * @Version 1.0
 **/
@Service
@Slf4j
public class SaleDeliveryPlanServiceImpl implements SaleDeliveryPlanService {

    @Autowired
    private SaleDeliveryGoodsPlanRulesClient saleDeliveryGoodsPlanRulesClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;



    /**
     * @param query
     * @Description: 分页查询发货计划编排规则.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan.SaleDeliveryPlanRulesResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/12
     */
    @Override
    public PageBean<SaleDeliveryPlanRulesResponseDTO> saleDeliveryGoodsPlanRules(SaleDeliveryPlanRulesRequestQuery query) throws Exception {
        PageBean<SaleDeliveryGoodsPlanRulesResponseDTO> pageBean = saleDeliveryGoodsPlanRulesClient.listSaleDeliveryGoodsPlanRulesPage(query.clone(SaleDeliveryGoodsPlanRulesRequestQuery.class));
        return GeneralConvertUtils.convert2PageBean(pageBean,SaleDeliveryPlanRulesResponseDTO.class);
    }

    /**
     * @param requestDTO
     * @Description: 新增发货计划编排规则.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/12
     */
    @Override
    public Boolean insertDeliveryGoodsPlanRules(SaleDeliveryPlanRulesRequestDTO requestDTO) throws Exception {
        SaleDeliveryGoodsPlanRulesRequestDTO saleDeliveryGoodsPlanRulesRequestDTO = requestDTO.clone(SaleDeliveryGoodsPlanRulesRequestDTO.class);
        saleDeliveryGoodsPlanRulesRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
        saleDeliveryGoodsPlanRulesRequestDTO.setUpdatedBy(appRuntimeEnv.getUsername());
        SaleDeliveryGoodsPlanRulesResponseDTO dto = saleDeliveryGoodsPlanRulesClient.insert(saleDeliveryGoodsPlanRulesRequestDTO);
        return Objects.nonNull(dto) ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * @param id
     * @param requestDTO
     * @Description: 修改发货计划编排规则.
     * @Param: [id, requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/12
     */
    @Override
    public Boolean updateDeliveryGoodsPlanRules(Long id, SaleDeliveryPlanRulesRequestDTO requestDTO) throws Exception {
        SaleDeliveryGoodsPlanRulesRequestDTO saleDeliveryGoodsPlanRulesRequestDTO = requestDTO.clone(SaleDeliveryGoodsPlanRulesRequestDTO.class);
        saleDeliveryGoodsPlanRulesRequestDTO.setUpdatedBy(appRuntimeEnv.getUsername());
        return saleDeliveryGoodsPlanRulesClient.updateById(id, saleDeliveryGoodsPlanRulesRequestDTO);
    }

    /**
     * @param id
     * @Description: 删除发货计划编排规则.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/12
     */
    @Override
    public Boolean deleteDeliveryGoodsPlanRules(List<Long> id) throws Exception{
        return saleDeliveryGoodsPlanRulesClient.deleteByIdIn(id);
    }
}
