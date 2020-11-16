package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.tool.domain.dto.BaseVO;
import com.deepexi.dd.domain.transaction.domain.dto.RuleControl;
import com.deepexi.dd.domain.transaction.remote.order.SaleDeliveryGoodsPlanRulesClient;
import com.deepexi.dd.domain.transaction.service.SaleDeliveryPlanRuleService;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryGoodsPlanRulesRequestQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SaleDeliveryPlanRuleServiceImpl implements SaleDeliveryPlanRuleService {
    @Autowired
    private SaleDeliveryGoodsPlanRulesClient saleDeliveryGoodsPlanRulesClient;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    private static String RULE_NAME="订货周期";

    @Override
    public SaleDeliveryGoodsPlanRulesResponseDTO getSaleDeliveryGoodsPlanRule(BaseVO baseVO) throws Exception {
        SaleDeliveryGoodsPlanRulesRequestQuery query = new SaleDeliveryGoodsPlanRulesRequestQuery();
        query.setRuleName(RULE_NAME);
        query.setAppId(baseVO.getAppId());
        query.setTenantId(baseVO.getTenantId());
        List<SaleDeliveryGoodsPlanRulesResponseDTO> saleDeliveryGoodsPlanRulesResponseDTOS = saleDeliveryGoodsPlanRulesClient.listSaleDeliveryGoodsPlanRules(query);
        if (CollectionUtil.isNotEmpty(saleDeliveryGoodsPlanRulesResponseDTOS)) {
            return saleDeliveryGoodsPlanRulesResponseDTOS.get(0);
        } else {
            SaleDeliveryGoodsPlanRulesRequestDTO savePlan = this.buildInitData(baseVO);
            return saleDeliveryGoodsPlanRulesClient.insert(savePlan);
        }
    }

    @Override
    public Boolean setSaleDeliveryGoodsPlanRuleEnable(RuleControl control) throws Exception{
        SaleDeliveryGoodsPlanRulesResponseDTO planRulesResponseDTO= getSaleDeliveryGoodsPlanRule(control);
        SaleDeliveryGoodsPlanRulesRequestDTO updateDTO=new SaleDeliveryGoodsPlanRulesRequestDTO();
        updateDTO.setIsEnabled(control.getIsEnabled());
        saleDeliveryGoodsPlanRulesClient.updateById(planRulesResponseDTO.getId(),updateDTO);
        return true;
    }

    private SaleDeliveryGoodsPlanRulesRequestDTO buildInitData(BaseVO baseVO) {
        SaleDeliveryGoodsPlanRulesRequestDTO saveRule = new SaleDeliveryGoodsPlanRulesRequestDTO();
        String userId=appRuntimeEnv.getUserId().toString();
        saveRule.setAppId(baseVO.getAppId());
        saveRule.setTenantId(baseVO.getTenantId());
        saveRule.setCreatedTime(new Date());
        saveRule.setCreatedBy(userId);
        saveRule.setDeleted(false);
        saveRule.setIsEnabled(0l);
        saveRule.setRemark("启用后，计划下月订货默认周期为每月的20日-下月10日");
        saveRule.setUpdatedBy(userId);
        saveRule.setUpdatedTime(new Date());
        saveRule.setRuleName(RULE_NAME);
        saveRule.setDeliveryWay("0");
        saveRule.setRule("=");
        saveRule.setValue(2010l);
        return saveRule;
    }
}
