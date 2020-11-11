package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanItemDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleDeliveryPlanItemService
 *
 * @author admin
 * @date Thu Aug 13 16:42:15 CST 2020
 * @version 1.0
 */
public interface SaleDeliveryPlanItemService {


    /**
     * 查询发货计划明细表列表
     *
     * @return
     */
    List<SaleDeliveryPlanItemDTO> listSaleDeliveryPlanItems(SaleDeliveryPlanItemQuery query);

    /**
     * 分页查询发货计划明细表列表
     *
     * @return
     */
    PageBean<SaleDeliveryPlanItemDTO> listSaleDeliveryPlanItemsPage(SaleDeliveryPlanItemQuery query);

    /**
     * 根据ID删除发货计划明细表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增发货计划明细表
     *
     * @param record
     * @return
     */
    SaleDeliveryPlanItemDTO insert(SaleDeliveryPlanItemDTO record);

    /**
     * 查询发货计划明细表详情
     *
     * @param id
     * @return
     */
    SaleDeliveryPlanItemDTO selectById(Long id);


    /**
     * 根据ID修改发货计划明细表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleDeliveryPlanItemDTO record);

}