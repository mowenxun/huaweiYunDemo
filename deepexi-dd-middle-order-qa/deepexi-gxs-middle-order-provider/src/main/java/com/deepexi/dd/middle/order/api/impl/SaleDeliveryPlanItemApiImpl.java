package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleDeliveryPlanItemApi;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanItemDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemQuery;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemRequestQuery;
import com.deepexi.dd.middle.order.service.SaleDeliveryPlanItemService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SaleDeliveryPlanItemApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleDeliveryPlanItemApiImpl implements SaleDeliveryPlanItemApi {

    @Autowired
    private SaleDeliveryPlanItemService saleDeliveryPlanItemService;

    @Override
    @ApiOperation("查询发货计划明细表列表")
    public List<SaleDeliveryPlanItemResponseDTO> listSaleDeliveryPlanItems(SaleDeliveryPlanItemRequestQuery query) {
        List<SaleDeliveryPlanItemDTO> saleDeliveryPlanItemDTOS = saleDeliveryPlanItemService
                .listSaleDeliveryPlanItems(query.clone(SaleDeliveryPlanItemQuery.class, CloneDirection.OPPOSITE));
        if (ObjectUtils.isEmpty(saleDeliveryPlanItemDTOS) || saleDeliveryPlanItemDTOS.size() < 0) {
            return null;
        }
        return ObjectCloneUtils
                .convertList(saleDeliveryPlanItemDTOS,
                             SaleDeliveryPlanItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询发货计划明细表列表")
    public PageBean<SaleDeliveryPlanItemResponseDTO> listSaleDeliveryPlanItemsPage(SaleDeliveryPlanItemRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleDeliveryPlanItemService
                                         .listSaleDeliveryPlanItemsPage(query.clone(SaleDeliveryPlanItemQuery.class, CloneDirection.OPPOSITE)),
                                 SaleDeliveryPlanItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除发货计划明细表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleDeliveryPlanItemService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增发货计划明细表")
    public SaleDeliveryPlanItemResponseDTO insert(@RequestBody SaleDeliveryPlanItemRequestDTO record) {
        return saleDeliveryPlanItemService.insert(record.clone(SaleDeliveryPlanItemDTO.class, CloneDirection.OPPOSITE))
                                          .clone(SaleDeliveryPlanItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询发货计划明细表")
    public SaleDeliveryPlanItemResponseDTO selectById(@PathVariable Long id) {
        SaleDeliveryPlanItemDTO result = saleDeliveryPlanItemService.selectById(id);
        return result != null ? result.clone(SaleDeliveryPlanItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新发货计划明细表")
    public Boolean updateById(@PathVariable Long id, @RequestBody SaleDeliveryPlanItemRequestDTO record) {
        return saleDeliveryPlanItemService.updateById(id, record.clone(SaleDeliveryPlanItemDTO.class, CloneDirection.OPPOSITE));
    }
}
