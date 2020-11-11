package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleDeliveryGoodsPlanRulesApi;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesDTO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryGoodsPlanRulesRequestQuery;
import com.deepexi.dd.middle.order.service.SaleDeliveryGoodsPlanRulesService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * SaleDeliveryGoodsPlanRulesApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleDeliveryGoodsPlanRulesApiImpl implements SaleDeliveryGoodsPlanRulesApi {

    @Autowired
    private SaleDeliveryGoodsPlanRulesService saleDeliveryGoodsPlanRulesService;

    @Override
    @ApiOperation("查询发货计划编排规则列表")
    public List<SaleDeliveryGoodsPlanRulesResponseDTO> listSaleDeliveryGoodsPlanRules(SaleDeliveryGoodsPlanRulesRequestQuery query) throws Exception{
        return ObjectCloneUtils
                .convertList(saleDeliveryGoodsPlanRulesService
                    .listSaleDeliveryGoodsPlanRules(query.clone(SaleDeliveryGoodsPlanRulesQuery.class, CloneDirection.OPPOSITE)),
                                    SaleDeliveryGoodsPlanRulesResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询发货计划编排规则列表")
    public PageBean<SaleDeliveryGoodsPlanRulesResponseDTO> listSaleDeliveryGoodsPlanRulesPage(SaleDeliveryGoodsPlanRulesRequestQuery query) throws Exception{
        return ObjectCloneUtils
                .convertPageBean(saleDeliveryGoodsPlanRulesService
                    .listSaleDeliveryGoodsPlanRulesPage(query.clone(SaleDeliveryGoodsPlanRulesQuery.class, CloneDirection.OPPOSITE)),
                        SaleDeliveryGoodsPlanRulesResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除发货计划编排规则")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) throws Exception{
        return saleDeliveryGoodsPlanRulesService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增发货计划编排规则")
    public SaleDeliveryGoodsPlanRulesResponseDTO insert(@RequestBody SaleDeliveryGoodsPlanRulesRequestDTO record) throws Exception{
        return saleDeliveryGoodsPlanRulesService.insert(record.clone(SaleDeliveryGoodsPlanRulesDTO.class, CloneDirection.OPPOSITE))
                .clone(SaleDeliveryGoodsPlanRulesResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询发货计划编排规则")
    public SaleDeliveryGoodsPlanRulesResponseDTO selectById(@PathVariable Long id) throws Exception{
        SaleDeliveryGoodsPlanRulesDTO result = saleDeliveryGoodsPlanRulesService.selectById(id);
        return result != null ? result.clone(SaleDeliveryGoodsPlanRulesResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新发货计划编排规则")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleDeliveryGoodsPlanRulesRequestDTO record) throws Exception{
        return saleDeliveryGoodsPlanRulesService.updateById(id, record.clone(SaleDeliveryGoodsPlanRulesDTO.class, CloneDirection.OPPOSITE));
    }
}
