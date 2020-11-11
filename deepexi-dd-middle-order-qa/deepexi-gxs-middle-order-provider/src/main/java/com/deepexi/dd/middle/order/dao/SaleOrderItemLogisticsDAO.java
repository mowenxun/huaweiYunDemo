package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SaleOrderItemLogisticsDO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemLogisticsQuery;

import java.util.List;


/**
 * SaleOrderItemLogisticsDAO
 *
 * @author admin
 * @date Sat Aug 22 16:34:04 CST 2020
 * @version 1.0
 */
public interface SaleOrderItemLogisticsDAO {

    /**
     * 查询OMS物流信息列表}
     *
     * @return
     */
    List<SaleOrderItemLogisticsDO> listSaleOrderItemLogisticss(SaleOrderItemLogisticsQuery query);

    /**
     * 根据ID删除OMS物流信息
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增OMS物流信息
     *
     * @param record
     * @return
     */
    int insert(SaleOrderItemLogisticsDO record);

    /**
     * 查询OMS物流信息详情
     *
     * @param id
     * @return
     */
    SaleOrderItemLogisticsDO selectById(Long id);

    /**
     * 根据ID修改OMS物流信息
     *
     * @param record
     * @return
     */
    int updateById(SaleOrderItemLogisticsDO record);

}