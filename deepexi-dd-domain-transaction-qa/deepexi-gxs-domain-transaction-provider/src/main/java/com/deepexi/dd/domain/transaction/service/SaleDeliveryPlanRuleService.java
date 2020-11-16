package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.tool.domain.dto.BaseVO;
import com.deepexi.dd.domain.transaction.domain.dto.RuleControl;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesResponseDTO;
import com.deepexi.util.config.Payload;

public interface SaleDeliveryPlanRuleService {
    SaleDeliveryGoodsPlanRulesResponseDTO getSaleDeliveryGoodsPlanRule(BaseVO baseVO) throws Exception;

    Boolean setSaleDeliveryGoodsPlanRuleEnable(RuleControl control) throws Exception;
}
