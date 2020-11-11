package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanMainDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanMainQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleDeliveryPlanMainService
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
public interface SaleDeliveryPlanMainService {


    /**
     * 查询发货编排主表列表
     *
     * @return
     */
    List<SaleDeliveryPlanMainDTO> listSaleDeliveryPlanMains(SaleDeliveryPlanMainQuery query);

    /**
     * 分页查询发货编排主表列表
     *
     * @return
     */
    PageBean<SaleDeliveryPlanMainDTO> listSaleDeliveryPlanMainsPage(SaleDeliveryPlanMainQuery query);

    /**
     * 根据ID删除发货编排主表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增发货编排主表
     *
     * @param record
     * @return
     */
    SaleDeliveryPlanMainDTO insert(SaleDeliveryPlanMainDTO record);

    /**
     * 查询发货编排主表详情
     *
     * @param id
     * @return
     */
    SaleDeliveryPlanMainDTO selectById(Long id);


    /**
     * 根据ID修改发货编排主表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleDeliveryPlanMainDTO record);

}