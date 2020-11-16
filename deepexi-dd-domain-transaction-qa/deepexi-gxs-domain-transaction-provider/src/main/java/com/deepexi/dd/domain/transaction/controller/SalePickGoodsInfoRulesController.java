package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.tool.domain.dto.BaseVO;
import com.deepexi.dd.domain.transaction.domain.dto.RuleControl;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderSkuRespDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.domain.transaction.remote.order.SaleDeliveryGoodsPlanRulesClient;
import com.deepexi.dd.domain.transaction.service.SaleDeliveryPlanRuleService;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryGoodsPlanRulesRequestQuery;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.h2.bnf.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-17 18:06
 */
@RestController
@Api(tags = "订货计划规则管理API【运营中心】")
@RequestMapping("admin-api/v1/domain/transaction/orderPlanningRules")
@Slf4j
public class SalePickGoodsInfoRulesController {
//    @Autowired
//    private SaleDeliveryGoodsPlanRulesClient saleDeliveryGoodsPlanRulesClient;
//    @Autowired
//    private AppRuntimeEnv appRuntimeEnv;

//    @ApiOperation("查询、修改【订货周期】")
//    @GetMapping("/controlRule")
//    public Payload<SaleDeliveryGoodsPlanRulesResponseDTO> getSaleDeliveryGoodsPlanRule(@Valid RuleControl operationType) throws Exception {
//        SaleDeliveryGoodsPlanRulesRequestQuery query = new SaleDeliveryGoodsPlanRulesRequestQuery();
//        query.setRuleName("订货周期");
//        List<SaleDeliveryGoodsPlanRulesResponseDTO> saleDeliveryGoodsPlanRulesResponseDTOS = saleDeliveryGoodsPlanRulesClient.listSaleDeliveryGoodsPlanRules(query);
//        if (!ObjectUtils.isEmpty(operationType) && operationType.getType() == 0) {
//            if (!ObjectUtils.isEmpty(saleDeliveryGoodsPlanRulesResponseDTOS)) {
//                return new Payload<>(saleDeliveryGoodsPlanRulesResponseDTOS.get(0));
//            }
//        } else if (!ObjectUtils.isEmpty(operationType) && operationType.getType() == 1) {
//            if (!ObjectUtils.isEmpty(saleDeliveryGoodsPlanRulesResponseDTOS)) {
//                if (!ObjectUtils.isEmpty(operationType.getIsEnabled()))
//                    saleDeliveryGoodsPlanRulesResponseDTOS.get(0).setIsEnabled(operationType.getIsEnabled());
//
//                if (!ObjectUtils.isEmpty(operationType.getOrderCycle())) {
//                    saleDeliveryGoodsPlanRulesResponseDTOS.get(0).setValue(Long.parseLong(operationType.getOrderCycle()));
//                    String startTime = operationType.getOrderCycle().substring(0, operationType.getOrderCycle().length() - 2);
//                    String endTime = operationType.getOrderCycle().substring(operationType.getOrderCycle().length() - 2, operationType.getOrderCycle().length());
//                    saleDeliveryGoodsPlanRulesResponseDTOS.get(0).setRemark("启用后，计划下月订货默认周期为每月的+" + startTime + "日-下月+" + endTime + "日");
//                    saleDeliveryGoodsPlanRulesResponseDTOS.get(0).setUpdatedTime(new Date());
//                }
//                Boolean isOk = saleDeliveryGoodsPlanRulesClient.updateById(saleDeliveryGoodsPlanRulesResponseDTOS.get(0).getId(),
//                                                                           saleDeliveryGoodsPlanRulesResponseDTOS.get(0).clone(SaleDeliveryGoodsPlanRulesRequestDTO.class));
//                log.info("更改订货周期状态:{}", JsonUtil.bean2JsonString(saleDeliveryGoodsPlanRulesResponseDTOS.get(0)), isOk);
//            }
//        }
//        return new Payload<>();
//    }

//    @ApiOperation("初始化订货周期")
//    @GetMapping("/initControlRule")
//    public Payload<Boolean> getSaleDeliveryGoodsPlanRule() throws Exception {
//        SaleDeliveryGoodsPlanRulesRequestQuery query = new SaleDeliveryGoodsPlanRulesRequestQuery();
//        query.setRuleName("订货周期");
//        List<SaleDeliveryGoodsPlanRulesResponseDTO> saleDeliveryGoodsPlanRulesResponseDTOS = saleDeliveryGoodsPlanRulesClient.listSaleDeliveryGoodsPlanRules(query);
//        if (!ObjectUtils.isEmpty(saleDeliveryGoodsPlanRulesResponseDTOS)) {
//            throw new ApplicationException("已存在订货周期规则，请勿重复操作");
//        } else {
//            SaleDeliveryGoodsPlanRulesRequestDTO savePlan = this.buildInitData();
//            SaleDeliveryGoodsPlanRulesResponseDTO insert = saleDeliveryGoodsPlanRulesClient.insert(savePlan);
//            Optional.ofNullable(insert).orElseThrow(() -> new ApplicationException("订货周期规则初始化失败"));
//        }
//        return new Payload<>(true);
//    }



//    private SaleDeliveryGoodsPlanRulesRequestDTO getInitData(RuleControl ruleControl) {
//        SaleDeliveryGoodsPlanRulesRequestDTO saveRule = new SaleDeliveryGoodsPlanRulesRequestDTO();
//        String userId=appRuntimeEnv.getUserId().toString();
//        saveRule.setAppId(ruleControl.getAppId());
//        saveRule.setTenantId(ruleControl.getTenantId());
//        saveRule.setCreatedTime(new Date());
//        saveRule.setCreatedBy(userId);
//        saveRule.setDeleted(false);
//        saveRule.setIsEnabled(ruleControl.getIsEnabled());
//        saveRule.setRemark("启用后，计划下月订货默认周期为每月的20日-下月10日");
//        saveRule.setUpdatedBy(userId);
//        saveRule.setUpdatedTime(new Date());
//        saveRule.setRuleName("订货周期");
//        saveRule.setDeliveryWay("0");
//        saveRule.setRule("=");
//        saveRule.setValue(1020l);
//        return saveRule;
//    }
    @Autowired
    private SaleDeliveryPlanRuleService saleDeliveryPlanRuleService;

    @ApiOperation("查询【订货周期】")
    @GetMapping("/getPlanRule")
    public Payload<SaleDeliveryGoodsPlanRulesResponseDTO> getSaleDeliveryGoodsPlanRule(@Valid BaseVO baseVO) throws Exception {
        RuleControl ruleControl=baseVO.clone(RuleControl.class);
        return new Payload<>(saleDeliveryPlanRuleService.getSaleDeliveryGoodsPlanRule(ruleControl));
    }

    @ApiOperation("设置【订货周期】")
    @PostMapping("/setPlanRuleEnable")
    public Payload<Boolean> setPlanRuleEnable(@Valid @RequestBody RuleControl ruleControl) throws Exception {
        return new Payload<>(saleDeliveryPlanRuleService.setSaleDeliveryGoodsPlanRuleEnable(ruleControl));
    }
}
