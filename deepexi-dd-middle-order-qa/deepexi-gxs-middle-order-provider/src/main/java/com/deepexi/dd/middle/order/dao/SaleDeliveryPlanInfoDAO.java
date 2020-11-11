package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanInfoDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoQuery;

import java.util.List;


/**
 * SaleDeliveryPlanInfoDAO
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
public interface SaleDeliveryPlanInfoDAO {

    /**
     * 查询发货计划表列表}
     *
     * @return
     */
    List<SaleDeliveryPlanInfoDO> listSaleDeliveryPlanInfos(SaleDeliveryPlanInfoQuery query);

    /**
     * 根据ID删除发货计划表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增发货计划表
     *
     * @param record
     * @return
     */
    int insert(SaleDeliveryPlanInfoDO record);

    /**
     * 查询发货计划表详情
     *
     * @param id
     * @return
     */
    SaleDeliveryPlanInfoDO selectById(Long id);

    /**
     * 根据ID修改发货计划表
     *
     * @param record
     * @return
     */
    int updateById(SaleDeliveryPlanInfoDO record);

}