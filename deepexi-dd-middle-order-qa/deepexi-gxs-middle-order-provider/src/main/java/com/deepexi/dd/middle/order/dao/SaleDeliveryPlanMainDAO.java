package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanMainDO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanMainQuery;

import java.util.List;


/**
 * SaleDeliveryPlanMainDAO
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
public interface SaleDeliveryPlanMainDAO {

    /**
     * 查询发货编排主表列表}
     *
     * @return
     */
    List<SaleDeliveryPlanMainDO> listSaleDeliveryPlanMains(SaleDeliveryPlanMainQuery query);

    /**
     * 根据ID删除发货编排主表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增发货编排主表
     *
     * @param record
     * @return
     */
    int insert(SaleDeliveryPlanMainDO record);

    /**
     * 查询发货编排主表详情
     *
     * @param id
     * @return
     */
    SaleDeliveryPlanMainDO selectById(Long id);

    /**
     * 根据ID修改发货编排主表
     *
     * @param record
     * @return
     */
    int updateById(SaleDeliveryPlanMainDO record);

}