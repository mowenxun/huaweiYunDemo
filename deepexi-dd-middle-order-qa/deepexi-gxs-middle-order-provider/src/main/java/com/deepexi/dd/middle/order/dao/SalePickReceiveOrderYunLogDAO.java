package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogDO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogQuery;

import java.util.List;


/**
 * SalePickReceiveOrderYunLogDAO
 *
 * @author admin
 * @date Wed Sep 23 13:47:55 CST 2020
 * @version 1.0
 */
public interface SalePickReceiveOrderYunLogDAO {

    /**
     * 查询列表}
     *
     * @return
     */
    List<SalePickReceiveOrderYunLogDO> listSalePickReceiveOrderYunLogs(SalePickReceiveOrderYunLogQuery query);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(SalePickReceiveOrderYunLogDO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SalePickReceiveOrderYunLogDO selectById(Long id);

    /**
     * 根据ID修改
     *
     * @param record
     * @return
     */
    int updateById(SalePickReceiveOrderYunLogDO record);

}