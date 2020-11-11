package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryGoodsPlanRulesResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryGoodsPlanRulesRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SaleDeliveryGoodsPlanRulesApi
 *
 * @author admin
 * @date Wed Aug 12 14:26:43 CST 2020
 * @version 1.0
 */
@Api(value = "发货计划编排规则管理")
@RequestMapping("/saleDeliveryGoodsPlanRuless")
public interface SaleDeliveryGoodsPlanRulesApi {
    /**
     * 查询发货计划编排规则列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleDeliveryGoodsPlanRulesResponseDTO> listSaleDeliveryGoodsPlanRules(SaleDeliveryGoodsPlanRulesRequestQuery query) throws Exception;

    /**
     * 分页查询发货计划编排规则列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleDeliveryGoodsPlanRulesResponseDTO> listSaleDeliveryGoodsPlanRulesPage(SaleDeliveryGoodsPlanRulesRequestQuery query) throws Exception;


    /**
     * 根据ID删除发货计划编排规则
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id) throws Exception;

    /**
     * 新增发货计划编排规则
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleDeliveryGoodsPlanRulesResponseDTO insert(@RequestBody SaleDeliveryGoodsPlanRulesRequestDTO record) throws Exception;

    /**
     * 查询发货计划编排规则详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleDeliveryGoodsPlanRulesResponseDTO selectById(@PathVariable Long id) throws Exception;


    /**
     * 根据ID修改发货计划编排规则
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleDeliveryGoodsPlanRulesRequestDTO record) throws Exception;

}