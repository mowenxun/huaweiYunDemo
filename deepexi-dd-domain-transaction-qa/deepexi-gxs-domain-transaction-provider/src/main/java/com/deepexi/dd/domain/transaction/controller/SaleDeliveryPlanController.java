package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan.SaleDeliveryPlanRulesRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleDeliveryPlan.SaleDeliveryPlanRulesResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleDeliveryPlanRulesRequestQuery;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.service.SaleDeliveryPlanService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName SaleDeliveryPlanController
 * @Description 发货计划
 * @Author SongTao
 * @Date 2020-08-12
 * @Version 1.0
 **/
@RestController
@Api(tags = "发货计划")
@RequestMapping("/admin-api/v1/domain/transaction/saleDeliveryPlan")
public class SaleDeliveryPlanController {

    @Autowired
    private SaleDeliveryPlanService saleDeliveryPlanService;

    @ApiOperation("分页查询发货编排规则")
    @GetMapping("/pageRules")
    public Payload<PageBean<SaleDeliveryPlanRulesResponseDTO>> saleDeliveryGoodsPlanRules(@Valid SaleDeliveryPlanRulesRequestQuery query) throws Exception {
        PageBean<SaleDeliveryPlanRulesResponseDTO> pageBean = saleDeliveryPlanService.saleDeliveryGoodsPlanRules(query);
        return new Payload<>(pageBean);
    }
    @ApiOperation("新增发货编排规则")
    @PostMapping("/saveRules")
    public Payload<Boolean> saveDeliveryGoodsPlanRules(@RequestBody @Valid SaleDeliveryPlanRulesRequestDTO requestDTO) throws Exception{
        if(Objects.isNull(requestDTO)){
            throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
        }
        return new Payload<>(saleDeliveryPlanService.insertDeliveryGoodsPlanRules(requestDTO));
    }

    @ApiOperation("修改发货编排规则")
    @PutMapping("/updateRules/{id}")
    public Payload<Boolean> updateDeliveryGoodsPlanRules(@PathVariable("id") Long id, @RequestBody @Valid SaleDeliveryPlanRulesRequestDTO record)throws Exception {
        if(Objects.isNull(id)){
            throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
        }
        return new Payload<>(saleDeliveryPlanService.updateDeliveryGoodsPlanRules(id,record));
    }

    @ApiOperation("批量删除销售订单表")
    @DeleteMapping("/deleteRules")
    public Payload<Boolean> deleteDeliveryGoodsPlanRules(@RequestBody List<Long> id) throws Exception {
        return new Payload<Boolean>(saleDeliveryPlanService.deleteDeliveryGoodsPlanRules(id));
    }

}
