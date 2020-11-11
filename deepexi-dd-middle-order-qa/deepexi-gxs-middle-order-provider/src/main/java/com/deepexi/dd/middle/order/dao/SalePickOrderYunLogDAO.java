package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogDO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogQuery;

import java.util.List;


/**
 * SalePickOrderYunLogDAO
 *
 * @author admin
 * @date Thu Aug 27 21:37:43 CST 2020
 * @version 1.0
 */
public interface SalePickOrderYunLogDAO {

    /**
     * 查询列表}
     *
     * @return
     */
    List<SalePickOrderYunLogDO> listSalePickOrderYunLogs(SalePickOrderYunLogQuery query);

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
    int insert(SalePickOrderYunLogDO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SalePickOrderYunLogDO selectById(Long id);

    /**
     * 根据ID修改
     *
     * @param record
     * @return
     */
    int updateById(SalePickOrderYunLogDO record);

}