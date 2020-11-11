package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanItemDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemQuery;

import java.util.List;


/**
 * SaleDeliveryPlanItemDAO
 *
 * @author admin
 * @date Thu Aug 13 16:42:15 CST 2020
 * @version 1.0
 */
public interface SaleDeliveryPlanItemDAO {

    /**
     * 查询发货计划明细表列表}
     *
     * @return
     */
    List<SaleDeliveryPlanItemDO> listSaleDeliveryPlanItems(SaleDeliveryPlanItemQuery query);

    /**
     * 根据ID删除发货计划明细表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增发货计划明细表
     *
     * @param record
     * @return
     */
    int insert(SaleDeliveryPlanItemDO record);

    /**
     * 查询发货计划明细表详情
     *
     * @param id
     * @return
     */
    SaleDeliveryPlanItemDO selectById(Long id);

    /**
     * 根据ID修改发货计划明细表
     *
     * @param record
     * @return
     */
    int updateById(SaleDeliveryPlanItemDO record);

}