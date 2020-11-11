package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SaleDeliveryPlanItemApi
 *
 * @author admin
 * @date Thu Aug 13 16:42:15 CST 2020
 * @version 1.0
 */
@Api(value = "发货计划明细表管理")
@RequestMapping("/saleDeliveryPlanItems")
public interface SaleDeliveryPlanItemApi {
    /**
     * 查询发货计划明细表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleDeliveryPlanItemResponseDTO> listSaleDeliveryPlanItems(SaleDeliveryPlanItemRequestQuery query);

    /**
     * 分页查询发货计划明细表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleDeliveryPlanItemResponseDTO> listSaleDeliveryPlanItemsPage(SaleDeliveryPlanItemRequestQuery query);


    /**
     * 根据ID删除发货计划明细表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增发货计划明细表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleDeliveryPlanItemResponseDTO insert(@RequestBody SaleDeliveryPlanItemRequestDTO record);

    /**
     * 查询发货计划明细表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleDeliveryPlanItemResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改发货计划明细表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleDeliveryPlanItemRequestDTO record);

}