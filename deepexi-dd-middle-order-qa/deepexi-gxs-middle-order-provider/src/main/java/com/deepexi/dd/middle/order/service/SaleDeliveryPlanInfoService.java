package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanInfoDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleDeliveryPlanInfoService
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
public interface SaleDeliveryPlanInfoService {


    /**
     * 查询发货计划表列表
     *
     * @return
     */
    List<SaleDeliveryPlanInfoDTO> listSaleDeliveryPlanInfos(SaleDeliveryPlanInfoQuery query);

    /**
     * 分页查询发货计划表列表
     *
     * @return
     */
    PageBean<SaleDeliveryPlanInfoDTO> listSaleDeliveryPlanInfosPage(SaleDeliveryPlanInfoQuery query);

    /**
     * 根据ID删除发货计划表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增发货计划表
     *
     * @param record
     * @return
     */
    SaleDeliveryPlanInfoDTO insert(SaleDeliveryPlanInfoDTO record);

    /**
     * 查询发货计划表详情
     *
     * @param id
     * @return
     */
    SaleDeliveryPlanInfoDTO selectById(Long id);


    /**
     * 根据ID修改发货计划表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleDeliveryPlanInfoDTO record);

}